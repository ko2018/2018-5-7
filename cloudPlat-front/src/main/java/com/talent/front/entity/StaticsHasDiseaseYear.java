package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：统计原始数据每年患病情况类
 */
public class StaticsHasDiseaseYear implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //统计人群id
    private String staticsSid;

    //创建人群的用户
    private String staticsYear;

    //统计名称
    private String staticsRSum;

    //统计纳入时间跨度end
    private String staticsDSum;

    //统计纳入时间跨度end
    private String staticsPSum;

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得统计人群id
     * @return String
     */
    public String getStaticsSid(){
        return this.staticsSid;
    }

    /**
     * 设置统计人群id
     * @param staticsSid  统计人群id
     */
    public void setStaticsSid(String staticsSid){
        this.staticsSid = staticsSid;
    }
    /**
     * 获得创建人群的用户
     * @return String
     */
    public String getStaticsYear(){
        return this.staticsYear;
    }

    /**
     * 设置创建人群的用户
     * @param staticsYear  创建人群的用户
     */
    public void setStaticsYear(String staticsYear){
        this.staticsYear = staticsYear;
    }
    /**
     * 获得统计名称
     * @return String
     */
    public String getStaticsRSum(){
        return this.staticsRSum;
    }

    /**
     * 设置统计名称
     * @param staticsRSum  统计名称
     */
    public void setStaticsRSum(String staticsRSum){
        this.staticsRSum = staticsRSum;
    }
    /**
     * 获得统计纳入时间跨度end
     * @return String
     */
    public String getStaticsDSum(){
        return this.staticsDSum;
    }

    /**
     * 设置统计纳入时间跨度end
     * @param staticsDSum  统计纳入时间跨度end
     */
    public void setStaticsDSum(String staticsDSum){
        this.staticsDSum = staticsDSum;
    }
    /**
     * 获得统计纳入时间跨度end
     * @return String
     */
    public String getStaticsPSum(){
        return this.staticsPSum;
    }

    /**
     * 设置统计纳入时间跨度end
     * @param staticsPSum  统计纳入时间跨度end
     */
    public void setStaticsPSum(String staticsPSum){
        this.staticsPSum = staticsPSum;
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
		this.staticsSid = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.staticsSid;
	}
	
	
	@Override
	public String toString() {
		return "StaticsHasDiseaseYear ["
		 		+ "this.staticsSid=" + this.staticsSid + ", "
		 		+ "this.staticsYear=" + this.staticsYear + ", "
		 		+ "this.staticsRSum=" + this.staticsRSum + ", "
		 		+ "this.staticsDSum=" + this.staticsDSum + ", "
		 		+ "this.staticsPSum=" + this.staticsPSum + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}