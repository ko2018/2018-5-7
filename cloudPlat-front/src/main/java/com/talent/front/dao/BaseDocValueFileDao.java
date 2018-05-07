package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDocValueFileDto;
import com.talent.front.entity.BaseDocValueFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-20 <br/>
 * 描述：不合法修改文件表类（Dao层）
 */
 @Mapper
public interface BaseDocValueFileDao extends BaseDao<BaseDocValueFile> {

	BaseDocValueFileDto selectDtoByPrimaryKey(String valuefileId);

	List<BaseDocValueFileDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseDocValueFileDto> pageListDtoJoin(PageObject pageObject);

}