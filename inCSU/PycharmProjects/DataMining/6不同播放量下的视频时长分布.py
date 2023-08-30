# Coded By Lucky
# Time: 2021/4/20 1:26

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import os
os.chdir('C:/Users/莫小殇/Desktop/B站挖掘/')
data = pd.read_csv('b站.csv',parse_dates=['pubdate'],encoding='utf-8')
data1 = data.dropna()
views = [100000,500000,1000000,10000000]
colors = ["#5bd1d7","#348498","#004d61","#ff502f"]
plt.figure(figsize=(10,5))
for view,color in zip(data1["likes"],colors):
    video_time = data1[(data1['video_time']<=1200)&(data1['views']>=view)]['video_time']
    sns.kdeplot(video_time,label='播放量>'+str(int(view/10000))+'万',color=color)
plt.legend(frameon=False)
plt.title('不同播放量下的视频时长分布')
plt.xlabel('视频时长/秒')
plt.ylabel('核密度值')
plt.rcParams['font.sans-serif'] = ['SimHei']  # 黑体
plt.show()
