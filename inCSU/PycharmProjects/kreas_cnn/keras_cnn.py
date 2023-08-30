# Coded By Lucky
# Time: 2020/12/2 8:04

# 1.准备工作
# 1.1 导入相关的包
import matplotlib.pyplot as plt
import tensorflow as tf
import numpy as np
import math
import h5py
from keras import models
from keras.layers import InputLayer,Input,Reshape,MaxPooling2D,Conv2D,Dense,Flatten
from keras.datasets import mnist
from keras.optimizers import Adam
from keras import backend as K
import keras.utils

# 1.2 载入数据
(x_train, y_train), (x_test, y_test) = mnist.load_data()
# path = 'mnist.npz'
# f = np.load(path)
# x_train, y_train = f['x_train'], f['y_train']
# x_test, y_test = f['x_test'], f['y_test']
# f.close()

# 1.3 配置神经网络
img_size = 28  # 图像的每个维度中的像素数。
img_size_flat = 28*28  # 图像存储在一维矩阵中的总长度
img_shape = (28, 28)  # 用来重塑图像的高度和宽度的元组。
img_shape_full = (28, 28, 1)  # 用来重塑图像的高度，宽度和深度的元组。
num_classes = 10   # 类别数量
num_channels = 1   # 图像的通道数

print(img_size)
print(img_size_flat)
print(img_shape)
print(img_shape_full)
print(num_classes)
print(num_channels)

# 1.4 绘制图像的辅助函数
def plot_images(images, cls_true, cls_pred=None):
    assert len(images) == len(cls_true) == 9
    # 创建3x3子图.
    fig, axes = plt.subplots(3, 3)
    fig.subplots_adjust(hspace=0.3, wspace=0.3)

    for i, ax in enumerate(axes.flat):
        # 画图.
        ax.imshow(images[i].reshape(img_shape), cmap='binary')

        # 显示真正的预测的类别.
        if cls_pred is None:
            xlabel = "True: {0}".format(cls_true[i])
        else:
            xlabel = "True: {0}, Pred: {1}".format(cls_true[i], cls_pred[i])

        # 将类别作为x轴的标签
        ax.set_xlabel(xlabel)

        # 去除图中的刻度线.
        ax.set_xticks([])
        ax.set_yticks([])
    plt.show()

# 测试plot_images()，输出前九张图片。
plot_images(x_train[0:9], y_train[0:9])

# 1.5 绘制错误分类图像的辅助函数
def plot_example_errors(cls_pred, correct):
    incorrect = (correct == False)
    images = x_test[incorrect]
    cls_pred = cls_pred[incorrect]
    cls_true = y_test[incorrect]
    # 画出前9张图像
    plot_images(images=images[0:9], cls_true=cls_true[0:9], cls_pred = cls_pred[0:9])

# 2. 序列模型
# 2.1 模型框架
model = models.Sequential()
model.add(InputLayer(input_shape=(img_size_flat,)))
model.add(Reshape(img_shape_full))
model.add(Conv2D(kernel_size=5, strides=1, filters=16, padding='same',
                 activation='relu', name='layer_conv1'))
model.add(MaxPooling2D(pool_size=2, strides=2))
model.add(Conv2D(kernel_size=5, strides=1, filters=36, padding='same',
                 activation='relu', name='layer_conv2'))
model.add(MaxPooling2D(pool_size=2, strides=2))
model.add(Flatten())
model.add(Dense(128, activation='relu'))
model.add(Dense(num_classes, activation='softmax'))

# 2.2 模型编译
model.compile(optimizer=Adam(lr=1e-3),loss='categorical_crossentropy',metrics=['accuracy'])

# 2.3 训练模型
x_train = x_train.reshape(60000, 784)
x_test = x_test.reshape(10000, 784)

x_train = x_train/255
x_test = x_test/255
y_train = keras.utils.to_categorical(y_train, 10)
model.fit(x_train, y_train, epochs=1, batch_size=128, validation_split=1/12, verbose=2)

# 2.4 评估与性能指标
y_test_f = keras.utils.to_categorical(y_test,10)
result = model.evaluate(x_test, y_test_f, verbose=1)
print("loss:", result[0])
print("acc", result[1])

# 2.5 预测
predict = model.predict(x_test)
predict = np.argmax(predict,axis=1)
plot_images(x_test[0:9],y_test[0:9],predict[0:9])

# 2.6 错分类的图片
y_pred = model.predict(x_test)
cls_pred = np.argmax(y_pred, axis=1)
correct = (cls_pred == y_test)
plot_example_errors(cls_pred, correct=correct)


print('-----模型二三的分割线-----')

# 3. 功能模型
# 3.1 模型框架
# 创建一个输入层，类似于TensorFlow中的feed_dict。
# 输入形状input-shape 必须是包含图像大小image_size_flat的元组。
inputs = Input(shape=(img_size_flat,))

# 用于构建神经网络的变量。
net = inputs

# 输入是一个包含784个元素的扁平数组,但卷积层期望图像形状是（28,28,1）
net = Reshape(img_shape_full)(net)
#  具有ReLU激活和最大池化的第一个卷积层。
net = Conv2D(kernel_size=5, strides=1, filters=16, padding='same',
             activation='relu', name='layer_conv1')(net)
net = MaxPooling2D(pool_size=2, strides=2)(net)

#  具有ReLU激活和最大池化的第二个卷积层.
net = Conv2D(kernel_size=5, strides=1, filters=36, padding='same',
             activation='relu', name='layer_conv2')(net)
net = MaxPooling2D(pool_size=2, strides=2)(net)

# 将卷积层的4级输出展平为2级，可以输入到完全连接/密集层。
net = Flatten()(net)

# 具有ReLU激活的第一个完全连接/密集层。
net = Dense(128, activation='relu')(net)

#  最后一个完全连接/密集层，具有softmax激活功能，用于分类
net = Dense(num_classes, activation='softmax')(net)

# 神经网络输出
outputs = net

# 3.2 模型编译
model2 = models.Model(inputs=inputs, outputs=outputs)
model2.compile(optimizer='rmsprop',loss='categorical_crossentropy', metrics=['accuracy'])

# 3.3 训练模型
model2.fit(x_train,y_train,batch_size=128,epochs=1,validation_split=1/12,verbose=2)

# 3.4 评估模型
y_test_h = keras.utils.to_categorical(y_test,10)
result = model2.evaluate(x_test, y_test_h, verbose=1)
print(model2.metrics_names[0], result[0])
print(model2.metrics_names[1], result[1])

# 3.5 预测
predict = model2.predict(x_test)
predict = np.argmax(predict,axis=1)
plot_images(x_test[0:9],y_test[0:9],predict[0:9])

# 3.6 错分类的图片
y_pred = model2.predict(x_test)
cls_pred = np.argmax(y_pred, axis=1)
correct = (cls_pred == y_test)
plot_example_errors(cls_pred,correct=correct)

# 4. 保存和加载模型
# 4.1 保存keras模型
path_model = "./model2.pkl"
model2.save(path_model)

# 4.2 删除模型
del model2

# 4.3 加载模型
model3 = models.load_model(path_model)

# 4.4 使用加载的模型进行预测
predict = model3.predict(x_test)
predict = np.argmax(predict,axis=1)
plot_images(x_test[0:9],y_test[0:9],predict[0:9])

# 5. 权重和输出的可视化
# 5.1 画卷积权重的辅助函数
def plot_conv_weights(weights, input_channel=0):
    # 获取权重的最低值和最高值.这用于校正图像的颜色强度，以便可以相互比较.
    w_min = np.min(weights)
    w_max = np.max(weights)
    # 卷积层中的卷积核数量
    num_filters = weights.shape[3]
    # 要绘制的网格数.卷积核的平方根.
    num_grids = math.ceil(math.sqrt(num_filters))
    # 创建带有网格子图的图像.
    fig, axes = plt.subplots(num_grids, num_grids)
    # 绘制所有卷积核的权重
    for i, ax in enumerate(axes.flat):
        #  仅绘制有限的卷积核权重
        if i<num_filters:
            #  获取输入通道的第i个卷积核的权重.有关于４维张量格式的详细信息请参阅new_conv_layer()
            img = weights[:, :, input_channel, i]
            # 画图
            ax.imshow(img, vmin=w_min, vmax=w_max,
                      interpolation='nearest', cmap='seismic')
        # 去除刻度线.
        ax.set_xticks([])
        ax.set_yticks([])
    plt.show()

# 5.2 得到层
model3.summary()
layer_input = model3.layers[0]
layer_conv1 = model3.layers[2]
layer_conv2 = model3.layers[4]

# 5.3 卷积权重
weights_conv1 = layer_conv1.get_weights()[0]
plot_conv_weights(weights=weights_conv1, input_channel=0)
weights_conv2 = layer_conv2.get_weights()[0]
plot_conv_weights(weights=weights_conv2, input_channel=0)

# 5.4 画卷积层输出的辅助函数
def plot_conv_output(values):
    num_filters = values.shape[3]
    num_grids = math.ceil(math.sqrt(num_filters))
    fig, axes = plt.subplots(num_grids, num_grids)
    for i, ax in enumerate(axes.flat):
        if i < num_filters:
            #  获取输入通道的第i个卷积核的权重.有关于４维张量格式的详细信息请参阅new_conv_layer()
            img = values[0, :, :, i]
            # 画图
            ax.imshow(img, interpolation='nearest', cmap='binary')
        # 去除刻度线.
        ax.set_xticks([])
        ax.set_yticks([])
    plt.show()

# 5.5 输入图像
def plot_image(image):
    plt.imshow(image.reshape(img_shape), interpolation='nearest', cmap='binary')
    plt.show()

image1 = x_test[0]
plot_image(image1)

# 5.6 卷积层输出
output_conv1 = K.function(inputs=[layer_input.input], outputs=[layer_conv1.output])
layer_output1 = output_conv1(np.array([image1]))[0]
print(layer_output1.shape)
plot_conv_output(values=layer_output1)

output_conv2 = models.Model(inputs=layer_input.input, outputs=layer_conv2.output)
layer_output2 = output_conv2.predict(np.array([image1]))
print(layer_output2.shape)
plot_conv_output(values=layer_output2)