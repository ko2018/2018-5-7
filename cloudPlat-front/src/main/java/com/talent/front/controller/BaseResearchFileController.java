package com.talent.front.controller;

import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.util.FileUtil;
import com.talent.front.constant.CommonConstant;
import com.talent.front.dto.BaseResearchFileDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseResearch;
import com.talent.front.entity.BaseResearchFile;
import com.talent.front.service.BaseResearchFileService;
import com.talent.front.service.BaseResearchService;
import com.talent.front.service.SysUserService;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：科研文档控制类
 */
@RestController
@RequestMapping("researchFile")
public class BaseResearchFileController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseResearchFileController.class);

    @Value("${default.file.url}")
    private String pathPrefix;

    @Autowired
    private BaseResearchFileService baseResearchFileService;

    @Autowired
    private BaseResearchService baseResearchService;

    @Autowired
    private SysUserService sysUserService;

    private SysUserDto getUser() {
        return UserCurrent.getInstance().getSysUserDto();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(BaseResearchFileDto baseResearchFileDto) throws Exception {
        if (StringUtils.isEmpty(baseResearchFileDto.getResearchId())
                || StringUtils.isEmpty(baseResearchFileDto.getFileName())
                || StringUtils.isEmpty(baseResearchFileDto.getFilePath())
                || StringUtils.isEmpty(baseResearchFileDto.getFileTag())) {
            throw new GlobalException(ErrorCode.RESEARCH_FILE_NULL);
        }

        BaseResearch baseResearch = this.baseResearchService.selectByPrimaryKey(baseResearchFileDto.getResearchId());
        if (baseResearch == null) {
            throw new GlobalException(ErrorCode.RESEARCH_NOT_EXIST);
        }

        int isSucess = baseResearchFileService.addResearchFile(baseResearchFileDto, getUser().getUserId());
        if (isSucess < 1) {
            throw new GlobalException(ErrorCode.RESEARCH_FILE_UPLOAD_ERR);
        }
        return getDefinedMap();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String, Object> delete(String fileId) throws Exception {
        BaseResearchFile baseResearchFile = baseResearchFileService.selectByPrimaryKey(fileId);
        if (baseResearchFile == null) {
            throw new GlobalException(ErrorCode.RESEARCH_FILE_NOT_EXIST);
        }

        baseResearchFile.setIsDel(CommonConstant.Is.YES);
        baseResearchFile.setUpdateUser(getUser().getUserId());
        baseResearchFile.setUpdateTime(new Date());
        BaseResearchFile result = baseResearchFileService.deleteResearchFile(baseResearchFile);
        if (result == null) {
            throw new GlobalException(ErrorCode.RESEARCH_FILE_DELETE_ERR);
        }
        return getDefinedMap();
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(String fileId, HttpServletResponse response) throws Exception {
        BaseResearchFile baseResearchFile = baseResearchFileService.selectByPrimaryKey(fileId);
        if (baseResearchFile == null) {
            throw new GlobalException(ErrorCode.RESEARCH_FILE_NOT_EXIST);
        }

        int isSucess = baseResearchFileService.downloadResearchFile(baseResearchFile, getUser().getUserId());
        if (isSucess < 1) {
            throw new GlobalException(ErrorCode.RESEARCH_FILE_DOWNLOAD_ERR);
        }

        try {
            response.setContentType("application/vnd.ms-excel");
            String suffix = baseResearchFile.getFilePath()
                    .substring(baseResearchFile.getFilePath().lastIndexOf(".") + 1).toLowerCase();
            response.setHeader("Content-disposition", "attachment;filename="
                    + new String(baseResearchFile.getFileName().getBytes("gb2312"), "ISO8859-1") + "." + suffix);
            response.setCharacterEncoding("UTF-8");
            URL url = new URL(pathPrefix + baseResearchFile.getFilePath());
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3 * 60 * 1000);

            byte[] buf = FileUtil.readInputStream(conn.getInputStream());
            OutputStream ouputStream = response.getOutputStream();
            ouputStream.write(buf);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        PageResult<BaseResearchFileDto> pageResult = baseResearchFileService.pageListDto(pageObject);
        List<BaseResearchFileDto> dtoList = pageResult.getQueryResult();
        if (dtoList != null) {
            SysUserDto user = null;
            for (BaseResearchFileDto dto : dtoList) {
                dto.setFilePath(pathPrefix + dto.getFilePath());
                user = sysUserService.selectDtoByPrimaryKey(dto.getCreator());
                dto.setUserName(user == null ? "" : user.getRealName());
                dto.setInstitutionName(user == null ? ""
                        : (user.getBaseInstitutionDto() == null ? ""
                                : user.getBaseInstitutionDto().getInstitutionName()));
            }
        }
        return setDataValue(pageResult);
    }

}