package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.StaticsDescribeAnalysiscDto;
import com.talent.front.entity.StaticsDescribeAnalysisc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析类（Dao层）
 */
 @Mapper
public interface StaticsDescribeAnalysiscDao extends BaseDao<StaticsDescribeAnalysisc> {

	StaticsDescribeAnalysiscDto selectDtoByPrimaryKey(String id);

	List<StaticsDescribeAnalysiscDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<StaticsDescribeAnalysiscDto> pageListDtoJoin(PageObject pageObject);

}