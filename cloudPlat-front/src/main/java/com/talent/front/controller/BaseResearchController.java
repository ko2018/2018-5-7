package com.talent.front.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.constant.YesOrNo;
import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.constant.CommonConstant;
import com.talent.front.dto.BaseResearchDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseInstitution;
import com.talent.front.entity.BaseResearch;
import com.talent.front.entity.SysUser;
import com.talent.front.service.BaseInstitutionService;
import com.talent.front.service.BaseResearchService;
import com.talent.front.service.SysUserService;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研管理表控制类
 */
@RestController
@RequestMapping("research")
public class BaseResearchController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseResearchController.class);

    @Autowired
    private BaseResearchService baseResearchService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BaseInstitutionService baseInstitutionService;

    private SysUserDto getUser() {
        return UserCurrent.getInstance().getSysUserDto();
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list() {
        PageObject pageObject = getPageObject();
        pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
        PageResult<BaseResearchDto> dto = baseResearchService.pageListDto(pageObject);
        if (dto.getQueryResult() != null) {
            for (BaseResearchDto baseResearchDto : dto.getQueryResult()) {
                BaseInstitution baseInstitution = null;
                for (String insId : baseResearchDto.getMemberInsId()) {
                    baseInstitution = baseInstitutionService.selectByPrimaryKey(insId);
                    if (baseInstitution != null && !StringUtils.isEmpty(baseInstitution.getInstitutionName())
                            && YesOrNo.N.toString().equals(baseInstitution.getDeleteStatus())) {
                        baseResearchDto.getMemberInsName().add(baseInstitution.getInstitutionName());
                    }
                }

                SysUser sysUser = null;
                for (String userId : baseResearchDto.getMemberUserId()) {
                    sysUser = sysUserService.selectByPrimaryKey(userId);
                    if (sysUser != null && YesOrNo.N.toString().equals(sysUser.getDeleteStatus())) {
                        baseResearchDto.getMemberUserName()
                                .add(StringUtils.isEmpty(sysUser.getRealName()) ? sysUser.getUserName()
                                        : sysUser.getRealName());
                    }
                }
            }
        }

        return setDataValue(dto);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(BaseResearchDto baseResearchDto) throws Exception {
        if (StringUtils.isEmpty(baseResearchDto.getResearchName())
                || StringUtils.isEmpty(baseResearchDto.getMajorInsId())
                || StringUtils.isEmpty(baseResearchDto.getMajorUserId())) {
            throw new GlobalException(ErrorCode.RESEARCH_NULL);
        }

        if (!isNameUnique(baseResearchDto)) {
            throw new GlobalException(ErrorCode.RESEARCH_NAME_EXIST);
        }

        int result = baseResearchService.addBaseResearchDto(baseResearchDto, getUser());
        if (result < 1) {
            throw new GlobalException(ErrorCode.RESEARCH_ADD_ERR);
        }
        return getDefinedMap();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String, Object> delete(String researchId) throws Exception {
        BaseResearch baseResearch = baseResearchService.selectByPrimaryKey(researchId);
        if (baseResearch == null) {
            throw new GlobalException(ErrorCode.RESEARCH_NULL);
        }

        baseResearch.setIsDel(CommonConstant.Is.YES);
        baseResearch.setUpdateUser(getUser().getUserId());
        baseResearch.setUpdateTime(new Date());
        BaseResearch result = baseResearchService.updateObjectByPrimaryKeySelective(baseResearch);
        // int result = baseResearchService.deleteByPrimaryKey(researchId);
        if (result == null) {
            throw new GlobalException(ErrorCode.RESEARCH_DELETE_ERR);
        }

        return getDefinedMap();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Map<String, Object> update(BaseResearchDto baseResearchDto) throws Exception {
        if (StringUtils.isEmpty(baseResearchDto.getResearchName())
                || StringUtils.isEmpty(baseResearchDto.getMajorInsId())
                || StringUtils.isEmpty(baseResearchDto.getMajorUserId())) {
            throw new GlobalException(ErrorCode.RESEARCH_NULL);
        }

        BaseResearch baseResearch = baseResearchService.selectByPrimaryKey(baseResearchDto.getResearchId());
        if (baseResearch == null) {
            throw new GlobalException(ErrorCode.RESEARCH_NULL);
        }

        if (!isNameUnique(baseResearchDto)) {
            throw new GlobalException(ErrorCode.RESEARCH_NAME_EXIST);
        }

        baseResearchDto.setUpdateUser(getUser().getUserId());
        baseResearchDto.setUpdateTime(new Date());
        BaseResearch result = baseResearchService.updateBaseResearchDto(baseResearchDto);
        if (result == null) {
            throw new GlobalException(ErrorCode.RESEARCH_UPDATE_ERR);
        }

        return getDefinedMap();
    }

    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public Map<String, Object> detail(String researchId) throws Exception {
        BaseResearchDto baseResearchDto = baseResearchService.selectDtoByPrimaryKey(researchId);
        if (baseResearchDto == null) {
            throw new GlobalException(ErrorCode.RESEARCH_NULL);
        }

        BaseInstitution baseInstitution = null;
        for (String insId : baseResearchDto.getMemberInsId()) {
            baseInstitution = baseInstitutionService.selectByPrimaryKey(insId);
            if (baseInstitution != null && !StringUtils.isEmpty(baseInstitution.getInstitutionName())) {
                baseResearchDto.getMemberInsName().add(baseInstitution.getInstitutionName());
            }
        }

        SysUser sysUser = null;
        for (String userId : baseResearchDto.getMemberUserId()) {
            sysUser = sysUserService.selectByPrimaryKey(userId);
            if (sysUser != null) {
                baseResearchDto.getMemberUserName().add(
                        StringUtils.isEmpty(sysUser.getRealName()) ? sysUser.getUserName() : sysUser.getRealName());
            }
        }

        return setDataValue(baseResearchDto);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder bin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor cust = new CustomDateEditor(sdf, true);
        bin.registerCustomEditor(Date.class, cust);
    }

    @RequestMapping(value = "checkName", method = RequestMethod.POST)
    public Map<String, Object> checkName(BaseResearchDto baseResearchDto) throws Exception {
        if (baseResearchDto == null || StringUtils.isEmpty(baseResearchDto.getResearchName())) {
            throw new GlobalException(ErrorCode.RESEARCH_NULL);
        }

        if (!isNameUnique(baseResearchDto)) {
            throw new GlobalException(ErrorCode.RESEARCH_NAME_EXIST);
        }

        return getDefinedMap();
    }

    private boolean isNameUnique(BaseResearchDto baseResearchDto) {
        boolean result = false;
        PageObject pageObject = new PageObject();
        pageObject.setPageSize(10);
        pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
        pageObject.getQueryCondition().put("researchName", baseResearchDto.getResearchName());
        List<BaseResearch> researchs = baseResearchService.pageList(pageObject).getQueryResult();

        if (researchs == null || researchs.size() == 0) {
            result = true;
        } else if (researchs.size() == 1) {
            if (researchs.get(0).getResearchId().equals(baseResearchDto.getResearchId())
                    && researchs.get(0).getResearchName().equals(baseResearchDto.getResearchName())) {
                result = true;
            }
        }
        return result;
    }
}