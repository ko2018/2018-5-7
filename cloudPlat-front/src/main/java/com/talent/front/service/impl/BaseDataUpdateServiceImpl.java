package com.talent.front.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dao.BaseDataUpdateDao;
import com.talent.front.dto.BaseDataUpdateDto;
import com.talent.front.entity.BaseDataStd;
import com.talent.front.entity.BaseDataUpdate;
import com.talent.front.service.BaseDataUpdateService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：数据更新表服务实现类
 */
@Service
public class BaseDataUpdateServiceImpl extends BaseServiceImpl<BaseDataUpdate> implements BaseDataUpdateService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDataUpdateServiceImpl.class);

    @Autowired
    private BaseDataUpdateDao baseDataUpdateDao;

    @Override
    public BaseDao<BaseDataUpdate> getBaseDao() {
        return this.baseDataUpdateDao;
    }

    @CacheSpeObject(value = "baseDataUpdate", key = "#datastdId")
    @Override
    public BaseDataUpdateDto selectDtoByPrimaryKey(String datastdId) {
        return this.baseDataUpdateDao.selectDtoByPrimaryKey(datastdId);
    }

    @CacheSpeObject(value = "baseDataUpdate", key = "#baseDataUpdate.datastdId", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public BaseDataUpdateDto updateDtoByPrimaryKeySelective(BaseDataUpdate baseDataUpdate) {
        this.baseDataUpdateDao.updateByPrimaryKeySelective(baseDataUpdate);
        return this.baseDataUpdateDao.selectDtoByPrimaryKey(baseDataUpdate.getDatastdId());
    }

    @Override
    public PageResult<BaseDataUpdateDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseDataUpdateDao.pageCountDto(pageObject);
        PageResult<BaseDataUpdateDto> pageResult = new PageResult<BaseDataUpdateDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDataUpdateDto> baseDataUpdateDtoList = ((BaseDataUpdateServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(baseDataUpdateDtoList);
        }
        return pageResult;
    }

    @Override
    public PageResult<BaseDataUpdateDto> pageListDtoJoin(PageObject pageObject) {
        long totalCount = this.baseDataUpdateDao.pageCountDtoJoin(pageObject);
        PageResult<BaseDataUpdateDto> pageResult = new PageResult<BaseDataUpdateDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDataUpdateDto> baseDataUpdateDtoList = this.baseDataUpdateDao.pageListDtoJoin(pageObject);
            pageResult.setQueryResult(baseDataUpdateDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseDataUpdateDto> pageListCache(PageObject pageObject) {
        return this.baseDataUpdateDao.pageListDto(pageObject);
    }

    @Override
    public void addBaseDataUpdates(List<BaseDataStd> list, int dictId) {
        Date date = new Date();
        List<BaseDataUpdate> tmp = new ArrayList<BaseDataUpdate>();
        for (BaseDataStd baseDataStd : list) {
            BaseDataUpdate baseDataUpdate = new BaseDataUpdate();
            baseDataUpdate.setDatastdId(baseDataStd.getDatastdId());
            baseDataUpdate.setDataObject(baseDataStd.getDataObject());
            baseDataUpdate.setDataupdateId(UUIDUtil.getUUID(32));
            baseDataUpdate.setCheckCode(baseDataStd.getCheckCode());
            baseDataUpdate.setDictId(dictId);
            baseDataUpdate.setDocId(baseDataStd.getDocId());
            baseDataUpdate.setInstitutionId(baseDataStd.getInstitutionId());
            baseDataUpdate.setUserCode(baseDataStd.getUserCode());
            baseDataUpdate.setCreator(baseDataStd.getCreator());
            baseDataUpdate.setAddTime(date);
            baseDataUpdate.setUpdateTime(date);
            tmp.add(baseDataUpdate);

        }
        if (tmp.size() > 0) {
            this.baseDataUpdateDao.batchInsert(tmp);
        }
    }

}