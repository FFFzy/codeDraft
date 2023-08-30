# Coded By Lucky
# Time: 2021/1/4 20:57

import requests
from bs4 import BeautifulSoup
import os

proxies = {'https': 'http://41.118.132.69:4433'}
hd = {'User-Agent': "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)"}
url = 'http://news.csu.edu.cn/'

req = requests.get(url, headers=hd, proxies=proxies)
bs = BeautifulSoup(req.content, 'html.parser')
div_all = bs.find_all('div', attrs={'class': 'nav'})

for infos in div_all:
    a_all = infos.find_all('a')  # 在div标签里找到所有的a标签

for a in a_all[0:10]:  # 获取其中10个a标签的信息
    ind_code = a.get('href')
    ind_name = a.text  # 获取a标签的文本内容
    print(ind_name)
    print(url+ind_code)
