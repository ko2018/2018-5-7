package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResRatioDto;
import com.talent.front.entity.BaseResRatio;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源占比服务接口类
 */
public interface BaseResRatioService extends BaseService<BaseResRatio>{

	/**
	* 以主鍵查询DTO
	*/
	BaseResRatioDto selectDtoByPrimaryKey(String resratioId);

	/**
	* 更新model返回DTO
	*/
	BaseResRatioDto updateDtoByPrimaryKeySelective(BaseResRatio baseResRatio);

	PageResult<BaseResRatioDto> pageListDto(PageObject pageObject);
	
	PageResult<BaseResRatioDto> pageListDtoJoin(PageObject pageObject);
	
	List<BaseResRatioDto> pageListCache(PageObject pageObject);

}