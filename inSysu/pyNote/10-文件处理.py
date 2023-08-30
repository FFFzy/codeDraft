## 文件读写
# 二进制文件读写使用 'rb' 'wb'
with open('test.txt', 'r') as f:
    print(f.read(5))      # 每次最多读取5个字节的内容,返回str
    print(f.read())       # 读取文件全部内容,返回str
    print(f.readline())   # 每次读取一行内容,返回str
    print(f.readlines())  # 读取文件全部内容,按行返回list

with open('test.txt', 'w') as f:
    f.write('Hello, world!')


## os模块
import os 

print(os.getcwd())         # 当前工作目录
print(os.listdir('.'))     # 列出目录的文件
print(os.stat('test.txt')) # 获取元数据

os.mkdir('test')         # 创建目录 
os.chdir('test')         # 切换目录
os.makedirs('dir1/dir2', exist_ok=True) # 创建多级目录，已存在时不报错

os.rename('test.txt', 'test.py') # 重命名 
os.remove('test.py')             # 删除文件

print(os.path.abspath('.'))  # 查看绝对路径
print(os.path.exists('.'))   # 判断文件或目录是否存在
print(os.path.isdir('.'))    # 判断是否是目录
print(os.path.isfile('.'))   # 判断是否是文件
print(os.path.islink('.'))   # 判断是否是链接
print(os.path.realpath('.')) # 返回被链接的原始文件
print(os.path.getsize('test.txt'))  # 获取文件大小
print(os.path.getmtime('test.txt')) # 获取修改日期

path = '/home/test/test.txt'
print(os.path.basename(path)) #'test.txt' 获取文件名
print(os.path.dirname(path))  #'/home/test' 获取目录
print(os.path.split(path))    #('/home/test', 'test.txt') 目录分离 
print(os.path.splitext(path)) #('/home/test/test', '.txt') 目录分离

print(os.path.join('test', 'test.txt'))      # test/test.txt 目录合并
print(os.path.expanduser('~/test/test.txt')) # 展开绝对路径 

#递归遍历文件夹
for root, dirs, files in os.walk('.'):
    for name in files:
        print(os.path.join(root, name))
    for name in dirs:
        print(os.path.join(root, name))