package com.talent.front.controller;

import java.util.Date;
import java.util.List;
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
import com.talent.front.dto.BaseResRatioDto;
import com.talent.front.dto.BaseResRatiotypeDto;
import com.talent.front.entity.BaseResRatio;
import com.talent.front.service.BaseResRatioService;
import com.talent.front.service.BaseResRatiotypeService;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源占比控制类
 */
@RestController
@RequestMapping("resRatio")
public class BaseResRatioController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseResRatioController.class);

    @Autowired
    private BaseResRatioService baseResRatioService;

    @Autowired
    private BaseResRatiotypeService baseResRatiotypeService;

    @RequestMapping(value = "list")
    public Map<String, Object> list() {
        PageObject pageObject = getPageObject();
        PageResult<BaseResRatioDto> pageResult = baseResRatioService.pageListDto(pageObject);
        return setDataValue(pageResult);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(BaseResRatio baseResRatio) throws Exception {
        if (StringUtils.isEmpty(baseResRatio.getResratioName())
                || StringUtils.isEmpty(baseResRatio.getResratioTypeid())) {
            throw new GlobalException(ErrorCode.REQUEST_PARAM_ERROR);
        }
        PageObject pageObject = new PageObject();
        pageObject.setPageSize(Integer.MAX_VALUE);
        Map<String, Object> map = pageObject.getQueryCondition();
        map.put("deleteStatus", "N");
        map.put("resratioTypeid", baseResRatio.getResratioTypeid());
        List<BaseResRatioDto> list = baseResRatioService.pageListDto(pageObject).getQueryResult();
        double all = 0;
        for (BaseResRatioDto baseResRatioDto : list) {
            all = all + baseResRatioDto.getResratioValue();
        }
        if (all + baseResRatio.getResratioValue() > 100) {
            throw new GlobalException(ErrorCode.RESRATIO_VALUE_EXCEED);
        }

        BaseResRatiotypeDto baseResRatiotypeDto = baseResRatiotypeService
                .selectDtoByPrimaryKey(baseResRatio.getResratioTypeid());
        if (baseResRatiotypeDto != null) {
            baseResRatio.setResratioTypename(baseResRatiotypeDto.getResratioTypename());
        }
        UserCurrent.getInstance().setUserId(baseResRatio);
        baseResRatio.setResratioId(UUIDUtil.getUUID(32));
        Date date = new Date();
        baseResRatio.setAddTime(date);
        baseResRatio.setUpdateTime(date);
        baseResRatioService.insertSelective(baseResRatio);
        return setDataValue(null);
    }

    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public Map<String, Object> detail(BaseResRatio baseResRatio) throws Exception {
        if (StringUtils.isEmpty(baseResRatio.getResratioId()))
            throw new GlobalException(ErrorCode.REQUEST_PARAM_ERROR);
        BaseResRatioDto baseResRatioDto = baseResRatioService.selectDtoByPrimaryKey(baseResRatio.getResratioId());
        if (baseResRatioDto != null) {
            return setDataValue(baseResRatioDto);
        } else {
            throw new GlobalException(ErrorCode.DATA_ACCESS_ERROR);
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Map<String, Object> update(BaseResRatio baseResRatio) throws Exception {
        if (StringUtils.isEmpty(baseResRatio.getResratioId()))
            throw new GlobalException(ErrorCode.REQUEST_PARAM_ERROR);

        PageObject pageObject = new PageObject();
        pageObject.setPageSize(Integer.MAX_VALUE);
        Map<String, Object> map = pageObject.getQueryCondition();
        map.put("deleteStatus", "N");
        map.put("resratioTypeid", baseResRatio.getResratioTypeid());
        List<BaseResRatioDto> list = baseResRatioService.pageListDto(pageObject).getQueryResult();
        double all = 0;
        for (BaseResRatioDto baseResRatioDto : list) {
            if (!baseResRatioDto.getResratioId().equals(baseResRatio.getResratioId())) {
                all = all + baseResRatioDto.getResratioValue();
            }
        }
        if (all + baseResRatio.getResratioValue() > 100) {
            throw new GlobalException(ErrorCode.RESRATIO_VALUE_EXCEED);
        }

        Date date = new Date();
        baseResRatio.setUpdateTime(date);
        baseResRatioService.updateDtoByPrimaryKeySelective(baseResRatio);
        return setDataValue(null);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String, Object> delete(BaseResRatio baseResRatio) throws Exception {
        if (StringUtils.isEmpty(baseResRatio.getResratioId()))
            throw new GlobalException(ErrorCode.REQUEST_PARAM_ERROR);
        BaseResRatioDto baseResRatioDto = baseResRatioService.selectDtoByPrimaryKey(baseResRatio.getResratioId());
        if (baseResRatioDto != null) {
            baseResRatioDto.setDeleteStatus(YesOrNo.Y.name());
            BaseResRatio tmp = baseResRatioService.updateDtoByPrimaryKeySelective(baseResRatioDto);
            return setDataValue(tmp);
        } else {
            throw new GlobalException(ErrorCode.UPDATE_DATA_ERROR);
        }
    }

}