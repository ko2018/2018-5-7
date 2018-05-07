package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.SysLogDto;
import com.talent.front.entity.SysLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：系统日志操作表服务接口类
 */
public interface SysLogService extends BaseService<SysLog>{

	SysLogDto updateObjectByPrimaryKeySelective(SysLog sysLog);

	PageResult<SysLogDto> pageListDto(PageObject pageObject);
	
	List<SysLogDto> pageListCache(PageObject pageObject);

}