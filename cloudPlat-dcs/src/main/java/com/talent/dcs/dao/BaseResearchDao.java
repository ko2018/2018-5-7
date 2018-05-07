package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseResearchDto;
import com.talent.dcs.entity.BaseResearch;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-05 <br/>
 * 描述：科研管理表（课题表）类（Dao层）
 */
@Mapper
public interface BaseResearchDao extends BaseDao<BaseResearch> {

    BaseResearchDto selectDtoByPrimaryKey(String researchId);

    List<BaseResearchDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseResearchDto> pageListDtoJoin(PageObject pageObject);

}