package com.talent.front.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.SysResourceDto;
import com.talent.front.entity.SysResource;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-01 <br/>
 * 描述：系统资源类（Dao层）
 */
@Mapper
public interface SysResourceDao extends BaseDao<SysResource> {

	SysResourceDto selectDtoByPrimaryKey(String resourceId);

	List<SysResourceDto> pageListDto(PageObject pageObject);

	List<SysResourceDto> findRoleResource(String roleId);

	List<SysResourceDto> findRoleResourceByUserIdAndParentId(Map<String, Object> map);

	List<SysResourceDto> findResourceByUserid(String userid);

}