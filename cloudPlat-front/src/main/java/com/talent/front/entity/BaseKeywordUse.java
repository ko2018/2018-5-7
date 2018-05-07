package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-01 <br/>
 * 描述：关键字应用表类
 */
public class BaseKeywordUse implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //应用id
    private String keywordUseId;

    //关键字id
    private String keywordId;

    //匹配内容
    private String matchContent;

    //匹配类型(0-不匹配 1-匹配)
    private Integer matchType;

    //是否删除(0-未删除 1-删除)
    private Integer isDel;

    //特殊包含词
    private String specialInWords;

    //特殊不包含词
    private String specialOutWords;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新者
    private String updateUser;

    //更新时间
    private Date updateTime;

    //通配长度
    private Integer wildcardLength;


    /**
     * 获得应用id
     * @return String
     */
    public String getKeywordUseId(){
        return this.keywordUseId;
    }

    /**
     * 设置应用id
     * @param keywordUseId  应用id
     */
    public void setKeywordUseId(String keywordUseId){
        this.keywordUseId = keywordUseId;
    }
    /**
     * 获得关键字id
     * @return String
     */
    public String getKeywordId(){
        return this.keywordId;
    }

    /**
     * 设置关键字id
     * @param keywordId  关键字id
     */
    public void setKeywordId(String keywordId){
        this.keywordId = keywordId;
    }
    /**
     * 获得匹配内容
     * @return String
     */
    public String getMatchContent(){
        return this.matchContent;
    }

    /**
     * 设置匹配内容
     * @param matchContent  匹配内容
     */
    public void setMatchContent(String matchContent){
        this.matchContent = matchContent;
    }
    /**
     * 获得匹配类型(0-不匹配 1-匹配)
     * @return Integer
     */
    public Integer getMatchType(){
        return this.matchType;
    }

    /**
     * 设置匹配类型(0-不匹配 1-匹配)
     * @param matchType  匹配类型(0-不匹配 1-匹配)
     */
    public void setMatchType(Integer matchType){
        this.matchType = matchType;
    }
    /**
     * 获得是否删除(0-未删除 1-删除)
     * @return Integer
     */
    public Integer getIsDel(){
        return this.isDel;
    }

    /**
     * 设置是否删除(0-未删除 1-删除)
     * @param isDel  是否删除(0-未删除 1-删除)
     */
    public void setIsDel(Integer isDel){
        this.isDel = isDel;
    }
    /**
     * 获得特殊包含词
     * @return String
     */
    public String getSpecialInWords(){
        return this.specialInWords;
    }

    /**
     * 设置特殊包含词
     * @param specialInWords  特殊包含词
     */
    public void setSpecialInWords(String specialInWords){
        this.specialInWords = specialInWords;
    }
    /**
     * 获得特殊不包含词
     * @return String
     */
    public String getSpecialOutWords(){
        return this.specialOutWords;
    }

    /**
     * 设置特殊不包含词
     * @param specialOutWords  特殊不包含词
     */
    public void setSpecialOutWords(String specialOutWords){
        this.specialOutWords = specialOutWords;
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
     * 获得通配长度
     * @return Integer
     */
    public Integer getWildcardLength(){
        return this.wildcardLength;
    }

    /**
     * 设置通配长度
     * @param wildcardLength  通配长度
     */
    public void setWildcardLength(Integer wildcardLength){
        this.wildcardLength = wildcardLength;
    }
    
    /**
     * 设置公共ID
     */
    @Override
	public void set_Id(String id) {
		this.keywordUseId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.keywordUseId;
	}
	
	
	@Override
	public String toString() {
		return "BaseKeywordUse ["
		 		+ "this.keywordUseId=" + this.keywordUseId + ", "
		 		+ "this.keywordId=" + this.keywordId + ", "
		 		+ "this.matchContent=" + this.matchContent + ", "
		 		+ "this.matchType=" + this.matchType + ", "
		 		+ "this.isDel=" + this.isDel + ", "
		 		+ "this.specialInWords=" + this.specialInWords + ", "
		 		+ "this.specialOutWords=" + this.specialOutWords + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateUser=" + this.updateUser + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		 		+ "this.wildcardLength=" + this.wildcardLength + ", "
		+ "]";   
	}
}