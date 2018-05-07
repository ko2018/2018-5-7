package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDataCleanDto;
import com.talent.front.entity.BaseDataClean;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：清洗数据表服务接口类
 */
public interface BaseDataCleanService extends BaseService<BaseDataClean> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseDataCleanDto selectDtoByPrimaryKey(String datacleanId);

	/**
	 * 更新model返回DTO
	 */
	BaseDataCleanDto updateDtoByPrimaryKeySelective(BaseDataClean baseDataClean);

	PageResult<BaseDataCleanDto> pageListDto(PageObject pageObject);

	List<BaseDataCleanDto> pageListCache(PageObject pageObject);

	BaseDataCleanDto getDtoByAllKey(String userCode, String institutionId, String checkCode, String docId);

}