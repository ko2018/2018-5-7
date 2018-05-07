package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseDataClean;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：清洗数据表Dto类
 */
public class BaseDataCleanDto extends BaseDataClean {

	@Override
	public String toString() {
		return "BaseDataClean ["
		 		+ "this.datacleanId=" + this.getDatacleanId() + ", "
		 		+ "this.userCode=" + this.getUserCode() + ", "
		 		+ "this.institutionId=" + this.getInstitutionId() + ", "
		 		+ "this.checkCode=" + this.getCheckCode() + ", "
		 		+ "this.docId=" + this.getDocId() + ", "
		 		+ "this.dataObject=" + this.getDataObject() + ", "
		 		+ "this.errorFlag=" + this.getErrorFlag() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}