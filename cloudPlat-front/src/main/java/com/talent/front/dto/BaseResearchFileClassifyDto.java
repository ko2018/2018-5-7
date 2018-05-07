package com.talent.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseResearchFileClassify;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档类型Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "creator", "updateUser", "addTime", "updateTime" })
public class BaseResearchFileClassifyDto extends BaseResearchFileClassify {

    private static final long serialVersionUID = 3879227310102918261L;

    @Override
	public String toString() {
		return "BaseResearchFileClassify ["
		 		+ "this.classifyId=" + this.getClassifyId() + ", "
		 		+ "this.classifyName=" + this.getClassifyName() + ", "
		 		+ "this.briefInfo=" + this.getBriefInfo() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}