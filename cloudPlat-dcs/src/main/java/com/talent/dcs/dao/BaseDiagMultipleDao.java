package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseDiagMultipleDto;
import com.talent.dcs.entity.BaseDiagMultiple;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：综合诊断表类（Dao层）
 */
@Mapper
public interface BaseDiagMultipleDao extends BaseDao<BaseDiagMultiple> {

	BaseDiagMultipleDto selectDtoByPrimaryKey(String recordId);

	List<BaseDiagMultipleDto> pageListDto(PageObject pageObject);

	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseDiagMultipleDto> pageListDtoJoin(PageObject pageObject);

	BaseDiagMultipleDto getDtoByDataId(@Param("dataId") String dataId);

	int resetEsFlag(String logicId, String userId);

}