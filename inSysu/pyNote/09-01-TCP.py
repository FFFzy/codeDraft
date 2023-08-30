#1 服务端 server.py
import socket    

host = '127.0.0.1'  # 设置ip
port = 9000         # 设置端口

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # 创建socket对象
s.bind((host, port))     # 绑定ip和端口
s.listen(5)              # 等待客户端连接
print("开始监听...")

while True:
    c, addr = s.accept() # 建立客户端连接
    print('客户端连接地址：', addr)

    data = c.recv(2048)
    print("接收到消息：", data.decode('utf-8'))

    c.send(b'Welcome to connect!')
    c.close()            # 关闭连接

    
#2 客户端 client.py
import socket  

host = '127.0.0.1' # 设置ip
port = 9000        # 设置端口

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # 创建socket对象
s.connect((host, port))

s.send(b'Hello')

data, addr = s.recvfrom(1024)
print(data.decode('utf-8'))

s.close() 