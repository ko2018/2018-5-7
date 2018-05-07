package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseDiagDto;
import com.talent.dcs.entity.BaseDiag;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：诊断病情表类（Dao层）
 */
@Mapper
public interface BaseDiagDao extends BaseDao<BaseDiag> {

	BaseDiagDto selectDtoByPrimaryKey(String recordId);

	List<BaseDiagDto> pageListDto(PageObject pageObject);

	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseDiagDto> pageListDtoJoin(PageObject pageObject);

	BaseDiagDto getDtoByDataIdAndDiseasesId(@Param("dataId") String dataId, @Param("diseasesId") String diseasesId);

}