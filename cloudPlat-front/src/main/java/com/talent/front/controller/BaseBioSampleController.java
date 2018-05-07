package com.talent.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.talent.front.service.BaseBioSampleService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：生物样本表控制类
 */
@Controller
public class BaseBioSampleController {
	private static final Logger logger = LoggerFactory.getLogger(BaseBioSampleController.class);

	@Autowired
	private BaseBioSampleService baseBioSampleService;

}