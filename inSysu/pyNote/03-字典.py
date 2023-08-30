# 字典操作
d = {1: 'A', 2: 'B'}
d.items()     # dict_items([(1, 'A'), (2, 'B')]) 返回所有(键, 值)元组
d.keys()      # dict_keys([1, 2])                返回所有键
d.values()    # dict_values(['A', 'B'])          返回所有值

1 in d                 # True  若键存在返回True
d.get(3, None)         # None  返回指定键的值，若键不存在返回None
d.setdefault(3, None) # None  返回指定键的值，若键不存在将会添加键并将值设为None

d.update({3:'C'})      # {1: 'A', 2: 'B', 3: 'C'} 添加键值对
d.pop(3)               # {1: 'A', 2: 'B'} 根据指定的键删除键值对
del d[2]               # {1: 'A'}         根据指定的键删除键值对
d.clear()              # {}               删除字典内所有键值对

# 字典表达式
d = {1: 'A', 2: 'B'}
d1 = {key: value for key, value in d.items() if key > 1}
# {2: 'B'}

d2 = {value: key for key, value in  d.items()} 
# {'A': 1, 'B': 2}