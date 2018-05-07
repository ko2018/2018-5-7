package com.talent.front.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.constant.YesOrNo;
import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.BaseResRatiotypeDto;
import com.talent.front.entity.BaseResRatiotype;
import com.talent.front.service.BaseResRatiotypeService;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源类型控制类
 */
@RestController
@RequestMapping("resRatiotype")
public class BaseResRatiotypeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseResRatiotypeController.class);

    @Autowired
    private BaseResRatiotypeService baseResRatiotypeService;

    @RequestMapping(value = "list")
    public Map<String, Object> list() {
        PageObject pageObject = getPageObject();
        PageResult<BaseResRatiotypeDto> pageResult = baseResRatiotypeService.pageListDto(pageObject);
        return setDataValue(pageResult);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(BaseResRatiotype baseResRatiotype) throws Exception {
        if (StringUtils.isEmpty(baseResRatiotype.getResratioTypename())) {
            throw new GlobalException(ErrorCode.REQUEST_PARAM_ERROR);
        }
        UserCurrent.getInstance().setUserId(baseResRatiotype);
        baseResRatiotype.setResratioTypeid(UUIDUtil.getUUID(32));
        Date date = new Date();
        baseResRatiotype.setAddTime(date);
        baseResRatiotype.setUpdateTime(date);
        baseResRatiotypeService.insertSelective(baseResRatiotype);
        return setDataValue(null);
    }

    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public Map<String, Object> detail(BaseResRatiotype baseResRatiotype) throws Exception {
        if (StringUtils.isEmpty(baseResRatiotype.getResratioTypeid()))
            throw new GlobalException(ErrorCode.REQUEST_PARAM_ERROR);
        BaseResRatiotypeDto baseResRatiotypeDto = baseResRatiotypeService
                .selectDtoByPrimaryKey(baseResRatiotype.getResratioTypeid());
        if (baseResRatiotypeDto != null) {
            return setDataValue(baseResRatiotypeDto);
        } else {
            throw new GlobalException(ErrorCode.DATA_ACCESS_ERROR);
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Map<String, Object> update(BaseResRatiotype baseResRatiotype) throws Exception {
        if (StringUtils.isEmpty(baseResRatiotype.getResratioTypeid()))
            throw new GlobalException(ErrorCode.REQUEST_PARAM_ERROR);
        Date date = new Date();
        baseResRatiotype.setUpdateTime(date);
        baseResRatiotypeService.updateResRatiotypeDto(baseResRatiotype);
        return setDataValue(null);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String, Object> delete(BaseResRatiotype baseResRatiotype) throws Exception {
        if (StringUtils.isEmpty(baseResRatiotype.getResratioTypeid()))
            throw new GlobalException(ErrorCode.REQUEST_PARAM_ERROR);
        BaseResRatiotypeDto baseResRatiotypeDto = baseResRatiotypeService
                .selectDtoByPrimaryKey(baseResRatiotype.getResratioTypeid());
        if (baseResRatiotypeDto != null) {
            baseResRatiotypeDto.setDeleteStatus(YesOrNo.Y.name());
            BaseResRatiotype tmp = baseResRatiotypeService.updateDtoByPrimaryKeySelective(baseResRatiotypeDto);
            return setDataValue(tmp);
        } else {
            throw new GlobalException(ErrorCode.UPDATE_DATA_ERROR);
        }
    }
}