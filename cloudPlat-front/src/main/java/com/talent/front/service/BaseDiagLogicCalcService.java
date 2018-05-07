package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDiagLogicCalcDto;
import com.talent.front.entity.BaseDiagLogicCalc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：逻辑运算关系服务接口类
 */
public interface BaseDiagLogicCalcService extends BaseService<BaseDiagLogicCalc>{

	/**
	* 以主鍵查询DTO
	*/
	BaseDiagLogicCalcDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	BaseDiagLogicCalcDto updateDtoByPrimaryKeySelective(BaseDiagLogicCalc baseDiagLogicCalc);

	PageResult<BaseDiagLogicCalcDto> pageListDto(PageObject pageObject);
	
	PageResult<BaseDiagLogicCalcDto> pageListDtoJoin(PageObject pageObject);
	
	List<BaseDiagLogicCalcDto> pageListCache(PageObject pageObject);

}