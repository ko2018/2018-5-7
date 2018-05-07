package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息附件表类
 */
public class BaseFile implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String fileId;

    //文件名称
    private String fileName;

    //文件路径
    private String filePath;

    //文件大小
    private Float fileSize;

    //文件宽
    private Integer fileWidth;

    //文件高
    private Integer fileHeight;

    //文件后缀
    private String fileExt;

    //文件类型
    private String fileType;

    //文件信息
    private String fileInfo;

    //是否删除
    private String deleteStatus;

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
     * 获得文件名称
     * @return String
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * 设置文件名称
     * @param fileName  文件名称
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
     * 获得文件大小
     * @return Float
     */
    public Float getFileSize(){
        return this.fileSize;
    }

    /**
     * 设置文件大小
     * @param fileSize  文件大小
     */
    public void setFileSize(Float fileSize){
        this.fileSize = fileSize;
    }
    /**
     * 获得文件宽
     * @return Integer
     */
    public Integer getFileWidth(){
        return this.fileWidth;
    }

    /**
     * 设置文件宽
     * @param fileWidth  文件宽
     */
    public void setFileWidth(Integer fileWidth){
        this.fileWidth = fileWidth;
    }
    /**
     * 获得文件高
     * @return Integer
     */
    public Integer getFileHeight(){
        return this.fileHeight;
    }

    /**
     * 设置文件高
     * @param fileHeight  文件高
     */
    public void setFileHeight(Integer fileHeight){
        this.fileHeight = fileHeight;
    }
    /**
     * 获得文件后缀
     * @return String
     */
    public String getFileExt(){
        return this.fileExt;
    }

    /**
     * 设置文件后缀
     * @param fileExt  文件后缀
     */
    public void setFileExt(String fileExt){
        this.fileExt = fileExt;
    }
    /**
     * 获得文件类型
     * @return String
     */
    public String getFileType(){
        return this.fileType;
    }

    /**
     * 设置文件类型
     * @param fileType  文件类型
     */
    public void setFileType(String fileType){
        this.fileType = fileType;
    }
    /**
     * 获得文件信息
     * @return String
     */
    public String getFileInfo(){
        return this.fileInfo;
    }

    /**
     * 设置文件信息
     * @param fileInfo  文件信息
     */
    public void setFileInfo(String fileInfo){
        this.fileInfo = fileInfo;
    }
    /**
     * 获得是否删除
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置是否删除
     * @param deleteStatus  是否删除
     */
    public void setDeleteStatus(String deleteStatus){
        this.deleteStatus = deleteStatus;
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
		return "BaseFile ["
		 		+ "this.fileId=" + this.fileId + ", "
		 		+ "this.fileName=" + this.fileName + ", "
		 		+ "this.filePath=" + this.filePath + ", "
		 		+ "this.fileSize=" + this.fileSize + ", "
		 		+ "this.fileWidth=" + this.fileWidth + ", "
		 		+ "this.fileHeight=" + this.fileHeight + ", "
		 		+ "this.fileExt=" + this.fileExt + ", "
		 		+ "this.fileType=" + this.fileType + ", "
		 		+ "this.fileInfo=" + this.fileInfo + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}