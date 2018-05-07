package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseKeywordUseDto;
import com.talent.front.entity.BaseKeywordUse;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-01 <br/>
 * 描述：关键字应用表类（Dao层）
 */
@Mapper
public interface BaseKeywordUseDao extends BaseDao<BaseKeywordUse> {

    BaseKeywordUseDto selectDtoByPrimaryKey(String keywordUseId);

    List<BaseKeywordUseDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseKeywordUseDto> pageListDtoJoin(PageObject pageObject);

    List<BaseKeywordUse> selectBaseKeywordUseByKeywordId(String keywordId);

}