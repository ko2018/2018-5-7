package com.talent.mds.entity;

import java.util.Date;
import com.talent.base.model.BaseEntity;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：标准数据表类
 */
public class BaseDataStd implements BaseEntity {

	private static final long serialVersionUID = 1L;

	// 原始数据ID
	private String datastdId;

	// 身份编号
	private String userCode;

	// 机构编码
	private String institutionId;

	// 数据编号
	private String checkCode;

	// 数据类型
	private String docId;

	// 数据对象
	private String dataObject;

	//
	private String isTransfer;

	//
	private String isEs;

	// 创建者
	private String creator;

	// 删除状态
	private String deleteStatus;

	// 创建时间
	private Date addTime;

	// 更新者
	private String updateUser;

	// 更新时间
	private Date updateTime;

	/**
	 * 获得原始数据ID
	 * 
	 * @return String
	 */
	public String getDatastdId() {
		return this.datastdId;
	}

	/**
	 * 设置原始数据ID
	 * 
	 * @param datastdId
	 *            原始数据ID
	 */
	public void setDatastdId(String datastdId) {
		this.datastdId = datastdId;
	}

	/**
	 * 获得身份编号
	 * 
	 * @return String
	 */
	public String getUserCode() {
		return this.userCode;
	}

	/**
	 * 设置身份编号
	 * 
	 * @param userCode
	 *            身份编号
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 获得机构编码
	 * 
	 * @return String
	 */
	public String getInstitutionId() {
		return this.institutionId;
	}

	/**
	 * 设置机构编码
	 * 
	 * @param institutionId
	 *            机构编码
	 */
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	/**
	 * 获得数据编号
	 * 
	 * @return String
	 */
	public String getCheckCode() {
		return this.checkCode;
	}

	/**
	 * 设置数据编号
	 * 
	 * @param checkCode
	 *            数据编号
	 */
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	/**
	 * 获得数据类型
	 * 
	 * @return String
	 */
	public String getDocId() {
		return this.docId;
	}

	/**
	 * 设置数据类型
	 * 
	 * @param docId
	 *            数据类型
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

	/**
	 * 获得数据对象
	 * 
	 * @return String
	 */
	public String getDataObject() {
		return this.dataObject;
	}

	/**
	 * 设置数据对象
	 * 
	 * @param dataObject
	 *            数据对象
	 */
	public void setDataObject(String dataObject) {
		this.dataObject = dataObject;
	}

	/**
	 * 获得
	 * 
	 * @return String
	 */
	public String getIsTransfer() {
		return this.isTransfer;
	}

	/**
	 * 设置
	 * 
	 * @param isTransfer
	 */
	public void setIsTransfer(String isTransfer) {
		this.isTransfer = isTransfer;
	}

	/**
	 * 获得
	 * 
	 * @return String
	 */
	public String getIsEs() {
		return this.isEs;
	}

	/**
	 * 设置
	 * 
	 * @param isEs
	 */
	public void setIsEs(String isEs) {
		this.isEs = isEs;
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
	 * 获得更新者
	 * 
	 * @return String
	 */
	public String getUpdateUser() {
		return this.updateUser;
	}

	/**
	 * 设置更新者
	 * 
	 * @param updateUser
	 *            更新者
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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
		this.datastdId = id;
	}

	/**
	 * 得到公共ID
	 */
	@Override
	public String get_Id() {
		return this.datastdId;
	}

	@Override
	public String toString() {
		return "BaseDataStd [" + "this.datastdId=" + this.datastdId + ", " + "this.userCode=" + this.userCode + ", "
				+ "this.institutionId=" + this.institutionId + ", " + "this.checkCode=" + this.checkCode + ", "
				+ "this.docId=" + this.docId + ", " + "this.dataObject=" + this.dataObject + ", " + "this.isTransfer="
				+ this.isTransfer + ", " + "this.isEs=" + this.isEs + ", " + "this.creator=" + this.creator + ", "
				+ "this.deleteStatus=" + this.deleteStatus + ", " + "this.addTime=" + this.addTime + ", "
				+ "this.updateUser=" + this.updateUser + ", " + "this.updateTime=" + this.updateTime + ", " + "]";
	}
}