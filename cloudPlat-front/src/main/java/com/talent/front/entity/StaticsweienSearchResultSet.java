package com.talent.front.entity;

import java.util.List;

import com.talent.base.model.BaseEntity;

public class StaticsweienSearchResultSet implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private List<StaticsweienSearchResultMain> mainList;
	
	private List<StaticsweienSearchResultSub> subList;
	
	
	public List<StaticsweienSearchResultSub> getSubList() {
		return subList;
	}

	public void setSubList(List<StaticsweienSearchResultSub> subList) {
		this.subList = subList;
	}

	public List<StaticsweienSearchResultMain> getMainList() {
		return mainList;
	}

	public void setMainList(List<StaticsweienSearchResultMain> mainList) {
		this.mainList = mainList;
	}

	@Override
	public String get_Id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set_Id(String arg0) {
		// TODO Auto-generated method stub

	}

}
