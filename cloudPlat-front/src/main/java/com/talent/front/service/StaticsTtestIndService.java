package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsTtestIndDto;
import com.talent.front.entity.StaticsTtestInd;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述： 独立T检验服务接口类
 */
public interface StaticsTtestIndService extends BaseService<StaticsTtestInd>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsTtestIndDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsTtestIndDto updateDtoByPrimaryKeySelective(StaticsTtestInd staticsTtestInd);

	PageResult<StaticsTtestIndDto> pageListDto(PageObject pageObject);
	
	PageResult<StaticsTtestIndDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsTtestIndDto> pageListCache(PageObject pageObject);

}