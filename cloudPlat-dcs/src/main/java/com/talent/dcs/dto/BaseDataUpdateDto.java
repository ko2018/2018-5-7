package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseDataUpdate;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：数据更新表Dto类
 */
public class BaseDataUpdateDto extends BaseDataUpdate {

	@Override
	public String toString() {
		return "BaseDataUpdate ["
		 		+ "this.dataupdateId=" + this.getDataupdateId() + ", "
		 		+ "this.datastdId=" + this.getDatastdId() + ", "
		 		+ "this.userCode=" + this.getUserCode() + ", "
		 		+ "this.institutionId=" + this.getInstitutionId() + ", "
		 		+ "this.checkCode=" + this.getCheckCode() + ", "
		 		+ "this.docId=" + this.getDocId() + ", "
		 		+ "this.dictId=" + this.getDictId() + ", "
		 		+ "this.dataObject=" + this.getDataObject() + ", "
		 		+ "this.isTransfer=" + this.getIsTransfer() + ", "
		 		+ "this.isEs=" + this.getIsEs() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}