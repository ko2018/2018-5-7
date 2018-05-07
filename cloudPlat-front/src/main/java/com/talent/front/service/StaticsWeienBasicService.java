package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsWeienBasicDto;
import com.talent.front.entity.StaticsSearchCondition;
import com.talent.front.entity.StaticsSearchResultSet;
import com.talent.front.entity.StaticsWeienBasic;
import com.talent.front.entity.StaticsweienSearchResultSet;
import com.talent.front.entity.StaticsWeienSearchCondition;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-12 <br/>
 * 描述：服务接口类
 */
public interface StaticsWeienBasicService extends BaseService<StaticsWeienBasic>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsWeienBasicDto selectDtoByPrimaryKey(String id);

	/**
	* 更新model返回DTO
	*/
	StaticsWeienBasicDto updateDtoByPrimaryKeySelective(StaticsWeienBasic staticsWeienBasic);

	PageResult<StaticsWeienBasicDto> pageListDto(PageObject pageObject);
	List<StaticsweienSearchResultSet> scanweienResultSet(StaticsWeienSearchCondition condition);
	PageResult<StaticsWeienBasicDto> pageListDtoJoin(PageObject pageObject);
	
	List<StaticsWeienBasicDto> pageListCache(PageObject pageObject);
	
	/**
	 * 统计患每种病的总数
	 * @param condition
	 * @return
	 */
	List<StaticsweienSearchResultSet> countDis(StaticsWeienSearchCondition condition);
}