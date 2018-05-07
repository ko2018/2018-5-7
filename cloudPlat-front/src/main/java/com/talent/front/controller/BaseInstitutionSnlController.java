package com.talent.front.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.constant.YesOrNo;
import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseInstitutionSnlDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.service.BaseInstitutionService;
import com.talent.front.service.BaseInstitutionSnlService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-18 <br/>
 * 描述：机构标准术语对应表控制类
 */
@RestController
@RequestMapping("institutionSnl")
public class BaseInstitutionSnlController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseInstitutionSnlController.class);

	@Autowired
	private BaseInstitutionSnlService baseInstitutionSnlService;
	@Autowired

	private BaseInstitutionService baseInstitutionService;

	private SysUserDto getUser() {
		SysUserDto sud = new SysUserDto();
		sud.setUserId("user_1");
		sud.setInstitutionId("14250e6b72e14233b7bbbad8870329c6");
		sud.setUserName("admin");
		return new SysUserDto();
	}

	@RequestMapping(value = "list")
	public Map<String, Object> list() {
		PageObject pageObject = getPageObject();
		PageResult<BaseInstitutionSnlDto> pageResult = baseInstitutionSnlService.pageListDto(pageObject);
		return setDataValue(pageResult);
	}

	@RequestMapping(value = "listJoin")
	public Map<String, Object> listJoin(String qryWord) {
		PageObject pageObject = getPageObject();
		PageResult<BaseInstitutionSnlDto> pageResult = baseInstitutionSnlService.pageListDtoJoin(pageObject);
		return setDataValue(pageResult);
	}

	@RequestMapping(value = "relation")
	public Map<String, Object> relation(String relation, int dictId, String institutionSnlVersionId,
			String institutionSnlIds) throws Exception {
		if (!StringUtils.isEmpty(relation)) {
			if (relation.trim().equals(YesOrNo.Y.name())) { // 关联
				baseInstitutionSnlService.updateRelation(dictId, institutionSnlVersionId, institutionSnlIds);
			} else {
				baseInstitutionSnlService.updateRelationCanel(dictId, institutionSnlVersionId);
			}
		} else {
			throw new GlobalException(ErrorCode.INSTITUTIONSNL_PARAM_ERROR);
		}
		return setDataValue(null);
	}

	@RequestMapping(value = "statistics")
	public Map<String, Object> statistics(String institutionSnlVersionId) throws Exception {
		return setDataValue(baseInstitutionSnlService.statistics(institutionSnlVersionId));
	}

	@RequestMapping(value = "relationAll")
	public Map<String, Object> relationAll(String institutionSnlVersionId) throws Exception {
		baseInstitutionSnlService.relationAll(institutionSnlVersionId);
		return setDataValue(null);
	}

}