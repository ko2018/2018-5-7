package com.talent.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.talent.front.service.BaseDiagLogicCalcService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：逻辑运算关系控制类
 */
@Controller
public class BaseDiagLogicCalcController {
	private static final Logger logger = LoggerFactory.getLogger(BaseDiagLogicCalcController.class);

	@Autowired
	private BaseDiagLogicCalcService baseDiagLogicCalcService;

}