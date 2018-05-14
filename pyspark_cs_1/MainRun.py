from t_test import SampleTtest, StudentTtest, DescribeAnalysis
import threading
import time

def serverHandler():
    while True :
        print "start"
        try:
            SampleTtest.service()
        except Exception as e:
            print e
       
        try:
            StudentTtest.service()
        except Exception as e:
            print e
        
        try:
            DescribeAnalysis.service()
        except Exception as e:
            print e
       
        print "end"
        time.sleep(2)
    return
       



if __name__ == "__main__":
    f1 = threading.Thread(target=serverHandler)
    f1.start()