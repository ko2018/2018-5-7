import numpy
value=[1.00,2.00,3.00,4.00,5.00,6.00,7.00,8.00,9.00,2.00]
group=[1.00,12.00,3.00,4.00,5.00,6.00,17.00,8.00,9.00,10.00]
cul=[1,1,1,1,1,1,1,1,1,1]

from scipy import stats
import pandas as pd
from statsmodels.formula.api import ols
from statsmodels.stats.anova import anova_lm
from pandas import Series, DataFrame

data = {'value':value, 'group':group, 'cul':cul}
frame = DataFrame(data)
# print frame
import  json
anova_result = anova_lm(ols('value~C(group)', frame).fit())
# print anova_result
# print (anova_result.to_json())
#
# dict = {'rowKey':'1', 'codeID':'2', 'organID':'3', 'examID':'4'}
# print "dict--->", dict
# print "tuple -- >", tuple(dict)

formula = 'value~ group+cul'
# anova_results = anova_lm(ols(formula, frame).fit())
# anova_results = anova_lm(ols(formula, frame).fit())
# print(anova_results)

dframe = pd.read_excel('F:/6-2-3-15.xlsx')
# print dframe
frame = DataFrame(dframe)
# print frame
frame["block1"] = frame["block"].astype('category')
frame["treat1"] = frame["treat"].astype('category')

formula = 'value~ block1+treat1'


anova_results = anova_lm(ols(formula, frame).fit())
print anova_results
from statsmodels.stats.multicomp import (pairwise_tukeyhsd,
                                         MultiComparison)

multiComp = MultiComparison(frame["treat1"], frame["treat"])

print "=======================>"
# print(multiComp.tukeyhsd().summary())


# print pairwise_tukeyhsd(frame["treat"], frame["treat1"],  alpha=0.05)
# print pairwise_tukeyhsd(frame["block1"], frame["block"],  alpha=0.05)

from scipy.stats import chi2_contingency
# data = numpy.array([value, group])
# V, p, dof, expected = stats.chi2_contingency(data)
# print V
# print p
# print dof
# print expected
import  statsmodels.stats.multicomp
# print frame['a']
# print pd.crosstab(frame['a'],frame['b'])

# crossFD = pd.crosstab(frame['a'],frame['b'])

# print chi2_contingency([crossFD['a'],crossFD['b']],False)

# print chi2_contingency([frame['a'],frame['b']],True)
from scipy.stats import chisquare
# chisquare_value, race_pvalue = chisquare(frame['a'], frame['b'])
# print chisquare_value, race_pvalue

# print(chisquare(f_obs=frame['a'], f_exp=frame['b']))[1]
import scipy
from scipy.stats import chisquare

# print chisquare(frame['a'], f_exp=frame['b'])
#
#
# print chisquare(frame['a'], f_exp=frame['b'], ddof=1)
#
# print chisquare(frame['a'])

# print scipy.stats.chi2_contingency([frame['a'],frame['b'] ])
from statsmodels.sandbox.stats.multicomp import multipletests
print multipletests(frame["treat1"])

# print multipletests(frame["block1"])