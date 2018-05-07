package com.talent.front.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.SysUser;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：管理员Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "password", "passwordSalt" })
// @JsonFilter("userFilter")
public class SysUserDto extends SysUser {
	private static final long serialVersionUID = 1L;

	// 机构名称
	private BaseInstitutionDto baseInstitutionDto;

	// 用户角色
	private List<SysRoleDto> roles;

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<SysRoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRoleDto> roles) {
		this.roles = roles;
	}

	public BaseInstitutionDto getBaseInstitutionDto() {
		return baseInstitutionDto;
	}

	public void setBaseInstitutionDto(BaseInstitutionDto baseInstitutionDto) {
		this.baseInstitutionDto = baseInstitutionDto;
	}

	@Override
	public String toString() {
		return "SysUser [" + "this.userId=" + this.getUserId() + ", " + "this.userName=" + this.getUserName() + ", "
				+ "this.realName=" + this.getRealName() + ", " + "this.nickname=" + this.getNickname() + ", "
				+ "this.email=" + this.getEmail() + ", " + "this.telephone=" + this.getTelephone() + ", "
				+ "this.institutionId=" + this.getInstitutionId() + ", " + "this.mobilePhone=" + this.getMobilePhone()
				+ ", " + "this.password=" + this.getPassword() + ", " + "this.passwordSalt=" + this.getPasswordSalt()
				+ ", " + "this.accessoryId=" + this.getAccessoryId() + ", " + "this.photo=" + this.getPhoto() + ", "
				+ "this.sex=" + this.getSex() + ", " + "this.birthday=" + this.getBirthday() + ", " + "this.qq="
				+ this.getQq() + ", " + "this.idCard=" + this.getIdCard() + ", " + "this.areaId=" + this.getAreaId()
				+ ", " + "this.userStatus=" + this.getUserStatus() + ", " + "this.lastLoginIp=" + this.getLastLoginIp()
				+ ", " + "this.loginCount=" + this.getLoginCount() + ", " + "this.lastLoginTime="
				+ this.getLastLoginTime() + ", " + "this.deleteStatus=" + this.getDeleteStatus() + ", "
				+ "this.creator=" + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", "
				+ "this.updateTime=" + this.getUpdateTime() + ", " + "]";
	}

}