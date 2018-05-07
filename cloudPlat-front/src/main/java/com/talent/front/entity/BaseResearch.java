package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研管理表类
 */
public class BaseResearch implements BaseEntity {

    private static final long serialVersionUID = 1L;

    // 课题id
    private String researchId;

    // 课题名称
    private String researchName;

    // 状态
    private String status;

    // 简介
    private String briefInfo;

    // 是否删除
    private Integer isDel;

    // 创建者
    private String creator;

    // 开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    // 结束时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    // 更新者
    private String updateUser;

    private String majorUserId;

    private String majorUserName;

    private String majorInsId;

    private String majorInsName;

    public String getMajorUserId() {
        return majorUserId;
    }

    public void setMajorUserId(String majorUserId) {
        this.majorUserId = majorUserId;
    }

    public String getMajorUserName() {
        return majorUserName;
    }

    public void setMajorUserName(String majorUserName) {
        this.majorUserName = majorUserName;
    }

    public String getMajorInsId() {
        return majorInsId;
    }

    public void setMajorInsId(String majorInsId) {
        this.majorInsId = majorInsId;
    }

    public String getMajorInsName() {
        return majorInsName;
    }

    public void setMajorInsName(String majorInsName) {
        this.majorInsName = majorInsName;
    }

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 获得课题id
     * 
     * @return String
     */
    public String getResearchId() {
        return this.researchId;
    }

    /**
     * 设置课题id
     * 
     * @param researchId 课题id
     */
    public void setResearchId(String researchId) {
        this.researchId = researchId;
    }

    /**
     * 获得课题名称
     * 
     * @return String
     */
    public String getResearchName() {
        return this.researchName;
    }

    /**
     * 设置课题名称
     * 
     * @param researchName 课题名称
     */
    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    /**
     * 获得状态
     * 
     * @return String
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态
     * 
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获得简介
     * 
     * @return String
     */
    public String getBriefInfo() {
        return this.briefInfo;
    }

    /**
     * 设置简介
     * 
     * @param briefInfo 简介
     */
    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    /**
     * 获得是否删除
     * 
     * @return Integer
     */
    public Integer getIsDel() {
        return this.isDel;
    }

    /**
     * 设置是否删除
     * 
     * @param isDel 是否删除
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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
     * 获得开始时间
     * 
     * @return Date
     */
    public Date getBeginTime() {
        return this.beginTime;
    }

    /**
     * 设置开始时间
     * 
     * @param beginTime 开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获得结束时间
     * 
     * @return Date
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置结束时间
     * 
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     * 获得更新者
     * 
     * @return String
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 设置更新者
     * 
     * @param updateUser 更新者
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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
        this.researchId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.researchId;
    }

    @Override
    public String toString() {
        return "BaseResearch [" + "this.researchId=" + this.researchId + ", " + "this.researchName=" + this.researchName
                + ", " + "this.status=" + this.status + ", " + "this.briefInfo=" + this.briefInfo + ", " + "this.isDel="
                + this.isDel + ", " + "this.creator=" + this.creator + ", " + "this.beginTime=" + this.beginTime + ", "
                + "this.endTime=" + this.endTime + ", " + "this.addTime=" + this.addTime + ", " + "this.updateUser="
                + this.updateUser + ", " + "this.updateTime=" + this.updateTime + ", " + "]";
    }
}