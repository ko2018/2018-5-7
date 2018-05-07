package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档类
 */
public class BaseResearchFile implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String fileId;

    //课题id
    private String researchId;

    //文件标签
    private String fileTag;
    
    //文件名
    private String fileName;

    //文件路径
    private String filePath;

    //文件类别编码
    private String classifyId;

    //文件类别名
    private String classifyName;
    
    //是否删除
    private Integer isDel;
    
    //创建者
    private String creator;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getFileTag()
    {
        return fileTag;
    }

    public void setFileTag(String fileTag)
    {
        this.fileTag = fileTag;
    }

    public String getClassifyName()
    {
        return classifyName;
    }

    public void setClassifyName(String classifyName)
    {
        this.classifyName = classifyName;
    }

    public Integer getIsDel()
    {
        return isDel;
    }

    public void setIsDel(Integer isDel)
    {
        this.isDel = isDel;
    }

    /**
     * 获得id
     * @return String
     */
    public String getFileId(){
        return this.fileId;
    }

    /**
     * 设置id
     * @param fileId  id
     */
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    /**
     * 获得课题id
     * @return String
     */
    public String getResearchId(){
        return this.researchId;
    }

    /**
     * 设置课题id
     * @param researchId  课题id
     */
    public void setResearchId(String researchId){
        this.researchId = researchId;
    }
    /**
     * 获得文件名
     * @return String
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * 设置文件名
     * @param fileName  文件名
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    /**
     * 获得文件路径
     * @return String
     */
    public String getFilePath(){
        return this.filePath;
    }

    /**
     * 设置文件路径
     * @param filePath  文件路径
     */
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    /**
     * 获得文件类别编码
     * @return String
     */
    public String getClassifyId(){
        return this.classifyId;
    }

    /**
     * 设置文件类别编码
     * @param classifyId  文件类别编码
     */
    public void setClassifyId(String classifyId){
        this.classifyId = classifyId;
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
		this.fileId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.fileId;
	}
	
	
	@Override
	public String toString() {
		return "BaseResearchFile ["
		 		+ "this.fileId=" + this.fileId + ", "
		 		+ "this.researchId=" + this.researchId + ", "
		 		+ "this.fileName=" + this.fileName + ", "
		 		+ "this.filePath=" + this.filePath + ", "
		 		+ "this.classifyId=" + this.classifyId + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}