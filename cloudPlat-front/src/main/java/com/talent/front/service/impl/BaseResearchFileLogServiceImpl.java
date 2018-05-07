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
import com.talent.front.dao.BaseResearchFileLogDao;
import com.talent.front.dto.BaseResearchFileLogDto;
import com.talent.front.entity.BaseResearchFileLog;
import com.talent.front.service.BaseResearchFileLogService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档操作日志表服务实现类
 */
@Service
public class BaseResearchFileLogServiceImpl extends BaseServiceImpl<BaseResearchFileLog> implements BaseResearchFileLogService {
	private static final Logger logger = LoggerFactory.getLogger(BaseResearchFileLogServiceImpl.class);

	@Autowired
	private BaseResearchFileLogDao baseResearchFileLogDao;

	@Override
	public BaseDao<BaseResearchFileLog> getBaseDao() {
		return this.baseResearchFileLogDao;
	}
	
	@CacheSpeObject(value = "baseResearchFileLog", key = "#baseResearchFileLog.logId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseResearchFileLogDto updateObjectByPrimaryKeySelective(BaseResearchFileLog baseResearchFileLog) {
		this.baseResearchFileLogDao.updateByPrimaryKeySelective(baseResearchFileLog);
		return this.baseResearchFileLogDao.selectDtoByPrimaryKey(baseResearchFileLog.getLogId());
	}
	
	@Override
	public PageResult<BaseResearchFileLogDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseResearchFileLogDao.pageCount(pageObject);
		PageResult<BaseResearchFileLogDto> pageResult = new PageResult<BaseResearchFileLogDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseResearchFileLogDto> baseResearchFileLogDtoList = ((BaseResearchFileLogServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseResearchFileLogDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseResearchFileLogDto> pageListCache(PageObject pageObject) {
		return this.baseResearchFileLogDao.pageListDto(pageObject);
	}

}