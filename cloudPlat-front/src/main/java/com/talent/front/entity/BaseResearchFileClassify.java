package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档类型类
 */
public class BaseResearchFileClassify implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //类型id
    private String classifyId;

    //类型名称
    private String classifyName;

    //描述说明
    private String briefInfo;

    //创建者
    private String creator;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * 获得类型id
     * @return String
     */
    public String getClassifyId(){
        return this.classifyId;
    }

    /**
     * 设置类型id
     * @param classifyId  类型id
     */
    public void setClassifyId(String classifyId){
        this.classifyId = classifyId;
    }
    /**
     * 获得类型名称
     * @return String
     */
    public String getClassifyName(){
        return this.classifyName;
    }

    /**
     * 设置类型名称
     * @param classifyName  类型名称
     */
    public void setClassifyName(String classifyName){
        this.classifyName = classifyName;
    }
    /**
     * 获得描述说明
     * @return String
     */
    public String getBriefInfo(){
        return this.briefInfo;
    }

    /**
     * 设置描述说明
     * @param briefInfo  描述说明
     */
    public void setBriefInfo(String briefInfo){
        this.briefInfo = briefInfo;
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
     * 获得更新者
     * @return String
     */
    public String getUpdateUser(){
        return this.updateUser;
    }

    /**
     * 设置更新者
     * @param updateUser  更新者
     */
    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
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
		this.classifyId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.classifyId;
	}
	
	
	@Override
	public String toString() {
		return "BaseResearchFileClassify ["
		 		+ "this.classifyId=" + this.classifyId + ", "
		 		+ "this.classifyName=" + this.classifyName + ", "
		 		+ "this.briefInfo=" + this.briefInfo + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}