# coding=UTF-8
import threading
import mysql_connect
import time
import utils
import PairTtest
import AnovaOneWay
def save_result_db(inputID, result):
    conn = None
    cursor = None
    try:
        conn = mysql_connect.connect()
      
        sql = mysql_connect.update_math_in_out_sql_result()
        conn.autocommit = True
        cursor = conn.cursor()
        
        cursor.execute(sql, (result,
                             time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time())),
                             inputID)
                             )
        conn.commit()
      
    except:
        import traceback
        traceback.print_exc()
        if conn :
            conn.rollback()
    
    finally:
        if cursor:
            cursor.close()
        if conn :
            conn.close()
    return 


def get_math_input_list():
    list_ask_queue = []
    cursor = None
    conn = None
    try:
        conn = mysql_connect.connect()
        sql = mysql_connect.get_math_in_out_sql_list()
        conn.autocommit = True
        cursor = conn.cursor()
        cursor.execute(sql)
        if cursor : 
            for row in cursor:
                list_ask_queue.append(print_math_in_DB(row))
            
    except:
        import traceback
        traceback.print_exc()
        if conn :
            conn.rollback()
    
    finally:
        if cursor : 
            cursor.close()
        if conn :
            conn.close()
    return list_ask_queue


# uuId
# s_crowd as crowd
# s_data_in as dataIn
# s_type as sType
# s_type_func as sTypeFunc
def print_math_in_DB(row):
    uuId = row[0]
    crowID = row[1]
    in_json = row[2]
    sType = row[3]
    sTypeFunc = row[4]
    print "in_json", in_json
    return  {'uuId':uuId, 'crowID':crowID, 'in_json':in_json, 's_type':sType, 's_type_func':sTypeFunc}

import json
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

STATICS_TTEST = "2"
STATICS_TTEST_PAIR = "3"
STATICS_ANOVA = "3"
STATICS_ANOVA_ONE_WAY = "1"
STATICS_ANOVA_TWO_WAY = "2"
STATICS_ANOVA_UNIANOVA = "3"

def serverHandler():
    list_ask_queue = get_math_input_list()
    print "static_math, ", len(list_ask_queue)
    if list_ask_queue.__len__() <= 0:
        return
    result = None
    for ask_parm in list_ask_queue:
        # ask_parm['s_type'] = "3"
        # ask_parm['s_type_func'] = "1"
        print "s_type->", ask_parm['s_type'], ",  s_type_func->", ask_parm['s_type_func']
        json_data = eval(ask_parm['in_json'])
        ask_parm['crowID'] = "1"
        if ask_parm['s_type'] == STATICS_TTEST:

            if ask_parm['s_type_func'] == STATICS_TTEST_PAIR :
                print "ask_parm=", ask_parm
                # in_json = json.dumps(ask_parm['in_json'],ensure_ascii=False)
                # json_data = eval(ask_parm['in_json'])
                # ask_parm['crowID'] = "1"
                result = PairTtest.calc_python_spark(json_data, ask_parm['crowID'])
        elif ask_parm['s_type'] == STATICS_ANOVA :
            if ask_parm['s_type_func'] == STATICS_ANOVA_ONE_WAY:
                result = AnovaOneWay.calc_python_spark(json_data, ask_parm['crowID'])
            # elif ask_parm['s_type_func'] == STATICS_ANOVA_TWO_WAY:
            #
            # elif ask_parm['s_type_func'] == STATICS_ANOVA_UNIANOVA:
                print "22"
            else :
                print "ask_parm=", ask_parm
                return
        else :
            print "ask_parm is not found !!!->", ask_parm
        if result is not None :
            save_result_db(ask_parm["uuId"], result)  

    return
  
if __name__ == "__main__":
    f1 = threading.Thread(target=serverHandler)
    f1.start()