package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-06 <br/>
 * 描述：诊断运算类
 */
public class BaseDiagCalc implements BaseEntity {

    private static final long serialVersionUID = 1L;

    // id
    private String calcId;

    // 用户id
    private String userId;

    // 逻辑id
    private String logicId;

    // 名称
    private String calcName;

    // 备注
    private String remark;

    // 诊断数据项
    private String diagItem;

    // 运算类型(1-lt 2-le 3-eq 4-gt 5-ge)
    private Integer calcType;

    // 结果类型(1-空 2-0 3-1 4-空白)
    private Integer resultType;

    // 结果数量
    private Integer resultAmount;

    // 是否删除
    private Integer isDel;

    // 创建者
    private String creator;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    // 更新者
    private String updateUser;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 获得id
     * 
     * @return String
     */
    public String getCalcId() {
        return this.calcId;
    }

    /**
     * 设置id
     * 
     * @param calcId id
     */
    public void setCalcId(String calcId) {
        this.calcId = calcId;
    }

    /**
     * 获得用户id
     * 
     * @return String
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置用户id
     * 
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获得逻辑id
     * 
     * @return String
     */
    public String getLogicId() {
        return this.logicId;
    }

    /**
     * 设置逻辑id
     * 
     * @param logicId 逻辑id
     */
    public void setLogicId(String logicId) {
        this.logicId = logicId;
    }

    /**
     * 获得名称
     * 
     * @return String
     */
    public String getCalcName() {
        return this.calcName;
    }

    /**
     * 设置名称
     * 
     * @param calcName 名称
     */
    public void setCalcName(String calcName) {
        this.calcName = calcName;
    }

    /**
     * 获得备注
     * 
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获得诊断数据项
     * 
     * @return String
     */
    public String getDiagItem() {
        return this.diagItem;
    }

    /**
     * 设置诊断数据项
     * 
     * @param diagItem 诊断数据项
     */
    public void setDiagItem(String diagItem) {
        this.diagItem = diagItem;
    }

    /**
     * 获得运算类型(1-lt 2-le 3-eq 4-gt 5-ge)
     * 
     * @return Integer
     */
    public Integer getCalcType() {
        return this.calcType;
    }

    /**
     * 设置运算类型(1-lt 2-le 3-eq 4-gt 5-ge)
     * 
     * @param calcType 运算类型(1-lt 2-le 3-eq 4-gt 5-ge)
     */
    public void setCalcType(Integer calcType) {
        this.calcType = calcType;
    }

    /**
     * 获得结果类型(1-空 2-0 3-1 4-空白)
     * 
     * @return Integer
     */
    public Integer getResultType() {
        return this.resultType;
    }

    /**
     * 设置结果类型(1-空 2-0 3-1 4-空白)
     * 
     * @param resultType 结果类型(1-空 2-0 3-1 4-空白)
     */
    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    /**
     * 获得结果数量
     * 
     * @return Integer
     */
    public Integer getResultAmount() {
        return this.resultAmount;
    }

    /**
     * 设置结果数量
     * 
     * @param resultAmount 结果数量
     */
    public void setResultAmount(Integer resultAmount) {
        this.resultAmount = resultAmount;
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
        this.calcId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.calcId;
    }

    @Override
    public String toString() {
        return "BaseDiagCalc [" + "this.calcId=" + this.calcId + ", " + "this.userId=" + this.userId + ", "
                + "this.logicId=" + this.logicId + ", " + "this.calcName=" + this.calcName + ", " + "this.remark="
                + this.remark + ", " + "this.diagItem=" + this.diagItem + ", " + "this.calcType=" + this.calcType + ", "
                + "this.resultType=" + this.resultType + ", " + "this.resultAmount=" + this.resultAmount + ", "
                + "this.isDel=" + this.isDel + ", " + "this.creator=" + this.creator + ", " + "this.addTime="
                + this.addTime + ", " + "this.updateUser=" + this.updateUser + ", " + "this.updateTime="
                + this.updateTime + ", " + "]";
    }
}