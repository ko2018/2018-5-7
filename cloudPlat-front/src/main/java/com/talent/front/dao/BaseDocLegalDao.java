package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDocLegalDto;
import com.talent.front.entity.BaseDocLegal;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：文档空值合法值记录表类（Dao层）
 */
@Mapper
public interface BaseDocLegalDao extends BaseDao<BaseDocLegal> {

    BaseDocLegalDto selectDtoByPrimaryKey(String docvalueId);

    List<BaseDocLegalDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseDocLegalDto> pageListDtoJoin(PageObject pageObject);

    BaseDocLegalDto getDtoByDictIdAndDocId(@Param("dictId") int dictId, @Param("docId") String docId);

    void updateByCountAll(@Param("countAll") int countAll, @Param("docId") String docId);

    void updateByCountChanged(@Param("countChanged") int countChanged, @Param("dictId") int dictId,
            @Param("docId") String docId);

}