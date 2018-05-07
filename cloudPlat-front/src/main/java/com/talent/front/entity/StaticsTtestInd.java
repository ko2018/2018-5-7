package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-03 <br/>
 * 描述： 独立T检验类
 */
public class StaticsTtestInd implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //s_group_id2:  分组2
    private String sGroupId2;

    //s_group:分组id1
    private String sGroupId1;

    //s_confidence:置信区间
    private String sConfidence;

    //s_dict_list: 指标list
    private String sDictList;

    //s_crowd:人群id
    private String sCrowd;
    //s_crowd:人群名称
    private String sCrowdName;

    //
    private String userId;

    //creat_time
    private Date addTime;

    //udpate_time
    private Date updateTime;

    //个案排除
    private String sExpectFlag;

    //删除状态
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
     * 获得s_group_id2:  分组2
     * @return String
     */
    public String getSGroupId2(){
        return this.sGroupId2;
    }

    /**
     * 设置s_group_id2:  分组2
     * @param sGroupId2  s_group_id2:  分组2
     */
    public void setSGroupId2(String sGroupId2){
        this.sGroupId2 = sGroupId2;
    }
    /**
     * 获得s_group:分组id1
     * @return String
     */
    public String getSGroupId1(){
        return this.sGroupId1;
    }

    /**
     * 设置s_group:分组id1
     * @param sGroupId1  s_group:分组id1
     */
    public void setSGroupId1(String sGroupId1){
        this.sGroupId1 = sGroupId1;
    }
    /**
     * 获得s_confidence:置信区间
     * @return String
     */
    public String getSConfidence(){
        return this.sConfidence;
    }

    /**
     * 设置s_confidence:置信区间
     * @param sConfidence  s_confidence:置信区间
     */
    public void setSConfidence(String sConfidence){
        this.sConfidence = sConfidence;
    }
    /**
     * 获得s_dict_list: 指标list
     * @return String
     */
    public String getSDictList(){
        return this.sDictList;
    }

    /**
     * 设置s_dict_list: 指标list
     * @param sDictList  s_dict_list: 指标list
     */
    public void setSDictList(String sDictList){
        this.sDictList = sDictList;
    }
    /**
     * 获得s_crowd:人群id
     * @return String
     */
    public String getSCrowd(){
        return this.sCrowd;
    }

    /**
     * 设置s_crowd:人群id
     * @param sCrowd  s_crowd:人群id
     */
    public void setSCrowd(String sCrowd){
        this.sCrowd = sCrowd;
    }
    /**
     * 获得
     * @return String
     */
    public String getUserId(){
        return this.userId;
    }

    /**
     * 设置
     * @param userId  
     */
    public void setUserId(String userId){
        this.userId = userId;
    }
    /**
     * 获得creat_time
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置creat_time
     * @param addTime  creat_time
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得udpate_time
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置udpate_time
     * @param updateTime  udpate_time
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    /**
     * 获得个案排除
     * @return String
     */
    public String getSExpectFlag(){
        return this.sExpectFlag;
    }

    /**
     * 设置个案排除
     * @param sExpectFlag  个案排除
     */
    public void setSExpectFlag(String sExpectFlag){
        this.sExpectFlag = sExpectFlag;
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
		return "StaticsTtestInd ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.sGroupId2=" + this.sGroupId2 + ", "
		 		+ "this.sGroupId1=" + this.sGroupId1 + ", "
		 		+ "this.sConfidence=" + this.sConfidence + ", "
		 		+ "this.sDictList=" + this.sDictList + ", "
		 		+ "this.sCrowd=" + this.sCrowd + ", "
		 		+ "this.userId=" + this.userId + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		 		+ "this.sExpectFlag=" + this.sExpectFlag + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		+ "]";   
	}
}