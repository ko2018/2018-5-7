package com.talent.front.dto;

import com.talent.front.entity.BaseInstitutionSnlVersion;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-25 <br/>
 * 描述：机构标准术语版本Dto类
 */
public class BaseInstitutionSnlVersionDto extends BaseInstitutionSnlVersion {
	private BaseInstitutionDto baseInstitutionDto;
	private SysUserDto sysUserDto;

	private String institutionName;
	private String institutionCode;
	private String realName;

	public BaseInstitutionDto getBaseInstitutionDto() {
		return baseInstitutionDto;
	}

	public void setBaseInstitutionDto(BaseInstitutionDto baseInstitutionDto) {
		this.baseInstitutionDto = baseInstitutionDto;
	}

	public SysUserDto getSysUserDto() {
		return sysUserDto;
	}

	public void setSysUserDto(SysUserDto sysUserDto) {
		this.sysUserDto = sysUserDto;
	}

	@Override
	public String toString() {
		return "BaseInstitutionSnlVersion [" + "this.institutionSnlVersionId=" + this.getInstitutionSnlVersionId()
				+ ", " + "this.institutionId=" + this.getInstitutionId() + ", " + "this.versionCode="
				+ this.getVersionCode() + ", " + "this.versionName=" + this.getVersionName() + ", " + "this.fieldCount="
				+ this.getFieldCount() + ", " + "this.isMapping=" + this.getIsMapping() + ", " + "this.versionPath="
				+ this.getVersionPath() + ", " + "this.deleteStatus=" + this.getDeleteStatus() + ", " + "this.creator="
				+ this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", " + "this.updateTime="
				+ this.getUpdateTime() + ", " + "]";
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}