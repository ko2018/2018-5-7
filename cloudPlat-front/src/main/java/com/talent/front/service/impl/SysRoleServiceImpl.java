package com.talent.front.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.SysRoleDao;
import com.talent.front.dto.SysRoleDto;
import com.talent.front.entity.SysRole;
import com.talent.front.service.SysRoleService;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.front.util.redis.annotation.CacheSpeObject;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：角色服务实现类
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
	private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public BaseDao<SysRole> getBaseDao() {
		return this.sysRoleDao;
	}

	@CacheSpeObject(value = "sysRole", key = "#sysRole.roleId")
	@Override
	public SysRoleDto updateObjectByPrimaryKeySelective(SysRole sysRole) {
		this.sysRoleDao.updateByPrimaryKeySelective(sysRole);
		return this.sysRoleDao.selectDtoByPrimaryKey(sysRole.getRoleId());
	}

	@Override
	public PageResult<SysRoleDto> pageListDto(PageObject pageObject) {
		long totalCount = this.sysRoleDao.pageCount(pageObject);
		PageResult<SysRoleDto> pageResult = new PageResult<SysRoleDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<SysRoleDto> sysRoleDtoList = ((SysRoleServiceImpl) AopContext.currentProxy())
					.pageListCache(pageObject);
			pageResult.setQueryResult(sysRoleDtoList);
		}
		return pageResult;
	}

	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<SysRoleDto> pageListCache(PageObject pageObject) {
		return this.sysRoleDao.pageListDto(pageObject);
	}

	@Override
	public Set<String> findRoleTypesByUserId(String userId) {
		Set<String> set = sysRoleDao.findRoleTypesByUserId(userId);
		return set;
	}

	@Override
	public List<SysRoleDto> findRolesByUserId(String userId) {
		List<SysRoleDto> list = sysRoleDao.findRolesByUserId(userId);
		return list;
	}

}