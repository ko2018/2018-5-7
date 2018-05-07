package com.talent.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseResearchStatus;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题状态表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "seq" })
public class BaseResearchStatusDto extends BaseResearchStatus {

    private static final long serialVersionUID = 765020485445732391L;
    
    private String lastStatus;
    
	public String getLastStatus()
    {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus)
    {
        this.lastStatus = lastStatus;
    }

    @Override
	public String toString() {
		return "BaseResearchStatus ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.researchId=" + this.getResearchId() + ", "
		 		+ "this.seq=" + this.getSeq() + ", "
		 		+ "this.status=" + this.getStatus() + ", "
		 		+ "this.operatorId=" + this.getOperatorId() + ", "
		 		+ "this.operateTime=" + this.getOperateTime() + ", "
		+ "]";   
	}

}