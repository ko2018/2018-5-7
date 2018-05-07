package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：角色类
 */
public class SysRole implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //role_id
    private String roleId;

    //角色名称
    private String roleName;

    //角色类型
    private String roleType;

    //状态default:0
    private String roleStatus;

    //是否删除（N:未删除，Y:已删除）
    private String deleteStatus;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得role_id
     * @return String
     */
    public String getRoleId(){
        return this.roleId;
    }

    /**
     * 设置role_id
     * @param roleId  role_id
     */
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }
    /**
     * 获得角色名称
     * @return String
     */
    public String getRoleName(){
        return this.roleName;
    }

    /**
     * 设置角色名称
     * @param roleName  角色名称
     */
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
    /**
     * 获得角色类型
     * @return String
     */
    public String getRoleType(){
        return this.roleType;
    }

    /**
     * 设置角色类型
     * @param roleType  角色类型
     */
    public void setRoleType(String roleType){
        this.roleType = roleType;
    }
    /**
     * 获得状态default:0
     * @return String
     */
    public String getRoleStatus(){
        return this.roleStatus;
    }

    /**
     * 设置状态default:0
     * @param roleStatus  状态default:0
     */
    public void setRoleStatus(String roleStatus){
        this.roleStatus = roleStatus;
    }
    /**
     * 获得是否删除（N:未删除，Y:已删除）
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置是否删除（N:未删除，Y:已删除）
     * @param deleteStatus  是否删除（N:未删除，Y:已删除）
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
    }
    /**
     * 获得创建者
     * @return String
     */
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置创建者
     * @param creator  创建者
     */
    public void setCreator(String creator){
        this.creator = creator;
    }
    /**
     * 获得创建时间
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置创建时间
     * @param addTime  创建时间
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得更新时间
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置更新时间
     * @param updateTime  更新时间
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.roleId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.roleId;
	}
	
	
	@Override
	public String toString() {
		return "SysRole ["
		 		+ "this.roleId=" + this.roleId + ", "
		 		+ "this.roleName=" + this.roleName + ", "
		 		+ "this.roleType=" + this.roleType + ", "
		 		+ "this.roleStatus=" + this.roleStatus + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}