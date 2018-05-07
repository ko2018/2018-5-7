package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseInstitutionSnlVersion;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：机构标准术语版本Dto类
 */
public class BaseInstitutionSnlVersionDto extends BaseInstitutionSnlVersion {

	@Override
	public String toString() {
		return "BaseInstitutionSnlVersion ["
		 		+ "this.institutionSnlVersionId=" + this.getInstitutionSnlVersionId() + ", "
		 		+ "this.institutionId=" + this.getInstitutionId() + ", "
		 		+ "this.versionCode=" + this.getVersionCode() + ", "
		 		+ "this.versionName=" + this.getVersionName() + ", "
		 		+ "this.fieldCount=" + this.getFieldCount() + ", "
		 		+ "this.isMapping=" + this.getIsMapping() + ", "
		 		+ "this.versionPath=" + this.getVersionPath() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}