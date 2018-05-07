package com.talent.front.service.impl;

import java.util.List;
import java.util.Map;

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
import com.talent.base.util.JacksonUtil;
import com.talent.front.dao.StaticsDescribeAnalysiscDao;
import com.talent.front.dao.StaticsDescribeAnalysiscResultDao;
import com.talent.front.dao.StaticsTtestIndGroupDao;
import com.talent.front.dto.StaticsDescribeAnalysiscDto;
import com.talent.front.dto.StaticsDescribeAnalysiscResultDto;
import com.talent.front.dto.StaticsTtestIndGroupDto;
import com.talent.front.entity.StaticsDescribeAnalysiscResult;
import com.talent.front.service.StaticsDescribeAnalysiscResultService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析结果服务实现类
 */
@Service
public class StaticsDescribeAnalysiscResultServiceImpl extends BaseServiceImpl<StaticsDescribeAnalysiscResult> implements StaticsDescribeAnalysiscResultService {
	private static final Logger logger = LoggerFactory.getLogger(StaticsDescribeAnalysiscResultServiceImpl.class);

	@Autowired
	private StaticsDescribeAnalysiscResultDao staticsDescribeAnalysiscResultDao;

	@Autowired
	private StaticsDescribeAnalysiscDao staticsDescribeAnalysiscDao;
	
	@Override
	public BaseDao<StaticsDescribeAnalysiscResult> getBaseDao() {
		return this.staticsDescribeAnalysiscResultDao;
	}
	
	@CacheSpeObject(value = "staticsDescribeAnalysiscResult", key = "#id")
	@Override
	public StaticsDescribeAnalysiscResultDto selectDtoByPrimaryKey(String id) {
		return this.staticsDescribeAnalysiscResultDao.selectDtoByPrimaryKey(id);
	}
	
	@CacheSpeObject(value = "staticsDescribeAnalysiscResult", key = "#staticsDescribeAnalysiscResult.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public StaticsDescribeAnalysiscResultDto updateDtoByPrimaryKeySelective(StaticsDescribeAnalysiscResult staticsDescribeAnalysiscResult) {
		this.staticsDescribeAnalysiscResultDao.updateByPrimaryKeySelective(staticsDescribeAnalysiscResult);
		return this.staticsDescribeAnalysiscResultDao.selectDtoByPrimaryKey(staticsDescribeAnalysiscResult.getId());
	}
	
	@Autowired
	private StaticsTtestIndGroupDao staticsTtestIndGroupDao;
	
	@Override
	public PageResult<StaticsDescribeAnalysiscResultDto> pageListDto(PageObject pageObject) {
		long totalCount = this.staticsDescribeAnalysiscResultDao.pageCountDto(pageObject);
		PageResult<StaticsDescribeAnalysiscResultDto> pageResult = new PageResult<StaticsDescribeAnalysiscResultDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsDescribeAnalysiscResultDto> staticsDescribeAnalysiscResultDtoList = ((StaticsDescribeAnalysiscResultServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			
			for(StaticsDescribeAnalysiscResultDto dto : staticsDescribeAnalysiscResultDtoList) {
				Map<String, Object> dictMap = JacksonUtil.readValue(dto.getDictVal(), Map.class);
				dto.setDictMap(dictMap);
				StaticsDescribeAnalysiscDto ttestDto = staticsDescribeAnalysiscDao.selectDtoByPrimaryKey(dto.getUuid());
				
				if(ttestDto != null) {
					if(ttestDto.getGroupid() != null) {
						StaticsTtestIndGroupDto  sgroup = staticsTtestIndGroupDao.selectDtoByPrimaryKey(ttestDto.getGroupid());
						dto.setGroup(sgroup);
					}
				
				}
				
			}
			pageResult.setQueryResult(staticsDescribeAnalysiscResultDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<StaticsDescribeAnalysiscResultDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.staticsDescribeAnalysiscResultDao.pageCountDtoJoin(pageObject);
		PageResult<StaticsDescribeAnalysiscResultDto> pageResult = new PageResult<StaticsDescribeAnalysiscResultDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<StaticsDescribeAnalysiscResultDto> staticsDescribeAnalysiscResultDtoList = this.staticsDescribeAnalysiscResultDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(staticsDescribeAnalysiscResultDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<StaticsDescribeAnalysiscResultDto> pageListCache(PageObject pageObject) {
		return this.staticsDescribeAnalysiscResultDao.pageListDto(pageObject);
	}

}