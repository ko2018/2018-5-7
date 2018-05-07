package com.talent.front.service.impl;

import java.util.Date;
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
import com.talent.base.util.UUIDUtil;
import com.talent.front.constant.ResearchConstant;
import com.talent.front.dao.BaseResearchFileDao;
import com.talent.front.dao.BaseResearchFileLogDao;
import com.talent.front.dto.BaseResearchFileDto;
import com.talent.front.entity.BaseResearchFile;
import com.talent.front.entity.BaseResearchFileLog;
import com.talent.front.service.BaseResearchFileService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档服务实现类
 */
@Service
public class BaseResearchFileServiceImpl extends BaseServiceImpl<BaseResearchFile> implements BaseResearchFileService {
    private static final Logger logger = LoggerFactory.getLogger(BaseResearchFileServiceImpl.class);

    @Autowired
    private BaseResearchFileDao baseResearchFileDao;

    @Autowired
    private BaseResearchFileLogDao baseResearchFileLogDao;

    @Override
    public BaseDao<BaseResearchFile> getBaseDao() {
        return this.baseResearchFileDao;
    }

    @CacheSpeObject(value = "baseResearchFile", key = "#baseResearchFile.fileId", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public BaseResearchFileDto updateObjectByPrimaryKeySelective(BaseResearchFile baseResearchFile) {
        this.baseResearchFileDao.updateByPrimaryKeySelective(baseResearchFile);
        return this.baseResearchFileDao.selectDtoByPrimaryKey(baseResearchFile.getFileId());
    }

    @Override
    public PageResult<BaseResearchFileDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseResearchFileDao.pageCount(pageObject);
        PageResult<BaseResearchFileDto> pageResult = new PageResult<BaseResearchFileDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseResearchFileDto> baseResearchFileDtoList = ((BaseResearchFileServiceImpl) AopContext
                    .currentProxy()).pageListCache(pageObject);
            pageResult.setQueryResult(baseResearchFileDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseResearchFileDto> pageListCache(PageObject pageObject) {
        return this.baseResearchFileDao.pageListDto(pageObject);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int addResearchFile(BaseResearchFileDto baseResearchFileDto, String userId) {
        baseResearchFileDto.setFileId(UUIDUtil.getUUID(32));
        baseResearchFileDto.setCreator(userId);
        baseResearchFileDto.setAddTime(new Date());
        baseResearchFileDto.setUpdateUser(userId);
        baseResearchFileDto.setUpdateTime(new Date());

        BaseResearchFileLog baseResearchFileLog = new BaseResearchFileLog();
        baseResearchFileLog.setLogId(UUIDUtil.getUUID(32));
        baseResearchFileLog.setResearchId(baseResearchFileDto.getResearchId());
        baseResearchFileLog.setFileId(baseResearchFileDto.getFileId());
        baseResearchFileLog.setOperatorId(userId);
        baseResearchFileLog.setOperateTime(new Date());
        baseResearchFileLog.setOperatorType(ResearchConstant.FileOperatorType.UPLOAD);
        this.baseResearchFileLogDao.insertSelective(baseResearchFileLog);

        return this.baseResearchFileDao.insertSelective(baseResearchFileDto);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public BaseResearchFileDto deleteResearchFile(BaseResearchFile baseResearchFile) {
        BaseResearchFileLog baseResearchFileLog = new BaseResearchFileLog();
        baseResearchFileLog.setLogId(UUIDUtil.getUUID(32));
        baseResearchFileLog.setResearchId(baseResearchFile.getResearchId());
        baseResearchFileLog.setFileId(baseResearchFile.getFileId());
        baseResearchFileLog.setOperatorId(baseResearchFile.getUpdateUser());
        baseResearchFileLog.setOperateTime(new Date());
        baseResearchFileLog.setOperatorType(ResearchConstant.FileOperatorType.DELETE);
        this.baseResearchFileLogDao.insertSelective(baseResearchFileLog);

        return ((BaseResearchFileServiceImpl) AopContext.currentProxy())
                .updateObjectByPrimaryKeySelective(baseResearchFile);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int downloadResearchFile(BaseResearchFile baseResearchFile, String userId) {
        BaseResearchFileLog baseResearchFileLog = new BaseResearchFileLog();
        baseResearchFileLog.setLogId(UUIDUtil.getUUID(32));
        baseResearchFileLog.setResearchId(baseResearchFile.getResearchId());
        baseResearchFileLog.setFileId(baseResearchFile.getFileId());
        baseResearchFileLog.setOperatorId(userId);
        baseResearchFileLog.setOperateTime(new Date());
        baseResearchFileLog.setOperatorType(ResearchConstant.FileOperatorType.DOWNLOAD);
        return this.baseResearchFileLogDao.insertSelective(baseResearchFileLog);
    }

}