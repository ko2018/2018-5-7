package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResearchDto;
import com.talent.front.entity.BaseResearch;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研管理表类（Dao层）
 */
 @Mapper
public interface BaseResearchDao extends BaseDao<BaseResearch> {

	BaseResearchDto selectDtoByPrimaryKey(String researchId);

	List<BaseResearchDto> pageListDto(PageObject pageObject);

}