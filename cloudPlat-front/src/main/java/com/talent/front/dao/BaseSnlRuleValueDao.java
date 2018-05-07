package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseSnlRuleValueDto;
import com.talent.front.entity.BaseSnlRuleValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：术语值规则类（Dao层）
 */
@Mapper
public interface BaseSnlRuleValueDao extends BaseDao<BaseSnlRuleValue> {

    BaseSnlRuleValueDto selectDtoByPrimaryKey(String valueId);

    List<BaseSnlRuleValueDto> pageListDto(PageObject pageObject);

    BaseSnlRuleValueDto getLegalValueDtoBySnlId(String snlId);

    BaseSnlRuleValueDto getDtoByDictId(int dictId);

    int replaceBaseSnlRuleValue(List<BaseSnlRuleValue> snlRuleValues);
}