package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsTtestSampleDto;
import com.talent.front.entity.StaticsTtestSample;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验服务接口类
 */
public interface StaticsTtestSampleService extends BaseService<StaticsTtestSample>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsTtestSampleDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsTtestSampleDto updateDtoByPrimaryKeySelective(StaticsTtestSample staticsTtestSample);

	PageResult<StaticsTtestSampleDto> pageListDto(PageObject pageObject);
	
	PageResult<StaticsTtestSampleDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsTtestSampleDto> pageListCache(PageObject pageObject);

}