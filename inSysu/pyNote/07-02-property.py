# property
class Student(object):
    def __init__(self, birth):
        self._birth = birth
    
    # birth是可读写属性
    @property                     
    def birth(self):
        return self._birth
    
    @birth.setter
    def birth(self, value):
        self._birth = value    
    
    @birth.deleter
    def birth(self):
        print("删除属性")
        del self._birth
        
    # age是只读属性
    @property                     
    def age(self):
        return 2023 - self._birth  

s = Student(2000)
print(s.birth) # 2000
s.birth = 2001
print(s.birth) # 2001
print(s.age)   # 22

del s.birth    # 删除属性