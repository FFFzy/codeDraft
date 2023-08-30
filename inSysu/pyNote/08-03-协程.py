# 协程
import aiohttp
import asyncio

urls = ['https://www.xxxx.com'] * 400

htmls = []
sem = asyncio.Semaphore(10) # 信号量，控制协程数量

async def getHtml(url):
    async with(sem):    
        async with aiohttp.ClientSession() as session:  
            async with session.get(url) as resp:  
                html = await resp.text() 
                #content = await resp.read()
                htmls.append(html)
                            
def main():
    loop = asyncio.get_event_loop()              # 获取事件循环
    tasks = [getHtml(url) for url in urls]     # 把所有任务放到一个列表中
    loop.run_until_complete(asyncio.wait(tasks)) # 激活协程
    loop.close()                                 # 关闭事件循环

if __name__ == '__main__':
    main() 


# 多进程+协程
import time
import asyncio
import aiohttp  
from multiprocessing import Pool

all_urls = ['https://www.xxx.com'] * 400

async def get_html(url, sem):
    async with(sem):    
        async with aiohttp.ClientSession() as session:  
            async with session.get(url) as resp:  
                html = await resp.text()             
                            
def main(urls):
    loop = asyncio.get_event_loop()                # 获取事件循环
    sem = asyncio.Semaphore(10)                    # 控制并发的数量
    tasks = [get_html(url, sem) for url in urls]   # 把所有任务放到一个列表中
    loop.run_until_complete(asyncio.wait(tasks))   # 激活协程
    loop.close()                                   # 关闭事件循环


if __name__ == '__main__':
    start = time.time()
    p = Pool(4)
    for i in range(4):
        p.apply_async(main, args=(all_urls[i*100:(i+1)*100],))     
    p.close() 
    p.join()  
    print(time.time()-start)  