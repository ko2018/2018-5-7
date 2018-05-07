package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：文档类型类
 */
public class BaseDocType implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //文档类型ID
    private String doctypeId;

    //文档类型名称
    private String doctypeName;

    //序列号
    private Integer sequence;

    //删除状态
    private String deleteStatus;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得文档类型ID
     * @return String
     */
    public String getDoctypeId(){
        return this.doctypeId;
    }

    /**
     * 设置文档类型ID
     * @param doctypeId  文档类型ID
     */
    public void setDoctypeId(String doctypeId){
        this.doctypeId = doctypeId;
    }
    /**
     * 获得文档类型名称
     * @return String
     */
    public String getDoctypeName(){
        return this.doctypeName;
    }

    /**
     * 设置文档类型名称
     * @param doctypeName  文档类型名称
     */
    public void setDoctypeName(String doctypeName){
        this.doctypeName = doctypeName;
    }
    /**
     * 获得序列号
     * @return Integer
     */
    public Integer getSequence(){
        return this.sequence;
    }

    /**
     * 设置序列号
     * @param sequence  序列号
     */
    public void setSequence(Integer sequence){
        this.sequence = sequence;
    }
    /**
     * 获得删除状态
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置删除状态
     * @param deleteStatus  删除状态
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
		this.doctypeId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.doctypeId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDocType ["
		 		+ "this.doctypeId=" + this.doctypeId + ", "
		 		+ "this.doctypeName=" + this.doctypeName + ", "
		 		+ "this.sequence=" + this.sequence + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}