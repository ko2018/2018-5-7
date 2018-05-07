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
import com.talent.front.dao.BaseDocTypeDao;
import com.talent.front.dto.BaseDocTypeDto;
import com.talent.front.entity.BaseDocType;
import com.talent.front.service.BaseDocTypeService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：文档类型服务实现类
 */
@Service
public class BaseDocTypeServiceImpl extends BaseServiceImpl<BaseDocType> implements BaseDocTypeService {
	private static final Logger logger = LoggerFactory.getLogger(BaseDocTypeServiceImpl.class);

	@Autowired
	private BaseDocTypeDao baseDocTypeDao;

	@Override
	public BaseDao<BaseDocType> getBaseDao() {
		return this.baseDocTypeDao;
	}
	
	@CacheSpeObject(value = "baseDocType", key = "#doctypeId")
	@Override
	public BaseDocTypeDto selectDtoByPrimaryKey(String doctypeId) {
		return this.baseDocTypeDao.selectDtoByPrimaryKey(doctypeId);
	}
	
	@CacheSpeObject(value = "baseDocType", key = "#baseDocType.doctypeId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseDocTypeDto updateDtoByPrimaryKeySelective(BaseDocType baseDocType) {
		this.baseDocTypeDao.updateByPrimaryKeySelective(baseDocType);
		return this.baseDocTypeDao.selectDtoByPrimaryKey(baseDocType.getDoctypeId());
	}
	
	@Override
	public PageResult<BaseDocTypeDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseDocTypeDao.pageCount(pageObject);
		PageResult<BaseDocTypeDto> pageResult = new PageResult<BaseDocTypeDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseDocTypeDto> baseDocTypeDtoList = ((BaseDocTypeServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseDocTypeDtoList);
		}
		return pageResult;
	}
	
	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseDocTypeDto> pageListCache(PageObject pageObject) {
		return this.baseDocTypeDao.pageListDto(pageObject);
	}

}