package com.talent.dcs.entity;


import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：文档空值合法值记录表类
 */
public class BaseDocLegal implements BaseEntity {

	private static final long serialVersionUID = 1L;

    //文档值id
    private String docvalueId;

    //术语集id(base_snl->dict_id)
    private Integer dictId;

    //文档id
    private String docId;

    //空值数
    private Integer countNull;

    //合法数
    private Integer countLegal;

    //不合法数
    private Integer countNolegal;

    //总数量
    private Integer countAll;

    //删除状态
    private String deleteStatus;

    //创建者
    private String creator;

    //创建时间
    private Date addTime;

    //更新时间
    private Date updateTime;


    /**
     * 获得文档值id
     * @return String
     */
    public String getDocvalueId(){
        return this.docvalueId;
    }

    /**
     * 设置文档值id
     * @param docvalueId  文档值id
     */
    public void setDocvalueId(String docvalueId){
        this.docvalueId = docvalueId;
    }
    /**
     * 获得术语集id(base_snl->dict_id)
     * @return Integer
     */
    public Integer getDictId(){
        return this.dictId;
    }

    /**
     * 设置术语集id(base_snl->dict_id)
     * @param dictId  术语集id(base_snl->dict_id)
     */
    public void setDictId(Integer dictId){
        this.dictId = dictId;
    }
    /**
     * 获得文档id
     * @return String
     */
    public String getDocId(){
        return this.docId;
    }

    /**
     * 设置文档id
     * @param docId  文档id
     */
    public void setDocId(String docId){
        this.docId = docId;
    }
    /**
     * 获得空值数
     * @return Integer
     */
    public Integer getCountNull(){
        return this.countNull;
    }

    /**
     * 设置空值数
     * @param countNull  空值数
     */
    public void setCountNull(Integer countNull){
        this.countNull = countNull;
    }
    /**
     * 获得合法数
     * @return Integer
     */
    public Integer getCountLegal(){
        return this.countLegal;
    }

    /**
     * 设置合法数
     * @param countLegal  合法数
     */
    public void setCountLegal(Integer countLegal){
        this.countLegal = countLegal;
    }
    /**
     * 获得不合法数
     * @return Integer
     */
    public Integer getCountNolegal(){
        return this.countNolegal;
    }

    /**
     * 设置不合法数
     * @param countNolegal  不合法数
     */
    public void setCountNolegal(Integer countNolegal){
        this.countNolegal = countNolegal;
    }
    /**
     * 获得总数量
     * @return Integer
     */
    public Integer getCountAll(){
        return this.countAll;
    }

    /**
     * 设置总数量
     * @param countAll  总数量
     */
    public void setCountAll(Integer countAll){
        this.countAll = countAll;
    }
    /**
     * 获得删除状态
     * @return String
     */
    public String getDeleteStatus(){
        return this.deleteStatus;
    }

    /**
     * 设置删除状态
     * @param deleteStatus  删除状态
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
		this.docvalueId = id;
	}

	/**
     * 得到公共ID
     */
	@Override
	public String get_Id() {
		return this.docvalueId;
	}
	
	
	@Override
	public String toString() {
		return "BaseDocLegal ["
		 		+ "this.docvalueId=" + this.docvalueId + ", "
		 		+ "this.dictId=" + this.dictId + ", "
		 		+ "this.docId=" + this.docId + ", "
		 		+ "this.countNull=" + this.countNull + ", "
		 		+ "this.countLegal=" + this.countLegal + ", "
		 		+ "this.countNolegal=" + this.countNolegal + ", "
		 		+ "this.countAll=" + this.countAll + ", "
		 		+ "this.deleteStatus=" + this.deleteStatus + ", "
		 		+ "this.creator=" + this.creator + ", "
		 		+ "this.addTime=" + this.addTime + ", "
		 		+ "this.updateTime=" + this.updateTime + ", "
		+ "]";   
	}
}