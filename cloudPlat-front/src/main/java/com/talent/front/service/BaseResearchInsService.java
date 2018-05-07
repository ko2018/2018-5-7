package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchInsDto;
import com.talent.front.entity.BaseResearchIns;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题机构关系表服务接口类
 */
public interface BaseResearchInsService extends BaseService<BaseResearchIns>{

	BaseResearchInsDto updateObjectByPrimaryKeySelective(BaseResearchIns baseResearchIns);

	PageResult<BaseResearchInsDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchInsDto> pageListCache(PageObject pageObject);

}