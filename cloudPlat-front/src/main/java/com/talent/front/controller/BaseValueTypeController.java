package com.talent.front.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.front.service.BaseValueTypeService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：数据类型表控制类
 */
@RestController
@RequestMapping("valueType")
public class BaseValueTypeController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(BaseValueTypeController.class);

	@Autowired
	private BaseValueTypeService baseValueTypeService;

	
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list() throws Exception
    {
        return setDataValue(baseValueTypeService.pageListDto(getPageObject()));
    }
}