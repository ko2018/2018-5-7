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
import com.talent.front.dao.BaseSnlClassifyDao;
import com.talent.front.dto.BaseSnlClassifyDto;
import com.talent.front.entity.BaseSnlClassify;
import com.talent.front.service.BaseSnlClassifyService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：标准类别表服务实现类
 */
@Service
public class BaseSnlClassifyServiceImpl extends BaseServiceImpl<BaseSnlClassify> implements BaseSnlClassifyService {
	private static final Logger logger = LoggerFactory.getLogger(BaseSnlClassifyServiceImpl.class);

	@Autowired
	private BaseSnlClassifyDao baseSnlClassifyDao;

	@Override
	public BaseDao<BaseSnlClassify> getBaseDao() {
		return this.baseSnlClassifyDao;
	}
	
	@CacheSpeObject(value = "baseSnlClassify", key = "#baseSnlClassify.classifyNo", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseSnlClassifyDto updateObjectByPrimaryKeySelective(BaseSnlClassify baseSnlClassify) {
		this.baseSnlClassifyDao.updateByPrimaryKeySelective(baseSnlClassify);
		return this.baseSnlClassifyDao.selectDtoByPrimaryKey(baseSnlClassify.getClassifyNo());
	}
	
	@Override
	public PageResult<BaseSnlClassifyDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseSnlClassifyDao.pageCount(pageObject);
		PageResult<BaseSnlClassifyDto> pageResult = new PageResult<BaseSnlClassifyDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseSnlClassifyDto> baseSnlClassifyDtoList = ((BaseSnlClassifyServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseSnlClassifyDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseSnlClassifyDto> pageListCache(PageObject pageObject) {
		return this.baseSnlClassifyDao.pageListDto(pageObject);
	}

}