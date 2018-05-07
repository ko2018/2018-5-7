package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.SysUserRoleDto;
import com.talent.front.entity.SysUserRole;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-09 <br/>
 * 描述：用户与角色关系表服务接口类
 */
public interface SysUserRoleService extends BaseService<SysUserRole>{

	SysUserRoleDto updateObjectByPrimaryKeySelective(SysUserRole sysUserRole);

	PageResult<SysUserRoleDto> pageListDto(PageObject pageObject);
	
	List<SysUserRoleDto> pageListCache(PageObject pageObject);

}