package com.talent.dcs.entity;

import java.util.Date;

import com.talent.base.model.BaseEntity;

/**
 * 作者：fwp <br/>
 * 生成日期：2017-03-21 <br/>
 * 描述：用户类
 */
public class SysUser implements BaseEntity {

	private static final long serialVersionUID = 1L;

	//
	private String userId;

	// N:未删除,Y已删除
	private String deleteStatus;

	// 用户名
	private String userName;

	// 真实姓名
	private String realName;

	// 昵称
	private String nickname;

	// 邮箱
	private String email;

	// 电话
	private String telephone;

	// 手机号码
	private String mobilePhone;

	// 密码
	private String password;

	// 密码盐
	private String passwordSalt;

	// 头像id
	private String accessoryId;

	// 头像
	private String photo;

	// 性别(F:女性，M男性)
	private String sex;

	// 生日
	private Date birthday;

	// qq号
	private String qq;

	// msn账号
	private String msn;

	// 身份证
	private String idCard;

	// 所在的
	private String areaId;

	// 店铺id
	private String storeId;

	// 可用余额
	private Double availableBalance;

	// 冻结余额
	private Double freezeBalance;

	// 积分
	private Integer integral;

	// 金币
	private Integer gold;

	// qq的openId
	private String qqOpenid;

	// 新浪openId
	private String sinaOpenid;

	// 微信openid
	private String wxOpenid;

	// 微信UnionID
	private String wxUnionid;

	// 1:有效，0:禁止登录
	private String userStatus;

	// 最后登陆ip
	private String lastLoginIp;

	// 登陆次数
	private Long loginCount;

	// 最后登陆时间
	private Date lastLoginTime;

	// 创建者
	private String creator;

	// 创建时间
	private Date addTime;

	// 更新时间
	private Date updateTime;

	public SysUser() {
	}

	public SysUser(SysUser user) {
		this.userId = user.get_Id();
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.addTime = user.getAddTime();
		this.passwordSalt = user.getPasswordSalt();
		this.lastLoginTime = user.getLastLoginTime();
	}

	/**
	 * 获得
	 * 
	 * @return String
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 设置
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获得N:未删除,Y已删除
	 * 
	 * @return String
	 */
	public String getDeleteStatus() {
		return this.deleteStatus;
	}

	/**
	 * 设置N:未删除,Y已删除
	 * 
	 * @param deleteStatus
	 *            N:未删除,Y已删除
	 */
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	/**
	 * 获得用户名
	 * 
	 * @return String
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 设置用户名
	 * 
	 * @param userName
	 *            用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获得真实姓名
	 * 
	 * @return String
	 */
	public String getRealName() {
		return this.realName;
	}

	/**
	 * 设置真实姓名
	 * 
	 * @param realName
	 *            真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 获得昵称
	 * 
	 * @return String
	 */
	public String getNickname() {
		return this.nickname;
	}

	/**
	 * 设置昵称
	 * 
	 * @param nickname
	 *            昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获得邮箱
	 * 
	 * @return String
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 设置邮箱
	 * 
	 * @param email
	 *            邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获得电话
	 * 
	 * @return String
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * 设置电话
	 * 
	 * @param telephone
	 *            电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获得手机号码
	 * 
	 * @return String
	 */
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	/**
	 * 设置手机号码
	 * 
	 * @param mobilePhone
	 *            手机号码
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * 获得密码
	 * 
	 * @return String
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置密码
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获得密码盐
	 * 
	 * @return String
	 */
	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	/**
	 * 设置密码盐
	 * 
	 * @param passwordSalt
	 *            密码盐
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	/**
	 * 获得头像id
	 * 
	 * @return String
	 */
	public String getAccessoryId() {
		return this.accessoryId;
	}

	/**
	 * 设置头像id
	 * 
	 * @param accessoryId
	 *            头像id
	 */
	public void setAccessoryId(String accessoryId) {
		this.accessoryId = accessoryId;
	}

	/**
	 * 获得头像
	 * 
	 * @return String
	 */
	public String getPhoto() {
		return this.photo;
	}

	/**
	 * 设置头像
	 * 
	 * @param photo
	 *            头像
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * 获得性别(F:女性，M男性)
	 * 
	 * @return String
	 */
	public String getSex() {
		return this.sex;
	}

	/**
	 * 设置性别(F:女性，M男性)
	 * 
	 * @param sex
	 *            性别(F:女性，M男性)
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获得生日
	 * 
	 * @return Date
	 */
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * 设置生日
	 * 
	 * @param birthday
	 *            生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获得qq号
	 * 
	 * @return String
	 */
	public String getQq() {
		return this.qq;
	}

	/**
	 * 设置qq号
	 * 
	 * @param qq
	 *            qq号
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 获得msn账号
	 * 
	 * @return String
	 */
	public String getMsn() {
		return this.msn;
	}

	/**
	 * 设置msn账号
	 * 
	 * @param msn
	 *            msn账号
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}

	/**
	 * 获得身份证
	 * 
	 * @return String
	 */
	public String getIdCard() {
		return this.idCard;
	}

	/**
	 * 设置身份证
	 * 
	 * @param idCard
	 *            身份证
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * 获得所在的
	 * 
	 * @return String
	 */
	public String getAreaId() {
		return this.areaId;
	}

	/**
	 * 设置所在的
	 * 
	 * @param areaId
	 *            所在的
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * 获得店铺id
	 * 
	 * @return String
	 */
	public String getStoreId() {
		return this.storeId;
	}

	/**
	 * 设置店铺id
	 * 
	 * @param storeId
	 *            店铺id
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * 获得可用余额
	 * 
	 * @return Double
	 */
	public Double getAvailableBalance() {
		return this.availableBalance;
	}

	/**
	 * 设置可用余额
	 * 
	 * @param availableBalance
	 *            可用余额
	 */
	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	/**
	 * 获得冻结余额
	 * 
	 * @return Double
	 */
	public Double getFreezeBalance() {
		return this.freezeBalance;
	}

	/**
	 * 设置冻结余额
	 * 
	 * @param freezeBalance
	 *            冻结余额
	 */
	public void setFreezeBalance(Double freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	/**
	 * 获得积分
	 * 
	 * @return Integer
	 */
	public Integer getIntegral() {
		return this.integral;
	}

	/**
	 * 设置积分
	 * 
	 * @param integral
	 *            积分
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	/**
	 * 获得金币
	 * 
	 * @return Integer
	 */
	public Integer getGold() {
		return this.gold;
	}

	/**
	 * 设置金币
	 * 
	 * @param gold
	 *            金币
	 */
	public void setGold(Integer gold) {
		this.gold = gold;
	}

	/**
	 * 获得qq的openId
	 * 
	 * @return String
	 */
	public String getQqOpenid() {
		return this.qqOpenid;
	}

	/**
	 * 设置qq的openId
	 * 
	 * @param qqOpenid
	 *            qq的openId
	 */
	public void setQqOpenid(String qqOpenid) {
		this.qqOpenid = qqOpenid;
	}

	/**
	 * 获得新浪openId
	 * 
	 * @return String
	 */
	public String getSinaOpenid() {
		return this.sinaOpenid;
	}

	/**
	 * 设置新浪openId
	 * 
	 * @param sinaOpenid
	 *            新浪openId
	 */
	public void setSinaOpenid(String sinaOpenid) {
		this.sinaOpenid = sinaOpenid;
	}

	/**
	 * 获得微信openid
	 * 
	 * @return String
	 */
	public String getWxOpenid() {
		return this.wxOpenid;
	}

	/**
	 * 设置微信openid
	 * 
	 * @param wxOpenid
	 *            微信openid
	 */
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	/**
	 * 获得微信UnionID
	 * 
	 * @return String
	 */
	public String getWxUnionid() {
		return this.wxUnionid;
	}

	/**
	 * 设置微信UnionID
	 * 
	 * @param wxUnionid
	 *            微信UnionID
	 */
	public void setWxUnionid(String wxUnionid) {
		this.wxUnionid = wxUnionid;
	}

	/**
	 * 获得1:有效，0:禁止登录
	 * 
	 * @return String
	 */
	public String getUserStatus() {
		return this.userStatus;
	}

	/**
	 * 设置1:有效，0:禁止登录
	 * 
	 * @param userStatus
	 *            1:有效，0:禁止登录
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * 获得最后登陆ip
	 * 
	 * @return String
	 */
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	/**
	 * 设置最后登陆ip
	 * 
	 * @param lastLoginIp
	 *            最后登陆ip
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * 获得登陆次数
	 * 
	 * @return Long
	 */
	public Long getLoginCount() {
		return this.loginCount;
	}

	/**
	 * 设置登陆次数
	 * 
	 * @param loginCount
	 *            登陆次数
	 */
	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * 获得最后登陆时间
	 * 
	 * @return Date
	 */
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * 设置最后登陆时间
	 * 
	 * @param lastLoginTime
	 *            最后登陆时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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
		this.userId = id;
	}

	/**
	 * 得到公共ID
	 */
	@Override
	public String get_Id() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "SysUser [" + "this.userId=" + this.userId + ", " + "this.deleteStatus=" + this.deleteStatus + ", "
				+ "this.userName=" + this.userName + ", " + "this.realName=" + this.realName + ", " + "this.nickname="
				+ this.nickname + ", " + "this.email=" + this.email + ", " + "this.telephone=" + this.telephone + ", "
				+ "this.mobilePhone=" + this.mobilePhone + ", " + "this.password=" + this.password + ", "
				+ "this.passwordSalt=" + this.passwordSalt + ", " + "this.accessoryId=" + this.accessoryId + ", "
				+ "this.photo=" + this.photo + ", " + "this.sex=" + this.sex + ", " + "this.birthday=" + this.birthday
				+ ", " + "this.qq=" + this.qq + ", " + "this.msn=" + this.msn + ", " + "this.idCard=" + this.idCard
				+ ", " + "this.areaId=" + this.areaId + ", " + "this.storeId=" + this.storeId + ", "
				+ "this.availableBalance=" + this.availableBalance + ", " + "this.freezeBalance=" + this.freezeBalance
				+ ", " + "this.integral=" + this.integral + ", " + "this.gold=" + this.gold + ", " + "this.qqOpenid="
				+ this.qqOpenid + ", " + "this.sinaOpenid=" + this.sinaOpenid + ", " + "this.wxOpenid=" + this.wxOpenid
				+ ", " + "this.wxUnionid=" + this.wxUnionid + ", " + "this.userStatus=" + this.userStatus + ", "
				+ "this.lastLoginIp=" + this.lastLoginIp + ", " + "this.loginCount=" + this.loginCount + ", "
				+ "this.lastLoginTime=" + this.lastLoginTime + ", " + "this.creator=" + this.creator + ", "
				+ "this.addTime=" + this.addTime + ", " + "this.updateTime=" + this.updateTime + ", " + "]";
	}
}