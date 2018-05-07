package com.talent.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseResearchUser;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题用户表Dto类
 */
@JsonInclude(Include.NON_NULL)
public class BaseResearchUserDto extends BaseResearchUser {

    private static final long serialVersionUID = -983731980009277932L;

    @Override
	public String toString() {
		return "BaseResearchUser ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.researchId=" + this.getResearchId() + ", "
		 		+ "this.userId=" + this.getUserId() + ", "
		 		+ "this.isMajor=" + this.getIsMajor() + ", "
		+ "]";   
	}

}