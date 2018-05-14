
from scipy import stats
import statistics
import numpy
import sys

import random,math

print sys.path

dataset = stats.norm.rvs(loc=2, scale=5, size=(3,1))
dataset1 = [0,1,2,3,4,5,6,7,8,9];
dataset=numpy.array(dataset1)
print dataset
# print 'observed: %.5f' % numpy.mean(dataset)
ab =  stats.ttest_1samp(dataset ,popmean=5)



print ab
print 'std: %.5f'% numpy.std(dataset)
cd = numpy.std(dataset)/math.sqrt(len(dataset))
print 'error : %.5f'%  cd
std = dataset.std()
print std
mean = dataset.mean()
print "std:%.5f"% std
print "mean:%.5f"% mean

interval=stats.t.interval(0.95,len(dataset)-1,5,std)
print interval

ci = stats.norm.interval(0.95, loc=mean, scale=std)
#print ['%f' % mean, '%f' % std, '(%.3f,%.3f)' % (ci[0], ci[1])]
mean, median, stddev, min, max, confidence = statistics.stats(dataset, 0.05)
print mean, stddev, min, max, confidence
print mean-5
print mean + confidence - 5
print mean  - confidence - 5



# 
# def t_test_interval(dataset, percent, expectedVal):
#     std = dataset.std()
#     interval=stats.t.interval(percent, len(dataset)-1, expectedVal, std) 
#     return interval
# 
# def t_test_sample(dataset, percent):
#    mean, median, stddev, min, max, confidence = statistics.stats(dataset, percent)
#    conf_low =  mean - confidence - 5
#    conf_up = mean + confidence - 5
#   
#    
# 
#    return mean, stddev, conf_low, conf_up, confidence

# x = "195:C11db4a8c5ff548e29608e38961098755:D2015:R14250e6b72e14233b7bbbad8870329c6:A0037"
# y = x.split(":")
# print y[1]
# 
# 
# t = []
# t.append('key')
# t.append('keyt')
# 
# print t.__contains__('keyt')
 
 


