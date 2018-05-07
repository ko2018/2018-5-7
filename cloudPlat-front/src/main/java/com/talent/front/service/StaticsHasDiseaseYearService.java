package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsHasDiseaseYearDto;
import com.talent.front.entity.StaticsHasDiseaseYear;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：统计原始数据每年患病情况服务接口类
 */
public interface StaticsHasDiseaseYearService extends BaseService<StaticsHasDiseaseYear>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsHasDiseaseYearDto selectDtoByPrimaryKey(String staticsSid);

	/**
	* 更新model返回DTO
	*/
	StaticsHasDiseaseYearDto updateDtoByPrimaryKeySelective(StaticsHasDiseaseYear staticsHasDiseaseYear);

	PageResult<StaticsHasDiseaseYearDto> pageListDto(PageObject pageObject);
	
	List<StaticsHasDiseaseYearDto> pageListCache(PageObject pageObject);

}