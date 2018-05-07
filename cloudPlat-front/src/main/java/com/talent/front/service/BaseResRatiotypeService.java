package com.talent.front.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.front.dto.BaseResRatiotypeDto;
import com.talent.front.entity.BaseResRatiotype;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源类型服务接口类
 */
public interface BaseResRatiotypeService extends BaseService<BaseResRatiotype> {

    /**
     * 以主鍵查询DTO
     */
    BaseResRatiotypeDto selectDtoByPrimaryKey(String resratioTypeid);

    /**
     * 更新model返回DTO
     */
    BaseResRatiotypeDto updateDtoByPrimaryKeySelective(BaseResRatiotype baseResRatiotype);

    PageResult<BaseResRatiotypeDto> pageListDto(PageObject pageObject);

    PageResult<BaseResRatiotypeDto> pageListDtoJoin(PageObject pageObject);

    List<BaseResRatiotypeDto> pageListCache(PageObject pageObject);

    void updateResRatiotypeDto(BaseResRatiotype baseResRatiotype);

}