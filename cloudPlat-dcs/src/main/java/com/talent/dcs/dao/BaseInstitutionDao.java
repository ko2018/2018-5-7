package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseInstitutionDto;
import com.talent.dcs.entity.BaseInstitution;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-01 <br/>
 * 描述：机构表类（Dao层）
 */
@Mapper
public interface BaseInstitutionDao extends BaseDao<BaseInstitution> {

    BaseInstitutionDto selectDtoByPrimaryKey(String institutionId);

    List<BaseInstitutionDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseInstitutionDto> pageListDtoJoin(PageObject pageObject);

}