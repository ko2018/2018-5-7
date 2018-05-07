package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseDocLegalDto;
import com.talent.dcs.entity.BaseDocLegal;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：文档空值合法值记录表类（Dao层）
 */
@Mapper
public interface BaseDocLegalDao extends BaseDao<BaseDocLegal> {

	BaseDocLegalDto selectDtoByPrimaryKey(String docvalueId);

	List<BaseDocLegalDto> pageListDto(PageObject pageObject);

	BaseDocLegalDto getDtoByDictIdAndDocId(@Param("dictId") int dictId, @Param("docId") String docId);

	void updateByCountAll(@Param("countAll") int countAll, @Param("docId") String docId);
	
	void updateCount(@Param("docId") String docId);

}