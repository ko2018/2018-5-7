package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResearchCrfSnlDto;
import com.talent.front.entity.BaseResearchCrfSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板属性表类（Dao层）
 */
 @Mapper
public interface BaseResearchCrfSnlDao extends BaseDao<BaseResearchCrfSnl> {

	BaseResearchCrfSnlDto selectDtoByPrimaryKey(String id);

	List<BaseResearchCrfSnlDto> pageListDto(PageObject pageObject);

}