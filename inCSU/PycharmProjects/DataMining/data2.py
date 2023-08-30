# Coded By Lucky
# Time: 2021/4/19 22:44

import requests
import json
import time
import pandas as pd
import os
import random
os.chdir('C:/Users/莫小殇/Desktop/B站挖掘/')
url = 'https://s.search.bilibili.com/cate/search?'
head=[
    "Mozilla/5.0 (Windows NT 6.0; rv:2.0) Gecko/20100101 Firefox/4.0 Opera 12.14",
    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0) Opera 12.14",
    "Opera/12.80 (Windows NT 5.1; U; en) Presto/2.10.289 Version/12.02",
    "Opera/9.80 (Windows NT 6.1; U; es-ES) Presto/2.9.181 Version/12.00",
    "Opera/9.80 (Windows NT 5.1; U; zh-sg) Presto/2.9.181 Version/12.00",
]
headers={
    'user-agent':random.choice(head),
    'refer':'https://www.bilibili.com/'
}
def get_inf(url,headers,num):
    df = []
    for page in range(num):
        params = {
            'main_ver': 'v3',
            'search_type': 'video',
            'view_type': 'hot_rank',
            'order': 'click',
            'copy_right': -1,
            'cate_id': 24,
            'page': page,
            'pagesize': 20,
            'jsonp': 'jsonp',
            'time_from': 20200101,
            'time_to': 20200331
        }
        try:
            r = requests.get(url,headers=headers,params=params)
            data = json.loads(r.text)
            inf_list = data['result']
            for i in range(len(inf_list)):
                author = data['result'][i]['author']
                duration = data['result'][i]['duration']
                collection = data['result'][i]['favorites']
                aid = data['result'][i]['id']
                title = data['result'][i]['title']
                pubdate = data['result'][i]['pubdate']
                review = data['result'][i]['review']
                play = data['result'][i]['play']
                df.append([author,title,pubdate,aid,duration,play,review,collection])
            print('第{}页爬取完毕'.format(page+1))
            time.sleep(random.randint(1,2))
        except:
            print('未爬到数据')
    df = pd.DataFrame(df,columns=['UP主','视频标题','发布时间','aid','视频时长','播放量','评论数','收藏数'])
    df.to_csv('bilibili.csv',encoding='gb18030',index=False)
    print('共{}条热门视频信息'.format(len(df)))

if __name__=='__main__':
    start = time.time()
    num = 100
    get_inf(url,headers,num)
    end = time.time()
    print('共花费{}分钟'.format(round((end-start)/60,2)))
