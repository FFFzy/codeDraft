class Student(object):
    score = 100  # 类的基本属性
    __math = 10  # 类的私有属性,__开头的属性和方法因为改名，继承时不会被覆盖
    
    __slots__ = ['name', '_age', '__sex']  
    #限制实例能添加的属性，减少实例占用内存（字典改为数组）
    #__slots__定义的属性仅对当前类实例起作用，对继承的子类不起作用，若在子类中定义__slots__，
    #则子类实例允许添加的属性为自身的__slots__加上父类的__slots__ 
    #1.Python的很多特性都依赖于普通的基于字典的实现。
    #2.定义了slots后的类不再支持多继承
    #3.内存优化工具而不是封装工具

    def __init__(self, name, age, sex):
        self.name = name 
        self._age = age    # 约定的私有属性
        self.__sex = sex   # 私有属性

    # 约定的私有方法
    def _getage(self):     
        print("age: ", self._age)      

    # 私有方法
    def __getsex(self):    
        print('sex: ', self.__sex)

    # 静态方法
    @staticmethod 
    def func1():
        print('staticmethod'); 

    # 类方法
    @classmethod  
    def func2(cls, name, age, sex):
        print(cls.score)            # 调用类属性
        cls(name, age, sex).func1() # 调用静态方法
        
s = Student('aya', 18, 'girl')          
print(s.score, s._Student__math)        # 100 10       访问类属性
print(s.name, s._age, s._Student__sex)  # aya 18 girl  访问实例属性
s._getage()                             # age:  18
s._Student__getsex()                    # sex:  girl   实例调用私有方法
s.func1()                               # staticmethod 实例调用静态方法
Student.func1()                         # staticmethod 类调用静态方法
Student.func2('aya', 18, 'girl')        # 100          类调用类方法

print(Student.__dict__)   # 类的属性字典
print(Student.__doc__)    # 类的文档字符串
print(Student.__name__)   # 类名
print(Student.__module__) # 类定义所在的模块
print(Student.__bases__)  # 类的所有父类组成的元组