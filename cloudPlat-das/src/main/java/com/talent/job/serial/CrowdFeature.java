package com.talent.job.serial;

import java.io.Serializable;
import java.util.List;

/**
 * ��Ⱥ����
 */
public class CrowdFeature implements Serializable {

    private static final long serialVersionUID = 1L;

    public String crowId;

    public String flag;

    public String dictionaryID;

    public String featureID;

    public String featureVal;

    public String featureType;

    public double lowLimit;

    public double upLimit;

    public List<String> valList;

    public String getDictionaryID() {
        return dictionaryID;
    }

    public void setDictionaryID(String dictionaryID) {
        this.dictionaryID = dictionaryID;
    }

    public String getCrowId() {
        return crowId;
    }

    public void setCrowId(String crowId) {
        this.crowId = crowId;
    }

    public String isFlag() {
        return flag;
    }

    public double getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(double lowLimit) {
        this.lowLimit = lowLimit;
    }

    public double getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(double upLimit) {
        this.upLimit = upLimit;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public String getFeatureID() {
        return featureID;
    }

    public void setFeatureID(String featureID) {
        this.featureID = featureID;
    }

    public String getFeatureVal() {
        return featureVal;
    }

    public void setFeatureVal(String featureVal) {
        this.featureVal = featureVal;
    }

    public String getFlag() {
        return flag;
    }

    public List<String> getValList() {
        return valList;
    }

    public void setValList(List<String> valList) {
        this.valList = valList;
    }

}
