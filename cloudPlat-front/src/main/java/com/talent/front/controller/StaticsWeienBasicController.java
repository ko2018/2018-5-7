package com.talent.front.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.front.entity.StaticsWeienSearchCondition;
import com.talent.front.entity.StaticsweienSearchResultSet;
import com.talent.front.service.StaticsWeienBasicService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-12 <br/>
 * 描述：控制类
 */
@RestController
@RequestMapping("venn")
public class StaticsWeienBasicController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(StaticsWeienBasicController.class);

	@Autowired
	private StaticsWeienBasicService staticsWeienBasicService;
	
	@RequestMapping(value = "vennStat")
	public Map<String, Object> scanHD(StaticsWeienSearchCondition condition) throws GlobalException {
		
		List<StaticsweienSearchResultSet> resultMaps = staticsWeienBasicService.countDis(condition);
		try {
			return setDataValue(resultMaps);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(ErrorCode.VENN_ERROR);
		}
	}

}