package com.talent.dcs.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseSnlDto;
import com.talent.dcs.entity.BaseSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：标准术语表类（Dao层）
 */
 @Mapper
public interface BaseSnlDao extends BaseDao<BaseSnl> {

	BaseSnlDto selectDtoByPrimaryKey(String snlId);

	List<BaseSnlDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseSnlDto> pageListDtoJoin(PageObject pageObject);

}