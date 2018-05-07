package com.talent.front.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDocDto;
import com.talent.front.dto.BaseDocLegalDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.service.BaseDocLegalService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：文档空值合法值记录表控制类
 */
@RestController
@RequestMapping("docLegal")
public class BaseDocLegalController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseDocLegalController.class);

	@Autowired
	private BaseDocLegalService baseDocLegalService;

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
		PageResult<BaseDocLegalDto> pageResult = baseDocLegalService.pageListDto(pageObject);
		return setDataValue(pageResult);
	}

	@RequestMapping(value = "listJoin")
	public Map<String, Object> listJoin(String qryWord) {
		PageObject pageObject = getPageObject();
		PageResult<BaseDocLegalDto> pageResult = baseDocLegalService.pageListDtoJoin(pageObject);
		return setDataValue(pageResult);
	}

}