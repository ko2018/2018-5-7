package com.talent.front.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsHasDiseaseYearDto;
import com.talent.front.service.StaticsHasDiseaseYearService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：统计原始数据每年患病情况控制类
 */
@RestController
@RequestMapping("organs")
public class StaticsHasDiseaseYearController  extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(StaticsHasDiseaseYearController.class);

	@Autowired
	private StaticsHasDiseaseYearService staticsHasDiseaseYearService;
	
	@RequestMapping(value = "list")
	public Map<String, Object> list() {
		PageObject pageObject = getPageObject();
		PageResult<StaticsHasDiseaseYearDto> pageResult = staticsHasDiseaseYearService.pageListDto(pageObject);
		return setDataValue(pageResult);
	}

}