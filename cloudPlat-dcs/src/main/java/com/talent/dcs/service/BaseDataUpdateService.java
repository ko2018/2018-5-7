package com.talent.dcs.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseDataUpdateDto;
import com.talent.dcs.entity.BaseDataStd;
import com.talent.dcs.entity.BaseDataUpdate;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：数据更新表服务接口类
 */
public interface BaseDataUpdateService extends BaseService<BaseDataUpdate> {

    /**
     * 以主鍵查询DTO
     */
    BaseDataUpdateDto selectDtoByPrimaryKey(String datastdId);

    /**
     * 更新model返回DTO
     */
    BaseDataUpdateDto updateDtoByPrimaryKeySelective(BaseDataUpdate baseDataUpdate);

    PageResult<BaseDataUpdateDto> pageListDto(PageObject pageObject);

    PageResult<BaseDataUpdateDto> pageListDtoJoin(PageObject pageObject);

    List<BaseDataUpdateDto> pageListCache(PageObject pageObject);

    void addBaseDataUpdate(BaseDataStd baseDataStd, int dictId);

    void addBaseDataUpdates(List<BaseDataStd> list, int dictId);

}