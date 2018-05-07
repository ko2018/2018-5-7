package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.SysLogDto;
import com.talent.front.entity.SysLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：系统日志操作表类（Dao层）
 */
 @Mapper
public interface SysLogDao extends BaseDao<SysLog> {

	SysLogDto selectDtoByPrimaryKey(String syslogId);

	List<SysLogDto> pageListDto(PageObject pageObject);

}