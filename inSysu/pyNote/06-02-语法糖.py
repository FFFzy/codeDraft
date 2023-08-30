# 装饰器
import functools

def log(func):
    @functools.wraps(func)  # 把原始函数的__name__等属性复制到wrapper()函数中
    def wrapper(*args, **kw):
        print(func.__name__) 
        return func(*args, **kw)
    return wrapper

@log
def func():
    print('Hello')
    
func()
# func
# Hello

# 传入参数的装饰器
def log(text):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kw):
            print(text, func.__name__)
            return func(*args, **kw)
        return wrapper
    return decorator

@log('execute')
def func():
    print('Hello')

func()
# execute func
# Hello