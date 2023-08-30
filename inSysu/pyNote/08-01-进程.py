# 多进程
from multiprocessing import Process, Queue
import os

def write(q):          
    for value in ['A', 'B', 'C', 'D']:
        print('Process %s put %s to queue.' % (os.getpid(), value))
        q.put(value)
        
def read(q):          
    while True:
        value = q.get(True)
        print('Process %s get %s from queue.' % (os.getpid(), value))

if __name__ == '__main__':
    q = Queue(maxsize=4)    # 父进程创建Queue，并传给各个子进程 q.qsize()
    
    pw = Process(target=write, args=(q,))
    pr = Process(target=read, args=(q,))
    pw.start()     
    pr.start()     
    
    pw.join()      # 等待pw结束
    pr.terminate() # pr进程是死循环，强行终止
    
# Process 16304 put A to queue.
# Process 16304 put B to queue.
# Process 16304 put C to queue.
# Process 14676 get A from queue.
# Process 16304 put D to queue.
# Process 14676 get B from queue.
# Process 14676 get C from queue.
# Process 14676 get D from queue.


# 进程池
from multiprocessing import Pool
import multiprocessing as mp
import time

def func(message):
    time.sleep(1)
    print('PID-{}: '.format(mp.current_process().pid), message)

if __name__ == '__main__':
    p = Pool(2)

    for i in range(5):
        p.apply_async(func, args=(i,))    
        
    p.close() #调用close()之后不能添加新的Process
    p.join()  #阻塞等待  
# PID-4368:  0
# PID-15972:  1
# PID-4368:  2
# PID-15972:  3
# PID-4368:  4