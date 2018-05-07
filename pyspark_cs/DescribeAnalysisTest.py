

import math
import collections
import utils
dataset = [1.0,12.0,3.0,4.0,5.0,6.0,17.0,8.0,9.0,10.0]
counter = collections.Counter(dataset)
tmp = dict(counter)

sort=sorted(tmp.items(),key=lambda e:e[1],reverse=True)
tmp = dict(sort)
k = 0
N = len(dataset) *1.0
# print tmp
result = {}
for key in tmp :
    val = tmp[key]
#     print key, tmp[key]
    k = k + tmp[key]
#     print tmp[key]*1.0/N, k*1.0/N
    tmp[key] = val, val*1.0/N*100, val*1.0/N*100, k*1.0/N*100
tmp["N"] = len(dataset), 100.0, 100.0
# print tmp
import json
# print json.dumps(tmp,sort_keys=True,indent =4,separators=(',', ': '),encoding="gbk",ensure_ascii=True )
tmp["N"] = len(dataset)
tmp["TYPE"] = "1"
# print "sort", dict(sort)
# import utils
# print utils.is_number("-1.0T")
# print 0.703/math.sqrt(len(dataset))
import pandas as pd

# df = pd.Series(dataset);
# print "skew", utils.get_Decimal(df.skew())
# N = len(dataset)
# m =  6*N*(N-1)*1.0
# p = ((N-2)*(N+1)*(N+3))*1.0
# print m
# print p
# print math.sqrt(m / p)


# print "kurt", utils.get_Decimal(df.kurt())
# k = 24*N*math.pow(N-1, 2)*1.0 
# print k
# y = (N-2)*(N-3)*(N+3)*(N+5)*1.0
# print y
# print math.sqrt(k/y)
# 
# print df.median()
# 
# print "9=1.000".__contains__("9=")
# start = 1
# end = 100
# sum1 = 0
# for n in range(start,end,10):  
#    
#     print n
    
# print '3,6,7'.split(',')
# sum1 = 15
# xx = (100/sum1)
# arrs = utils.floatrange(0.0,100.0, 16)
# print  arrs
# 
# print  arrs[1:len(arrs)-1]

import numpy as np
# a=np.array(dataset)
# print np.percentile(dataset,40)
dataset_list = list(dataset)
dataset_list = sorted(dataset_list)
# print dataset_list
# per = 0.066667
per = 0.8666666667
kill= (len(dataset_list)+1)*per
# print dataset_list
j = int(kill)
g = kill - int(kill)
# print 'j',j
# print 'g',g
# print (1-g)*dataset_list[j-1] 
# print g*dataset_list[j+1-1]
# 
# print dataset_list[j]
import utils
# arrs = [6.6666667, 10,12,13.333333,20,25,26.6666667,33.333333333,40,46.666666666,50,53.333333333333,55,60,66.66666667]
# for arr in arrs : 
#     print arr , "rs =>",  utils.get_percent(dataset_list, arr)

# print dict(list(5, 7))

arr = []
div = {}
div["1"] = 1
arr.append(div)
div["2"] = 1
arr.append(div)
div["3"] = 1
arr.append(div)
div["4"] = 1
arr.append(div)
div["5"] = 1
arr.append(div)
print div 
print arr
    
    


