package com.talent.dcs.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：机构标准术语对应表类
 */
public class BaseInstitutionSnl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	// 机构标准术语ID
	private String institutionSnlId;

	// 术语集id(base_snl->dict_id)
	private Integer dictId;

	// 对应文档列名
	private String docCname;

	// 对用文档列号
	private Integer docCvalue;

	// 机构标准术语版本Id
	private String institutionSnlVersionId;

	// 标准snlCode（备用）
	private String snlCode;

	// 标准snlName（备用）
	private String snlName;

	//
	private String snlNameUs;

	// 删除状态
	private String deleteStatus;

	// 创建者
	private String creator;

	// 创建时间
	private Date addTime;

	// 更新时间
	private Date updateTime;

	/**
	 * 获得机构标准术语ID
	 * 
	 * @return String
	 */
	public String getInstitutionSnlId() {
		return this.institutionSnlId;
	}

	/**
	 * 设置机构标准术语ID
	 * 
	 * @param institutionSnlId
	 *            机构标准术语ID
	 */
	public void setInstitutionSnlId(String institutionSnlId) {
		this.institutionSnlId = institutionSnlId;
	}

	/**
	 * 获得术语集id(base_snl->dict_id)
	 * 
	 * @return Integer
	 */
	public Integer getDictId() {
		return this.dictId;
	}

	/**
	 * 设置术语集id(base_snl->dict_id)
	 * 
	 * @param dictId
	 *            术语集id(base_snl->dict_id)
	 */
	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	/**
	 * 获得对应文档列名
	 * 
	 * @return String
	 */
	public String getDocCname() {
		return this.docCname;
	}

	/**
	 * 设置对应文档列名
	 * 
	 * @param docCname
	 *            对应文档列名
	 */
	public void setDocCname(String docCname) {
		this.docCname = docCname;
	}

	/**
	 * 获得对用文档列号
	 * 
	 * @return Integer
	 */
	public Integer getDocCvalue() {
		return this.docCvalue;
	}

	/**
	 * 设置对用文档列号
	 * 
	 * @param docCvalue
	 *            对用文档列号
	 */
	public void setDocCvalue(Integer docCvalue) {
		this.docCvalue = docCvalue;
	}

	/**
	 * 获得机构标准术语版本Id
	 * 
	 * @return String
	 */
	public String getInstitutionSnlVersionId() {
		return this.institutionSnlVersionId;
	}

	/**
	 * 设置机构标准术语版本Id
	 * 
	 * @param institutionSnlVersionId
	 *            机构标准术语版本Id
	 */
	public void setInstitutionSnlVersionId(String institutionSnlVersionId) {
		this.institutionSnlVersionId = institutionSnlVersionId;
	}

	/**
	 * 获得标准snlCode（备用）
	 * 
	 * @return String
	 */
	public String getSnlCode() {
		return this.snlCode;
	}

	/**
	 * 设置标准snlCode（备用）
	 * 
	 * @param snlCode
	 *            标准snlCode（备用）
	 */
	public void setSnlCode(String snlCode) {
		this.snlCode = snlCode;
	}

	/**
	 * 获得标准snlName（备用）
	 * 
	 * @return String
	 */
	public String getSnlName() {
		return this.snlName;
	}

	/**
	 * 设置标准snlName（备用）
	 * 
	 * @param snlName
	 *            标准snlName（备用）
	 */
	public void setSnlName(String snlName) {
		this.snlName = snlName;
	}

	/**
	 * 获得
	 * 
	 * @return String
	 */
	public String getSnlNameUs() {
		return this.snlNameUs;
	}

	/**
	 * 设置
	 * 
	 * @param snlNameUs
	 */
	public void setSnlNameUs(String snlNameUs) {
		this.snlNameUs = snlNameUs;
	}

	/**
	 * 获得删除状态
	 * 
	 * @return String
	 */
	public String getDeleteStatus() {
		return this.deleteStatus;
	}

	/**
	 * 设置删除状态
	 * 
	 * @param deleteStatus
	 *            删除状态
	 */
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	/**
	 * 获得创建者
	 * 
	 * @return String
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 设置创建者
	 * 
	 * @param creator
	 *            创建者
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 获得创建时间
	 * 
	 * @return Date
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param addTime
	 *            创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获得更新时间
	 * 
	 * @return Date
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 设置公共ID
	 */
	@Override
	public void set_Id(String id) {
		this.institutionSnlId = id;
	}

	/**
	 * 得到公共ID
	 */
	@Override
	public String get_Id() {
		return this.institutionSnlId;
	}

	@Override
	public String toString() {
		return "BaseInstitutionSnl [" + "this.institutionSnlId=" + this.institutionSnlId + ", " + "this.dictId="
				+ this.dictId + ", " + "this.docCname=" + this.docCname + ", " + "this.docCvalue=" + this.docCvalue
				+ ", " + "this.institutionSnlVersionId=" + this.institutionSnlVersionId + ", " + "this.snlCode="
				+ this.snlCode + ", " + "this.snlName=" + this.snlName + ", " + "this.snlNameUs=" + this.snlNameUs
				+ ", " + "this.deleteStatus=" + this.deleteStatus + ", " + "this.creator=" + this.creator + ", "
				+ "this.addTime=" + this.addTime + ", " + "this.updateTime=" + this.updateTime + ", " + "]";
	}
}