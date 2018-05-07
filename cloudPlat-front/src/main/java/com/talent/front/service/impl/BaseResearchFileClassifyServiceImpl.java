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
import com.talent.front.dao.BaseResearchFileClassifyDao;
import com.talent.front.dto.BaseResearchFileClassifyDto;
import com.talent.front.entity.BaseResearchFileClassify;
import com.talent.front.service.BaseResearchFileClassifyService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档类型服务实现类
 */
@Service
public class BaseResearchFileClassifyServiceImpl extends BaseServiceImpl<BaseResearchFileClassify> implements BaseResearchFileClassifyService {
	private static final Logger logger = LoggerFactory.getLogger(BaseResearchFileClassifyServiceImpl.class);

	@Autowired
	private BaseResearchFileClassifyDao baseResearchFileClassifyDao;

	@Override
	public BaseDao<BaseResearchFileClassify> getBaseDao() {
		return this.baseResearchFileClassifyDao;
	}
	
	@CacheSpeObject(value = "baseResearchFileClassify", key = "#baseResearchFileClassify.classifyId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseResearchFileClassifyDto updateObjectByPrimaryKeySelective(BaseResearchFileClassify baseResearchFileClassify) {
		this.baseResearchFileClassifyDao.updateByPrimaryKeySelective(baseResearchFileClassify);
		return this.baseResearchFileClassifyDao.selectDtoByPrimaryKey(baseResearchFileClassify.getClassifyId());
	}
	
	@Override
	public PageResult<BaseResearchFileClassifyDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseResearchFileClassifyDao.pageCount(pageObject);
		PageResult<BaseResearchFileClassifyDto> pageResult = new PageResult<BaseResearchFileClassifyDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseResearchFileClassifyDto> baseResearchFileClassifyDtoList = ((BaseResearchFileClassifyServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseResearchFileClassifyDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseResearchFileClassifyDto> pageListCache(PageObject pageObject) {
		return this.baseResearchFileClassifyDao.pageListDto(pageObject);
	}

}