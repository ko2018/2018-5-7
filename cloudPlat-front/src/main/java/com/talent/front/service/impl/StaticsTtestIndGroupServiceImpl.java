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
import com.talent.front.dao.StaticsTtestIndGroupDao;
import com.talent.front.dto.StaticsTtestIndGroupDto;
import com.talent.front.entity.StaticsTtestIndGroup;
import com.talent.front.service.StaticsTtestIndGroupService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：独立T检验分组服务实现类
 */
@Service
public class StaticsTtestIndGroupServiceImpl extends BaseServiceImpl<StaticsTtestIndGroup> implements StaticsTtestIndGroupService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestIndGroupServiceImpl.class);

	@Autowired
	private StaticsTtestIndGroupDao staticsTtestIndGroupDao;

	@Override
	public BaseDao<StaticsTtestIndGroup> getBaseDao() {
		return this.staticsTtestIndGroupDao;
	}
	

	@Override
	public StaticsTtestIndGroupDto selectDtoByPrimaryKey(String id) {
		return this.staticsTtestIndGroupDao.selectDtoByPrimaryKey(id);
	}
	
	
	@Override
	public StaticsTtestIndGroupDto updateDtoByPrimaryKeySelective(StaticsTtestIndGroup staticsTtestIndGroup) {
		this.staticsTtestIndGroupDao.updateByPrimaryKeySelective(staticsTtestIndGroup);
		return this.staticsTtestIndGroupDao.selectDtoByPrimaryKey(staticsTtestIndGroup.getId());
	}
	
	@Override
	public PageResult<StaticsTtestIndGroupDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsTtestIndGroupDao.pageCountDto(pageObject);
		PageResult<StaticsTtestIndGroupDto> pageResult = new PageResult<StaticsTtestIndGroupDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestIndGroupDto> staticsTtestIndGroupDtoList = ((StaticsTtestIndGroupServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(staticsTtestIndGroupDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<StaticsTtestIndGroupDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsTtestIndGroupDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsTtestIndGroupDto> pageResult = new PageResult<StaticsTtestIndGroupDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestIndGroupDto> staticsTtestIndGroupDtoList = this.staticsTtestIndGroupDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsTtestIndGroupDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsTtestIndGroupDto> pageListCache(PageObject pageObject) {
		return this.staticsTtestIndGroupDao.pageListDto(pageObject);
	}

}