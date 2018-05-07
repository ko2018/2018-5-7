package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseKeywordUseDto;
import com.talent.front.entity.BaseKeywordUse;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-26 <br/>
 * 描述：关键字应用表服务接口类
 */
public interface BaseKeywordUseService extends BaseService<BaseKeywordUse>{

	/**
	* 以主鍵查询DTO
	*/
	BaseKeywordUseDto selectDtoByPrimaryKey(String keywordUseId);

	/**
	* 更新model返回DTO
	*/
	BaseKeywordUseDto updateDtoByPrimaryKeySelective(BaseKeywordUse baseKeywordUse);

	PageResult<BaseKeywordUseDto> pageListDto(PageObject pageObject);
	
	PageResult<BaseKeywordUseDto> pageListDtoJoin(PageObject pageObject);
	
	List<BaseKeywordUseDto> pageListCache(PageObject pageObject);

}