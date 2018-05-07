package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseDataCleanDto;
import com.talent.dcs.entity.BaseDataClean;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：清洗数据表类（Dao层）
 */
@Mapper
public interface BaseDataCleanDao extends BaseDao<BaseDataClean> {

	BaseDataCleanDto selectDtoByPrimaryKey(String datacleanId);

	List<BaseDataCleanDto> pageListDto(PageObject pageObject);

	BaseDataCleanDto getDtoByAllKey(@Param("userCode") String userCode, @Param("institutionId") String institutionId,
			@Param("checkCode") String checkCode, @Param("docId") String docId);
	
}