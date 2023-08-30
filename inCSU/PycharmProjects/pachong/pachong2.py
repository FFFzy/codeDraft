# Coded By Lucky
# Time: 2021/1/4 21:29

from bs4 import BeautifulSoup
import requests
def getHtml(url, label, attr):
    response = requests.get(url)
    response.encoding = 'utf-8'
    html = response.text
    soup = BeautifulSoup(html, 'html.parser');
    for target in soup.find_all(label):
        try:
            link = target.get(attr)
            name = target.text
        except:
            link = ''
        if link:
            print(name)
            print(link)

url = 'https://www.baidu.com/'
label = 'a'
attr = 'href'
getHtml(url, label, attr)