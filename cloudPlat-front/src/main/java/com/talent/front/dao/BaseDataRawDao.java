package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDataRawDto;
import com.talent.front.entity.BaseDataRaw;
import com.talent.front.entity.BaseSearchDataRaw;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：原始数据表类（Dao层）
 */
@Mapper
public interface BaseDataRawDao extends BaseDao<BaseDataRaw> {

    BaseDataRawDto selectDtoByPrimaryKey(String datarawId);

    List<BaseDataRawDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseDataRawDto> pageListDtoJoin(PageObject pageObject);

    BaseDataRawDto getDtoByAllKey(@Param("userCode") String userCode, @Param("institutionId") String institutionId,
            @Param("checkCode") String checkCode, @Param("docId") String docId);

    List<BaseDataRawDto> selectDtoByOrganExamIDUserID(BaseSearchDataRaw baseSearchDataRaw);

}