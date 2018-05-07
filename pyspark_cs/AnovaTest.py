import numpy
value=[1.00,2.00,3.00,4.00,5.00,6.00,7.00,8.00,9.00,2.00]
group=[1.00,12.00,3.00,4.00,5.00,6.00,17.00,8.00,9.00,10.00]

from scipy import stats
import pandas as pd
from statsmodels.formula.api import ols
from statsmodels.stats.anova import anova_lm
from pandas import Series, DataFrame

import numpy as np
import statsmodels.api as sm
from statsmodels.stats.multicomp import pairwise_tukeyhsd


hs=[1.00,2.00,3.00,4.00,5.00,6.00,7.00,8.00,9.00,2.00]
fetus=[1.00,2.00,1.00,2.00,1.00,2.00,2.00,2.00,1.00,1.00]
observer=[1.00,2.00,3.00,2.00,1.00,2.00,4.00,4.00,1.00,1.00]
data = {'value':value, 'group':group}
df = DataFrame(data)
# print frame
import  json
formula='hs ~ fetus'
anova_results = anova_lm(ols(formula, df).fit())
print anova_results

formula = 'hs~C(fetus)+C(observer)+C(fetus):C(observer)'
anova_results = anova_lm(ols(formula, df).fit())

print anova_results

hsd=pairwise_tukeyhsd(hs, fetus)
print hsd.summary()