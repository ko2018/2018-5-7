package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseValueTypeDto;
import com.talent.front.entity.BaseValueType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：数据类型表类（Dao层）
 */
 @Mapper
public interface BaseValueTypeDao extends BaseDao<BaseValueType> {

	BaseValueTypeDto selectDtoByPrimaryKey(String valuetypeId);

	List<BaseValueTypeDto> pageListDto(PageObject pageObject);

}