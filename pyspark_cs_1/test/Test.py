#utf-8
from scipy import stats
import pandas as pd
dframe = pd.read_excel('F:/6-2-3-15.xlsx')
from pandas import Series, DataFrame
# print dframe
frame = DataFrame(dframe)
# print frame
frame["a1"] = frame["a"].astype('category')
frame["b1"] = frame["b"].astype('category')
frame["x1"] = frame["x"].astype('category')
# print frame

print "================wwww44444======="
mask2 = (frame["a1"] == 2)
mask3 = (frame["a1"] == 3)
df2 = frame[mask2]
df3 = frame[mask3]
# print df2
# print df2

mask23 = (frame["a1"] != 1)
print "========mask23=============="
# print frame[mask23]
print "======================"

# print df3
# print df3

import statsmodels.stats.multitest as multitest

# print frame["a1"]
reject_, pvals_corrected_, alphacSidak, alphacBonf = multitest.multipletests(df2["x1"], alpha=0.05, method='bonferroni')

print "pvals_corrected_", pvals_corrected_
print "alphacSidak", alphacSidak
print "alphacBonf", alphacBonf
# print "a1", alphacSidak, alphacBonf
from statsmodels.stats.multicomp import (pairwise_tukeyhsd,
                                         MultiComparison)

multiComp = MultiComparison(frame['x1'], frame['a1'])
# print(multiComp.tukeyhsd().summary())

# print pairwise_tukeyhsd( frame['x1'], frame['a1'], alpha=0.05)

tukey=multiComp.tukeyhsd()

# print "q_crit", tukey.q_crit
# results = pairwise_tukeyhsd( frame['x'], frame['a'], alpha=0.05)
res = pairwise_tukeyhsd( frame[mask23]['x1'], frame[mask23]["a1"], alpha=0.05)
print res


# print "meandiffs", res.meandiffs

# from statsmodels.stats.libqsturng import psturng
# print psturng(np.abs(res.meandiffs / res.std_pairs), len(res.groupsunique), res.df_total)


# group2 = np.array(frame[mask2]['x1'])
# group3 = np.array(frame[mask3]['x1'])
# list_groups = [group2, group3]
# list_total = group2 + group3
#
# print "list_groups", list_groups
# print "list_total", list_total
# print "result--------------------------------------------------"

# f_score=MuttpleExam.F(list_groups,list_total)
# print"F score:",f_score

# df1=len(list_groups)-1
#
# df2=len(list_total)-1*len(list_groups)
# #
# probability=f.sf(f_score,df1,df2)
#
# print probability



