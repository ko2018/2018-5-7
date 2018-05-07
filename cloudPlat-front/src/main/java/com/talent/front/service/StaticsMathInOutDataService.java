package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsMathInOutDataDto;
import com.talent.front.entity.StaticsMathInOutData;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-05 <br/>
 * 描述：数学分析服务接口类
 */
public interface StaticsMathInOutDataService extends BaseService<StaticsMathInOutData>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsMathInOutDataDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsMathInOutDataDto updateDtoByPrimaryKeySelective(StaticsMathInOutData staticsMathInOutData);

	PageResult<StaticsMathInOutDataDto> pageListDto(PageObject pageObject);
	
	PageResult<StaticsMathInOutDataDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsMathInOutDataDto> pageListCache(PageObject pageObject);

}