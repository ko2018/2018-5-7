package com.talent.front.entity;


import com.talent.base.model.BaseEntity;

public class StaticsWeienSearchCondition implements BaseEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 人群ids
	 */
	private String crowdIds;
	
	/**
	 * 机构id
	 */
	private String groupPro;
	
	private String beginDate;
	
	private String endDate;
	
	/**
	 * 性别 （全部  男  女）
	 */
	private Integer sex;
	
	/**
	 * 年龄层
	 */
	private String ageLayer;
	
	/**
	 * 疾病ids
	 */
	private String diseaseIds;
	
	/**
	 * 疾病名称
	 */
	private String diseaseNames;
	
	public String getDiseaseNames() {
		return diseaseNames;
	}

	public void setDiseaseNames(String diseaseNames) {
		this.diseaseNames = diseaseNames;
	}

	public String getCrowdIds() {
		return crowdIds;
	}

	public void setCrowdIds(String crowdIds) {
		this.crowdIds = crowdIds;
	}

	public String getGroupPro() {
		return groupPro;
	}

	public void setGroupPro(String groupPro) {
		this.groupPro = groupPro;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAgeLayer() {
		return ageLayer;
	}

	public void setAgeLayer(String ageLayer) {
		this.ageLayer = ageLayer;
	}

	public String getDiseaseIds() {
		return diseaseIds;
	}

	public void setDiseaseIds(String diseaseIds) {
		this.diseaseIds = diseaseIds;
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
