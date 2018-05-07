package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResRatioDto;
import com.talent.front.entity.BaseResRatio;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源占比类（Dao层）
 */
@Mapper
public interface BaseResRatioDao extends BaseDao<BaseResRatio> {

    BaseResRatioDto selectDtoByPrimaryKey(String resratioId);

    List<BaseResRatioDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseResRatioDto> pageListDtoJoin(PageObject pageObject);

    int updateByRatioTypeId(@Param("resRatioTypeId") String resRatioTypeId,
            @Param("resRatioTypeName") String resRatioTypeName);

}