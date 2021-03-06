package com.talent.job.serial;

import java.io.Serializable;

// 统计临时对象
public class RowKey implements Serializable {

    private static final long serialVersionUID = 1L;

    public String userCode;

    public long caseR1;

    public long caseP1;

    public long caseR2;

    public long caseP2;

    public long caseR3;

    public long caseP3;

    // 标记是否满足结局逻辑关系
    public boolean flag1 = false;

    // 是否属于该人群中
    public boolean inCrowd = false;

    public String tempVal;

    public int endIndex;

    public int sum;

    public void clear() {
        caseR1 = 0l;
        caseP1 = 0l;
        caseR2 = 0l;
        caseP2 = 0l;
        flag1 = false;
        inCrowd = false;
        tempVal = null;
    }

}
