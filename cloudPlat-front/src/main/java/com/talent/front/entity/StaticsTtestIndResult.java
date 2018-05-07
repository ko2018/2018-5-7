package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-03 <br/>
 * 描述： 独立T检验结果类
 */
public class StaticsTtestIndResult implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //独立T检验id
    private String sUuid;

    //指标id
    private String sDict;

    //组1总数
    private String sGroup1Sum;

    // 组2总数
    private String sGroup2Sum;

    // 组1平均值
    private String sGroup1Mean;

    //组2平均值
    private String sGroup2Mean;

    //组1标准偏差
    private String sGroup1Std;

    //组1标准误差平均值
    private String sGroup1StdError;

    //组2标准偏差
    private String sGroup2Std;

    //组2标准误差平均值
    private String sGroup2StdError;

    //s_f
    private String sF;

    // 显著性Sig
    private String sSig;

    // 方差齐性t
    private String sEqualT;

    //非方差齐性t
    private String sUnequalT;

    //方差齐性自由度
    private String sEqualFreeDegree;

    //方差非齐性自由度
    private String sUnequalFreeDegree;

    //方差齐性显著性
    private String sEqualSig;

    //方差非齐性显著性
    private String sUnequalSig;

    //方差齐性平均差
    private String sEqualMean;

    //方差非齐性平均差
    private String sUnequalMean;

    //方差齐性标准误差差值
    private String sEqualStdError;

    //方差非齐性标准误差差值
    private String sUnequalStdError;

    //s _std_dev_unit方差齐性 置信区间 上限
    private String sEqualConfidenceLow;

    //方差齐性 置信区间 下限
    private String sEqualConfidenceUp;

    //s_confidence_low方差非齐性 置信区间
    private String sUnequalConfidenceLow;

    //s_confidence_up方差非齐性 置信区间
    private String sUnequalConfidenceUp;

    //creat_time
    private Date addTime;

    //udpate_time
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
     * 获得独立T检验id
     * @return String
     */
    public String getSUuid(){
        return this.sUuid;
    }

    /**
     * 设置独立T检验id
     * @param sUuid  独立T检验id
     */
    public void setSUuid(String sUuid){
        this.sUuid = sUuid;
    }
    /**
     * 获得指标id
     * @return String
     */
    public String getSDict(){
        return this.sDict;
    }

    /**
     * 设置指标id
     * @param sDict  指标id
     */
    public void setSDict(String sDict){
        this.sDict = sDict;
    }
    /**
     * 获得组1总数
     * @return String
     */
    public String getSGroup1Sum(){
        return this.sGroup1Sum;
    }

    /**
     * 设置组1总数
     * @param sGroup1Sum  组1总数
     */
    public void setSGroup1Sum(String sGroup1Sum){
        this.sGroup1Sum = sGroup1Sum;
    }
    /**
     * 获得 组2总数
     * @return String
     */
    public String getSGroup2Sum(){
        return this.sGroup2Sum;
    }

    /**
     * 设置 组2总数
     * @param sGroup2Sum   组2总数
     */
    public void setSGroup2Sum(String sGroup2Sum){
        this.sGroup2Sum = sGroup2Sum;
    }
    /**
     * 获得 组1平均值
     * @return String
     */
    public String getSGroup1Mean(){
        return this.sGroup1Mean;
    }

    /**
     * 设置 组1平均值
     * @param sGroup1Mean   组1平均值
     */
    public void setSGroup1Mean(String sGroup1Mean){
        this.sGroup1Mean = sGroup1Mean;
    }
    /**
     * 获得组2平均值
     * @return String
     */
    public String getSGroup2Mean(){
        return this.sGroup2Mean;
    }

    /**
     * 设置组2平均值
     * @param sGroup2Mean  组2平均值
     */
    public void setSGroup2Mean(String sGroup2Mean){
        this.sGroup2Mean = sGroup2Mean;
    }
    /**
     * 获得组1标准偏差
     * @return String
     */
    public String getSGroup1Std(){
        return this.sGroup1Std;
    }

    /**
     * 设置组1标准偏差
     * @param sGroup1Std  组1标准偏差
     */
    public void setSGroup1Std(String sGroup1Std){
        this.sGroup1Std = sGroup1Std;
    }
    /**
     * 获得标准误差平均值
     * @return String
     */
    public String getSGroup1StdError(){
        return this.sGroup1StdError;
    }

    /**
     * 设置标准误差平均值
     * @param sGroup1StdError  标准误差平均值
     */
    public void setSGroup1StdError(String sGroup1StdError){
        this.sGroup1StdError = sGroup1StdError;
    }
    /**
     * 获得
     * @return String
     */
    public String getSGroup2Std(){
        return this.sGroup2Std;
    }

    /**
     * 设置
     * @param sGroup2Std  
     */
    public void setSGroup2Std(String sGroup2Std){
        this.sGroup2Std = sGroup2Std;
    }
    /**
     * 获得标准误差平均值
     * @return String
     */
    public String getSGroup2StdError(){
        return this.sGroup2StdError;
    }

    /**
     * 设置标准误差平均值
     * @param sGroup2StdError  标准误差平均值
     */
    public void setSGroup2StdError(String sGroup2StdError){
        this.sGroup2StdError = sGroup2StdError;
    }
    /**
     * 获得s_f
     * @return String
     */
    public String getSF(){
        return this.sF;
    }

    /**
     * 设置s_f
     * @param sF  s_f
     */
    public void setSF(String sF){
        this.sF = sF;
    }
    /**
     * 获得 显著性
     * @return String
     */
    public String getSSig(){
        return this.sSig;
    }

    /**
     * 设置 显著性
     * @param sSig   显著性
     */
    public void setSSig(String sSig){
        this.sSig = sSig;
    }
    /**
     * 获得 方差齐性t
     * @return String
     */
    public String getSEqualT(){
        return this.sEqualT;
    }

    /**
     * 设置 方差齐性t
     * @param sEqualT   方差齐性t
     */
    public void setSEqualT(String sEqualT){
        this.sEqualT = sEqualT;
    }
    /**
     * 获得非方差齐性t
     * @return String
     */
    public String getSUnequalT(){
        return this.sUnequalT;
    }

    /**
     * 设置非方差齐性t
     * @param sUnequalT  非方差齐性t
     */
    public void setSUnequalT(String sUnequalT){
        this.sUnequalT = sUnequalT;
    }
    /**
     * 获得方差齐性自由度
     * @return String
     */
    public String getSEqualFreeDegree(){
        return this.sEqualFreeDegree;
    }

    /**
     * 设置方差齐性自由度
     * @param sEqualFreeDegree  方差齐性自由度
     */
    public void setSEqualFreeDegree(String sEqualFreeDegree){
        this.sEqualFreeDegree = sEqualFreeDegree;
    }
    /**
     * 获得方差非齐性自由度
     * @return String
     */
    public String getSUnequalFreeDegree(){
        return this.sUnequalFreeDegree;
    }

    /**
     * 设置方差非齐性自由度
     * @param sUnequalFreeDegree  方差非齐性自由度
     */
    public void setSUnequalFreeDegree(String sUnequalFreeDegree){
        this.sUnequalFreeDegree = sUnequalFreeDegree;
    }
    /**
     * 获得方差齐性显著性
     * @return String
     */
    public String getSEqualSig(){
        return this.sEqualSig;
    }

    /**
     * 设置方差齐性显著性
     * @param sEqualSig  方差齐性显著性
     */
    public void setSEqualSig(String sEqualSig){
        this.sEqualSig = sEqualSig;
    }
    /**
     * 获得方差非齐性显著性
     * @return String
     */
    public String getSUnequalSig(){
        return this.sUnequalSig;
    }

    /**
     * 设置方差非齐性显著性
     * @param sUnequalSig  方差非齐性显著性
     */
    public void setSUnequalSig(String sUnequalSig){
        this.sUnequalSig = sUnequalSig;
    }
    /**
     * 获得方差齐性平均差
     * @return String
     */
    public String getSEqualMean(){
        return this.sEqualMean;
    }

    /**
     * 设置方差齐性平均差
     * @param sEqualMean  方差齐性平均差
     */
    public void setSEqualMean(String sEqualMean){
        this.sEqualMean = sEqualMean;
    }
    /**
     * 获得方差非齐性平均差
     * @return String
     */
    public String getSUnequalMean(){
        return this.sUnequalMean;
    }

    /**
     * 设置方差非齐性平均差
     * @param sUnequalMean  方差非齐性平均差
     */
    public void setSUnequalMean(String sUnequalMean){
        this.sUnequalMean = sUnequalMean;
    }
    /**
     * 获得方差齐性标准误差差值
     * @return String
     */
    public String getSEqualStdError(){
        return this.sEqualStdError;
    }

    /**
     * 设置方差齐性标准误差差值
     * @param sEqualStdError  方差齐性标准误差差值
     */
    public void setSEqualStdError(String sEqualStdError){
        this.sEqualStdError = sEqualStdError;
    }
    /**
     * 获得方差非齐性标准误差差值
     * @return String
     */
    public String getSUnequalStdError(){
        return this.sUnequalStdError;
    }

    /**
     * 设置方差非齐性标准误差差值
     * @param sUnequalStdError  方差非齐性标准误差差值
     */
    public void setSUnequalStdError(String sUnequalStdError){
        this.sUnequalStdError = sUnequalStdError;
    }
    /**
     * 获得s _std_dev_unit
     * @return String
     */
    public String getSEqualConfidenceLow(){
        return this.sEqualConfidenceLow;
    }

    /**
     * 设置s _std_dev_unit
     * @param sEqualConfidenceLow  s _std_dev_unit
     */
    public void setSEqualConfidenceLow(String sEqualConfidenceLow){
        this.sEqualConfidenceLow = sEqualConfidenceLow;
    }
    /**
     * 获得
     * @return String
     */
    public String getSEqualConfidenceUp(){
        return this.sEqualConfidenceUp;
    }

    /**
     * 设置
     * @param sEqualConfidenceUp  
     */
    public void setSEqualConfidenceUp(String sEqualConfidenceUp){
        this.sEqualConfidenceUp = sEqualConfidenceUp;
    }
    /**
     * 获得s_confidence_low
     * @return String
     */
    public String getSUnequalConfidenceLow(){
        return this.sUnequalConfidenceLow;
    }

    /**
     * 设置s_confidence_low
     * @param sUnequalConfidenceLow  s_confidence_low
     */
    public void setSUnequalConfidenceLow(String sUnequalConfidenceLow){
        this.sUnequalConfidenceLow = sUnequalConfidenceLow;
    }
    /**
     * 获得s_confidence_up
     * @return String
     */
    public String getSUnequalConfidenceUp(){
        return this.sUnequalConfidenceUp;
    }

    /**
     * 设置s_confidence_up
     * @param sUnequalConfidenceUp  s_confidence_up
     */
    public void setSUnequalConfidenceUp(String sUnequalConfidenceUp){
        this.sUnequalConfidenceUp = sUnequalConfidenceUp;
    }
    /**
     * 获得creat_time
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置creat_time
     * @param addTime  creat_time
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得udpate_time
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置udpate_time
     * @param updateTime  udpate_time
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
		return "StaticsTtestIndResult ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.sUuid=" + this.sUuid + ", "
		 		+ "this.sDict=" + this.sDict + ", "
		 		+ "this.sGroup1Sum=" + this.sGroup1Sum + ", "
		 		+ "this.sGroup2Sum=" + this.sGroup2Sum + ", "
		 		+ "this.sGroup1Mean=" + this.sGroup1Mean + ", "
		 		+ "this.sGroup2Mean=" + this.sGroup2Mean + ", "
		 		+ "this.sGroup1Std=" + this.sGroup1Std + ", "
		 		+ "this.sGroup1StdError=" + this.sGroup1StdError + ", "
		 		+ "this.sGroup2Std=" + this.sGroup2Std + ", "
		 		+ "this.sGroup2StdError=" + this.sGroup2StdError + ", "
		 		+ "this.sF=" + this.sF + ", "
		 		+ "this.sSig=" + this.sSig + ", "
		 		+ "this.sEqualT=" + this.sEqualT + ", "
		 		+ "this.sUnequalT=" + this.sUnequalT + ", "
		 		+ "this.sEqualFreeDegree=" + this.sEqualFreeDegree + ", "
		 		+ "this.sUnequalFreeDegree=" + this.sUnequalFreeDegree + ", "
		 		+ "this.sEqualSig=" + this.sEqualSig + ", "
		 		+ "this.sUnequalSig=" + this.sUnequalSig + ", "
		 		+ "this.sEqualMean=" + this.sEqualMean + ", "
		 		+ "this.sUnequalMean=" + this.sUnequalMean + ", "
		 		+ "this.sEqualStdError=" + this.sEqualStdError + ", "
		 		+ "this.sUnequalStdError=" + this.sUnequalStdError + ", "
		 		+ "this.sEqualConfidenceLow=" + this.sEqualConfidenceLow + ", "
		 		+ "this.sEqualConfidenceUp=" + this.sEqualConfidenceUp + ", "
		 		+ "this.sUnequalConfidenceLow=" + this.sUnequalConfidenceLow + ", "
		 		+ "this.sUnequalConfidenceUp=" + this.sUnequalConfidenceUp + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}