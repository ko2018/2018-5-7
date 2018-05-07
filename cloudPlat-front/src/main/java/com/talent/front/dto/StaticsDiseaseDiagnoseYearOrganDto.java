package com.talent.front.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.StaticsDiseaseDiagnoseYearOrgan;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：统计临时疾病诊断年表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "organIDList", "addTime", "updateTime" })
public class StaticsDiseaseDiagnoseYearOrganDto extends StaticsDiseaseDiagnoseYearOrgan {
    public BaseDiseasesSysDto baseDiseasesSysDto;

    public BaseInstitutionDto baseInstitutionDto;

    private List<String> organIDList;

    private int count;

    private String nameCn;

    public List<String> getOrganIDList() {
        return organIDList;
    }

    public void setOrganIDList(List<String> organIDList) {
        this.organIDList = organIDList;
    }

    @Override
    public String toString() {
        return "StaticsDiseaseDiagnoseYearOrgan [" + "this.staticsId=" + this.getStaticsId() + ", "
                + "this.staticsDisease=" + this.getStaticsDisease() + ", " + "this.staticsV=" + this.getStaticsV()
                + ", " + "this.staticsUid=" + this.getStaticsUid() + ", " + "this.staticsTime=" + this.getStaticsTime()
                + ", " + "this.staticsOrgan=" + this.getStaticsOrgan() + ", " + "this.staticsBirth="
                + this.getStaticsBirth() + ", " + "this.staticsAgeGroup=" + this.getStaticsAgeGroup() + ", "
                + "this.staticsSex=" + this.getStaticsSex() + ", " + "this.staticsExamList=" + this.getStaticsExamList()
                + ", " + "this.addTime=" + this.getAddTime() + ", " + "this.updateTime=" + this.getUpdateTime() + ", "
                + "]";
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

}