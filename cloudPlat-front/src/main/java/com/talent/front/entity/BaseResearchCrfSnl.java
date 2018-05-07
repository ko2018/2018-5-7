package com.talent.front.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板属性表类
 */
public class BaseResearchCrfSnl implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //模板id
    private String templetId;

    //术语id
    private String snlId;
    
    private String snlName;

    //列序号
    private Integer colSeq;

    public String getSnlName()
    {
        return snlName;
    }

    public void setSnlName(String snlName)
    {
        this.snlName = snlName;
    }

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
     * 获得模板id
     * @return String
     */
    public String getTempletId(){
        return this.templetId;
    }

    /**
     * 设置模板id
     * @param templetId  模板id
     */
    public void setTempletId(String templetId){
        this.templetId = templetId;
    }
    /**
     * 获得术语id
     * @return String
     */
    public String getSnlId(){
        return this.snlId;
    }

    /**
     * 设置术语id
     * @param snlId  术语id
     */
    public void setSnlId(String snlId){
        this.snlId = snlId;
    }
    /**
     * 获得列序号
     * @return Integer
     */
    public Integer getColSeq(){
        return this.colSeq;
    }

    /**
     * 设置列序号
     * @param colSeq  列序号
     */
    public void setColSeq(Integer colSeq){
        this.colSeq = colSeq;
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
		return "BaseResearchCrfSnl ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.templetId=" + this.templetId + ", "
		 		+ "this.snlId=" + this.snlId + ", "
		 		+ "this.colSeq=" + this.colSeq + ", "
		+ "]";   
	}
}