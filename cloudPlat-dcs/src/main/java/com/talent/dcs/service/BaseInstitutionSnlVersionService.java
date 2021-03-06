package com.talent.dcs.service;

import java.util.List;
import java.util.Map;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseInstitutionSnlDto;
import com.talent.dcs.dto.BaseInstitutionSnlVersionDto;
import com.talent.dcs.entity.BaseInstitutionSnl;
import com.talent.dcs.entity.BaseInstitutionSnlVersion;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：机构标准术语版本服务接口类
 */
public interface BaseInstitutionSnlVersionService extends BaseService<BaseInstitutionSnlVersion> {

    /**
     * 以主鍵查询DTO
     */
    BaseInstitutionSnlVersionDto selectDtoByPrimaryKey(String institutionSnlVersionId);

    /**
     * 更新model返回DTO
     */
    BaseInstitutionSnlVersionDto updateDtoByPrimaryKeySelective(BaseInstitutionSnlVersion baseInstitutionSnlVersion);

    PageResult<BaseInstitutionSnlVersionDto> pageListDto(PageObject pageObject);

    List<BaseInstitutionSnlVersionDto> pageListCache(PageObject pageObject);

    BaseInstitutionSnlVersionDto versionIsExist(List<BaseInstitutionSnl> list, String institutionId, boolean isSimple);

    Map<String, Integer> getDictidAndCode();

    Map<Integer, BaseInstitutionSnlDto> getValueAndInstitutionSnl(String institutionSnlVersionId);

    Map<Integer, BaseInstitutionSnlDto> getDictIdAndInstitutionSnl(String institutionSnlVersionId);

}