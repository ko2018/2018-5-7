package com.talent.dcs.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseInstitutionDto;
import com.talent.dcs.entity.BaseInstitution;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-01 <br/>
 * 描述：机构表服务接口类
 */
public interface BaseInstitutionService extends BaseService<BaseInstitution> {

    /**
     * 以主鍵查询DTO
     */
    BaseInstitutionDto selectDtoByPrimaryKey(String institutionId);

    /**
     * 更新model返回DTO
     */
    BaseInstitutionDto updateDtoByPrimaryKeySelective(BaseInstitution baseInstitution);

    PageResult<BaseInstitutionDto> pageListDto(PageObject pageObject);

    PageResult<BaseInstitutionDto> pageListDtoJoin(PageObject pageObject);

    List<BaseInstitutionDto> pageListCache(PageObject pageObject);

}