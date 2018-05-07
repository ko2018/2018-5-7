package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：逻辑运算关系类
 */
public class BaseDiagLogicCalc implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //诊断逻辑id
    private String diagLogicId;

    //运算id
    private String calcId;


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
     * 获得诊断逻辑id
     * @return String
     */
    public String getDiagLogicId(){
        return this.diagLogicId;
    }

    /**
     * 设置诊断逻辑id
     * @param diagLogicId  诊断逻辑id
     */
    public void setDiagLogicId(String diagLogicId){
        this.diagLogicId = diagLogicId;
    }
    /**
     * 获得运算id
     * @return String
     */
    public String getCalcId(){
        return this.calcId;
    }

    /**
     * 设置运算id
     * @param calcId  运算id
     */
    public void setCalcId(String calcId){
        this.calcId = calcId;
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
		return "BaseDiagLogicCalc ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.diagLogicId=" + this.diagLogicId + ", "
		 		+ "this.calcId=" + this.calcId + ", "
		+ "]";   
	}
}