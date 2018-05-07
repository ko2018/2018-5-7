package com.talent.front.dto;

import com.talent.front.entity.BaseSnlRuleValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：术语值规则Dto类
 */
public class BaseSnlRuleValueDto extends BaseSnlRuleValue {

	@Override
	public String toString() {
		return "BaseSnlRuleValue ["
		 		+ "this.valueId=" + this.getValueId() + ", "
		 		+ "this.snlId=" + this.getSnlId() + ", "
		 		+ "this.snlCode=" + this.getSnlCode() + ", "
		 		+ "this.dictId=" + this.getDictId() + ", "
		 		+ "this.isLegitimate=" + this.getIsLegitimate() + ", "
		 		+ "this.valuetypeId=" + this.getValuetypeId() + ", "
		 		+ "this.valuetypeName=" + this.getValuetypeName() + ", "
		 		+ "this.lowerLimit=" + this.getLowerLimit() + ", "
		 		+ "this.upperLimit=" + this.getUpperLimit() + ", "
		 		+ "this.keyWord=" + this.getKeyWord() + ", "
		 		+ "this.arithmeticLogic=" + this.getArithmeticLogic() + ", "
		 		+ "this.arithmeticNum=" + this.getArithmeticNum() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}