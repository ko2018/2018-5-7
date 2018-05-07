package com.talent.dcs.service;

import java.util.List;
import java.util.Map;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseSnlDto;
import com.talent.dcs.entity.BaseSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：标准术语表服务接口类
 */
public interface BaseSnlService extends BaseService<BaseSnl> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseSnlDto selectDtoByPrimaryKey(String snlId);

	/**
	 * 更新model返回DTO
	 */
	BaseSnlDto updateDtoByPrimaryKeySelective(BaseSnl baseSnl);

	PageResult<BaseSnlDto> pageListDto(PageObject pageObject);

	PageResult<BaseSnlDto> pageListDtoJoin(PageObject pageObject);

	List<BaseSnlDto> pageListCache(PageObject pageObject);

	Map<String, BaseSnl> getMapByNameObject();

	Map<Integer, BaseSnl> getMapByDictIdObject();

}