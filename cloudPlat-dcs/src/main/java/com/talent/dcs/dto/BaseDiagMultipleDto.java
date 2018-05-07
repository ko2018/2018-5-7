package com.talent.dcs.dto;

import java.util.List;

import com.talent.dcs.entity.BaseDiagMultiple;
import com.talent.dcs.entity.BaseDiseasesSys;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-25 <br/>
 * 描述：综合诊断表Dto类
 */
public class BaseDiagMultipleDto extends BaseDiagMultiple {

    private static final long serialVersionUID = 1L;

    private List<BaseDiseasesSys> diseases;
    
	public List<BaseDiseasesSys> getDiseases()
    {
        return diseases;
    }

    public void setDiseases(List<BaseDiseasesSys> diseases)
    {
        this.diseases = diseases;
    }

	@Override
	public String toString() {
		return "BaseDiagMultiple ["
		 		+ "this.recordId=" + this.getRecordId() + ", "
		 		+ "this.userId=" + this.getUserId() + ", "
		 		+ "this.dataId=" + this.getDataId() + ", "
		 		+ "this.userCode=" + this.getUserCode() + ", "
		 		+ "this.insId=" + this.getInsId() + ", "
		 		+ "this.docId=" + this.getDocId() + ", "
		 		+ "this.checkCode=" + this.getCheckCode() + ", "
		 		+ "this.multipleDiagTrue=" + this.getMultipleDiagTrue() + ", "
		 		+ "this.multipleDiagFlase=" + this.getMultipleDiagFalse() + ", "
		 		+ "this.multipleDiagNull=" + this.getMultipleDiagNull() + ", "
		 		+ "this.multipleDiagBlank=" + this.getMultipleDiagBlank() + ", "
		 		+ "this.recordTime=" + this.getRecordTime() + ", "
		 		+ "this.isEs=" + this.getIsEs() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}