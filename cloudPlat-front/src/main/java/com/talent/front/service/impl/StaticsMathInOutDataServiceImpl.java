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
import com.talent.front.dao.StaticsMathInOutDataDao;
import com.talent.front.dto.StaticsMathInOutDataDto;
import com.talent.front.entity.StaticsMathInOutData;
import com.talent.front.service.StaticsMathInOutDataService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-05 <br/>
 * 描述：数学分析服务实现类
 */
@Service
public class StaticsMathInOutDataServiceImpl extends BaseServiceImpl<StaticsMathInOutData> implements StaticsMathInOutDataService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsMathInOutDataServiceImpl.class);

	@Autowired
	private StaticsMathInOutDataDao staticsMathInOutDataDao;

	@Override
	public BaseDao<StaticsMathInOutData> getBaseDao() {
		return this.staticsMathInOutDataDao;
	}
	
	@CacheSpeObject(value = "staticsMathInOutData", key = "#id")
	@Override
	public StaticsMathInOutDataDto selectDtoByPrimaryKey(String id) {
		return this.staticsMathInOutDataDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "staticsMathInOutData", key = "#staticsMathInOutData.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsMathInOutDataDto updateDtoByPrimaryKeySelective(StaticsMathInOutData staticsMathInOutData) {
		this.staticsMathInOutDataDao.updateByPrimaryKeySelective(staticsMathInOutData);
		return this.staticsMathInOutDataDao.selectDtoByPrimaryKey(staticsMathInOutData.getId());
	}
	
	@Override
	public PageResult<StaticsMathInOutDataDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsMathInOutDataDao.pageCountDto(pageObject);
		PageResult<StaticsMathInOutDataDto> pageResult = new PageResult<StaticsMathInOutDataDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsMathInOutDataDto> staticsMathInOutDataDtoList = ((StaticsMathInOutDataServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			for(StaticsMathInOutDataDto dto : staticsMathInOutDataDtoList) {
				dto.setDataInMaps();
			}
			pageResult.setQueryResult(staticsMathInOutDataDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<StaticsMathInOutDataDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsMathInOutDataDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsMathInOutDataDto> pageResult = new PageResult<StaticsMathInOutDataDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsMathInOutDataDto> staticsMathInOutDataDtoList = this.staticsMathInOutDataDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsMathInOutDataDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsMathInOutDataDto> pageListCache(PageObject pageObject) {
		return this.staticsMathInOutDataDao.pageListDto(pageObject);
	}

}