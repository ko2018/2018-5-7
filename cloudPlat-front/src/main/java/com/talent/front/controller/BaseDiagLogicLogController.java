package com.talent.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.talent.front.service.BaseDiagLogicLogService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断逻辑日志控制类
 */
@Controller
public class BaseDiagLogicLogController {
	private static final Logger logger = LoggerFactory.getLogger(BaseDiagLogicLogController.class);

	@Autowired
	private BaseDiagLogicLogService baseDiagLogicLogService;

}