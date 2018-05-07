package com.talent.dcs.entity;

import java.util.Date;

import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-01 <br/>
 * 描述：机构表类
 */
public class BaseInstitution implements BaseEntity {

    private static final long serialVersionUID = 1L;

    // 机构id
    private String institutionId;

    // 机构Code
    private String institutionCode;

    // 机构名称
    private String institutionName;

    // 机构图片
    private String institutionLogo;

    // 机构级别id
    private String institutionlevelId;

    // 机构级别名称
    private String institutionlevelName;

    // 机构所在区
    private String areaId;

    // 省市区字符串
    private String areaNameAll;

    // 机构详细地址
    private String institutionAddress;

    // 经度
    private Double longitude;

    // 纬度
    private Double latitude;

    // 机构联系人
    private String institutionOwner;

    // 机构电话
    private String institutionOwnerPhone;

    // 办公室电话
    private String institutionPhone;

    //
    private String deleteStatus;

    // 创建者
    private String creator;

    // 创建时间
    private Date addTime;

    // 更新时间
    private Date updateTime;

    /**
     * 获得机构id
     * 
     * @return String
     */
    public String getInstitutionId() {
        return this.institutionId;
    }

    /**
     * 设置机构id
     * 
     * @param institutionId 机构id
     */
    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    /**
     * 获得机构Code
     * 
     * @return String
     */
    public String getInstitutionCode() {
        return this.institutionCode;
    }

    /**
     * 设置机构Code
     * 
     * @param institutionCode 机构Code
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    /**
     * 获得机构名称
     * 
     * @return String
     */
    public String getInstitutionName() {
        return this.institutionName;
    }

    /**
     * 设置机构名称
     * 
     * @param institutionName 机构名称
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    /**
     * 获得机构图片
     * 
     * @return String
     */
    public String getInstitutionLogo() {
        return this.institutionLogo;
    }

    /**
     * 设置机构图片
     * 
     * @param institutionLogo 机构图片
     */
    public void setInstitutionLogo(String institutionLogo) {
        this.institutionLogo = institutionLogo;
    }

    /**
     * 获得机构级别id
     * 
     * @return String
     */
    public String getInstitutionlevelId() {
        return this.institutionlevelId;
    }

    /**
     * 设置机构级别id
     * 
     * @param institutionlevelId 机构级别id
     */
    public void setInstitutionlevelId(String institutionlevelId) {
        this.institutionlevelId = institutionlevelId;
    }

    /**
     * 获得机构级别名称
     * 
     * @return String
     */
    public String getInstitutionlevelName() {
        return this.institutionlevelName;
    }

    /**
     * 设置机构级别名称
     * 
     * @param institutionlevelName 机构级别名称
     */
    public void setInstitutionlevelName(String institutionlevelName) {
        this.institutionlevelName = institutionlevelName;
    }

    /**
     * 获得机构所在区
     * 
     * @return String
     */
    public String getAreaId() {
        return this.areaId;
    }

    /**
     * 设置机构所在区
     * 
     * @param areaId 机构所在区
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * 获得省市区字符串
     * 
     * @return String
     */
    public String getAreaNameAll() {
        return this.areaNameAll;
    }

    /**
     * 设置省市区字符串
     * 
     * @param areaNameAll 省市区字符串
     */
    public void setAreaNameAll(String areaNameAll) {
        this.areaNameAll = areaNameAll;
    }

    /**
     * 获得机构详细地址
     * 
     * @return String
     */
    public String getInstitutionAddress() {
        return this.institutionAddress;
    }

    /**
     * 设置机构详细地址
     * 
     * @param institutionAddress 机构详细地址
     */
    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }

    /**
     * 获得经度
     * 
     * @return Double
     */
    public Double getLongitude() {
        return this.longitude;
    }

    /**
     * 设置经度
     * 
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获得纬度
     * 
     * @return Double
     */
    public Double getLatitude() {
        return this.latitude;
    }

    /**
     * 设置纬度
     * 
     * @param latitude 纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 获得机构联系人
     * 
     * @return String
     */
    public String getInstitutionOwner() {
        return this.institutionOwner;
    }

    /**
     * 设置机构联系人
     * 
     * @param institutionOwner 机构联系人
     */
    public void setInstitutionOwner(String institutionOwner) {
        this.institutionOwner = institutionOwner;
    }

    /**
     * 获得机构电话
     * 
     * @return String
     */
    public String getInstitutionOwnerPhone() {
        return this.institutionOwnerPhone;
    }

    /**
     * 设置机构电话
     * 
     * @param institutionOwnerPhone 机构电话
     */
    public void setInstitutionOwnerPhone(String institutionOwnerPhone) {
        this.institutionOwnerPhone = institutionOwnerPhone;
    }

    /**
     * 获得办公室电话
     * 
     * @return String
     */
    public String getInstitutionPhone() {
        return this.institutionPhone;
    }

    /**
     * 设置办公室电话
     * 
     * @param institutionPhone 办公室电话
     */
    public void setInstitutionPhone(String institutionPhone) {
        this.institutionPhone = institutionPhone;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getDeleteStatus() {
        return this.deleteStatus;
    }

    /**
     * 设置
     * 
     * @param deleteStatus
     */
    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 获得创建者
     * 
     * @return String
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * 设置创建者
     * 
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获得创建时间
     * 
     * @return Date
     */
    public Date getAddTime() {
        return this.addTime;
    }

    /**
     * 设置创建时间
     * 
     * @param addTime 创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获得更新时间
     * 
     * @return Date
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置更新时间
     * 
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 设置公共ID
     */
    @Override
    public void set_Id(String id) {
        this.institutionId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.institutionId;
    }

    @Override
    public String toString() {
        return "BaseInstitution [" + "this.institutionId=" + this.institutionId + ", " + "this.institutionCode="
                + this.institutionCode + ", " + "this.institutionName=" + this.institutionName + ", "
                + "this.institutionLogo=" + this.institutionLogo + ", " + "this.institutionlevelId="
                + this.institutionlevelId + ", " + "this.institutionlevelName=" + this.institutionlevelName + ", "
                + "this.areaId=" + this.areaId + ", " + "this.areaNameAll=" + this.areaNameAll + ", "
                + "this.institutionAddress=" + this.institutionAddress + ", " + "this.longitude=" + this.longitude
                + ", " + "this.latitude=" + this.latitude + ", " + "this.institutionOwner=" + this.institutionOwner
                + ", " + "this.institutionOwnerPhone=" + this.institutionOwnerPhone + ", " + "this.institutionPhone="
                + this.institutionPhone + ", " + "this.deleteStatus=" + this.deleteStatus + ", " + "this.creator="
                + this.creator + ", " + "this.addTime=" + this.addTime + ", " + "this.updateTime=" + this.updateTime
                + ", " + "]";
    }
}