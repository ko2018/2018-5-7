package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResRatiotypeDto;
import com.talent.front.entity.BaseResRatiotype;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源类型类（Dao层）
 */
 @Mapper
public interface BaseResRatiotypeDao extends BaseDao<BaseResRatiotype> {

	BaseResRatiotypeDto selectDtoByPrimaryKey(String resratioTypeid);

	List<BaseResRatiotypeDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseResRatiotypeDto> pageListDtoJoin(PageObject pageObject);

}