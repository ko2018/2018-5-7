package com.talent.dcs.dto;

import com.talent.dcs.entity.SysUser;

/**
 * 作者：fwp <br/>
 * 生成日期：2017-03-16 <br/>
 * 描述：用户Dto类
 */
public class SysUserDto extends SysUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@Override
	public String toString() {
		return "SysUser [" + "this.userId=" + this.getUserId() + ", " + "this.deleteStatus=" + this.getDeleteStatus()
				+ ", " + "this.userName=" + this.getUserName() + ", " + "this.realName=" + this.getRealName() + ", "
				+ "this.nickname=" + this.getNickname() + ", " + "this.email=" + this.getEmail() + ", "
				+ "this.telephone=" + this.getTelephone() + ", " + "this.mobilePhone=" + this.getMobilePhone() + ", "
				+ "this.password=" + this.getPassword() + ", " + "this.passwordSalt=" + this.getPasswordSalt() + ", "
				+ "this.accessoryId=" + this.getAccessoryId() + ", " + "this.photo=" + this.getPhoto() + ", "
				+ "this.sex=" + this.getSex() + ", " + "this.birthday=" + this.getBirthday() + ", " + "this.qq="
				+ this.getQq() + ", " + "this.msn=" + this.getMsn() + ", " + "this.idCard=" + this.getIdCard() + ", "
				+ "this.areaId=" + this.getAreaId() + ", " + "this.storeId=" + this.getStoreId() + ", " + "this.gold="
				+ this.getGold() + ", " + "this.qqOpenid=" + this.getQqOpenid() + ", " + "this.sinaOpenid="
				+ this.getSinaOpenid() + ", " + "this.wxOpenid=" + this.getWxOpenid() + ", " + "this.wxUnionid="
				+ this.getWxUnionid() + ", " + "this.userStatus=" + this.getUserStatus() + ", " + "this.lastLoginIp="
				+ this.getLastLoginIp() + ", " + "this.loginCount=" + this.getLoginCount() + ", "
				+ "this.lastLoginTime=" + this.getLastLoginTime() + ", " + "this.creator=" + this.getCreator() + ", "
				+ "this.addTime=" + this.getAddTime() + ", " + "this.updateTime=" + this.getUpdateTime() + ", " + "]";
	}

}