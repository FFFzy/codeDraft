# Coded By Lucky
# Time: 2021/1/4 18:54

import jieba

input = open('荷塘月色.txt','r',encoding='UTF-8')

seg = input.read()
seg_list = jieba.cut(seg)

output = open('jieba_output.txt','w',encoding='UTF-8')
output.write("/".join(seg_list))

input.close()
output.close()