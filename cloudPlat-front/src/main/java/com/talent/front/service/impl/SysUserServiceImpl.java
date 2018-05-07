package com.talent.front.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dao.SysResourceDao;
import com.talent.front.dao.SysRoleDao;
import com.talent.front.dao.SysUserDao;
import com.talent.front.dao.SysUserRoleDao;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.SysUser;
import com.talent.front.entity.SysUserRole;
import com.talent.front.service.SysUserService;
import com.talent.front.util.redis.RedisUtil;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：管理员服务实现类
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysResourceDao sysResourceDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public BaseDao<SysUser> getBaseDao() {
        return this.sysUserDao;
    }

    @Value("${default.security.losetime}")
    long losetime;

    @CacheSpeObject(value = "sysUser", key = "#userId")
    @Override
    public SysUserDto selectDtoByPrimaryKey(String userId) {
        return this.sysUserDao.selectDtoByPrimaryKey(userId);
    }

    @CacheSpeObject(value = "sysUser", key = "#sysUser.userId", OPER = CacheSpeObject.OPER.UPDATE)
    @Override
    public SysUserDto updateDtoByPrimaryKeySelective(SysUser sysUser) {
        Date data = new Date();
        sysUser.setUpdateTime(data);
        this.sysUserDao.updateByPrimaryKeySelective(sysUser);
        return this.sysUserDao.selectDtoByPrimaryKey(sysUser.getUserId());
    }

    @Override
    public PageResult<SysUserDto> pageListDto(PageObject pageObject) {
        long totalCount = this.sysUserDao.pageCountDto(pageObject);
        PageResult<SysUserDto> pageResult = new PageResult<SysUserDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<SysUserDto> sysUserDtoList = ((SysUserServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(sysUserDtoList);
        }
        return pageResult;
    }

    @Override
    public PageResult<SysUserDto> pageListDtoJoin(PageObject pageObject) {
        long totalCount = this.sysUserDao.pageCountDtoJoin(pageObject);
        PageResult<SysUserDto> pageResult = new PageResult<SysUserDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<SysUserDto> sysUserDtoList = this.sysUserDao.pageListDtoJoin(pageObject);
            pageResult.setQueryResult(sysUserDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<SysUserDto> pageListCache(PageObject pageObject) {
        return this.sysUserDao.pageListDto(pageObject);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean addUser(SysUser sysUser, String roleIds) {
        sysUserDao.insertSelective(sysUser);
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        String[] roleId = roleIds.split(",");
        for (String tmp : roleId) {
            SysUserRole sur = new SysUserRole();
            sur.setUserRoleId(UUIDUtil.getUUID(32));
            sur.setUserId(sysUser.getUserId());
            sur.setRoleId(tmp);
            list.add(sur);
        }
        sysUserRoleDao.batchInsert(list);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean update(SysUser sysUser, String roleIds) {
        sysUser.setUpdateTime(new Date());
        ((SysUserServiceImpl) AopContext.currentProxy()).updateDtoByPrimaryKeySelective(sysUser);
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        String[] roleId = roleIds.split(",");
        for (String tmp : roleId) {
            SysUserRole sur = new SysUserRole();
            sur.setUserRoleId(UUIDUtil.getUUID(32));
            sur.setUserId(sysUser.getUserId());
            sur.setRoleId(tmp);
            list.add(sur);
        }
        sysUserRoleDao.deleteByUserId(sysUser.getUserId());
        sysUserRoleDao.batchInsert(list);
        return true;
    }

    @Override
    public SysUserDto getUserByName(String username) {
        return this.sysUserDao.getUserByName(username);
    }

    @Override
    public SysUserDto getDtoByToken(String token) {
        Object object = redisUtil.get(token);
        if (object != null) {
            redisUtil.expire(token, losetime);
            if (object instanceof SysUserDto) {
                SysUserDto sysUserDto = (SysUserDto) object;
                return sysUserDto;
            }
        }
        return null;
    }

}