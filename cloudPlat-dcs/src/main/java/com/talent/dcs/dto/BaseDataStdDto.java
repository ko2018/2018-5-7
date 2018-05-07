package com.talent.dcs.dto;

import com.talent.dcs.entity.BaseDataStd;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：标准数据表Dto类
 */
public class BaseDataStdDto extends BaseDataStd {

	@Override
	public String toString() {
		return "BaseDataStd [" + "this.datastdId=" + this.getDatastdId() + ", " + "this.userCode=" + this.getUserCode()
				+ ", " + "this.institutionId=" + this.getInstitutionId() + ", " + "this.checkCode="
				+ this.getCheckCode() + ", " + "this.docId=" + this.getDocId() + ", " + "this.dataObject="
				+ this.getDataObject() + ", " + "this.isTransfer=" + this.getIsTransfer() + ", " + "this.isEs="
				+ this.getIsEs() + ", " + "this.creator=" + this.getCreator() + ", " + "this.deleteStatus="
				+ this.getDeleteStatus() + ", " + "this.addTime=" + this.getAddTime() + ", " + "this.updateUser="
				+ this.getUpdateUser() + ", " + "this.updateTime=" + this.getUpdateTime() + ", " + "]";
	}

}