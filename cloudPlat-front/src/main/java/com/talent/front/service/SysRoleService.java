package com.talent.front.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.SysRoleDto;
import com.talent.front.entity.SysRole;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：角色服务接口类
 */
public interface SysRoleService extends BaseService<SysRole> {

	SysRoleDto updateObjectByPrimaryKeySelective(SysRole sysRole);

	PageResult<SysRoleDto> pageListDto(PageObject pageObject);

	List<SysRoleDto> pageListCache(PageObject pageObject);

	Set<String> findRoleTypesByUserId(String userId);

	List<SysRoleDto> findRolesByUserId(String userId);

}