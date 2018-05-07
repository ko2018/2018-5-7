package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDiagLogicLogDto;
import com.talent.front.entity.BaseDiagLogicLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断逻辑日志服务接口类
 */
public interface BaseDiagLogicLogService extends BaseService<BaseDiagLogicLog>{

	/**
	* 以主鍵查询DTO
	*/
	BaseDiagLogicLogDto selectDtoByPrimaryKey(String logId);

	/**
	* 更新model返回DTO
	*/
	BaseDiagLogicLogDto updateDtoByPrimaryKeySelective(BaseDiagLogicLog baseDiagLogicLog);

	PageResult<BaseDiagLogicLogDto> pageListDto(PageObject pageObject);
	
	PageResult<BaseDiagLogicLogDto> pageListDtoJoin(PageObject pageObject);
	
	List<BaseDiagLogicLogDto> pageListCache(PageObject pageObject);

}