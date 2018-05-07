package com.talent.dcs.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseInstitutionSnlDto;
import com.talent.dcs.entity.BaseInstitutionSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：机构标准术语对应表类（Dao层）
 */
 @Mapper
public interface BaseInstitutionSnlDao extends BaseDao<BaseInstitutionSnl> {

	BaseInstitutionSnlDto selectDtoByPrimaryKey(String institutionSnlId);

	List<BaseInstitutionSnlDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseInstitutionSnlDto> pageListDtoJoin(PageObject pageObject);

}