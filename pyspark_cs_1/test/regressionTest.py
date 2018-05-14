#utf-8
from scipy.stats.stats import pearsonr
from scipy.stats.stats import spearmanr
from scipy.stats.stats import kendalltau
x = [1,2,3,4,5,6,7,8,9,2]
y = [1,12,3,4,5,6,17,8,9,10]
print pearsonr(x, y)

print spearmanr(x, y)

print kendalltau(x, y)



