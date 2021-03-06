package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDocTypeDto;
import com.talent.front.entity.BaseDocType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：文档类型类（Dao层）
 */
 @Mapper
public interface BaseDocTypeDao extends BaseDao<BaseDocType> {

	BaseDocTypeDto selectDtoByPrimaryKey(String doctypeId);

	List<BaseDocTypeDto> pageListDto(PageObject pageObject);

}