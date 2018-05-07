package com.talent.dcs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.dcs.dao.BaseDocLegalDao;
import com.talent.dcs.dto.BaseDocLegalDto;
import com.talent.dcs.entity.BaseDocLegal;
import com.talent.dcs.service.BaseDocLegalService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：文档空值合法值记录表服务实现类
 */
@Service
public class BaseDocLegalServiceImpl extends BaseServiceImpl<BaseDocLegal> implements BaseDocLegalService {
	private static final Logger logger = LoggerFactory.getLogger(BaseDocLegalServiceImpl.class);

	@Autowired
	private BaseDocLegalDao baseDocLegalDao;

	@Override
	public BaseDao<BaseDocLegal> getBaseDao() {
		return this.baseDocLegalDao;
	}

	@Override
	public BaseDocLegalDto selectDtoByPrimaryKey(String docvalueId) {
		return this.baseDocLegalDao.selectDtoByPrimaryKey(docvalueId);
	}

	@Override
	public BaseDocLegalDto updateDtoByPrimaryKeySelective(BaseDocLegal baseDocLegal) {
		this.baseDocLegalDao.updateByPrimaryKeySelective(baseDocLegal);
		return this.baseDocLegalDao.selectDtoByPrimaryKey(baseDocLegal.getDocvalueId());
	}

	@Override
	public PageResult<BaseDocLegalDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseDocLegalDao.pageCount(pageObject);
		PageResult<BaseDocLegalDto> pageResult = new PageResult<BaseDocLegalDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseDocLegalDto> baseDocLegalDtoList = ((BaseDocLegalServiceImpl) AopContext.currentProxy())
					.pageListCache(pageObject);
			pageResult.setQueryResult(baseDocLegalDtoList);
		}
		return pageResult;
	}

	@Override
	// @CacheSpeList(value = "pageList", key =
	// "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseDocLegalDto> pageListCache(PageObject pageObject) {
		return this.baseDocLegalDao.pageListDto(pageObject);
	}

	/**
	 * 插入baseDocLegal ， 判断是否已经存在
	 * 
	 * @param baseDocLegal
	 */
	@Override
	public void addBaseDocLegal(BaseDocLegal baseDocLegal) {
		PageObject po = new PageObject();
		po.setPageSize(10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictId", baseDocLegal.getDictId());
		map.put("docId", baseDocLegal.getDocId());
		po.setQueryCondition(map);
		List<BaseDocLegalDto> baseDocLegalDtos = pageListDto(po).getQueryResult();
		if (baseDocLegalDtos.size() == 0) {
			baseDocLegalDao.insertSelective(baseDocLegal);
		} else {
			// 要不要修改总数
		}
	}

	@Override
	public BaseDocLegalDto getDtoByDictIdAndDocId(int dictId, String docId) {
		return baseDocLegalDao.getDtoByDictIdAndDocId(dictId, docId);
	}

	@Override
	public void updateByCountAll(int countAll, String docId) {
		baseDocLegalDao.updateByCountAll(countAll, docId);
	}

	@Override
	public void updateCount(String docId) {
		baseDocLegalDao.updateCount(docId);
	}

}