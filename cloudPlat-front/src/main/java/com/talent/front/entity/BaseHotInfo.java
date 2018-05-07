package com.talent.front.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息表类
 */
public class BaseHotInfo implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String hotInfoId;

    //标题
    private String infoTitle;

    //内容
    private String infoContent;

    //来源
    private String infoOrigin;

    //来源Url
    private String infoOriginUrl;

    //附件
    private String infoAttach;

    //类别id
    private String infoTypeId;

    //类别名称
    private String infoTypeName;

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
    public String getHotInfoId(){
        return this.hotInfoId;
    }

    /**
     * 设置id
     * @param hotInfoId  id
     */
    public void setHotInfoId(String hotInfoId){
        this.hotInfoId = hotInfoId;
    }
    /**
     * 获得标题
     * @return String
     */
    public String getInfoTitle(){
        return this.infoTitle;
    }

    /**
     * 设置标题
     * @param infoTitle  标题
     */
    public void setInfoTitle(String infoTitle){
        this.infoTitle = infoTitle;
    }
    /**
     * 获得内容
     * @return String
     */
    public String getInfoContent(){
        return this.infoContent;
    }

    /**
     * 设置内容
     * @param infoContent  内容
     */
    public void setInfoContent(String infoContent){
        this.infoContent = infoContent;
    }
    /**
     * 获得来源
     * @return String
     */
    public String getInfoOrigin(){
        return this.infoOrigin;
    }

    /**
     * 设置来源
     * @param infoOrigin  来源
     */
    public void setInfoOrigin(String infoOrigin){
        this.infoOrigin = infoOrigin;
    }
    /**
     * 获得来源Url
     * @return String
     */
    public String getInfoOriginUrl(){
        return this.infoOriginUrl;
    }

    /**
     * 设置来源Url
     * @param infoOriginUrl  来源Url
     */
    public void setInfoOriginUrl(String infoOriginUrl){
        this.infoOriginUrl = infoOriginUrl;
    }
    /**
     * 获得附件
     * @return String
     */
    public String getInfoAttach(){
        return this.infoAttach;
    }

    /**
     * 设置附件
     * @param infoAttach  附件
     */
    public void setInfoAttach(String infoAttach){
        this.infoAttach = infoAttach;
    }
    /**
     * 获得类别id
     * @return String
     */
    public String getInfoTypeId(){
        return this.infoTypeId;
    }

    /**
     * 设置类别id
     * @param infoTypeId  类别id
     */
    public void setInfoTypeId(String infoTypeId){
        this.infoTypeId = infoTypeId;
    }
    /**
     * 获得类别名称
     * @return String
     */
    public String getInfoTypeName(){
        return this.infoTypeName;
    }

    /**
     * 设置类别名称
     * @param infoTypeName  类别名称
     */
    public void setInfoTypeName(String infoTypeName){
        this.infoTypeName = infoTypeName;
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
		this.hotInfoId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.hotInfoId;
	}
	
	
	@Override
	public String toString() {
		return "BaseHotInfo ["
		 		+ "this.hotInfoId=" + this.hotInfoId + ", "
		 		+ "this.infoTitle=" + this.infoTitle + ", "
		 		+ "this.infoContent=" + this.infoContent + ", "
		 		+ "this.infoOrigin=" + this.infoOrigin + ", "
		 		+ "this.infoOriginUrl=" + this.infoOriginUrl + ", "
		 		+ "this.infoAttach=" + this.infoAttach + ", "
		 		+ "this.infoTypeId=" + this.infoTypeId + ", "
		 		+ "this.infoTypeName=" + this.infoTypeName + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}