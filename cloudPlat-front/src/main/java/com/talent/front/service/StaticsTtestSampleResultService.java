package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsTtestSampleResultDto;
import com.talent.front.entity.StaticsTtestSampleResult;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验的结果服务接口类
 */
public interface StaticsTtestSampleResultService extends BaseService<StaticsTtestSampleResult>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsTtestSampleResultDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsTtestSampleResultDto updateDtoByPrimaryKeySelective(StaticsTtestSampleResult staticsTtestSampleResult);

	PageResult<StaticsTtestSampleResultDto> pageListDto(PageObject pageObject);
	
	PageResult<StaticsTtestSampleResultDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsTtestSampleResultDto> pageListCache(PageObject pageObject);

}