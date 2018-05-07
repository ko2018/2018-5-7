package com.talent.mds.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.talent.base.database.BaseDao;
import com.talent.mds.entity.SysUser;

/**
 * 作者：fwp <br/>
 * 生成日期：2017-03-16 <br/>
 * 描述：用户类（Dao层）
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUser> {

	@Select("select * from sys_user where user_name=#{user_name}") // mybatis的注解
	public SysUser findUser(@Param("username") String user_name);

}