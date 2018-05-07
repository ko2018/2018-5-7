package com.talent.front.dto;

import com.talent.front.entity.Area;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：省市区Dto类
 */
public class AreaDto extends Area {
	private AreaDto parentAreaDto;

	public AreaDto getParentAreaDto() {
		return parentAreaDto;
	}

	public void setParentAreaDto(AreaDto parentAreaDto) {
		this.parentAreaDto = parentAreaDto;
	}

	@Override
	public String toString() {
		return "Area [" + "this.areaId=" + this.getAreaId() + ", " + "this.areaName=" + this.getAreaName() + ", "
				+ "this.deleteStatus=" + this.getDeleteStatus() + ", " + "this.level=" + this.getLevel() + ", "
				+ "this.sequence=" + this.getSequence() + ", " + "this.parentId=" + this.getParentId() + ", "
				+ "this.common=" + this.getCommon() + ", " + "this.addTime=" + this.getAddTime() + ", " + "]";
	}

}