package com.talent.front.entity;

import java.util.List;

import com.talent.base.model.BaseEntity;
import com.talent.front.dto.BaseDataRawDto;
import com.talent.front.dto.BaseSnlDto;

public class BaseSearchDataRawResult implements BaseEntity{

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private static final long serialVersionUID = 1L;
	private String organID;
	private String userCode;
	private String examID;

	private BaseDataRawDto baseDataRawDto;
    private List<BaseSnlDto> baseSnlDtoList;
    
	private int pageCurIndex;
	private int pageCount;
	private int pageSize;
	
	public int getPageCurIndex() {
		return pageCurIndex;
	}

	public void setPageCurIndex(int pageCurIndex) {
		this.pageCurIndex = pageCurIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<BaseSnlDto> getBaseSnlDtoList() {
		return baseSnlDtoList;
	}

	public void setBaseSnlDtoList(List<BaseSnlDto> baseSnlDtoList) {
		this.baseSnlDtoList = baseSnlDtoList;
	}


	public BaseDataRawDto getBaseDataRawDto() {
		return baseDataRawDto;
	}

	public void setBaseDataRawDto(BaseDataRawDto baseDataRawDto) {
		this.baseDataRawDto = baseDataRawDto;
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
