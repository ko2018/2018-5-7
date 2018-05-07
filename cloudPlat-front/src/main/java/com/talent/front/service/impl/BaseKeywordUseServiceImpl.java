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
import com.talent.front.dao.BaseKeywordUseDao;
import com.talent.front.dto.BaseKeywordUseDto;
import com.talent.front.entity.BaseKeywordUse;
import com.talent.front.service.BaseKeywordUseService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-26 <br/>
 * 描述：关键字应用表服务实现类
 */
@Service
public class BaseKeywordUseServiceImpl extends BaseServiceImpl<BaseKeywordUse> implements BaseKeywordUseService {
	private static final Logger logger = LoggerFactory.getLogger(BaseKeywordUseServiceImpl.class);

	@Autowired
	private BaseKeywordUseDao baseKeywordUseDao;

	@Override
	public BaseDao<BaseKeywordUse> getBaseDao() {
		return this.baseKeywordUseDao;
	}
	
	@CacheSpeObject(value = "baseKeywordUse", key = "#keywordUseId")
	@Override
	public BaseKeywordUseDto selectDtoByPrimaryKey(String keywordUseId) {
		return this.baseKeywordUseDao.selectDtoByPrimaryKey(keywordUseId);
	}
	
	@CacheSpeObject(value = "baseKeywordUse", key = "#baseKeywordUse.keywordUseId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseKeywordUseDto updateDtoByPrimaryKeySelective(BaseKeywordUse baseKeywordUse) {
		this.baseKeywordUseDao.updateByPrimaryKeySelective(baseKeywordUse);
		return this.baseKeywordUseDao.selectDtoByPrimaryKey(baseKeywordUse.getKeywordUseId());
	}
	
	@Override
	public PageResult<BaseKeywordUseDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseKeywordUseDao.pageCountDto(pageObject);
		PageResult<BaseKeywordUseDto> pageResult = new PageResult<BaseKeywordUseDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseKeywordUseDto> baseKeywordUseDtoList = ((BaseKeywordUseServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseKeywordUseDtoList);
		}
		return pageResult;
	}
	
	@Override
	public PageResult<BaseKeywordUseDto> pageListDtoJoin(PageObject pageObject) {
		long totalCount = this.baseKeywordUseDao.pageCountDtoJoin(pageObject);
		PageResult<BaseKeywordUseDto> pageResult = new PageResult<BaseKeywordUseDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseKeywordUseDto> baseKeywordUseDtoList = this.baseKeywordUseDao.pageListDtoJoin(pageObject);
			pageResult.setQueryResult(baseKeywordUseDtoList);
		}
		return pageResult;
	}
	
	@Override
	//@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseKeywordUseDto> pageListCache(PageObject pageObject) {
		return this.baseKeywordUseDao.pageListDto(pageObject);
	}

}