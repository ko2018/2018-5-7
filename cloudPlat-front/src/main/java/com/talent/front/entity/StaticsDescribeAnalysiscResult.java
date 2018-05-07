package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析结果类
 */
public class StaticsDescribeAnalysiscResult implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //uuid
    private String uuid;

    //指标
    private String dict;

    //指标结果
    private String dictVal;

    //add_time
    private Date addTime;

    //update_time
    private Date updateTime;


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
     * 获得uuid
     * @return String
     */
    public String getUuid(){
        return this.uuid;
    }

    /**
     * 设置uuid
     * @param uuid  uuid
     */
    public void setUuid(String uuid){
        this.uuid = uuid;
    }
    /**
     * 获得指标
     * @return String
     */
    public String getDict(){
        return this.dict;
    }

    /**
     * 设置指标
     * @param dict  指标
     */
    public void setDict(String dict){
        this.dict = dict;
    }
    /**
     * 获得指标结果
     * @return String
     */
    public String getDictVal(){
        return this.dictVal;
    }

    /**
     * 设置指标结果
     * @param dictVal  指标结果
     */
    public void setDictVal(String dictVal){
        this.dictVal = dictVal;
    }
    /**
     * 获得add_time
     * @return Date
     */
    public Date getAddTime(){
        return this.addTime;
    }

    /**
     * 设置add_time
     * @param addTime  add_time
     */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }
    /**
     * 获得update_time
     * @return Date
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置update_time
     * @param updateTime  update_time
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
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
		return "StaticsDescribeAnalysiscResult ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.uuid=" + this.uuid + ", "
		 		+ "this.dict=" + this.dict + ", "
		 		+ "this.dictVal=" + this.dictVal + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}