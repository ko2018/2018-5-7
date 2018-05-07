package com.talent.front.service.impl;

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
import com.talent.front.dao.BaseSnlRuleValueDao;
import com.talent.front.dto.BaseSnlRuleValueDto;
import com.talent.front.entity.BaseSnlRuleValue;
import com.talent.front.service.BaseSnlRuleValueService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：术语值规则服务实现类
 */
@Service
public class BaseSnlRuleValueServiceImpl extends BaseServiceImpl<BaseSnlRuleValue> implements BaseSnlRuleValueService {
    private static final Logger logger = LoggerFactory.getLogger(BaseSnlRuleValueServiceImpl.class);

    @Autowired
    private BaseSnlRuleValueDao baseSnlRuleValueDao;

    @Override
    public BaseDao<BaseSnlRuleValue> getBaseDao() {
        return this.baseSnlRuleValueDao;
    }

    @CacheSpeObject(value = "baseSnlRuleValue", key = "#valueId")
    @Override
    public BaseSnlRuleValueDto selectDtoByPrimaryKey(String valueId) {
        return this.baseSnlRuleValueDao.selectDtoByPrimaryKey(valueId);
    }

    @CacheSpeObject(value = "baseSnlRuleValue", key = "#baseSnlRuleValue.valueId", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public BaseSnlRuleValueDto updateDtoByPrimaryKeySelective(BaseSnlRuleValue baseSnlRuleValue) {
        this.baseSnlRuleValueDao.updateByPrimaryKeySelective(baseSnlRuleValue);
        return this.baseSnlRuleValueDao.selectDtoByPrimaryKey(baseSnlRuleValue.getValueId());
    }

    @Override
    public PageResult<BaseSnlRuleValueDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseSnlRuleValueDao.pageCount(pageObject);
        PageResult<BaseSnlRuleValueDto> pageResult = new PageResult<BaseSnlRuleValueDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseSnlRuleValueDto> baseSnlRuleValueDtoList = ((BaseSnlRuleValueServiceImpl) AopContext
                    .currentProxy()).pageListCache(pageObject);
            pageResult.setQueryResult(baseSnlRuleValueDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseSnlRuleValueDto> pageListCache(PageObject pageObject) {
        return this.baseSnlRuleValueDao.pageListDto(pageObject);
    }

    @Override
    public BaseSnlRuleValueDto getDtoByDictId(int dictId) {
        return this.baseSnlRuleValueDao.getDtoByDictId(dictId);
    }

    /**
     * 简单描述该方法的实现功能（可选）.
     * 
     * @see com.talent.front.service.BaseSnlRuleValueService#replaceBaseSnlRuleValue(java.util.List)
     */
    @Override
    public int replaceBaseSnlRuleValue(List<BaseSnlRuleValue> snlRuleValues) {

        return this.baseSnlRuleValueDao.replaceBaseSnlRuleValue(snlRuleValues);
    }

}