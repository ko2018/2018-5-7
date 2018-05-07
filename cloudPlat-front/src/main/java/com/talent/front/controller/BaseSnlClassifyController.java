package com.talent.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.talent.front.service.BaseSnlClassifyService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：标准类别表控制类
 */
@Controller
public class BaseSnlClassifyController {
	private static final Logger logger = LoggerFactory.getLogger(BaseSnlClassifyController.class);

	@Autowired
	private BaseSnlClassifyService baseSnlClassifyService;

}