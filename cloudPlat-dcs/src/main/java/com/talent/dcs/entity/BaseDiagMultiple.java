package com.talent.dcs.entity;


import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-10 <br/>
 * 描述：综合诊断表类
 */
public class BaseDiagMultiple implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //记录id
    private String recordId;

    //用户id
    private String userId;

    //数据id
    private String dataId;

    //身份编号
    private String userCode;

    //机构编码
    private String insId;

    //文档id
    private String docId;

    //体检编号
    private String checkCode;

    //患病诊断
    private String multipleDiagTrue;

    //未患病诊断
    private String multipleDiagFalse;

    //空值诊断
    private String multipleDiagNull;

    //空白诊断
    private String multipleDiagBlank;

    //记录时间
    private Date recordTime;

    //是否存入es
    private String isEs;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    private Date updateTime;


    /**
     * 获得记录id
     * @return String
     */
    public String getRecordId(){
        return this.recordId;
    }

    /**
     * 设置记录id
     * @param recordId  记录id
     */
    public void setRecordId(String recordId){
        this.recordId = recordId;
    }
    /**
     * 获得用户id
     * @return String
     */
    public String getUserId(){
        return this.userId;
    }

    /**
     * 设置用户id
     * @param userId  用户id
     */
    public void setUserId(String userId){
        this.userId = userId;
    }
    /**
     * 获得数据id
     * @return String
     */
    public String getDataId(){
        return this.dataId;
    }

    /**
     * 设置数据id
     * @param dataId  数据id
     */
    public void setDataId(String dataId){
        this.dataId = dataId;
    }
    /**
     * 获得身份编号
     * @return String
     */
    public String getUserCode(){
        return this.userCode;
    }

    /**
     * 设置身份编号
     * @param userCode  身份编号
     */
    public void setUserCode(String userCode){
        this.userCode = userCode;
    }
    /**
     * 获得机构编码
     * @return String
     */
    public String getInsId(){
        return this.insId;
    }

    /**
     * 设置机构编码
     * @param insId  机构编码
     */
    public void setInsId(String insId){
        this.insId = insId;
    }
    /**
     * 获得文档id
     * @return String
     */
    public String getDocId(){
        return this.docId;
    }

    /**
     * 设置文档id
     * @param docId  文档id
     */
    public void setDocId(String docId){
        this.docId = docId;
    }
    /**
     * 获得体检编号
     * @return String
     */
    public String getCheckCode(){
        return this.checkCode;
    }

    /**
     * 设置体检编号
     * @param checkCode  体检编号
     */
    public void setCheckCode(String checkCode){
        this.checkCode = checkCode;
    }
    /**
     * 获得患病诊断
     * @return String
     */
    public String getMultipleDiagTrue(){
        return this.multipleDiagTrue;
    }

    /**
     * 设置患病诊断
     * @param multipleDiagTrue  患病诊断
     */
    public void setMultipleDiagTrue(String multipleDiagTrue){
        this.multipleDiagTrue = multipleDiagTrue;
    }
    /**
     * 获得未患病诊断
     * @return String
     */
    public String getMultipleDiagFalse(){
        return this.multipleDiagFalse;
    }

    /**
     * 设置未患病诊断
     * @param multipleDiagFalse  未患病诊断
     */
    public void setMultipleDiagFalse(String multipleDiagFalse){
        this.multipleDiagFalse = multipleDiagFalse;
    }
    /**
     * 获得空值诊断
     * @return String
     */
    public String getMultipleDiagNull(){
        return this.multipleDiagNull;
    }

    /**
     * 设置空值诊断
     * @param multipleDiagNull  空值诊断
     */
    public void setMultipleDiagNull(String multipleDiagNull){
        this.multipleDiagNull = multipleDiagNull;
    }
    /**
     * 获得空白诊断
     * @return String
     */
    public String getMultipleDiagBlank(){
        return this.multipleDiagBlank;
    }

    /**
     * 设置空白诊断
     * @param multipleDiagBlank  空白诊断
     */
    public void setMultipleDiagBlank(String multipleDiagBlank){
        this.multipleDiagBlank = multipleDiagBlank;
    }
    /**
     * 获得记录时间
     * @return Date
     */
    public Date getRecordTime(){
        return this.recordTime;
    }

    /**
     * 设置记录时间
     * @param recordTime  记录时间
     */
    public void setRecordTime(Date recordTime){
        this.recordTime = recordTime;
    }
    /**
     * 获得是否存入es
     * @return String
     */
    public String getIsEs(){
        return this.isEs;
    }

    /**
     * 设置是否存入es
     * @param isEs  是否存入es
     */
    public void setIsEs(String isEs){
        this.isEs = isEs;
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
		this.recordId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.recordId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDiagMultiple ["
		 		+ "this.recordId=" + this.recordId + ", "
		 		+ "this.userId=" + this.userId + ", "
		 		+ "this.dataId=" + this.dataId + ", "
		 		+ "this.userCode=" + this.userCode + ", "
		 		+ "this.insId=" + this.insId + ", "
		 		+ "this.docId=" + this.docId + ", "
		 		+ "this.checkCode=" + this.checkCode + ", "
		 		+ "this.multipleDiagTrue=" + this.multipleDiagTrue + ", "
		 		+ "this.multipleDiagFalse=" + this.multipleDiagFalse + ", "
		 		+ "this.multipleDiagNull=" + this.multipleDiagNull + ", "
		 		+ "this.multipleDiagBlank=" + this.multipleDiagBlank + ", "
		 		+ "this.recordTime=" + this.recordTime + ", "
		 		+ "this.isEs=" + this.isEs + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}