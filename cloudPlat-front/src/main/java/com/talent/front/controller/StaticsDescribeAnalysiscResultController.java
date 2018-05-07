package com.talent.front.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.StaticsDescribeAnalysiscResultDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.service.StaticsDescribeAnalysiscResultService;
import com.talent.front.util.security.UserCurrent;

import io.netty.util.internal.StringUtil;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析结果控制类
 */
@RestController
@RequestMapping("describeRs")
public class StaticsDescribeAnalysiscResultController  extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticsDescribeAnalysiscResultController.class);

	@Autowired
	private StaticsDescribeAnalysiscResultService staticsDescribeAnalysiscResultService;
	
	@RequestMapping(value = "getResult")
	public Map<String, Object> get(String uuID) throws GlobalException {
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
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_ERROR);
		}

		try {
			PageObject pageObject = getPageObject();
			
			pageObject.getQueryCondition().put("uuid", uuID);
			PageResult<StaticsDescribeAnalysiscResultDto> list = staticsDescribeAnalysiscResultService.pageListDto(pageObject);
			logger.info("userID=" + userID);
			return setDataValue(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
	}

}