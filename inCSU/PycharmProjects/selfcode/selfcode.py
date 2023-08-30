# Coded By Lucky
# Time: 2020/12/18 14:05

from keras.layers import Input, Dense
from keras.models import Model
import matplotlib.pyplot as plt
from keras.datasets import mnist
import numpy as np

# 1.搭建自编码器
# 编码器维度
encoding_dim = 32

input_img = Input(shape=(784,))
# "encoded" 是把输入编码表示
encoded = Dense(encoding_dim, activation='relu')(input_img)
# "decoded" 是输入的有损重构
decoded = Dense(784, activation='sigmoid')(encoded)

# 搭建自编码模型
autoencoder = Model(input_img, decoded)
autoencoder.compile(optimizer='adadelta', loss='binary_crossentropy')

# 2.导入数据集
(x_train, _), (x_test, _) = mnist.load_data()
x_train = x_train.astype('float32') / 255.
x_test = x_test.astype('float32') / 255.
x_train = x_train.reshape((len(x_train), np.prod(x_train.shape[1:])))
x_test = x_test.reshape((len(x_test), np.prod(x_test.shape[1:])))

print(x_train.shape)
print(x_test.shape)

# 3.拟合模型
autoencoder.fit(x_train, x_train, epochs=50, batch_size=256, shuffle=True, validation_data=(x_test, x_test))
encoder = Model(inputs=input_img, outputs=encoded)
encoded_input = Input(shape=(encoding_dim,))
decoder_layer = autoencoder.layers[-1]

decoder = Model(inputs=encoded_input, outputs=decoder_layer(encoded_input))

# 4.对比
# 从测试集选取一些数据来编码和解码
encoded_imgs = encoder.predict(x_test)
decoded_imgs = decoder.predict(encoded_imgs)
n = 10  # 打印的图片数量
plt.figure(figsize=(20, 4))
for i in range(n):
    # 显示原来图像
    ax = plt.subplot(2, n, i + 1)
    plt.imshow(x_test[i].reshape(28, 28))
    plt.gray()
    ax.get_xaxis().set_visible(False)
    ax.get_yaxis().set_visible(False)

    # 显示重构后的图像
    ax = plt.subplot(2, n, i + 1 + n)
    plt.imshow(decoded_imgs[i].reshape(28, 28))
    plt.gray()
    ax.get_xaxis().set_visible(False)
    ax.get_yaxis().set_visible(False)
plt.show()
