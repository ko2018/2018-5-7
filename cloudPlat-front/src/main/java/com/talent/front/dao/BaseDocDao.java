package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDocDto;
import com.talent.front.entity.BaseDoc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：文档表类（Dao层）
 */
@Mapper
public interface BaseDocDao extends BaseDao<BaseDoc> {

    BaseDocDto selectDtoByPrimaryKey(String docId);

    List<BaseDocDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseDocDto> pageListDtoJoin(PageObject pageObject);

    int deleteAll(String docId);
}