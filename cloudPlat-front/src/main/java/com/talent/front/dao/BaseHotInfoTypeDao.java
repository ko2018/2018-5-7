package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseHotInfoTypeDto;
import com.talent.front.entity.BaseHotInfoType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息类型表类（Dao层）
 */
 @Mapper
public interface BaseHotInfoTypeDao extends BaseDao<BaseHotInfoType> {

	BaseHotInfoTypeDto selectDtoByPrimaryKey(String infoTypeId);

	List<BaseHotInfoTypeDto> pageListDto(PageObject pageObject);

}