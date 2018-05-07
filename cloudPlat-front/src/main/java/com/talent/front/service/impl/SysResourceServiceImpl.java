package com.talent.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.front.util.redis.annotation.CacheSpeObject;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.SysResourceDao;
import com.talent.front.dto.SysResourceDto;
import com.talent.front.entity.SysResource;
import com.talent.front.service.SysResourceService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-01 <br/>
 * 描述：系统资源服务实现类
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {
	private static final Logger logger = LoggerFactory.getLogger(SysResourceServiceImpl.class);

	@Autowired
	private SysResourceDao sysResourceDao;

	@Override
	public BaseDao<SysResource> getBaseDao() {
		return this.sysResourceDao;
	}
	
	@CacheSpeObject(value = "sysResource", key = "#sysResource.resourceId")
	@Override
	public SysResourceDto updateObjectByPrimaryKeySelective(SysResource sysResource) {
		this.sysResourceDao.updateByPrimaryKeySelective(sysResource);
		return this.sysResourceDao.selectDtoByPrimaryKey(sysResource.getResourceId());
	}
	
	@Override
	public PageResult<SysResourceDto> pageListDto(PageObject pageObject) {
		long totalCount = this.sysResourceDao.pageCount(pageObject);
		PageResult<SysResourceDto> pageResult = new PageResult<SysResourceDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<SysResourceDto> sysResourceDtoList = ((SysResourceServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(sysResourceDtoList);
		}
		return pageResult;
	}
	
	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<SysResourceDto> pageListCache(PageObject pageObject) {
		return this.sysResourceDao.pageListDto(pageObject);
	}

}