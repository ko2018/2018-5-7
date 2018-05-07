package com.talent.dcs.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：疾病诊断逻辑类
 */
public class BaseDiseasesLogic implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String logicId;

    //用户id
    private String userId;

    //疾病id
    private String diseasesId;

    //简介
    private String briefInfo;

    //是否默认
    private Integer isDefault;

    //是否删除
    private Integer isDel;

    //诊断逻辑
    private String diagLogic;

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
     * 获得id
     * @return String
     */
    public String getLogicId(){
        return this.logicId;
    }

    /**
     * 设置id
     * @param logicId  id
     */
    public void setLogicId(String logicId){
        this.logicId = logicId;
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
     * 获得疾病id
     * @return String
     */
    public String getDiseasesId(){
        return this.diseasesId;
    }

    /**
     * 设置疾病id
     * @param diseasesId  疾病id
     */
    public void setDiseasesId(String diseasesId){
        this.diseasesId = diseasesId;
    }
    /**
     * 获得简介
     * @return String
     */
    public String getBriefInfo(){
        return this.briefInfo;
    }

    /**
     * 设置简介
     * @param briefInfo  简介
     */
    public void setBriefInfo(String briefInfo){
        this.briefInfo = briefInfo;
    }
    /**
     * 获得是否默认
     * @return Integer
     */
    public Integer getIsDefault(){
        return this.isDefault;
    }

    /**
     * 设置是否默认
     * @param isDefault  是否默认
     */
    public void setIsDefault(Integer isDefault){
        this.isDefault = isDefault;
    }
    /**
     * 获得是否删除
     * @return Integer
     */
    public Integer getIsDel(){
        return this.isDel;
    }

    /**
     * 设置是否删除
     * @param isDel  是否删除
     */
    public void setIsDel(Integer isDel){
        this.isDel = isDel;
    }
    /**
     * 获得诊断逻辑
     * @return String
     */
    public String getDiagLogic(){
        return this.diagLogic;
    }

    /**
     * 设置诊断逻辑
     * @param diagLogic  诊断逻辑
     */
    public void setDiagLogic(String diagLogic){
        this.diagLogic = diagLogic;
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
		this.logicId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.logicId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDiseasesLogic ["
		 		+ "this.logicId=" + this.logicId + ", "
		 		+ "this.userId=" + this.userId + ", "
		 		+ "this.diseasesId=" + this.diseasesId + ", "
		 		+ "this.briefInfo=" + this.briefInfo + ", "
		 		+ "this.isDefault=" + this.isDefault + ", "
		 		+ "this.isDel=" + this.isDel + ", "
		 		+ "this.diagLogic=" + this.diagLogic + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}