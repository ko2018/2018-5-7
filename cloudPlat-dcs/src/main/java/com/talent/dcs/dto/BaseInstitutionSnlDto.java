package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseInstitutionSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：机构标准术语对应表Dto类
 */
public class BaseInstitutionSnlDto extends BaseInstitutionSnl {

	@Override
	public String toString() {
		return "BaseInstitutionSnl ["
		 		+ "this.institutionSnlId=" + this.getInstitutionSnlId() + ", "
		 		+ "this.dictId=" + this.getDictId() + ", "
		 		+ "this.docCname=" + this.getDocCname() + ", "
		 		+ "this.docCvalue=" + this.getDocCvalue() + ", "
		 		+ "this.institutionSnlVersionId=" + this.getInstitutionSnlVersionId() + ", "
		 		+ "this.snlCode=" + this.getSnlCode() + ", "
		 		+ "this.snlName=" + this.getSnlName() + ", "
		 		+ "this.snlNameUs=" + this.getSnlNameUs() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}