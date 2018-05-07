package com.talent.front.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.SysRoleDto;
import com.talent.front.entity.SysRole;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：角色类（Dao层）
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRole> {

	SysRoleDto selectDtoByPrimaryKey(String roleId);

	List<SysRoleDto> pageListDto(PageObject pageObject);

	Set<String> findRoleTypesByUserId(@Param("userId") String userId);
	
	List<SysRoleDto> findRolesByUserId(@Param("userId") String userId);

}