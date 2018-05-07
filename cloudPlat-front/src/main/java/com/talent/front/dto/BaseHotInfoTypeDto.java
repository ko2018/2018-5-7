package com.talent.front.dto;

import com.talent.front.entity.BaseHotInfoType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息类型表Dto类
 */
public class BaseHotInfoTypeDto extends BaseHotInfoType {

	@Override
	public String toString() {
		return "BaseHotInfoType ["
		 		+ "this.infoTypeId=" + this.getInfoTypeId() + ", "
		 		+ "this.infoTypeName=" + this.getInfoTypeName() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}