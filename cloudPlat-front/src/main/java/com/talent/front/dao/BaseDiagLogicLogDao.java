package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDiagLogicLogDto;
import com.talent.front.entity.BaseDiagLogicLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断逻辑日志类（Dao层）
 */
 @Mapper
public interface BaseDiagLogicLogDao extends BaseDao<BaseDiagLogicLog> {

	BaseDiagLogicLogDto selectDtoByPrimaryKey(String logId);

	List<BaseDiagLogicLogDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseDiagLogicLogDto> pageListDtoJoin(PageObject pageObject);

}