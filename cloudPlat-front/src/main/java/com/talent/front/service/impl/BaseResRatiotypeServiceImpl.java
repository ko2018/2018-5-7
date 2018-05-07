package com.talent.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.BaseResRatioDao;
import com.talent.front.dao.BaseResRatiotypeDao;
import com.talent.front.dto.BaseResRatiotypeDto;
import com.talent.front.entity.BaseResRatiotype;
import com.talent.front.service.BaseResRatiotypeService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源类型服务实现类
 */
@Service
public class BaseResRatiotypeServiceImpl extends BaseServiceImpl<BaseResRatiotype> implements BaseResRatiotypeService {
    private static final Logger logger = LoggerFactory.getLogger(BaseResRatiotypeServiceImpl.class);

    @Autowired
    private BaseResRatiotypeDao baseResRatiotypeDao;

    @Autowired
    private BaseResRatioDao baseResRatioDao;

    @Override
    public BaseDao<BaseResRatiotype> getBaseDao() {
        return this.baseResRatiotypeDao;
    }

    @CacheSpeObject(value = "baseResRatiotype", key = "#resratioTypeid")
    @Override
    public BaseResRatiotypeDto selectDtoByPrimaryKey(String resratioTypeid) {
        return this.baseResRatiotypeDao.selectDtoByPrimaryKey(resratioTypeid);
    }

    @CacheSpeObject(value = "baseResRatiotype", key = "#baseResRatiotype.resratioTypeid", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public BaseResRatiotypeDto updateDtoByPrimaryKeySelective(BaseResRatiotype baseResRatiotype) {
        this.baseResRatiotypeDao.updateByPrimaryKeySelective(baseResRatiotype);
        return this.baseResRatiotypeDao.selectDtoByPrimaryKey(baseResRatiotype.getResratioTypeid());
    }

    @Override
    public PageResult<BaseResRatiotypeDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseResRatiotypeDao.pageCountDto(pageObject);
        PageResult<BaseResRatiotypeDto> pageResult = new PageResult<BaseResRatiotypeDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseResRatiotypeDto> baseResRatiotypeDtoList = ((BaseResRatiotypeServiceImpl) AopContext
                    .currentProxy()).pageListCache(pageObject);
            pageResult.setQueryResult(baseResRatiotypeDtoList);
        }
        return pageResult;
    }

    @Override
    public PageResult<BaseResRatiotypeDto> pageListDtoJoin(PageObject pageObject) {
        long totalCount = this.baseResRatiotypeDao.pageCountDtoJoin(pageObject);
        PageResult<BaseResRatiotypeDto> pageResult = new PageResult<BaseResRatiotypeDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseResRatiotypeDto> baseResRatiotypeDtoList = this.baseResRatiotypeDao.pageListDtoJoin(pageObject);
            pageResult.setQueryResult(baseResRatiotypeDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseResRatiotypeDto> pageListCache(PageObject pageObject) {
        return this.baseResRatiotypeDao.pageListDto(pageObject);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateResRatiotypeDto(BaseResRatiotype baseResRatiotype) {
        ((BaseResRatiotypeServiceImpl) AopContext.currentProxy()).updateDtoByPrimaryKeySelective(baseResRatiotype);
        baseResRatioDao.updateByRatioTypeId(baseResRatiotype.getResratioTypeid(),
                baseResRatiotype.getResratioTypename());
    }

}