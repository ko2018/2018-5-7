package com.talent.front.dto;

import com.talent.front.entity.BaseResRatio;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源占比Dto类
 */
public class BaseResRatioDto extends BaseResRatio {

	@Override
	public String toString() {
		return "BaseResRatio ["
		 		+ "this.resratioId=" + this.getResratioId() + ", "
		 		+ "this.resratioName=" + this.getResratioName() + ", "
		 		+ "this.resratioValue=" + this.getResratioValue() + ", "
		 		+ "this.resratioTypeid=" + this.getResratioTypeid() + ", "
		 		+ "this.resratioTypename=" + this.getResratioTypename() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}