package com.talent.front.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.model.PageObject;
import com.talent.front.constant.CommonConstant;
import com.talent.front.service.BaseHotInfoTypeService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息类型表控制类
 */
@RestController
@RequestMapping("hotInfoType")
public class BaseHotInfoTypeController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(BaseHotInfoTypeController.class);

	@Autowired
	private BaseHotInfoTypeService baseHotInfoTypeService;

	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list() throws Exception
    {
        PageObject pageObject = getPageObject();
        pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
        return setDataValue(baseHotInfoTypeService.pageListDto(pageObject));
    }
	
}