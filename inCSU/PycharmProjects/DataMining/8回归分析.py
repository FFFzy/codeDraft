# Coded By Lucky
# Time: 2021/4/20 1:42

import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
import os
os.chdir('C:/Users/莫小殇/Desktop/B站挖掘/')
data = pd.read_csv('b站.csv',parse_dates=['pubdate'],encoding='utf-8')
data1 =  data.dropna()
data1 = data1.sort_index(ascending=False)
def get_first(x):
    return x.iloc[0,:]
up_group = data1.groupby('UP主').apply(get_first)

pd.set_option('display.float_format',lambda x:'.2%f'%x)
plt.figure(figsize=(10,5))
corr_variable = ['video_time','likes','coins','favorites','shares','views','comments','danmu']
sns.heatmap(up_group[corr_variable].corr(),cmap='YlOrRd',annot=True)
plt.title('各数值变量相关系数热力图')
plt.rcParams['font.sans-serif'] = ['SimHei']  # 黑体
plt.show()
