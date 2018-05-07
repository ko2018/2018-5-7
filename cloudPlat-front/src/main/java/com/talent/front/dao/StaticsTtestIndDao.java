package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.StaticsTtestIndDto;
import com.talent.front.entity.StaticsTtestInd;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述： 独立T检验类（Dao层）
 */
 @Mapper
public interface StaticsTtestIndDao extends BaseDao<StaticsTtestInd> {

	StaticsTtestIndDto selectDtoByPrimaryKey(String id);

	List<StaticsTtestIndDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<StaticsTtestIndDto> pageListDtoJoin(PageObject pageObject);

}