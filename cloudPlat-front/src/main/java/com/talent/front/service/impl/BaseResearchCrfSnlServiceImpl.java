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
import com.talent.front.dao.BaseResearchCrfSnlDao;
import com.talent.front.dto.BaseResearchCrfSnlDto;
import com.talent.front.entity.BaseResearchCrfSnl;
import com.talent.front.service.BaseResearchCrfSnlService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板属性表服务实现类
 */
@Service
public class BaseResearchCrfSnlServiceImpl extends BaseServiceImpl<BaseResearchCrfSnl> implements BaseResearchCrfSnlService {
	private static final Logger logger = LoggerFactory.getLogger(BaseResearchCrfSnlServiceImpl.class);

	@Autowired
	private BaseResearchCrfSnlDao baseResearchCrfSnlDao;

	@Override
	public BaseDao<BaseResearchCrfSnl> getBaseDao() {
		return this.baseResearchCrfSnlDao;
	}
	
	@CacheSpeObject(value = "baseResearchCrfSnl", key = "#id")
	@Override
	public BaseResearchCrfSnlDto selectDtoByPrimaryKey(String id) {
		return this.baseResearchCrfSnlDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "baseResearchCrfSnl", key = "#baseResearchCrfSnl.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseResearchCrfSnlDto updateDtoByPrimaryKeySelective(BaseResearchCrfSnl baseResearchCrfSnl) {
		this.baseResearchCrfSnlDao.updateByPrimaryKeySelective(baseResearchCrfSnl);
		return this.baseResearchCrfSnlDao.selectDtoByPrimaryKey(baseResearchCrfSnl.getId());
	}
	
	@Override
	public PageResult<BaseResearchCrfSnlDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseResearchCrfSnlDao.pageCount(pageObject);
		PageResult<BaseResearchCrfSnlDto> pageResult = new PageResult<BaseResearchCrfSnlDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseResearchCrfSnlDto> baseResearchCrfSnlDtoList = ((BaseResearchCrfSnlServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseResearchCrfSnlDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseResearchCrfSnlDto> pageListCache(PageObject pageObject) {
		return this.baseResearchCrfSnlDao.pageListDto(pageObject);
	}

}