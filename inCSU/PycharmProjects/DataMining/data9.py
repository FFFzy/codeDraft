# Coded By Lucky
# Time: 2021/4/20 1:52

import pandas as pd
import numpy
import os
os.chdir('C:/Users/莫小殇/Desktop/B站挖掘/')
data = pd.read_csv('b站.csv',parse_dates=['pubdate'],encoding='utf-8')
data1 =  data.dropna()  #删除缺失值
data1 = data1.drop_duplicates() #删除重复值
data2 = data1[['UP主','pubdate','likes','coins','favorites','shares','comments','views','danmu']]

#I值计算
danmu_sum = data2.groupby('UP主')['danmu'].sum()
comments_sum = data2.groupby('UP主')['comments'].sum()
views_sum = data2.groupby('UP主')['views'].sum()
counts_sum = data2.groupby('UP主')['pubdate'].count()

I = round((danmu_sum+comments_sum)/views_sum/counts_sum*100,2).reset_index()
I.columns=['UP主','I']
I.head()

#计算F值
earliest = data2.groupby('UP主')['pubdate'].min()
latest = data2.groupby('UP主')['pubdate'].max()
F = round((latest-earliest).dt.days/data2.groupby('UP主')['pubdate'].count()).reset_index()
counts_sum = data2.groupby('UP主')['pubdate'].count().reset_index()
counts_sum.columns = ['UP主','pub_counts']
counts_sum1 = counts_sum[counts_sum['pub_counts']>5]   #剔除发布视频数小于5的UP主

F = pd.merge(counts_sum1, F,on='UP主', how='inner')
F.columns = ['UP主','pub_counts','F']
F.head()

#计算L值
data2['likes_rate'] = (data2['likes']*1+data2['coins']*2+data2['favorites']*3+data2['shares']*4)/data2['views']*100
L = round((data2.groupby('UP主')['likes_rate'].sum()/data2.groupby('UP主')['pubdate'].count()).reset_index(),2)
L.columns = ['UP主', 'L']
L.head()


#%%

#合并IFL值
IF = pd.merge(I,F,on = 'UP主',how = 'inner')
IFL = pd.merge(IF,L,on = 'UP主',how = 'inner')
IFL.head()


# 打分,500即代表无穷大,right=False表示为划分区间格式为左闭右开
IFL['I_score'] = pd.cut(IFL['I'], bins=[0,0.03,0.05,0.09,500],
                        labels=[1,2,3,4], right=False).astype(float)

IFL['F_score'] = pd.cut(IFL['F'], bins=[0,7,15,30,90,500],
                        labels=[5,4,3,2,1], right=False).astype(float)

IFL['L_score'] = pd.cut(IFL['L'], bins=[2.08,23.07,29.89,36.49,500],
                        labels=[1,2,3,4], right=False).astype(float)

#与各自均值比较
IFL['I值是否大于均值'] = (IFL['I_score']>IFL['I_score'].mean())*1  # 布尔值与数字运算规则:True则返回数字;False返回0
IFL['F值是否大于均值'] = (IFL['F_score']>IFL['F_score'].mean())*1
IFL['L值是否大于均值'] = (IFL['L_score']>IFL['L_score'].mean())*1
IFL.head()


#%%

IFL['value'] = (IFL['I值是否大于均值'])*100+(IFL['F值是否大于均值'])*10+(IFL['L值是否大于均值'])*1
label_dict = {
    111:'高价值UP主',
    101:'高价值拖更UP主',
    11:'高质量UP主',
    110:'接地气活跃UP主',
    1:'高质量拖更UP主',
    10:'活跃UP主',
    100:'接地气UP主',
    0:'萌新UP主',
}
IFL['label'] = IFL['value'].map(label_dict)
IFL.head()

