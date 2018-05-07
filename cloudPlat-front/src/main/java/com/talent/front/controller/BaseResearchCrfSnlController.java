package com.talent.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.talent.front.service.BaseResearchCrfSnlService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研CRF模板属性表控制类
 */
@Controller
public class BaseResearchCrfSnlController {
	private static final Logger logger = LoggerFactory.getLogger(BaseResearchCrfSnlController.class);

	@Autowired
	private BaseResearchCrfSnlService baseResearchCrfSnlService;

}