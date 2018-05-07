package com.talent.dcs.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：知识库表(疾病表)类
 */
public class BaseDiseasesSys implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String diseasesId;

    //编码
    private String diseasesCode;

    //中文名
    private String nameCn;

    //中文名全名
    private String fullNameCn;

    //中文名缩写
    private String shortNameCn;

    //英文名
    private String nameUs;

    //英文名缩写
    private String shortNameUs;

    //父节点id
    private String parentId;

    //简介
    private String briefInfo;

    //是否叶子节点
    private Integer isLeaf;

    //是否删除
    private Integer isDel;

    //深度
    private Integer depth;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    private Date updateTime;


    /**
     * 获得id
     * @return String
     */
    public String getDiseasesId(){
        return this.diseasesId;
    }

    /**
     * 设置id
     * @param diseasesId  id
     */
    public void setDiseasesId(String diseasesId){
        this.diseasesId = diseasesId;
    }
    /**
     * 获得编码
     * @return String
     */
    public String getDiseasesCode(){
        return this.diseasesCode;
    }

    /**
     * 设置编码
     * @param diseasesCode  编码
     */
    public void setDiseasesCode(String diseasesCode){
        this.diseasesCode = diseasesCode;
    }
    /**
     * 获得中文名
     * @return String
     */
    public String getNameCn(){
        return this.nameCn;
    }

    /**
     * 设置中文名
     * @param nameCn  中文名
     */
    public void setNameCn(String nameCn){
        this.nameCn = nameCn;
    }
    /**
     * 获得中文名全名
     * @return String
     */
    public String getFullNameCn(){
        return this.fullNameCn;
    }

    /**
     * 设置中文名全名
     * @param fullNameCn  中文名全名
     */
    public void setFullNameCn(String fullNameCn){
        this.fullNameCn = fullNameCn;
    }
    /**
     * 获得中文名缩写
     * @return String
     */
    public String getShortNameCn(){
        return this.shortNameCn;
    }

    /**
     * 设置中文名缩写
     * @param shortNameCn  中文名缩写
     */
    public void setShortNameCn(String shortNameCn){
        this.shortNameCn = shortNameCn;
    }
    /**
     * 获得英文名
     * @return String
     */
    public String getNameUs(){
        return this.nameUs;
    }

    /**
     * 设置英文名
     * @param nameUs  英文名
     */
    public void setNameUs(String nameUs){
        this.nameUs = nameUs;
    }
    /**
     * 获得英文名缩写
     * @return String
     */
    public String getShortNameUs(){
        return this.shortNameUs;
    }

    /**
     * 设置英文名缩写
     * @param shortNameUs  英文名缩写
     */
    public void setShortNameUs(String shortNameUs){
        this.shortNameUs = shortNameUs;
    }
    /**
     * 获得父节点id
     * @return String
     */
    public String getParentId(){
        return this.parentId;
    }

    /**
     * 设置父节点id
     * @param parentId  父节点id
     */
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    /**
     * 获得简介
     * @return String
     */
    public String getBriefInfo(){
        return this.briefInfo;
    }

    /**
     * 设置简介
     * @param briefInfo  简介
     */
    public void setBriefInfo(String briefInfo){
        this.briefInfo = briefInfo;
    }
    /**
     * 获得是否叶子节点
     * @return Integer
     */
    public Integer getIsLeaf(){
        return this.isLeaf;
    }

    /**
     * 设置是否叶子节点
     * @param isLeaf  是否叶子节点
     */
    public void setIsLeaf(Integer isLeaf){
        this.isLeaf = isLeaf;
    }
    /**
     * 获得是否删除
     * @return Integer
     */
    public Integer getIsDel(){
        return this.isDel;
    }

    /**
     * 设置是否删除
     * @param isDel  是否删除
     */
    public void setIsDel(Integer isDel){
        this.isDel = isDel;
    }
    /**
     * 获得深度
     * @return Integer
     */
    public Integer getDepth(){
        return this.depth;
    }

    /**
     * 设置深度
     * @param depth  深度
     */
    public void setDepth(Integer depth){
        this.depth = depth;
    }
    /**
     * 获得创建者
     * @return String
     */
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置创建者
     * @param creator  创建者
     */
    public void setCreator(String creator){
        this.creator = creator;
    }
    /**
     * 获得创建时间
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置创建时间
     * @param addTime  创建时间
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得更新者
     * @return String
     */
    public String getUpdateUser(){
        return this.updateUser;
    }

    /**
     * 设置更新者
     * @param updateUser  更新者
     */
    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
    }
    /**
     * 获得更新时间
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置更新时间
     * @param updateTime  更新时间
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.diseasesId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.diseasesId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDiseasesSys ["
		 		+ "this.diseasesId=" + this.diseasesId + ", "
		 		+ "this.diseasesCode=" + this.diseasesCode + ", "
		 		+ "this.nameCn=" + this.nameCn + ", "
		 		+ "this.fullNameCn=" + this.fullNameCn + ", "
		 		+ "this.shortNameCn=" + this.shortNameCn + ", "
		 		+ "this.nameUs=" + this.nameUs + ", "
		 		+ "this.shortNameUs=" + this.shortNameUs + ", "
		 		+ "this.parentId=" + this.parentId + ", "
		 		+ "this.briefInfo=" + this.briefInfo + ", "
		 		+ "this.isLeaf=" + this.isLeaf + ", "
		 		+ "this.isDel=" + this.isDel + ", "
		 		+ "this.depth=" + this.depth + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}