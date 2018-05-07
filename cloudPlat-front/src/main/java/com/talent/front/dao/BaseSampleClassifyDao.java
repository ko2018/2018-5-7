package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseSampleClassifyDto;
import com.talent.front.entity.BaseSampleClassify;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：样本类型表类（Dao层）
 */
 @Mapper
public interface BaseSampleClassifyDao extends BaseDao<BaseSampleClassify> {

	BaseSampleClassifyDto selectDtoByPrimaryKey(String classifyCode);

	List<BaseSampleClassifyDto> pageListDto(PageObject pageObject);

}