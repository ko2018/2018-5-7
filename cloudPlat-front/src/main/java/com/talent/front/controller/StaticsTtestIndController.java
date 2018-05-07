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
import com.talent.front.dto.StaticsTtestIndDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.service.StaticsTtestIndService;
import com.talent.front.util.security.UserCurrent;

import io.netty.util.internal.StringUtil;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-29 <br/>
 * 描述： 独立T检验控制类
 */
@RestController
@RequestMapping("ttestInd")
public class StaticsTtestIndController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(StaticsTtestIndController.class);

	@Autowired
	private StaticsTtestIndService staticsTtestIndService;

	private static final String DELETE_FLAG = "1", SAVE_FLAG = "0";
	
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
		PageResult<StaticsTtestIndDto> pageResult = staticsTtestIndService.pageListDto(pageObject);
		logger.info("userID=" + userID);
		return setDataValue(pageResult);
	}
	
	@RequestMapping(value = "create")
	public Map<String, Object> create(StaticsTtestIndDto record) throws GlobalException {
		SysUserDto sysUserDto = UserCurrent.getInstance().getSysUserDto();
		if(sysUserDto == null) {
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		String userID = sysUserDto.getUserId();
		if (StringUtil.isNullOrEmpty(userID)) {
			logger.error("userID is null");
			throw new GlobalException(ErrorCode.USER_DENY);
		}
		
		if (StringUtil.isNullOrEmpty(record.getSConfidence())) {
			logger.error("getSConfidence is null");
			throw new GlobalException(ErrorCode.TTEST_IND_PARM_ERROR);
		}
		
		if (StringUtil.isNullOrEmpty(record.getSCrowd())) {
			logger.error("getSCrowd is null");
			throw new GlobalException(ErrorCode.TTEST_IND_PARM_ERROR);
		}
		if (StringUtil.isNullOrEmpty(record.getSDictList())) {
			logger.error("getSDictList is "+record.getSDictList());
			throw new GlobalException(ErrorCode.TTEST_IND_PARM_ERROR);
		}
		if (StringUtil.isNullOrEmpty(record.getSExpectFlag())) {
			logger.error("getSExpectFlag is "+record.getSExpectFlag());
			throw new GlobalException(ErrorCode.TTEST_IND_PARM_ERROR);
		}
		if (StringUtil.isNullOrEmpty(record.getSGroupId1())) {
			logger.error("getSGroupId1 is "+record.getSGroupId1());
			throw new GlobalException(ErrorCode.TTEST_IND_PARM_ERROR);
		}
		if (StringUtil.isNullOrEmpty(record.getSGroupId2())) {
			logger.error("getSGroupId2 is "+record.getSGroupId2());
			throw new GlobalException(ErrorCode.TTEST_IND_PARM_ERROR);
		}
		
		
		record.setUserId(userID);
		String uuID = UUIDUtil.getUUID(32);
		record.setId(uuID);
		record.setDeleteStatus(SAVE_FLAG);
		record.setAddTime(new Date());
		staticsTtestIndService.insert(record);
		logger.info("userID=" + userID);
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
		
		if (StringUtil.isNullOrEmpty(uuID)) {
			logger.error("uuID is null");
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_ERROR);
		}
	
		try {
			StaticsTtestIndDto record = new StaticsTtestIndDto();
			record.setUserId(userID);
			record.setId(uuID);
			record.setDeleteStatus(DELETE_FLAG);
			staticsTtestIndService.updateByPrimaryKeySelective(record);
			logger.info("userID=" + userID);
			return this.getDefinedMap();
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.SAMPLE_TTEST_PARM_ERROR);
		}
	}
}