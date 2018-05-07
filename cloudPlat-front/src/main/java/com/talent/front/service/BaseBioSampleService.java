package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseBioSampleDto;
import com.talent.front.entity.BaseBioSample;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：生物样本表服务接口类
 */
public interface BaseBioSampleService extends BaseService<BaseBioSample>{

	BaseBioSampleDto updateObjectByPrimaryKeySelective(BaseBioSample baseBioSample);

	PageResult<BaseBioSampleDto> pageListDto(PageObject pageObject);
	
	List<BaseBioSampleDto> pageListCache(PageObject pageObject);

}