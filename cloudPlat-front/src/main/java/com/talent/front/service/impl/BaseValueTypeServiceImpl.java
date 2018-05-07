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
import com.talent.front.dao.BaseValueTypeDao;
import com.talent.front.dto.BaseValueTypeDto;
import com.talent.front.entity.BaseValueType;
import com.talent.front.service.BaseValueTypeService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：数据类型表服务实现类
 */
@Service
public class BaseValueTypeServiceImpl extends BaseServiceImpl<BaseValueType> implements BaseValueTypeService {
	private static final Logger logger = LoggerFactory.getLogger(BaseValueTypeServiceImpl.class);

	@Autowired
	private BaseValueTypeDao baseValueTypeDao;

	@Override
	public BaseDao<BaseValueType> getBaseDao() {
		return this.baseValueTypeDao;
	}
	
	@CacheSpeObject(value = "baseValueType", key = "#valuetypeId")
	@Override
	public BaseValueTypeDto selectDtoByPrimaryKey(String valuetypeId) {
		return this.baseValueTypeDao.selectDtoByPrimaryKey(valuetypeId);
	}
	
	@CacheSpeObject(value = "baseValueType", key = "#baseValueType.valuetypeId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseValueTypeDto updateDtoByPrimaryKeySelective(BaseValueType baseValueType) {
		this.baseValueTypeDao.updateByPrimaryKeySelective(baseValueType);
		return this.baseValueTypeDao.selectDtoByPrimaryKey(baseValueType.getValuetypeId());
	}
	
	@Override
	public PageResult<BaseValueTypeDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseValueTypeDao.pageCount(pageObject);
		PageResult<BaseValueTypeDto> pageResult = new PageResult<BaseValueTypeDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseValueTypeDto> baseValueTypeDtoList = ((BaseValueTypeServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseValueTypeDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseValueTypeDto> pageListCache(PageObject pageObject) {
		return this.baseValueTypeDao.pageListDto(pageObject);
	}

}