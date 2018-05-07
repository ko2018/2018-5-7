package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：统计临时疾病诊断月表类
 */
public class StaticsDiseaseDiagnoseMonthOrgan implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //统计人群id
    private String staticsId = "";

    //创建人群的用户
    private String staticsDisease = "";

    //统计名称
    private String staticsV = "";

    //统计纳入时间跨度start
    private String staticsUid = "";

    //统计纳入时间跨度end
    private String staticsTime = "";

    //统计纳入疾病特征列表
    private String staticsOrgan = "";

    //统计纳入逻辑关系公式
    private String staticsBirth = "";

    //统计纳入逻辑关系公式
    private String staticsAgeGroup = "";

    //统计纳入逻辑关系公式
    private String staticsSex = "";

    //统计纳入逻辑关系公式
    private String staticsExamList = "";

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得统计人群id
     * @return String
     */
    public String getStaticsId(){
        return this.staticsId;
    }

    /**
     * 设置统计人群id
     * @param staticsId  统计人群id
     */
    public void setStaticsId(String staticsId){
        this.staticsId = staticsId;
    }
    /**
     * 获得创建人群的用户
     * @return String
     */
    public String getStaticsDisease(){
        return this.staticsDisease;
    }

    /**
     * 设置创建人群的用户
     * @param staticsDisease  创建人群的用户
     */
    public void setStaticsDisease(String staticsDisease){
        this.staticsDisease = staticsDisease;
    }
    /**
     * 获得统计名称
     * @return String
     */
    public String getStaticsV(){
        return this.staticsV;
    }

    /**
     * 设置统计名称
     * @param staticsV  统计名称
     */
    public void setStaticsV(String staticsV){
        this.staticsV = staticsV;
    }
    /**
     * 获得统计纳入时间跨度start
     * @return String
     */
    public String getStaticsUid(){
        return this.staticsUid;
    }

    /**
     * 设置统计纳入时间跨度start
     * @param staticsUid  统计纳入时间跨度start
     */
    public void setStaticsUid(String staticsUid){
        this.staticsUid = staticsUid;
    }
    /**
     * 获得统计纳入时间跨度end
     * @return String
     */
    public String getStaticsTime(){
        return this.staticsTime;
    }

    /**
     * 设置统计纳入时间跨度end
     * @param staticsTime  统计纳入时间跨度end
     */
    public void setStaticsTime(String staticsTime){
        this.staticsTime = staticsTime;
    }
    /**
     * 获得统计纳入疾病特征列表
     * @return String
     */
    public String getStaticsOrgan(){
        return this.staticsOrgan;
    }

    /**
     * 设置统计纳入疾病特征列表
     * @param staticsOrgan  统计纳入疾病特征列表
     */
    public void setStaticsOrgan(String staticsOrgan){
        this.staticsOrgan = staticsOrgan;
    }
    /**
     * 获得统计纳入逻辑关系公式
     * @return String
     */
    public String getStaticsBirth(){
        return this.staticsBirth;
    }

    /**
     * 设置统计纳入逻辑关系公式
     * @param staticsBirth  统计纳入逻辑关系公式
     */
    public void setStaticsBirth(String staticsBirth){
        this.staticsBirth = staticsBirth;
    }
    /**
     * 获得统计纳入逻辑关系公式
     * @return String
     */
    public String getStaticsAgeGroup(){
        return this.staticsAgeGroup;
    }

    /**
     * 设置统计纳入逻辑关系公式
     * @param staticsAgeGroup  统计纳入逻辑关系公式
     */
    public void setStaticsAgeGroup(String staticsAgeGroup){
        this.staticsAgeGroup = staticsAgeGroup;
    }
    /**
     * 获得统计纳入逻辑关系公式
     * @return String
     */
    public String getStaticsSex(){
        return this.staticsSex;
    }

    /**
     * 设置统计纳入逻辑关系公式
     * @param staticsSex  统计纳入逻辑关系公式
     */
    public void setStaticsSex(String staticsSex){
        this.staticsSex = staticsSex;
    }
    /**
     * 获得统计纳入逻辑关系公式
     * @return String
     */
    public String getStaticsExamList(){
        return this.staticsExamList;
    }

    /**
     * 设置统计纳入逻辑关系公式
     * @param staticsExamList  统计纳入逻辑关系公式
     */
    public void setStaticsExamList(String staticsExamList){
        this.staticsExamList = staticsExamList;
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
     * 获得更新时间
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置更新时间
     * @param updateTime  更新时间
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
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
		return "StaticsDiseaseDiagnoseMonthOrgan ["
		 		+ "this.staticsId=" + this.staticsId + ", "
		 		+ "this.staticsDisease=" + this.staticsDisease + ", "
		 		+ "this.staticsV=" + this.staticsV + ", "
		 		+ "this.staticsUid=" + this.staticsUid + ", "
		 		+ "this.staticsTime=" + this.staticsTime + ", "
		 		+ "this.staticsOrgan=" + this.staticsOrgan + ", "
		 		+ "this.staticsBirth=" + this.staticsBirth + ", "
		 		+ "this.staticsAgeGroup=" + this.staticsAgeGroup + ", "
		 		+ "this.staticsSex=" + this.staticsSex + ", "
		 		+ "this.staticsExamList=" + this.staticsExamList + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}