package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.SysResourceDto;
import com.talent.front.entity.SysResource;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-01 <br/>
 * 描述：系统资源服务接口类
 */
public interface SysResourceService extends BaseService<SysResource>{

	SysResourceDto updateObjectByPrimaryKeySelective(SysResource sysResource);

	PageResult<SysResourceDto> pageListDto(PageObject pageObject);
	
	List<SysResourceDto> pageListCache(PageObject pageObject);

}