package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseDocResultFileDto;
import com.talent.dcs.entity.BaseDoc;
import com.talent.dcs.entity.BaseDocResultFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-10 <br/>
 * 描述：智能诊断结果数据文件类（Dao层）
 */
@Mapper
public interface BaseDocResultFileDao extends BaseDao<BaseDocResultFile> {

	BaseDocResultFileDto selectDtoByPrimaryKey(String resultId);

	List<BaseDocResultFileDto> pageListDto(PageObject pageObject);

	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseDocResultFileDto> pageListDtoJoin(PageObject pageObject);

	int updatePersistence(BaseDocResultFile baseDocResultFile);

}