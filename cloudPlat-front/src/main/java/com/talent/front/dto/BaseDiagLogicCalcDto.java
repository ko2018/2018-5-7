package com.talent.front.dto;

import com.talent.front.entity.BaseDiagLogicCalc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：逻辑运算关系Dto类
 */
public class BaseDiagLogicCalcDto extends BaseDiagLogicCalc {

	@Override
	public String toString() {
		return "BaseDiagLogicCalc ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.diagLogicId=" + this.getDiagLogicId() + ", "
		 		+ "this.calcId=" + this.getCalcId() + ", "
		+ "]";   
	}

}