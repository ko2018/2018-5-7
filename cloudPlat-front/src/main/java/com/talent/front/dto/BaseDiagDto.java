package com.talent.front.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseDiag;
import com.talent.front.entity.BaseDiseasesSys;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断病情表Dto类
 */
@JsonInclude(Include.NON_NULL)
public class BaseDiagDto extends BaseDiag {

    private static final long serialVersionUID = 4795248799708078257L;

    private BaseDiseasesSys baseDiseasesSys;

    private List<BaseItemDto> baseItems = new ArrayList();

    public BaseDiseasesSys getBaseDiseasesSys() {
        return baseDiseasesSys;
    }

    public void setBaseDiseasesSys(BaseDiseasesSys baseDiseasesSys) {
        this.baseDiseasesSys = baseDiseasesSys;
    }

    public List<BaseItemDto> getBaseItems() {
        return baseItems;
    }

    public void setBaseItems(List<BaseItemDto> baseItems) {
        this.baseItems = baseItems;
    }

    @Override
    public String toString() {
        return "BaseDiag [" + "this.recordId=" + this.getRecordId() + ", " + "this.userId=" + this.getUserId() + ", "
                + "this.dataId=" + this.getDataId() + ", " + "this.userCode=" + this.getUserCode() + ", "
                + "this.insId=" + this.getInsId() + ", " + "this.docId=" + this.getDocId() + ", " + "this.checkCode="
                + this.getCheckCode() + ", " + "this.diseasesId=" + this.getDiseasesId() + ", " + "this.recordTime="
                + this.getRecordTime() + ", " + "this.isEs=" + this.getIsEs() + ", " + "this.resultType="
                + this.getResultType() + ", " + "this.itemTrue=" + this.getItemTrue() + ", " + "this.itemFalse="
                + this.getItemFalse() + ", " + "this.itemNull=" + this.getItemNull() + ", " + "this.itemBlank="
                + this.getItemBlank() + ", " + "]";
    }

}