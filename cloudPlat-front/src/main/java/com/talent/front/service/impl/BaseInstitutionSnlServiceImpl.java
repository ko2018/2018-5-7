package com.talent.front.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.base.constant.YesOrNo;
import com.talent.base.database.BaseDao;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.constant.CommonConstant;
import com.talent.front.dao.BaseDocDao;
import com.talent.front.dao.BaseInstitutionSnlDao;
import com.talent.front.dao.BaseInstitutionSnlVersionDao;
import com.talent.front.dao.BaseSnlDao;
import com.talent.front.dto.BaseInstitutionSnlDataDto;
import com.talent.front.dto.BaseInstitutionSnlDto;
import com.talent.front.dto.BaseSnlDto;
import com.talent.front.entity.BaseDoc;
import com.talent.front.entity.BaseInstitutionSnl;
import com.talent.front.entity.BaseInstitutionSnlVersion;
import com.talent.front.service.BaseInstitutionSnlService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：机构标准术语对应表服务实现类
 */
@Service
public class BaseInstitutionSnlServiceImpl extends BaseServiceImpl<BaseInstitutionSnl>
        implements BaseInstitutionSnlService {
    private static final Logger logger = LoggerFactory.getLogger(BaseInstitutionSnlServiceImpl.class);

    @Autowired
    private BaseInstitutionSnlDao baseInstitutionSnlDao;

    @Autowired
    private BaseSnlDao baseSnlDao;

    @Autowired
    private BaseDocDao baseDocDao;

    @Autowired
    private BaseInstitutionSnlVersionDao baseInstitutionSnlVersionDao;

    @Override
    public BaseDao<BaseInstitutionSnl> getBaseDao() {
        return this.baseInstitutionSnlDao;
    }

    @CacheSpeObject(value = "baseInstitutionSnl", key = "#institutionSnlId")
    @Override
    public BaseInstitutionSnlDto selectDtoByPrimaryKey(String institutionSnlId) {
        return this.baseInstitutionSnlDao.selectDtoByPrimaryKey(institutionSnlId);
    }

    @CacheSpeObject(value = "baseInstitutionSnl", key = "#baseInstitutionSnl.institutionSnlId", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public BaseInstitutionSnlDto updateDtoByPrimaryKeySelective(BaseInstitutionSnl baseInstitutionSnl) {
        this.baseInstitutionSnlDao.updateByPrimaryKeySelective(baseInstitutionSnl);
        return this.baseInstitutionSnlDao.selectDtoByPrimaryKey(baseInstitutionSnl.getInstitutionSnlId());
    }

    @Override
    public PageResult<BaseInstitutionSnlDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseInstitutionSnlDao.pageCount(pageObject);
        PageResult<BaseInstitutionSnlDto> pageResult = new PageResult<BaseInstitutionSnlDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseInstitutionSnlDto> baseInstitutionSnlDtoList = ((BaseInstitutionSnlServiceImpl) AopContext
                    .currentProxy()).pageListCache(pageObject);
            pageResult.setQueryResult(baseInstitutionSnlDtoList);
        }
        return pageResult;
    }

    @Override
    public PageResult<BaseInstitutionSnlDto> pageListDtoJoin(PageObject pageObject) {
        long totalCount = this.baseInstitutionSnlDao.pageCountDtoJoin(pageObject);
        PageResult<BaseInstitutionSnlDto> pageResult = new PageResult<BaseInstitutionSnlDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseInstitutionSnlDto> baseInstitutionSnlDtoList = this.baseInstitutionSnlDao
                    .pageListDtoJoin(pageObject);
            pageResult.setQueryResult(baseInstitutionSnlDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseInstitutionSnlDto> pageListCache(PageObject pageObject) {
        return this.baseInstitutionSnlDao.pageListDto(pageObject);
    }

    @Override
    public int updateRelationCanel(int dictId, String institutionSnlVersionId) {
        return this.baseInstitutionSnlDao.updateRelationCanel(dictId, institutionSnlVersionId);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateRelation(int dictId, String institutionSnlVersionId, String institutionSnlIds)
            throws GlobalException {
        this.baseInstitutionSnlDao.updateRelationCanel(dictId, institutionSnlVersionId);
        String[] isIds = institutionSnlIds.split(",");
        BaseSnlDto baseSnlDto = baseSnlDao.selectDtoByDictId(dictId);
        if (baseSnlDto != null) {
            for (int i = 0; i < isIds.length; i++) {
                BaseInstitutionSnl bis = new BaseInstitutionSnlDto();
                bis.setInstitutionSnlId(isIds[i]);
                bis.setDictId(dictId);
                bis.setSnlName(baseSnlDto.getNameCn());
                bis.setSnlCode(baseSnlDto.getSnlCode());
                bis.setSnlNameUs(baseSnlDto.getNameUs());
                baseInstitutionSnlDao.updateByPrimaryKeySelective(bis);

            }
        } else {
            throw new GlobalException(ErrorCode.INSTITUTIONSNL_DICTID_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public BaseInstitutionSnlDataDto statistics(String institutionSnlVersionId) {
        BaseInstitutionSnlDataDto baseInstitutionSnlDataDto = new BaseInstitutionSnlDataDto();
        PageObject pageObject = new PageObject();
        pageObject.getQueryCondition().put("deleteStatus", YesOrNo.N.name());
        pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
        pageObject.getQueryCondition().put("isLeaf", CommonConstant.Is.YES);
        long snlCount = this.baseSnlDao.pageCount(pageObject);

        pageObject.getQueryCondition().put("institutionSnlVersionId", institutionSnlVersionId);
        long insSnlCount = this.baseInstitutionSnlDao.pageCount(pageObject);

        pageObject.getQueryCondition().put("dictIdMin", 1);
        pageObject.setPageSize(10000);
        long insSnlRelationCount = this.baseInstitutionSnlDao.pageCount(pageObject);

        List<BaseInstitutionSnl> list = this.baseInstitutionSnlDao.pageList(pageObject);
        Set<Integer> set = new HashSet<Integer>();
        for (BaseInstitutionSnl baseInstitutionSnl : list) {
            set.add(baseInstitutionSnl.getDictId());
        }
        long snlRelationCount = set.size();

        baseInstitutionSnlDataDto.setInsSnlCount(insSnlCount);
        baseInstitutionSnlDataDto.setInsSnlRelationCount(insSnlRelationCount);
        baseInstitutionSnlDataDto.setSnlCount(snlCount);
        baseInstitutionSnlDataDto.setSnlRelationCount(snlRelationCount);

        return baseInstitutionSnlDataDto;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void relationAll(String institutionSnlVersionId) throws GlobalException {
        PageObject pageObject = new PageObject();
        pageObject.getQueryCondition().put("deleteStatus", YesOrNo.N.name());
        pageObject.getQueryCondition().put("institutionSnlVersionId", institutionSnlVersionId);
        pageObject.getQueryCondition().put("dictId", 0);
        long snlCount = this.baseInstitutionSnlDao.pageCount(pageObject);
        if (snlCount > 0) {
            throw new GlobalException(ErrorCode.INSTITUTIONSNL_DICTID_NOALL);
        } else {
            pageObject = new PageObject();
            pageObject.getQueryCondition().put("deleteStatus", YesOrNo.N.name());
            pageObject.getQueryCondition().put("institutionSnlVersionId", institutionSnlVersionId);
            pageObject.setPageSize(10000);
            List<BaseDoc> list = baseDocDao.pageList(pageObject);
            for (BaseDoc baseDoc : list) {
                baseDoc.setIsMapping(YesOrNo.Y.name());
                baseDoc.setUpdateTime(new Date());
                baseDocDao.updateByPrimaryKeySelective(baseDoc);
            }
            BaseInstitutionSnlVersion baseInstitutionSnlVersion = baseInstitutionSnlVersionDao
                    .selectDtoByPrimaryKey(institutionSnlVersionId);
            if (baseInstitutionSnlVersion != null) {
                baseInstitutionSnlVersion.setIsMapping(YesOrNo.Y.name());
                baseInstitutionSnlVersionDao.updateByPrimaryKeySelective(baseInstitutionSnlVersion);
            }
        }
    }

}