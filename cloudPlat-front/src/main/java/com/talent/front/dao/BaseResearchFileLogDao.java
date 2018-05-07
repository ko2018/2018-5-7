package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResearchFileLogDto;
import com.talent.front.entity.BaseResearchFileLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档操作日志表类（Dao层）
 */
 @Mapper
public interface BaseResearchFileLogDao extends BaseDao<BaseResearchFileLog> {

	BaseResearchFileLogDto selectDtoByPrimaryKey(String logId);

	List<BaseResearchFileLogDto> pageListDto(PageObject pageObject);

}