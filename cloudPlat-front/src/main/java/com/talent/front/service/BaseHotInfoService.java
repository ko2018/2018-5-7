package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseHotInfoDto;
import com.talent.front.entity.BaseHotInfo;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息表服务接口类
 */
public interface BaseHotInfoService extends BaseService<BaseHotInfo>{

	/**
	* 以主鍵查询DTO
	*/
	BaseHotInfoDto selectDtoByPrimaryKey(String hotInfoId);

	/**
	* 更新model返回DTO
	*/
	BaseHotInfoDto updateDtoByPrimaryKeySelective(BaseHotInfo baseHotInfo);

	PageResult<BaseHotInfoDto> pageListDto(PageObject pageObject);
	
	List<BaseHotInfoDto> pageListCache(PageObject pageObject);
	
	List<BaseHotInfo> selectDtoByIds(List<String> ids);
	
	int batchUpdateSelective(List<BaseHotInfo> baseHotInfos);
}