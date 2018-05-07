package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseFileDto;
import com.talent.front.entity.BaseFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息附件表类（Dao层）
 */
 @Mapper
public interface BaseFileDao extends BaseDao<BaseFile> {

	BaseFileDto selectDtoByPrimaryKey(String fileId);

	List<BaseFileDto> pageListDto(PageObject pageObject);

	List<BaseFileDto> getBaseFilesByIds(List<String> fileIds);
}