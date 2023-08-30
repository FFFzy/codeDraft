# Coded By Lucky
# Time: 2021/4/19 21:21

import matplotlib.pyplot as plt
import pandas as pd

df=pd.read_csv('C:/Users/莫小殇/Desktop/role_count.csv',encoding='gbk')
df=df.fillna(0).set_index('episode')
xinyi=df[df['新一']>=250]['新一'].to_frame()
print(xinyi) #新一登场集数
plt.figure(figsize=(10,5))
plt.plot(df.index,df['新一'],label='新一',color='blue',alpha=0.6)
plt.rcParams['font.sans-serif']=['SimHei']#黑体
plt.annotate('集数:50,讨论次数:309',
             xy=(50,309),
             xytext=(40,330),
             arrowprops=dict(color='red',headwidth=8,headlength=8)
            )
plt.annotate('集数:206,讨论次数:263',
             xy=(206,263),
             xytext=(195,280),
             arrowprops=dict(color='red',headwidth=8,headlength=8)
            )
plt.annotate('集数:571,讨论次数:290',
             xy=(571,290),
             xytext=(585,310),
             arrowprops=dict(color='red',headwidth=8,headlength=8)
            )
plt.hlines(xmin=df.index.min(),xmax=df.index.max(),y=250,linestyles='--',colors='red')
plt.legend(loc='best',frameon=False)
plt.xlabel('集数')
plt.ylabel('讨论次数')
plt.title('工藤新一讨论次数分布图')
plt.show()
