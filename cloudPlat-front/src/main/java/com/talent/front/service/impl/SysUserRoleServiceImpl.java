package com.talent.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.front.util.redis.annotation.CacheSpeObject;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.SysUserRoleDao;
import com.talent.front.dto.SysUserRoleDto;
import com.talent.front.entity.SysUserRole;
import com.talent.front.service.SysUserRoleService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-09 <br/>
 * 描述：用户与角色关系表服务实现类
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {
	private static final Logger logger = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public BaseDao<SysUserRole> getBaseDao() {
		return this.sysUserRoleDao;
	}
	
	@CacheSpeObject(value = "sysUserRole", key = "#sysUserRole.userRoleId")
	@Override
	public SysUserRoleDto updateObjectByPrimaryKeySelective(SysUserRole sysUserRole) {
		this.sysUserRoleDao.updateByPrimaryKeySelective(sysUserRole);
		return this.sysUserRoleDao.selectDtoByPrimaryKey(sysUserRole.getUserRoleId());
	}
	
	@Override
	public PageResult<SysUserRoleDto> pageListDto(PageObject pageObject) {
		long totalCount = this.sysUserRoleDao.pageCount(pageObject);
		PageResult<SysUserRoleDto> pageResult = new PageResult<SysUserRoleDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<SysUserRoleDto> sysUserRoleDtoList = ((SysUserRoleServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(sysUserRoleDtoList);
		}
		return pageResult;
	}
	
	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<SysUserRoleDto> pageListCache(PageObject pageObject) {
		return this.sysUserRoleDao.pageListDto(pageObject);
	}

}