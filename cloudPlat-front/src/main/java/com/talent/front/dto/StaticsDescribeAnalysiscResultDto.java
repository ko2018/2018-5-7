package com.talent.front.dto;

import java.util.Map;

import com.talent.front.dao.BaseSnlDao;
import com.talent.front.entity.StaticsDescribeAnalysiscResult;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析结果Dto类
 */
public class StaticsDescribeAnalysiscResultDto extends StaticsDescribeAnalysiscResult {

	private StaticsTtestIndGroupDto group;
	
	private Map<String, Object> dictMap;
	
	private BaseSnlDto baseSnlDto;
	

	public BaseSnlDto getBaseSnlDto() {
		return baseSnlDto;
	}


	public void setBaseSnlDto(BaseSnlDto baseSnlDto) {
		this.baseSnlDto = baseSnlDto;
	}


	public Map<String, Object> getDictMap() {
		return dictMap;
	}


	public void setDictMap(Map<String, Object> dictMap) {
		this.dictMap = dictMap;
	}


	public StaticsTtestIndGroupDto getGroup() {
		return group;
	}


	public void setGroup(StaticsTtestIndGroupDto group) {
		this.group = group;
	}


	@Override
	public String toString() {
		return "StaticsDescribeAnalysiscResult ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.uuid=" + this.getUuid() + ", "
		 		+ "this.dict=" + this.getDict() + ", "
		 		+ "this.dictVal=" + this.getDictVal() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}