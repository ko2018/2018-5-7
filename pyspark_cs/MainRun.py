import StudentTtest 
import DescribeAnalysis
import SampleTtest
import threading
import time

def serverHandler():
    while True :
        print "start"
        try:
            # 单样本T检验
            SampleTtest.service()
        except Exception as e:
            print e
       
        try:
            # 独立样本T检验
            StudentTtest.service()
        except Exception as e:
            print e
        
        try:
            # 描述性分析
            DescribeAnalysis.service()
        except Exception as e:
            print e
       
        print "end"
        time.sleep(2)
    return
       



if __name__ == "__main__":
    f1 = threading.Thread(target=serverHandler)
    f1.start()