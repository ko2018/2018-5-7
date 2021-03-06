package com.talent.mode;

import java.io.Serializable;

public class BaseCrowdES implements Serializable {

    private static final long serialVersionUID = 1L;

    // std表唯一id
    private String stdId;

    // 人群列表
    private String crowdList;

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getCrowdList() {
        return crowdList;
    }

    public void setCrowdList(String crowdList) {
        this.crowdList = crowdList;
    }

}
