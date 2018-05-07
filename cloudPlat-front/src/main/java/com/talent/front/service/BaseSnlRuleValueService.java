package com.talent.front.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.front.dto.BaseSnlRuleValueDto;
import com.talent.front.entity.BaseSnlRuleValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：术语值规则服务接口类
 */
public interface BaseSnlRuleValueService extends BaseService<BaseSnlRuleValue> {

    /**
     * 以主鍵查询DTO
     */
    BaseSnlRuleValueDto selectDtoByPrimaryKey(String valueId);

    /**
     * 更新model返回DTO
     */
    BaseSnlRuleValueDto updateDtoByPrimaryKeySelective(BaseSnlRuleValue baseSnlRuleValue);

    PageResult<BaseSnlRuleValueDto> pageListDto(PageObject pageObject);

    List<BaseSnlRuleValueDto> pageListCache(PageObject pageObject);

    BaseSnlRuleValueDto getDtoByDictId(int dictId);

    int replaceBaseSnlRuleValue(List<BaseSnlRuleValue> snlRuleValues);

}