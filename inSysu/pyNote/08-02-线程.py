# 多线程
import threading

lock = threading.Lock()
num = 0

def func(n):
    lock.acquire()
    global num
    num += n
    print('arg: {} num: {}'.format(n, num))
    lock.release()   
    
t1 = threading.Thread(target=func, args=(1,), name='Thread1', daemon=True)
t2 = threading.Thread(target=func, args=(2,), name='Thread2', daemon=True)
t1.start()  #启动线程
t2.start()
t1.join()   #阻塞等待线程
t2.join()   
# arg: 1 num: 1
# arg: 2 num: 3


# 线程池
from concurrent.futures import ThreadPoolExecutor, as_completed 
import time 

def square(n):
    time.sleep(1)
    return n * n

if __name__ == '__main__':
    with ThreadPoolExecutor(max_workers=3) as executor:
        tasks = [executor.submit(square, num) for num in range(5)]
        for future in as_completed(tasks):
            print(future.result())
# 1
# 0
# 4
# 9
# 16