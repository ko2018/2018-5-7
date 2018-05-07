package com.talent.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseSnlClassify;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：标准类别表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "creator", "updateUser", "addTime", "updateTime" })
public class BaseSnlClassifyDto extends BaseSnlClassify {

    private static final long serialVersionUID = 2319638887992449112L;

    @Override
	public String toString() {
		return "BaseSnlClassify ["
		 		+ "this.classifyNo=" + this.getClassifyNo() + ", "
		 		+ "this.snlType=" + this.getSnlType() + ", "
		 		+ "this.nameCn=" + this.getNameCn() + ", "
		 		+ "this.nameUs=" + this.getNameUs() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}