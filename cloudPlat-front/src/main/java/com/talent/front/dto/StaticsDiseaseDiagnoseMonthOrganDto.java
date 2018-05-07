package com.talent.front.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.StaticsDiseaseDiagnoseMonthOrgan;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：统计临时疾病诊断月表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "organIDList", "addTime", "updateTime" })
public class StaticsDiseaseDiagnoseMonthOrganDto extends StaticsDiseaseDiagnoseMonthOrgan {
	public BaseDiseasesSysDto baseDiseasesSysDto;
	public BaseInstitutionDto baseInstitutionDto;
	private List<String> organIDList;
	private String crowdID;
	
	public BaseDiseasesSysDto getBaseDiseasesSysDto() {
		return baseDiseasesSysDto;
	}

	public String getCrowdID() {
		return crowdID;
	}

	public void setCrowdID(String crowdID) {
		this.crowdID = crowdID;
	}

	public void setBaseDiseasesSysDto(BaseDiseasesSysDto baseDiseasesSysDto) {
		this.baseDiseasesSysDto = baseDiseasesSysDto;
	}

	public BaseInstitutionDto getBaseInstitutionDto() {
		return baseInstitutionDto;
	}

	public void setBaseInstitutionDto(BaseInstitutionDto baseInstitutionDto) {
		this.baseInstitutionDto = baseInstitutionDto;
	}

	public List<String> getOrganIDList() {
		return organIDList;
	}

	public void setOrganIDList(List<String> organIDList) {
		this.organIDList = organIDList;
	}

	@Override
	public String toString() {
		return "StaticsDiseaseDiagnoseMonthOrgan ["
		 		+ "this.staticsId=" + this.getStaticsId() + ", "
		 		+ "this.staticsDisease=" + this.getStaticsDisease() + ", "
		 		+ "this.staticsV=" + this.getStaticsV() + ", "
		 		+ "this.staticsUid=" + this.getStaticsUid() + ", "
		 		+ "this.staticsTime=" + this.getStaticsTime() + ", "
		 		+ "this.staticsOrgan=" + this.getStaticsOrgan() + ", "
		 		+ "this.staticsBirth=" + this.getStaticsBirth() + ", "
		 		+ "this.staticsAgeGroup=" + this.getStaticsAgeGroup() + ", "
		 		+ "this.staticsSex=" + this.getStaticsSex() + ", "
		 		+ "this.staticsExamList=" + this.getStaticsExamList() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}