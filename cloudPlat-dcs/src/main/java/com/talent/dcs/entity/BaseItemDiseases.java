package com.talent.dcs.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：疾病数据项表类
 */
public class BaseItemDiseases implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //id
    private String id;

    //数据项id
    private String itemId;

    //疾病id
    private String diseaseId;


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
     * 获得数据项id
     * @return String
     */
    public String getItemId(){
        return this.itemId;
    }

    /**
     * 设置数据项id
     * @param itemId  数据项id
     */
    public void setItemId(String itemId){
        this.itemId = itemId;
    }
    /**
     * 获得疾病id
     * @return String
     */
    public String getDiseaseId(){
        return this.diseaseId;
    }

    /**
     * 设置疾病id
     * @param diseaseId  疾病id
     */
    public void setDiseaseId(String diseaseId){
        this.diseaseId = diseaseId;
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
		return "BaseItemDiseases ["
		 		+ "this.id=" + this.id + ", "
		 		+ "this.itemId=" + this.itemId + ", "
		 		+ "this.diseaseId=" + this.diseaseId + ", "
		+ "]";   
	}
}