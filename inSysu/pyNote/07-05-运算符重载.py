#1 常用运算符 
class Vector(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y
        
    # print()时显示
    def __str__(self):
        return 'Vector(%r, %r)' % (self.x, self.y)
    __repr__ = __str__ # 对象直接调用时显示
    
    # 重载加号运算符，类似的：sub  mul  div  mod  pow
    def __add__(self, other):
        return Vector(self.x+other.x, self.y+other.y)
    
    # 直接调用实例自身
    def __call__(self):
        print('Called by myself!')

    # 在对象被删除时自动触发
    def __del__(self):
        print('Deleted!')
    
v1 = Vector(1, 1)
v2 = Vector(2, 2)
v3 = v1 + v2
print(v3)      # Vector(3, 3)
v3()           # Called by myself!
del v3         # Deleted!


#2 重载下标运算符
class IntList(object):
    def __init__(self, *args):
        if all(map(lambda x:isinstance(x, int), args)):
            self._list = list(args)
        else:
            print("所有传入的参数必须是整数！")
            
    def __getitem__(self, index):
        return self._list[index]
    
    def __setitem__(self, index, value):
        self._list[index] = value
        
    def __delitem__(self, index):
        del self._list[index]
        
    def __len__(self):
        print("调用__len__方法")
        return len(self._list)

l = IntList(1,2,3,4,5)
print(len(l))
# 调用__len__方法
# 5

print(l[0]) # 1

l[0] = -1
print(l[0]) # -1

del l[0]
print(l[0]) # 2