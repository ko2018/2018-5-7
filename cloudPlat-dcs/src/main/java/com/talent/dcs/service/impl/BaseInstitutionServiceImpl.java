package com.talent.dcs.service.impl;

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
import com.talent.dcs.dao.BaseInstitutionDao;
import com.talent.dcs.dto.BaseInstitutionDto;
import com.talent.dcs.entity.BaseInstitution;
import com.talent.dcs.service.BaseInstitutionService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-01 <br/>
 * 描述：机构表服务实现类
 */
@Service
public class BaseInstitutionServiceImpl extends BaseServiceImpl<BaseInstitution> implements BaseInstitutionService {
    private static final Logger logger = LoggerFactory.getLogger(BaseInstitutionServiceImpl.class);

    @Autowired
    private BaseInstitutionDao baseInstitutionDao;

    @Override
    public BaseDao<BaseInstitution> getBaseDao() {
        return this.baseInstitutionDao;
    }

    @Override
    public BaseInstitutionDto selectDtoByPrimaryKey(String institutionId) {
        return this.baseInstitutionDao.selectDtoByPrimaryKey(institutionId);
    }

    @Override
    public BaseInstitutionDto updateDtoByPrimaryKeySelective(BaseInstitution baseInstitution) {
        this.baseInstitutionDao.updateByPrimaryKeySelective(baseInstitution);
        return this.baseInstitutionDao.selectDtoByPrimaryKey(baseInstitution.getInstitutionId());
    }

    @Override
    public PageResult<BaseInstitutionDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseInstitutionDao.pageCountDto(pageObject);
        PageResult<BaseInstitutionDto> pageResult = new PageResult<BaseInstitutionDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseInstitutionDto> baseInstitutionDtoList = ((BaseInstitutionServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(baseInstitutionDtoList);
        }
        return pageResult;
    }

    @Override
    public PageResult<BaseInstitutionDto> pageListDtoJoin(PageObject pageObject) {
        long totalCount = this.baseInstitutionDao.pageCountDtoJoin(pageObject);
        PageResult<BaseInstitutionDto> pageResult = new PageResult<BaseInstitutionDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseInstitutionDto> baseInstitutionDtoList = this.baseInstitutionDao.pageListDtoJoin(pageObject);
            pageResult.setQueryResult(baseInstitutionDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseInstitutionDto> pageListCache(PageObject pageObject) {
        return this.baseInstitutionDao.pageListDto(pageObject);
    }

}