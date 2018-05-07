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
import com.talent.front.dao.BaseResearchUserDao;
import com.talent.front.dto.BaseResearchUserDto;
import com.talent.front.entity.BaseResearchUser;
import com.talent.front.service.BaseResearchUserService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题用户表服务实现类
 */
@Service
public class BaseResearchUserServiceImpl extends BaseServiceImpl<BaseResearchUser> implements BaseResearchUserService {
	private static final Logger logger = LoggerFactory.getLogger(BaseResearchUserServiceImpl.class);

	@Autowired
	private BaseResearchUserDao baseResearchUserDao;

	@Override
	public BaseDao<BaseResearchUser> getBaseDao() {
		return this.baseResearchUserDao;
	}
	
	@CacheSpeObject(value = "baseResearchUser", key = "#baseResearchUser.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseResearchUserDto updateObjectByPrimaryKeySelective(BaseResearchUser baseResearchUser) {
		this.baseResearchUserDao.updateByPrimaryKeySelective(baseResearchUser);
		return this.baseResearchUserDao.selectDtoByPrimaryKey(baseResearchUser.getId());
	}
	
	@Override
	public PageResult<BaseResearchUserDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseResearchUserDao.pageCount(pageObject);
		PageResult<BaseResearchUserDto> pageResult = new PageResult<BaseResearchUserDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseResearchUserDto> baseResearchUserDtoList = ((BaseResearchUserServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseResearchUserDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseResearchUserDto> pageListCache(PageObject pageObject) {
		return this.baseResearchUserDao.pageListDto(pageObject);
	}

}