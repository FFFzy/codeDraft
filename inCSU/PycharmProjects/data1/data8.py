# Coded By Lucky
# Time: 2021/4/19 21:35

import codecs
import csv
import jieba
linesName=[]
names={}
relationship={}
jieba.load_userdict('role.txt')
txt=[ line.strip() for line in open('stopwords.txt','r',encoding='gbk')]
name_list=[ i.replace('\n','') for i in open('role.txt','r',encoding='utf-8').readlines()]

def base(path):
    with codecs.open(path,'r','UTF-8') as f:
        for line in f.readlines():
            line=line.replace('\r\n','')
            poss = jieba.cut(line)
            linesName.append([])
            for word in poss:
                if word in txt:
                    continue
                linesName[-1].append(word)
                if names.get(word) is None:
                    names[word]=0
                    relationship[word]={}
                names[word]+=1
    return linesName,relationship

def relationships(linesName,relationship,name_list):
    for line in linesName:
        for name1 in line:
            if name1 in name_list:
                for name2 in line:
                    if name1==name2:
                        continue
                    if relationship[name1].get(name2) is None:
                        relationship[name1][name2]=1
                    else:
                        relationship[name1][name2]+=1
    return relationship

def write_csv(relationship):
    csv_writer2=open('edges.csv','w',encoding='gb18030')
    writer=csv.writer(csv_writer2,delimiter=',',lineterminator='\n')
    writer.writerow(['Source','Target','Weight'])
    for name,edges in relationship.items():
        for k,v in edges.items():
            if v>10:
                writer.writerow([name,k,v])
    csv_writer2.close()

if __name__=='__main__':
    linesName,relationship=base('txt_all.txt')
    data=relationships(linesName,relationship,name_list)
    write_csv(data)
