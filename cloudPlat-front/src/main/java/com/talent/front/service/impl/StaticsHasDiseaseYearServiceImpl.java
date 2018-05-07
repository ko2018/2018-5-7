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
import com.talent.front.dao.StaticsHasDiseaseYearDao;
import com.talent.front.dto.StaticsHasDiseaseYearDto;
import com.talent.front.entity.StaticsHasDiseaseYear;
import com.talent.front.service.StaticsHasDiseaseYearService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：统计原始数据每年患病情况服务实现类
 */
@Service
public class StaticsHasDiseaseYearServiceImpl extends BaseServiceImpl<StaticsHasDiseaseYear> implements StaticsHasDiseaseYearService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsHasDiseaseYearServiceImpl.class);

	@Autowired
	private StaticsHasDiseaseYearDao staticsHasDiseaseYearDao;

	@Override
	public BaseDao<StaticsHasDiseaseYear> getBaseDao() {
		return this.staticsHasDiseaseYearDao;
	}
	
	@CacheSpeObject(value = "staticsHasDiseaseYear", key = "#staticsSid")
	@Override
	public StaticsHasDiseaseYearDto selectDtoByPrimaryKey(String staticsSid) {
		return this.staticsHasDiseaseYearDao.selectDtoByPrimaryKey(staticsSid);
	}
	
	@CacheSpeObject(value = "staticsHasDiseaseYear", key = "#staticsHasDiseaseYear.staticsSid", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsHasDiseaseYearDto updateDtoByPrimaryKeySelective(StaticsHasDiseaseYear staticsHasDiseaseYear) {
		this.staticsHasDiseaseYearDao.updateByPrimaryKeySelective(staticsHasDiseaseYear);
		return this.staticsHasDiseaseYearDao.selectDtoByPrimaryKey(staticsHasDiseaseYear.getStaticsSid());
	}
	
	@Override
	public PageResult<StaticsHasDiseaseYearDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsHasDiseaseYearDao.pageCount(pageObject);
		PageResult<StaticsHasDiseaseYearDto> pageResult = new PageResult<StaticsHasDiseaseYearDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsHasDiseaseYearDto> staticsHasDiseaseYearDtoList = ((StaticsHasDiseaseYearServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(staticsHasDiseaseYearDtoList);
		}
		return pageResult;
	}
	
	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsHasDiseaseYearDto> pageListCache(PageObject pageObject) {
		return this.staticsHasDiseaseYearDao.pageListDto(pageObject);
	}

}