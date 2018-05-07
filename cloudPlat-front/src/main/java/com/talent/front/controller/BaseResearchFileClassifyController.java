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
import com.talent.front.service.BaseResearchFileClassifyService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档类型控制类
 */
@RestController
@RequestMapping("researchFileClassify")
public class BaseResearchFileClassifyController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(BaseResearchFileClassifyController.class);

	@Autowired
	private BaseResearchFileClassifyService baseResearchFileClassifyService;
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list()
    {
	    PageObject pageObject = getPageObject();
	    pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
        return setDataValue(baseResearchFileClassifyService.pageListDto(pageObject));
    }
	
}