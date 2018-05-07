package com.talent.base.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.talent.base.database.BaseDao;
import com.talent.base.model.BaseEntity;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.util.UUIDUtil;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public PageResult<T> pageList(PageObject pageObject) {
		PageResult<T> pageResult = new PageResult<T>();
		pageResult.setCurPage(pageObject.getCurPage());
		pageResult.setCurPageSize(pageObject.getPageSize());
		pageResult.setTotalCount(getBaseDao().pageCount(pageObject));
		pageResult.setQueryResult(getBaseDao().pageList(pageObject));
		return pageResult;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int deleteByPrimaryKey(String dictDetailId) {
		return getBaseDao().deleteByPrimaryKey(dictDetailId);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int insert(T record) {
		if (StringUtils.isEmpty(record.get_Id())) {
			record.set_Id(UUIDUtil.getUUID());
		}
		return getBaseDao().insert(record);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int insertSelective(T record) {
		if (StringUtils.isEmpty(record.get_Id())) {
			record.set_Id(UUIDUtil.getUUID());
		}
		return getBaseDao().insertSelective(record);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public T selectByPrimaryKey(String dictDetailId) {
		return getBaseDao().selectByPrimaryKey(dictDetailId);
	}

	/**
	 * @deprecated this is update method by default.if you use in cache ,please use
	 *             {@link #updateObjectByPrimaryKeySelective(BaseEntity)}
	 */
	@Deprecated
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int updateByPrimaryKeySelective(T record) {
		return getBaseDao().updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int updateByPrimaryKey(T record) {
		return getBaseDao().updateByPrimaryKey(record);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void batchInsert(List<T> list) {
		getBaseDao().batchInsert(list);
	}

	public abstract BaseDao<T> getBaseDao();

}
