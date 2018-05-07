package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：省市区类
 */
public class Area implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //地区id
    private String areaId;

    //地区名称
    private String areaName;

    //N:未删除；Y：已删除
    private String deleteStatus;

    //级别
    private Integer level;

    //排序值
    private Integer sequence;

    //父节点
    private String parentId;

    //是否为常见地区（N：不是；Y:是）
    private String common;

    //创建时间
    private Date addTime;


    /**
     * 获得地区id
     * @return String
     */
    public String getAreaId(){
        return this.areaId;
    }

    /**
     * 设置地区id
     * @param areaId  地区id
     */
    public void setAreaId(String areaId){
        this.areaId = areaId;
    }
    /**
     * 获得地区名称
     * @return String
     */
    public String getAreaName(){
        return this.areaName;
    }

    /**
     * 设置地区名称
     * @param areaName  地区名称
     */
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }
    /**
     * 获得N:未删除；Y：已删除
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置N:未删除；Y：已删除
     * @param deleteStatus  N:未删除；Y：已删除
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
    }
    /**
     * 获得级别
     * @return Integer
     */
    public Integer getLevel(){
        return this.level;
    }

    /**
     * 设置级别
     * @param level  级别
     */
    public void setLevel(Integer level){
        this.level = level;
    }
    /**
     * 获得排序值
     * @return Integer
     */
    public Integer getSequence(){
        return this.sequence;
    }

    /**
     * 设置排序值
     * @param sequence  排序值
     */
    public void setSequence(Integer sequence){
        this.sequence = sequence;
    }
    /**
     * 获得父节点
     * @return String
     */
    public String getParentId(){
        return this.parentId;
    }

    /**
     * 设置父节点
     * @param parentId  父节点
     */
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    /**
     * 获得是否为常见地区（N：不是；Y:是）
     * @return String
     */
    public String getCommon(){
        return this.common;
    }

    /**
     * 设置是否为常见地区（N：不是；Y:是）
     * @param common  是否为常见地区（N：不是；Y:是）
     */
    public void setCommon(String common){
        this.common = common;
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
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.areaId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.areaId;
	}
	
	
	@Override
	public String toString() {
		return "Area ["
		 		+ "this.areaId=" + this.areaId + ", "
		 		+ "this.areaName=" + this.areaName + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.level=" + this.level + ", "
		 		+ "this.sequence=" + this.sequence + ", "
		 		+ "this.parentId=" + this.parentId + ", "
		 		+ "this.common=" + this.common + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		+ "]";   
	}
}