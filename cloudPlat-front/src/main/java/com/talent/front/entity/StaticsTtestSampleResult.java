package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验的结果类
 */
public class StaticsTtestSampleResult implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //
    private String sId;

    //
    private String sDictId;

    //s_sum：记录总数
    private String sSum;

    //s_mean：平均值
    private String sMean;

    //s_std：标准差
    private String sStd;

    //s_std_dev：标准误差
    private String sStdDev;

    //s_t
    private String sT;

    //s_confidence_low：置信区间下限
    private String sConfidenceLow;

    //s_free_degree：自由度
    private String sFreeDegree;

    //s_p：显著性
    private String sP;

    //s_mean_dev：平均差
    private String sMeanDev;

    //s_confidence_up：置信区间上限
    private String sConfidenceUp;

    //s_confidence：置信区间
    private String sConfidence;

    //s_expect_val：检验值
    private String sExpectVal;

    //start_time
    private Date addTime;

    //update_time
    private Date updateTime;


    /**
     * 获得id
     * @return String
     */
    public String getId(){
        return this.id;
    }

    /**
     * 设置id
     * @param id  id
     */
    public void setId(String id){
        this.id = id;
    }
    /**
     * 获得
     * @return String
     */
    public String getSId(){
        return this.sId;
    }

    /**
     * 设置
     * @param sId  
     */
    public void setSId(String sId){
        this.sId = sId;
    }
    /**
     * 获得
     * @return String
     */
    public String getSDictId(){
        return this.sDictId;
    }

    /**
     * 设置
     * @param sDictId  
     */
    public void setSDictId(String sDictId){
        this.sDictId = sDictId;
    }
    /**
     * 获得s_sum：记录总数
     * @return String
     */
    public String getSSum(){
        return this.sSum;
    }

    /**
     * 设置s_sum：记录总数
     * @param sSum  s_sum：记录总数
     */
    public void setSSum(String sSum){
        this.sSum = sSum;
    }
    /**
     * 获得s_mean：平均值
     * @return String
     */
    public String getSMean(){
        return this.sMean;
    }

    /**
     * 设置s_mean：平均值
     * @param sMean  s_mean：平均值
     */
    public void setSMean(String sMean){
        this.sMean = sMean;
    }
    /**
     * 获得s_std：标准差
     * @return String
     */
    public String getSStd(){
        return this.sStd;
    }

    /**
     * 设置s_std：标准差
     * @param sStd  s_std：标准差
     */
    public void setSStd(String sStd){
        this.sStd = sStd;
    }
    /**
     * 获得s_std_dev：标准误差
     * @return String
     */
    public String getSStdDev(){
        return this.sStdDev;
    }

    /**
     * 设置s_std_dev：标准误差
     * @param sStdDev  s_std_dev：标准误差
     */
    public void setSStdDev(String sStdDev){
        this.sStdDev = sStdDev;
    }
    /**
     * 获得s_t
     * @return String
     */
    public String getST(){
        return this.sT;
    }

    /**
     * 设置s_t
     * @param sT  s_t
     */
    public void setST(String sT){
        this.sT = sT;
    }
    /**
     * 获得s_confidence_low：置信区间下限
     * @return String
     */
    public String getSConfidenceLow(){
        return this.sConfidenceLow;
    }

    /**
     * 设置s_confidence_low：置信区间下限
     * @param sConfidenceLow  s_confidence_low：置信区间下限
     */
    public void setSConfidenceLow(String sConfidenceLow){
        this.sConfidenceLow = sConfidenceLow;
    }
    /**
     * 获得s_free_degree：自由度
     * @return String
     */
    public String getSFreeDegree(){
        return this.sFreeDegree;
    }

    /**
     * 设置s_free_degree：自由度
     * @param sFreeDegree  s_free_degree：自由度
     */
    public void setSFreeDegree(String sFreeDegree){
        this.sFreeDegree = sFreeDegree;
    }
    /**
     * 获得s_p：显著性
     * @return String
     */
    public String getSP(){
        return this.sP;
    }

    /**
     * 设置s_p：显著性
     * @param sP  s_p：显著性
     */
    public void setSP(String sP){
        this.sP = sP;
    }
    /**
     * 获得s_mean_dev：平均差
     * @return String
     */
    public String getSMeanDev(){
        return this.sMeanDev;
    }

    /**
     * 设置s_mean_dev：平均差
     * @param sMeanDev  s_mean_dev：平均差
     */
    public void setSMeanDev(String sMeanDev){
        this.sMeanDev = sMeanDev;
    }
    /**
     * 获得s_confidence_up：置信区间上限
     * @return String
     */
    public String getSConfidenceUp(){
        return this.sConfidenceUp;
    }

    /**
     * 设置s_confidence_up：置信区间上限
     * @param sConfidenceUp  s_confidence_up：置信区间上限
     */
    public void setSConfidenceUp(String sConfidenceUp){
        this.sConfidenceUp = sConfidenceUp;
    }
    /**
     * 获得s_confidence：置信区间
     * @return String
     */
    public String getSConfidence(){
        return this.sConfidence;
    }

    /**
     * 设置s_confidence：置信区间
     * @param sConfidence  s_confidence：置信区间
     */
    public void setSConfidence(String sConfidence){
        this.sConfidence = sConfidence;
    }
    /**
     * 获得s_expect_val：检验值
     * @return String
     */
    public String getSExpectVal(){
        return this.sExpectVal;
    }

    /**
     * 设置s_expect_val：检验值
     * @param sExpectVal  s_expect_val：检验值
     */
    public void setSExpectVal(String sExpectVal){
        this.sExpectVal = sExpectVal;
    }
    /**
     * 获得start_time
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置start_time
     * @param addTime  start_time
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得update_time
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置update_time
     * @param updateTime  update_time
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.id = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.id;
	}
	
	
	@Override
	public String toString() {
		return "StaticsTtestSampleResult ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.sId=" + this.sId + ", "
		 		+ "this.sDictId=" + this.sDictId + ", "
		 		+ "this.sSum=" + this.sSum + ", "
		 		+ "this.sMean=" + this.sMean + ", "
		 		+ "this.sStd=" + this.sStd + ", "
		 		+ "this.sStdDev=" + this.sStdDev + ", "
		 		+ "this.sT=" + this.sT + ", "
		 		+ "this.sConfidenceLow=" + this.sConfidenceLow + ", "
		 		+ "this.sFreeDegree=" + this.sFreeDegree + ", "
		 		+ "this.sP=" + this.sP + ", "
		 		+ "this.sMeanDev=" + this.sMeanDev + ", "
		 		+ "this.sConfidenceUp=" + this.sConfidenceUp + ", "
		 		+ "this.sConfidence=" + this.sConfidence + ", "
		 		+ "this.sExpectVal=" + this.sExpectVal + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}