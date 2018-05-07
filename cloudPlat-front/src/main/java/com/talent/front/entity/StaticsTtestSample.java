package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-30 <br/>
 * 描述：单样本T检验类
 */
public class StaticsTtestSample implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //用户id
    private String userId;

    //  人群id
    private String sCrowd;
    
    //  人群Name
    private String sCrowdName;

    //s_confidence：置信区间
    private String sConfidence;

    //s_expect_val:检验值
    private String sExpectVal;

    //s_dic_id：指标id
    private String sDicId;

    //start_time
    private Date addTime;

    //update_time
    private Date updateTime;

    //
    private String sExpectFlag;

    //delete_status
    private String deleteStatus;


    public String getsCrowdName() {
		return sCrowdName;
	}

	public void setsCrowdName(String sCrowdName) {
		this.sCrowdName = sCrowdName;
	}

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
     * 获得用户id
     * @return String
     */
    public String getUserId(){
        return this.userId;
    }

    /**
     * 设置用户id
     * @param userId  用户id
     */
    public void setUserId(String userId){
        this.userId = userId;
    }
    /**
     * 获得  人群id
     * @return String
     */
    public String getSCrowd(){
        return this.sCrowd;
    }

    /**
     * 设置  人群id
     * @param sCrowd    人群id
     */
    public void setSCrowd(String sCrowd){
        this.sCrowd = sCrowd;
    }
    /**
     * 获得s_confidence：置信区间
     * @return String
     */
    public String getSConfidence(){
        return this.sConfidence;
    }

    /**
     * 设置s_confidence：置信区间
     * @param sConfidence  s_confidence：置信区间
     */
    public void setSConfidence(String sConfidence){
        this.sConfidence = sConfidence;
    }
    /**
     * 获得s_expect_val:检验值
     * @return String
     */
    public String getSExpectVal(){
        return this.sExpectVal;
    }

    /**
     * 设置s_expect_val:检验值
     * @param sExpectVal  s_expect_val:检验值
     */
    public void setSExpectVal(String sExpectVal){
        this.sExpectVal = sExpectVal;
    }
    /**
     * 获得s_dic_id：指标id
     * @return String
     */
    public String getSDicId(){
        return this.sDicId;
    }

    /**
     * 设置s_dic_id：指标id
     * @param sDicId  s_dic_id：指标id
     */
    public void setSDicId(String sDicId){
        this.sDicId = sDicId;
    }
    /**
     * 获得start_time
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置start_time
     * @param addTime  start_time
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得update_time
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置update_time
     * @param updateTime  update_time
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    /**
     * 获得
     * @return String
     */
    public String getSExpectFlag(){
        return this.sExpectFlag;
    }

    /**
     * 设置
     * @param sExpectFlag  
     */
    public void setSExpectFlag(String sExpectFlag){
        this.sExpectFlag = sExpectFlag;
    }
    /**
     * 获得delete_status
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置delete_status
     * @param deleteStatus  delete_status
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
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
		return "StaticsTtestSample ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.userId=" + this.userId + ", "
		 		+ "this.sCrowd=" + this.sCrowd + ", "
		 		+ "this.sConfidence=" + this.sConfidence + ", "
		 		+ "this.sExpectVal=" + this.sExpectVal + ", "
		 		+ "this.sDicId=" + this.sDicId + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		 		+ "this.sExpectFlag=" + this.sExpectFlag + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		+ "]";   
	}
}