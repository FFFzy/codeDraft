# Coded By Lucky
# Time: 2021/4/20 0:10

import pandas as pd
import os
os.chdir('C:/Users/莫小殇/Desktop/B站挖掘/')
data = pd.read_csv('b站.csv',parse_dates=['pubdate'],encoding='UTF-8')  # parse_dates将pubdate解析为日期格式
data.isnull.sum()

data1 =  data.dropna()
pd.set_option('display.float_format','{:,.0f}'.format) #不显示科学计数
numeric_variable = ['video_time','likes','coins','favorites','shares','views','comments','danmu','fans']
data1[numeric_variable].describe()

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

plt.style.use('ggplot')
plt.rcParams['font.sans-serif']=['kaiti']
data1 = data1.set_index('pubdate')   #resample函数需要日期型字段作为索引
data1['count'] = 1
hour_resample1 = data1['count'].resample('H').sum().to_frame() #按小时对视频个数采样
hour_resample2 = data1['views'].resample('H').mean().to_frame() #按小时对视频平均播放量采样
hour_resample = pd.concat([hour_resample1, hour_resample2], axis=1)
hour_resample.index = hour_resample.index.map(lambda x: str(x).split(' ')[1]) #分离出小时数据
pd.set_option('display.precision', 0)
hour_group = hour_resample.reset_index().groupby(
    'pubdate').agg({'count': np.sum, 'views': np.mean}) #按小时聚合

def plot_line_bar(data, y1_max, y1_range, y2_max, y2_range):  #双轴组合图
    x = data.index
    y1 = data['count']
    y2 = data['views']
    f, ax1 = plt.subplots(figsize=(10, 5))
    ax1.bar(x, y1, color='#348498', label='发布视频数')
    ax2 = ax1.twinx()  # 双轴图关键
    ax2.grid(False)  # 去掉第二条线的网格
    ax2.plot(x, y2, label='平均播放量', color='#F75940', marker='o')
    ax1.set_yticks(np.arange(0, y1_max, y1_range)) #设置主坐标轴范围
    ax1.set_ylabel('发布视频数')
    ax2.set_yticks(np.arange(0, y2_max, y2_range))   #设置次坐标轴范围
    ax2.set_ylabel('平均播放量')
    for tl in ax1.get_xticklabels():  # 旋转横轴标签
        tl.set_rotation(90)
    ax1.legend(loc=2)
    ax2.legend(loc=1)  # loc1代表放置右上方
    plt.title('2020年B站动画区视频发布时间及平均播放量分布')
    plt.show()

if __name__ == '__main__':
    plot_line_bar(hour_group, 6000, 1000, 250000, 50000)
