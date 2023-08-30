name = input("请输入您的姓名：")  # 输入，返回字符串
print("Hello: ", name)          # 输出

l = [-2, -1, 0, 1, 2]
max(l)               # 2
min(l)               # -2
len(l)               # 5 
sum(l)               # 0    

abs(-1)              # 1      绝对值
pow(2, 5)            # 32     开方
divmod(5, 2)         # (2, 1) (商,余数)
round(1.25361, 3)    # 1.254  保留小数
round(1627731, -1)   # 1627730
hash('B')            # 8720545829485517778 返回对象的哈希值

for i in range(5):
    print(i)  # 0 1 2 3 4
for i in range(1, 5):
    print(i)  # 1 2 3 4
for i in range(1, 5, 2):
    print(i)  # 1 3

# map映射
for i in map(lambda x: x*x, [1, 2, 3]):
    print(i)  # 1 4 9
for i in map(lambda x,y: x+y, [1,3,5], [2,4,6]):
    print(i)  # 3 7 11

# filter过滤
for i in filter(lambda e: e%2, [1, 2, 3]):  
    print(i)  # 1 3

# sorted排序
print(sorted([('b', 2), ('a', 1)], key=lambda x:x[0]))   
# [('a', 1), ('b', 2)]    

# zip打包
l1 = ['a','b','c']
l2 = [1,2,3]
for i in zip(l1, l2):
    print(i)  # ('a', 1) ('b', 2) ('c', 3)       

# zip解压
for i in zip(*zip(l1, l2)):
    print(i)  # ('a', 'b', 'c') (1, 2, 3)