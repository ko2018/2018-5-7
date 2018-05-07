package com.talent.dcs.entity;

import java.util.Date;

import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-04 <br/>
 * 描述：文档值通用不合法值记录类
 */
public class BaseDocDefValue implements BaseEntity {

    private static final long serialVersionUID = 1L;

    //
    private String docdefvalueId;

    //
    private Integer dictId;

    //
    private String docId;

    //
    private String docdefvalueValue;

    //
    private String docdefvalueValuenew;

    //
    private Integer docdefvalueNum;

    //
    private String isLegal;

    //
    private String isReplace;

    //
    private String deleteStatus;

    //
    private String creator;

    //
    private Date addTime;

    //
    private Date updateTime;

    /**
     * 获得
     * 
     * @return String
     */
    public String getDocdefvalueId() {
        return this.docdefvalueId;
    }

    /**
     * 设置
     * 
     * @param docdefvalueId
     */
    public void setDocdefvalueId(String docdefvalueId) {
        this.docdefvalueId = docdefvalueId;
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
    public String getDocId() {
        return this.docId;
    }

    /**
     * 设置
     * 
     * @param docId
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getDocdefvalueValue() {
        return this.docdefvalueValue;
    }

    /**
     * 设置
     * 
     * @param docdefvalueValue
     */
    public void setDocdefvalueValue(String docdefvalueValue) {
        this.docdefvalueValue = docdefvalueValue;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getDocdefvalueValuenew() {
        return this.docdefvalueValuenew;
    }

    /**
     * 设置
     * 
     * @param docdefvalueValuenew
     */
    public void setDocdefvalueValuenew(String docdefvalueValuenew) {
        this.docdefvalueValuenew = docdefvalueValuenew;
    }

    /**
     * 获得
     * 
     * @return Integer
     */
    public Integer getDocdefvalueNum() {
        return this.docdefvalueNum;
    }

    /**
     * 设置
     * 
     * @param docdefvalueNum
     */
    public void setDocdefvalueNum(Integer docdefvalueNum) {
        this.docdefvalueNum = docdefvalueNum;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getIsLegal() {
        return this.isLegal;
    }

    /**
     * 设置
     * 
     * @param isLegal
     */
    public void setIsLegal(String isLegal) {
        this.isLegal = isLegal;
    }

    /**
     * 获得
     * 
     * @return String
     */
    public String getIsReplace() {
        return this.isReplace;
    }

    /**
     * 设置
     * 
     * @param isReplace
     */
    public void setIsReplace(String isReplace) {
        this.isReplace = isReplace;
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
        this.docdefvalueId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.docdefvalueId;
    }

    @Override
    public String toString() {
        return "BaseDocDefValue [" + "this.docdefvalueId=" + this.docdefvalueId + ", " + "this.dictId=" + this.dictId
                + ", " + "this.docId=" + this.docId + ", " + "this.docdefvalueValue=" + this.docdefvalueValue + ", "
                + "this.docdefvalueValuenew=" + this.docdefvalueValuenew + ", " + "this.docdefvalueNum="
                + this.docdefvalueNum + ", " + "this.isLegal=" + this.isLegal + ", " + "this.isReplace="
                + this.isReplace + ", " + "this.deleteStatus=" + this.deleteStatus + ", " + "this.creator="
                + this.creator + ", " + "this.addTime=" + this.addTime + ", " + "this.updateTime=" + this.updateTime
                + ", " + "]";
    }
}