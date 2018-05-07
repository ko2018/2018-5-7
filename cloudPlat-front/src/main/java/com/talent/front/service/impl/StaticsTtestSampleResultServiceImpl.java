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
import com.talent.front.dao.StaticsTtestSampleResultDao;
import com.talent.front.dto.StaticsTtestSampleResultDto;
import com.talent.front.entity.StaticsTtestSampleResult;
import com.talent.front.service.StaticsTtestSampleResultService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验的结果服务实现类
 */
@Service
public class StaticsTtestSampleResultServiceImpl extends BaseServiceImpl<StaticsTtestSampleResult> implements StaticsTtestSampleResultService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestSampleResultServiceImpl.class);

	@Autowired
	private StaticsTtestSampleResultDao staticsTtestSampleResultDao;

	@Override
	public BaseDao<StaticsTtestSampleResult> getBaseDao() {
		return this.staticsTtestSampleResultDao;
	}
	
	@CacheSpeObject(value = "staticsTtestSampleResult", key = "#id")
	@Override
	public StaticsTtestSampleResultDto selectDtoByPrimaryKey(String id) {
		return this.staticsTtestSampleResultDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "staticsTtestSampleResult", key = "#staticsTtestSampleResult.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsTtestSampleResultDto updateDtoByPrimaryKeySelective(StaticsTtestSampleResult staticsTtestSampleResult) {
		this.staticsTtestSampleResultDao.updateByPrimaryKeySelective(staticsTtestSampleResult);
		return this.staticsTtestSampleResultDao.selectDtoByPrimaryKey(staticsTtestSampleResult.getId());
	}
	
	@Override
	public PageResult<StaticsTtestSampleResultDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsTtestSampleResultDao.pageCountDto(pageObject);
		PageResult<StaticsTtestSampleResultDto> pageResult = new PageResult<StaticsTtestSampleResultDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestSampleResultDto> staticsTtestSampleResultDtoList = ((StaticsTtestSampleResultServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(staticsTtestSampleResultDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<StaticsTtestSampleResultDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsTtestSampleResultDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsTtestSampleResultDto> pageResult = new PageResult<StaticsTtestSampleResultDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestSampleResultDto> staticsTtestSampleResultDtoList = this.staticsTtestSampleResultDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsTtestSampleResultDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsTtestSampleResultDto> pageListCache(PageObject pageObject) {
		return this.staticsTtestSampleResultDao.pageListDto(pageObject);
	}

}