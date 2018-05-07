package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-09 <br/>
 * 描述：用户与角色关系表类
 */
public class SysUserRole implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //
    private String userRoleId;

    //用户id
    private String userId;

    //角色id
    private String roleId;


    /**
     * 获得
     * @return String
     */
    public String getUserRoleId(){
        return this.userRoleId;
    }

    /**
     * 设置
     * @param userRoleId  
     */
    public void setUserRoleId(String userRoleId){
        this.userRoleId = userRoleId;
    }
    /**
     * 获得用户id
     * @return String
     */
    public String getUserId(){
        return this.userId;
    }

    /**
     * 设置用户id
     * @param userId  用户id
     */
    public void setUserId(String userId){
        this.userId = userId;
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
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.userRoleId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.userRoleId;
	}
	
	
	@Override
	public String toString() {
		return "SysUserRole ["
		 		+ "this.userRoleId=" + this.userRoleId + ", "
		 		+ "this.userId=" + this.userId + ", "
		 		+ "this.roleId=" + this.roleId + ", "
		+ "]";   
	}
}