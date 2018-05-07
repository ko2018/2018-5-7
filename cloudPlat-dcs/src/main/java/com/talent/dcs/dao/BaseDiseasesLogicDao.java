package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseDiseasesLogicDto;
import com.talent.dcs.entity.BaseDiseasesLogic;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：疾病诊断逻辑类（Dao层）
 */
 @Mapper
public interface BaseDiseasesLogicDao extends BaseDao<BaseDiseasesLogic> {

	BaseDiseasesLogicDto selectDtoByPrimaryKey(String logicId);

	List<BaseDiseasesLogicDto> pageListDto(PageObject pageObject);
	
	int changeDefault(String diseasesId, String userId, int isDefault);
	
	BaseDiseasesLogicDto selectDefaultDtoByDiseasesId(String diseasesId);

}