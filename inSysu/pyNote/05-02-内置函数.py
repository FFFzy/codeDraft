# 类型转换
int('123')     # 123
float('123.4') # 123.4
str(123)       # '123' 
bool(2)        # True

bin(2)         # 0b10 二进制
oct(8)         # 0o10 八进制
hex(16)        # 0x10 十六进制

bytes(1)       # b'\x00'
ord('a')       # 97 
chr(97)        # a

list((1,2,3))        # [1, 2, 3]
set([1,2,3,3])       # {1, 2, 3}
frozenset([1,2,3,3]) # frozenset({1, 2, 3})   

dict([('A', 1), ('B', 2)])     # {'A': 1, 'B': 2}
dict(zip(['A', 'B'], [1, 2]))  # {'A': 1, 'B': 2}

# 表达式执行
eval("1+2*3")  # 7 

r = compile("3*4+5",'','eval')
eval(r)       # 17

r = compile("print('hello,world')", "<string>", "exec")
exec(r)       # hello,world

# 对象自省
type(2)       # <class 'int'>
id(2)         # 140731323023664 内存地址
dir([str])    # 返回对象的属性、方法列表 
help('sys')   # 返回对象的帮助文档
locals()      # 返回当前局部变量的字典
globals()     # 返回当前全局变量的字典
vars()        # 返回对象属性和属性值的字典

all([0,1,2])  # False 所有元素为真返回True
any([0,1,2])  # True  任一元素为真返回True

callable(2)                  # False 是否可调用
isinstance(2,int)            # True  是否是其实例
isinstance(2,(str,int,list)) # True  满足一项返回True
issubclass(str, int)         # False 是否是其子类

# 类的属性
class Student(object):
    def __init__(self, name):
        self.name = name

s = Student('Tom')
getattr(s, 'name')    # Tom
#getattr(s, 'age')    # 属性不存在触发AttributeError异常              
getattr(s, 'age', 5)  # 属性age不存在，但会返回默认值5
hasattr(s, 'age')     # False，查询属性是否存在  
setattr(s, 'age', 5)  # 设置属性 age 值
delattr(s, 'age')     # 删除属性