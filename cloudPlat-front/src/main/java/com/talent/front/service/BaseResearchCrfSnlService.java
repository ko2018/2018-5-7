package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchCrfSnlDto;
import com.talent.front.entity.BaseResearchCrfSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板属性表服务接口类
 */
public interface BaseResearchCrfSnlService extends BaseService<BaseResearchCrfSnl>{

	/**
	* 以主鍵查询DTO
	*/
	BaseResearchCrfSnlDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	BaseResearchCrfSnlDto updateDtoByPrimaryKeySelective(BaseResearchCrfSnl baseResearchCrfSnl);

	PageResult<BaseResearchCrfSnlDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchCrfSnlDto> pageListCache(PageObject pageObject);

}