# Coded By Lucky
# Time: 2021/4/20 1:35
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np
import os
os.chdir('C:/Users/莫小殇/Desktop/B站挖掘/')

data = pd.read_csv('b站.csv',parse_dates=['pubdate'],encoding='utf-8')
data1 =  data.dropna()
data1 = data1.sort_values(by='pubdate',ascending=False)  #以pubdate逆序排列
def get_first(x):
    return x.iloc[0,:]
df1 = data1.groupby('UP主').apply(get_first)

def fans_label(x):
    if x<1000:
        return '<1千赞'
    elif 1000<=x<5000:
        return '1-5千赞'
    elif 5000<=x<10000:
        return '5-10千赞'
    elif 10000<=x<=50000:
        return '10-50千赞'
    else:
        return '>50千赞'
df1['likes'] = df1['likes'].apply(fans_label)
df1['views'] =np.log(df1['views'])  #为箱线图更加美观，对播放量取对数

plt.figure(figsize=(10,5))
plt.rcParams['font.sans-serif']=['kaiti']
sns.boxplot(x='likes',y='views',data=df1,
            order=['<1千赞','1-5千赞','5-10千赞','10-50千赞','>50千赞'],palette='Set3',)
plt.xlabel('点赞数',labelpad=10)
plt.ylabel('播放量(取对数)')
plt.title('UP主视频点赞数与播放量关系分布')
plt.rcParams['font.sans-serif'] = ['SimHei']  # 黑体
plt.show()
