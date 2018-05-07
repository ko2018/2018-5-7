package com.talent.front.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.talent.base.service.BaseService;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseInstitutionSnlDataDto;
import com.talent.front.dto.BaseInstitutionSnlDto;
import com.talent.front.dto.BaseInstitutionSnlVersionDto;
import com.talent.front.entity.BaseInstitutionSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：机构标准术语对应表服务接口类
 */
public interface BaseInstitutionSnlService extends BaseService<BaseInstitutionSnl> {

	/**
	 * 以主鍵查询DTO
	 */
	BaseInstitutionSnlDto selectDtoByPrimaryKey(String institutionSnlId);

	/**
	 * 更新model返回DTO
	 */
	BaseInstitutionSnlDto updateDtoByPrimaryKeySelective(BaseInstitutionSnl baseInstitutionSnl);

	PageResult<BaseInstitutionSnlDto> pageListDto(PageObject pageObject);

	PageResult<BaseInstitutionSnlDto> pageListDtoJoin(PageObject pageObject);

	List<BaseInstitutionSnlDto> pageListCache(PageObject pageObject);

	int updateRelationCanel(int dictId, String institutionSnlVersionId);

	void updateRelation(int dictId, String institutionSnlVersionId, String institutionSnlIds) throws Exception;

	BaseInstitutionSnlDataDto statistics(String institutionSnlVersionId);

	void relationAll(String institutionSnlVersionId) throws Exception;

}