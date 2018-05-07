package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseDoc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-04 <br/>
 * 描述：文档表Dto类
 */
public class BaseDocDto extends BaseDoc {

	@Override
	public String toString() {
		return "BaseDoc ["
		 		+ "this.docId=" + this.getDocId() + ", "
		 		+ "this.institutionId=" + this.getInstitutionId() + ", "
		 		+ "this.researchId=" + this.getResearchId() + ", "
		 		+ "this.docRows=" + this.getDocRows() + ", "
		 		+ "this.docName=" + this.getDocName() + ", "
		 		+ "this.docSourceName=" + this.getDocSourceName() + ", "
		 		+ "this.docPath=" + this.getDocPath() + ", "
		 		+ "this.doctypeId=" + this.getDoctypeId() + ", "
		 		+ "this.doctypeName=" + this.getDoctypeName() + ", "
		 		+ "this.docStatus=" + this.getDocStatus() + ", "
		 		+ "this.docCount=" + this.getDocCount() + ", "
		 		+ "this.isMapping=" + this.getIsMapping() + ", "
		 		+ "this.institutionSnlVersionId=" + this.getInstitutionSnlVersionId() + ", "
		 		+ "this.isLegal=" + this.getIsLegal() + ", "
		 		+ "this.isPersistence=" + this.getIsPersistence() + ", "
		 		+ "this.isClean=" + this.getIsClean() + ", "
		 		+ "this.isReclean=" + this.getIsReclean() + ", "
		 		+ "this.isEs=" + this.getIsEs() + ", "
		 		+ "this.versionId=" + this.getVersionId() + ", "
		 		+ "this.mappingStr=" + this.getMappingStr() + ", "
		 		+ "this.legalStr=" + this.getLegalStr() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}