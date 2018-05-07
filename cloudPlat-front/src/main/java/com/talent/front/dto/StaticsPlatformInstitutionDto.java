package com.talent.front.dto;

import com.talent.base.model.BaseEntity;


/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-15 <br/>
 * 描述：统计首页平台对应表Dto类
 */
public class StaticsPlatformInstitutionDto implements BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String get_Id() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void set_Id(String id) {
		this.id = id;
		
	}

}