package com.talent.dcs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.talent.base.constant.YesOrNo;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.base.util.JacksonUtil;
import com.talent.dcs.dao.BaseDataCleanDao;
import com.talent.dcs.dao.BaseDataStdDao;
import com.talent.dcs.dao.BaseDocValueDao;
import com.talent.dcs.dto.BaseDocValueDto;
import com.talent.dcs.entity.BaseDataClean;
import com.talent.dcs.entity.BaseDataStd;
import com.talent.dcs.entity.BaseDocValue;
import com.talent.dcs.service.BaseDataUpdateService;
import com.talent.dcs.service.BaseDocValueService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-17 <br/>
 * 描述：文档值显示记录表服务实现类
 */
@Service
public class BaseDocValueServiceImpl extends BaseServiceImpl<BaseDocValue> implements BaseDocValueService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocValueServiceImpl.class);

    @Autowired
    private BaseDocValueDao baseDocValueDao;

    @Autowired
    private BaseDataCleanDao baseDataCleanDao;

    @Autowired
    private BaseDataStdDao baseDataStdDao;

    @Autowired
    private BaseDataUpdateService baseDataUpdateService;

    @Override
    public BaseDao<BaseDocValue> getBaseDao() {
        return this.baseDocValueDao;
    }

    @Override
    public BaseDocValueDto selectDtoByPrimaryKey(String docvalueId) {
        return this.baseDocValueDao.selectDtoByPrimaryKey(docvalueId);
    }

    @Override
    public BaseDocValueDto updateDtoByPrimaryKeySelective(BaseDocValue baseDocValue) {
        this.baseDocValueDao.updateByPrimaryKeySelective(baseDocValue);
        return this.baseDocValueDao.selectDtoByPrimaryKey(baseDocValue.getDocvalueId());
    }

    @Override
    public PageResult<BaseDocValueDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseDocValueDao.pageCountDto(pageObject);
        PageResult<BaseDocValueDto> pageResult = new PageResult<BaseDocValueDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDocValueDto> baseDocValueDtoList = ((BaseDocValueServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(baseDocValueDtoList);
        }
        return pageResult;
    }

    @Override
    public PageResult<BaseDocValueDto> pageListDtoJoin(PageObject pageObject) {
        long totalCount = this.baseDocValueDao.pageCountDtoJoin(pageObject);
        PageResult<BaseDocValueDto> pageResult = new PageResult<BaseDocValueDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDocValueDto> baseDocValueDtoList = this.baseDocValueDao.pageListDtoJoin(pageObject);
            pageResult.setQueryResult(baseDocValueDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseDocValueDto> pageListCache(PageObject pageObject) {
        return this.baseDocValueDao.pageListDto(pageObject);
    }

    /**
     * 
     * Description: 更新错误记录
     *
     * @author fwp
     * @param docvalueId
     * @param docvalueValuenew
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void change(String docvalueId, String docId, int dictId, String docvalueValue, String docvalueValuenew) {
        Date data = new Date();
        List<BaseDataStd> tmp = new ArrayList<BaseDataStd>();
        PageObject pageObject = new PageObject();
        pageObject.setPageSize(Integer.MAX_VALUE);
        pageObject.getQueryCondition().put("docIdLike", docId);
        pageObject.getQueryCondition().put("errorFlagLike", "\\_" + dictId);
        pageObject.getQueryCondition().put("dataObject", "\"\\_" + dictId + "\":\"" + docvalueValue);
        List<BaseDataClean> list = baseDataCleanDao.pageList(pageObject);
        for (BaseDataClean baseDataClean : list) {
            BaseDataStd baseDataStd = baseDataStdDao.getDtoByAllKey(baseDataClean.getUserCode(),
                    baseDataClean.getInstitutionId(), baseDataClean.getCheckCode());
            String date_Object = baseDataStd.getDataObject();
            if (!StringUtils.isEmpty(date_Object)) {
                Map map = JacksonUtil.readValue(date_Object, Map.class);
                if (map != null) {
                    map.put("_" + dictId, docvalueValuenew);
                    baseDataStd.setDataObject(JacksonUtil.toJSon(map));
                    baseDataStd.setUpdateTime(data);
                    baseDataStdDao.updateByPrimaryKeySelective(baseDataStd);

                    tmp.add(baseDataStd);
                }
            }

            String date_Object_error = baseDataClean.getDataObject();
            if (!StringUtils.isEmpty(date_Object_error)) {
                Map map_error = JacksonUtil.readValue(date_Object_error, Map.class);
                if (map_error != null) {
                    Iterator<String> iter = map_error.keySet().iterator();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        if (key.equals("_" + dictId)) {
                            iter.remove();
                        }
                    }
                    if (map_error.size() > 0) {
                        String errorFlag = baseDataClean.getErrorFlag();
                        if (!StringUtils.isEmpty(errorFlag)) {
                            errorFlag = errorFlag.replace("_" + dictId, "");
                            errorFlag = errorFlag.replace(",,", ",");
                        }
                        baseDataClean.setErrorFlag(errorFlag);
                        baseDataClean.setDataObject(JacksonUtil.toJSon(map_error));
                        baseDataClean.setUpdateTime(data);
                        baseDataCleanDao.updateByPrimaryKeySelective(baseDataClean);
                    } else {
                        baseDataClean.setDataObject("");
                        baseDataClean.setErrorFlag("");
                        baseDataClean.setUpdateTime(data);
                        baseDataCleanDao.updateByPrimaryKeySelective(baseDataClean);
                    }
                }

            }

        }
        baseDataUpdateService.addBaseDataUpdates(tmp, dictId);

        BaseDocValueDto baseDocValueDto = baseDocValueDao.selectDtoByPrimaryKey(docvalueId);
        baseDocValueDto.setIsReplace(YesOrNo.Y.name());
        baseDocValueDto.setDocvalueValuenew(docvalueValuenew);
        baseDocValueDto.setUpdateTime(data);
        baseDocValueDao.updateByPrimaryKeySelective(baseDocValueDto);
    }

}