#1 单继承
class Base(object):
    def __init__(self):
        print('Base')
             
class A(Base):
    def __init__(self):   
        # Student.__init__(self, name)
        super().__init__()     # 调用super()会得到一个用来引用父类属性的对象，且严格按照
                               # MRO规定的顺序向后查找。即使没有直接继承关系，super()仍然
                               # 会按照MRO继续往后查找                        
        print('A')
        
a = A()
# Base
# A

#2 多继承
'''
子类会先于父类被检查
多个父类会根据它们在列表中的顺序被检查
如果对下一个类存在两个合法的选择，选择第一个父类  
'''
class Base:
    def __init__(self):
        print('Base')

class A(Base):
    def __init__(self):
        super().__init__()
        print('A')

class B(Base):
    def __init__(self):
        super().__init__()
        print('B')

class C(A,B):
    def __init__(self):
        super().__init__()  # Only one call to super() here
        print('C')

c = C()
# Base
# B
# A
# C
print(C.__mro__)  
# 所有基类的线性顺序表
# (<class '__main__.C'>, <class '__main__.A'>, <class '__main__.B'>, <class '__main__.Base'>, <class 'object'>)