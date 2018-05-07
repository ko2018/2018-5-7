package com.talent.front.controller;

import java.util.Date;
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
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.StaticsTtestIndGroupDto;
import com.talent.front.dto.StaticsTtestSampleDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.StaticsTtestIndGroup;
import com.talent.front.entity.StaticsTtestSample;
import com.talent.front.service.StaticsTtestIndGroupService;
import com.talent.front.util.NumberValidationUtils;
import com.talent.front.util.security.UserCurrent;

import io.netty.util.internal.StringUtil;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述：独立T检验分组控制类
 */
@RestController
@RequestMapping("ttestIndG")
public class StaticsTtestIndGroupController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestIndGroupController.class);

	@Autowired
	private StaticsTtestIndGroupService staticsTtestIndGroupService;
	
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
		PageResult<StaticsTtestIndGroupDto> pageResult = staticsTtestIndGroupService.pageListDto(pageObject);
		logger.info("userID=" + userID);
		return setDataValue(pageResult);
	}
	
	@RequestMapping(value = "create")
	public Map<String, Object> create(StaticsTtestIndGroupDto record) throws GlobalException {
		SysUserDto sysUserDto = UserCurrent.getInstance().getSysUserDto();
		if(sysUserDto == null) {
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		String userID = sysUserDto.getUserId();
		if (StringUtil.isNullOrEmpty(userID)) {
			logger.error("userID is null");
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		
		if (StringUtil.isNullOrEmpty(record.getGUnitName())) {
			logger.error("getGUnitName is null");
			throw new GlobalException(ErrorCode.TTEST_IND_G_PARM_ERROR);
		}
		
		if (StringUtil.isNullOrEmpty(record.getGUnitParm())) {
			logger.error("getGUnitParm is null");
			throw new GlobalException(ErrorCode.TTEST_IND_G_PARM_ERROR);
		}
		if (StringUtil.isNullOrEmpty(record.getGUnitDict())) {
			logger.error("getGUnitDict is "+record.getGUnitDict());
			throw new GlobalException(ErrorCode.TTEST_IND_G_PARM_ERROR);
		}
		
		
		record.setUserId(userID);
		String uuID = UUIDUtil.getUUID(32);
		record.setId(uuID);
		record.setDeleteStatus(SAVE_FLAG);
		record.setAddTime(new Date());
		staticsTtestIndGroupService.insert(record);
		logger.info("userID=" + userID);
		return this.getDefinedMap();
	}
	
	@RequestMapping(value = "update")
	public Map<String, Object> update(StaticsTtestIndGroup record) throws GlobalException {
		SysUserDto sysUserDto = UserCurrent.getInstance().getSysUserDto();
		if(sysUserDto == null) {
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		String userID = sysUserDto.getUserId();
		if (StringUtil.isNullOrEmpty(userID)) {
			logger.error("userID is null");
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		
		if (StringUtil.isNullOrEmpty(record.getGUnitName())) {
			logger.error("getGUnitName is null");
			throw new GlobalException(ErrorCode.TTEST_IND_G_PARM_ERROR);
		}
		
		if (StringUtil.isNullOrEmpty(record.getGUnitParm())) {
			logger.error("getGUnitParm is null");
			throw new GlobalException(ErrorCode.TTEST_IND_G_PARM_ERROR);
		}
		if (StringUtil.isNullOrEmpty(record.getGUnitDict())) {
			logger.error("getGUnitDict is "+record.getGUnitDict());
			throw new GlobalException(ErrorCode.TTEST_IND_G_PARM_ERROR);
		}
		if (StringUtil.isNullOrEmpty(record.getId())) {
			logger.error("getGUnitDict is "+record.getGUnitDict());
			throw new GlobalException(ErrorCode.TTEST_IND_G_PARM_ERROR);
		}
		
		record.setUserId(userID);
		staticsTtestIndGroupService.updateByPrimaryKeySelective(record);
		logger.info("userID=" + userID);
		return this.getDefinedMap();
	}
	
	@RequestMapping(value = "get")
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
			
			logger.info("userID=" + userID);
			return this.setDataValue(staticsTtestIndGroupService.selectDtoByPrimaryKey(uuID));
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
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
		
		if (StringUtil.isNullOrEmpty(uuID)) {
			logger.error("uuID is null");
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_ERROR);
		}
		
		try {
			StaticsTtestIndGroupDto record = new StaticsTtestIndGroupDto();
			record.setUserId(userID);
			record.setId(uuID);
			record.setDeleteStatus(DELETE_FLAG);
			staticsTtestIndGroupService.updateByPrimaryKeySelective(record);
			logger.info("userID=" + userID);
			return this.getDefinedMap();
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
	}
	
	private static final String DELETE_FLAG = "1", SAVE_FLAG = "0";
}