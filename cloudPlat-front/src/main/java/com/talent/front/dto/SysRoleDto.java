package com.talent.front.dto;

import com.talent.front.entity.SysRole;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：角色Dto类
 */
public class SysRoleDto extends SysRole {

	@Override
	public String toString() {
		return "SysRole ["
		 		+ "this.roleId=" + this.getRoleId() + ", "
		 		+ "this.roleName=" + this.getRoleName() + ", "
		 		+ "this.roleType=" + this.getRoleType() + ", "
		 		+ "this.roleStatus=" + this.getRoleStatus() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}