package com.talent.front.service.impl;

import java.util.ArrayList;
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
import com.talent.front.dao.BaseSnlDao;
import com.talent.front.dao.StaticsCustomCrowdDao;
import com.talent.front.dao.StaticsDescribeAnalysiscDao;
import com.talent.front.dao.StaticsTtestIndGroupDao;
import com.talent.front.dto.BaseSnlDto;
import com.talent.front.dto.StaticsCustomCrowdDto;
import com.talent.front.dto.StaticsDescribeAnalysiscDto;
import com.talent.front.dto.StaticsTtestIndGroupDto;
import com.talent.front.entity.StaticsDescribeAnalysisc;
import com.talent.front.service.StaticsDescribeAnalysiscService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析服务实现类
 */
@Service
public class StaticsDescribeAnalysiscServiceImpl extends BaseServiceImpl<StaticsDescribeAnalysisc> implements StaticsDescribeAnalysiscService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsDescribeAnalysiscServiceImpl.class);

	@Autowired
	private StaticsDescribeAnalysiscDao staticsDescribeAnalysiscDao;

	
	@Autowired
	private BaseSnlDao baseSnlDao;
	
	@Autowired
	private StaticsCustomCrowdDao staticsCustomCrowdDao;
	
	@Autowired
	private StaticsTtestIndGroupDao staticsTtestIndGroupDao;
	
	@Override
	public BaseDao<StaticsDescribeAnalysisc> getBaseDao() {
		return this.staticsDescribeAnalysiscDao;
	}
	
	@CacheSpeObject(value = "staticsDescribeAnalysisc", key = "#id")
	@Override
	public StaticsDescribeAnalysiscDto selectDtoByPrimaryKey(String id) {
		return this.staticsDescribeAnalysiscDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "staticsDescribeAnalysisc", key = "#staticsDescribeAnalysisc.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsDescribeAnalysiscDto updateDtoByPrimaryKeySelective(StaticsDescribeAnalysisc staticsDescribeAnalysisc) {
		this.staticsDescribeAnalysiscDao.updateByPrimaryKeySelective(staticsDescribeAnalysisc);
		return this.staticsDescribeAnalysiscDao.selectDtoByPrimaryKey(staticsDescribeAnalysisc.getId());
	}
	
	@Override
	public PageResult<StaticsDescribeAnalysiscDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsDescribeAnalysiscDao.pageCountDto(pageObject);
		PageResult<StaticsDescribeAnalysiscDto> pageResult = new PageResult<StaticsDescribeAnalysiscDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsDescribeAnalysiscDto> staticsDescribeAnalysiscDtoList = ((StaticsDescribeAnalysiscServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			
			for(StaticsDescribeAnalysiscDto dto : staticsDescribeAnalysiscDtoList) {
				String sDictStr = dto.getDictList();
				String[] dictArrs = sDictStr.split(",");
				List<String> list = new ArrayList<String>();
				for(String dictId : dictArrs) {
					BaseSnlDto dto1 = this.baseSnlDao.selectDtoByDictId(Integer.parseInt(dictId));
					if(dto1 != null) {
						list.add(dto1.getNameCn());
					}
				}
				
				dto.setListSnlList(list);
				
				
				if(dto.getCrowdId() != null) {
					StaticsCustomCrowdDto crowdDto = staticsCustomCrowdDao.selectDtoByPrimaryKey(dto.getCrowdId());
					if(crowdDto != null) {
						dto.setsCrowdName(crowdDto.getStaticsPName());
			
					}
				}
				
				if(dto.getGroupid() != null) {
					StaticsTtestIndGroupDto  group = staticsTtestIndGroupDao.selectDtoByPrimaryKey(dto.getGroupid());
					dto.setGroup(group);
				}
				
				
			}
			pageResult.setQueryResult(staticsDescribeAnalysiscDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<StaticsDescribeAnalysiscDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsDescribeAnalysiscDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsDescribeAnalysiscDto> pageResult = new PageResult<StaticsDescribeAnalysiscDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsDescribeAnalysiscDto> staticsDescribeAnalysiscDtoList = this.staticsDescribeAnalysiscDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsDescribeAnalysiscDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsDescribeAnalysiscDto> pageListCache(PageObject pageObject) {
		return this.staticsDescribeAnalysiscDao.pageListDto(pageObject);
	}

}