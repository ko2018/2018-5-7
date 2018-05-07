package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-05 <br/>
 * 描述：数学分析类
 */
public class StaticsMathInOutData implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //s_type：类型1
    private String sType;

    //s_type_func:类型2
    private String sTypeFunc;

    //s_data_in：输入
    private String sDataIn;

    //s_data_out:输出
    private String sDataOut;

    //人群id
    private String sCrowd;

    //
    private String exeStatus;

    //
    private String deleteStatus;

    //
    private String creator;

    //add_time
    private Date addTime;

    //update_time
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
     * 获得s_type：类型1
     * @return String
     */
    public String getSType(){
        return this.sType;
    }

    /**
     * 设置s_type：类型1
     * @param sType  s_type：类型1
     */
    public void setSType(String sType){
        this.sType = sType;
    }
    /**
     * 获得s_type_func:类型2
     * @return String
     */
    public String getSTypeFunc(){
        return this.sTypeFunc;
    }

    /**
     * 设置s_type_func:类型2
     * @param sTypeFunc  s_type_func:类型2
     */
    public void setSTypeFunc(String sTypeFunc){
        this.sTypeFunc = sTypeFunc;
    }
    /**
     * 获得s_data_in：输入
     * @return String
     */
    public String getSDataIn(){
        return this.sDataIn;
    }

    /**
     * 设置s_data_in：输入
     * @param sDataIn  s_data_in：输入
     */
    public void setSDataIn(String sDataIn){
        this.sDataIn = sDataIn;
    }
    /**
     * 获得s_data_out:输出
     * @return String
     */
    public String getSDataOut(){
        return this.sDataOut;
    }

    /**
     * 设置s_data_out:输出
     * @param sDataOut  s_data_out:输出
     */
    public void setSDataOut(String sDataOut){
        this.sDataOut = sDataOut;
    }
    /**
     * 获得人群id
     * @return String
     */
    public String getSCrowd(){
        return this.sCrowd;
    }

    /**
     * 设置人群id
     * @param sCrowd  人群id
     */
    public void setSCrowd(String sCrowd){
        this.sCrowd = sCrowd;
    }
    /**
     * 获得
     * @return String
     */
    public String getExeStatus(){
        return this.exeStatus;
    }

    /**
     * 设置
     * @param exeStatus  
     */
    public void setExeStatus(String exeStatus){
        this.exeStatus = exeStatus;
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
		return "StaticsMathInOutData ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.sType=" + this.sType + ", "
		 		+ "this.sTypeFunc=" + this.sTypeFunc + ", "
		 		+ "this.sDataIn=" + this.sDataIn + ", "
		 		+ "this.sDataOut=" + this.sDataOut + ", "
		 		+ "this.sCrowd=" + this.sCrowd + ", "
		 		+ "this.exeStatus=" + this.exeStatus + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}