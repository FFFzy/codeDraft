import pymongo
from pymongo import MongoClient

client = MongoClient('mongodb://localhost:27017')

if __name__ == '__main__':
    db = client.blog   # 数据库
    col = db.articles  # 集合

    # 增
    col.insert_one({"title": "1", "content": "A", "score": 95})
    col.insert_many([{"title": "2", "content": "B", "score": 90},
                     {"title": "3", "content": "C", "score": 70},
                     {"title": "4", "content": "D", "score": 50},
                     {"title": "5", "content": "E", "score": 40},
                     {"title": "6", "content": "F", "score": 10}])

    # 改
    col.update_one({'title': "1"}, {'$inc': {'score': 5}})
    col.update_many({'score': {'$lt': 80}}, {'$inc': {'score': 5}})
    # lt gt lte gte ne 
    # in nin  是否范围内：{'$in': [70, 80]}
    # regex   正则表达式：{'$regex': '^A.*'}
    # exists  属性是否存在：{'score': {'$exists': True}}
    # type    类型盘算：{'score': {'$type': 'int'}}

    # 删
    col.delete_one({'title': '1'})
    col.delete_many({'score': {'$gt': 70}})

    # 查
    doc = col.find_one({"title": "1"})
    print(doc)                   # None
    print(col.find().count())    # 3
    for doc in col.find().sort('score', pymongo.ASCENDING):
        print(doc)
        # {'_id': ObjectId('64aeae4264faad021d1118c9'), 'title': '6', 'content': 'F', 'score': 15}
        # {'_id': ObjectId('64aeae4264faad021d1118c8'), 'title': '5', 'content': 'E', 'score': 45}
        # {'_id': ObjectId('64aeae4264faad021d1118c7'), 'title': '4', 'content': 'D', 'score': 55}


