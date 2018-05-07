package com.talent.front.service;

import java.util.List;

import com.talent.base.service.BaseService;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsDiseaseDiagnoseMonthOrganDto;
import com.talent.front.entity.StaticsDiseaseDiagnoseMonthOrgan;
import com.talent.front.entity.StaticsSearchCondition;
import com.talent.front.entity.StaticsSearchResultSet;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：统计临时疾病诊断月表服务接口类
 */
public interface StaticsDiseaseDiagnoseMonthOrganService extends BaseService<StaticsDiseaseDiagnoseMonthOrgan>{

	/**
	* 以主鍵查询DTO
	*/
	StaticsDiseaseDiagnoseMonthOrganDto selectDtoByPrimaryKey(String staticsId);

	/**
	* 更新model返回DTO
	*/
	StaticsDiseaseDiagnoseMonthOrganDto updateDtoByPrimaryKeySelective(StaticsDiseaseDiagnoseMonthOrgan staticsDiseaseDiagnoseMonthOrgan);

	PageResult<StaticsDiseaseDiagnoseMonthOrganDto> pageListDto(PageObject pageObject);
	
	List<StaticsDiseaseDiagnoseMonthOrganDto> pageListCache(PageObject pageObject);

	StaticsSearchResultSet scanHighDiseaseResultSet(StaticsSearchCondition condition);
	StaticsSearchResultSet scanCrowdHighDiseaseResultSet(StaticsSearchCondition condition);
	
	PageResult<StaticsDiseaseDiagnoseMonthOrganDto> pageListDetailsDto(PageObject pageObject);
	
	List<StaticsDiseaseDiagnoseMonthOrganDto> pageListCache1(PageObject pageObject);
	
}