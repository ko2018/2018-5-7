package com.talent.dcs.service;

import java.util.List;
import java.util.Map;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseDataRawDto;
import com.talent.dcs.dto.BaseDataStdDto;
import com.talent.dcs.dto.BaseSnlRuleValueDto;
import com.talent.dcs.entity.BaseDataClean;
import com.talent.dcs.entity.BaseDataStd;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：标准数据表服务接口类
 */
public interface BaseDataStdService extends BaseService<BaseDataStd> {

    /**
     * 以主鍵查询DTO
     */
    BaseDataStdDto selectDtoByPrimaryKey(String datastdId);

    /**
     * 更新model返回DTO
     */
    BaseDataStdDto updateDtoByPrimaryKeySelective(BaseDataStd baseDataStd);

    PageResult<BaseDataStdDto> pageListDto(PageObject pageObject);

    List<BaseDataStdDto> pageListCache(PageObject pageObject);

    void cleanData(BaseDataRawDto baseDataRawDto, Map<Integer, BaseSnlRuleValueDto> mapClean,
            Map<Integer, BaseSnlRuleValueDto> mapCleanDef, String docId);

    BaseDataStdDto getDtoByAllKey(String userCode, String institutionId, String checkCode);

    void addBaseDataStdDto(BaseDataClean baseDataClean);

}