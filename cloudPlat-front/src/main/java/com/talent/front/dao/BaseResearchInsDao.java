package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResearchInsDto;
import com.talent.front.entity.BaseResearchIns;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题机构关系表类（Dao层）
 */
 @Mapper
public interface BaseResearchInsDao extends BaseDao<BaseResearchIns> {

	BaseResearchInsDto selectDtoByPrimaryKey(String id);

	List<BaseResearchInsDto> pageListDto(PageObject pageObject);
	
	int deleteByResearchId(String researchId);

}