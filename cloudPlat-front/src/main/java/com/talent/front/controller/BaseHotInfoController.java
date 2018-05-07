package com.talent.front.controller;

import java.util.Arrays;
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
import com.talent.front.constant.HotInfoType;
import com.talent.front.dto.BaseHotInfoDto;
import com.talent.front.entity.BaseHotInfo;
import com.talent.front.service.BaseFileService;
import com.talent.front.service.BaseHotInfoService;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息表控制类
 */
@RestController
@RequestMapping("hotInfo")
public class BaseHotInfoController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseHotInfoController.class);

    @Autowired
    private BaseHotInfoService baseHotInfoService;

    @Autowired
    private BaseFileService baseFileService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list() throws Exception {
        PageObject pageObject = getPageObject();
        pageObject.getQueryCondition().put("deleteStatus", YesOrNo.N.name());
        PageResult<BaseHotInfoDto> pageResult = baseHotInfoService.pageListDto(pageObject);
        List<BaseHotInfoDto> hotInfos = pageResult.getQueryResult();
        if (hotInfos != null && hotInfos.size() > 0) {
            hotInfos.stream().forEach(h -> {
                if (!StringUtils.isEmpty(h.getInfoAttach())) {
                    h.setBaseFiles(baseFileService.getBaseFilesByIds(Arrays.asList(h.getInfoAttach().split(","))));
                }
            });
        }
        return setDataValue(pageResult);
    }

    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public Map<String, Object> detail(String hotInfoId) throws Exception {
        BaseHotInfoDto baseHotInfoDto = baseHotInfoService.selectDtoByPrimaryKey(hotInfoId);
        if (baseHotInfoDto != null && !StringUtils.isEmpty(baseHotInfoDto.getInfoAttach())) {
            baseHotInfoDto.setBaseFiles(
                    baseFileService.getBaseFilesByIds(Arrays.asList(baseHotInfoDto.getInfoAttach().split(","))));
        }
        return setDataValue(baseHotInfoDto);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String, Object> delete(String hotInfoId) throws Exception {
        BaseHotInfo baseHotInfo = baseHotInfoService.selectByPrimaryKey(hotInfoId);
        if (baseHotInfo == null) {
            throw new GlobalException(ErrorCode.HOT_INFO_NOT_EXIST);
        }
        baseHotInfo.setUpdateTime(new Date());
        baseHotInfo.setUpdateUser(UserCurrent.getInstance().getSysUserDto().getUserId());
        baseHotInfo.setDeleteStatus(YesOrNo.Y.name());

        BaseHotInfoDto baseHotInfoDto = baseHotInfoService.updateDtoByPrimaryKeySelective(baseHotInfo);
        if (baseHotInfoDto == null) {
            throw new GlobalException(ErrorCode.HOT_INFO_DELETE_ERR);
        }
        return getDefinedMap();
    }

    @RequestMapping(value = "batchDelete", method = RequestMethod.POST)
    public Map<String, Object> batchDelete(String batchIds) throws Exception {
        if (StringUtils.isEmpty(batchIds)) {
            throw new GlobalException(ErrorCode.HOT_INFO_NULL);
        }

        List<String> ids = Arrays.asList(batchIds.split(","));
        List<BaseHotInfo> baseHotInfos = baseHotInfoService.selectDtoByIds(ids);
        if (baseHotInfos == null) {
            throw new GlobalException(ErrorCode.HOT_INFO_NOT_EXIST);
        }

        baseHotInfos.stream().forEach(b -> {
            b.setUpdateTime(new Date());
            b.setUpdateUser(UserCurrent.getInstance().getSysUserDto().getUserId());
            b.setDeleteStatus(YesOrNo.Y.name());
        });

        int result = baseHotInfoService.batchUpdateSelective(baseHotInfos);
        if (result == 0) {
            throw new GlobalException(ErrorCode.HOT_INFO_DELETE_ERR);
        }
        return getDefinedMap();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(BaseHotInfoDto baseHotInfoDto) throws Exception {
        if (baseHotInfoDto == null || StringUtils.isEmpty(baseHotInfoDto.getInfoTitle())
                || StringUtils.isEmpty(baseHotInfoDto.getInfoTypeId())
                || StringUtils.isEmpty(baseHotInfoDto.getInfoTypeName())) {
            throw new GlobalException(ErrorCode.HOT_INFO_NULL);
        }

        if (HotInfoType.NEWS.getCode().equals(baseHotInfoDto.getInfoTypeId())) {
            if (StringUtils.isEmpty(baseHotInfoDto.getInfoContent())
                    && StringUtils.isEmpty(baseHotInfoDto.getInfoOriginUrl())) {
                throw new GlobalException(ErrorCode.HOT_INFO_NULL);
            }
        } else {
            if (StringUtils.isEmpty(baseHotInfoDto.getInfoAttach())) {
                throw new GlobalException(ErrorCode.HOT_INFO_NULL);
            }
        }

        baseHotInfoDto.setCreator(UserCurrent.getInstance().getSysUserDto().getUserId());
        baseHotInfoDto.setAddTime(new Date());
        baseHotInfoDto.setUpdateUser(UserCurrent.getInstance().getSysUserDto().getUserId());
        baseHotInfoDto.setUpdateTime(new Date());

        int result = baseHotInfoService.insertSelective(baseHotInfoDto);
        if (result < 1) {
            throw new GlobalException(ErrorCode.HOT_INFO_ADD_ERR);
        }
        return getDefinedMap();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Map<String, Object> update(BaseHotInfoDto baseHotInfoDto) throws Exception {
        if (baseHotInfoDto == null || StringUtils.isEmpty(baseHotInfoDto.getHotInfoId())
                || StringUtils.isEmpty(baseHotInfoDto.getInfoTitle())
                || StringUtils.isEmpty(baseHotInfoDto.getInfoTypeId())
                || StringUtils.isEmpty(baseHotInfoDto.getInfoTypeName())) {
            throw new GlobalException(ErrorCode.HOT_INFO_NULL);
        }

        if (HotInfoType.NEWS.getCode().equals(baseHotInfoDto.getInfoTypeId())) {
            if (StringUtils.isEmpty(baseHotInfoDto.getInfoContent())
                    && StringUtils.isEmpty(baseHotInfoDto.getInfoOriginUrl())) {
                throw new GlobalException(ErrorCode.HOT_INFO_NULL);
            }
        } else {
            if (StringUtils.isEmpty(baseHotInfoDto.getInfoAttach())) {
                throw new GlobalException(ErrorCode.HOT_INFO_NULL);
            }
        }

        baseHotInfoDto.setUpdateUser(UserCurrent.getInstance().getSysUserDto().getUserId());
        baseHotInfoDto.setUpdateTime(new Date());
        BaseHotInfoDto result = baseHotInfoService.updateDtoByPrimaryKeySelective(baseHotInfoDto);
        if (result == null) {
            throw new GlobalException(ErrorCode.HOT_INFO_ADD_ERR);
        }
        return getDefinedMap();
    }
}