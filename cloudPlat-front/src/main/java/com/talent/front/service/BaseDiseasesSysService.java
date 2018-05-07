package com.talent.front.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.front.dto.BaseDiseasesSysDto;
import com.talent.front.entity.BaseDiseasesSys;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：知识库表服务接口类
 */
public interface BaseDiseasesSysService extends BaseService<BaseDiseasesSys> {

    /**
     * 以主鍵查询DTO
     */
    BaseDiseasesSysDto selectDtoByPrimaryKey(String diseasesId);

    /**
     * 更新model返回DTO
     */
    BaseDiseasesSysDto updateDtoByPrimaryKeySelective(BaseDiseasesSys baseDiseasesSys);

    PageResult<BaseDiseasesSysDto> pageListDto(PageObject pageObject);

    List<BaseDiseasesSysDto> pageListCache(PageObject pageObject);

    int deleteBaseDiseasesSysById(BaseDiseasesSys baseDiseasesSys);

    List<BaseDiseasesSysDto> getListDtoByIds(List<String> diseasesIds);
}