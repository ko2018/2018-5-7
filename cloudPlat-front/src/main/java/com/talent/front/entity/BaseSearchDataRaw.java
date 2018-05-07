package com.talent.front.entity;

import com.talent.base.model.BaseEntity;

public class BaseSearchDataRaw implements BaseEntity{

	private static final long serialVersionUID = 1L;
	private String organID;
	private String userCode;
	private String examID;
	private String nameLike;
	private int pageIndex;
	private int pageSize;
	
	private String crowdID;

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCrowdID() {
		return crowdID;
	}

	public void setCrowdID(String crowdID) {
		this.crowdID = crowdID;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getOrganID() {
		return organID;
	}

	public void setOrganID(String organID) {
		this.organID = organID;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
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
