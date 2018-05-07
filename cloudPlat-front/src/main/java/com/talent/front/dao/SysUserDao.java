package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.SysUser;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-11-28 <br/>
 * 描述：管理员类（Dao层）
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUser> {

    SysUserDto selectDtoByPrimaryKey(String userId);

    List<SysUserDto> pageListDto(PageObject pageObject);

    long pageCountDto(PageObject pageObject);

    long pageCountDtoJoin(PageObject pageObject);

    List<SysUserDto> pageListDtoJoin(PageObject pageObject);

    SysUserDto getUserByName(@Param("username") String username);

}