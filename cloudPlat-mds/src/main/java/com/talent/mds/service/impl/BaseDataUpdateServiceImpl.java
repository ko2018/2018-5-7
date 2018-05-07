package com.talent.mds.service.impl;

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
import com.talent.mds.dao.BaseDataUpdateDao;
import com.talent.mds.dto.BaseDataUpdateDto;
import com.talent.mds.entity.BaseDataUpdate;
import com.talent.mds.service.BaseDataUpdateService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-22 <br/>
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

    @Override
    public BaseDataUpdateDto selectDtoByPrimaryKey(String dataupdateId) {
        return this.baseDataUpdateDao.selectDtoByPrimaryKey(dataupdateId);
    }

    @Override
    public BaseDataUpdateDto updateDtoByPrimaryKeySelective(BaseDataUpdate baseDataUpdate) {
        this.baseDataUpdateDao.updateByPrimaryKeySelective(baseDataUpdate);
        return this.baseDataUpdateDao.selectDtoByPrimaryKey(baseDataUpdate.getDataupdateId());
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

}