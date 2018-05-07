package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断逻辑类
 */
public class BaseDiagLogic implements BaseEntity {

    private static final long serialVersionUID = 1L;

    // id
    private String diagLogicId;

    // 用户id
    private String userId;

    // 逻辑id
    private String logicId;

    // 备注
    private String remark;

    // 逻辑顺序
    private Integer logicSeq;

    // 结果类型(1-空 2-0 3-1 4-空白)
    private Integer resultType;

    // 是否删除
    private Integer isDel;

    // 诊断逻辑
    private String diagLogic;

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
    public String getDiagLogicId() {
        return this.diagLogicId;
    }

    /**
     * 设置id
     * 
     * @param diagLogicId id
     */
    public void setDiagLogicId(String diagLogicId) {
        this.diagLogicId = diagLogicId;
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
     * 获得逻辑顺序
     * 
     * @return Integer
     */
    public Integer getLogicSeq() {
        return this.logicSeq;
    }

    /**
     * 设置逻辑顺序
     * 
     * @param logicSeq 逻辑顺序
     */
    public void setLogicSeq(Integer logicSeq) {
        this.logicSeq = logicSeq;
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
     * 获得诊断逻辑
     * 
     * @return String
     */
    public String getDiagLogic() {
        return this.diagLogic;
    }

    /**
     * 设置诊断逻辑
     * 
     * @param diagLogic 诊断逻辑
     */
    public void setDiagLogic(String diagLogic) {
        this.diagLogic = diagLogic;
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
        this.diagLogicId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.diagLogicId;
    }

    @Override
    public String toString() {
        return "BaseDiagLogic [" + "this.diagLogicId=" + this.diagLogicId + ", " + "this.userId=" + this.userId + ", "
                + "this.logicId=" + this.logicId + ", " + "this.remark=" + this.remark + ", " + "this.logicSeq="
                + this.logicSeq + ", " + "this.resultType=" + this.resultType + ", " + "this.isDel=" + this.isDel + ", "
                + "this.diagLogic=" + this.diagLogic + ", " + "this.creator=" + this.creator + ", " + "this.addTime="
                + this.addTime + ", " + "this.updateUser=" + this.updateUser + ", " + "this.updateTime="
                + this.updateTime + ", " + "]";
    }
}