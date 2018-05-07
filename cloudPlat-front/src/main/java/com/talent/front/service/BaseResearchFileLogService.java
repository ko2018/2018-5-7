package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchFileLogDto;
import com.talent.front.entity.BaseResearchFileLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档操作日志表服务接口类
 */
public interface BaseResearchFileLogService extends BaseService<BaseResearchFileLog>{

	BaseResearchFileLogDto updateObjectByPrimaryKeySelective(BaseResearchFileLog baseResearchFileLog);

	PageResult<BaseResearchFileLogDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchFileLogDto> pageListCache(PageObject pageObject);

}