package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-25 <br/>
 * 描述：机构标准术语版本类
 */
public class BaseInstitutionSnlVersion implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //机构标准术语版本Id
    private String institutionSnlVersionId;

    //机构id
    private String institutionId;

    //版本号
    private Integer versionCode;

    //版本名称
    private String versionName;

    //字段数量
    private Integer fieldCount;

    //是否完成映射
    private String isMapping;

    //版本文件路径
    private String versionPath;

    //删除状态
    private String deleteStatus;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得机构标准术语版本Id
     * @return String
     */
    public String getInstitutionSnlVersionId(){
        return this.institutionSnlVersionId;
    }

    /**
     * 设置机构标准术语版本Id
     * @param institutionSnlVersionId  机构标准术语版本Id
     */
    public void setInstitutionSnlVersionId(String institutionSnlVersionId){
        this.institutionSnlVersionId = institutionSnlVersionId;
    }
    /**
     * 获得机构id
     * @return String
     */
    public String getInstitutionId(){
        return this.institutionId;
    }

    /**
     * 设置机构id
     * @param institutionId  机构id
     */
    public void setInstitutionId(String institutionId){
        this.institutionId = institutionId;
    }
    /**
     * 获得版本号
     * @return Integer
     */
    public Integer getVersionCode(){
        return this.versionCode;
    }

    /**
     * 设置版本号
     * @param versionCode  版本号
     */
    public void setVersionCode(Integer versionCode){
        this.versionCode = versionCode;
    }
    /**
     * 获得版本名称
     * @return String
     */
    public String getVersionName(){
        return this.versionName;
    }

    /**
     * 设置版本名称
     * @param versionName  版本名称
     */
    public void setVersionName(String versionName){
        this.versionName = versionName;
    }
    /**
     * 获得字段数量
     * @return Integer
     */
    public Integer getFieldCount(){
        return this.fieldCount;
    }

    /**
     * 设置字段数量
     * @param fieldCount  字段数量
     */
    public void setFieldCount(Integer fieldCount){
        this.fieldCount = fieldCount;
    }
    /**
     * 获得是否完成映射
     * @return String
     */
    public String getIsMapping(){
        return this.isMapping;
    }

    /**
     * 设置是否完成映射
     * @param isMapping  是否完成映射
     */
    public void setIsMapping(String isMapping){
        this.isMapping = isMapping;
    }
    /**
     * 获得版本文件路径
     * @return String
     */
    public String getVersionPath(){
        return this.versionPath;
    }

    /**
     * 设置版本文件路径
     * @param versionPath  版本文件路径
     */
    public void setVersionPath(String versionPath){
        this.versionPath = versionPath;
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
		this.institutionSnlVersionId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.institutionSnlVersionId;
	}
	
	
	@Override
	public String toString() {
		return "BaseInstitutionSnlVersion ["
		 		+ "this.institutionSnlVersionId=" + this.institutionSnlVersionId + ", "
		 		+ "this.institutionId=" + this.institutionId + ", "
		 		+ "this.versionCode=" + this.versionCode + ", "
		 		+ "this.versionName=" + this.versionName + ", "
		 		+ "this.fieldCount=" + this.fieldCount + ", "
		 		+ "this.isMapping=" + this.isMapping + ", "
		 		+ "this.versionPath=" + this.versionPath + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}