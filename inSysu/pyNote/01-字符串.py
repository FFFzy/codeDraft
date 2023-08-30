s = 'Hello'

s.upper()           # 全部大写
s.lower()           # 全部小写
s.swapcase()        # 大小写互换
s.capitalize()      # 首字母大写，其余小写

s.strip()           # 去两边空格
s.lstrip()          # 去左边空格
s.rstrip()          # 去右边空格
s.strip('Ho')       # 去两边所有指定的字符
s.lstrip('lo')      # 去左边所有指定的字符
s.rstrip('lo')      # 去右边所有指定的字符

s.rjust(7)          # 获取固定长度，右对齐，左边不够用空格补齐
s.ljust(7)          # 获取固定长度，左对齐，右边不够用空格补齐
s.center(7)         # 获取固定长度，中间对齐，两边不够用空格补齐
s.zfill(7)          # 获取固定长度，右对齐，左边不足用0补齐

s.startswith('He')  # 是否以'He'开头,可传元组
s.endswith('lo')    # 是否以'lo'结尾
s.isalnum()         # 是否全为字母或数字
s.isalpha()         # 是否全字母
s.isdigit()         # 是否全数字
s.islower()         # 是否全小写
s.isupper()         # 是否全大写

s.find('o')         # 查找返回'o'第一次出现的位置，没有返回-1
s.find('o', 1)      # 从指定起始位置开始查找
s.find('o', 1, 3)   # 从指定起始及结束位置查找
s.rfind('o')        # 从右边开始查找
s.count('o')        # 查找到指定字符串的数量

s.replace('l', 'm')      # 替换所有'l'为'm'
s.replace('l', 'm', 1)   # 替换指定次数的'l'为'm'
s.expandtabs(4)          # 每个tab替换为4个空格，默认为8个

s.split(' ')                        # 按' '分割字符串，返回列表
''.join(['H', 'e', 'l', 'l', 'o'])  # 按''连接列表元素，返回字符串