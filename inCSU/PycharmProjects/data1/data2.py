# Coded By Lucky
# Time: 2021/4/19 18:59

import requests
import re
from bs4 import BeautifulSoup as BS
import os

path = 'C:/Users/dell/Desktop/柯南'
if os.path.exists(path) == False:
    os.makedirs(path)
os.chdir(path)


def gethtml(url, header):
    r = requests.get(url, headers=header)
    r.encoding = 'utf-8'
    return r.text


def crawl_comments(r_text):
    txt1 = gethtml(url, header)
    pat = '"cid":(\d+)'
    chapter_total = re.findall(pat, txt1)[1:-2]
    count = 1
    for chapter in chapter_total:
        url_base = 'http://comment.bilibili.com/{}.xml'.format(chapter)
        txt2 = gethtml(url_base, header)
        soup = BS(txt2, 'lxml')
        all_d = soup.find_all('d')
        with open('{}.txt'.format(count), 'w', encoding='utf-8') as f:
            for d in all_d:
                f.write(d.get_text() + '\n')
        print('第{}话弹幕写入完毕'.format(count))
        count += 1


if __name__ == '__main__':
    url = 'https://www.bilibili.com/bangumi/play/ep321808'
    header = {'user-agent': 'Opera/12.80 (Windows NT 5.1; U; en) Presto/2.10.289 Version/12.02'}
    r_text = gethtml(url, header)
    crawl_comments(r_text)

