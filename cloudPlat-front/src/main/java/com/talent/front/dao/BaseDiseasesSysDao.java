package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDiseasesSysDto;
import com.talent.front.entity.BaseDiseasesSys;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：知识库表类（Dao层）
 */
@Mapper
public interface BaseDiseasesSysDao extends BaseDao<BaseDiseasesSys> {

    BaseDiseasesSysDto selectDtoByPrimaryKey(String diseasesId);

    List<BaseDiseasesSysDto> pageListDto(PageObject pageObject);

    List<BaseDiseasesSysDto> getDtoListByIds(List<String> diseasesIds);

}