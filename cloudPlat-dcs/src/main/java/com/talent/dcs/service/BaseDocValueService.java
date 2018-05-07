package com.talent.dcs.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseDocValueDto;
import com.talent.dcs.entity.BaseDocValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-17 <br/>
 * 描述：文档值显示记录表服务接口类
 */
public interface BaseDocValueService extends BaseService<BaseDocValue> {

    /**
     * 以主鍵查询DTO
     */
    BaseDocValueDto selectDtoByPrimaryKey(String docvalueId);

    /**
     * 更新model返回DTO
     */
    BaseDocValueDto updateDtoByPrimaryKeySelective(BaseDocValue baseDocValue);

    PageResult<BaseDocValueDto> pageListDto(PageObject pageObject);

    PageResult<BaseDocValueDto> pageListDtoJoin(PageObject pageObject);

    List<BaseDocValueDto> pageListCache(PageObject pageObject);

    void change(String docvalueId, String docId, int dictId, String docvalueValue, String docvalueValuenew);

}