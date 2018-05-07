package com.talent.front.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.SysRoleDto;
import com.talent.front.service.SysRoleService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：管理员控制类
 */
@RestController
@RequestMapping("role")
public class SysRoleController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(value = "list")
	public Map<String, Object> list() {
		// UserDetails userDetails = (UserDetails)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// logger.info(userDetails.getName());
		// logger.info(((SysUserDto) userDetails).getUserId());

		PageObject pageObject = getPageObject();
		pageObject.setPageSize(30);
		PageResult<SysRoleDto> pageResult = sysRoleService.pageListDto(pageObject);
		List<SysRoleDto> list = pageResult.getQueryResult();
		if (list.size() > 0) {
			Iterator<SysRoleDto> it = list.iterator();
			while (it.hasNext()) {
				SysRoleDto sysRoleDto = it.next();
				if (sysRoleDto.getRoleType().equals("superAdmin")) {
					it.remove();
					pageResult.setTotalCount(pageResult.getTotalCount() - 1);
				}
			}
		}
		return setDataValue(pageResult);
	}

}