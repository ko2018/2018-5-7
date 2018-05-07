package com.talent.front.dto;

import com.talent.front.entity.BaseBioSample;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：生物样本表Dto类
 */
public class BaseBioSampleDto extends BaseBioSample {

	@Override
	public String toString() {
		return "BaseBioSample ["
		 		+ "this.sampleId=" + this.getSampleId() + ", "
		 		+ "this.classifyCode=" + this.getClassifyCode() + ", "
		 		+ "this.quantity=" + this.getQuantity() + ", "
		 		+ "this.stockTime=" + this.getStockTime() + ", "
		 		+ "this.stockPosition=" + this.getStockPosition() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}