package com.talent.dcs.service;

import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.dcs.dto.BaseDocDto;
import com.talent.dcs.entity.BaseDoc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：文档表服务接口类
 */
public interface BaseDocService extends BaseService<BaseDoc> {

    /**
     * 以主鍵查询DTO
     */
    BaseDocDto selectDtoByPrimaryKey(String docId);

    /**
     * 更新model返回DTO
     */
    BaseDocDto updateDtoByPrimaryKeySelective(BaseDoc baseDoc);

    PageResult<BaseDocDto> pageListDto(PageObject pageObject);

    List<BaseDocDto> pageListCache(PageObject pageObject);

    void addInstitutionSnlAndVersion(String[] array, BaseDocDto baseDocDto);

    void addInstitutionSnlAndVersionByCsv(CSVReader cr, BaseDocDto baseDocDto) throws IOException;

}