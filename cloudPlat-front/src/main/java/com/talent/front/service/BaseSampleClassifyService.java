package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseSampleClassifyDto;
import com.talent.front.entity.BaseSampleClassify;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：样本类型表服务接口类
 */
public interface BaseSampleClassifyService extends BaseService<BaseSampleClassify>{

	BaseSampleClassifyDto updateObjectByPrimaryKeySelective(BaseSampleClassify baseSampleClassify);

	PageResult<BaseSampleClassifyDto> pageListDto(PageObject pageObject);
	
	List<BaseSampleClassifyDto> pageListCache(PageObject pageObject);

}