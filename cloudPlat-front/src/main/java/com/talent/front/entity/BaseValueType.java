package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：数据类型表类
 */
public class BaseValueType implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //标准编号
    private String valuetypeId;

    //数据类型名称
    private String valuetypeName;

    //序号
    private Integer sequence;

    //是否删除
    private String deleteStatus;

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
    public String getValuetypeId(){
        return this.valuetypeId;
    }

    /**
     * 设置标准编号
     * @param valuetypeId  标准编号
     */
    public void setValuetypeId(String valuetypeId){
        this.valuetypeId = valuetypeId;
    }
    /**
     * 获得数据类型名称
     * @return String
     */
    public String getValuetypeName(){
        return this.valuetypeName;
    }

    /**
     * 设置数据类型名称
     * @param valuetypeName  数据类型名称
     */
    public void setValuetypeName(String valuetypeName){
        this.valuetypeName = valuetypeName;
    }
    /**
     * 获得序号
     * @return Integer
     */
    public Integer getSequence(){
        return this.sequence;
    }

    /**
     * 设置序号
     * @param sequence  序号
     */
    public void setSequence(Integer sequence){
        this.sequence = sequence;
    }
    /**
     * 获得是否删除
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置是否删除
     * @param deleteStatus  是否删除
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
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
		this.valuetypeId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.valuetypeId;
	}
	
	
	@Override
	public String toString() {
		return "BaseValueType ["
		 		+ "this.valuetypeId=" + this.valuetypeId + ", "
		 		+ "this.valuetypeName=" + this.valuetypeName + ", "
		 		+ "this.sequence=" + this.sequence + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}