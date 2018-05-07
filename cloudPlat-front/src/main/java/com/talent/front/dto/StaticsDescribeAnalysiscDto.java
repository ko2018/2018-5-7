package com.talent.front.dto;

import java.util.List;

import com.talent.front.entity.StaticsDescribeAnalysisc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析Dto类
 */
public class StaticsDescribeAnalysiscDto extends StaticsDescribeAnalysisc {
	private List<String> listSnlList;
	
	private StaticsTtestIndGroupDto group;
	
	private String sCrowdName;
	
	public String getsCrowdName() {
		return sCrowdName;
	}


	public void setsCrowdName(String sCrowdName) {
		this.sCrowdName = sCrowdName;
	}


	public List<String> getListSnlList() {
		return listSnlList;
	}


	public void setListSnlList(List<String> listSnlList) {
		this.listSnlList = listSnlList;
	}
	
	public StaticsTtestIndGroupDto getGroup() {
		return group;
	}


	public void setGroup(StaticsTtestIndGroupDto group) {
		this.group = group;
	}


	@Override
	public String toString() {
		return "StaticsDescribeAnalysisc ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.userId=" + this.getUserId() + ", "
		 		+ "this.crowdId=" + this.getCrowdId() + ", "
		 		+ "this.groupid=" + this.getGroupid() + ", "
		 		+ "this.dictList=" + this.getDictList() + ", "
		 		+ "this.type=" + this.getType() + ", "
		 		+ "this.optionList=" + this.getOptionList() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		 		+ "this.sort=" + this.getSort() + ", "
		+ "]";   
	}

}