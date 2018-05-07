package com.talent.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.talent.front.service.BaseSnlRuleValueService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：术语值规则控制类
 */
@Controller
public class BaseSnlRuleValueController {
	private static final Logger logger = LoggerFactory.getLogger(BaseSnlRuleValueController.class);

	@Autowired
	private BaseSnlRuleValueService baseSnlRuleValueService;

}