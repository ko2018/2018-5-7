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
import com.talent.front.dao.BaseResRatioDao;
import com.talent.front.dto.BaseResRatioDto;
import com.talent.front.entity.BaseResRatio;
import com.talent.front.service.BaseResRatioService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源占比服务实现类
 */
@Service
public class BaseResRatioServiceImpl extends BaseServiceImpl<BaseResRatio> implements BaseResRatioService {
	private static final Logger logger = LoggerFactory.getLogger(BaseResRatioServiceImpl.class);

	@Autowired
	private BaseResRatioDao baseResRatioDao;

	@Override
	public BaseDao<BaseResRatio> getBaseDao() {
		return this.baseResRatioDao;
	}
	
	@CacheSpeObject(value = "baseResRatio", key = "#resratioId")
	@Override
	public BaseResRatioDto selectDtoByPrimaryKey(String resratioId) {
		return this.baseResRatioDao.selectDtoByPrimaryKey(resratioId);
	}
	
	@CacheSpeObject(value = "baseResRatio", key = "#baseResRatio.resratioId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseResRatioDto updateDtoByPrimaryKeySelective(BaseResRatio baseResRatio) {
		this.baseResRatioDao.updateByPrimaryKeySelective(baseResRatio);
		return this.baseResRatioDao.selectDtoByPrimaryKey(baseResRatio.getResratioId());
	}
	
	@Override
	public PageResult<BaseResRatioDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseResRatioDao.pageCountDto(pageObject);
		PageResult<BaseResRatioDto> pageResult = new PageResult<BaseResRatioDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseResRatioDto> baseResRatioDtoList = ((BaseResRatioServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseResRatioDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<BaseResRatioDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.baseResRatioDao.pageCountDtoJoin(pageObject);
		PageResult<BaseResRatioDto> pageResult = new PageResult<BaseResRatioDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseResRatioDto> baseResRatioDtoList = this.baseResRatioDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(baseResRatioDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseResRatioDto> pageListCache(PageObject pageObject) {
		return this.baseResRatioDao.pageListDto(pageObject);
	}

}