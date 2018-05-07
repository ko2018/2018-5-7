import numpy
value=[1.00,2.00,3.00,4.00,5.00,6.00,7.00,8.00,9.00,2.00]
group=[1.00,12.00,3.00,4.00,5.00,6.00,17.00,8.00,9.00,10.00]

from scipy import stats
import pandas as pd
from statsmodels.formula.api import ols
from statsmodels.stats.anova import anova_lm
from pandas import Series, DataFrame

data = {'value':value, 'group':group}
frame = DataFrame(data)
print frame
import  json
anova_result = anova_lm(ols('value~C(group)', frame).fit())
print anova_result
print (anova_result.to_json())
#
# dict = {'rowKey':'1', 'codeID':'2', 'organID':'3', 'examID':'4'}
# print "dict--->", dict
# print "tuple -- >", tuple(dict)