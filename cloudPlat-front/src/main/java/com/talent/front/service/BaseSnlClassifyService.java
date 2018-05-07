package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseSnlClassifyDto;
import com.talent.front.entity.BaseSnlClassify;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：标准类别表服务接口类
 */
public interface BaseSnlClassifyService extends BaseService<BaseSnlClassify>{

	BaseSnlClassifyDto updateObjectByPrimaryKeySelective(BaseSnlClassify baseSnlClassify);

	PageResult<BaseSnlClassifyDto> pageListDto(PageObject pageObject);
	
	List<BaseSnlClassifyDto> pageListCache(PageObject pageObject);

}