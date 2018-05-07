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
import com.talent.front.dao.StaticsTtestIndDao;
import com.talent.front.dao.StaticsTtestIndGroupDao;
import com.talent.front.dao.StaticsTtestIndResultDao;
import com.talent.front.dto.StaticsCustomCrowdDto;
import com.talent.front.dto.StaticsTtestIndDto;
import com.talent.front.dto.StaticsTtestIndGroupDto;
import com.talent.front.dto.StaticsTtestIndResultDto;
import com.talent.front.entity.StaticsTtestIndResult;
import com.talent.front.service.StaticsTtestIndResultService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述： 独立T检验结果服务实现类
 */
@Service
public class StaticsTtestIndResultServiceImpl extends BaseServiceImpl<StaticsTtestIndResult> implements StaticsTtestIndResultService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestIndResultServiceImpl.class);

	@Autowired
	private StaticsTtestIndResultDao staticsTtestIndResultDao;
	
	@Autowired
	private StaticsTtestIndGroupDao staticsTtestIndGroupDao;
	
	@Autowired
	private StaticsTtestIndDao staticsTtestIndDao;


	@Override
	public BaseDao<StaticsTtestIndResult> getBaseDao() {
		return this.staticsTtestIndResultDao;
	}
	
	@CacheSpeObject(value = "staticsTtestIndResult", key = "#id")
	@Override
	public StaticsTtestIndResultDto selectDtoByPrimaryKey(String id) {
		return this.staticsTtestIndResultDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "staticsTtestIndResult", key = "#staticsTtestIndResult.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsTtestIndResultDto updateDtoByPrimaryKeySelective(StaticsTtestIndResult staticsTtestIndResult) {
		this.staticsTtestIndResultDao.updateByPrimaryKeySelective(staticsTtestIndResult);
		return this.staticsTtestIndResultDao.selectDtoByPrimaryKey(staticsTtestIndResult.getId());
	}
	
	@Override
	public PageResult<StaticsTtestIndResultDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsTtestIndResultDao.pageCountDto(pageObject);
		PageResult<StaticsTtestIndResultDto> pageResult = new PageResult<StaticsTtestIndResultDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestIndResultDto> staticsTtestIndResultDtoList = ((StaticsTtestIndResultServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			for(StaticsTtestIndResultDto dto : staticsTtestIndResultDtoList) {
				StaticsTtestIndDto ttestDto = staticsTtestIndDao.selectDtoByPrimaryKey(dto.getSUuid());
				if(ttestDto != null) {
					if(ttestDto.getSGroupId1() != null) {
						StaticsTtestIndGroupDto  sgroup1 = staticsTtestIndGroupDao.selectDtoByPrimaryKey(ttestDto.getSGroupId1());
						dto.setSgroup1(sgroup1);
					}
					if(ttestDto.getSGroupId2() != null) {
						StaticsTtestIndGroupDto  sgroup2 = staticsTtestIndGroupDao.selectDtoByPrimaryKey(ttestDto.getSGroupId2());
						dto.setSgroup2(sgroup2);
					}
	
				}
			
			}
			pageResult.setQueryResult(staticsTtestIndResultDtoList);
		}
		return pageResult;
	}

	
	
	@Override
	public PageResult<StaticsTtestIndResultDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsTtestIndResultDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsTtestIndResultDto> pageResult = new PageResult<StaticsTtestIndResultDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestIndResultDto> staticsTtestIndResultDtoList = this.staticsTtestIndResultDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsTtestIndResultDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsTtestIndResultDto> pageListCache(PageObject pageObject) {
		return this.staticsTtestIndResultDao.pageListDto(pageObject);
	}

}