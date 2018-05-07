package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：标准类别表类
 */
public class BaseSnlClassify implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //标准编号
    private String classifyNo;

    //1-国际标准 2-国家标准  3-术语标准
    private Integer snlType;

    //中文名
    private String nameCn;

    //英文名
    private String nameUs;

    //创建者
    private String creator;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * 获得标准编号
     * @return String
     */
    public String getClassifyNo(){
        return this.classifyNo;
    }

    /**
     * 设置标准编号
     * @param classifyNo  标准编号
     */
    public void setClassifyNo(String classifyNo){
        this.classifyNo = classifyNo;
    }
    /**
     * 获得1-国际标准 2-国家标准  3-术语标准
     * @return Integer
     */
    public Integer getSnlType(){
        return this.snlType;
    }

    /**
     * 设置1-国际标准 2-国家标准  3-术语标准
     * @param snlType  1-国际标准 2-国家标准  3-术语标准
     */
    public void setSnlType(Integer snlType){
        this.snlType = snlType;
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
		this.classifyNo = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.classifyNo;
	}
	
	
	@Override
	public String toString() {
		return "BaseSnlClassify ["
		 		+ "this.classifyNo=" + this.classifyNo + ", "
		 		+ "this.snlType=" + this.snlType + ", "
		 		+ "this.nameCn=" + this.nameCn + ", "
		 		+ "this.nameUs=" + this.nameUs + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}