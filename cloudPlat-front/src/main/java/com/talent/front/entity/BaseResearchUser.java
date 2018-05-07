package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题用户表类
 */
public class BaseResearchUser implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //课题id
    private String researchId;

    //用户id
    private String userId;

    //是否主要
    private Integer isMajor;


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
     * 获得是否主要
     * @return Integer
     */
    public Integer getIsMajor(){
        return this.isMajor;
    }

    /**
     * 设置是否主要
     * @param isMajor  是否主要
     */
    public void setIsMajor(Integer isMajor){
        this.isMajor = isMajor;
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
		return "BaseResearchUser ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.researchId=" + this.researchId + ", "
		 		+ "this.userId=" + this.userId + ", "
		 		+ "this.isMajor=" + this.isMajor + ", "
		+ "]";   
	}
}