package com.talent.dcs.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;
import com.talent.base.constant.YesOrNo;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.base.util.UUIDUtil;
import com.talent.dcs.constant.DocTypeEnum;
import com.talent.dcs.dao.BaseDocDao;
import com.talent.dcs.dao.BaseInstitutionSnlDao;
import com.talent.dcs.dao.BaseSnlDao;
import com.talent.dcs.dto.BaseDocDto;
import com.talent.dcs.dto.BaseInstitutionSnlDto;
import com.talent.dcs.dto.BaseInstitutionSnlVersionDto;
import com.talent.dcs.entity.BaseDoc;
import com.talent.dcs.entity.BaseInstitutionSnl;
import com.talent.dcs.entity.BaseSnl;
import com.talent.dcs.service.BaseDocService;
import com.talent.dcs.service.BaseInstitutionSnlVersionService;
import com.talent.dcs.service.BaseSnlService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：文档表服务实现类
 */
@Service
public class BaseDocServiceImpl extends BaseServiceImpl<BaseDoc> implements BaseDocService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocServiceImpl.class);

    @Autowired
    private BaseDocDao baseDocDao;

    @Autowired
    private BaseSnlDao baseSnlDao;

    @Autowired
    private BaseInstitutionSnlDao baseInstitutionSnlDao;

    @Autowired
    private BaseInstitutionSnlVersionService baseInstitutionSnlVersionService;

    @Autowired
    private BaseSnlService baseSnlService;

    @Override
    public BaseDao<BaseDoc> getBaseDao() {
        return this.baseDocDao;
    }

    @Override
    public BaseDocDto selectDtoByPrimaryKey(String docId) {
        return this.baseDocDao.selectDtoByPrimaryKey(docId);
    }

    @Override
    public BaseDocDto updateDtoByPrimaryKeySelective(BaseDoc baseDoc) {
        this.baseDocDao.updateByPrimaryKeySelective(baseDoc);
        return this.baseDocDao.selectDtoByPrimaryKey(baseDoc.getDocId());
    }

    @Override
    public PageResult<BaseDocDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseDocDao.pageCount(pageObject);
        PageResult<BaseDocDto> pageResult = new PageResult<BaseDocDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDocDto> baseDocDtoList = ((BaseDocServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(baseDocDtoList);
        }
        return pageResult;
    }

    @Override
    public List<BaseDocDto> pageListCache(PageObject pageObject) {
        return this.baseDocDao.pageListDto(pageObject);
    }

    /**
     * 根据文档记录，增加机构术语对应关系，以及生成版本号
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void addInstitutionSnlAndVersion(String[] array, BaseDocDto baseDocDto) {
        Map<String, BaseSnl> map = baseSnlService.getMapByNameObject();
        boolean isAll = true;// 是否完全匹配
        Date date = new Date();
        // 获得总列数
        int coloumNum = array.length;
        String versionId = UUIDUtil.getUUID(32);
        List<BaseInstitutionSnl> list = new ArrayList<BaseInstitutionSnl>();
        for (int i = 0; i < coloumNum; i++) {
            BaseInstitutionSnlDto baseInstitutionSnl = new BaseInstitutionSnlDto();
            baseInstitutionSnl.setDocCname(array[i]);
            baseInstitutionSnl.setDocCvalue(i);

            baseInstitutionSnl.setInstitutionSnlVersionId(versionId);
            baseInstitutionSnl.setAddTime(date);
            baseInstitutionSnl.setUpdateTime(date);
            baseInstitutionSnl.setInstitutionSnlId(UUIDUtil.getUUID(32));
            baseInstitutionSnl.setDeleteStatus(YesOrNo.N.name()); // 批量提交需要显示设置
            baseInstitutionSnl.setDictId(0);
            if (map.size() > 0) {
                BaseSnl baseSnl = map.get(array[i].trim());
                if (baseSnl != null) {
                    baseInstitutionSnl.setSnlCode(baseSnl.getSnlCode());
                    baseInstitutionSnl.setSnlName(baseSnl.getNameCn());
                    baseInstitutionSnl.setSnlNameUs(baseSnl.getNameUs());
                    baseInstitutionSnl.setDictId(baseSnl.getDictId());
                } else {
                    isAll = false;
                }
            } else {
                isAll = false;
            }

            // baseSnlService.
            list.add(baseInstitutionSnl);
        }

        if (baseDocDto.getDoctypeId().equals(DocTypeEnum.CLEANED.getCode())) {
            if (isAll) {
                if (list.size() > 0) {
                    baseInstitutionSnlDao.batchInsert(list);

                    BaseInstitutionSnlVersionDto baseInstitutionSnlVersionDto = new BaseInstitutionSnlVersionDto();
                    baseInstitutionSnlVersionDto.setInstitutionSnlVersionId(versionId);

                    baseInstitutionSnlVersionDto.setAddTime(date);
                    baseInstitutionSnlVersionDto.setUpdateTime(date);
                    // baseInstitutionSnlVersionDto.setVersionPath(versionPath);
                    baseInstitutionSnlVersionDto.setFieldCount(list.size());
                    baseInstitutionSnlVersionDto.setInstitutionId(baseDocDto.getInstitutionId());
                    baseInstitutionSnlVersionDto.setVersionName(baseDocDto.getDocName());
                    baseInstitutionSnlVersionDto.setIsMapping(YesOrNo.Y.name());
                    baseInstitutionSnlVersionDto.setDeleteStatus(YesOrNo.Y.name());
                    baseInstitutionSnlVersionService.insertSelective(baseInstitutionSnlVersionDto);

                    baseDocDto.setInstitutionSnlVersionId(versionId);
                    baseDocDto.setIsMapping(YesOrNo.Y.name());
                }
                baseDocDto.setUpdateTime(date);
                baseDocDao.updateByPrimaryKeySelective(baseDocDto);
            } else {
                baseDocDto.setIsMapping("E"); // 错误,文档对应关系错误
                baseDocDto.setUpdateTime(date);
                baseDocDao.updateByPrimaryKeySelective(baseDocDto);
            }
        } else if (baseDocDto.getDoctypeId().equals(DocTypeEnum.ORIGINAL.getCode())) {

            // TODO是否做版本号判断
            BaseInstitutionSnlVersionDto baseInstitutionSnlVersionDtoTmp = baseInstitutionSnlVersionService
                    .versionIsExist(list, baseDocDto.getInstitutionId(), true);
            if (baseInstitutionSnlVersionDtoTmp != null) {
                logger.info("已经存在的版本ID:" + baseInstitutionSnlVersionDtoTmp.getInstitutionSnlVersionId());
                baseDocDto.setInstitutionSnlVersionId(baseInstitutionSnlVersionDtoTmp.getInstitutionSnlVersionId());
                if (baseInstitutionSnlVersionDtoTmp.getIsMapping().equals(YesOrNo.Y.name()))
                    baseDocDto.setIsMapping(YesOrNo.Y.name());
            } else {
                if (list.size() > 0) {
                    baseInstitutionSnlDao.batchInsert(list);

                    BaseInstitutionSnlVersionDto baseInstitutionSnlVersionDto = new BaseInstitutionSnlVersionDto();
                    baseInstitutionSnlVersionDto.setInstitutionSnlVersionId(versionId);

                    baseInstitutionSnlVersionDto.setAddTime(date);
                    baseInstitutionSnlVersionDto.setUpdateTime(date);
                    // baseInstitutionSnlVersionDto.setVersionPath(versionPath);
                    baseInstitutionSnlVersionDto.setFieldCount(list.size());
                    baseInstitutionSnlVersionDto.setInstitutionId(baseDocDto.getInstitutionId());
                    baseInstitutionSnlVersionDto.setVersionName(baseDocDto.getDocName());
                    baseInstitutionSnlVersionDto.setIsMapping(YesOrNo.N.name());
                    baseInstitutionSnlVersionService.insertSelective(baseInstitutionSnlVersionDto);

                    baseDocDto.setInstitutionSnlVersionId(versionId);
                    baseDocDto.setIsMapping(YesOrNo.N.name());
                }
            }

            baseDocDto.setUpdateTime(date);
            baseDocDao.updateByPrimaryKeySelective(baseDocDto);
        }
    }

    /**
     * 根据文档记录，增加机构术语对应关系，以及生成版本号
     * 
     * @throws IOException
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void addInstitutionSnlAndVersionByCsv(CSVReader cr, BaseDocDto baseDocDto) throws IOException {
        String[] csvRow = cr.readNext();
        Map<String, BaseSnl> map = baseSnlService.getMapByNameObject();

        String versionId = UUIDUtil.getUUID(32);
        List<BaseInstitutionSnl> list = new ArrayList<BaseInstitutionSnl>();
        for (int i = 0; i < csvRow.length; i++) {
            BaseInstitutionSnlDto baseInstitutionSnl = new BaseInstitutionSnlDto();
            baseInstitutionSnl.setDocCname(csvRow[i]);
            baseInstitutionSnl.setDocCvalue(i);

            baseInstitutionSnl.setInstitutionSnlVersionId(versionId);
            Date date = new Date();
            baseInstitutionSnl.setAddTime(date);
            baseInstitutionSnl.setUpdateTime(date);
            baseInstitutionSnl.setInstitutionSnlId(UUIDUtil.getUUID(32));
            baseInstitutionSnl.setDeleteStatus(YesOrNo.N.name()); // 批量提交需要显示设置
            baseInstitutionSnl.setDictId(0);
            if (map.size() > 0) {
                BaseSnl baseSnl = map.get(csvRow[i].trim());
                if (baseSnl != null) {
                    baseInstitutionSnl.setSnlCode(baseSnl.getSnlCode());
                    baseInstitutionSnl.setSnlName(baseSnl.getNameCn());
                    baseInstitutionSnl.setSnlNameUs(baseSnl.getNameUs());
                    baseInstitutionSnl.setDictId(baseSnl.getDictId());
                }
            }

            // baseSnlService.
            list.add(baseInstitutionSnl);
        }
        // TODO是否做版本号判断
        BaseInstitutionSnlVersionDto baseInstitutionSnlVersionDtoTmp = baseInstitutionSnlVersionService
                .versionIsExist(list, baseDocDto.getInstitutionId(), true);
        Date date = new Date();
        if (baseInstitutionSnlVersionDtoTmp != null) {
            logger.info("已经存在的版本ID:" + baseInstitutionSnlVersionDtoTmp.getInstitutionSnlVersionId());
            baseDocDto.setInstitutionSnlVersionId(baseInstitutionSnlVersionDtoTmp.getInstitutionSnlVersionId());
            if (baseInstitutionSnlVersionDtoTmp.getIsMapping().equals(YesOrNo.Y.name()))
                baseDocDto.setIsMapping(YesOrNo.Y.name());
        } else {
            if (list.size() > 0) {
                baseInstitutionSnlDao.batchInsert(list);

                BaseInstitutionSnlVersionDto baseInstitutionSnlVersionDto = new BaseInstitutionSnlVersionDto();
                baseInstitutionSnlVersionDto.setInstitutionSnlVersionId(versionId);

                baseInstitutionSnlVersionDto.setAddTime(date);
                baseInstitutionSnlVersionDto.setUpdateTime(date);
                // baseInstitutionSnlVersionDto.setVersionPath(versionPath);
                baseInstitutionSnlVersionDto.setFieldCount(list.size());
                baseInstitutionSnlVersionDto.setInstitutionId(baseDocDto.getInstitutionId());
                baseInstitutionSnlVersionDto.setVersionName(baseDocDto.getDocName());
                baseInstitutionSnlVersionDto.setIsMapping(YesOrNo.N.name());
                baseInstitutionSnlVersionService.insertSelective(baseInstitutionSnlVersionDto);

                baseDocDto.setInstitutionSnlVersionId(versionId);
                baseDocDto.setIsMapping(YesOrNo.N.name());
            }
        }

        baseDocDto.setUpdateTime(date);
        baseDocDao.updateByPrimaryKeySelective(baseDocDto);
    }

}