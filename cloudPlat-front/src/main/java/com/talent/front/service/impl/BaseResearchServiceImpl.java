package com.talent.front.service.impl;

import java.util.ArrayList;
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
import com.talent.front.dao.BaseResearchDao;
import com.talent.front.dao.BaseResearchInsDao;
import com.talent.front.dao.BaseResearchStatusDao;
import com.talent.front.dao.BaseResearchUserDao;
import com.talent.front.dto.BaseResearchDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseResearch;
import com.talent.front.entity.BaseResearchIns;
import com.talent.front.entity.BaseResearchStatus;
import com.talent.front.entity.BaseResearchUser;
import com.talent.front.service.BaseResearchService;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研管理表服务实现类
 */
@Service
public class BaseResearchServiceImpl extends BaseServiceImpl<BaseResearch> implements BaseResearchService {
    private static final Logger logger = LoggerFactory.getLogger(BaseResearchServiceImpl.class);

    @Autowired
    private BaseResearchDao baseResearchDao;

    @Autowired
    private BaseResearchInsDao baseResearchInsDao;

    @Autowired
    private BaseResearchUserDao baseResearchUserDao;

    @Autowired
    private BaseResearchStatusDao baseResearchStatusDao;

    @Override
    public BaseDao<BaseResearch> getBaseDao() {
        return this.baseResearchDao;
    }

    @CacheSpeObject(value = "baseResearch", key = "#baseResearch.researchId", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public BaseResearchDto updateObjectByPrimaryKeySelective(BaseResearch baseResearch) {
        this.baseResearchDao.updateByPrimaryKeySelective(baseResearch);
        return this.baseResearchDao.selectDtoByPrimaryKey(baseResearch.getResearchId());
    }

    @Override
    public PageResult<BaseResearchDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseResearchDao.pageCount(pageObject);
        PageResult<BaseResearchDto> pageResult = new PageResult<BaseResearchDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseResearchDto> baseResearchDtoList = ((BaseResearchServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(baseResearchDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseResearchDto> pageListCache(PageObject pageObject) {
        return this.baseResearchDao.pageListDto(pageObject);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int addBaseResearchDto(BaseResearchDto baseResearchDto, SysUserDto user) {
        baseResearchDto.setResearchId(UUIDUtil.getUUID(32));
        baseResearchDto.setCreator(user.getUserId());
        baseResearchDto.setAddTime(new Date());
        baseResearchDto.setUpdateUser(user.getUserId());
        baseResearchDto.setUpdateTime(new Date());
        baseResearchDto.setStatus("新建");

        List<BaseResearchIns> insList = new ArrayList();
        BaseResearchIns majorIns = new BaseResearchIns();
        majorIns.setId(UUIDUtil.getUUID(32));
        majorIns.setResearchId(baseResearchDto.getResearchId());
        majorIns.setInsId(baseResearchDto.getMajorInsId());
        majorIns.setIsMajor(ResearchConstant.IsMajor.YES);
        insList.add(majorIns);

        List<String> insIds = baseResearchDto.getMemberInsId();
        if (insIds != null) {
            for (String id : insIds) {
                BaseResearchIns ins = new BaseResearchIns();
                ins.setId(UUIDUtil.getUUID(32));
                ins.setResearchId(baseResearchDto.getResearchId());
                ins.setInsId(id);
                ins.setIsMajor(ResearchConstant.IsMajor.NO);
                insList.add(ins);
            }
        }
        this.baseResearchInsDao.batchInsert(insList);

        List<BaseResearchUser> userList = new ArrayList();
        BaseResearchUser majorUser = new BaseResearchUser();
        majorUser.setId(UUIDUtil.getUUID(32));
        majorUser.setResearchId(baseResearchDto.getResearchId());
        majorUser.setUserId(baseResearchDto.getMajorUserId());
        majorUser.setIsMajor(ResearchConstant.IsMajor.YES);
        userList.add(majorUser);

        List<String> userIds = baseResearchDto.getMemberUserId();
        if (userIds != null) {
            for (String id : userIds) {
                BaseResearchUser researchUser = new BaseResearchUser();
                researchUser.setId(UUIDUtil.getUUID(32));
                researchUser.setResearchId(baseResearchDto.getResearchId());
                researchUser.setUserId(id);
                researchUser.setIsMajor(ResearchConstant.IsMajor.NO);
                userList.add(researchUser);
            }
        }
        this.baseResearchUserDao.batchInsert(userList);

        Integer seqCurrent = baseResearchStatusDao.getResearchCurrentSeq(baseResearchDto.getResearchId());
        BaseResearchStatus baseResearchStatus = new BaseResearchStatus();
        baseResearchStatus.setId(UUIDUtil.getUUID(32));
        baseResearchStatus.setResearchId(baseResearchDto.getResearchId());
        baseResearchStatus.setStatus(baseResearchDto.getStatus());
        baseResearchStatus.setOperatorId(user.getUserId());
        baseResearchStatus.setOperatorName(user.getRealName());
        baseResearchStatus.setOperateTime(new Date());
        baseResearchStatus.setSeq(seqCurrent == null ? 0 : seqCurrent + 1);
        this.baseResearchStatusDao.insertSelective(baseResearchStatus);

        return this.baseResearchDao.insertSelective(baseResearchDto);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public BaseResearch updateBaseResearchDto(BaseResearchDto baseResearchDto) {
        this.baseResearchInsDao.deleteByResearchId(baseResearchDto.getResearchId());
        List<BaseResearchIns> insList = new ArrayList();
        BaseResearchIns majorIns = new BaseResearchIns();
        majorIns.setId(UUIDUtil.getUUID(32));
        majorIns.setResearchId(baseResearchDto.getResearchId());
        majorIns.setInsId(baseResearchDto.getMajorInsId());
        majorIns.setIsMajor(ResearchConstant.IsMajor.YES);
        insList.add(majorIns);

        List<String> insIds = baseResearchDto.getMemberInsId();
        if (insIds != null) {
            for (String id : insIds) {
                BaseResearchIns ins = new BaseResearchIns();
                ins.setId(UUIDUtil.getUUID(32));
                ins.setResearchId(baseResearchDto.getResearchId());
                ins.setInsId(id);
                ins.setIsMajor(ResearchConstant.IsMajor.NO);
                insList.add(ins);
            }
        }
        this.baseResearchInsDao.batchInsert(insList);

        this.baseResearchUserDao.deleteByResearchId(baseResearchDto.getResearchId());
        List<BaseResearchUser> userList = new ArrayList();
        BaseResearchUser majorUser = new BaseResearchUser();
        majorUser.setId(UUIDUtil.getUUID(32));
        majorUser.setResearchId(baseResearchDto.getResearchId());
        majorUser.setUserId(baseResearchDto.getMajorUserId());
        majorUser.setIsMajor(ResearchConstant.IsMajor.YES);
        userList.add(majorUser);

        List<String> userIds = baseResearchDto.getMemberUserId();
        if (userIds != null) {
            for (String id : userIds) {
                BaseResearchUser user = new BaseResearchUser();
                user.setId(UUIDUtil.getUUID(32));
                user.setResearchId(baseResearchDto.getResearchId());
                user.setUserId(id);
                user.setIsMajor(ResearchConstant.IsMajor.NO);
                userList.add(user);
            }
        }
        this.baseResearchUserDao.batchInsert(userList);
        return ((BaseResearchServiceImpl) AopContext.currentProxy()).updateObjectByPrimaryKeySelective(baseResearchDto);
    }

    @Override
    public BaseResearchDto selectDtoByPrimaryKey(String researchId) {
        return this.baseResearchDao.selectDtoByPrimaryKey(researchId);
    }

}