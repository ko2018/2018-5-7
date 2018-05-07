package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseDocDefValueDto;
import com.talent.dcs.entity.BaseDocDefValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-03 <br/>
 * 描述：文档值通用不合法值记录类（Dao层）
 */
@Mapper
public interface BaseDocDefValueDao extends BaseDao<BaseDocDefValue> {

    BaseDocDefValueDto selectDtoByPrimaryKey(String docdefvalueId);

    List<BaseDocDefValueDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseDocDefValueDto> pageListDtoJoin(PageObject pageObject);

    BaseDocDefValueDto getDtoByDictIdAndDocId(@Param("dictId") int dictId, @Param("docId") String docId,
            @Param("docdefvalueValue") String docvalueValue);

}