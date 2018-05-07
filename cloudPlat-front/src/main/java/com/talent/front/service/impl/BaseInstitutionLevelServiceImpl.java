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
import com.talent.front.dao.BaseInstitutionLevelDao;
import com.talent.front.dto.BaseInstitutionLevelDto;
import com.talent.front.entity.BaseInstitutionLevel;
import com.talent.front.service.BaseInstitutionLevelService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-08 <br/>
 * 描述：机构级别服务实现类
 */
@Service
public class BaseInstitutionLevelServiceImpl extends BaseServiceImpl<BaseInstitutionLevel> implements BaseInstitutionLevelService {
	private static final Logger logger = LoggerFactory.getLogger(BaseInstitutionLevelServiceImpl.class);

	@Autowired
	private BaseInstitutionLevelDao baseInstitutionLevelDao;

	@Override
	public BaseDao<BaseInstitutionLevel> getBaseDao() {
		return this.baseInstitutionLevelDao;
	}
	
	@CacheSpeObject(value = "baseInstitutionLevel", key = "#institutionlevelId")
	@Override
	public BaseInstitutionLevelDto selectDtoByPrimaryKey(String institutionlevelId) {
		return this.baseInstitutionLevelDao.selectDtoByPrimaryKey(institutionlevelId);
	}
	
	@CacheSpeObject(value = "baseInstitutionLevel", key = "#baseInstitutionLevel.institutionlevelId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseInstitutionLevelDto updateDtoByPrimaryKeySelective(BaseInstitutionLevel baseInstitutionLevel) {
		this.baseInstitutionLevelDao.updateByPrimaryKeySelective(baseInstitutionLevel);
		return this.baseInstitutionLevelDao.selectDtoByPrimaryKey(baseInstitutionLevel.getInstitutionlevelId());
	}
	
	@Override
	public PageResult<BaseInstitutionLevelDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseInstitutionLevelDao.pageCountDto(pageObject);
		PageResult<BaseInstitutionLevelDto> pageResult = new PageResult<BaseInstitutionLevelDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseInstitutionLevelDto> baseInstitutionLevelDtoList = ((BaseInstitutionLevelServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseInstitutionLevelDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<BaseInstitutionLevelDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.baseInstitutionLevelDao.pageCountDtoJoin(pageObject);
		PageResult<BaseInstitutionLevelDto> pageResult = new PageResult<BaseInstitutionLevelDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseInstitutionLevelDto> baseInstitutionLevelDtoList = this.baseInstitutionLevelDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(baseInstitutionLevelDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseInstitutionLevelDto> pageListCache(PageObject pageObject) {
		return this.baseInstitutionLevelDao.pageListDto(pageObject);
	}

}