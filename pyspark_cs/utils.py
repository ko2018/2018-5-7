#coding:utf-8
import sys 
reload(sys) 
sys.setdefaultencoding("utf-8")
import hashlib
from decimal import Decimal
def create_id(str_parm):
    m = hashlib.md5(str_parm.encode('utf-8'))
    return m.hexdigest()


def print_output(output):
    for (k, v) in output:
        print (k, v)
        return
      
    return

def print_output_1(output):
    for v in output:
        print v
        return
      
    return


import json
def to_json(sDict):
    return json.dumps(sDict,sort_keys=True,indent =4,separators=(',', ': '),encoding="gbk",ensure_ascii=True )

def get_Decimal(val):
    try:
        return str(Decimal(val).quantize(Decimal('0.0000')))
    except :
        print "error"
    return "-1" 

def get_Decimal_float(val):
    return (Decimal(val).quantize(Decimal('0.0000')))

def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        pass
 
    return False

def get_percent(dataset, per):
    per = per * 0.01
    data= (len(dataset)+1)*per
    j = int(data)
    g = data - int(data)
#     print 'j',j
#     print 'g',g
    if j >= len(dataset) :
        return "-1"
    
    if j == 0:
        return dataset[j]
    return (1-g)*dataset[j-1] + g*dataset[j+1-1]

def floatrange(start, stop, steps):
    return [start+float(i)*(stop-start)/(float(steps)-1) for i in range(steps)]

def option_dict_list(optionList):
    print "optionList-" ,optionList
    colsArrs = optionList.split(",")
    dict = {}
    list = []
    for cols in colsArrs :
        print "cols, " ,cols
        vals = cols.split(":")
        print "vals, " ,vals
        dict[vals[0]] = vals[1]
        list.append(vals[0])
    return dict, list

