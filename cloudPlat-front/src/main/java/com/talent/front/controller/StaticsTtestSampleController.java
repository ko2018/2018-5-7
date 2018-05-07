package com.talent.front.controller;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.StaticsTtestSampleDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.StaticsTtestSample;
import com.talent.front.service.StaticsTtestSampleService;
import com.talent.front.util.NumberValidationUtils;
import com.talent.front.util.security.UserCurrent;

import io.netty.util.internal.StringUtil;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：单样本T检验控制类
 */
@RestController
@RequestMapping("ttestSample")
public class StaticsTtestSampleController  extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestSampleController.class);

	@Autowired
	private StaticsTtestSampleService staticsTtestSampleService;
	
	@RequestMapping(value = "list")
	public Map<String, Object> list() throws GlobalException {
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
		
		pageObject.getQueryCondition().put("deleteStatus", "0");
		pageObject.getQueryCondition().put("userId", userID);
		PageResult<StaticsTtestSampleDto> pageResult = staticsTtestSampleService.pageListDto(pageObject);
		logger.info("userID=" + userID);
		return setDataValue(pageResult);
	}
	
	@RequestMapping(value = "create")
	public Map<String, Object> create(StaticsTtestSampleDto record) throws GlobalException {
		SysUserDto sysUserDto = UserCurrent.getInstance().getSysUserDto();
		if(sysUserDto == null) {
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		String userID = sysUserDto.getUserId();
		if (StringUtil.isNullOrEmpty(userID)) {
			logger.error("userID is null");
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		
		if (StringUtil.isNullOrEmpty(record.getSCrowd())) {
			logger.error("getSCrowd is null");
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
		
		if (StringUtil.isNullOrEmpty(record.getSConfidence())) {
			logger.error("getSConfidence is null");
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
		if (!NumberValidationUtils.isPositiveDecimal(record.getSConfidence())) {
			logger.error("getSConfidence is "+record.getSConfidence());
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
		
		if (StringUtil.isNullOrEmpty(record.getSDicId())) {
			logger.error("getSDicId is null");
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
		
		if (StringUtil.isNullOrEmpty(record.getSExpectVal())) {
			logger.error("getSExpectVal is null");
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
		
		if (StringUtil.isNullOrEmpty(record.getSDicId())) {
			logger.error("getSDicId is null");
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}

		if(!NumberValidationUtils.isPositiveInteger(record.getSDicId())) {
			logger.error("getSDicId is not number"+record.getSDicId());
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
		if (StringUtil.isNullOrEmpty(record.getSExpectFlag())) {
			logger.error("getSExpectFlag is null");
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}

		record.setUserId(userID);
		String uuID = UUIDUtil.getUUID(32);
		record.setId(uuID);
		record.setDeleteStatus(SAVE_FLAG);
		record.setAddTime(new Date());
		staticsTtestSampleService.insert(record);
		logger.info("userID=" + userID);
		return this.getDefinedMap();
	}
	


	
	
	private static final String DELETE_FLAG = "1", SAVE_FLAG = "0";
	@RequestMapping(value = "delete")
	public Map<String, Object> delete(String uuID) throws GlobalException {
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
			StaticsTtestSample record = new StaticsTtestSample();
			record.setUserId(userID);
			record.setId(uuID);
			record.setDeleteStatus(DELETE_FLAG);
			staticsTtestSampleService.updateByPrimaryKeySelective(record);
			logger.info("userID=" + userID);
			return this.getDefinedMap();
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
	}

	

}