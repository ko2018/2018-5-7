package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：统计临时疾病诊断年表类
 */
public class StaticsDiseaseDiagnoseYearOrgan implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //统计人群id
    private String staticsId = "";

    //创建人群的用户
    private String staticsDisease = "";

    //统计名称
    private String staticsV = "";


    private String staticsUid = "";


    private String staticsTime = "";


    private String staticsOrgan = "";


    private String staticsBirth = "";


    private String staticsAgeGroup = "";


    private String staticsSex = "";


    private String staticsExamList = "";


    private Date addTime;


    private Date updateTime;



    public String getStaticsId(){
        return this.staticsId;
    }


    public void setStaticsId(String staticsId){
        this.staticsId = staticsId;
    }

    public String getStaticsDisease(){
        return this.staticsDisease;
    }


    public void setStaticsDisease(String staticsDisease){
        this.staticsDisease = staticsDisease;
    }

    public String getStaticsV(){
        return this.staticsV;
    }

  
    public void setStaticsV(String staticsV){
        this.staticsV = staticsV;
    }

    public String getStaticsUid(){
        return this.staticsUid;
    }


    public void setStaticsUid(String staticsUid){
        this.staticsUid = staticsUid;
    }

    public String getStaticsTime(){
        return this.staticsTime;
    }


    public void setStaticsTime(String staticsTime){
        this.staticsTime = staticsTime;
    }

    public String getStaticsOrgan(){
        return this.staticsOrgan;
    }


    public void setStaticsOrgan(String staticsOrgan){
        this.staticsOrgan = staticsOrgan;
    }

    public String getStaticsBirth(){
        return this.staticsBirth;
    }


    public void setStaticsBirth(String staticsBirth){
        this.staticsBirth = staticsBirth;
    }

    public String getStaticsAgeGroup(){
        return this.staticsAgeGroup;
    }


    public void setStaticsAgeGroup(String staticsAgeGroup){
        this.staticsAgeGroup = staticsAgeGroup;
    }

    public String getStaticsSex(){
        return this.staticsSex;
    }


    public void setStaticsSex(String staticsSex){
        this.staticsSex = staticsSex;
    }
  
    public String getStaticsExamList(){
        return this.staticsExamList;
    }


    public void setStaticsExamList(String staticsExamList){
        this.staticsExamList = staticsExamList;
    }

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
		return "StaticsDiseaseDiagnoseYearOrgan ["
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