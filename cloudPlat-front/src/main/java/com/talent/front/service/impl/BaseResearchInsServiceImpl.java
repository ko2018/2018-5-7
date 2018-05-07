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
import com.talent.front.dao.BaseResearchInsDao;
import com.talent.front.dto.BaseResearchInsDto;
import com.talent.front.entity.BaseResearchIns;
import com.talent.front.service.BaseResearchInsService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题机构关系表服务实现类
 */
@Service
public class BaseResearchInsServiceImpl extends BaseServiceImpl<BaseResearchIns> implements BaseResearchInsService {
	private static final Logger logger = LoggerFactory.getLogger(BaseResearchInsServiceImpl.class);

	@Autowired
	private BaseResearchInsDao baseResearchInsDao;

	@Override
	public BaseDao<BaseResearchIns> getBaseDao() {
		return this.baseResearchInsDao;
	}
	
	@CacheSpeObject(value = "baseResearchIns", key = "#baseResearchIns.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseResearchInsDto updateObjectByPrimaryKeySelective(BaseResearchIns baseResearchIns) {
		this.baseResearchInsDao.updateByPrimaryKeySelective(baseResearchIns);
		return this.baseResearchInsDao.selectDtoByPrimaryKey(baseResearchIns.getId());
	}
	
	@Override
	public PageResult<BaseResearchInsDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseResearchInsDao.pageCount(pageObject);
		PageResult<BaseResearchInsDto> pageResult = new PageResult<BaseResearchInsDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseResearchInsDto> baseResearchInsDtoList = ((BaseResearchInsServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseResearchInsDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseResearchInsDto> pageListCache(PageObject pageObject) {
		return this.baseResearchInsDao.pageListDto(pageObject);
	}

}