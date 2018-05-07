package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析类
 */
public class StaticsDescribeAnalysisc implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //用户id
    private String userId;

    //人群id
    private String crowdId;

    //分组id
    private String groupid;

    //指标列表
    private String dictList;

    //0是描述，1是概率
    private String type;

    //操作项
    private String optionList;

    //
    private String deleteStatus;

    //add_time
    private Date addTime;

    //update_time
    private Date updateTime;

    //排序规则
    private String sort;


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
     * 获得人群id
     * @return String
     */
    public String getCrowdId(){
        return this.crowdId;
    }

    /**
     * 设置人群id
     * @param crowdId  人群id
     */
    public void setCrowdId(String crowdId){
        this.crowdId = crowdId;
    }
    /**
     * 获得分组id
     * @return String
     */
    public String getGroupid(){
        return this.groupid;
    }

    /**
     * 设置分组id
     * @param groupid  分组id
     */
    public void setGroupid(String groupid){
        this.groupid = groupid;
    }
    /**
     * 获得指标列表
     * @return String
     */
    public String getDictList(){
        return this.dictList;
    }

    /**
     * 设置指标列表
     * @param dictList  指标列表
     */
    public void setDictList(String dictList){
        this.dictList = dictList;
    }
    /**
     * 获得0是描述，1是概率
     * @return String
     */
    public String getType(){
        return this.type;
    }

    /**
     * 设置0是描述，1是概率
     * @param type  0是描述，1是概率
     */
    public void setType(String type){
        this.type = type;
    }
    /**
     * 获得操作项
     * @return String
     */
    public String getOptionList(){
        return this.optionList;
    }

    /**
     * 设置操作项
     * @param optionList  操作项
     */
    public void setOptionList(String optionList){
        this.optionList = optionList;
    }
    /**
     * 获得
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置
     * @param deleteStatus  
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
    }
    /**
     * 获得add_time
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置add_time
     * @param addTime  add_time
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
     * 获得排序规则
     * @return String
     */
    public String getSort(){
        return this.sort;
    }

    /**
     * 设置排序规则
     * @param sort  排序规则
     */
    public void setSort(String sort){
        this.sort = sort;
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
		return "StaticsDescribeAnalysisc ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.userId=" + this.userId + ", "
		 		+ "this.crowdId=" + this.crowdId + ", "
		 		+ "this.groupid=" + this.groupid + ", "
		 		+ "this.dictList=" + this.dictList + ", "
		 		+ "this.type=" + this.type + ", "
		 		+ "this.optionList=" + this.optionList + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		 		+ "this.sort=" + this.sort + ", "
		+ "]";   
	}
}