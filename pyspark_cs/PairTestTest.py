# coding:gbk 
from scipy import stats
import numpy as np

import statistics

a = [1.00, 2.00, 3.00, 4.00, 5.00, 6.00, 7.00, 8.00, 9.00, 2.00]
b = [1.00, 12.00, 3.00, 4.00, 5.00, 6.00, 17.00, 8.00, 9.00, 10.00]
# print "mean", numpy.mean(a)
# print "std", numpy.std(a)
import math
mean, _, stddev, _, _, confidence = statistics.stats(a, 1.0 - 0.95)
print mean, stddev, confidence
print stddev/math.sqrt(len(a))

# print "mean", numpy.mean(b)
# print "std", numpy.std(b)
N = a.__len__()


mean, _, stddev, _, _, confidence = statistics.stats(b, 1.0 - 0.95)
print mean, stddev, confidence
print stddev/math.sqrt(len(b))
print "ttest_rel", stats.ttest_rel(a,b)
print stats.ttest_rel(a,b)[0]

arr = np.array([a,b]) 
print "corrcoef", np.corrcoef(arr)[0, 1]

r, p=stats.pearsonr(a, b)
print "pearsonr", r, p

arr1 = np.array(a) 
brr1 = np.array(b)
crr1 = -brr1 + arr1

mean, _, stddev, _, _, confidence = statistics.stats(crr1, 1.0 - 0.95)
print mean, stddev, confidence
print "stddeverror", stddev/math.sqrt(len(crr1))
print mean + confidence
print mean - confidence
ttest_1samp = stats.ttest_1samp(crr1 , popmean=0.95)
print ttest_1samp

# data1 = '000:Rcb87ebfd61d84558b1f4967ec949e20e:D2010-10:1:C2:F_91F18C751838C2638FB4183E7A8EDFAC:98002070'

# print "data1 == >", data1.split(":")[6].__len__()

# data2 ={"_390":"51","_1":"F_91F18C751838C2638FB4183E7A8EDFAC","_4":"98002070","_16":"20081127","_9":"1","_11":"19741127","_10":"34"}
# import json
# import utils
# print utils.to_json(data2)
# valsDict = json.loads(utils.to_json(data2))
# print valsDict.has_key("_360")


val = [1,2,4,5]
print val[0:3]
# print val.__len__()

import sys
reload(sys)
sys.setdefaultencoding('GBK')

 
print "中文".encode('GBK')