package com.talent.front.entity;

import com.talent.base.model.BaseEntity;

public class StaticsweienSearchResultSub implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *疾病ID
	 */
	private String diseaseId;
	
	/**
	 *疾病名称
	 */
	private String diseaseName;
	
	/**
	 *患者编码
	 */
	private String personCode;
	
	/**
	 * 患病人数
	 */
	private Integer sickCount;

	public String getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Integer getSickCount() {
		return sickCount;
	}

	public void setSickCount(Integer sickCount) {
		this.sickCount = sickCount;
	}

	@Override
	public void set_Id(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String get_Id() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
