package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseValueTypeDto;
import com.talent.front.entity.BaseValueType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：数据类型表服务接口类
 */
public interface BaseValueTypeService extends BaseService<BaseValueType>{

	/**
	* 以主鍵查询DTO
	*/
	BaseValueTypeDto selectDtoByPrimaryKey(String valuetypeId);

	/**
	* 更新model返回DTO
	*/
	BaseValueTypeDto updateDtoByPrimaryKeySelective(BaseValueType baseValueType);

	PageResult<BaseValueTypeDto> pageListDto(PageObject pageObject);
	
	List<BaseValueTypeDto> pageListCache(PageObject pageObject);

}