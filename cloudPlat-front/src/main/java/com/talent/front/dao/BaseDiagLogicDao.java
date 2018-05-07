package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseDiagLogicDto;
import com.talent.front.entity.BaseDiagLogic;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断逻辑类（Dao层）
 */
@Mapper
public interface BaseDiagLogicDao extends BaseDao<BaseDiagLogic> {

    BaseDiagLogicDto selectDtoByPrimaryKey(String diagLogicId);

    List<BaseDiagLogicDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<BaseDiagLogicDto> pageListDtoJoin(PageObject pageObject);

    int deleteByLogicId(@Param("logicId") String logicId, @Param("updateUser") String updateUser);

    List<BaseDiagLogicDto> getLogicsByLogicId(@Param("logicId") String logicId);
}