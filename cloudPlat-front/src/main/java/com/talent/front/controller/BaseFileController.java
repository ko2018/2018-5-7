package com.talent.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.talent.front.service.BaseFileService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息附件表控制类
 */
@Controller
public class BaseFileController {
	private static final Logger logger = LoggerFactory.getLogger(BaseFileController.class);

	@Autowired
	private BaseFileService baseFileService;

}