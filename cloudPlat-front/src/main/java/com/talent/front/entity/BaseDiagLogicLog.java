package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断逻辑日志类
 */
public class BaseDiagLogicLog implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String logId;

    //逻辑id
    private String logicId;

    //操作类型
    private Integer operateType;

    //处理状态
    private Integer processStatus;

    //日志时间
    private Date logTime;

    //操作者
    private String operator;


    /**
     * 获得id
     * @return String
     */
    public String getLogId(){
        return this.logId;
    }

    /**
     * 设置id
     * @param logId  id
     */
    public void setLogId(String logId){
        this.logId = logId;
    }
    /**
     * 获得逻辑id
     * @return String
     */
    public String getLogicId(){
        return this.logicId;
    }

    /**
     * 设置逻辑id
     * @param logicId  逻辑id
     */
    public void setLogicId(String logicId){
        this.logicId = logicId;
    }
    /**
     * 获得操作类型
     * @return Integer
     */
    public Integer getOperateType(){
        return this.operateType;
    }

    /**
     * 设置操作类型
     * @param operateType  操作类型
     */
    public void setOperateType(Integer operateType){
        this.operateType = operateType;
    }
    /**
     * 获得处理状态
     * @return Integer
     */
    public Integer getProcessStatus(){
        return this.processStatus;
    }

    /**
     * 设置处理状态
     * @param processStatus  处理状态
     */
    public void setProcessStatus(Integer processStatus){
        this.processStatus = processStatus;
    }
    /**
     * 获得日志时间
     * @return Date
     */
    public Date getLogTime(){
        return this.logTime;
    }

    /**
     * 设置日志时间
     * @param logTime  日志时间
     */
    public void setLogTime(Date logTime){
        this.logTime = logTime;
    }
    /**
     * 获得操作者
     * @return String
     */
    public String getOperator(){
        return this.operator;
    }

    /**
     * 设置操作者
     * @param operator  操作者
     */
    public void setOperator(String operator){
        this.operator = operator;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.logId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.logId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDiagLogicLog ["
		 		+ "this.logId=" + this.logId + ", "
		 		+ "this.logicId=" + this.logicId + ", "
		 		+ "this.operateType=" + this.operateType + ", "
		 		+ "this.processStatus=" + this.processStatus + ", "
		 		+ "this.logTime=" + this.logTime + ", "
		 		+ "this.operator=" + this.operator + ", "
		+ "]";   
	}
}