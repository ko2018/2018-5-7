package com.talent.dcs.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseDiseasesLogicDto;
import com.talent.dcs.entity.BaseDiseasesLogic;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：疾病诊断逻辑服务接口类
 */
public interface BaseDiseasesLogicService extends BaseService<BaseDiseasesLogic> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseDiseasesLogicDto selectDtoByPrimaryKey(String logicId);

	/**
	 * 更新model返回DTO
	 */
	BaseDiseasesLogicDto updateDtoByPrimaryKeySelective(BaseDiseasesLogic baseDiseasesLogic);

	PageResult<BaseDiseasesLogicDto> pageListDto(PageObject pageObject);

	List<BaseDiseasesLogicDto> pageListCache(PageObject pageObject);

	BaseDiseasesLogicDto changeDefault(BaseDiseasesLogicDto baseDiseasesLogicDto);

	BaseDiseasesLogicDto selectDefaultDtoByDiseasesId(String diseasesId);
}