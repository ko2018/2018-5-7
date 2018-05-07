package com.talent.front.dto;

import com.talent.front.entity.StaticsTtestSample;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验Dto类
 */
public class StaticsTtestSampleDto extends StaticsTtestSample {

	BaseSnlDto baseSnlDto;

	public BaseSnlDto getBaseSnlDto() {
		return baseSnlDto;
	}

	public void setBaseSnlDto(BaseSnlDto baseSnlDto) {
		this.baseSnlDto = baseSnlDto;
	}

	@Override
	public String toString() {
		return "StaticsTtestSample [" + "this.id=" + this.getId() + ", " + "this.sCrowd=" + this.getSCrowd() + ", "
				+ "this.sConfidence=" + this.getSConfidence() + ", " + "this.sExpectVal=" + this.getSExpectVal() + ", "
				+ "this.sDicId=" + this.getSDicId() + ", " + "this.addTime=" + this.getAddTime() + ", "
				+ "this.updateTime=" + this.getUpdateTime() + ", " + "this.deleteStatus=" + this.getDeleteStatus()
				+ ", " + "]";
	}

}