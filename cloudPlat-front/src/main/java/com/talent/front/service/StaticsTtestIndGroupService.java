package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsTtestIndGroupDto;
import com.talent.front.entity.StaticsTtestIndGroup;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：独立T检验分组服务接口类
 */
public interface StaticsTtestIndGroupService extends BaseService<StaticsTtestIndGroup>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsTtestIndGroupDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsTtestIndGroupDto updateDtoByPrimaryKeySelective(StaticsTtestIndGroup staticsTtestIndGroup);

	PageResult<StaticsTtestIndGroupDto> pageListDto(PageObject pageObject);
	
	PageResult<StaticsTtestIndGroupDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsTtestIndGroupDto> pageListCache(PageObject pageObject);

}