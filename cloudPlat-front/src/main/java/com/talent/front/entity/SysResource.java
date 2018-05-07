package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-01 <br/>
 * 描述：系统资源类
 */
public class SysResource implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //resource_id
    private String resourceId;

    //资源名称
    private String resourceName;

    //访问url
    private String resourceUrl;

    //是否叶子(Y/N)
    private String isLeaf;

    //父id
    private String parentId;

    //权限名称
    private String permission;

    //操作权限名
    private String operCode;

    //权限类型
    private String resoyrceType;

    //状态default:0
    private String resoyrceStatus;

    //图标
    private String accessoryId;

    //是否删除（N:未删除，Y:已删除）
    private String deleteStatus;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得resource_id
     * @return String
     */
    public String getResourceId(){
        return this.resourceId;
    }

    /**
     * 设置resource_id
     * @param resourceId  resource_id
     */
    public void setResourceId(String resourceId){
        this.resourceId = resourceId;
    }
    /**
     * 获得资源名称
     * @return String
     */
    public String getResourceName(){
        return this.resourceName;
    }

    /**
     * 设置资源名称
     * @param resourceName  资源名称
     */
    public void setResourceName(String resourceName){
        this.resourceName = resourceName;
    }
    /**
     * 获得访问url
     * @return String
     */
    public String getResourceUrl(){
        return this.resourceUrl;
    }

    /**
     * 设置访问url
     * @param resourceUrl  访问url
     */
    public void setResourceUrl(String resourceUrl){
        this.resourceUrl = resourceUrl;
    }
    /**
     * 获得是否叶子(Y/N)
     * @return String
     */
    public String getIsLeaf(){
        return this.isLeaf;
    }

    /**
     * 设置是否叶子(Y/N)
     * @param isLeaf  是否叶子(Y/N)
     */
    public void setIsLeaf(String isLeaf){
        this.isLeaf = isLeaf;
    }
    /**
     * 获得父id
     * @return String
     */
    public String getParentId(){
        return this.parentId;
    }

    /**
     * 设置父id
     * @param parentId  父id
     */
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    /**
     * 获得权限名称
     * @return String
     */
    public String getPermission(){
        return this.permission;
    }

    /**
     * 设置权限名称
     * @param permission  权限名称
     */
    public void setPermission(String permission){
        this.permission = permission;
    }
    /**
     * 获得操作权限名
     * @return String
     */
    public String getOperCode(){
        return this.operCode;
    }

    /**
     * 设置操作权限名
     * @param operCode  操作权限名
     */
    public void setOperCode(String operCode){
        this.operCode = operCode;
    }
    /**
     * 获得权限类型
     * @return String
     */
    public String getResoyrceType(){
        return this.resoyrceType;
    }

    /**
     * 设置权限类型
     * @param resoyrceType  权限类型
     */
    public void setResoyrceType(String resoyrceType){
        this.resoyrceType = resoyrceType;
    }
    /**
     * 获得状态default:0
     * @return String
     */
    public String getResoyrceStatus(){
        return this.resoyrceStatus;
    }

    /**
     * 设置状态default:0
     * @param resoyrceStatus  状态default:0
     */
    public void setResoyrceStatus(String resoyrceStatus){
        this.resoyrceStatus = resoyrceStatus;
    }
    /**
     * 获得图标
     * @return String
     */
    public String getAccessoryId(){
        return this.accessoryId;
    }

    /**
     * 设置图标
     * @param accessoryId  图标
     */
    public void setAccessoryId(String accessoryId){
        this.accessoryId = accessoryId;
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
		this.resourceId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.resourceId;
	}
	
	
	@Override
	public String toString() {
		return "SysResource ["
		 		+ "this.resourceId=" + this.resourceId + ", "
		 		+ "this.resourceName=" + this.resourceName + ", "
		 		+ "this.resourceUrl=" + this.resourceUrl + ", "
		 		+ "this.isLeaf=" + this.isLeaf + ", "
		 		+ "this.parentId=" + this.parentId + ", "
		 		+ "this.permission=" + this.permission + ", "
		 		+ "this.operCode=" + this.operCode + ", "
		 		+ "this.resoyrceType=" + this.resoyrceType + ", "
		 		+ "this.resoyrceStatus=" + this.resoyrceStatus + ", "
		 		+ "this.accessoryId=" + this.accessoryId + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}