package com.talent.front.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseDiagMultiple;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：综合诊断表Dto类
 */
@JsonInclude(Include.NON_NULL)
public class BaseDiagMultipleDto extends BaseDiagMultiple {

    private static final long serialVersionUID = 1061819294698312990L;

    private List<BaseDiseasesSysDto> diseases = new ArrayList();

    private String multipleResult;

    private String insName;

    public List<BaseDiseasesSysDto> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<BaseDiseasesSysDto> diseases) {
        this.diseases = diseases;
    }

    public String getMultipleResult() {
        return multipleResult;
    }

    public void setMultipleResult(String multipleResult) {
        this.multipleResult = multipleResult;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    @Override
    public String toString() {
        return "BaseDiagMultiple [" + "this.recordId=" + this.getRecordId() + ", " + "this.userId=" + this.getUserId()
                + ", " + "this.dataId=" + this.getDataId() + ", " + "this.userCode=" + this.getUserCode() + ", "
                + "this.insId=" + this.getInsId() + ", " + "this.docId=" + this.getDocId() + ", " + "this.checkCode="
                + this.getCheckCode() + ", " + "this.multipleDiagTrue=" + this.getMultipleDiagTrue() + ", "
                + "this.multipleDiagFalse=" + this.getMultipleDiagFalse() + ", " + "this.multipleDiagNull="
                + this.getMultipleDiagNull() + ", " + "this.multipleDiagBlank=" + this.getMultipleDiagBlank() + ", "
                + "this.recordTime=" + this.getRecordTime() + ", " + "this.isEs=" + this.getIsEs() + ", "
                + "this.creator=" + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", "
                + "this.updateUser=" + this.getUpdateUser() + ", " + "this.updateTime=" + this.getUpdateTime() + ", "
                + "]";
    }

}