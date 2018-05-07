package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：系统日志操作表类
 */
public class SysLog implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //系统日志id
    private String syslogId;

    //日志内容
    private String syslogContent;

    //日志title
    private String syslogTitle;

    //日志ip
    private String syslogIp;

    //删除状态
    private String deleteStatus;

    //类型
    private Integer syslogType;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;


    /**
     * 获得系统日志id
     * @return String
     */
    public String getSyslogId(){
        return this.syslogId;
    }

    /**
     * 设置系统日志id
     * @param syslogId  系统日志id
     */
    public void setSyslogId(String syslogId){
        this.syslogId = syslogId;
    }
    /**
     * 获得日志内容
     * @return String
     */
    public String getSyslogContent(){
        return this.syslogContent;
    }

    /**
     * 设置日志内容
     * @param syslogContent  日志内容
     */
    public void setSyslogContent(String syslogContent){
        this.syslogContent = syslogContent;
    }
    /**
     * 获得日志title
     * @return String
     */
    public String getSyslogTitle(){
        return this.syslogTitle;
    }

    /**
     * 设置日志title
     * @param syslogTitle  日志title
     */
    public void setSyslogTitle(String syslogTitle){
        this.syslogTitle = syslogTitle;
    }
    /**
     * 获得日志ip
     * @return String
     */
    public String getSyslogIp(){
        return this.syslogIp;
    }

    /**
     * 设置日志ip
     * @param syslogIp  日志ip
     */
    public void setSyslogIp(String syslogIp){
        this.syslogIp = syslogIp;
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
     * 获得类型
     * @return Integer
     */
    public Integer getSyslogType(){
        return this.syslogType;
    }

    /**
     * 设置类型
     * @param syslogType  类型
     */
    public void setSyslogType(Integer syslogType){
        this.syslogType = syslogType;
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
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.syslogId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.syslogId;
	}
	
	
	@Override
	public String toString() {
		return "SysLog ["
		 		+ "this.syslogId=" + this.syslogId + ", "
		 		+ "this.syslogContent=" + this.syslogContent + ", "
		 		+ "this.syslogTitle=" + this.syslogTitle + ", "
		 		+ "this.syslogIp=" + this.syslogIp + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.syslogType=" + this.syslogType + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		+ "]";   
	}
}