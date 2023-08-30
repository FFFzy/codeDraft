#迭代器
class Fib(object):  
    def __init__(self, num):
        self.a, self.b = 0, 1
        self.num = num
        
    def __iter__(self):       
        return self           
    
    def __next__(self):
        self.a, self.b = self.b, self.a + self.b
        if self.a > self.num: 
            raise StopIteration()
        return self.a  

fib = Fib(10)
for i in fib:
    print(i) # 1 1 2 3 5 8

# 生成器
def g(num):
    for i in range(num):
        yield i * i

for i in g(5):
    print(i) # 0 1 4 9 16

# 上下文管理器
class File(object):
    def __init__(self, name, method):
        self.file = open(name, method)
    
    def __enter__(self):
        print('enter')
        return self.file
    
    #exit返回True清空处理异常，否则抛出异常
    def __exit__(self, type, val, trace): 
        print('exit')
        self.file.close()
        return True

with File('test.py', 'r') as f:
    f.undefined_function()
    print('Hello')  # 这里不执行了
# enter
# exit