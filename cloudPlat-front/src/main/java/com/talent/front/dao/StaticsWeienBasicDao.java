package com.talent.front.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.front.dto.StaticsWeienBasicDto;
import com.talent.front.entity.StaticsHighDisease;
import com.talent.front.entity.StaticsSearchCondition;
import com.talent.front.entity.StaticsWeienBasic;
import com.talent.front.entity.StaticsweienSearchResultSet;
import com.talent.front.entity.StaticsweienSearchResultSub;
import com.talent.front.entity.StaticsWeienSearchCondition;
import com.talent.front.entity.StaticsweienSearchResultMain;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-12 <br/>
 * 描述：类（Dao层）
 */
 @Mapper
public interface StaticsWeienBasicDao extends BaseDao<StaticsWeienBasic> {

	StaticsWeienBasicDto selectDtoByPrimaryKey(String id);

	List<StaticsWeienBasicDto> pageListDto(PageObject pageObject);
	
	long pageCountDto(PageObject pageObject);
	List<StaticsweienSearchResultSet> scanWeienList(StaticsWeienSearchCondition condition);
	long pageCountDtoJoin(PageObject pageObject);

	List<StaticsWeienBasicDto> pageListDtoJoin(PageObject pageObject);
	
	/**
	 * 统计患每种病的总数
	 * @param condition
	 * @return
	 */
	List<StaticsweienSearchResultMain> countDis(Map<String,Object> map);
	
	/**
	 * 获取患病人情况
	 * @param condition
	 * @return
	 */
	List<StaticsweienSearchResultSub> getDisPersonList(Map<String,Object> map);

}