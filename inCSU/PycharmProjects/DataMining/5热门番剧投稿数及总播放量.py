# Coded By Lucky
# Time: 2021/4/20 0:37

import csv
from itertools import islice
import pandas as pd
import numpy as np

import os

import matplotlib.pyplot as plt

os.chdir('C:/Users/莫小殇/Desktop/B站挖掘/')
writer = pd.ExcelWriter('热门番剧总信息.xlsx')
fanjus = [line.strip() for line in open('fanju.txt',encoding='utf-8').readlines()]    #搜索关键词文件
hot_fanju = []
for fanju in fanjus:
    key_words = fanju.split('|')
    df = []
    csv_file=open('b站.csv',encoding='UTF-8')
    csv_reader_lines = csv.reader(csv_file)
    for line in islice(csv_reader_lines,1,None):
        if any(words in line[1] for words in key_words):
            df.append(line)
    df1 = pd.DataFrame(df,columns=['UP主','title','pubdate','video_time',
                                  'likes','coins','favorites','shares','views','comments','danmu'])
    df1.to_excel(writer,sheet_name = key_words[0],index=False)
    views_sum = df1['views'].apply(np.int).sum()
    hot_fanju.append([key_words[0],len(df1),views_sum])
    print('{}总播放量为:{}'.format(key_words[0],str(views_sum/10000)+'万'))
hot_fanju = pd.DataFrame(hot_fanju,columns=['番剧','投稿数','播放量'])
hot_fanju.to_excel('热门番剧投稿数及总播放量.xlsx',encoding='gb18030',index=False)
writer.save()
writer.close()
csv_file.close()

def plot_line_bar(data, y1_max, y1_range, y2_max, y2_range):  #双轴组合图
    x = data['番剧']
    y1 = data['播放量']
    y2 = data['投稿数']
    f, ax1 = plt.subplots(figsize=(10, 5))
    ax1.bar(x, y1, color='#348498', label='播放量')
    ax2 = ax1.twinx()  # 双轴图关键
    ax2.grid(False)  # 去掉第二条线的网格
    ax2.plot(x, y2, label='投稿数', color='#F75940', marker='o')
    ax1.set_yticks(np.arange(0, y1_max, y1_range)) #设置主坐标轴范围
    ax1.set_ylabel('播放量')
    ax2.set_yticks(np.arange(0, y2_max, y2_range))  # 设置次坐标轴范围
    ax2.set_ylabel('投稿数')
    for tl in ax1.get_xticklabels():  # 旋转横轴标签
        tl.set_rotation(90)
    ax1.legend(loc=2)
    ax2.legend(loc=1)  # loc1代表放置右上方
    plt.title('2020年Bilibili动画区视频热门番剧')
    plt.rcParams['font.sans-serif'] = ['SimHei']  # 黑体
    plt.show()

if __name__ == '__main__':
    data = pd.read_excel("热门番剧投稿数及总播放量.xlsx")
    plot_line_bar(data, 300000, 50000,200,20)
