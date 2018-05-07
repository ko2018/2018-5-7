package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsTtestIndResultDto;
import com.talent.front.entity.StaticsTtestIndResult;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述： 独立T检验结果服务接口类
 */
public interface StaticsTtestIndResultService extends BaseService<StaticsTtestIndResult>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsTtestIndResultDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsTtestIndResultDto updateDtoByPrimaryKeySelective(StaticsTtestIndResult staticsTtestIndResult);

	PageResult<StaticsTtestIndResultDto> pageListDto(PageObject pageObject);
	
	PageResult<StaticsTtestIndResultDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsTtestIndResultDto> pageListCache(PageObject pageObject);

}