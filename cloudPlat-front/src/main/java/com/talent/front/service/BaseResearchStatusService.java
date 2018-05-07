package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchStatusDto;
import com.talent.front.entity.BaseResearchStatus;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题状态表服务接口类
 */
public interface BaseResearchStatusService extends BaseService<BaseResearchStatus>{

	BaseResearchStatusDto updateObjectByPrimaryKeySelective(BaseResearchStatus baseResearchStatus);

	PageResult<BaseResearchStatusDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchStatusDto> pageListCache(PageObject pageObject);
	
	Integer getResearchCurrentSeq(String researchId);
	
	Integer addBaseResearchStatus(BaseResearchStatusDto baseResearchStatusDto);
}