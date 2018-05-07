package com.talent.front.entity;

import java.util.List;

import com.talent.base.model.BaseEntity;

public class StaticsSearchResultSetWeien  implements BaseEntity{

	private static final long serialVersionUID = 1L;
	//高发病前10
	private List<StaticsweienSearchResultSet> parmList;
	public List<StaticsweienSearchResultSet> getParmList() {
		return parmList;
	}

	public void setParmList(List<StaticsweienSearchResultSet> parmList) {
		this.parmList = parmList;
	}

	public String getDiseaseSum() {
		return diseaseSum;
	}

	public void setDiseaseSum(String diseaseSum) {
		this.diseaseSum = diseaseSum;
	}

	@Override
	public void set_Id(String id) {
		this.id = id;
		
	}
	@Override
	public String get_Id() {
		
		return this.id;
	}

	//人群总数
    //患病总数
    private String diseaseSum = "";

    private String id = "";
	
	
}
