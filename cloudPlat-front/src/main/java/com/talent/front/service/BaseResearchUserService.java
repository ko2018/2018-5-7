package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchUserDto;
import com.talent.front.entity.BaseResearchUser;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题用户表服务接口类
 */
public interface BaseResearchUserService extends BaseService<BaseResearchUser>{

	BaseResearchUserDto updateObjectByPrimaryKeySelective(BaseResearchUser baseResearchUser);

	PageResult<BaseResearchUserDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchUserDto> pageListCache(PageObject pageObject);

}