package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseFileDto;
import com.talent.front.entity.BaseFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息附件表服务接口类
 */
public interface BaseFileService extends BaseService<BaseFile> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseFileDto selectDtoByPrimaryKey(String fileId);

	/**
	 * 更新model返回DTO
	 */
	BaseFileDto updateDtoByPrimaryKeySelective(BaseFile baseFile);

	PageResult<BaseFileDto> pageListDto(PageObject pageObject);

	List<BaseFileDto> pageListCache(PageObject pageObject);

	long getAllCount(PageObject pageObject);
	
	List<BaseFileDto> getBaseFilesByIds(List<String> fileIds);

}