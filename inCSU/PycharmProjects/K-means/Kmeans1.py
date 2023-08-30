# Coded By Lucky
# Time: 2021/1/4 23:44

import numpy as np
from numpy import random
import matplotlib.pyplot as plt

# K-means
def k_means(matrix, center_point, k, n):
    # print('k=', k, ' n=', n)
    # 2.对每个样本点，计算得到距其最近的质心，将其类别标为该质心所对应的cluster
    cluster = {}
    for i in range(0, k):
        cluster[i] = []
    for i in range(0, 100):
        # 记录所有距离
        d = []
        # 记录下标信息
        index = {}
        for j in range(0, k):
            s = ((matrix[0][i] - center_point[j][0]) ** 2 + (matrix[1][i] - center_point[j][1]) ** 2)**0.5
            index[s] = j
            d.append(s)
        # 排序
        d.sort()
        # 将 i 点加入到距离最近的质心中
        cluster[index[d[0]]].append(i)
    # 3.重新计算k个 cluster 对应的质心
    center_point_new = {}
    # for i in range(0, k):
    #     center_point_new[i] = center_point[i]
    # print(cluster)
    for i in cluster:
        if len(cluster[i]) != 0:
            x = 0
            y = 0
            for point in cluster[i]:
                x += matrix[0][point]
                y += matrix[1][point]
            center_point_new[i] = [x/len(cluster[i]), y/len(cluster[i])]
        else:
            center_point_new[i] = center_point[i]
    # print(center_point_new)
    # print(center_point)
    is_same = True
    # 遍历判断是否质心不再改变
    for i in center_point:
        # print(center_point[i])
        # print(center_point_new[i])
        if center_point[i] != center_point_new[i]:
            is_same = False

    # 如果质心相同，或者已经迭代 n 次即不再继续
    if is_same or n >= 10:
        # print(center_point_new, '\n', n)
        # print(cluster)
        estimator = computeSSE(center_point_new, cluster)
        return center_point_new, cluster, estimator
    else:
        return k_means(matrix, center_point_new, k=k, n=n+1)


# 计算SSE
def computeSSE(center_point, cluster):
    estimator = 0
    for i in cluster:
        if len(cluster[i]) != 0:
            for point in cluster[i]:
                estimator += (matrix[0][point] - center_point[i][0]) ** 2 + (matrix[1][point] - center_point[i][1]) ** 2
    return estimator

if __name__ == '__main__':
    matrix = np.array(random.randint((100), size=(100, 100)))
    # 利用SSE选择最优k
    SSE = []
    for k in range(1, 10):  # K的范围 ： 1-10
        # k = 10
        print('*'*40)
        print('k：', k)
        # 1.随机选定 聚类中心
        center_point_index = np.array(random.randint((100), size=(k)))
        center_point = {}
        for i in range(0, k):
            center_point[i] = [matrix[0][center_point_index[i]], matrix[1][center_point_index[i]]]
        center_point_new, cluster, estimator = k_means(matrix, center_point, k=k, n=1)
        print('center_point_new:\n', center_point_new, '\ncluster:\n', cluster, '\nestimator:', estimator)
        SSE.append(estimator)

    X = range(1, 10)
    plt.xlabel('k')
    plt.ylabel('SSE')
    plt.plot(X, SSE, 'o-')
    plt.show()
