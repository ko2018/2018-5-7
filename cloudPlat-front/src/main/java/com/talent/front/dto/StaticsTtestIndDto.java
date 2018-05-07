package com.talent.front.dto;

import java.util.List;

import com.talent.front.entity.StaticsTtestInd;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-03 <br/>
 * 描述： 独立T检验Dto类
 */
public class StaticsTtestIndDto extends StaticsTtestInd {

	private List<String> listSnlList;
	
	private StaticsTtestIndGroupDto group1;
	
	private StaticsTtestIndGroupDto group2;



	public List<String> getListSnlList() {
		return listSnlList;
	}


	public void setListSnlList(List<String> listSnlList) {
		this.listSnlList = listSnlList;
	}


	public StaticsTtestIndGroupDto getGroup1() {
		return group1;
	}


	public void setGroup1(StaticsTtestIndGroupDto group1) {
		this.group1 = group1;
	}


	public StaticsTtestIndGroupDto getGroup2() {
		return group2;
	}


	public void setGroup2(StaticsTtestIndGroupDto group2) {
		this.group2 = group2;
	}


	@Override
	public String toString() {
		return "StaticsTtestInd ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.sGroupId2=" + this.getSGroupId2() + ", "
		 		+ "this.sGroupId1=" + this.getSGroupId1() + ", "
		 		+ "this.sConfidence=" + this.getSConfidence() + ", "
		 		+ "this.sDictList=" + this.getSDictList() + ", "
		 		+ "this.sCrowd=" + this.getSCrowd() + ", "
		 		+ "this.userId=" + this.getUserId() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		 		+ "this.sExpectFlag=" + this.getSExpectFlag() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		+ "]";   
	}

}