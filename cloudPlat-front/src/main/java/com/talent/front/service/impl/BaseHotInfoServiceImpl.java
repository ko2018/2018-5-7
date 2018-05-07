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
import com.talent.front.dao.BaseHotInfoDao;
import com.talent.front.dto.BaseHotInfoDto;
import com.talent.front.entity.BaseHotInfo;
import com.talent.front.service.BaseHotInfoService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息表服务实现类
 */
@Service
public class BaseHotInfoServiceImpl extends BaseServiceImpl<BaseHotInfo> implements BaseHotInfoService {
	private static final Logger logger = LoggerFactory.getLogger(BaseHotInfoServiceImpl.class);

	@Autowired
	private BaseHotInfoDao baseHotInfoDao;

	@Override
	public BaseDao<BaseHotInfo> getBaseDao() {
		return this.baseHotInfoDao;
	}
	
	@CacheSpeObject(value = "baseHotInfo", key = "#hotInfoId")
	@Override
	public BaseHotInfoDto selectDtoByPrimaryKey(String hotInfoId) {
		return this.baseHotInfoDao.selectDtoByPrimaryKey(hotInfoId);
	}
	
	@CacheSpeObject(value = "baseHotInfo", key = "#baseHotInfo.hotInfoId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseHotInfoDto updateDtoByPrimaryKeySelective(BaseHotInfo baseHotInfo) {
		this.baseHotInfoDao.updateByPrimaryKeySelective(baseHotInfo);
		return this.baseHotInfoDao.selectDtoByPrimaryKey(baseHotInfo.getHotInfoId());
	}
	
	@Override
	public PageResult<BaseHotInfoDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseHotInfoDao.pageCount(pageObject);
		PageResult<BaseHotInfoDto> pageResult = new PageResult<BaseHotInfoDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseHotInfoDto> baseHotInfoDtoList = ((BaseHotInfoServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseHotInfoDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseHotInfoDto> pageListCache(PageObject pageObject) {
		return this.baseHotInfoDao.pageListDto(pageObject);
	}

	@Override
	public List<BaseHotInfo> selectDtoByIds(List<String> ids) {
		return this.baseHotInfoDao.selectByIds(ids);
	}

	@Override
	public int batchUpdateSelective(List<BaseHotInfo> baseHotInfos) {
		return this.baseHotInfoDao.batchUpdateSelective(baseHotInfos);
	}

}