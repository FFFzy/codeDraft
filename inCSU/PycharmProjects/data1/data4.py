# Coded By Lucky
# Time: 2021/4/19 19:42

import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
plt.rcParams['font.sans-serif']=['kaiti']
plt.style.use('ggplot')
df=pd.read_csv('C:/Users/莫小殇/Desktop/role_count.csv',encoding='gbk')
df=df.fillna(0).set_index('episode')
plt.figure(figsize=(10,5))
role_sum=df.sum().to_frame().sort_values(by=0,ascending=False)
g=sns.barplot(role_sum.index,role_sum[0],palette='Set3',alpha=0.8)
index=np.arange(len(role_sum))
for name,count in zip(index,role_sum[0]):
    g.text(name,count+50,int(count),ha='center',va='bottom',)
plt.title('B站名侦探柯南弹幕——主要人物讨论总次数分布')
plt.ylabel('讨论次数')
plt.show()