# main.py
from tasks import mytask
import time 


def func(num):
    start = time.time()
    t = mytask.delay({"num": num})
    print("任务：%s 耗时：%s 秒 " % (t.task_id, time.time()-start))


if __name__ == '__main__':
    for i in range(3):
        func(i)  # 这里即时返回 任务处理仍耗时


# 启动传递任务
$ python main.py