package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-01 <br/>
 * 描述：独立T检验分组类
 */
public class StaticsTtestIndGroup implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //
    private String userId;

    //g_unit
    private String gUnitName;

    //g_unit_parm
    private String gUnitParm;

    //g_unit_type
    private String gUnitDict;

    //create_time
    private Date addTime;

    //update_time
    private Date updateTime;

    //delete_status
    private String deleteStatus;


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
     * 获得g_unit
     * @return String
     */
    public String getGUnitName(){
        return this.gUnitName;
    }

    /**
     * 设置g_unit
     * @param gUnitName  g_unit
     */
    public void setGUnitName(String gUnitName){
        this.gUnitName = gUnitName;
    }
    /**
     * 获得g_unit_parm
     * @return String
     */
    public String getGUnitParm(){
        return this.gUnitParm;
    }

    /**
     * 设置g_unit_parm
     * @param gUnitParm  g_unit_parm
     */
    public void setGUnitParm(String gUnitParm){
        this.gUnitParm = gUnitParm;
    }
    /**
     * 获得g_unit_type
     * @return String
     */
    public String getGUnitDict(){
        return this.gUnitDict;
    }

    /**
     * 设置g_unit_type
     * @param gUnitDict  g_unit_type
     */
    public void setGUnitDict(String gUnitDict){
        this.gUnitDict = gUnitDict;
    }
    /**
     * 获得create_time
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置create_time
     * @param addTime  create_time
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
		return "StaticsTtestIndGroup ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.userId=" + this.userId + ", "
		 		+ "this.gUnitName=" + this.gUnitName + ", "
		 		+ "this.gUnitParm=" + this.gUnitParm + ", "
		 		+ "this.gUnitDict=" + this.gUnitDict + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		+ "]";   
	}
}