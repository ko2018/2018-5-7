package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseResearchCrfTempletDto;
import com.talent.front.entity.BaseResearchCrfTemplet;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板表服务接口类
 */
public interface BaseResearchCrfTempletService extends BaseService<BaseResearchCrfTemplet>{

	/**
	* 以主鍵查询DTO
	*/
	BaseResearchCrfTempletDto selectDtoByPrimaryKey(String templetId);

	/**
	* 更新model返回DTO
	*/
	BaseResearchCrfTempletDto updateDtoByPrimaryKeySelective(BaseResearchCrfTemplet baseResearchCrfTemplet);

	PageResult<BaseResearchCrfTempletDto> pageListDto(PageObject pageObject);
	
	List<BaseResearchCrfTempletDto> pageListCache(PageObject pageObject);
	
	int addBaseResearchCrfTempletDto(BaseResearchCrfTempletDto baseResearchCrfTempletDto);
	
	BaseResearchCrfTempletDto deleteBaseResearchCrfTempletDto(BaseResearchCrfTemplet baseResearchCrfTemplet);

}