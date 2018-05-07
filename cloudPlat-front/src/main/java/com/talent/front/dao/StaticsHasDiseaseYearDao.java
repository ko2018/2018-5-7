package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.StaticsHasDiseaseYearDto;
import com.talent.front.entity.StaticsHasDiseaseYear;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：统计原始数据每年患病情况类（Dao层）
 */
 @Mapper
public interface StaticsHasDiseaseYearDao extends BaseDao<StaticsHasDiseaseYear> {

	StaticsHasDiseaseYearDto selectDtoByPrimaryKey(String staticsSid);

	List<StaticsHasDiseaseYearDto> pageListDto(PageObject pageObject);

}