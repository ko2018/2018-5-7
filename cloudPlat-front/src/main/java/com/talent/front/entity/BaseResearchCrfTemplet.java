package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：科研CRF模板表类
 */
public class BaseResearchCrfTemplet implements BaseEntity {

    private static final long serialVersionUID = 1L;

    // 模板id
    private String templetId;

    // 课题id
    private String researchId;

    // 模板名称
    private String templetName;

    // 描述说明
    private String briefInfo;

    // 文件路径
    private String filePath;

    // 是否删除
    private Integer isDel;

    // 创建者
    private String creator;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    // 操作者
    private String updateUser;

    // 操作时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 获得模板id
     * 
     * @return String
     */
    public String getTempletId() {
        return this.templetId;
    }

    /**
     * 设置模板id
     * 
     * @param templetId 模板id
     */
    public void setTempletId(String templetId) {
        this.templetId = templetId;
    }

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
     * 获得模板名称
     * 
     * @return String
     */
    public String getTempletName() {
        return this.templetName;
    }

    /**
     * 设置模板名称
     * 
     * @param templetName 模板名称
     */
    public void setTempletName(String templetName) {
        this.templetName = templetName;
    }

    /**
     * 获得描述说明
     * 
     * @return String
     */
    public String getBriefInfo() {
        return this.briefInfo;
    }

    /**
     * 设置描述说明
     * 
     * @param briefInfo 描述说明
     */
    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    /**
     * 获得文件路径
     * 
     * @return String
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * 设置文件路径
     * 
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
     * 获得操作者
     * 
     * @return String
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 设置操作者
     * 
     * @param updateUser 操作者
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获得操作时间
     * 
     * @return Date
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置操作时间
     * 
     * @param updateTime 操作时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 设置公共ID
     */
    @Override
    public void set_Id(String id) {
        this.templetId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.templetId;
    }

    @Override
    public String toString() {
        return "BaseResearchCrfTemplet [" + "this.templetId=" + this.templetId + ", " + "this.researchId="
                + this.researchId + ", " + "this.templetName=" + this.templetName + ", " + "this.briefInfo="
                + this.briefInfo + ", " + "this.filePath=" + this.filePath + ", " + "this.isDel=" + this.isDel + ", "
                + "this.creator=" + this.creator + ", " + "this.addTime=" + this.addTime + ", " + "this.updateUser="
                + this.updateUser + ", " + "this.updateTime=" + this.updateTime + ", " + "]";
    }
}