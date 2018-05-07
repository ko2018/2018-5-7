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
import com.talent.front.dao.BaseSampleClassifyDao;
import com.talent.front.dto.BaseSampleClassifyDto;
import com.talent.front.entity.BaseSampleClassify;
import com.talent.front.service.BaseSampleClassifyService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：样本类型表服务实现类
 */
@Service
public class BaseSampleClassifyServiceImpl extends BaseServiceImpl<BaseSampleClassify> implements BaseSampleClassifyService {
	private static final Logger logger = LoggerFactory.getLogger(BaseSampleClassifyServiceImpl.class);

	@Autowired
	private BaseSampleClassifyDao baseSampleClassifyDao;

	@Override
	public BaseDao<BaseSampleClassify> getBaseDao() {
		return this.baseSampleClassifyDao;
	}
	
	@CacheSpeObject(value = "baseSampleClassify", key = "#baseSampleClassify.classifyCode")
	@Override
	public BaseSampleClassifyDto updateObjectByPrimaryKeySelective(BaseSampleClassify baseSampleClassify) {
		this.baseSampleClassifyDao.updateByPrimaryKeySelective(baseSampleClassify);
		return this.baseSampleClassifyDao.selectDtoByPrimaryKey(baseSampleClassify.getClassifyCode());
	}
	
	@Override
	public PageResult<BaseSampleClassifyDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseSampleClassifyDao.pageCount(pageObject);
		PageResult<BaseSampleClassifyDto> pageResult = new PageResult<BaseSampleClassifyDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseSampleClassifyDto> baseSampleClassifyDtoList = ((BaseSampleClassifyServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseSampleClassifyDtoList);
		}
		return pageResult;
	}
	
	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseSampleClassifyDto> pageListCache(PageObject pageObject) {
		return this.baseSampleClassifyDao.pageListDto(pageObject);
	}

}