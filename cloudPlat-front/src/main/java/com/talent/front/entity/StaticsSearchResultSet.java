package com.talent.front.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.base.model.BaseEntity;

/**
 * @author zhangqian
 * @time 2017年12月22日 上午10:22:14
 * @version 1.0v
 */

public class StaticsSearchResultSet implements BaseEntity {
	
	private static final long serialVersionUID = 1L;
	//高发病前10
	private List<StaticsHighDisease> parmList;
	//人群总数
    private String peopleSum = "";
    //患病总数
    private String diseaseSum = "";

    private String id = "";
    
	private String examTime = "";
	@Override
	public void set_Id(String id) {
		this.id = id;
		
	}



	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	
	

	@Override
	public String get_Id() {
		
		return this.id;
	}



	public List<StaticsHighDisease> getParmList() {
		return parmList;
	}



	public void setParmList(List<StaticsHighDisease> parmList) {
		this.parmList = parmList;
	}



	public String getPeopleSum() {
		return peopleSum;
	}

	public void setPeopleSum(String peopleSum) {
		this.peopleSum = peopleSum;
	}



	public String getDiseaseSum() {
		return diseaseSum;
	}



	public void setDiseaseSum(String diseaseSum) {
		this.diseaseSum = diseaseSum;
	}

    
		
}
