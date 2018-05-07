package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.SysUserRoleDto;
import com.talent.front.entity.SysUserRole;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-09 <br/>
 * 描述：用户与角色关系表类（Dao层）
 */
 @Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRole> {

	SysUserRoleDto selectDtoByPrimaryKey(String userRoleId);

	List<SysUserRoleDto> pageListDto(PageObject pageObject);
	
	void deleteByUserId(String userId);

}