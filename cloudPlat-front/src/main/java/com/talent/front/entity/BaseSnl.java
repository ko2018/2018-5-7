package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-17 <br/>
 * 描述：标准术语表类
 */
public class BaseSnl implements BaseEntity {

    private static final long serialVersionUID = 1L;

    // id
    private String snlId;

    // 标准类别
    private Integer snlType;

    // 类别名
    private String snlTypeName;

    // 术语编码
    private String snlCode;

    // 中文名
    private String nameCn;

    // 中文全拼
    private String fullNameCn;

    // 中文名缩写
    private String shortNameCn;

    // 英文名
    private String nameUs;

    // 英文名缩写
    private String shortNameUs;

    // 父节点编码
    private String parentCode;

    // 深度
    private Integer depth;

    // 是否叶子节点
    private Integer isLeaf;

    // 字典id
    private Integer dictId;

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

    // 是否删除
    private Integer isDel;

    // 单位
    private String unit;

    // 是否可以删除
    private Integer isCanDel;

    public Integer getIsCanDel() {
        return isCanDel;
    }

    public void setIsCanDel(Integer isCanDel) {
        this.isCanDel = isCanDel;
    }

    /**
     * 获得id
     * 
     * @return String
     */
    public String getSnlId() {
        return this.snlId;
    }

    /**
     * 设置id
     * 
     * @param snlId id
     */
    public void setSnlId(String snlId) {
        this.snlId = snlId;
    }

    /**
     * 获得标准类别
     * 
     * @return Integer
     */
    public Integer getSnlType() {
        return this.snlType;
    }

    /**
     * 设置标准类别
     * 
     * @param snlType 标准类别
     */
    public void setSnlType(Integer snlType) {
        this.snlType = snlType;
    }

    /**
     * 获得类别名
     * 
     * @return String
     */
    public String getSnlTypeName() {
        return this.snlTypeName;
    }

    /**
     * 设置类别名
     * 
     * @param snlTypeName 类别名
     */
    public void setSnlTypeName(String snlTypeName) {
        this.snlTypeName = snlTypeName;
    }

    /**
     * 获得术语编码
     * 
     * @return String
     */
    public String getSnlCode() {
        return this.snlCode;
    }

    /**
     * 设置术语编码
     * 
     * @param snlCode 术语编码
     */
    public void setSnlCode(String snlCode) {
        this.snlCode = snlCode;
    }

    /**
     * 获得中文名
     * 
     * @return String
     */
    public String getNameCn() {
        return this.nameCn;
    }

    /**
     * 设置中文名
     * 
     * @param nameCn 中文名
     */
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    /**
     * 获得中文全拼
     * 
     * @return String
     */
    public String getFullNameCn() {
        return this.fullNameCn;
    }

    /**
     * 设置中文全拼
     * 
     * @param fullNameCn 中文全拼
     */
    public void setFullNameCn(String fullNameCn) {
        this.fullNameCn = fullNameCn;
    }

    /**
     * 获得中文名缩写
     * 
     * @return String
     */
    public String getShortNameCn() {
        return this.shortNameCn;
    }

    /**
     * 设置中文名缩写
     * 
     * @param shortNameCn 中文名缩写
     */
    public void setShortNameCn(String shortNameCn) {
        this.shortNameCn = shortNameCn;
    }

    /**
     * 获得英文名
     * 
     * @return String
     */
    public String getNameUs() {
        return this.nameUs;
    }

    /**
     * 设置英文名
     * 
     * @param nameUs 英文名
     */
    public void setNameUs(String nameUs) {
        this.nameUs = nameUs;
    }

    /**
     * 获得英文名缩写
     * 
     * @return String
     */
    public String getShortNameUs() {
        return this.shortNameUs;
    }

    /**
     * 设置英文名缩写
     * 
     * @param shortNameUs 英文名缩写
     */
    public void setShortNameUs(String shortNameUs) {
        this.shortNameUs = shortNameUs;
    }

    /**
     * 获得父节点编码
     * 
     * @return String
     */
    public String getParentCode() {
        return this.parentCode;
    }

    /**
     * 设置父节点编码
     * 
     * @param parentCode 父节点编码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 获得深度
     * 
     * @return Integer
     */
    public Integer getDepth() {
        return this.depth;
    }

    /**
     * 设置深度
     * 
     * @param depth 深度
     */
    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    /**
     * 获得是否叶子节点
     * 
     * @return Integer
     */
    public Integer getIsLeaf() {
        return this.isLeaf;
    }

    /**
     * 设置是否叶子节点
     * 
     * @param isLeaf 是否叶子节点
     */
    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 获得字典id
     * 
     * @return Integer
     */
    public Integer getDictId() {
        return this.dictId;
    }

    /**
     * 设置字典id
     * 
     * @param dictId 字典id
     */
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
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
     * 获得单位
     * 
     * @return String
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * 设置单位
     * 
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 设置公共ID
     */
    @Override
    public void set_Id(String id) {
        this.snlId = id;
    }

    /**
     * 得到公共ID
     */
    @Override
    public String get_Id() {
        return this.snlId;
    }

    @Override
    public String toString() {
        return "BaseSnl [" + "this.snlId=" + this.snlId + ", " + "this.snlType=" + this.snlType + ", "
                + "this.snlTypeName=" + this.snlTypeName + ", " + "this.snlCode=" + this.snlCode + ", " + "this.nameCn="
                + this.nameCn + ", " + "this.fullNameCn=" + this.fullNameCn + ", " + "this.shortNameCn="
                + this.shortNameCn + ", " + "this.nameUs=" + this.nameUs + ", " + "this.shortNameUs=" + this.shortNameUs
                + ", " + "this.parentCode=" + this.parentCode + ", " + "this.depth=" + this.depth + ", "
                + "this.isLeaf=" + this.isLeaf + ", " + "this.dictId=" + this.dictId + ", " + "this.creator="
                + this.creator + ", " + "this.addTime=" + this.addTime + ", " + "this.updateUser=" + this.updateUser
                + ", " + "this.updateTime=" + this.updateTime + ", " + "this.isDel=" + this.isDel + ", " + "this.unit="
                + this.unit + ", " + "]";
    }
}