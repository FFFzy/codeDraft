# Coded By Lucky
# Time: 2021/1/4 19:33

import snownlp

input = open('荷塘月色.txt','r',encoding='UTF-8')

seg = input.read()
seg_list = snownlp.SnowNLP(seg)

output = open('SnowNLP_output.txt','w',encoding='UTF-8')
output.write("/".join(seg_list.words))

input.close()
output.close()