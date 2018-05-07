package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseHotInfoTypeDto;
import com.talent.front.entity.BaseHotInfoType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息类型表服务接口类
 */
public interface BaseHotInfoTypeService extends BaseService<BaseHotInfoType>{

	/**
	* 以主鍵查询DTO
	*/
	BaseHotInfoTypeDto selectDtoByPrimaryKey(String infoTypeId);

	/**
	* 更新model返回DTO
	*/
	BaseHotInfoTypeDto updateDtoByPrimaryKeySelective(BaseHotInfoType baseHotInfoType);

	PageResult<BaseHotInfoTypeDto> pageListDto(PageObject pageObject);
	
	List<BaseHotInfoTypeDto> pageListCache(PageObject pageObject);

}