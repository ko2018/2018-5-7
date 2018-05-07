package com.talent.front.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseInstitutionLevelDto;
import com.talent.front.service.BaseInstitutionLevelService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：机构级别控制类
 */
@RestController
@RequestMapping("institutionLevel")
public class BaseInstitutionLevelController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseInstitutionLevelController.class);

	@Autowired
	private BaseInstitutionLevelService baseInstitutionLevelService;

	@RequestMapping(value = "list")
	public Map<String, Object> list() {
		PageObject pageObject = getPageObject();
		pageObject.setPageSize(30);
		pageObject.setOrderByClause("sequence asc");
		PageResult<BaseInstitutionLevelDto> pageResult = baseInstitutionLevelService.pageListDto(pageObject);
		return setDataValue(pageResult);
	}
}