package com.talent.front.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-15 <br/>
 * 描述：统计首页平台对应表类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "staticsId","_Id" })
public class StaticsPlatformBase implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //统计id
    private String staticsId;

    //统计记录总数
    private String staticsRecords = "";

    //统计体检人数总数
    private String staticsPeople = "";

    //统计机构总数
    private String staticsOrgans = "";

    //统计病人总数
    private String staticsHasDisease = "";



    /**
     * 获得统计id
     * @return String
     */
    public String getStaticsId(){
        return this.staticsId;
    }

    /**
     * 设置统计id
     * @param staticsId  统计id
     */
    public void setStaticsId(String staticsId){
        this.staticsId = staticsId;
    }
    /**
     * 获得统计记录总数
     * @return String
     */
    public String getStaticsRecords(){
        return this.staticsRecords;
    }

    /**
     * 设置统计记录总数
     * @param staticsRecords  统计记录总数
     */
    public void setStaticsRecords(String staticsRecords){
        this.staticsRecords = staticsRecords;
    }
    /**
     * 获得统计体检人数总数
     * @return String
     */
    public String getStaticsPeople(){
        return this.staticsPeople;
    }

    /**
     * 设置统计体检人数总数
     * @param staticsPeople  统计体检人数总数
     */
    public void setStaticsPeople(String staticsPeople){
        this.staticsPeople = staticsPeople;
    }
    /**
     * 获得统计机构总数
     * @return String
     */
    public String getStaticsOrgans(){
        return this.staticsOrgans;
    }

    /**
     * 设置统计机构总数
     * @param staticsOrgans  统计机构总数
     */
    public void setStaticsOrgans(String staticsOrgans){
        this.staticsOrgans = staticsOrgans;
    }
    /**
     * 获得统计病人总数
     * @return String
     */
    public String getStaticsHasDisease(){
        return this.staticsHasDisease;
    }

    /**
     * 设置统计病人总数
     * @param staticsHasDisease  统计病人总数
     */
    public void setStaticsHasDisease(String staticsHasDisease){
        this.staticsHasDisease = staticsHasDisease;
    }
  
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.staticsId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.staticsId;
	}
	
	
	@Override
	public String toString() {
		return "StaticsPlatformBase ["
		 		+ "this.staticsId=" + this.staticsId + ", "
		 		+ "this.staticsRecords=" + this.staticsRecords + ", "
		 		+ "this.staticsPeople=" + this.staticsPeople + ", "
		 		+ "this.staticsOrgans=" + this.staticsOrgans + ", "
		 		+ "this.staticsHasDisease=" + this.staticsHasDisease + ", "
		 	
		+ "]";   
	}
}