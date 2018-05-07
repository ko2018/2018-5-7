package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchFileDto;
import com.talent.front.entity.BaseResearchFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档服务接口类
 */
public interface BaseResearchFileService extends BaseService<BaseResearchFile>{

	BaseResearchFileDto updateObjectByPrimaryKeySelective(BaseResearchFile baseResearchFile);

	PageResult<BaseResearchFileDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchFileDto> pageListCache(PageObject pageObject);
	
	int addResearchFile(BaseResearchFileDto baseResearchFileDto, String userId);
	
	BaseResearchFileDto deleteResearchFile(BaseResearchFile baseResearchFile);
	
	int downloadResearchFile(BaseResearchFile baseResearchFile, String userId);
}