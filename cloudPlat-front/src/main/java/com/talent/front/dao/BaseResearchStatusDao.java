package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResearchStatusDto;
import com.talent.front.entity.BaseResearchStatus;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题状态表类（Dao层）
 */
 @Mapper
public interface BaseResearchStatusDao extends BaseDao<BaseResearchStatus> {

	BaseResearchStatusDto selectDtoByPrimaryKey(String id);

	List<BaseResearchStatusDto> pageListDto(PageObject pageObject);
	
	Integer getResearchCurrentSeq(String researchId);

}