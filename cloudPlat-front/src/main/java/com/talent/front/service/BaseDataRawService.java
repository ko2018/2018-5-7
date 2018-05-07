package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDataRawDto;
import com.talent.front.entity.BaseDataRaw;
import com.talent.front.entity.BaseSearchDataRaw;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：原始数据表服务接口类
 */
public interface BaseDataRawService extends BaseService<BaseDataRaw> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseDataRawDto selectDtoByPrimaryKey(String datarawId);

	/**
	 * 更新model返回DTO
	 */
	BaseDataRawDto updateDtoByPrimaryKeySelective(BaseDataRaw baseDataRaw);

	PageResult<BaseDataRawDto> pageListDto(PageObject pageObject);

	List<BaseDataRawDto> pageListCache(PageObject pageObject);

	BaseDataRawDto getDtoByAllKey(String userCode, String institutionId, String checkCode, String docId);

	void addBaseDataRawDto(BaseDataRawDto baseDataRawDto);
	
	BaseDataRawDto selectDtoByOrganExamIDUserID(BaseSearchDataRaw baseSearchDataRaw);

}