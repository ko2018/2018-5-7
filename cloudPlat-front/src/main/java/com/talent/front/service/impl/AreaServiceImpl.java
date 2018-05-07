package com.talent.front.service.impl;

import java.util.List;
import java.util.Map;

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
import com.talent.front.dao.AreaDao;
import com.talent.front.dto.AreaDto;
import com.talent.front.entity.Area;
import com.talent.front.service.AreaService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：省市区服务实现类
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {
	private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

	@Autowired
	private AreaDao areaDao;

	@Override
	public BaseDao<Area> getBaseDao() {
		return this.areaDao;
	}

	@CacheSpeObject(value = "area", key = "#area.areaId")
	@Override
	public AreaDto updateObjectByPrimaryKeySelective(Area area) {
		this.areaDao.updateByPrimaryKeySelective(area);
		return this.areaDao.selectDtoByPrimaryKey(area.getAreaId());
	}

	@Override
	public PageResult<AreaDto> pageListDto(PageObject pageObject) {
		long totalCount = this.areaDao.pageCount(pageObject);
		PageResult<AreaDto> pageResult = new PageResult<AreaDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<AreaDto> areaDtoList = ((AreaServiceImpl) AopContext.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(areaDtoList);
		}
		return pageResult;
	}

	@Override
	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<AreaDto> pageListCache(PageObject pageObject) {
		return this.areaDao.pageListDto(pageObject);
	}

	public List<AreaDto> selectDtoByParentId(String id) {
		return this.areaDao.selectDtoByParentId(id);
	}

	public List<AreaDto> selectDtoByParentIdLevel(Map<String, Object> map) {
		return this.areaDao.selectDtoByParentIdLevel(map);
	}

	public AreaDto selectAreaDto(Map<String, Object> paramMap) {
		return this.areaDao.selectAreaDto(paramMap);
	};

}