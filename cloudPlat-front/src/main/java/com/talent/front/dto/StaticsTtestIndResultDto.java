package com.talent.front.dto;

import com.talent.front.entity.StaticsTtestIndResult;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-03 <br/>
 * 描述： 独立T检验结果Dto类
 */
public class StaticsTtestIndResultDto extends StaticsTtestIndResult {

	private BaseSnlDto baseSnlDto;
	
	private StaticsTtestIndGroupDto sgroup1;
	private StaticsTtestIndGroupDto sgroup2;
	
	public BaseSnlDto getBaseSnlDto() {
		return baseSnlDto;
	}

	

	public StaticsTtestIndGroupDto getSgroup1() {
		return sgroup1;
	}



	public void setSgroup1(StaticsTtestIndGroupDto sgroup1) {
		this.sgroup1 = sgroup1;
	}



	public StaticsTtestIndGroupDto getSgroup2() {
		return sgroup2;
	}



	public void setSgroup2(StaticsTtestIndGroupDto sgroup2) {
		this.sgroup2 = sgroup2;
	}



	public void setBaseSnlDto(BaseSnlDto baseSnlDto) {
		this.baseSnlDto = baseSnlDto;
	}


	@Override
	public String toString() {
		return "StaticsTtestIndResult ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.sUuid=" + this.getSUuid() + ", "
		 		+ "this.sDict=" + this.getSDict() + ", "
		 		+ "this.sGroup1Sum=" + this.getSGroup1Sum() + ", "
		 		+ "this.sGroup2Sum=" + this.getSGroup2Sum() + ", "
		 		+ "this.sGroup1Mean=" + this.getSGroup1Mean() + ", "
		 		+ "this.sGroup2Mean=" + this.getSGroup2Mean() + ", "
		 		+ "this.sGroup1Std=" + this.getSGroup1Std() + ", "
		 		+ "this.sGroup1StdError=" + this.getSGroup1StdError() + ", "
		 		+ "this.sGroup2Std=" + this.getSGroup2Std() + ", "
		 		+ "this.sGroup2StdError=" + this.getSGroup2StdError() + ", "
		 		+ "this.sF=" + this.getSF() + ", "
		 		+ "this.sSig=" + this.getSSig() + ", "
		 		+ "this.sEqualT=" + this.getSEqualT() + ", "
		 		+ "this.sUnequalT=" + this.getSUnequalT() + ", "
		 		+ "this.sEqualFreeDegree=" + this.getSEqualFreeDegree() + ", "
		 		+ "this.sUnequalFreeDegree=" + this.getSUnequalFreeDegree() + ", "
		 		+ "this.sEqualSig=" + this.getSEqualSig() + ", "
		 		+ "this.sUnequalSig=" + this.getSUnequalSig() + ", "
		 		+ "this.sEqualMean=" + this.getSEqualMean() + ", "
		 		+ "this.sUnequalMean=" + this.getSUnequalMean() + ", "
		 		+ "this.sEqualStdError=" + this.getSEqualStdError() + ", "
		 		+ "this.sUnequalStdError=" + this.getSUnequalStdError() + ", "
		 		+ "this.sEqualConfidenceLow=" + this.getSEqualConfidenceLow() + ", "
		 		+ "this.sEqualConfidenceUp=" + this.getSEqualConfidenceUp() + ", "
		 		+ "this.sUnequalConfidenceLow=" + this.getSUnequalConfidenceLow() + ", "
		 		+ "this.sUnequalConfidenceUp=" + this.getSUnequalConfidenceUp() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}