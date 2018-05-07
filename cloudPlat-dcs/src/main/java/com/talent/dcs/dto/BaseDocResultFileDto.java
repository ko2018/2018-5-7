package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseDocResultFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-10 <br/>
 * 描述：智能诊断结果数据文件Dto类
 */
public class BaseDocResultFileDto extends BaseDocResultFile {

	@Override
	public String toString() {
		return "BaseDocResultFile ["
		 		+ "this.resultId=" + this.getResultId() + ", "
		 		+ "this.institutionId=" + this.getInstitutionId() + ", "
		 		+ "this.researchId=" + this.getResearchId() + ", "
		 		+ "this.docPath=" + this.getDocPath() + ", "
		 		+ "this.isPersistence=" + this.getIsPersistence() + ", "
		 		+ "this.versionId=" + this.getVersionId() + ", "
		 		+ "this.docMd5=" + this.getDocMd5() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}