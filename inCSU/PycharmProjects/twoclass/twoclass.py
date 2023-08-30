# Coded By Lucky
# Time: 2020/11/4 8:17

import numpy as np
import pandas as pd
import csv

def dataProcesss_X(rawData):
    if "income" in rawData.colums:
        Data = rawData.drop(["sex","income"],axis=1)
    else:
        Data = rawData.drop(["sex"],axis=1)
    listObjectColumn = [col for col in Data.colnums if Data[col].dtypes == "object"]
    listNonObjectColumn = [x for x in list(Data) if x not in listObjectColumn]

    ObjectData = Data[listObjectColumn]
    NonObjectData = Data[listNonObjectColumn]

    # 将性别以male = 0，female = 1插入
    NonObjectData.insert(0,"sex",(rawData["sex"] == "Female").astype(np.int))
    ObjectData = pd.get_dummies(ObjectData)  # get_dummies 进行ont-hot处理

    Data = pd.concat([NonObjectData,ObjectData],axis=1)
    Data_x = Data.astype("int64")

    Data_x = (Data_x - Data_x.mean())/Data_x.std()  # .std求标准差
    return Data_x

if __name__ == "__main__":
    trainData = pd.read_csv("./data/train.csv")
    testData = pd.read_csv("./data/test.csv")
    ans = pd.read_csv("./data/correct_answer.csv")

    x_train = dataProcesss_X(trainData).drop(['native_country_ Holand-Netherlands'],axis=1).values
    x_test = dataProcesss_X(testData).values
    y_train = dataProcesss_X(trainData).values
    y_ans = ans['label'].values



def train(X_train,Y_train):
    train_data_size = X_train.shape[0]
    cnt1 = 0
    cnt2 = 0

    mu1 = np.zeros((106,))
    mu2 = np.zeros((106,))

    for i in range(train_data_size):
        if Y_train[i] == 1:  # 即>50k
            mu1 += X_train[i]
            cnt1 +=1
        else:
            mu2 += X_train[i]
            cnt2 += 1

    mu1 /= cnt1
    mu2 /= cnt2

    sigma1 = np.zeros((106,106))
    sigma2 = np.zeros((106,106))
    for i in range(train_data_size):
        if Y_train[i] == 1:
            sigma1 += np.dot(np.transpose([X_train[i] - mu1]),[X_train[i] - mu1])
        else:
            sigma2 += np.dot(np.transpose([X_train[i] - mu2]), [X_train[i] - mu2])

    sigma1 /= cnt1
    sigma2 /= cnt2
    shared_sigma = (float(cnt1) / train_data_size) * sigma1 + (float(cnt2) / train_data_size) * sigma2

    N1 = cnt1
    N2 = cnt2

    return mu1,mu2,shared_sigma,N1,N2

def func(self,x):
    arr = np.empty([x.shape[0],1],dtype=float)
    for i in range(x.shape[0]):
        z = x[i,:].dot(self.w) + self.b
        z *= (-1)
        arr[i][0] = 1 /(1 + np.exp(z))
    return  np.clip(arr,1e-8,1-(1e-8))

def predict(self,x):
    ans = np.ones([x.shape[0],1],dtype=int)
    for i in range(x.shape[0]):
        if x[i] > 5:
            ans[i] = 0
    return ans

def write_file(self,path):
    result = self.func(self.data['x_test'])
    answer = self.predict(result)
    with open(path,'w',newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['id','label'])
        for i in range(answer.shape[0]):
            writer.writerow([i+1,answer[i][0]])