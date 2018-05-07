package com.talent.mds.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.mds.dto.BaseDataStdDto;
import com.talent.mds.entity.BaseDataStd;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：标准数据表服务接口类
 */
public interface BaseDataStdService extends BaseService<BaseDataStd> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseDataStdDto selectDtoByPrimaryKey(String datastdId);

	/**
	 * 更新model返回DTO
	 */
	BaseDataStdDto updateDtoByPrimaryKeySelective(BaseDataStd baseDataStd);

	PageResult<BaseDataStdDto> pageListDto(PageObject pageObject);

	List<BaseDataStdDto> pageListCache(PageObject pageObject);

	/**
	 * @deprecated
	 */
	void sendDataDtd(BaseDataStdDto baseDataStdDto) throws JsonProcessingException;

}