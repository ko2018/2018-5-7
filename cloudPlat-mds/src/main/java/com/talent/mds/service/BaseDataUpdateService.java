package com.talent.mds.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.mds.dto.BaseDataUpdateDto;
import com.talent.mds.entity.BaseDataUpdate;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-22 <br/>
 * 描述：数据更新表服务接口类
 */
public interface BaseDataUpdateService extends BaseService<BaseDataUpdate> {

    /**
     * 以主鍵查询DTO
     */
    BaseDataUpdateDto selectDtoByPrimaryKey(String dataupdateId);

    /**
     * 更新model返回DTO
     */
    BaseDataUpdateDto updateDtoByPrimaryKeySelective(BaseDataUpdate baseDataUpdate);

    PageResult<BaseDataUpdateDto> pageListDto(PageObject pageObject);

    PageResult<BaseDataUpdateDto> pageListDtoJoin(PageObject pageObject);

    List<BaseDataUpdateDto> pageListCache(PageObject pageObject);

}