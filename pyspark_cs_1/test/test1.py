#-*-coding:utf-8-*-

from pyspark import SparkConf, SparkContext

import jieba

def split(line):
    # word_list = jieba.cut(line.strip().split("\t")[1])  #进行中文分词
    ls =[]
    word_list = line.split("\t")
    # ls.append(line)
    for word in word_list:
        if len(word)>1:      #过滤掉单音节词
            ls.append(word)
    return ls

def combine(line):
    result = ""
    result +=line[0]+"\t"+str(line[1])   #让数字在前，方便统计
    return result

def main(sc):
    text = sc.textFile("F:/out.txt")

    word_list = text.map(split).collect() #保存为列表
    print "word_list", word_list
    count = sc.parallelize(word_list[0]) #返回列表中的第一个元素
    # results = count.map(lambda w:(w,1)).reduceByKey(lambda x,y:x+y).map(combine).sortByKey().saveAsTextFile("/file/douban_movie_data/result")
    collect = count.map(lambda w: (w, 1)).reduceByKey(lambda x, y: x + y).map(combine).sortByKey().collect();
    for key in collect:
        print "key->", key
    print "succeed"

if __name__=="__main__":
    conf = SparkConf().setAppName("wordSplit")
    conf.setMaster("local")
    sc= SparkContext(conf = conf)
    main(sc)