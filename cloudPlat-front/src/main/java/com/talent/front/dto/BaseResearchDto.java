package com.talent.front.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseResearch;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研管理表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "isDel" })
public class BaseResearchDto extends BaseResearch {

    private static final long serialVersionUID = -2367040982150716384L;

    private List<String> memberUserId = new ArrayList();
    
    private List<String> memberUserName = new ArrayList();
    
    private List<String> memberInsId = new ArrayList();
    
    private List<String> memberInsName = new ArrayList();
    
    public List<String> getMemberUserId()
    {
        return memberUserId;
    }

    public void setMemberUserId(List<String> memberUserId)
    {
        this.memberUserId = memberUserId;
    }


    public List<String> getMemberUserName()
    {
        return memberUserName;
    }


    public void setMemberUserName(List<String> memberUserName)
    {
        this.memberUserName = memberUserName;
    }

    public List<String> getMemberInsId()
    {
        return memberInsId;
    }


    public void setMemberInsId(List<String> memberInsId)
    {
        this.memberInsId = memberInsId;
    }


    public List<String> getMemberInsName()
    {
        return memberInsName;
    }


    public void setMemberInsName(List<String> memberInsName)
    {
        this.memberInsName = memberInsName;
    }


    @Override
	public String toString() {
		return "BaseResearch ["
		 		+ "this.researchId=" + this.getResearchId() + ", "
		 		+ "this.researchName=" + this.getResearchName() + ", "
		 		+ "this.status=" + this.getStatus() + ", "
		 		+ "this.briefInfo=" + this.getBriefInfo() + ", "
		 		+ "this.isDel=" + this.getIsDel() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.beginTime=" + this.getBeginTime() + ", "
		 		+ "this.endTime=" + this.getEndTime() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}