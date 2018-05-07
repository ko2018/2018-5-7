import numpy
from scipy.stats import ttest_ind
from scipy import stats
 

a1 = [1,3,5,17,9]
b1 = [12,4,6,8,10,41]
print numpy.array(a1).mean()
print numpy.array(b1).mean()

t,p=ttest_ind(numpy.array(a1), numpy.array(b1))  
print t,p
import statsmodels.stats.api as sms


import statistics
dataseta = numpy.array(a1)
datasetb = numpy.array(b1)
import math
mean1, median, stddev1, min1, max1, confidence = statistics.stats(dataseta, 0.05)
print  mean1, median, stddev1, min1, max1, confidence
print "math==>", stddev1/math.sqrt(len(dataseta))
# 
# print  mean + confidence
# print  mean - confidence


mean2, median, stddev2, min, max, confidence = statistics.stats(datasetb, 0.05)
print "math==>", stddev2/math.sqrt(len(datasetb))
print  "==>", mean2, median, stddev2, min, max, confidence
print  "====>", confidence
print "%=", mean2 + confidence
print "%=", mean2  - confidence
# print  mean + confidence
# print  mean - confidence
mean = dataseta.mean()
# std=dataseta.std()

interval=stats.t.interval(0.95,len(datasetb)-1,mean,stddev2)
print interval
 
# print levene(dataseta, datasetb)
# 
# 
# print ttest_ind(dataseta, datasetb, equal_var=True)
# print ttest_ind(dataseta, datasetb, equal_var=False)


from scipy.stats import levene  
print "====%%%%%===", levene(dataseta, datasetb, center = 'trimmed')


 
cm = sms.CompareMeans(sms.DescrStatsW(dataseta), sms.DescrStatsW(datasetb))
print cm.tconfint_diff(alpha=0.05 , usevar='pooled')

cm = sms.CompareMeans(sms.DescrStatsW(dataseta), sms.DescrStatsW(datasetb))
print cm.tconfint_diff(alpha=0.05, usevar='unequal')

# print (stats.t.ppf(1-0.05, 5))

import statsmodels.api as sm
print "+++", sm.stats.ttest_ind(dataseta,datasetb,usevar='pooled')  
print "+++", sm.stats.ttest_ind(dataseta,datasetb,usevar='unequal')  

# interval=stats.t.interval(0.95,len(dataseta)-1,mean,stddev1)
# print interval
(statistic, pvalue) = stats.ttest_ind_from_stats(mean1=mean1, std1=stddev1, nobs1=len(dataseta), mean2=mean2, std2=stddev2, nobs2=len(datasetb))

print "t statistic is: ", statistic
print "pvalue is: ", pvalue

print math.sqrt((len(dataseta)+len(datasetb))*(len(dataseta)+len(datasetb))*( 1.0/len(dataseta) + 1.0/len(datasetb)) )

print math.sqrt(stddev1*stddev1/len(dataseta) + stddev2*stddev2/len(datasetb))

at = (len(dataseta)+len(datasetb)+1)*(len(dataseta)+len(datasetb))
bt = ( 1.0/len(dataseta) + 1.0/len(datasetb)) 
print math.sqrt(at * bt)













