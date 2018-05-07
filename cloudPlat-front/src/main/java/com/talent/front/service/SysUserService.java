package com.talent.front.service;

import java.util.List;

import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseService;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.SysUser;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：管理员服务接口类
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 以主鍵查询DTO
     */
    SysUserDto selectDtoByPrimaryKey(String userId);

    /**
     * 更新model返回DTO
     */
    SysUserDto updateDtoByPrimaryKeySelective(SysUser sysUser);

    PageResult<SysUserDto> pageListDto(PageObject pageObject);

    PageResult<SysUserDto> pageListDtoJoin(PageObject pageObject);

    List<SysUserDto> pageListCache(PageObject pageObject);

    SysUserDto getUserByName(String username);

    boolean addUser(SysUser sysUser, String roleIds);

    boolean update(SysUser sysUser, String roleIds);

    SysUserDto getDtoByToken(String token);

}