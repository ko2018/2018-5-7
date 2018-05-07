package com.talent.front.controller;

import java.util.Date;
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
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.StaticsDescribeAnalysiscDto;

import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.StaticsDescribeAnalysisc;
import com.talent.front.service.StaticsDescribeAnalysiscService;
import com.talent.front.util.security.UserCurrent;

import io.netty.util.internal.StringUtil;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-07 <br/>
 * 描述：描述性分析控制类
 */
@RestController
@RequestMapping("describe")
public class StaticsDescribeAnalysiscController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticsDescribeAnalysiscController.class);

	@Autowired
	private StaticsDescribeAnalysiscService staticsDescribeAnalysiscService;
	
	
	@RequestMapping(value = "dlist")
	public Map<String, Object> dlist() throws GlobalException {
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
		
		pageObject.getQueryCondition().put("type", "0");
		pageObject.getQueryCondition().put("deleteStatus", "0");
		pageObject.getQueryCondition().put("userId", userID);
		PageResult<StaticsDescribeAnalysiscDto> pageResult = staticsDescribeAnalysiscService.pageListDto(pageObject);
	
		return setDataValue(pageResult);
	}
	
	@RequestMapping(value = "rlist")
	public Map<String, Object> rlist() throws GlobalException {
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
		
		pageObject.getQueryCondition().put("type", "1");
		pageObject.getQueryCondition().put("deleteStatus", "0");
		pageObject.getQueryCondition().put("userId", userID);
		PageResult<StaticsDescribeAnalysiscDto> pageResult = staticsDescribeAnalysiscService.pageListDto(pageObject);
	
		return setDataValue(pageResult);
	}
	
	private static final String SAVE ="0", DEL = "1";
	@RequestMapping(value = "create")
	public Map<String, Object> create(StaticsDescribeAnalysisc record) throws GlobalException {
	
		SysUserDto sysUserDto = UserCurrent.getInstance().getSysUserDto();
		if(sysUserDto == null) {
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		String userID = sysUserDto.getUserId();
		if (StringUtil.isNullOrEmpty(userID)) {
			logger.error("userID is null");
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		
		if(StringUtil.isNullOrEmpty(record.getCrowdId())) {
			logger.error("getCrowdId is null");
			throw new GlobalException(ErrorCode.DESCRIBE_ANALYSIS_PARM);
		}
		
		if(StringUtil.isNullOrEmpty(record.getDictList())) {
			logger.error("getDictList is null");
			throw new GlobalException(ErrorCode.DESCRIBE_ANALYSIS_PARM);
		}
		
		if(StringUtil.isNullOrEmpty(record.getOptionList())) {
			logger.error("getOptionList is null");
			throw new GlobalException(ErrorCode.DESCRIBE_ANALYSIS_PARM);
		}
		
		
		if(StringUtil.isNullOrEmpty(record.getSort())) {
			logger.error("getSort is null");
			throw new GlobalException(ErrorCode.DESCRIBE_ANALYSIS_PARM);
		}
		
		if(StringUtil.isNullOrEmpty(record.getType())) {
			logger.error("getType is null");
			throw new GlobalException(ErrorCode.DESCRIBE_ANALYSIS_PARM);
		}
		
		if(StringUtil.isNullOrEmpty(record.getGroupid())) {
			logger.error("getGroupid is null");
			throw new GlobalException(ErrorCode.DESCRIBE_ANALYSIS_PARM);
		}
		
		String uuID = UUIDUtil.getUUID(32);
		record.setId(uuID);
		record.setAddTime(new Date());
		record.setDeleteStatus(SAVE);
		record.setUserId(userID);
		staticsDescribeAnalysiscService.insert(record);
	
		return this.getDefinedMap();
	}
	
	
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
		
		StaticsDescribeAnalysisc record = new StaticsDescribeAnalysisc();
		record.setUserId(userID);
		record.setId(uuID);
		record.setDeleteStatus(DEL);
		staticsDescribeAnalysiscService.updateByPrimaryKeySelective(record);
		return this.getDefinedMap();
	
	}


}