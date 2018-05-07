package com.talent.front.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.front.dto.StaticsPlatformInstitutionDto;
import com.talent.front.entity.StaticsPlatformBase;
import com.talent.front.service.BaseDataStdService;
import com.talent.front.service.BaseDiagService;
import com.talent.front.service.BaseInstitutionService;


/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-06 <br/>
 * 描述：首页平台统计对应表控制类
 */
@RestController
@RequestMapping("home")
public class StaticsPlatformBaseController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticsPlatformBaseController.class);

	@Autowired
	private BaseDataStdService baseDataStdService;

	@Autowired
	private BaseDiagService baseDiagService;
	@Autowired
	private BaseInstitutionService baseInstituService;
	
	@RequestMapping(value = "statics")
	public Map<String, Object> list() {
		StaticsPlatformBase dto = baseDataStdService.pageCountByPid();
//		dto.setStaticsHasDisease(""+baseDiagService.pageCount1());
		dto.setStaticsHasDisease("0");//改成生物样本
		dto.setStaticsOrgans(""+baseInstituService.pageCount());
		dto.set_Id("");
		return setDataValue(dto);
	}
	
	@RequestMapping(value = "map")
	public Map<String, Object> map() {
		return setDataValue(baseDataStdService.pageCountByOrgans());
	}
	
	@RequestMapping(value = "areaID")
	public Map<String, Object> areaID(String areaID) {
		
		List<StaticsPlatformInstitutionDto> list = baseDataStdService.pageCountByOrgansField(areaID);
		if(list.size() > 20) {
			list = list.subList(0, 19);
		}
		return setDataValue(list);
	}
	
}