package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：角色与资源表类
 */
public class SysRoleResource implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //role_resource_id
    private String roleResourceId;

    //角色id
    private String roleId;

    //资源id
    private String resourceId;

    //角色权限
    private String rolePermission;


    /**
     * 获得role_resource_id
     * @return String
     */
    public String getRoleResourceId(){
        return this.roleResourceId;
    }

    /**
     * 设置role_resource_id
     * @param roleResourceId  role_resource_id
     */
    public void setRoleResourceId(String roleResourceId){
        this.roleResourceId = roleResourceId;
    }
    /**
     * 获得角色id
     * @return String
     */
    public String getRoleId(){
        return this.roleId;
    }

    /**
     * 设置角色id
     * @param roleId  角色id
     */
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }
    /**
     * 获得资源id
     * @return String
     */
    public String getResourceId(){
        return this.resourceId;
    }

    /**
     * 设置资源id
     * @param resourceId  资源id
     */
    public void setResourceId(String resourceId){
        this.resourceId = resourceId;
    }
    /**
     * 获得角色权限
     * @return String
     */
    public String getRolePermission(){
        return this.rolePermission;
    }

    /**
     * 设置角色权限
     * @param rolePermission  角色权限
     */
    public void setRolePermission(String rolePermission){
        this.rolePermission = rolePermission;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.roleResourceId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.roleResourceId;
	}
	
	
	@Override
	public String toString() {
		return "SysRoleResource ["
		 		+ "this.roleResourceId=" + this.roleResourceId + ", "
		 		+ "this.roleId=" + this.roleId + ", "
		 		+ "this.resourceId=" + this.resourceId + ", "
		 		+ "this.rolePermission=" + this.rolePermission + ", "
		+ "]";   
	}
}