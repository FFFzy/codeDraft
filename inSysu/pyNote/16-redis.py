import redis

pool = redis.ConnectionPool(host='localhost', port=6379, decode_responses=True)  
r = redis.Redis(connection_pool=pool)

if __name__ == '__main__':
    # 字符串
    r.set('str1', 1, ex=3)  # 过期时间3s
    r.expire('str1', 10)    # 设置过期时间为10s
    if r.exists("str1"):
        v = r.get('str1')
        print(v, type(v))   # 1 <class 'str'>
        r.delete("str1")

    # hash
    r.hset("hash1", "k1", "v1")
    r.hset("hash1", "k2", "v2")
    v = r.hget("hash1", "k1")
    print(v, type(v))  # v1 <class 'str'>
    vv = r.hgetall("hash1")
    print(vv)  # {'k1': 'v1', 'k2': 'v2'}
    
    # 列表
    r.lpush("list1", 1, 2, 3)
    r.rpush("list1", 4, 5, 6)
    r.lrem('list1', 1, 2)   # 删除1个值为2的元素
    v = r.lrange("list1", 0, -1)
    print(v)  # ['3', '1', '4', '5', '6']
    
    # 集合
    r.sadd("set1", 33, 44, 55, 66)
    r.srem("set1", 33)
    v = r.smembers("set1")
    print(v)  # {'55', '44', '66'}

    # 有序集合
    r.zadd("zset1", {'A': 100, 'B': 90, 'C': 80})
    r.zrem("zset1", 'A')            # 删除
    v = r.zrevrange('zset1', 0, 1)  # 返回前两名元素
    print(v)  # ['B', 'C']