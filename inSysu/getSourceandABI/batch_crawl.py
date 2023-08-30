import csv
from time import sleep
import requests as rq
import json
import os
import pandas as pd

def make_dir(path):
    folders = []
    while not os.path.isdir(path):
        path, suffix = os.path.split(path)
        folders.append(suffix)
    for folder in folders[::-1]:
        path = os.path.join(path, folder)
        os.mkdir(path)

def is_json(myjson):
    try:
        json_object = json.loads(myjson)
    except ValueError as e:
        return False
    return True


def is_nft(source):
    keywords = 'nft,NFT,ERC721,IERC721'
    return any([w in source and w for w in keywords.split(',')])

send_headers = {
    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36",
    "Connection": "keep-alive",
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
    "Accept-Language": "zh-CN,zh;q=0.9"}

contract_data = []

def download_url(url, retry=5, retry_delay=5, throw_if_fail=False):
    resp = rq.get(url, headers=send_headers, allow_redirects=False)

    if resp.status_code != 200:
        if retry > 0:
            sleep(retry_delay)
            f'Download source failed for {url}, status {resp.status_code}, retry in {retry_delay} secs'
            return download_url(url, retry-1, retry_delay)
        else:
            if throw_if_fail:
                raise Exception(f'Download source abort for {url}, status {resp.status_code}')
            return
    return resp

def process_csv(filename, csv_file):
    with open(filename) as f:
        render = csv.reader(f)  # reader(迭代器对象)--> 迭代器对象
        # 取表头
        note = next(render)
        header_row = next(render)
        print(header_row)
        root = "./crawl/"+csv_file+"/"
        # 3 = begin index
        index = 3
        for row in render:
            contract_address = row[4]
            contract_name = row[3]
            index += 1
            print(contract_address)
            print(contract_name)
            curl_link = 'https://api.etherscan.io/api?module=contract&action=getsourcecode&address=' + \
                    contract_address+'&apikey=BEYXCX8H6CDFDE5MUU4WQ6NUTIUKM1ZVQW'
            print(curl_link)
            
            output = download_url(curl_link)
            json_res = output.json()
            print(json_res)

            if "result" in json_res:
                source_code = json_res["result"][0]["SourceCode"]
                if not is_nft(source_code):
                    continue
                make_dir(root+contract_address)
                if is_json(source_code):
                    res = json.loads(source_code)
                    for key in res:
                        sol_file = open(
                            root+contract_address+"/"+key, 'w', encoding='UTF-8')
                        sol_file.write(res[key]["content"])
                        if "contract "+contract_name in res[key]["content"]:
                            contract_data_element = [contract_address, contract_name, key]
                            # contract_data.append(contract_data_element)
                            df = pd.DataFrame([contract_data_element],columns=['ContractAddress','ContractName','ContractPath'])
                            df.to_csv('./contract.csv',sep=',',mode='a',index=False,header=None)
                        sol_file.close()
                elif source_code[0] == source_code[1] == "{":
                    new_code = source_code[1:-1]
                    res = json.loads(new_code)
                    sources = res["sources"]
                    for name in sources:
                        # print(name)

                        _dir, _file = os.path.split(
                            root+contract_address+"/"+name)
                        # print(_dir)
                        make_dir(_dir)
                        sol_file = open(
                            root+contract_address+"/"+name, 'w', encoding='UTF-8')
                        sol_file.write(sources[name]["content"])
                        
                        if "contract "+contract_name in sources[name]["content"]:
                            contract_data_element = [contract_address, contract_name, name]
                            # contract_data.append(contract_data_element)
                            df = pd.DataFrame([contract_data_element],columns=['ContractAddress','ContractName','ContractPath'])
                            df.to_csv('./contract.csv',sep=',',mode='a',index=False,header=None)
                        sol_file.close()
                else:
                    sol_file = open(root+contract_address + "/" +
                                contract_name + ".sol", 'w', encoding='UTF-8')
                    sol_file.write(source_code)
                    contract_data_element = [contract_address, contract_name, contract_name]
                    df = pd.DataFrame([contract_data_element],columns=['ContractAddress','ContractName','ContractPath'])
                    df.to_csv('./contract.csv',sep=',',mode='a',index=False,header=None)
                    sol_file.close()

if __name__ == "__main__":
    # for file in files:
    name = "dataset.csv"
    make_dir("./crawl/"+name)
    process_csv(name, name)
    