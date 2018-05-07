package com.talent.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.front.util.redis.annotation.CacheSpeObject;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.front.dao.BaseDataRawDao;
import com.talent.front.dto.BaseDataRawDto;
import com.talent.front.entity.BaseDataRaw;
import com.talent.front.entity.BaseSearchDataRaw;
import com.talent.front.service.BaseDataRawService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：原始数据表服务实现类
 */
@Service
public class BaseDataRawServiceImpl extends BaseServiceImpl<BaseDataRaw> implements BaseDataRawService {
	private static final Logger logger = LoggerFactory.getLogger(BaseDataRawServiceImpl.class);

	@Autowired
	private BaseDataRawDao baseDataRawDao;

	@Override
	public BaseDao<BaseDataRaw> getBaseDao() {
		return this.baseDataRawDao;
	}

	@CacheSpeObject(value = "baseDataRaw", key = "#datarawId")
	@Override
	public BaseDataRawDto selectDtoByPrimaryKey(String datarawId) {
		return this.baseDataRawDao.selectDtoByPrimaryKey(datarawId);
	}

	@CacheSpeObject(value = "baseDataRaw", key = "#baseDataRaw.datarawId", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseDataRawDto updateDtoByPrimaryKeySelective(BaseDataRaw baseDataRaw) {
		this.baseDataRawDao.updateByPrimaryKeySelective(baseDataRaw);
		return this.baseDataRawDao.selectDtoByPrimaryKey(baseDataRaw.getDatarawId());
	}

	@Override
	public PageResult<BaseDataRawDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseDataRawDao.pageCount(pageObject);
		PageResult<BaseDataRawDto> pageResult = new PageResult<BaseDataRawDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseDataRawDto> baseDataRawDtoList = ((BaseDataRawServiceImpl) AopContext.currentProxy())
					.pageListCache(pageObject);
			pageResult.setQueryResult(baseDataRawDtoList);
		}
		return pageResult;
	}

	@Override
	// @CacheSpeList(value = "pageList", key =
	// "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseDataRawDto> pageListCache(PageObject pageObject) {
		return this.baseDataRawDao.pageListDto(pageObject);
	}

	@Override
	public BaseDataRawDto getDtoByAllKey(String userCode, String institutionId, String checkCode, String docId) {
		return this.baseDataRawDao.getDtoByAllKey(userCode, institutionId, checkCode, docId);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addBaseDataRawDto(BaseDataRawDto baseDataRawDto) {
		BaseDataRawDto tmp = baseDataRawDao.getDtoByAllKey(baseDataRawDto.getUserCode(),
				baseDataRawDto.getInstitutionId(), baseDataRawDto.getCheckCode(), baseDataRawDto.getDocId());
		if (tmp == null) {
			baseDataRawDao.insertSelective(baseDataRawDto);
		}
	}
	
	public BaseDataRawDto selectDtoByOrganExamIDUserID(BaseSearchDataRaw baseSearchDataRaw) {
		List<BaseDataRawDto> list = baseDataRawDao.selectDtoByOrganExamIDUserID(baseSearchDataRaw);
		if(list == null)
			return null;
		if(list.size() < 1)
			return null;
		
		return list.get(0);
	}

}