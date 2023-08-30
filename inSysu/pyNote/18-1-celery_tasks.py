# tasks.py
import time
from celery import Celery

app = Celery('tasks', broker='redis://localhost:6379/0')

@app.task
def mytask(params):
    start = time.time()
    print('task start...')
    time.sleep(params['num'])
    print("task use：%s seconds " % (time.time() - start))


# 启动 Worker，监听 Broker 中是否有任务
$ celery -A tasks worker --loglevel=info    