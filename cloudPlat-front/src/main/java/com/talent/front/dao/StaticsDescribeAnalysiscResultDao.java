package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.StaticsDescribeAnalysiscResultDto;
import com.talent.front.entity.StaticsDescribeAnalysiscResult;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析结果类（Dao层）
 */
 @Mapper
public interface StaticsDescribeAnalysiscResultDao extends BaseDao<StaticsDescribeAnalysiscResult> {

	StaticsDescribeAnalysiscResultDto selectDtoByPrimaryKey(String id);

	List<StaticsDescribeAnalysiscResultDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<StaticsDescribeAnalysiscResultDto> pageListDtoJoin(PageObject pageObject);

}