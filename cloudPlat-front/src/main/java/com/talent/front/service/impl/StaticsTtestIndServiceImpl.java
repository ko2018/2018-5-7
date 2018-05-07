package com.talent.front.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.front.util.redis.annotation.CacheSpeObject;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.BaseSnlDao;
import com.talent.front.dao.StaticsCustomCrowdDao;
import com.talent.front.dao.StaticsTtestIndDao;
import com.talent.front.dao.StaticsTtestIndGroupDao;
import com.talent.front.dto.BaseSnlDto;
import com.talent.front.dto.StaticsCustomCrowdDto;
import com.talent.front.dto.StaticsTtestIndDto;
import com.talent.front.dto.StaticsTtestIndGroupDto;
import com.talent.front.entity.StaticsTtestInd;
import com.talent.front.service.StaticsTtestIndService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述： 独立T检验服务实现类
 */
@Service
public class StaticsTtestIndServiceImpl extends BaseServiceImpl<StaticsTtestInd> implements StaticsTtestIndService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestIndServiceImpl.class);

	@Autowired
	private StaticsTtestIndDao staticsTtestIndDao;
	
	@Autowired
	private BaseSnlDao baseSnlDao;
	
	@Autowired
	private StaticsTtestIndGroupDao staticsTtestIndGroupDao;
	
	@Autowired
	private StaticsCustomCrowdDao staticsCustomCrowdDao;

	@Override
	public BaseDao<StaticsTtestInd> getBaseDao() {
		return this.staticsTtestIndDao;
	}
	
	@CacheSpeObject(value = "staticsTtestInd", key = "#id")
	@Override
	public StaticsTtestIndDto selectDtoByPrimaryKey(String id) {
		return this.staticsTtestIndDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "staticsTtestInd", key = "#staticsTtestInd.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsTtestIndDto updateDtoByPrimaryKeySelective(StaticsTtestInd staticsTtestInd) {
		this.staticsTtestIndDao.updateByPrimaryKeySelective(staticsTtestInd);
		return this.staticsTtestIndDao.selectDtoByPrimaryKey(staticsTtestInd.getId());
	}
	
	@Override
	public PageResult<StaticsTtestIndDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsTtestIndDao.pageCountDto(pageObject);
		PageResult<StaticsTtestIndDto> pageResult = new PageResult<StaticsTtestIndDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestIndDto> staticsTtestIndDtoList = ((StaticsTtestIndServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			for(StaticsTtestIndDto dto : staticsTtestIndDtoList) {
				String sDictStr = dto.getSDictList();
				String[] dictArrs = sDictStr.split(",");
				List<String> list = new ArrayList<String>();
				for(String dictId : dictArrs) {
					BaseSnlDto dto1 = this.baseSnlDao.selectDtoByDictId(Integer.parseInt(dictId));
					if(dto1 != null) {
						list.add(dto1.getNameCn());
					}
				}
				
				dto.setListSnlList(list);
				
				if(dto.getSGroupId1() != null) {
					StaticsTtestIndGroupDto  group1 = staticsTtestIndGroupDao.selectDtoByPrimaryKey(dto.getSGroupId1());
					dto.setGroup1(group1);
				}
				
				if(dto.getSGroupId2() != null) {
					StaticsTtestIndGroupDto  group2 = staticsTtestIndGroupDao.selectDtoByPrimaryKey(dto.getSGroupId2());
					dto.setGroup2(group2);
				}
				
				if(dto.getSCrowd() != null) {
					StaticsCustomCrowdDto crowdDto = staticsCustomCrowdDao.selectDtoByPrimaryKey(dto.getSCrowd());
					if(crowdDto != null) {
						dto.setsCrowdName(crowdDto.getStaticsPName());
			
					}
				}
			}
			
			pageResult.setQueryResult(staticsTtestIndDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<StaticsTtestIndDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsTtestIndDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsTtestIndDto> pageResult = new PageResult<StaticsTtestIndDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsTtestIndDto> staticsTtestIndDtoList = this.staticsTtestIndDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsTtestIndDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsTtestIndDto> pageListCache(PageObject pageObject) {
		return this.staticsTtestIndDao.pageListDto(pageObject);
	}

}