package com.talent.front.service.impl;

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
import com.talent.front.dao.BaseDataCleanDao;
import com.talent.front.dao.BaseDataStdDao;
import com.talent.front.dao.BaseDocLegalDao;
import com.talent.front.dao.BaseDocValueDao;
import com.talent.front.dto.BaseDocValueDto;
import com.talent.front.entity.BaseDataClean;
import com.talent.front.entity.BaseDataStd;
import com.talent.front.entity.BaseDocValue;
import com.talent.front.service.BaseDataUpdateService;
import com.talent.front.service.BaseDocValueService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：文档值显示记录表服务实现类
 */
@Service
public class BaseDocValueServiceImpl extends BaseServiceImpl<BaseDocValue> implements BaseDocValueService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocValueServiceImpl.class);

    @Autowired
    private BaseDocValueDao baseDocValueDao;

    @Autowired
    private BaseDocLegalDao baseDocLegalDao;

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

    @CacheSpeObject(value = "baseDocValue", key = "#docvalueId")
    @Override
    public BaseDocValueDto selectDtoByPrimaryKey(String docvalueId) {
        return this.baseDocValueDao.selectDtoByPrimaryKey(docvalueId);
    }

    @CacheSpeObject(value = "baseDocValue", key = "#baseDocValue.docvalueId", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public BaseDocValueDto updateDtoByPrimaryKeySelective(BaseDocValue baseDocValue) {
        this.baseDocValueDao.updateByPrimaryKeySelective(baseDocValue);
        return this.baseDocValueDao.selectDtoByPrimaryKey(baseDocValue.getDocvalueId());
    }

    @Override
    public PageResult<BaseDocValueDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseDocValueDao.pageCount(pageObject);
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
    public PageResult<BaseDocValueDto> pageListDtoJoinGroupBy(PageObject pageObject) {
        long totalCount = this.baseDocValueDao.pageCountDtoJoinGroupBy(pageObject);
        PageResult<BaseDocValueDto> pageResult = new PageResult<BaseDocValueDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDocValueDto> baseDocValueDtoList = this.baseDocValueDao.pageListDtoJoinGroupBy(pageObject);
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

    @Override
    public BaseDocValueDto getDtoByDictIdAndDocId(int dictId, String docId, String docvalueValue) {
        return this.baseDocValueDao.getDtoByDictIdAndDocId(dictId, docId, docvalueValue);
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
        if (list.size() > 0) {
            baseDocLegalDao.updateByCountChanged(list.size(), dictId, docId);
        }
        baseDataUpdateService.addBaseDataUpdates(tmp, dictId);

        BaseDocValueDto baseDocValueDto = baseDocValueDao.selectDtoByPrimaryKey(docvalueId);
        baseDocValueDto.setIsReplace(YesOrNo.Y.name());
        baseDocValueDto.setDocvalueValuenew(docvalueValuenew);
        baseDocValueDto.setUpdateTime(data);
        baseDocValueDao.updateByPrimaryKeySelective(baseDocValueDto);
    }

    @Override
    public List<BaseDocValueDto> pageListDtoJoinSpe(List<String> docvalueValues, String docId, int dictId) {
        return baseDocValueDao.pageListDtoJoinSpe(docvalueValues, docId, dictId);
    }

    @Override
    public List<BaseDocValueDto> graph(int floor, String docId, int dictId) {
        return this.baseDocValueDao.graph(floor, docId, dictId);
    }

}