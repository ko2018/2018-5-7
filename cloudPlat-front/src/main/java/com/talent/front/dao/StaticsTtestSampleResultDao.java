package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.StaticsTtestSampleResultDto;
import com.talent.front.entity.StaticsTtestSampleResult;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验的结果类（Dao层）
 */
 @Mapper
public interface StaticsTtestSampleResultDao extends BaseDao<StaticsTtestSampleResult> {

	StaticsTtestSampleResultDto selectDtoByPrimaryKey(String id);

	List<StaticsTtestSampleResultDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<StaticsTtestSampleResultDto> pageListDtoJoin(PageObject pageObject);

}