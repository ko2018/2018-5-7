package com.talent.dcs.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.base.constant.YesOrNo;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.dcs.dao.BaseDataRawDao;
import com.talent.dcs.dto.BaseDataRawDto;
import com.talent.dcs.entity.BaseDataRaw;
import com.talent.dcs.service.BaseDataRawService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：原始数据表服务实现类
 */
@Service
public class BaseDataRawServiceImpl extends BaseServiceImpl<BaseDataRaw> implements BaseDataRawService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDataRawServiceImpl.class);

    @Autowired
    private BaseDataRawDao baseDataRawDao;

    @Override
    public BaseDao<BaseDataRaw> getBaseDao() {
        return this.baseDataRawDao;
    }

    @Override
    public BaseDataRawDto selectDtoByPrimaryKey(String datarawId) {
        return this.baseDataRawDao.selectDtoByPrimaryKey(datarawId);
    }

    @Override
    public BaseDataRawDto updateDtoByPrimaryKeySelective(BaseDataRaw baseDataRaw) {
        this.baseDataRawDao.updateByPrimaryKeySelective(baseDataRaw);
        return this.baseDataRawDao.selectDtoByPrimaryKey(baseDataRaw.getDatarawId());
    }

    @Override
    public PageResult<BaseDataRawDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseDataRawDao.pageCount(pageObject);
        PageResult<BaseDataRawDto> pageResult = new PageResult<BaseDataRawDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDataRawDto> baseDataRawDtoList = ((BaseDataRawServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(baseDataRawDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseDataRawDto> pageListCache(PageObject pageObject) {
        return this.baseDataRawDao.pageListDto(pageObject);
    }

    @Override
    public BaseDataRawDto getDtoByAllKey(String userCode, String institutionId, String checkCode, String docId) {
        return this.baseDataRawDao.getDtoByAllKey(userCode, institutionId, checkCode, docId);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean addBaseDataRawDto(BaseDataRawDto baseDataRawDto) {
        BaseDataRawDto tmp = baseDataRawDao.getDtoByAllKey(baseDataRawDto.getUserCode(),
                baseDataRawDto.getInstitutionId(), baseDataRawDto.getCheckCode(), null);
        if (tmp == null) {
            baseDataRawDao.insertSelective(baseDataRawDto);
        } else {
            if (!tmp.getDocId().equals(baseDataRawDto.getDocId())) {
                String json = tmp.getDataObject();
                String json1 = baseDataRawDto.getDataObject();
                if (!StringUtils.isEmpty(json) && !StringUtils.isEmpty(json1)) {
                    if (json.equals(json1)) {
                        baseDataRawDto.setIsRepeat(YesOrNo.Y.name());
                        baseDataRawDao.insertSelective(baseDataRawDto);
                        return false;
                    } else {
                        // PhysicalExaminationRecordDto
                        // physicalExaminationRecordDto =
                        // JacksonUtil.readValue(json,
                        // PhysicalExaminationRecordDto.class);
                        // PhysicalExaminationRecordDto
                        // physicalExaminationRecordDto1 =
                        // JacksonUtil.readValue(json1,
                        // PhysicalExaminationRecordDto.class);
                        //
                        // Class cla = physicalExaminationRecordDto.getClass();
                        // Class cla1 =
                        // physicalExaminationRecordDto1.getClass();
                        // int length = cla.getFields().length;
                        //
                        // for (int i = 1; i < length + 1; i++) {
                        // try {
                        // Method method = cla.getMethod("get_" + i);
                        // Object object =
                        // method.invoke(physicalExaminationRecordDto);
                        // Method method1 = cla1.getMethod("get_" + i);
                        // Object object1 =
                        // method1.invoke(physicalExaminationRecordDto1);
                        // if (object == null && object1 != null) {
                        // method = cla.getMethod("set_" + i, String.class);
                        // method.invoke(physicalExaminationRecordDto,
                        // new Object[] { String.valueOf(object1) });
                        // }
                        // } catch (Exception e) {
                        // e.printStackTrace();
                        // }
                        // }
                        // tmp.setDataObject(JacksonUtil.toJSon(physicalExaminationRecordDto));
                        // tmp.setIsClean(YesOrNo.N.name());
                        // baseDataRawDao.updateByPrimaryKeySelective(tmp);
                        baseDataRawDao.insertSelective(baseDataRawDto);
                    }
                } else if (!StringUtils.isEmpty(json1)) {
                    // tmp.setDataObject(json1);
                    // tmp.setIsClean(YesOrNo.N.name());
                    // baseDataRawDao.updateByPrimaryKeySelective(tmp);
                    baseDataRawDao.insertSelective(baseDataRawDto);
                }
            } else {
                // 同一文档同一数据不用处理(但要把重复数统计上)
                if (tmp.getIsRepeat().equals(YesOrNo.Y.name()))
                    return false;
            }
        }
        return true;
    }
}
