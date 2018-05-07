package com.talent.dcs.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseInstitutionSnlVersionDto;
import com.talent.dcs.entity.BaseInstitutionSnlVersion;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：机构标准术语版本类（Dao层）
 */
 @Mapper
public interface BaseInstitutionSnlVersionDao extends BaseDao<BaseInstitutionSnlVersion> {

	BaseInstitutionSnlVersionDto selectDtoByPrimaryKey(String institutionSnlVersionId);

	List<BaseInstitutionSnlVersionDto> pageListDto(PageObject pageObject);

}