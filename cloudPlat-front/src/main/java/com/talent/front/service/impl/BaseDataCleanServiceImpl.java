package com.talent.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.front.util.redis.annotation.CacheSpeObject;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.BaseDataCleanDao;
import com.talent.front.dto.BaseDataCleanDto;
import com.talent.front.entity.BaseDataClean;
import com.talent.front.service.BaseDataCleanService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：清洗数据表服务实现类
 */
@Service
public class BaseDataCleanServiceImpl extends BaseServiceImpl<BaseDataClean> implements BaseDataCleanService {
	private static final Logger logger = LoggerFactory.getLogger(BaseDataCleanServiceImpl.class);

	@Autowired
	private BaseDataCleanDao baseDataCleanDao;

	@Override
	public BaseDao<BaseDataClean> getBaseDao() {
		return this.baseDataCleanDao;
	}

	@CacheSpeObject(value = "baseDataClean", key = "#datacleanId")
	@Override
	public BaseDataCleanDto selectDtoByPrimaryKey(String datacleanId) {
		return this.baseDataCleanDao.selectDtoByPrimaryKey(datacleanId);
	}

	@CacheSpeObject(value = "baseDataClean", key = "#baseDataClean.datacleanId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseDataCleanDto updateDtoByPrimaryKeySelective(BaseDataClean baseDataClean) {
		this.baseDataCleanDao.updateByPrimaryKeySelective(baseDataClean);
		return this.baseDataCleanDao.selectDtoByPrimaryKey(baseDataClean.getDatacleanId());
	}

	@Override
	public PageResult<BaseDataCleanDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseDataCleanDao.pageCount(pageObject);
		PageResult<BaseDataCleanDto> pageResult = new PageResult<BaseDataCleanDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseDataCleanDto> baseDataCleanDtoList = ((BaseDataCleanServiceImpl) AopContext.currentProxy())
					.pageListCache(pageObject);
			pageResult.setQueryResult(baseDataCleanDtoList);
		}
		return pageResult;
	}

	@Override
	// @CacheSpeList(value = "pageList", key =
	// "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseDataCleanDto> pageListCache(PageObject pageObject) {
		return this.baseDataCleanDao.pageListDto(pageObject);
	}

	@Override
	public BaseDataCleanDto getDtoByAllKey(String userCode, String institutionId, String checkCode, String docId) {
		return this.baseDataCleanDao.getDtoByAllKey(userCode, institutionId, checkCode, docId);
	}

}