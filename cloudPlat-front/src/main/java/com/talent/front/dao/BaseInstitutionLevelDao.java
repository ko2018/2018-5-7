package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseInstitutionLevelDto;
import com.talent.front.entity.BaseInstitutionLevel;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-08 <br/>
 * 描述：机构级别类（Dao层）
 */
 @Mapper
public interface BaseInstitutionLevelDao extends BaseDao<BaseInstitutionLevel> {

	BaseInstitutionLevelDto selectDtoByPrimaryKey(String institutionlevelId);

	List<BaseInstitutionLevelDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseInstitutionLevelDto> pageListDtoJoin(PageObject pageObject);

}