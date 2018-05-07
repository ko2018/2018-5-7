package com.talent.front.dto;

import com.talent.front.entity.BaseInstitutionSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：机构标准术语对应表Dto类
 */
public class BaseInstitutionSnlDto extends BaseInstitutionSnl {
	// 标准术语
	private BaseSnlDto baseSnlDto;

	private String snlCode;
	private String nameCn;
	private String nameUs;
	private String valuetypeName;

	public BaseSnlDto getBaseSnlDto() {
		return baseSnlDto;
	}

	public void setBaseSnlDto(BaseSnlDto baseSnlDto) {
		this.baseSnlDto = baseSnlDto;
	}

	@Override
	public String toString() {
		return "BaseInstitutionSnl [" + "this.institutionSnlId=" + this.getInstitutionSnlId() + ", " + "this.dictId="
				+ this.getDictId() + ", " + "this.docCname=" + this.getDocCname() + ", " + "this.docCvalue="
				+ this.getDocCvalue() + ", " + "this.institutionSnlVersionId=" + this.getInstitutionSnlVersionId()
				+ ", " + "this.snlCode=" + this.getSnlCode() + ", " + "this.snlName=" + this.getSnlName() + ", "
				+ "this.deleteStatus=" + this.getDeleteStatus() + ", " + "this.creator=" + this.getCreator() + ", "
				+ "this.addTime=" + this.getAddTime() + ", " + "this.updateTime=" + this.getUpdateTime() + ", " + "]";
	}

	public String getSnlCode() {
		return snlCode;
	}

	public void setSnlCode(String snlCode) {
		this.snlCode = snlCode;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameUs() {
		return nameUs;
	}

	public void setNameUs(String nameUs) {
		this.nameUs = nameUs;
	}

	public String getValuetypeName() {
		return valuetypeName;
	}

	public void setValuetypeName(String valuetypeName) {
		this.valuetypeName = valuetypeName;
	}

}