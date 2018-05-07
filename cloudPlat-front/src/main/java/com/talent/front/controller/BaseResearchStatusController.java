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

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.util.UUIDUtil;
import com.talent.front.constant.CommonConstant;
import com.talent.front.dto.BaseResearchStatusDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseResearch;
import com.talent.front.service.BaseResearchService;
import com.talent.front.service.BaseResearchStatusService;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题状态表控制类
 */
@RestController
@RequestMapping("researchStatus")
public class BaseResearchStatusController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseResearchStatusController.class);

    @Autowired
    private BaseResearchStatusService baseResearchStatusService;

    @Autowired
    private BaseResearchService baseResearchService;

    private SysUserDto getUser() {
        return UserCurrent.getInstance().getSysUserDto();
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list() throws Exception {
        PageObject pageObject = getPageObject();
        pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
        BaseResearch baseResearch = this.baseResearchService
                .selectByPrimaryKey((String) pageObject.getQueryCondition().get("researchId"));
        if (baseResearch == null) {
            throw new GlobalException(ErrorCode.RESEARCH_NOT_EXIST);
        }
        PageResult<BaseResearchStatusDto> dto = baseResearchStatusService.pageListDto(pageObject);

        if (dto.getQueryResult() != null && dto.getQueryResult().size() > 1) {
            PageObject page = new PageObject();
            page.setPageSize(10000);
            page.getQueryCondition().put("researchId", pageObject.getQueryCondition().get("researchId"));
            List<BaseResearchStatusDto> dtos = baseResearchStatusService.pageListDto(page).getQueryResult();
            dto.getQueryResult().stream().filter(d -> d.getSeq() > 0)
                    .forEach(t -> t.setLastStatus(getLastStauts(t, dtos)));
        }

        return setDataValue(dto);
    }

    private String getLastStauts(BaseResearchStatusDto dto, List<BaseResearchStatusDto> dtos) {
        String result = "";
        if (dtos != null) {
            for (BaseResearchStatusDto statusDto : dtos) {
                if (statusDto.getResearchId().equals(dto.getResearchId())
                        && dto.getSeq().equals(statusDto.getSeq() + 1)) {
                    result = statusDto.getStatus();
                    break;
                }
            }
        }
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(BaseResearchStatusDto baseResearchStatusDto) throws Exception {
        if (baseResearchStatusDto == null || StringUtils.isEmpty(baseResearchStatusDto.getResearchId())
                || StringUtils.isEmpty(baseResearchStatusDto.getStatus())) {
            throw new GlobalException(ErrorCode.RESEARCH_STATUS_NULL);
        }

        BaseResearch baseResearch = baseResearchService.selectByPrimaryKey(baseResearchStatusDto.getResearchId());
        if (baseResearch == null) {
            throw new GlobalException(ErrorCode.RESEARCH_NOT_EXIST);
        }

        Integer seqCurrent = baseResearchStatusService.getResearchCurrentSeq(baseResearchStatusDto.getResearchId());
        baseResearchStatusDto.setId(UUIDUtil.getUUID(32));
        baseResearchStatusDto.setOperatorId(getUser().getUserId());
        baseResearchStatusDto.setOperatorName(getUser().getRealName());
        baseResearchStatusDto.setOperateTime(new Date());

        baseResearchStatusDto.setCreator(getUser().getUserId());
        baseResearchStatusDto.setAddTime(new Date());
        baseResearchStatusDto.setUpdateUser(getUser().getUserId());
        baseResearchStatusDto.setUpdateTime(new Date());

        baseResearchStatusDto.setSeq(seqCurrent == null ? 0 : seqCurrent + 1);

        int result = baseResearchStatusService.addBaseResearchStatus(baseResearchStatusDto);
        // int result =
        // baseResearchStatusService.insertSelective(baseResearchStatusDto);
        if (result < 1) {
            throw new GlobalException(ErrorCode.SNL_DELETE_ERR);
        }

        return getDefinedMap();
    }
}