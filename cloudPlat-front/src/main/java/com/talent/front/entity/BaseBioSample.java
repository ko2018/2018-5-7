package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：生物样本表类
 */
public class BaseBioSample implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //样本id
    private String sampleId;

    //样本类型
    private String classifyCode;

    //样本数量
    private Integer quantity;

    //入库时间
    private Date stockTime;

    //入库位置
    private String stockPosition;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    private Date updateTime;


    /**
     * 获得样本id
     * @return String
     */
    public String getSampleId(){
        return this.sampleId;
    }

    /**
     * 设置样本id
     * @param sampleId  样本id
     */
    public void setSampleId(String sampleId){
        this.sampleId = sampleId;
    }
    /**
     * 获得样本类型
     * @return String
     */
    public String getClassifyCode(){
        return this.classifyCode;
    }

    /**
     * 设置样本类型
     * @param classifyCode  样本类型
     */
    public void setClassifyCode(String classifyCode){
        this.classifyCode = classifyCode;
    }
    /**
     * 获得样本数量
     * @return Integer
     */
    public Integer getQuantity(){
        return this.quantity;
    }

    /**
     * 设置样本数量
     * @param quantity  样本数量
     */
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    /**
     * 获得入库时间
     * @return Date
     */
    public Date getStockTime(){
        return this.stockTime;
    }

    /**
     * 设置入库时间
     * @param stockTime  入库时间
     */
    public void setStockTime(Date stockTime){
        this.stockTime = stockTime;
    }
    /**
     * 获得入库位置
     * @return String
     */
    public String getStockPosition(){
        return this.stockPosition;
    }

    /**
     * 设置入库位置
     * @param stockPosition  入库位置
     */
    public void setStockPosition(String stockPosition){
        this.stockPosition = stockPosition;
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
		this.sampleId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.sampleId;
	}
	
	
	@Override
	public String toString() {
		return "BaseBioSample ["
		 		+ "this.sampleId=" + this.sampleId + ", "
		 		+ "this.classifyCode=" + this.classifyCode + ", "
		 		+ "this.quantity=" + this.quantity + ", "
		 		+ "this.stockTime=" + this.stockTime + ", "
		 		+ "this.stockPosition=" + this.stockPosition + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}