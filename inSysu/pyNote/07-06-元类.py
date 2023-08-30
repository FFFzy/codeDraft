#1 type()动态创建类
def myfunc(self, name='Tom'):
     print('Hello, %s!' % name)
        
Student = type('Student', (object,), dict(func=myfunc)) 
#参数分别为：class名称、继承的父类集合、方法名称与函数绑定
s = Student()
s.func('Jack') # Hello, Jack!


#2 metaclass元类
class ListMetaclass(type): 
    def __new__(cls, name, bases, attrs):
        # __new__ 是在__init__之前被调用的特殊方法
        # __new__是用来创建对象并返回之的方法,  而__init__只是用来将传入的参数初始化给对象
        attrs['doc'] = 'A metaclass!'  # 为类动态添加属性
        attrs['add'] = lambda self, value: self.append(value)  # 为类动态添加方法

        new_attrs = {}
        for k, v in attrs.items():
            print("k=%s v=%s" % (k,v))  # 打印所有类属性出来
            new_attrs[k] = v
            if not k.startswith("__"):
               new_attrs[k.upper()] = v

        return type.__new__(cls, name, bases, new_attrs)

# 使用ListMetaclass来定制类
class MyList(list, metaclass=ListMetaclass):
    name = "MYLIST"

l = MyList()
# k=__module__ v=__main__
# k=__qualname__ v=MyList
# k=name v=MYLIST
# k=doc v=A metaclass!
# k=add v=<function ListMetaclass.__new__.<locals>.<lambda> at 0x0000018BDD8CB920>
print(l.doc)   # A metaclass!
print(l.NAME)  # MYLIST
l.add(1)  
print(l)       # [1]
