package com.talent.front.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.constant.YesOrNo;
import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.SysRoleDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.SysUser;
import com.talent.front.service.SysRoleService;
import com.talent.front.service.SysUserService;
import com.talent.front.util.BCryptUtil;
import com.talent.front.util.MD5Util;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：管理员控制类
 */
@RestController
@RequestMapping("user")
public class SysUserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(value = "list")
	public Map<String, Object> list() {
		PageObject pageObject = getPageObject();
		return setDataValue(sysUserService.pageListDto(pageObject));
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Map<String, Object> add(SysUser sysUser, String roleIds) throws Exception {
		if (StringUtils.isEmpty(sysUser.getUserName()))
			throw new GlobalException(ErrorCode.USER_NAME_NULL);
		sysUser.setPassword(BCryptUtil.encode(sysUser.getPassword()));
		SysUser dbUser = sysUserService.getUserByName(sysUser.getUserName());
		if (dbUser != null) {
			throw new GlobalException(ErrorCode.USER_EXIST);
		} else {
			UserCurrent.getInstance().setUserId(sysUser);
			sysUser.setUserId(UUIDUtil.getUUID(32));
			Date date = new Date();
			sysUser.setAddTime(date);
			sysUser.setUpdateTime(date);
			sysUserService.addUser(sysUser, roleIds);
		}
		return setDataValue(null);
	}

	@RequestMapping(value = "detail", method = RequestMethod.POST)
	public Map<String, Object> detail(SysUser sysUser) throws Exception {
		if (StringUtils.isEmpty(sysUser.getUserId()))
			throw new GlobalException(ErrorCode.USER_ID_UNEXIST);
		SysUserDto sysUserTmp = sysUserService.selectDtoByPrimaryKey(sysUser.getUserId());
		List<SysRoleDto> roles = sysRoleService.findRolesByUserId(sysUser.getUserId());
		if (sysUserTmp != null) {
			sysUserTmp.setRoles(roles);
			return setDataValue(sysUserTmp);
		} else {
			throw new GlobalException(ErrorCode.USER_ID_UNEXIST);
		}
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Map<String, Object> update(SysUser sysUser, String roleIds) throws Exception {
		if (StringUtils.isEmpty(sysUser.getUserId()))
			throw new GlobalException(ErrorCode.USER_ID_UNEXIST);
		sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
		SysUser sysUserTmp = sysUserService.selectByPrimaryKey(sysUser.getUserId());
		if (sysUserTmp != null) {
			UserCurrent.getInstance().setUserId(sysUser);
			sysUserService.update(sysUser, roleIds);
			return setDataValue(null);
		} else {
			throw new GlobalException(ErrorCode.USER_ID_UNEXIST);
		}
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Map<String, Object> delete(SysUser sysUser) throws Exception {
		if (StringUtils.isEmpty(sysUser.getUserId()))
			throw new GlobalException(ErrorCode.USER_ID_UNEXIST);
		SysUser sysUserTmp = sysUserService.selectByPrimaryKey(sysUser.getUserId());
		if (sysUserTmp != null) {
			Set<String> set = sysRoleService.findRoleTypesByUserId(sysUser.getUserId());
			for (String roleType : set) {
				if (roleType.equals("superAdmin")) {
					throw new GlobalException(ErrorCode.USER_SUPERADMIN_NETDELETE);
				}
			}
			UserCurrent.getInstance().setUserId(sysUser);
			sysUser.setDeleteStatus(YesOrNo.Y.name());
			sysUser.setUpdateTime(new Date());
			sysUserService.updateDtoByPrimaryKeySelective(sysUser);
			return setDataValue(null);
		} else {
			throw new GlobalException(ErrorCode.USER_ID_UNEXIST);
		}
	}

}