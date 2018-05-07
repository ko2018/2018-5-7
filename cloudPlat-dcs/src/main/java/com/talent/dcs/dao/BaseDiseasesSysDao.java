package com.talent.dcs.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseDiseasesSysDto;
import com.talent.dcs.entity.BaseDiseasesSys;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：知识库表(疾病表)类（Dao层）
 */
 @Mapper
public interface BaseDiseasesSysDao extends BaseDao<BaseDiseasesSys> {

	BaseDiseasesSysDto selectDtoByPrimaryKey(String diseasesId);

	List<BaseDiseasesSysDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseDiseasesSysDto> pageListDtoJoin(PageObject pageObject);

}