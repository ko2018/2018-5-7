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
import com.talent.front.dao.BaseBioSampleDao;
import com.talent.front.dto.BaseBioSampleDto;
import com.talent.front.entity.BaseBioSample;
import com.talent.front.service.BaseBioSampleService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：生物样本表服务实现类
 */
@Service
public class BaseBioSampleServiceImpl extends BaseServiceImpl<BaseBioSample> implements BaseBioSampleService {
	private static final Logger logger = LoggerFactory.getLogger(BaseBioSampleServiceImpl.class);

	@Autowired
	private BaseBioSampleDao baseBioSampleDao;

	@Override
	public BaseDao<BaseBioSample> getBaseDao() {
		return this.baseBioSampleDao;
	}
	
	@CacheSpeObject(value = "baseBioSample", key = "#baseBioSample.sampleId")
	@Override
	public BaseBioSampleDto updateObjectByPrimaryKeySelective(BaseBioSample baseBioSample) {
		this.baseBioSampleDao.updateByPrimaryKeySelective(baseBioSample);
		return this.baseBioSampleDao.selectDtoByPrimaryKey(baseBioSample.getSampleId());
	}
	
	@Override
	public PageResult<BaseBioSampleDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseBioSampleDao.pageCount(pageObject);
		PageResult<BaseBioSampleDto> pageResult = new PageResult<BaseBioSampleDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseBioSampleDto> baseBioSampleDtoList = ((BaseBioSampleServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseBioSampleDtoList);
		}
		return pageResult;
	}
	
	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseBioSampleDto> pageListCache(PageObject pageObject) {
		return this.baseBioSampleDao.pageListDto(pageObject);
	}

}