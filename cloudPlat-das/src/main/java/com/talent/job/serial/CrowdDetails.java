package com.talent.job.serial;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 人群对象
 */
public class CrowdDetails implements Serializable {

    public static final long serialVersionUID = 1L;

    public String crowId;

    public String crowdName;

    // 日期起始
    public YandM dateBeforeDate;

    // 日期起始
    public YandM dateBeforeExtDate;

    // 日期结束
    public YandM dateAfterDate;

    // 日期结束
    public YandM dateAfterExtDate;

    // 纳入规则
    public String inRelationStr;

    // 结局规则
    public String outRelationStr;

    // 随访机构
    public List<String> organList;

    // 随访时间
    public YandM followUpYear;

    public String type;

    // 纳入特征指标
    public Map<String, CrowdFeature> inFeatrueMap;

    // 纳入疾病指标
    public Map<String, CrowdFeature> inDiseaseMap;

    public Map<String, CrowdFeature> inItemMap;

    // 纳入特征指标
    public Map<String, CrowdFeature> outFeatrueMap;

    // 纳入疾病指标
    public Map<String, CrowdFeature> outDiseaseMap;

    public Map<String, CrowdFeature> outItemMap;

    // 时间窗
    public YandM timeWindow;

    public String getCrowId() {
        return crowId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCrowId(String crowId) {
        this.crowId = crowId;
    }

    public List<String> getOrganList() {
        return organList;
    }

    public void setOrganList(List<String> organList) {
        this.organList = organList;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getCrowdName() {
        return crowdName;
    }

    public void setCrowdName(String crowdName) {
        this.crowdName = crowdName;
    }

    public Map<String, CrowdFeature> getInFeatrueMap() {
        return inFeatrueMap;
    }

    public void setInFeatrueMap(Map<String, CrowdFeature> inFeatrueMap) {
        this.inFeatrueMap = inFeatrueMap;
    }

    public Map<String, CrowdFeature> getInDiseaseMap() {
        return inDiseaseMap;
    }

    public void setInDiseaseMap(Map<String, CrowdFeature> inDiseaseMap) {
        this.inDiseaseMap = inDiseaseMap;
    }

    public Map<String, CrowdFeature> getInItemMap() {
        return inItemMap;
    }

    public void setInItemMap(Map<String, CrowdFeature> inItemMap) {
        this.inItemMap = inItemMap;
    }

    public String getInRelationStr() {
        return inRelationStr;
    }

    public void setInRelationStr(String inRelationStr) {
        this.inRelationStr = inRelationStr;
    }

    public String getOutRelationStr() {
        return outRelationStr;
    }

    public void setOutRelationStr(String outRelationStr) {
        this.outRelationStr = outRelationStr;
    }

    public Map<String, CrowdFeature> getOutFeatrueMap() {
        return outFeatrueMap;
    }

    public void setOutFeatrueMap(Map<String, CrowdFeature> outFeatrueMap) {
        this.outFeatrueMap = outFeatrueMap;
    }

    public Map<String, CrowdFeature> getOutDiseaseMap() {
        return outDiseaseMap;
    }

    public void setOutDiseaseMap(Map<String, CrowdFeature> outDiseaseMap) {
        this.outDiseaseMap = outDiseaseMap;
    }

    public Map<String, CrowdFeature> getOutItemMap() {
        return outItemMap;
    }

    public void setOutItemMap(Map<String, CrowdFeature> outItemMap) {
        this.outItemMap = outItemMap;
    }

}
