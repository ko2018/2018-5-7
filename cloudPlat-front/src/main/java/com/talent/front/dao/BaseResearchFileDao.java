package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResearchFileDto;
import com.talent.front.entity.BaseResearchFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档类（Dao层）
 */
 @Mapper
public interface BaseResearchFileDao extends BaseDao<BaseResearchFile> {

	BaseResearchFileDto selectDtoByPrimaryKey(String fileId);

	List<BaseResearchFileDto> pageListDto(PageObject pageObject);

}