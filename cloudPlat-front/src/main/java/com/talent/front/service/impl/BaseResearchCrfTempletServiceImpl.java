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
import com.talent.front.dao.BaseResearchCrfSnlDao;
import com.talent.front.dao.BaseResearchCrfTempletDao;
import com.talent.front.dao.BaseResearchFileLogDao;
import com.talent.front.dto.BaseResearchCrfTempletDto;
import com.talent.front.entity.BaseResearchCrfTemplet;
import com.talent.front.entity.BaseResearchFileLog;
import com.talent.front.service.BaseResearchCrfTempletService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板表服务实现类
 */
@Service
public class BaseResearchCrfTempletServiceImpl extends BaseServiceImpl<BaseResearchCrfTemplet>
        implements BaseResearchCrfTempletService {
    private static final Logger logger = LoggerFactory.getLogger(BaseResearchCrfTempletServiceImpl.class);

    @Autowired
    private BaseResearchCrfTempletDao baseResearchCrfTempletDao;

    @Autowired
    private BaseResearchCrfSnlDao baseResearchCrfSnlDao;

    @Autowired
    private BaseResearchFileLogDao baseResearchFileLogDao;

    @Override
    public BaseDao<BaseResearchCrfTemplet> getBaseDao() {
        return this.baseResearchCrfTempletDao;
    }

    @CacheSpeObject(value = "baseResearchCrfTemplet", key = "#templetId")
    @Override
    public BaseResearchCrfTempletDto selectDtoByPrimaryKey(String templetId) {
        return this.baseResearchCrfTempletDao.selectDtoByPrimaryKey(templetId);
    }

    @CacheSpeObject(value = "baseResearchCrfTemplet", key = "#baseResearchCrfTemplet.templetId", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public BaseResearchCrfTempletDto updateDtoByPrimaryKeySelective(BaseResearchCrfTemplet baseResearchCrfTemplet) {
        this.baseResearchCrfTempletDao.updateByPrimaryKeySelective(baseResearchCrfTemplet);
        return this.baseResearchCrfTempletDao.selectDtoByPrimaryKey(baseResearchCrfTemplet.getTempletId());
    }

    @Override
    public PageResult<BaseResearchCrfTempletDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseResearchCrfTempletDao.pageCount(pageObject);
        PageResult<BaseResearchCrfTempletDto> pageResult = new PageResult<BaseResearchCrfTempletDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseResearchCrfTempletDto> baseResearchCrfTempletDtoList = ((BaseResearchCrfTempletServiceImpl) AopContext
                    .currentProxy()).pageListCache(pageObject);
            pageResult.setQueryResult(baseResearchCrfTempletDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseResearchCrfTempletDto> pageListCache(PageObject pageObject) {
        return this.baseResearchCrfTempletDao.pageListDto(pageObject);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int addBaseResearchCrfTempletDto(BaseResearchCrfTempletDto baseResearchCrfTempletDto) {
        baseResearchCrfTempletDto.getBaseResearchCrfSnlList().stream().forEach(a -> {
            a.setId(UUIDUtil.getUUID(32));
            a.setTempletId(baseResearchCrfTempletDto.getTempletId());
        });
        this.baseResearchCrfSnlDao.batchInsert(baseResearchCrfTempletDto.getBaseResearchCrfSnlList());

        // BaseResearchFileLog baseResearchFileLog = new BaseResearchFileLog();
        // baseResearchFileLog.setLogId(UUIDUtil.getUUID(32));
        // baseResearchFileLog.setResearchId(baseResearchCrfTempletDto.getResearchId());
        // baseResearchFileLog.setFileId(baseResearchCrfTempletDto.getTempletId());
        // baseResearchFileLog.setOperatorId(baseResearchCrfTempletDto.getCreator());
        // baseResearchFileLog.setOperateTime(new Date());
        // baseResearchFileLog.setOperatorType(ResearchConstant.FileOperatorType.CREATE);
        // this.baseResearchFileLogDao.insertSelective(baseResearchFileLog);

        return this.baseResearchCrfTempletDao.insertSelective(baseResearchCrfTempletDto);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public BaseResearchCrfTempletDto deleteBaseResearchCrfTempletDto(BaseResearchCrfTemplet baseResearchCrfTemplet) {
        BaseResearchFileLog baseResearchFileLog = new BaseResearchFileLog();
        baseResearchFileLog.setLogId(UUIDUtil.getUUID(32));
        baseResearchFileLog.setResearchId(baseResearchCrfTemplet.getResearchId());
        baseResearchFileLog.setFileId(baseResearchCrfTemplet.getTempletId());
        baseResearchFileLog.setOperatorId(baseResearchCrfTemplet.getUpdateUser());
        baseResearchFileLog.setOperateTime(new Date());
        baseResearchFileLog.setOperatorType(ResearchConstant.FileOperatorType.DELETE);
        this.baseResearchFileLogDao.insertSelective(baseResearchFileLog);

        return ((BaseResearchCrfTempletServiceImpl) AopContext.currentProxy())
                .updateDtoByPrimaryKeySelective(baseResearchCrfTemplet);
    }

}