package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseKeywordDto;
import com.talent.front.entity.BaseKeyword;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-26 <br/>
 * 描述：关键字表类（Dao层）
 */
@Mapper
public interface BaseKeywordDao extends BaseDao<BaseKeyword> {

    BaseKeywordDto selectDtoByPrimaryKey(String keywordId);

    List<BaseKeywordDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseKeywordDto> pageListDtoJoin(PageObject pageObject);

    List<BaseKeywordDto> getKeywordByIds(List<String> keywordIds);

}