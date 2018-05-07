package com.talent.front.dto;

import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：机构标准术语对应表Dto类
 */
public class BaseInstitutionSnlDataDto implements BaseEntity {
	private String id;
	private long snlCount = 0; // 平台标准属性总数
	private long snlRelationCount = 0; // 已关联
	private long insSnlCount = 0; // 机构属性总数
	private long insSnlRelationCount = 0; // 已关联

	@Override
	public void set_Id(String id) {
		this.id = id;

	}

	@Override
	public String get_Id() {
		return this.id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSnlCount() {
		return snlCount;
	}

	public void setSnlCount(long snlCount) {
		this.snlCount = snlCount;
	}

	public long getSnlRelationCount() {
		return snlRelationCount;
	}

	public void setSnlRelationCount(long snlRelationCount) {
		this.snlRelationCount = snlRelationCount;
	}

	public long getInsSnlCount() {
		return insSnlCount;
	}

	public void setInsSnlCount(long insSnlCount) {
		this.insSnlCount = insSnlCount;
	}

	public long getInsSnlRelationCount() {
		return insSnlRelationCount;
	}

	public void setInsSnlRelationCount(long insSnlRelationCount) {
		this.insSnlRelationCount = insSnlRelationCount;
	}

}