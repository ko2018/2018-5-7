package com.talent.dcs.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.dcs.constant.CommonConstant;
import com.talent.dcs.dao.BaseDiagMultipleDao;
import com.talent.dcs.dao.BaseDiseasesLogicDao;
import com.talent.dcs.dao.BaseItemDao;
import com.talent.dcs.dto.BaseDiseasesLogicDto;
import com.talent.dcs.entity.BaseDiseasesLogic;
import com.talent.dcs.service.BaseDiseasesLogicService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：疾病诊断逻辑服务实现类
 */
@Service
public class BaseDiseasesLogicServiceImpl extends BaseServiceImpl<BaseDiseasesLogic>
		implements BaseDiseasesLogicService {
	private static final Logger logger = LoggerFactory.getLogger(BaseDiseasesLogicServiceImpl.class);

	@Autowired
	private BaseDiseasesLogicDao baseDiseasesLogicDao;

	@Autowired
	private BaseDiagMultipleDao baseDiagMultipleDao;

	@Override
	public BaseDao<BaseDiseasesLogic> getBaseDao() {
		return this.baseDiseasesLogicDao;
	}

	@Override
	public BaseDiseasesLogicDto selectDtoByPrimaryKey(String logicId) {
		return this.baseDiseasesLogicDao.selectDtoByPrimaryKey(logicId);
	}

	@Override
	public BaseDiseasesLogicDto updateDtoByPrimaryKeySelective(BaseDiseasesLogic baseDiseasesLogic) {
		this.baseDiseasesLogicDao.updateByPrimaryKeySelective(baseDiseasesLogic);
		return this.baseDiseasesLogicDao.selectDtoByPrimaryKey(baseDiseasesLogic.getLogicId());
	}

	@Override
	public PageResult<BaseDiseasesLogicDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseDiseasesLogicDao.pageCount(pageObject);
		PageResult<BaseDiseasesLogicDto> pageResult = new PageResult<BaseDiseasesLogicDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseDiseasesLogicDto> baseDiseasesLogicDtoList = ((BaseDiseasesLogicServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseDiseasesLogicDtoList);
		}
		return pageResult;
	}

	@Override
	// @CacheSpeList(value = "pageList", key =
	// "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseDiseasesLogicDto> pageListCache(PageObject pageObject) {
		return this.baseDiseasesLogicDao.pageListDto(pageObject);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public BaseDiseasesLogicDto changeDefault(BaseDiseasesLogicDto baseDiseasesLogicDto) {
		baseDiseasesLogicDao.changeDefault(baseDiseasesLogicDto.getDiseasesId(), baseDiseasesLogicDto.getUserId(),
				CommonConstant.Is.NO);

		// 变更默认逻辑,重置综合诊断表写es标识字段
		baseDiagMultipleDao.resetEsFlag(baseDiseasesLogicDto.getLogicId(), baseDiseasesLogicDto.getUserId());

		baseDiseasesLogicDto.setIsDefault(CommonConstant.Is.YES);
		return ((BaseDiseasesLogicServiceImpl) AopContext.currentProxy())
				.updateDtoByPrimaryKeySelective(baseDiseasesLogicDto);
	}

	@Override
	public BaseDiseasesLogicDto selectDefaultDtoByDiseasesId(String diseasesId) {
		return baseDiseasesLogicDao.selectDefaultDtoByDiseasesId(diseasesId);
	}

}