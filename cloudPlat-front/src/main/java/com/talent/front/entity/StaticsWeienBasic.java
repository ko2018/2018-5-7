package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-12 <br/>
 * 描述：类
 */
public class StaticsWeienBasic implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //
    private String id;

    //用户id
    private String personId;

    //日期
    private String date;

    //性别
    private String examSex;

    //年龄层
    private String examAgeGroup;

    //诊断结果
    private String disResult;

    //机构
    private String groupPro;

    //知识库id
    private String knowlegeId;


    /**
     * 获得
     * @return String
     */
    public String getId(){
        return this.id;
    }

    /**
     * 设置
     * @param id  
     */
    public void setId(String id){
        this.id = id;
    }
    /**
     * 获得用户id
     * @return String
     */
    public String getPersonId(){
        return this.personId;
    }

    /**
     * 设置用户id
     * @param personId  用户id
     */
    public void setPersonId(String personId){
        this.personId = personId;
    }
    /**
     * 获得日期
     * @return String
     */
    public String getDate(){
        return this.date;
    }

    /**
     * 设置日期
     * @param date  日期
     */
    public void setDate(String date){
        this.date = date;
    }
    /**
     * 获得性别
     * @return String
     */
    public String getExamSex(){
        return this.examSex;
    }

    /**
     * 设置性别
     * @param examSex  性别
     */
    public void setExamSex(String examSex){
        this.examSex = examSex;
    }
    /**
     * 获得年龄层
     * @return String
     */
    public String getExamAgeGroup(){
        return this.examAgeGroup;
    }

    /**
     * 设置年龄层
     * @param examAgeGroup  年龄层
     */
    public void setExamAgeGroup(String examAgeGroup){
        this.examAgeGroup = examAgeGroup;
    }
    /**
     * 获得诊断结果
     * @return String
     */
    public String getDisResult(){
        return this.disResult;
    }

    /**
     * 设置诊断结果
     * @param disResult  诊断结果
     */
    public void setDisResult(String disResult){
        this.disResult = disResult;
    }
    /**
     * 获得机构
     * @return String
     */
    public String getGroupPro(){
        return this.groupPro;
    }

    /**
     * 设置机构
     * @param groupPro  机构
     */
    public void setGroupPro(String groupPro){
        this.groupPro = groupPro;
    }
    /**
     * 获得知识库id
     * @return String
     */
    public String getKnowlegeId(){
        return this.knowlegeId;
    }

    /**
     * 设置知识库id
     * @param knowlegeId  知识库id
     */
    public void setKnowlegeId(String knowlegeId){
        this.knowlegeId = knowlegeId;
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
		return "StaticsWeienBasic ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.personId=" + this.personId + ", "
		 		+ "this.date=" + this.date + ", "
		 		+ "this.examSex=" + this.examSex + ", "
		 		+ "this.examAgeGroup=" + this.examAgeGroup + ", "
		 		+ "this.disResult=" + this.disResult + ", "
		 		+ "this.groupPro=" + this.groupPro + ", "
		 		+ "this.knowlegeId=" + this.knowlegeId + ", "
		+ "]";   
	}
}