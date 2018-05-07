package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：术语值规则类
 */
public class BaseSnlRuleValue implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String valueId;

    //术语id
    private String snlId;

    //术语编码
    private String snlCode;

    //字典值
    private Integer dictId;

    //合法标识
    private Integer isLegitimate;

    //术语值类型 数值-1 文本-2
    private String valuetypeId;

    //类型名称
    private String valuetypeName;

    //下限值
    private Double lowerLimit;

    //上限值
    private Double upperLimit;

    //关键字
    private String keyWord;

    //运算逻辑
    private String arithmeticLogic;

    //运算系数
    private Integer arithmeticNum;

    //创建者
    private String creator;

    //更新者
    private String updateUser;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * 获得id
     * @return String
     */
    public String getValueId(){
        return this.valueId;
    }

    /**
     * 设置id
     * @param valueId  id
     */
    public void setValueId(String valueId){
        this.valueId = valueId;
    }
    /**
     * 获得术语id
     * @return String
     */
    public String getSnlId(){
        return this.snlId;
    }

    /**
     * 设置术语id
     * @param snlId  术语id
     */
    public void setSnlId(String snlId){
        this.snlId = snlId;
    }
    /**
     * 获得术语编码
     * @return String
     */
    public String getSnlCode(){
        return this.snlCode;
    }

    /**
     * 设置术语编码
     * @param snlCode  术语编码
     */
    public void setSnlCode(String snlCode){
        this.snlCode = snlCode;
    }
    /**
     * 获得字典值
     * @return Integer
     */
    public Integer getDictId(){
        return this.dictId;
    }

    /**
     * 设置字典值
     * @param dictId  字典值
     */
    public void setDictId(Integer dictId){
        this.dictId = dictId;
    }
    /**
     * 获得合法标识
     * @return Integer
     */
    public Integer getIsLegitimate(){
        return this.isLegitimate;
    }

    /**
     * 设置合法标识
     * @param isLegitimate  合法标识
     */
    public void setIsLegitimate(Integer isLegitimate){
        this.isLegitimate = isLegitimate;
    }
    /**
     * 获得术语值类型 数值-1 文本-2
     * @return String
     */
    public String getValuetypeId(){
        return this.valuetypeId;
    }

    /**
     * 设置术语值类型 数值-1 文本-2
     * @param valuetypeId  术语值类型 数值-1 文本-2
     */
    public void setValuetypeId(String valuetypeId){
        this.valuetypeId = valuetypeId;
    }
    /**
     * 获得类型名称
     * @return String
     */
    public String getValuetypeName(){
        return this.valuetypeName;
    }

    /**
     * 设置类型名称
     * @param valuetypeName  类型名称
     */
    public void setValuetypeName(String valuetypeName){
        this.valuetypeName = valuetypeName;
    }
    /**
     * 获得下限值
     * @return Double
     */
    public Double getLowerLimit(){
        return this.lowerLimit;
    }

    /**
     * 设置下限值
     * @param lowerLimit  下限值
     */
    public void setLowerLimit(Double lowerLimit){
        this.lowerLimit = lowerLimit;
    }
    /**
     * 获得上限值
     * @return Double
     */
    public Double getUpperLimit(){
        return this.upperLimit;
    }

    /**
     * 设置上限值
     * @param upperLimit  上限值
     */
    public void setUpperLimit(Double upperLimit){
        this.upperLimit = upperLimit;
    }
    /**
     * 获得关键字
     * @return String
     */
    public String getKeyWord(){
        return this.keyWord;
    }

    /**
     * 设置关键字
     * @param keyWord  关键字
     */
    public void setKeyWord(String keyWord){
        this.keyWord = keyWord;
    }
    /**
     * 获得运算逻辑
     * @return String
     */
    public String getArithmeticLogic(){
        return this.arithmeticLogic;
    }

    /**
     * 设置运算逻辑
     * @param arithmeticLogic  运算逻辑
     */
    public void setArithmeticLogic(String arithmeticLogic){
        this.arithmeticLogic = arithmeticLogic;
    }
    /**
     * 获得运算系数
     * @return Integer
     */
    public Integer getArithmeticNum(){
        return this.arithmeticNum;
    }

    /**
     * 设置运算系数
     * @param arithmeticNum  运算系数
     */
    public void setArithmeticNum(Integer arithmeticNum){
        this.arithmeticNum = arithmeticNum;
    }
    /**
     * 获得创建者
     * @return String
     */
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置创建者
     * @param creator  创建者
     */
    public void setCreator(String creator){
        this.creator = creator;
    }
    /**
     * 获得更新者
     * @return String
     */
    public String getUpdateUser(){
        return this.updateUser;
    }

    /**
     * 设置更新者
     * @param updateUser  更新者
     */
    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
    }
    /**
     * 获得创建时间
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置创建时间
     * @param addTime  创建时间
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得更新时间
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置更新时间
     * @param updateTime  更新时间
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.valueId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.valueId;
	}
	
	
	@Override
	public String toString() {
		return "BaseSnlRuleValue ["
		 		+ "this.valueId=" + this.valueId + ", "
		 		+ "this.snlId=" + this.snlId + ", "
		 		+ "this.snlCode=" + this.snlCode + ", "
		 		+ "this.dictId=" + this.dictId + ", "
		 		+ "this.isLegitimate=" + this.isLegitimate + ", "
		 		+ "this.valuetypeId=" + this.valuetypeId + ", "
		 		+ "this.valuetypeName=" + this.valuetypeName + ", "
		 		+ "this.lowerLimit=" + this.lowerLimit + ", "
		 		+ "this.upperLimit=" + this.upperLimit + ", "
		 		+ "this.keyWord=" + this.keyWord + ", "
		 		+ "this.arithmeticLogic=" + this.arithmeticLogic + ", "
		 		+ "this.arithmeticNum=" + this.arithmeticNum + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}