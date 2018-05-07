package com.talent.dcs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.dcs.dto.BaseItemDto;
import com.talent.dcs.entity.BaseItem;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：数据项类（Dao层）
 */
@Mapper
public interface BaseItemDao extends BaseDao<BaseItem> {

	BaseItemDto selectDtoByPrimaryKey(String itemId);

	List<BaseItemDto> pageListDto(PageObject pageObject);

	long pageCountDto(PageObject pageObject);

	long pageCountDtoJoin(PageObject pageObject);

	List<BaseItemDto> pageListDtoJoin(PageObject pageObject);

	long pageCountJoin(PageObject pageObject);

	List<BaseItemDto> findItemsByLogicId(String logicId);

	void batchUpdateSelective(List<BaseItem> baseItems);

	int getCountByAlias(String itemAlias);

}