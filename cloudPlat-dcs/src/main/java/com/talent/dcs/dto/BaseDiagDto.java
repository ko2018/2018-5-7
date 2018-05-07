package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseDiag;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：诊断病情表Dto类
 */
public class BaseDiagDto extends BaseDiag {

	@Override
	public String toString() {
		return "BaseDiag ["
		 		+ "this.recordId=" + this.getRecordId() + ", "
		 		+ "this.userId=" + this.getUserId() + ", "
		 		+ "this.dataId=" + this.getDataId() + ", "
		 		+ "this.userCode=" + this.getUserCode() + ", "
		 		+ "this.insId=" + this.getInsId() + ", "
		 		+ "this.docId=" + this.getDocId() + ", "
		 		+ "this.checkCode=" + this.getCheckCode() + ", "
		 		+ "this.diseasesId=" + this.getDiseasesId() + ", "
		 		+ "this.recordTime=" + this.getRecordTime() + ", "
		 		+ "this.isEs=" + this.getIsEs() + ", "
		 		+ "this.resultType=" + this.getResultType() + ", "
		 		+ "this.itemTrue=" + this.getItemTrue() + ", "
		 		+ "this.itemFalse=" + this.getItemFalse() + ", "
		 		+ "this.itemNull=" + this.getItemNull() + ", "
		 		+ "this.itemBlank=" + this.getItemBlank() + ", "
		+ "]";   
	}

}