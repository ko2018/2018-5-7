package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-26 <br/>
 * 描述：关键字表类
 */
public class BaseKeyword implements BaseEntity {

    private static final long serialVersionUID = 1L;

    //
    private String keywordId;

    //
    private String insId;

    //
    private String snlId;

    //
    private Integer dictId;

    //
    private String keywordCode;

    //
    private String keywordContent;

    //
    private String keywordDesc;

    //
    private Integer isDel;

    //
    private String creator;

    //
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    //
    private String updateUser;

    //
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 获得
     * 
     * @return String
     */
    public String getKeywordId() {
        return this.keywordId;
    }

    /**
     * 设置
     * 
     * @param keywordId
     */
    public void setKeywordId(String keywordId) {
        this.keywordId = keywordId;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getInsId() {
        return this.insId;
    }

    /**
     * 设置
     * 
     * @param insId
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getSnlId() {
        return this.snlId;
    }

    /**
     * 设置
     * 
     * @param snlId
     */
    public void setSnlId(String snlId) {
        this.snlId = snlId;
    }

    /**
     * 获得
     * 
     * @return Integer
     */
    public Integer getDictId() {
        return this.dictId;
    }

    /**
     * 设置
     * 
     * @param dictId
     */
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getKeywordCode() {
        return this.keywordCode;
    }

    /**
     * 设置
     * 
     * @param keywordCode
     */
    public void setKeywordCode(String keywordCode) {
        this.keywordCode = keywordCode;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getKeywordContent() {
        return this.keywordContent;
    }

    /**
     * 设置
     * 
     * @param keywordContent
     */
    public void setKeywordContent(String keywordContent) {
        this.keywordContent = keywordContent;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getKeywordDesc() {
        return this.keywordDesc;
    }

    /**
     * 设置
     * 
     * @param keywordDesc
     */
    public void setKeywordDesc(String keywordDesc) {
        this.keywordDesc = keywordDesc;
    }

    /**
     * 获得
     * 
     * @return Integer
     */
    public Integer getIsDel() {
        return this.isDel;
    }

    /**
     * 设置
     * 
     * @param isDel
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * 设置
     * 
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获得
     * 
     * @return Date
     */
    public Date getAddTime() {
        return this.addTime;
    }

    /**
     * 设置
     * 
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 设置
     * 
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获得
     * 
     * @return Date
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置
     * 
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 设置公共ID
     */
    @Override
    public void set_Id(String id) {
        this.keywordId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.keywordId;
    }

    @Override
    public String toString() {
        return "BaseKeyword [" + "this.keywordId=" + this.keywordId + ", " + "this.insId=" + this.insId + ", "
                + "this.snlId=" + this.snlId + ", " + "this.dictId=" + this.dictId + ", " + "this.keywordCode="
                + this.keywordCode + ", " + "this.keywordContent=" + this.keywordContent + ", " + "this.keywordDesc="
                + this.keywordDesc + ", " + "this.isDel=" + this.isDel + ", " + "this.creator=" + this.creator + ", "
                + "this.addTime=" + this.addTime + ", " + "this.updateUser=" + this.updateUser + ", "
                + "this.updateTime=" + this.updateTime + ", " + "]";
    }
}