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
import com.talent.front.dao.StaticsCustomCrowdDao;
import com.talent.front.dao.StaticsTtestSampleDao;
import com.talent.front.dto.StaticsCustomCrowdDto;
import com.talent.front.dto.StaticsTtestSampleDto;
import com.talent.front.entity.StaticsTtestSample;
import com.talent.front.service.StaticsTtestSampleService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验服务实现类
 */
@Service
public class StaticsTtestSampleServiceImpl extends BaseServiceImpl<StaticsTtestSample> implements StaticsTtestSampleService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestSampleServiceImpl.class);

	@Autowired
	private StaticsTtestSampleDao staticsTtestSampleDao;

	@Override
	public BaseDao<StaticsTtestSample> getBaseDao() {
		return this.staticsTtestSampleDao;
	}
	
	@CacheSpeObject(value = "staticsTtestSample", key = "#id")
	@Override
	public StaticsTtestSampleDto selectDtoByPrimaryKey(String id) {
		return this.staticsTtestSampleDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "staticsTtestSample", key = "#staticsTtestSample.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsTtestSampleDto updateDtoByPrimaryKeySelective(StaticsTtestSample staticsTtestSample) {
		this.staticsTtestSampleDao.updateByPrimaryKeySelective(staticsTtestSample);
		return this.staticsTtestSampleDao.selectDtoByPrimaryKey(staticsTtestSample.getId());
	}
	
	
	@Autowired
	private StaticsCustomCrowdDao staticsCustomCrowdDao;
	
	@Override
	public PageResult<StaticsTtestSampleDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsTtestSampleDao.pageCountDto(pageObject);
		PageResult<StaticsTtestSampleDto> pageResult = new PageResult<StaticsTtestSampleDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestSampleDto> staticsTtestSampleDtoList = ((StaticsTtestSampleServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			for(StaticsTtestSampleDto dto : staticsTtestSampleDtoList) {
				if(dto.getSCrowd() != null) {
					StaticsCustomCrowdDto crowdDto = staticsCustomCrowdDao.selectDtoByPrimaryKey(dto.getSCrowd() );
					if(crowdDto != null) {
						dto.setsCrowdName(crowdDto.getStaticsPName());
					}
				}
			}
			pageResult.setQueryResult(staticsTtestSampleDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<StaticsTtestSampleDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsTtestSampleDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsTtestSampleDto> pageResult = new PageResult<StaticsTtestSampleDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestSampleDto> staticsTtestSampleDtoList = this.staticsTtestSampleDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsTtestSampleDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsTtestSampleDto> pageListCache(PageObject pageObject) {
		return this.staticsTtestSampleDao.pageListDto(pageObject);
	}

}