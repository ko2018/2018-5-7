package com.talent.front.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.AreaDto;
import com.talent.front.entity.Area;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：省市区类（Dao层）
 */
@Mapper
public interface AreaDao extends BaseDao<Area> {

	AreaDto selectDtoByPrimaryKey(String areaId);

	List<AreaDto> pageListDto(PageObject pageObject);

	List<AreaDto> selectDtoByParentId(String id);

	List<AreaDto> selectDtoByParentIdLevel(Map<String, Object> map);

	AreaDto selectAreaDto(Map<String, Object> paramMap);

}