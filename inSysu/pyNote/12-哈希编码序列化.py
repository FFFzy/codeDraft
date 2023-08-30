## 哈希
import hashlib

md5 = hashlib.md5()
md5.update('hello'.encode('utf-8'))
md5.update('world'.encode('utf-8'))
print(md5.hexdigest())  

sha1 = hashlib.sha1()
sha1.update('hello'.encode('utf-8'))
sha1.update('world'.encode('utf-8'))
print(sha1.hexdigest()) 


## base64编码
import base64
#二进制转base64
base64.b64encode(b'binary\x00string')     # b'YmluYXJ5AHN0cmluZw=='
#base64转二进制
base64.b64decode(b'YmluYXJ5AHN0cmluZw==') # b'binary\x00string'


## json序列化
import json

# 字典序列化为json字符串
d = dict(name='Tom', age=20, score=80)
json_str = json.dumps(d)  
print(json_str)
# {"name": "Tom", "age": 20, "score": 80}

# json字符串反序列化为字典
json_str = '{"age": 20, "score": 80, "name": "Tom"}'
d = json.loads(json_str)  
print(d)
# {'age': 20, 'score': 80, 'name': 'Tom'}

# 写入json文件
with open("test.json", "w", encoding='utf-8') as dump_f:
    json.dump(d, dump_f, ensure_ascii=False)

# 读取json文件
with open("test.json",'r') as load_f:
    load_dict = json.load(load_f)
    print(load_dict)