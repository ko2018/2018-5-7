package com.talent.front.entity;

import java.util.List;

import com.talent.base.model.BaseEntity;

/**
 * @author zhangqian
 * @time 2017年12月22日 上午10:22:14
 * @version 1.0v
 */
public class StaticsSearchResultSetTR implements BaseEntity  {

	private static final long serialVersionUID = 1L;
	
	private String id = "";
	
	private List<StaticsSearchResultSet> searchResult;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<StaticsSearchResultSet> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<StaticsSearchResultSet> searchResult) {
		this.searchResult = searchResult;
	}

	@Override
	public void set_Id(String id) {
		this.id = id;
	}

	@Override
	public String get_Id() {
	
		return this.id;
	}
	
	
	
	
	
}
