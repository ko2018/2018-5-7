package com.talent.dcs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.dcs.constant.CommonConstant;
import com.talent.dcs.dao.BaseSnlDao;
import com.talent.dcs.dto.BaseSnlDto;
import com.talent.dcs.entity.BaseSnl;
import com.talent.dcs.service.BaseSnlService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：标准术语表服务实现类
 */
@Service
public class BaseSnlServiceImpl extends BaseServiceImpl<BaseSnl> implements BaseSnlService {
	private static final Logger logger = LoggerFactory.getLogger(BaseSnlServiceImpl.class);

	@Autowired
	private BaseSnlDao baseSnlDao;

	@Override
	public BaseDao<BaseSnl> getBaseDao() {
		return this.baseSnlDao;
	}

	@Override
	public BaseSnlDto selectDtoByPrimaryKey(String snlId) {
		return this.baseSnlDao.selectDtoByPrimaryKey(snlId);
	}

	@Override
	public BaseSnlDto updateDtoByPrimaryKeySelective(BaseSnl baseSnl) {
		this.baseSnlDao.updateByPrimaryKeySelective(baseSnl);
		return this.baseSnlDao.selectDtoByPrimaryKey(baseSnl.getSnlId());
	}

	@Override
	public PageResult<BaseSnlDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseSnlDao.pageCountDto(pageObject);
		PageResult<BaseSnlDto> pageResult = new PageResult<BaseSnlDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseSnlDto> baseSnlDtoList = ((BaseSnlServiceImpl) AopContext.currentProxy())
					.pageListCache(pageObject);
			pageResult.setQueryResult(baseSnlDtoList);
		}
		return pageResult;
	}

	@Override
	public PageResult<BaseSnlDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.baseSnlDao.pageCountDtoJoin(pageObject);
		PageResult<BaseSnlDto> pageResult = new PageResult<BaseSnlDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseSnlDto> baseSnlDtoList = this.baseSnlDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(baseSnlDtoList);
		}
		return pageResult;
	}

	@Override
	// @CacheSpeList(value = "pageList", key =
	// "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseSnlDto> pageListCache(PageObject pageObject) {
		return this.baseSnlDao.pageListDto(pageObject);
	}

	@Override
	public Map<String, BaseSnl> getMapByNameObject() {
		Map<String, BaseSnl> map = new HashMap<String, BaseSnl>();
		PageObject pageObject = new PageObject();
		pageObject.setPageSize(10000);
		pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
		pageObject.getQueryCondition().put("isLeaf", CommonConstant.Is.YES);

		List<BaseSnl> list = this.baseSnlDao.pageList(pageObject);
		for (BaseSnl baseSnl : list) {
			map.put(baseSnl.getNameCn(), baseSnl);
		}
		return map;
	}

	@Override
	public Map<Integer, BaseSnl> getMapByDictIdObject() {
		Map<Integer, BaseSnl> map = new HashMap<Integer, BaseSnl>();
		PageObject pageObject = new PageObject();
		pageObject.setPageSize(10000);
		pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
		pageObject.getQueryCondition().put("isLeaf", CommonConstant.Is.YES);

		List<BaseSnl> list = this.baseSnlDao.pageList(pageObject);
		for (BaseSnl baseSnl : list) {
			map.put(baseSnl.getDictId(), baseSnl);
		}
		return map;
	}

}