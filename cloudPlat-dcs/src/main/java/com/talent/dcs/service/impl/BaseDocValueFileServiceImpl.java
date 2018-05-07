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
import com.talent.dcs.dao.BaseDocValueFileDao;
import com.talent.dcs.dto.BaseDocValueFileDto;
import com.talent.dcs.entity.BaseDocValueFile;
import com.talent.dcs.service.BaseDocValueFileService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-20 <br/>
 * 描述：不合法修改文件表服务实现类
 */
@Service
public class BaseDocValueFileServiceImpl extends BaseServiceImpl<BaseDocValueFile> implements BaseDocValueFileService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocValueFileServiceImpl.class);

    @Autowired
    private BaseDocValueFileDao baseDocValueFileDao;

    @Override
    public BaseDao<BaseDocValueFile> getBaseDao() {
        return this.baseDocValueFileDao;
    }

    @Override
    public BaseDocValueFileDto selectDtoByPrimaryKey(String valuefileId) {
        return this.baseDocValueFileDao.selectDtoByPrimaryKey(valuefileId);
    }

    @Override
    public BaseDocValueFileDto updateDtoByPrimaryKeySelective(BaseDocValueFile baseDocValueFile) {
        this.baseDocValueFileDao.updateByPrimaryKeySelective(baseDocValueFile);
        return this.baseDocValueFileDao.selectDtoByPrimaryKey(baseDocValueFile.getValuefileId());
    }

    @Override
    public PageResult<BaseDocValueFileDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseDocValueFileDao.pageCountDto(pageObject);
        PageResult<BaseDocValueFileDto> pageResult = new PageResult<BaseDocValueFileDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDocValueFileDto> baseDocValueFileDtoList = ((BaseDocValueFileServiceImpl) AopContext
                    .currentProxy()).pageListCache(pageObject);
            pageResult.setQueryResult(baseDocValueFileDtoList);
        }
        return pageResult;
    }

    @Override
    public PageResult<BaseDocValueFileDto> pageListDtoJoin(PageObject pageObject) {
        long totalCount = this.baseDocValueFileDao.pageCountDtoJoin(pageObject);
        PageResult<BaseDocValueFileDto> pageResult = new PageResult<BaseDocValueFileDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDocValueFileDto> baseDocValueFileDtoList = this.baseDocValueFileDao.pageListDtoJoin(pageObject);
            pageResult.setQueryResult(baseDocValueFileDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseDocValueFileDto> pageListCache(PageObject pageObject) {
        return this.baseDocValueFileDao.pageListDto(pageObject);
    }

}