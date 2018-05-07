package com.talent.dcs.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-10 <br/>
 * 描述：智能诊断结果数据文件类
 */
public class BaseDocResultFile implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //文件ID
    private String resultId;

    //机构ID
    private String institutionId;

    //课题Id(科研管理id)
    private String researchId;

    //文件地址
    private String docPath;

    //是否完成数据持久化（存入mysql）
    private String isPersistence;

    //版本号（乐观锁）(备用)
    private Integer versionId;

    //文件MD5值(备用)
    private String docMd5;

    //创建者
    private String creator;

    //删除状态
    private String deleteStatus;

    //创建时间
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    private Date updateTime;


    /**
     * 获得文件ID
     * @return String
     */
    public String getResultId(){
        return this.resultId;
    }

    /**
     * 设置文件ID
     * @param resultId  文件ID
     */
    public void setResultId(String resultId){
        this.resultId = resultId;
    }
    /**
     * 获得机构ID
     * @return String
     */
    public String getInstitutionId(){
        return this.institutionId;
    }

    /**
     * 设置机构ID
     * @param institutionId  机构ID
     */
    public void setInstitutionId(String institutionId){
        this.institutionId = institutionId;
    }
    /**
     * 获得课题Id(科研管理id)
     * @return String
     */
    public String getResearchId(){
        return this.researchId;
    }

    /**
     * 设置课题Id(科研管理id)
     * @param researchId  课题Id(科研管理id)
     */
    public void setResearchId(String researchId){
        this.researchId = researchId;
    }
    /**
     * 获得文件地址
     * @return String
     */
    public String getDocPath(){
        return this.docPath;
    }

    /**
     * 设置文件地址
     * @param docPath  文件地址
     */
    public void setDocPath(String docPath){
        this.docPath = docPath;
    }
    /**
     * 获得是否完成数据持久化（存入mysql）
     * @return String
     */
    public String getIsPersistence(){
        return this.isPersistence;
    }

    /**
     * 设置是否完成数据持久化（存入mysql）
     * @param isPersistence  是否完成数据持久化（存入mysql）
     */
    public void setIsPersistence(String isPersistence){
        this.isPersistence = isPersistence;
    }
    /**
     * 获得版本号（乐观锁）(备用)
     * @return Integer
     */
    public Integer getVersionId(){
        return this.versionId;
    }

    /**
     * 设置版本号（乐观锁）(备用)
     * @param versionId  版本号（乐观锁）(备用)
     */
    public void setVersionId(Integer versionId){
        this.versionId = versionId;
    }
    /**
     * 获得文件MD5值(备用)
     * @return String
     */
    public String getDocMd5(){
        return this.docMd5;
    }

    /**
     * 设置文件MD5值(备用)
     * @param docMd5  文件MD5值(备用)
     */
    public void setDocMd5(String docMd5){
        this.docMd5 = docMd5;
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
     * 获得删除状态
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置删除状态
     * @param deleteStatus  删除状态
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
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
		this.resultId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.resultId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDocResultFile ["
		 		+ "this.resultId=" + this.resultId + ", "
		 		+ "this.institutionId=" + this.institutionId + ", "
		 		+ "this.researchId=" + this.researchId + ", "
		 		+ "this.docPath=" + this.docPath + ", "
		 		+ "this.isPersistence=" + this.isPersistence + ", "
		 		+ "this.versionId=" + this.versionId + ", "
		 		+ "this.docMd5=" + this.docMd5 + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}