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
import com.talent.front.dao.BaseHotInfoTypeDao;
import com.talent.front.dto.BaseHotInfoTypeDto;
import com.talent.front.entity.BaseHotInfoType;
import com.talent.front.service.BaseHotInfoTypeService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息类型表服务实现类
 */
@Service
public class BaseHotInfoTypeServiceImpl extends BaseServiceImpl<BaseHotInfoType> implements BaseHotInfoTypeService {
	private static final Logger logger = LoggerFactory.getLogger(BaseHotInfoTypeServiceImpl.class);

	@Autowired
	private BaseHotInfoTypeDao baseHotInfoTypeDao;

	@Override
	public BaseDao<BaseHotInfoType> getBaseDao() {
		return this.baseHotInfoTypeDao;
	}
	
	@CacheSpeObject(value = "baseHotInfoType", key = "#infoTypeId")
	@Override
	public BaseHotInfoTypeDto selectDtoByPrimaryKey(String infoTypeId) {
		return this.baseHotInfoTypeDao.selectDtoByPrimaryKey(infoTypeId);
	}
	
	@CacheSpeObject(value = "baseHotInfoType", key = "#baseHotInfoType.infoTypeId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseHotInfoTypeDto updateDtoByPrimaryKeySelective(BaseHotInfoType baseHotInfoType) {
		this.baseHotInfoTypeDao.updateByPrimaryKeySelective(baseHotInfoType);
		return this.baseHotInfoTypeDao.selectDtoByPrimaryKey(baseHotInfoType.getInfoTypeId());
	}
	
	@Override
	public PageResult<BaseHotInfoTypeDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseHotInfoTypeDao.pageCount(pageObject);
		PageResult<BaseHotInfoTypeDto> pageResult = new PageResult<BaseHotInfoTypeDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseHotInfoTypeDto> baseHotInfoTypeDtoList = ((BaseHotInfoTypeServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseHotInfoTypeDtoList);
		}
		return pageResult;
	}
	
	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseHotInfoTypeDto> pageListCache(PageObject pageObject) {
		return this.baseHotInfoTypeDao.pageListDto(pageObject);
	}

}