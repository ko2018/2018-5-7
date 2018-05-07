package com.talent.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.BaseResearchCrfTempletDto;
import com.talent.front.entity.BaseResearchCrfTemplet;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板表类（Dao层）
 */
 @Mapper
public interface BaseResearchCrfTempletDao extends BaseDao<BaseResearchCrfTemplet> {

	BaseResearchCrfTempletDto selectDtoByPrimaryKey(String templetId);

	List<BaseResearchCrfTempletDto> pageListDto(PageObject pageObject);

}