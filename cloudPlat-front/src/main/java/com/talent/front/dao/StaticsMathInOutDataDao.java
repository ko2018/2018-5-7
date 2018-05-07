package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.StaticsMathInOutDataDto;
import com.talent.front.entity.StaticsMathInOutData;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-05 <br/>
 * 描述：数学分析类（Dao层）
 */
 @Mapper
public interface StaticsMathInOutDataDao extends BaseDao<StaticsMathInOutData> {

	StaticsMathInOutDataDto selectDtoByPrimaryKey(String id);

	List<StaticsMathInOutDataDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<StaticsMathInOutDataDto> pageListDtoJoin(PageObject pageObject);

}