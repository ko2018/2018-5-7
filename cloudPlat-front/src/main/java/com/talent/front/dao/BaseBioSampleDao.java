package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseBioSampleDto;
import com.talent.front.entity.BaseBioSample;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：生物样本表类（Dao层）
 */
 @Mapper
public interface BaseBioSampleDao extends BaseDao<BaseBioSample> {

	BaseBioSampleDto selectDtoByPrimaryKey(String sampleId);

	List<BaseBioSampleDto> pageListDto(PageObject pageObject);

}