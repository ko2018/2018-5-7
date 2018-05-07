package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDocValueFileDto;
import com.talent.front.entity.BaseDocValueFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-20 <br/>
 * 描述：不合法修改文件表服务接口类
 */
public interface BaseDocValueFileService extends BaseService<BaseDocValueFile>{

	/**
	* 以主鍵查询DTO
	*/
	BaseDocValueFileDto selectDtoByPrimaryKey(String valuefileId);

	/**
	* 更新model返回DTO
	*/
	BaseDocValueFileDto updateDtoByPrimaryKeySelective(BaseDocValueFile baseDocValueFile);

	PageResult<BaseDocValueFileDto> pageListDto(PageObject pageObject);
	
	PageResult<BaseDocValueFileDto> pageListDtoJoin(PageObject pageObject);
	
	List<BaseDocValueFileDto> pageListCache(PageObject pageObject);

}