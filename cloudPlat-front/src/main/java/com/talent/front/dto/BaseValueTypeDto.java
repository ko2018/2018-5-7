package com.talent.front.dto;

import com.talent.front.entity.BaseValueType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：数据类型表Dto类
 */
public class BaseValueTypeDto extends BaseValueType {

	@Override
	public String toString() {
		return "BaseValueType ["
		 		+ "this.valuetypeId=" + this.getValuetypeId() + ", "
		 		+ "this.valuetypeName=" + this.getValuetypeName() + ", "
		 		+ "this.sequence=" + this.getSequence() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}