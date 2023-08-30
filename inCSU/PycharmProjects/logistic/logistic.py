# Coded By Lucky
# Time: 2020/11/11 8:15

import numpy as np
import pandas as pd
import csv
import matplotlib as plt

def dataProcess_X(rawData):
    if "income" in rawData.columns:
        Data = rawData.drop(["sex", 'income'], axis=1)
    else:
        Data = rawData.drop(["sex"], axis=1)
    listObjectColumn = [col for col in Data.columns if Data[col].dtypes == "object"] #读取非数字的column
    listNonObjedtColumn = [x for x in list(Data) if x not in listObjectColumn] #数字的column

    ObjectData = Data[listObjectColumn]
    NonObjectData = Data[listNonObjedtColumn]

    NonObjectData.insert(0 ,"sex", (rawData["sex"] == " Female").astype(np.int))
    ObjectData = pd.get_dummies(ObjectData)

    Data = pd.concat([NonObjectData, ObjectData], axis=1)
    Data_x = Data.astype("int64")

    Data_x = (Data_x - Data_x.mean()) / Data_x.std()

    return Data_x

def dataProcess_Y(rawData):
    df_y = rawData['income']
    Data_y = pd.DataFrame((df_y==' >50K').astype("int64"), columns=["income"])
    return Data_y

def _normalize_column_normal(X,train = True,specified_column = None,X_mean = None,X_std = None):
    if train:
        if specified_column == None:
            specified_column = np.arange(X.shape[1])
        length = len(specified_column)
        X_mean = np.reshape(np.mean(X[:,specified_column],0),(1,length))
        X_std  = np.reshape(np.std(X[:,specified_column],0), (1,length))

    X[:,specified_column] = np.divide(np.subtract(X[:,specified_column],X_mean),X_std)

    return X,X_mean,X_std

def _cross_entropy(y_pred,Y_label):
    cross_entropy = -np.dot(Y_label,np.log(y_pred))-np.dot((1-Y_label),np.log(1-y_pred))
    return cross_entropy

def _sigmoid(z):
    return np.clip(1/(1.0 + np.exp(-z)), 1e-6, 1-1e-6)

def get_prob(X,w,b):
    return _sigmoid(np.add(np.matmul(X,w),b))

def infer(X,w,b):
    return np.round(get_prob(X,w,b))

def _loss(y_pred, Y_label, lamda, w):
    return _cross_entropy(y_pred, Y_label) + lamda * np.sum(np.square(w))

def _gradient_regularization(X, Y_label, w, b, lamda):
    y_pred = get_prob(X,w,b)
    pred_error = Y_label - y_pred
    w_grad = -np.mean(np.multiply(pred_error.T, X.T), 1)+lamda * w
    b_grad = -np.mean(pred_error)
    return w_grad,b_grad

def _gradient(X,Y_label, w, b):
    y_pred = get_prob(X,w,b)
    pred_error = Y_label - y_pred
    w_grad = -np.mean(np.multiply(pred_error.T, X.T), 1)
    b_grad = -np.mean(pred_error)
    return w_grad,b_grad

def train_dev_split(X, y, dev_size = 0.25):
    train_len = int(round(len(X)*(1-dev_size)))
    return X[0:train_len],y[0:train_len], X[train_len:None], y[train_len:None]

def _shuffle(X, Y):
    randomize = np.arange(len(X))
    np.random.shuffle(randomize)
    return (X[randomize], Y[randomize])

def train(X_train, Y_train):
    max_iter = 40
    batch_size = 32
    learning_rate = 0.2
    num_train = len(Y_train)
    # num_dev = len(Y_dev)

    step = 1
    for epoch in range(max_iter):
        X_train, Y_train = _shuffle(X_train, Y_train)

        total_loss = 0.0

        for idx in range(int(np.floor(len(Y_train)/batch_size))):
            X = X_train[idx * batch_size:(idx + 1) * batch_size]
            Y = Y_train[idx * batch_size:(idx + 1) * batch_size]

            w_grad, b_grad = _gradient_regularization(X, Y, w, b, lamda)

            w = w - learning_rate/np.sqrt(step) * w_grad
            b = b - learning_rate/np.sqrt(step) * b_grad

            step = step + 1

        y_train_pred = get_prob(X_train, w, b)
        Y_train_pred = np.round(y_train_pred)
        train_acc.append(accuracy(Y_train_pred,Y_train))
        loss_train.append(_loss(y_train_pred, Y_train, lamda, w)/num_train)

    return w, b, loss_train,train_acc

def accuracy(Y_pred, Y_label):
    acc = np.sum(Y_pred == Y_label)/len(Y_pred)
    return acc


if __name__ == "main" :
    output_fpath = "./data/output.csv"
    train_data = pd.read_csv("./data/train.csv")
    test_data = pd.read_csv("./data/test.csv")

    X_train = dataProcess_X(train_data).drop(['native_country_ Holand-Netherlands'], axis=1).values
    Y_train = dataProcess_Y(train_data)

    w = np.zeros((X_train.shape[1],))
    b = np.zeros((1,))

    # w_grad, b_grad = _gradient(X_train,Y_train,w,b)

    regularize = True
    if regularize:
        lamda = 0.001
    else:
        lamda = 0

    loss_train = []
    loss_validation = []
    train_acc = []
    dev_acc = []

    X_train,Y_train,X_dev,Y_dev = train_dev_split(X_train, Y_train, dev_size=0.25)

    y_dev_pred = get_prob(X_dev, w, b)
    Y_dev_pred = np.round(y_dev_pred)

    col = [0, 1, 3, 4, 5, 7, 10 ,12, 25, 26, 27, 28]

    X_train, X_mean, X_std = _normalize_column_normal(X_train, specified_column=col)

    w, b, loss_train,train_acc = train(X_train, Y_train)

    print("loss_train"+loss_train[19])
    loss_validation, dev_acc = train(X_dev, Y_dev)

    plt.plot(loss_train)
    plt.plot(loss_validation)
    plt.legend(['train','dev'])
    plt.show()

    plt.plot(train_acc)
    plt.plot(dev_acc)
    plt.legend(['train', 'dev'])
    plt.show()

    X_test = dataProcess_X(test_data)
    features = X_test.columns.values
    X_test = X_test.values
    X_test, _, _ = _normalize_column_normal(X_test, train=False, specified_column=col, X_mean=X_mean, X_std=X_std)

    result = infer(X_test, w, b)

    with open(output_fpath,'w') as f:
        f.write('id,label\n')
        for i, v in enumerate(result):
            f.write('%d,%d\n' %(i+1,v))

    # ind = np.argsort(np.abs(w))[::-1]
    # for i in ind[0:10]:
    #     print(features[i], w[i])

