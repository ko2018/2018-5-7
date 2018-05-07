package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseInstitutionLevelDto;
import com.talent.front.entity.BaseInstitutionLevel;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-08 <br/>
 * 描述：机构级别服务接口类
 */
public interface BaseInstitutionLevelService extends BaseService<BaseInstitutionLevel>{

	/**
	* 以主鍵查询DTO
	*/
	BaseInstitutionLevelDto selectDtoByPrimaryKey(String institutionlevelId);

	/**
	* 更新model返回DTO
	*/
	BaseInstitutionLevelDto updateDtoByPrimaryKeySelective(BaseInstitutionLevel baseInstitutionLevel);

	PageResult<BaseInstitutionLevelDto> pageListDto(PageObject pageObject);
	
	PageResult<BaseInstitutionLevelDto> pageListDtoJoin(PageObject pageObject);
	
	List<BaseInstitutionLevelDto> pageListCache(PageObject pageObject);

}