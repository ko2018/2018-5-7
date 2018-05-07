package com.talent.front.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.front.util.redis.annotation.CacheSpeObject;
import com.talent.front.util.redis.annotation.CacheSpeList;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dao.BaseResearchDao;
import com.talent.front.dao.BaseResearchStatusDao;
import com.talent.front.dto.BaseResearchStatusDto;
import com.talent.front.entity.BaseResearch;
import com.talent.front.entity.BaseResearchStatus;
import com.talent.front.service.BaseResearchService;
import com.talent.front.service.BaseResearchStatusService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题状态表服务实现类
 */
@Service
public class BaseResearchStatusServiceImpl extends BaseServiceImpl<BaseResearchStatus> implements BaseResearchStatusService {
	private static final Logger logger = LoggerFactory.getLogger(BaseResearchStatusServiceImpl.class);

	@Autowired
	private BaseResearchStatusDao baseResearchStatusDao;

	@Autowired
	private BaseResearchService baseResearchService;
	
	@Override
	public BaseDao<BaseResearchStatus> getBaseDao() {
		return this.baseResearchStatusDao;
	}
	
	@CacheSpeObject(value = "baseResearchStatus", key = "#baseResearchStatus.id", OPER = CacheSpeObject.OPER.UPDATE)
	@Override
	public BaseResearchStatusDto updateObjectByPrimaryKeySelective(BaseResearchStatus baseResearchStatus) {
		this.baseResearchStatusDao.updateByPrimaryKeySelective(baseResearchStatus);
		return this.baseResearchStatusDao.selectDtoByPrimaryKey(baseResearchStatus.getId());
	}
	
	@Override
	public PageResult<BaseResearchStatusDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseResearchStatusDao.pageCount(pageObject);
		PageResult<BaseResearchStatusDto> pageResult = new PageResult<BaseResearchStatusDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseResearchStatusDto> baseResearchStatusDtoList = ((BaseResearchStatusServiceImpl) AopContext
					.currentProxy()).pageListCache(pageObject);
			pageResult.setQueryResult(baseResearchStatusDtoList);
		}
		return pageResult;
	}
	
	@Override
//	@CacheSpeList(value = "pageList", key = "#pageObject.sortBy+'_'+#pageObject.sortDir")
	public List<BaseResearchStatusDto> pageListCache(PageObject pageObject) {
		return this.baseResearchStatusDao.pageListDto(pageObject);
	}

    @Override
    public Integer getResearchCurrentSeq(String researchId)
    {
        return this.baseResearchStatusDao.getResearchCurrentSeq(researchId);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Integer addBaseResearchStatus(BaseResearchStatusDto baseResearchStatusDto)
    {
        BaseResearch baseResearch = new BaseResearch();
        baseResearch.setResearchId(baseResearchStatusDto.getResearchId());
        baseResearch.setStatus(baseResearchStatusDto.getStatus());
        baseResearchService.updateObjectByPrimaryKeySelective(baseResearch);
        
        return this.baseResearchStatusDao.insertSelective(baseResearchStatusDto);
    }

}