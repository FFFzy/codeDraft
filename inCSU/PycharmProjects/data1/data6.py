# Coded By Lucky
# Time: 2021/4/19 21:29

import matplotlib.pyplot as plt
import pandas as pd

df=pd.read_csv('C:/Users/莫小殇/Desktop/role_count.csv',encoding='gbk')
plt.figure(figsize=(10,5))
names=['琴酒','伏特加','贝姐']
colors=['#090707','#004e66','#EC7357']
alphas=[0.8,0.7,0.6]
for name,color,alpha in zip(names,colors,alphas):
    plt.plot(df.index,df[name],label=name,color=color,alpha=alpha)
plt.legend(loc='best',frameon=False)
plt.annotate('集数:{},讨论次数:{}'.
             format(df['贝姐'].idxmax(),int(df['贝姐'].max())),
             xy=(df['贝姐'].idxmax(),df['贝姐'].max()),
             xytext=(df['贝姐'].idxmax()+30,df['贝姐'].max()),
             arrowprops=dict(color='red',headwidth=8,headlength=8)
            )
plt.xlabel('集数')
plt.ylabel('讨论次数')
plt.title('酒厂成员讨论次数分布图')
plt.hlines(xmin=df.index.min(),xmax=df.index.max(),y=200,linestyles='--',colors='red')
plt.ylim(0,400)
plt.rcParams['font.sans-serif']=['SimHei']#黑体
plt.show()

#输出主线剧集
mainline=set(list(df[df['贝姐']>=200].index)+list(df[df['琴酒']>=200].index))  #伏特加可忽略不计
print(mainline)
