import re

string = 'Hello123World456Hello'

# 从起始位置匹配第一个
print(re.match('Hello', string).span()) # (0, 5)      
print(re.match('World', string))        # None

# 在整个字符串匹配第一个
print(re.search('Hello', string).span()) # (0, 5)      
print(re.search('World', string).span()) # (8, 13)

result = re.search(r'([A-Za-z]+)(\d+)', string)
print(result.group(0)) # Hello123
print(result.group(1)) # Hello
print(result.group(2)) # 123

# 匹配所有
pattern = re.compile(r'\d+')   
result = pattern.findall(string)
print(result) # ['123', '456']

pattern = re.compile(r'([A-Za-z]+)(\d+)')   
result = pattern.findall(string)
print(result) # [('Hello', '123'), ('World', '456')]

# 将匹配的子串替换
result = re.sub(r'[A-Za-z]+', '', string)
print(result) # 123456

# 将匹配的数字乘以2
def double(matched):
    value = int(matched.group('value'))
    return str(value * 2)
print(re.sub('(?P<value>\d+)', double, string))
# Hello246World912Hello

# 按照匹配的子串分割
result = re.split(r'[A-Za-z]+', string)
print(result) # ['', '123', '456', '']