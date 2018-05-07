package com.talent.mds.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.base.constant.YesOrNo;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.mds.dao.BaseDataStdDao;
import com.talent.mds.dto.BaseDataStdDto;
import com.talent.mds.entity.BaseDataStd;
import com.talent.mds.service.BaseDataStdService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：标准数据表服务实现类
 */
@Service
public class BaseDataStdServiceImpl extends BaseServiceImpl<BaseDataStd> implements BaseDataStdService {
	private static final Logger logger = LoggerFactory.getLogger(BaseDataStdServiceImpl.class);

	@Autowired
	private BaseDataStdDao baseDataStdDao;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public BaseDao<BaseDataStd> getBaseDao() {
		return this.baseDataStdDao;
	}

	@Override
	public BaseDataStdDto selectDtoByPrimaryKey(String datastdId) {
		return this.baseDataStdDao.selectDtoByPrimaryKey(datastdId);
	}

	@Override
	public BaseDataStdDto updateDtoByPrimaryKeySelective(BaseDataStd baseDataStd) {
		this.baseDataStdDao.updateByPrimaryKeySelective(baseDataStd);
		return this.baseDataStdDao.selectDtoByPrimaryKey(baseDataStd.getDatastdId());
	}

	@Override
	public PageResult<BaseDataStdDto> pageListDto(PageObject pageObject) {
		long totalCount = this.baseDataStdDao.pageCount(pageObject);
		PageResult<BaseDataStdDto> pageResult = new PageResult<BaseDataStdDto>();
		pageResult.setTotalCount(totalCount);
		pageResult.setCurPage(pageObject.getCurPage());
		if (totalCount > 0) {
			List<BaseDataStdDto> baseDataStdDtoList = ((BaseDataStdServiceImpl) AopContext.currentProxy())
					.pageListCache(pageObject);
			pageResult.setQueryResult(baseDataStdDtoList);
		}
		return pageResult;
	}

	@Override
	public List<BaseDataStdDto> pageListCache(PageObject pageObject) {
		return this.baseDataStdDao.pageListDto(pageObject);
	}

	/**
	 * @deprecated
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void sendDataDtd(BaseDataStdDto baseDataStdDto) throws JsonProcessingException {
		String json = new ObjectMapper().writeValueAsString(baseDataStdDto);
		kafkaTemplate.send("dataStd", json);
		baseDataStdDto.setIsTransfer(YesOrNo.Y.name());
		baseDataStdDao.updateByPrimaryKeySelective(baseDataStdDto);
	}

}