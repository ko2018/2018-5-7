package com.talent.front.dto;

import com.talent.front.entity.BaseDataRaw;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：原始数据表Dto类
 */
public class BaseDataRawDto extends BaseDataRaw {
	BaseInstitutionDto baseInstitutionDto;
	
	public BaseInstitutionDto getBaseInstitutionDto() {
		return baseInstitutionDto;
	}

	public void setBaseInstitutionDto(BaseInstitutionDto baseInstitutionDto) {
		this.baseInstitutionDto = baseInstitutionDto;
	}

	@Override
	public String toString() {
		return "BaseDataRaw ["
		 		+ "this.datarawId=" + this.getDatarawId() + ", "
		 		+ "this.userCode=" + this.getUserCode() + ", "
		 		+ "this.institutionId=" + this.getInstitutionId() + ", "
		 		+ "this.checkCode=" + this.getCheckCode() + ", "
		 		+ "this.docId=" + this.getDocId() + ", "
		 		+ "this.dataObject=" + this.getDataObject() + ", "
		 		+ "this.isClean=" + this.getIsClean() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}