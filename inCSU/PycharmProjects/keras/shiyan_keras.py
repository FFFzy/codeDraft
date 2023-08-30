# Coded By Lucky
# Time: 2020/11/20 14:03

import numpy as np
from keras.datasets import mnist
from keras.utils import np_utils
from keras import models
from keras import layers

# 1. 数据预处理
# 1.1 加载训练集和测试集
(X_train,y_train),(X_test,y_test) = mnist.load_data()

print(X_train.shape)
print(y_train.shape)
print(X_test.shape)
print(y_test.shape)

# 1.2 重塑形状
X_train = X_train.reshape([60000, 784])
X_test = X_test.reshape([10000, 784])

X_train = X_train.astype(np.float32)
X_test = X_test.astype(np.float32)

print(X_train.shape)
print(X_test.shape)
print(X_train.dtype)
print(X_test.dtype)

# 1.3 归一化
X_train /= 255
X_test /= 255

print(X_train[2, 100:150])

# 1.4 ont-hot 编码
y_train = np_utils.to_categorical(y_train)
y_test = np_utils.to_categorical(y_test)

print(y_train)
print(y_test)

# 2 搭建神经网络
# 2.1 添加层
model = models.Sequential()
model.add(layers.Dense(10, input_shape=(784,)))
model.add(layers.Activation('softmax'))

model.summary()

# 2.2 编译神经网络
model.compile(optimizer='sgd', loss='categorical_crossentropy', metrics=['accuracy'])

# 2.3 训练神经网络
model.fit(X_train, y_train, epochs=200, batch_size=128, verbose=1, validation_split=0.2)

# 2.4 评估神经网络
test_loss, test_acc = model.evaluate(X_test,y_test, verbose=1)
print('Test loss is : ', test_loss, '\n', 'Test accuracy is : ', test_acc)

# 3 优化神经网络
# 3.1 模型改进
new_model = models.Sequential()
new_model.add(layers.Dense(128, input_shape=(784,), activation='relu'))
new_model.add(layers.Dense(128, activation='relu'))
new_model.add(layers.Dense(10, activation='softmax'))
new_model.summary()

# 3.2 编译神经网络
new_model.compile(optimizer='sgd', loss='categorical_crossentropy', metrics=['accuracy'])

# 3.3 训练神经网络
new_model.fit(X_train, y_train, epochs=20, batch_size=128, verbose=1, validation_split=0.2)

# 3.4 评估神经网络
test_loss1, test_acc1 = new_model.evaluate(X_test, y_test, verbose=1)
print('Test loss on new_model is : ', test_loss1, '\n', 'Test accuracy on new_model is : ', test_acc1)