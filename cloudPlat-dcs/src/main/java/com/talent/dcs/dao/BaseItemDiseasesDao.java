package com.talent.dcs.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseItemDiseasesDto;
import com.talent.dcs.entity.BaseItemDiseases;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：疾病数据项表类（Dao层）
 */
 @Mapper
public interface BaseItemDiseasesDao extends BaseDao<BaseItemDiseases> {

	BaseItemDiseasesDto selectDtoByPrimaryKey(String id);

	List<BaseItemDiseasesDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseItemDiseasesDto> pageListDtoJoin(PageObject pageObject);

}