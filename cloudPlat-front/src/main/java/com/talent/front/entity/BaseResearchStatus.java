package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：课题状态表类
 */
public class BaseResearchStatus implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //课题id
    private String researchId;

    //序号
    private Integer seq;

    //状态
    private String status;

    //
    private String operatorName;

    //操作者
    private String operatorId;

    //操作时间
    private Date operateTime;

    //
    private String creator;

    //
    private Date addTime;

    //
    private String updateUser;

    //
    private Date updateTime;


    /**
     * 获得id
     * @return String
     */
    public String getId(){
        return this.id;
    }

    /**
     * 设置id
     * @param id  id
     */
    public void setId(String id){
        this.id = id;
    }
    /**
     * 获得课题id
     * @return String
     */
    public String getResearchId(){
        return this.researchId;
    }

    /**
     * 设置课题id
     * @param researchId  课题id
     */
    public void setResearchId(String researchId){
        this.researchId = researchId;
    }
    /**
     * 获得序号
     * @return Integer
     */
    public Integer getSeq(){
        return this.seq;
    }

    /**
     * 设置序号
     * @param seq  序号
     */
    public void setSeq(Integer seq){
        this.seq = seq;
    }
    /**
     * 获得状态
     * @return String
     */
    public String getStatus(){
        return this.status;
    }

    /**
     * 设置状态
     * @param status  状态
     */
    public void setStatus(String status){
        this.status = status;
    }
    /**
     * 获得
     * @return String
     */
    public String getOperatorName(){
        return this.operatorName;
    }

    /**
     * 设置
     * @param operatorName  
     */
    public void setOperatorName(String operatorName){
        this.operatorName = operatorName;
    }
    /**
     * 获得操作者
     * @return String
     */
    public String getOperatorId(){
        return this.operatorId;
    }

    /**
     * 设置操作者
     * @param operatorId  操作者
     */
    public void setOperatorId(String operatorId){
        this.operatorId = operatorId;
    }
    /**
     * 获得操作时间
     * @return Date
     */
    public Date getOperateTime(){
        return this.operateTime;
    }

    /**
     * 设置操作时间
     * @param operateTime  操作时间
     */
    public void setOperateTime(Date operateTime){
        this.operateTime = operateTime;
    }
    /**
     * 获得
     * @return String
     */
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置
     * @param creator  
     */
    public void setCreator(String creator){
        this.creator = creator;
    }
    /**
     * 获得
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置
     * @param addTime  
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得
     * @return String
     */
    public String getUpdateUser(){
        return this.updateUser;
    }

    /**
     * 设置
     * @param updateUser  
     */
    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
    }
    /**
     * 获得
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置
     * @param updateTime  
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.id = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.id;
	}
	
	
	@Override
	public String toString() {
		return "BaseResearchStatus ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.researchId=" + this.researchId + ", "
		 		+ "this.seq=" + this.seq + ", "
		 		+ "this.status=" + this.status + ", "
		 		+ "this.operatorName=" + this.operatorName + ", "
		 		+ "this.operatorId=" + this.operatorId + ", "
		 		+ "this.operateTime=" + this.operateTime + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}