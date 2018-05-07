package com.talent.dcs.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseDocDefValueDto;
import com.talent.dcs.entity.BaseDocDefValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-03 <br/>
 * 描述：文档值通用不合法值记录服务接口类
 */
public interface BaseDocDefValueService extends BaseService<BaseDocDefValue> {

    /**
     * 以主鍵查询DTO
     */
    BaseDocDefValueDto selectDtoByPrimaryKey(String docdefvalueId);

    /**
     * 更新model返回DTO
     */
    BaseDocDefValueDto updateDtoByPrimaryKeySelective(BaseDocDefValue baseDocDefValue);

    PageResult<BaseDocDefValueDto> pageListDto(PageObject pageObject);

    PageResult<BaseDocDefValueDto> pageListDtoJoin(PageObject pageObject);

    List<BaseDocDefValueDto> pageListCache(PageObject pageObject);

}