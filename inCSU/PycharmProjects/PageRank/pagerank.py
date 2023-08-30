# Coded By Lucky
# Time: 2021/1/4 20:05

import numpy as np
from scipy.sparse import csc_matrix

def pageRank(G, s=1, maxerr=.0001):

    n = G.shape[0]

    # 将 G into 马尔科夫 A
    A = csc_matrix(G, dtype=np.float)
    rsums = np.array(A.sum(1))[:, 0]
    ri, ci = A.nonzero()
    A.data /= rsums[ri]

    sink = rsums == 0

    # 计算PR值，直到满足收敛条件
    ro, r = np.zeros(n), np.ones(n)
    while np.sum(np.abs(r - ro)) > maxerr:
        ro = r.copy()
        for i in range(0, n):
            Ai = np.array(A[:, i].todense())[:, 0]
            Di = sink / float(n)
            Ei = np.ones(n) / float(n)
            r[i] = ro.dot(Ai * s + Di * s + Ei * (1 - s))

    # 归一化
    return r / float(sum(r))

if __name__ == '__main__':
    G = np.array([[0, 1, 0, 0],
                  [1, 0, 0, 0],
                  [1, 1, 0, 1],
                  [0, 1, 1, 0]])
    print(pageRank(G, s=1))

from numpy import *

a = array([[0, 1, 1, 0],
           [1, 0, 0, 1],
           [1, 0, 0, 1],
           [1, 1, 0, 0]], dtype=float)  # dtype指定为float


def graphMove(a):  # 构造转移矩阵
    b = transpose(a)  # b为a的转置矩阵
    c = zeros((a.shape), dtype=float)
    for i in range(a.shape[0]):
        for j in range(a.shape[1]):
            c[i][j] = a[i][j] / (b[j].sum())  # 完成初始化分配
    # print c,"\n===================================================="
    return c


def firstPr(c):  # pr值得初始化
    pr = zeros((c.shape[0], 1), dtype=float)  # 构造一个存放pr值得矩阵
    for i in range(c.shape[0]):
        pr[i] = float(1) / c.shape[0]
    # print pr,"\n==================================================="
    return pr


def pageRank(p, m, v):  # 计算pageRank值
    while ((v == p * dot(m, v) + (
            1 - p) * v).all() == False):  # 判断pr矩阵是否收敛,(v == p*dot(m,v) + (1-p)*v).all()判断前后的pr矩阵是否相等，若相等则停止循环
        # print v
        v = p * dot(m, v) + (1 - p) * v
        # print (v == p*dot(m,v) + (1-p)*v).all()
    return v


if __name__ == "__main__":
    M = graphMove(a)
    pr = firstPr(M)
    p = 0.8  # 引入浏览当前网页的概率为p,假设p=0.8
    print
    pageRank(p, M, pr)  # 计算pr值