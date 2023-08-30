# Coded By Lucky
# Time: 2021/4/19 22:15

import numpy as np
import aiohttp
import asyncio
import pandas as pd
import os
import random
import time
import json


os.chdir('C:/Users/莫小殇/Desktop/B站挖掘/')

with open('C:/Users/莫小殇/Desktop/B站挖掘/aid1.txt','r') as f:  # 将爬取得到aid保存为aid.txt
    aid = f.readlines()
df1 = []
aid_list = np.char.strip(aid,'\n')
semaphore = asyncio.Semaphore(10)  # 限制并发量
head=[
    "Mozilla/5.0 (Windows NT 6.0; rv:2.0) Gecko/20100101 Firefox/4.0 Opera 12.14",
    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0) Opera 12.14",
    "Opera/12.80 (Windows NT 5.1; U; en) Presto/2.10.289 Version/12.02",
    "Opera/9.80 (Windows NT 6.1; U; es-ES) Presto/2.9.181 Version/12.00",
    "Opera/9.80 (Windows NT 5.1; U; zh-sg) Presto/2.9.181 Version/12.00",
]
header={
    'user-agent':random.choice(head),
    'refer':'https://www.bilibili.com/'
}
async def get_page(url):
    await asyncio.sleep(2)
    async with aiohttp.ClientSession() as session:
        async with await session.get(url, headers=header) as r:
            r_text = await r.text()
            return r_text

async def parse_page(text):
        try:
            data = json.loads(text)
            name = data['data']['owner']['name']
            video_name = data['data']['title']
            release_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(data['data']['pubdate'])) #时间戳转化
            like = data['data']['stat']['like']
            coin = data['data']['stat']['coin']
            collection = data['data']['stat']['favorite']
            share = data['data']['stat']['share']
            view = data['data']['stat']['view']
            dm = data['data']['stat']['danmaku']
            df1.append([name,video_name,release_time,like,coin,collection,share,view,dm])
        except:
            print('未爬到数据')

async def get_data(url):
    async with semaphore:
        text = await get_page(url)
        await parse_page(text)
    print('下载完成:', url)

def main():
    url_list = ['https://api.bilibili.com/x/web-interface/view?aid='+aid for aid in aid_list]
    tasks = [asyncio.ensure_future(get_data(url)) for url in url_list]
    loop = asyncio.get_event_loop()
    loop.run_until_complete(asyncio.wait(tasks))
    loop.close()

if __name__=='__main__':
    start = time.time()
    main()
    df1 = pd.DataFrame(df1,columns=['UP主','视频名称','发布时间','点赞数','硬币数','收藏数','分享数','播放量','弹幕数'])
    df1.to_csv('b站.csv',encoding='gb18030',index=False)
    end = time.time()
    print('共花费{}分钟'.format(round((end-start)/60,2)))

