package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档操作日志表类
 */
public class BaseResearchFileLog implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String logId;

    //课题id
    private String researchId;

    //文件id
    private String fileId;

    //操作类型
    private Integer operatorType;

    //操作者
    private String operatorId;

    //操作时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    private String operatorName;

    public String getOperatorName()
    {
        return operatorName;
    }

    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }

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
     * 获得文件id
     * @return String
     */
    public String getFileId(){
        return this.fileId;
    }

    /**
     * 设置文件id
     * @param fileId  文件id
     */
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    /**
     * 获得操作类型
     * @return Integer
     */
    public Integer getOperatorType(){
        return this.operatorType;
    }

    /**
     * 设置操作类型
     * @param operatorType  操作类型
     */
    public void setOperatorType(Integer operatorType){
        this.operatorType = operatorType;
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
		return "BaseResearchFileLog ["
		 		+ "this.logId=" + this.logId + ", "
		 		+ "this.researchId=" + this.researchId + ", "
		 		+ "this.fileId=" + this.fileId + ", "
		 		+ "this.operatorType=" + this.operatorType + ", "
		 		+ "this.operatorId=" + this.operatorId + ", "
		 		+ "this.operateTime=" + this.operateTime + ", "
		+ "]";   
	}
}