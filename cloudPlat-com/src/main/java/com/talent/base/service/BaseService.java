package com.talent.base.service;

import java.util.List;

import com.talent.base.model.BaseEntity;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;

public interface BaseService<T extends BaseEntity> {
	int deleteByPrimaryKey(String dictDetailId);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(String dictDetailId);

	/**
	 * @deprecated this is update method by default.if you use in cache ,please use
	 *             {@link #updateObjectByPrimaryKeySelective(BaseEntity)}
	 * @param record
	 * @return
	 */
	@Deprecated
	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

	PageResult<T> pageList(PageObject pageObject);

	void batchInsert(List<T> list);
}
