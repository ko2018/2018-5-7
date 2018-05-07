package com.talent.front.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.util.UUIDUtil;
import com.talent.front.constant.CommonConstant;
import com.talent.front.dto.StaticsCustomCrowdDto;
import com.talent.front.dto.StaticsMathInOutDataDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.service.StaticsMathInOutDataService;
import com.talent.front.util.security.UserCurrent;

import io.netty.util.internal.StringUtil;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-05 <br/>
 * 描述：数学分析控制类
 */
@RestController
@RequestMapping("math")
public class StaticsMathInOutDataController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(StaticsMathInOutDataController.class);

    @Autowired
    private StaticsMathInOutDataService staticsMathInOutDataService;

    @RequestMapping(value = "list")
    public Map<String, Object> list() throws GlobalException {
        PageObject pageObject = getPageObject();
        SysUserDto sysUserDto = UserCurrent.getInstance().getSysUserDto();
        if (sysUserDto == null) {
            throw new GlobalException(ErrorCode.USER_DENY);
        }
        String userID = sysUserDto.getUserId();
        if (StringUtil.isNullOrEmpty(userID)) {
            logger.error("userID is null");
            throw new GlobalException(ErrorCode.USER_DENY);
        }
        pageObject.getQueryCondition().put("deleteStatus", "0");
        pageObject.getQueryCondition().put("creator", userID);
        PageResult<StaticsMathInOutDataDto> pageResult = staticsMathInOutDataService.pageListDto(pageObject);
        return setDataValue(pageResult);
    }
    

    @RequestMapping(value = "getRs", method = RequestMethod.POST)
    public Map<String, Object> getRs(String id) throws GlobalException {

    	StaticsMathInOutDataDto dto = staticsMathInOutDataService.selectDtoByPrimaryKey(id);
    	if(dto != null) {
    		dto.setDataOutMaps();
    		return setDataValue(dto);
    	}
    	else
    		throw new GlobalException(ErrorCode.STATICS_MATH_ERROR_NO_FOUND);

    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String, Object> delete(String id) throws GlobalException {
        String userID = UserCurrent.getInstance().getSysUserDto().getUserId();
        StaticsMathInOutDataDto record = new StaticsMathInOutDataDto();
        record.setDeleteStatus("1");
        record.setId(id);
        record.setCreator(userID);
        record.setUpdateTime(new Date());
        //更新
        if(staticsMathInOutDataService.updateByPrimaryKeySelective(record) > 0)
            return getDefinedMap();
        else
            throw new GlobalException(ErrorCode.STATICS_MATH_ERROR_NO_FOUND);

    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Map<String, Object> create(StaticsMathInOutDataDto record) throws GlobalException {
    	 SysUserDto sysUserDto = UserCurrent.getInstance().getSysUserDto();
         if (sysUserDto == null) {
             throw new GlobalException(ErrorCode.USER_DENY);
         }
         String userID = sysUserDto.getUserId();
         if (StringUtil.isNullOrEmpty(userID)) {
             logger.error("userID is null");
             throw new GlobalException(ErrorCode.USER_DENY);
         }
        record.setDeleteStatus("0");
        record.setCreator(userID);
        record.setAddTime(new Date());
        record.setUpdateTime(new Date());
        staticsMathInOutDataService.insert(record);
        
        return getDefinedMap();

    }
    
    

}