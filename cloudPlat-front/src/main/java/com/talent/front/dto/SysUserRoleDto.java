package com.talent.front.dto;

import com.talent.front.entity.SysUserRole;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-09 <br/>
 * 描述：用户与角色关系表Dto类
 */
public class SysUserRoleDto extends SysUserRole {

	@Override
	public String toString() {
		return "SysUserRole ["
		 		+ "this.userRoleId=" + this.getUserRoleId() + ", "
		 		+ "this.userId=" + this.getUserId() + ", "
		 		+ "this.roleId=" + this.getRoleId() + ", "
		+ "]";   
	}

}