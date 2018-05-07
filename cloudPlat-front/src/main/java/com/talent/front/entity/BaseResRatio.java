package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源占比类
 */
public class BaseResRatio implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //
    private String resratioId;

    //
    private String resratioName;

    //
    private Double resratioValue;

    //
    private String resratioTypeid;

    //
    private String resratioTypename;

    //
    private String deleteStatus;

    //
    private String creator;

    //
    private Date addTime;

    //
    private String updateUser;

    //
    private Date updateTime;


    /**
     * 获得
     * @return String
     */
    public String getResratioId(){
        return this.resratioId;
    }

    /**
     * 设置
     * @param resratioId  
     */
    public void setResratioId(String resratioId){
        this.resratioId = resratioId;
    }
    /**
     * 获得
     * @return String
     */
    public String getResratioName(){
        return this.resratioName;
    }

    /**
     * 设置
     * @param resratioName  
     */
    public void setResratioName(String resratioName){
        this.resratioName = resratioName;
    }
    /**
     * 获得
     * @return Double
     */
    public Double getResratioValue(){
        return this.resratioValue;
    }

    /**
     * 设置
     * @param resratioValue  
     */
    public void setResratioValue(Double resratioValue){
        this.resratioValue = resratioValue;
    }
    /**
     * 获得
     * @return String
     */
    public String getResratioTypeid(){
        return this.resratioTypeid;
    }

    /**
     * 设置
     * @param resratioTypeid  
     */
    public void setResratioTypeid(String resratioTypeid){
        this.resratioTypeid = resratioTypeid;
    }
    /**
     * 获得
     * @return String
     */
    public String getResratioTypename(){
        return this.resratioTypename;
    }

    /**
     * 设置
     * @param resratioTypename  
     */
    public void setResratioTypename(String resratioTypename){
        this.resratioTypename = resratioTypename;
    }
    /**
     * 获得
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置
     * @param deleteStatus  
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
    }
    /**
     * 获得
     * @return String
     */
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置
     * @param creator  
     */
    public void setCreator(String creator){
        this.creator = creator;
    }
    /**
     * 获得
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置
     * @param addTime  
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得
     * @return String
     */
    public String getUpdateUser(){
        return this.updateUser;
    }

    /**
     * 设置
     * @param updateUser  
     */
    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
    }
    /**
     * 获得
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置
     * @param updateTime  
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.resratioId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.resratioId;
	}
	
	
	@Override
	public String toString() {
		return "BaseResRatio ["
		 		+ "this.resratioId=" + this.resratioId + ", "
		 		+ "this.resratioName=" + this.resratioName + ", "
		 		+ "this.resratioValue=" + this.resratioValue + ", "
		 		+ "this.resratioTypeid=" + this.resratioTypeid + ", "
		 		+ "this.resratioTypename=" + this.resratioTypename + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}