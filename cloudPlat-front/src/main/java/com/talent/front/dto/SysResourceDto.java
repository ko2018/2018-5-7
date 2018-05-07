package com.talent.front.dto;

import com.talent.front.entity.SysResource;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-01 <br/>
 * 描述：系统资源Dto类
 */
public class SysResourceDto extends SysResource {

	@Override
	public String toString() {
		return "SysResource [" + "this.resourceId=" + this.getResourceId() + ", " + "this.resourceName="
				+ this.getResourceName() + ", " + "this.resourceUrl=" + this.getResourceUrl() + ", " + "this.isLeaf="
				+ this.getIsLeaf() + ", " + "this.parentId=" + this.getParentId() + ", " + "this.permission="
				+ this.getPermission() + ", " + "this.operCode=" + this.getOperCode() + ", " + "this.resoyrceType="
				+ this.getResoyrceType() + ", " + "this.resoyrceStatus=" + this.getResoyrceStatus() + ", "
				+ "this.accessoryId=" + this.getAccessoryId() + ", " + "this.deleteStatus=" + this.getDeleteStatus()
				+ ", " + "this.creator=" + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", "
				+ "this.updateTime=" + this.getUpdateTime() + ", " + "]";
	}

	private String rolePermission;

	public String getRolePermission() {
		return rolePermission;
	}

	public void setRolePermission(String rolePermission) {
		this.rolePermission = rolePermission;
	}

}