package com.talent.front.dto;

import com.talent.front.entity.StaticsTtestIndGroup;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-01 <br/>
 * 描述：独立T检验分组Dto类
 */
public class StaticsTtestIndGroupDto extends StaticsTtestIndGroup {
	
	private BaseSnlDto baseSnlDto;
	
	public BaseSnlDto getBaseSnlDto() {
		return baseSnlDto;
	}

	public void setBaseSnlDto(BaseSnlDto baseSnlDto) {
		this.baseSnlDto = baseSnlDto;
	}

	@Override
	public String toString() {
		return "StaticsTtestIndGroup ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.userId=" + this.getUserId() + ", "
		 		+ "this.gUnitName=" + this.getGUnitName() + ", "
		 		+ "this.gUnitParm=" + this.getGUnitParm() + ", "
		 		+ "this.gUnitDict=" + this.getGUnitDict() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		+ "]";   
	}

}