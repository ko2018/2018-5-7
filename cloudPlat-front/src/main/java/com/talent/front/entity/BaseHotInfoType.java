package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息类型表类
 */
public class BaseHotInfoType implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String infoTypeId;

    //类别名称
    private String infoTypeName;

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
    public String getInfoTypeId(){
        return this.infoTypeId;
    }

    /**
     * 设置id
     * @param infoTypeId  id
     */
    public void setInfoTypeId(String infoTypeId){
        this.infoTypeId = infoTypeId;
    }
    /**
     * 获得类别名称
     * @return String
     */
    public String getInfoTypeName(){
        return this.infoTypeName;
    }

    /**
     * 设置类别名称
     * @param infoTypeName  类别名称
     */
    public void setInfoTypeName(String infoTypeName){
        this.infoTypeName = infoTypeName;
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
		this.infoTypeId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.infoTypeId;
	}
	
	
	@Override
	public String toString() {
		return "BaseHotInfoType ["
		 		+ "this.infoTypeId=" + this.infoTypeId + ", "
		 		+ "this.infoTypeName=" + this.infoTypeName + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}