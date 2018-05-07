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
import com.talent.dcs.dao.BaseDocDefValueDao;
import com.talent.dcs.dto.BaseDocDefValueDto;
import com.talent.dcs.entity.BaseDocDefValue;
import com.talent.dcs.service.BaseDocDefValueService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-03 <br/>
 * 描述：文档值通用不合法值记录服务实现类
 */
@Service
public class BaseDocDefValueServiceImpl extends BaseServiceImpl<BaseDocDefValue> implements BaseDocDefValueService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocDefValueServiceImpl.class);

    @Autowired
    private BaseDocDefValueDao baseDocDefValueDao;

    @Override
    public BaseDao<BaseDocDefValue> getBaseDao() {
        return this.baseDocDefValueDao;
    }

    @Override
    public BaseDocDefValueDto selectDtoByPrimaryKey(String docdefvalueId) {
        return this.baseDocDefValueDao.selectDtoByPrimaryKey(docdefvalueId);
    }

    @Override
    public BaseDocDefValueDto updateDtoByPrimaryKeySelective(BaseDocDefValue baseDocDefValue) {
        this.baseDocDefValueDao.updateByPrimaryKeySelective(baseDocDefValue);
        return this.baseDocDefValueDao.selectDtoByPrimaryKey(baseDocDefValue.getDocdefvalueId());
    }

    @Override
    public PageResult<BaseDocDefValueDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseDocDefValueDao.pageCountDto(pageObject);
        PageResult<BaseDocDefValueDto> pageResult = new PageResult<BaseDocDefValueDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDocDefValueDto> baseDocDefValueDtoList = ((BaseDocDefValueServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(baseDocDefValueDtoList);
        }
        return pageResult;
    }

    @Override
    public PageResult<BaseDocDefValueDto> pageListDtoJoin(PageObject pageObject) {
        long totalCount = this.baseDocDefValueDao.pageCountDtoJoin(pageObject);
        PageResult<BaseDocDefValueDto> pageResult = new PageResult<BaseDocDefValueDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDocDefValueDto> baseDocDefValueDtoList = this.baseDocDefValueDao.pageListDtoJoin(pageObject);
            pageResult.setQueryResult(baseDocDefValueDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseDocDefValueDto> pageListCache(PageObject pageObject) {
        return this.baseDocDefValueDao.pageListDto(pageObject);
    }

}