package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：清洗数据表类
 */
public class BaseDataClean implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //原始数据ID
    private String datacleanId;

    //身份编号
    private String userCode;

    //机构编码
    private String institutionId;

    //数据编号
    private String checkCode;

    //数据类型
    private String docId;

    //数据对象
    private String dataObject;

    //错误标识
    private String errorFlag;

    //创建者
    private String creator;

    //删除状态
    private String deleteStatus;

    //创建时间
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    private Date updateTime;


    /**
     * 获得原始数据ID
     * @return String
     */
    public String getDatacleanId(){
        return this.datacleanId;
    }

    /**
     * 设置原始数据ID
     * @param datacleanId  原始数据ID
     */
    public void setDatacleanId(String datacleanId){
        this.datacleanId = datacleanId;
    }
    /**
     * 获得身份编号
     * @return String
     */
    public String getUserCode(){
        return this.userCode;
    }

    /**
     * 设置身份编号
     * @param userCode  身份编号
     */
    public void setUserCode(String userCode){
        this.userCode = userCode;
    }
    /**
     * 获得机构编码
     * @return String
     */
    public String getInstitutionId(){
        return this.institutionId;
    }

    /**
     * 设置机构编码
     * @param institutionId  机构编码
     */
    public void setInstitutionId(String institutionId){
        this.institutionId = institutionId;
    }
    /**
     * 获得数据编号
     * @return String
     */
    public String getCheckCode(){
        return this.checkCode;
    }

    /**
     * 设置数据编号
     * @param checkCode  数据编号
     */
    public void setCheckCode(String checkCode){
        this.checkCode = checkCode;
    }
    /**
     * 获得数据类型
     * @return String
     */
    public String getDocId(){
        return this.docId;
    }

    /**
     * 设置数据类型
     * @param docId  数据类型
     */
    public void setDocId(String docId){
        this.docId = docId;
    }
    /**
     * 获得数据对象
     * @return String
     */
    public String getDataObject(){
        return this.dataObject;
    }

    /**
     * 设置数据对象
     * @param dataObject  数据对象
     */
    public void setDataObject(String dataObject){
        this.dataObject = dataObject;
    }
    /**
     * 获得错误标识
     * @return String
     */
    public String getErrorFlag(){
        return this.errorFlag;
    }

    /**
     * 设置错误标识
     * @param errorFlag  错误标识
     */
    public void setErrorFlag(String errorFlag){
        this.errorFlag = errorFlag;
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
     * 获得删除状态
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置删除状态
     * @param deleteStatus  删除状态
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
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
		this.datacleanId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.datacleanId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDataClean ["
		 		+ "this.datacleanId=" + this.datacleanId + ", "
		 		+ "this.userCode=" + this.userCode + ", "
		 		+ "this.institutionId=" + this.institutionId + ", "
		 		+ "this.checkCode=" + this.checkCode + ", "
		 		+ "this.docId=" + this.docId + ", "
		 		+ "this.dataObject=" + this.dataObject + ", "
		 		+ "this.errorFlag=" + this.errorFlag + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}