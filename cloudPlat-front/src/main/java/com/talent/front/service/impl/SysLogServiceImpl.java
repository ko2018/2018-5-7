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
import com.talent.front.dao.SysLogDao;
import com.talent.front.dto.SysLogDto;
import com.talent.front.entity.SysLog;
import com.talent.front.service.SysLogService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：系统日志操作表服务实现类
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {
	private static final Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);

	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public BaseDao<SysLog> getBaseDao() {
		return this.sysLogDao;
	}
	
	@CacheSpeObject(value = "sysLog", key = "#sysLog.syslogId")
	@Override
	public SysLogDto updateObjectByPrimaryKeySelective(SysLog sysLog) {
		this.sysLogDao.updateByPrimaryKeySelective(sysLog);
		return this.sysLogDao.selectDtoByPrimaryKey(sysLog.getSyslogId());
	}
	
	@Override
	public PageResult<SysLogDto> pageListDto(PageObject pageObject) {
		long totalCount = this.sysLogDao.pageCount(pageObject);
		PageResult<SysLogDto> pageResult = new PageResult<SysLogDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<SysLogDto> sysLogDtoList = ((SysLogServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(sysLogDtoList);
		}
		return pageResult;
	}
	
	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<SysLogDto> pageListCache(PageObject pageObject) {
		return this.sysLogDao.pageListDto(pageObject);
	}

}