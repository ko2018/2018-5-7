package com.talent.dcs.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseDiagMultipleDto;
import com.talent.dcs.entity.BaseDiag;
import com.talent.dcs.entity.BaseDiagMultiple;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：综合诊断表服务接口类
 */
public interface BaseDiagMultipleService extends BaseService<BaseDiagMultiple> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseDiagMultipleDto selectDtoByPrimaryKey(String recordId);

	/**
	 * 更新model返回DTO
	 */
	BaseDiagMultipleDto updateDtoByPrimaryKeySelective(BaseDiagMultiple baseDiagMultiple);

	PageResult<BaseDiagMultipleDto> pageListDto(PageObject pageObject);

	PageResult<BaseDiagMultipleDto> pageListDtoJoin(PageObject pageObject);

	List<BaseDiagMultipleDto> pageListCache(PageObject pageObject);

	BaseDiagMultipleDto getDtoByDataId(String dataId);

	void addDiagAndMultiple(BaseDiagMultiple baseDiagMultiple, List<BaseDiag> list);

}