package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDocTypeDto;
import com.talent.front.entity.BaseDocType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：文档类型服务接口类
 */
public interface BaseDocTypeService extends BaseService<BaseDocType>{

	/**
	* 以主鍵查询DTO
	*/
	BaseDocTypeDto selectDtoByPrimaryKey(String doctypeId);

	/**
	* 更新model返回DTO
	*/
	BaseDocTypeDto updateDtoByPrimaryKeySelective(BaseDocType baseDocType);

	PageResult<BaseDocTypeDto> pageListDto(PageObject pageObject);
	
	List<BaseDocTypeDto> pageListCache(PageObject pageObject);

}