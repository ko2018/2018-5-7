package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.StaticsTtestSampleDto;
import com.talent.front.entity.StaticsTtestSample;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验类（Dao层）
 */
 @Mapper
public interface StaticsTtestSampleDao extends BaseDao<StaticsTtestSample> {

	StaticsTtestSampleDto selectDtoByPrimaryKey(String id);

	List<StaticsTtestSampleDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<StaticsTtestSampleDto> pageListDtoJoin(PageObject pageObject);

}