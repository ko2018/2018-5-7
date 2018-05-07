package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-08 <br/>
 * 描述：机构级别类
 */
public class BaseInstitutionLevel implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //机构级别id
    private String institutionlevelId;

    //机构级别名称
    private String institutionlevelName;

    //
    private Integer sequence;

    //级别
    private Integer level;

    // 父节点
    private String parentId;

    //
    private String deleteStatus;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得机构级别id
     * @return String
     */
    public String getInstitutionlevelId(){
        return this.institutionlevelId;
    }

    /**
     * 设置机构级别id
     * @param institutionlevelId  机构级别id
     */
    public void setInstitutionlevelId(String institutionlevelId){
        this.institutionlevelId = institutionlevelId;
    }
    /**
     * 获得机构级别名称
     * @return String
     */
    public String getInstitutionlevelName(){
        return this.institutionlevelName;
    }

    /**
     * 设置机构级别名称
     * @param institutionlevelName  机构级别名称
     */
    public void setInstitutionlevelName(String institutionlevelName){
        this.institutionlevelName = institutionlevelName;
    }
    /**
     * 获得
     * @return Integer
     */
    public Integer getSequence(){
        return this.sequence;
    }

    /**
     * 设置
     * @param sequence  
     */
    public void setSequence(Integer sequence){
        this.sequence = sequence;
    }
    /**
     * 获得级别
     * @return Integer
     */
    public Integer getLevel(){
        return this.level;
    }

    /**
     * 设置级别
     * @param level  级别
     */
    public void setLevel(Integer level){
        this.level = level;
    }
    /**
     * 获得 父节点
     * @return String
     */
    public String getParentId(){
        return this.parentId;
    }

    /**
     * 设置 父节点
     * @param parentId   父节点
     */
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    /**
     * 获得
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置
     * @param deleteStatus  
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
		this.institutionlevelId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.institutionlevelId;
	}
	
	
	@Override
	public String toString() {
		return "BaseInstitutionLevel ["
		 		+ "this.institutionlevelId=" + this.institutionlevelId + ", "
		 		+ "this.institutionlevelName=" + this.institutionlevelName + ", "
		 		+ "this.sequence=" + this.sequence + ", "
		 		+ "this.level=" + this.level + ", "
		 		+ "this.parentId=" + this.parentId + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}