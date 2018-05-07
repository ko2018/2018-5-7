package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseResearch;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研管理表服务接口类
 */
public interface BaseResearchService extends BaseService<BaseResearch>{

	BaseResearchDto updateObjectByPrimaryKeySelective(BaseResearch baseResearch);

	PageResult<BaseResearchDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchDto> pageListCache(PageObject pageObject);
	
	int addBaseResearchDto(BaseResearchDto baseResearchDto, SysUserDto user);
	
	BaseResearch updateBaseResearchDto(BaseResearchDto baseResearchDto);
	
	BaseResearchDto selectDtoByPrimaryKey(String researchId);

}