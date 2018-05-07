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
import com.talent.front.dao.BaseDiagLogicLogDao;
import com.talent.front.dto.BaseDiagLogicLogDto;
import com.talent.front.entity.BaseDiagLogicLog;
import com.talent.front.service.BaseDiagLogicLogService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断逻辑日志服务实现类
 */
@Service
public class BaseDiagLogicLogServiceImpl extends BaseServiceImpl<BaseDiagLogicLog> implements BaseDiagLogicLogService {
	private static final Logger logger = LoggerFactory.getLogger(BaseDiagLogicLogServiceImpl.class);

	@Autowired
	private BaseDiagLogicLogDao baseDiagLogicLogDao;

	@Override
	public BaseDao<BaseDiagLogicLog> getBaseDao() {
		return this.baseDiagLogicLogDao;
	}
	
	@CacheSpeObject(value = "baseDiagLogicLog", key = "#logId")
	@Override
	public BaseDiagLogicLogDto selectDtoByPrimaryKey(String logId) {
		return this.baseDiagLogicLogDao.selectDtoByPrimaryKey(logId);
	}
	
	@CacheSpeObject(value = "baseDiagLogicLog", key = "#baseDiagLogicLog.logId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseDiagLogicLogDto updateDtoByPrimaryKeySelective(BaseDiagLogicLog baseDiagLogicLog) {
		this.baseDiagLogicLogDao.updateByPrimaryKeySelective(baseDiagLogicLog);
		return this.baseDiagLogicLogDao.selectDtoByPrimaryKey(baseDiagLogicLog.getLogId());
	}
	
	@Override
	public PageResult<BaseDiagLogicLogDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseDiagLogicLogDao.pageCountDto(pageObject);
		PageResult<BaseDiagLogicLogDto> pageResult = new PageResult<BaseDiagLogicLogDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseDiagLogicLogDto> baseDiagLogicLogDtoList = ((BaseDiagLogicLogServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseDiagLogicLogDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<BaseDiagLogicLogDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.baseDiagLogicLogDao.pageCountDtoJoin(pageObject);
		PageResult<BaseDiagLogicLogDto> pageResult = new PageResult<BaseDiagLogicLogDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseDiagLogicLogDto> baseDiagLogicLogDtoList = this.baseDiagLogicLogDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(baseDiagLogicLogDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseDiagLogicLogDto> pageListCache(PageObject pageObject) {
		return this.baseDiagLogicLogDao.pageListDto(pageObject);
	}

}