package com.talent.dcs.entity;

import java.util.Date;

import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-20 <br/>
 * 描述：不合法修改文件表类
 */
public class BaseDocValueFile implements BaseEntity {

    private static final long serialVersionUID = 1L;

    // 文件ID
    private String valuefileId;

    // 数据类型
    private String docId;

    // 字典ID
    private Integer dictId;

    // 文件地址
    private String valuefilePath;

    // 是否修改
    private String isChange;

    // 版本号（乐观锁）(备用)
    private Integer versionId;

    // 文件MD5值(备用)
    private String docMd5;

    // 创建者
    private String creator;

    // 删除状态
    private String deleteStatus;

    // 创建时间
    private Date addTime;

    // 更新者
    private String updateUser;

    // 更新时间
    private Date updateTime;

    /**
     * 获得文件ID
     * 
     * @return String
     */
    public String getValuefileId() {
        return this.valuefileId;
    }

    /**
     * 设置文件ID
     * 
     * @param valuefileId 文件ID
     */
    public void setValuefileId(String valuefileId) {
        this.valuefileId = valuefileId;
    }

    /**
     * 获得数据类型
     * 
     * @return String
     */
    public String getDocId() {
        return this.docId;
    }

    /**
     * 设置数据类型
     * 
     * @param docId 数据类型
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * 获得字典ID
     * 
     * @return Integer
     */
    public Integer getDictId() {
        return this.dictId;
    }

    /**
     * 设置字典ID
     * 
     * @param dictId 字典ID
     */
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    /**
     * 获得文件地址
     * 
     * @return String
     */
    public String getValuefilePath() {
        return this.valuefilePath;
    }

    /**
     * 设置文件地址
     * 
     * @param valuefilePath 文件地址
     */
    public void setValuefilePath(String valuefilePath) {
        this.valuefilePath = valuefilePath;
    }

    /**
     * 获得是否修改
     * 
     * @return String
     */
    public String getIsChange() {
        return this.isChange;
    }

    /**
     * 设置是否修改
     * 
     * @param isChange 是否修改
     */
    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    /**
     * 获得版本号（乐观锁）(备用)
     * 
     * @return Integer
     */
    public Integer getVersionId() {
        return this.versionId;
    }

    /**
     * 设置版本号（乐观锁）(备用)
     * 
     * @param versionId 版本号（乐观锁）(备用)
     */
    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    /**
     * 获得文件MD5值(备用)
     * 
     * @return String
     */
    public String getDocMd5() {
        return this.docMd5;
    }

    /**
     * 设置文件MD5值(备用)
     * 
     * @param docMd5 文件MD5值(备用)
     */
    public void setDocMd5(String docMd5) {
        this.docMd5 = docMd5;
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
     * 获得删除状态
     * 
     * @return String
     */
    public String getDeleteStatus() {
        return this.deleteStatus;
    }

    /**
     * 设置删除状态
     * 
     * @param deleteStatus 删除状态
     */
    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
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
        this.valuefileId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.valuefileId;
    }

    @Override
    public String toString() {
        return "BaseDocValueFile [" + "this.valuefileId=" + this.valuefileId + ", " + "this.docId=" + this.docId + ", "
                + "this.dictId=" + this.dictId + ", " + "this.valuefilePath=" + this.valuefilePath + ", "
                + "this.isChange=" + this.isChange + ", " + "this.versionId=" + this.versionId + ", " + "this.docMd5="
                + this.docMd5 + ", " + "this.creator=" + this.creator + ", " + "this.deleteStatus=" + this.deleteStatus
                + ", " + "this.addTime=" + this.addTime + ", " + "this.updateUser=" + this.updateUser + ", "
                + "this.updateTime=" + this.updateTime + ", " + "]";
    }
}