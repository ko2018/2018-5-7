package com.talent.base.database;

import java.util.List;

import com.talent.base.model.BaseEntity;
import com.talent.base.model.PageObject;

public interface BaseDao<T extends BaseEntity> {

	int deleteByPrimaryKey(String dictDetailId);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(String dictDetailId);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

	List<T> pageList(PageObject pageObject);

	long pageCount(PageObject pageObject);

	void batchInsert(List<T> list);
}
