package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：样本类型表类
 */
public class BaseSampleClassify implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //类型编号
    private String classifyCode;

    //类型名称
    private String classifyName;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    private Date updateTime;


    /**
     * 获得类型编号
     * @return String
     */
    public String getClassifyCode(){
        return this.classifyCode;
    }

    /**
     * 设置类型编号
     * @param classifyCode  类型编号
     */
    public void setClassifyCode(String classifyCode){
        this.classifyCode = classifyCode;
    }
    /**
     * 获得类型名称
     * @return String
     */
    public String getClassifyName(){
        return this.classifyName;
    }

    /**
     * 设置类型名称
     * @param classifyName  类型名称
     */
    public void setClassifyName(String classifyName){
        this.classifyName = classifyName;
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
		this.classifyCode = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.classifyCode;
	}
	
	
	@Override
	public String toString() {
		return "BaseSampleClassify ["
		 		+ "this.classifyCode=" + this.classifyCode + ", "
		 		+ "this.classifyName=" + this.classifyName + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}