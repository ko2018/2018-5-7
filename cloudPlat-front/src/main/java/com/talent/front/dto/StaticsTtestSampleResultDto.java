package com.talent.front.dto;

import com.talent.front.entity.StaticsTtestSampleResult;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验的结果Dto类
 */
public class StaticsTtestSampleResultDto extends StaticsTtestSampleResult {

	BaseSnlDto baseSnlDto;
	
	public BaseSnlDto getBaseSnlDto() {
		return baseSnlDto;
	}

	public void setBaseSnlDto(BaseSnlDto baseSnlDto) {
		this.baseSnlDto = baseSnlDto;
	}

	@Override
	public String toString() {
		return "StaticsTtestSampleResult ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.sSum=" + this.getSSum() + ", "
		 		+ "this.sMean=" + this.getSMean() + ", "
		 		+ "this.sStd=" + this.getSStd() + ", "
		 		+ "this.sStdDev=" + this.getSStdDev() + ", "
		 		+ "this.sT=" + this.getST() + ", "
		 		+ "this.sFreeDegree=" + this.getSFreeDegree() + ", "
		 		+ "this.sP=" + this.getSP() + ", "
		 		+ "this.sMeanDev=" + this.getSMeanDev() + ", "
		 		+ "this.sConfidenceLow=" + this.getSConfidenceLow() + ", "
		 		+ "this.sConfidenceUp=" + this.getSConfidenceUp() + ", "
		 		+ "this.sConfidence=" + this.getSConfidence() + ", "
		 		+ "this.sExpectVal=" + this.getSExpectVal() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}