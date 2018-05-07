package com.talent.front.dto;

import com.talent.front.entity.StaticsPlatformBase;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-15 <br/>
 * 描述：统计首页平台对应表Dto类
 */
public class StaticsPlatformBaseDto extends StaticsPlatformBase {
	private String staticsAreaId;
	private AreaDto areaDto;
	
	public String getStaticsAreaId() {
		return staticsAreaId;
	}


	public void setStaticsAreaId(String staticsAreaId) {
		this.staticsAreaId = staticsAreaId;
	}

	public AreaDto getAreaDto() {
		return areaDto;
	}


	public void setAreaDto(AreaDto areaDto) {
		this.areaDto = areaDto;
	}


	@Override
	public String toString() {
		return "StaticsPlatformBase ["
		 		+ "this.staticsId=" + this.getStaticsId() + ", "
		 		+ "this.staticsRecords=" + this.getStaticsRecords() + ", "
		 		+ "this.staticsPeople=" + this.getStaticsPeople() + ", "
		 		+ "this.staticsOrgans=" + this.getStaticsOrgans() + ", "
		 		+ "this.staticsHasDisease=" + this.getStaticsHasDisease() + ", "

		+ "]";   
	}

}