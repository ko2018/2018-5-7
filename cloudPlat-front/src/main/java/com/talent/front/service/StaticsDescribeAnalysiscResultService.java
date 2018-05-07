package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsDescribeAnalysiscResultDto;
import com.talent.front.entity.StaticsDescribeAnalysiscResult;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析结果服务接口类
 */
public interface StaticsDescribeAnalysiscResultService extends BaseService<StaticsDescribeAnalysiscResult>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsDescribeAnalysiscResultDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsDescribeAnalysiscResultDto updateDtoByPrimaryKeySelective(StaticsDescribeAnalysiscResult staticsDescribeAnalysiscResult);

	PageResult<StaticsDescribeAnalysiscResultDto> pageListDto(PageObject pageObject);
	
	PageResult<StaticsDescribeAnalysiscResultDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsDescribeAnalysiscResultDto> pageListCache(PageObject pageObject);

}