#1 server.py
import socket

host = '127.0.0.1' # 设置ip
port = 9000        # 设置端口

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  # udp协议
s.bind((host, port))

while True:
    data, addr = s.recvfrom(1024)
    print('server收到来自 {} 的消息：'.format(addr), data)
    s.sendto(data.upper(), addr)
    
s.close()


#2 client.py
import socket

host = '127.0.0.1' # 设置ip
port = 9000        # 设置端口

c = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)

c.sendto(b'hello', (host, port))
data, addr = c.recvfrom(1024)
print('客户端收到来自 {} 的消息：'.format(addr), data)
    
c.close()