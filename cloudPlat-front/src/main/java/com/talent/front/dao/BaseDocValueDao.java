package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDocValueDto;
import com.talent.front.entity.BaseDocValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：文档值显示记录表类（Dao层）
 */
@Mapper
public interface BaseDocValueDao extends BaseDao<BaseDocValue> {

    BaseDocValueDto selectDtoByPrimaryKey(String docvalueId);

    List<BaseDocValueDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseDocValueDto> pageListDtoJoin(PageObject pageObject);

    long pageCountDtoJoinGroupBy(PageObject pageObject);

    List<BaseDocValueDto> pageListDtoJoinGroupBy(PageObject pageObject);

    BaseDocValueDto getDtoByDictIdAndDocId(@Param("dictId") int dictId, @Param("docId") String docId,
            @Param("docvalueValue") String docvalueValue);

    int deleteByDocId(String docId);

    List<BaseDocValueDto> pageListDtoJoinSpe(@Param("docvalueValues") List<String> docvalueValues,
            @Param("docId") String docId, @Param("dictId") int dictId);

    List<BaseDocValueDto> graph(@Param("floor") int floor, @Param("docId") String docId, @Param("dictId") int dictId);

}