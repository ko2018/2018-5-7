package com.talent.job.serial;import java.io.Serializable;import java.util.ArrayList;import java.util.List;/** ���ڷϵ� */public class BaseDisData implements Serializable {    private static final long serialVersionUID = 1L;    public String organID;    public String examID;    public String codeID;    public String examTime;    public String diseaseID;    public List<String> diseaseList = new ArrayList<String>();    public void setDiseaseList() {        diseaseList.clear();        String[] arrs = this.diseaseID.split(",");        for (int i = 0; i < arrs.length; i++) {            diseaseList.add(arrs[i]);        }    }    public String examYear = "";    public int examYearInt;    public String getExamYear() {        try {            if (this.examYear != null && !"".equals(this.examYear))                return this.examYear;            int index = examTime.indexOf("-");            return this.examTime.substring(0, index);        } catch (Exception e) {            e.printStackTrace();            return "";        }    }    public String getExamTime() {        return examTime;    }    public void setExamTime(String examTime) {        this.examTime = examTime;    }    public String getDiseaseID() {        return diseaseID;    }    public void setDiseaseID(String diseaseID) {        this.diseaseID = diseaseID;    }    public String getOrganID() {        return organID;    }    public void setOrganID(String organID) {        this.organID = organID;    }    public String getExamID() {        return examID;    }    public void setExamID(String examID) {        this.examID = examID;    }    public String getCodeID() {        return codeID;    }    public void setCodeID(String codeID) {        this.codeID = codeID;    }    public List<String> getDiseaseList() {        return diseaseList;    }    public void setDiseaseList(List<String> diseaseList) {        this.diseaseList = diseaseList;    }}