package com.talent.dcs.entity;

import java.util.Date;

import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-17 <br/>
 * 描述：文档值显示记录表类
 */
public class BaseDocValue implements BaseEntity {

    private static final long serialVersionUID = 1L;

    // 文档值id
    private String docvalueId;

    // 术语集id(base_snl->dict_id)
    private Integer dictId;

    // 文档id
    private String docId;

    // 文档当前值
    private String docvalueValue;

    // 替换后的值
    private String docvalueValuenew;

    // 出现次数
    private Integer docvalueNum;

    // 是否合法
    private String isLegal;

    // 是否修改完成
    private String isReplace;

    // 删除状态
    private String deleteStatus;

    // 创建者
    private String creator;

    // 创建时间
    private Date addTime;

    // 更新时间
    private Date updateTime;

    /**
     * 获得文档值id
     * 
     * @return String
     */
    public String getDocvalueId() {
        return this.docvalueId;
    }

    /**
     * 设置文档值id
     * 
     * @param docvalueId 文档值id
     */
    public void setDocvalueId(String docvalueId) {
        this.docvalueId = docvalueId;
    }

    /**
     * 获得术语集id(base_snl->dict_id)
     * 
     * @return Integer
     */
    public Integer getDictId() {
        return this.dictId;
    }

    /**
     * 设置术语集id(base_snl->dict_id)
     * 
     * @param dictId 术语集id(base_snl->dict_id)
     */
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    /**
     * 获得文档id
     * 
     * @return String
     */
    public String getDocId() {
        return this.docId;
    }

    /**
     * 设置文档id
     * 
     * @param docId 文档id
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * 获得文档当前值
     * 
     * @return String
     */
    public String getDocvalueValue() {
        return this.docvalueValue;
    }

    /**
     * 设置文档当前值
     * 
     * @param docvalueValue 文档当前值
     */
    public void setDocvalueValue(String docvalueValue) {
        this.docvalueValue = docvalueValue;
    }

    /**
     * 获得替换后的值
     * 
     * @return String
     */
    public String getDocvalueValuenew() {
        return this.docvalueValuenew;
    }

    /**
     * 设置替换后的值
     * 
     * @param docvalueValuenew 替换后的值
     */
    public void setDocvalueValuenew(String docvalueValuenew) {
        this.docvalueValuenew = docvalueValuenew;
    }

    /**
     * 获得出现次数
     * 
     * @return Integer
     */
    public Integer getDocvalueNum() {
        return this.docvalueNum;
    }

    /**
     * 设置出现次数
     * 
     * @param docvalueNum 出现次数
     */
    public void setDocvalueNum(Integer docvalueNum) {
        this.docvalueNum = docvalueNum;
    }

    /**
     * 获得是否合法
     * 
     * @return String
     */
    public String getIsLegal() {
        return this.isLegal;
    }

    /**
     * 设置是否合法
     * 
     * @param isLegal 是否合法
     */
    public void setIsLegal(String isLegal) {
        this.isLegal = isLegal;
    }

    /**
     * 获得是否修改完成
     * 
     * @return String
     */
    public String getIsReplace() {
        return this.isReplace;
    }

    /**
     * 设置是否修改完成
     * 
     * @param isReplace 是否修改完成
     */
    public void setIsReplace(String isReplace) {
        this.isReplace = isReplace;
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
        this.docvalueId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.docvalueId;
    }

    @Override
    public String toString() {
        return "BaseDocValue [" + "this.docvalueId=" + this.docvalueId + ", " + "this.dictId=" + this.dictId + ", "
                + "this.docId=" + this.docId + ", " + "this.docvalueValue=" + this.docvalueValue + ", "
                + "this.docvalueValuenew=" + this.docvalueValuenew + ", " + "this.docvalueNum=" + this.docvalueNum
                + ", " + "this.isLegal=" + this.isLegal + ", " + "this.isReplace=" + this.isReplace + ", "
                + "this.deleteStatus=" + this.deleteStatus + ", " + "this.creator=" + this.creator + ", "
                + "this.addTime=" + this.addTime + ", " + "this.updateTime=" + this.updateTime + ", " + "]";
    }
}