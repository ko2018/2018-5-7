package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchFileClassifyDto;
import com.talent.front.entity.BaseResearchFileClassify;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档类型服务接口类
 */
public interface BaseResearchFileClassifyService extends BaseService<BaseResearchFileClassify>{

	BaseResearchFileClassifyDto updateObjectByPrimaryKeySelective(BaseResearchFileClassify baseResearchFileClassify);

	PageResult<BaseResearchFileClassifyDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchFileClassifyDto> pageListCache(PageObject pageObject);

}