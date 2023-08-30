# Coded By Lucky
# Time: 2020/10/23 14:43

import pandas as pd
import csv
import numpy as np

# 1.处理数据
train_data = pd.read_csv('./data/train.csv',usecols=range(2,27), encoding='GBK')
row = train_data['測項'].unique()

# 2.训练数据规整化
eighteen_train_data = pd.DataFrame(np.zeros((18,24*240)))

n=0
for i in row:
    train_data1 = train_data[train_data['測項'] ==i]
    train_data1.drop(['測項'],axis=1,inplace=True)
    train_data1 = np.array(train_data1)
    train_data1[train_data1 == 'NR']='0'
    train_data1 = train_data1.astype('float')
    train_data1 = train_data1.reshape(5760,1)
    train_data1 = train_data1.T
    eighteen_train_data.loc[n] = train_data1
    n = n+1

train_array = np.array(eighteen_train_data).astype(float)

x_train = []
y_train = []
for i in range(eighteen_train_data.shape[1]-9):
    x_temp = np.ones(18*9)
    count = 0
    for j in range(18):
        x = train_array[j,i:i+9]
        for k in range(9):
            x_temp[count] = x[k]
            count += 1
    x_train.append(x_temp)

    y = int(train_array[9,i+9])
    y_train.append(y)

x_train = np.array(x_train)
y_train = np.array(y_train)

# 3.实现线性回归
epoch = 2000
bias = 0
weights = np.ones(18*9)
learning_rate = 1

bias_g2_sum = 0
weights_g2_sum = np.zeros(18*9)

for i in range(epoch):
    b_g = 0
    w_g = np.zeros(18*9)
    for j in range(len(x_train)):
        b_g = b_g + (y_train[j] - weights.dot(x_train[j]) -bias) * (-1)
        for k in range(18*9):
            w_g[k] = w_g[k] + (y_train[j] - weights.dot(x_train[j]) - bias) * (-x_train[j, k])

    b_g = b_g/len(x_train)
    w_g = w_g/len(x_train)

    bias_g2_sum += b_g**2
    weights_g2_sum += w_g**2

    bias -= learning_rate/bias_g2_sum**0.5*b_g
    weights -= learning_rate/weights_g2_sum**0.5*w_g

    if i%200 == 0:
        loss = 0
        for j in range(len(x_train)):
            loss += (y_train[j] - weights.dot(x_train[j])-bias)**2
        print("After {} epochs ,the loss is:".format(i), loss/len(x_train))

# 4.存储模型
np.save('model_weights.npy',weights)
np.save('model_bias.npy',bias)

w = np.load('model_weights.npy')
b = np.load('model_bias.npy')

# 5.读取测试数据
test_data = pd.read_csv('./data/test.csv',header=None, usecols=range(2,11), encoding='UTF-8')

for i in range(test_data.shape[0]):
    for j in range(2,11):
        if test_data.loc[i][j] == 'NR':
            test_data.loc[i][j] = '0'

# 6.预测PM2.5
x_test = []
for i in range(int (test_data.shape[0]/18)):
    x_temp = np.ones(18*9)
    count = 0
    for j in range (18*i,18*(i+1)):
        for k in range(2,11):
            x_temp[count] = test_data.loc[j][k]
            count = count+1

    x_test.append(x_temp)
x_test = np.array(x_test).astype(float)

pre_list = []
for i in range(len(x_test)):
    pre = w.dot(x_test[i])-b
    pre_list.append(pre)
    print('id_',i,'',pre)

sampleSubmission = open('./data/sampleSubmission.csv', 'r')
reader = csv.reader(sampleSubmission)
rows = [rowTemp for rowTemp in reader]

newfile = open('./data/sampleSubmission.csv', 'w', newline='')

writer = csv.writer(newfile)
writer.writerow(rows[0])
for i in range(len(rows)-1):
    rows[i+1][1] = pre_list[i]
    writer.writerow(rows[i+1])

newfile.close()
sampleSubmission.close()

# 7.测试结果
y_test = pd.read_csv('./data/predict.csv', usecols=range(1, 2))
y_test = np.array(y_test).astype(float)

loss = 0
for i in range(len(x_test)):
    loss += (y_test[i] - w.dot(x_test[i]) - b) ** 2
print(loss)
print('The loss on test data is:', loss / len(x_test))
