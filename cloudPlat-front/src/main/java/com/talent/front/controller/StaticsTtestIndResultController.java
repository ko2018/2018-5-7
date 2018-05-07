package com.talent.front.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsTtestIndDto;
import com.talent.front.dto.StaticsTtestIndResultDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.StaticsTtestIndResult;
import com.talent.front.service.StaticsTtestIndResultService;
import com.talent.front.util.security.UserCurrent;

import io.netty.util.internal.StringUtil;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述： 独立T检验结果控制类
 */
@RestController
@RequestMapping("ttestIndRs")
public class StaticsTtestIndResultController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestIndResultController.class);

	@Autowired
	private StaticsTtestIndResultService staticsTtestIndResultService;
	@RequestMapping(value = "getResult")
	public Map<String, Object> getResult(String uuID) throws GlobalException {
		PageObject pageObject = getPageObject();
		SysUserDto sysUserDto = UserCurrent.getInstance().getSysUserDto();
		if(sysUserDto == null) {
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		String userID = sysUserDto.getUserId();
	
		if (StringUtil.isNullOrEmpty(userID)) {
			logger.error("userID is null");
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		
		if (StringUtil.isNullOrEmpty(uuID)) {
			logger.error("uuID is null");
			throw new GlobalException(ErrorCode.TTEST_IND_PARM_ERROR);
		}
		pageObject.getQueryCondition().put("sUuid", uuID);
		PageResult<StaticsTtestIndResultDto> pageResult = staticsTtestIndResultService.pageListDto(pageObject);
		logger.info("userID=" + userID);
		return setDataValue(pageResult);
	}
	
	
}