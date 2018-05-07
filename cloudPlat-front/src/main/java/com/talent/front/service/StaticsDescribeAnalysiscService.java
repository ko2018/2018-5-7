package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsDescribeAnalysiscDto;
import com.talent.front.entity.StaticsDescribeAnalysisc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析服务接口类
 */
public interface StaticsDescribeAnalysiscService extends BaseService<StaticsDescribeAnalysisc>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsDescribeAnalysiscDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsDescribeAnalysiscDto updateDtoByPrimaryKeySelective(StaticsDescribeAnalysisc staticsDescribeAnalysisc);

	PageResult<StaticsDescribeAnalysiscDto> pageListDto(PageObject pageObject);
	
	PageResult<StaticsDescribeAnalysiscDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsDescribeAnalysiscDto> pageListCache(PageObject pageObject);

}