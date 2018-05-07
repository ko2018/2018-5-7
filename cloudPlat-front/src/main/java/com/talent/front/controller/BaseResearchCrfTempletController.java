package com.talent.front.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.util.FileUtil;
import com.talent.base.util.UUIDUtil;
import com.talent.front.constant.CommonConstant;
import com.talent.front.dto.BaseResearchCrfTempletDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseResearch;
import com.talent.front.entity.BaseResearchCrfSnl;
import com.talent.front.entity.BaseResearchCrfTemplet;
import com.talent.front.service.BaseResearchCrfTempletService;
import com.talent.front.service.BaseResearchFileLogService;
import com.talent.front.service.BaseResearchService;
import com.talent.front.util.FastdfsFileManagerBoot;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研CRF模板表控制类
 */
@RestController
@RequestMapping("researchCrfTemplet")
public class BaseResearchCrfTempletController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseResearchCrfTempletController.class);

    @Value("${default.file.url}")
    private String pathPrefix;

    @Autowired
    private BaseResearchCrfTempletService baseResearchCrfTempletService;

    @Autowired
    private BaseResearchService baseResearchService;

    @Autowired
    private BaseResearchFileLogService baseResearchFileLogService;

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
        return setDataValue(baseResearchCrfTempletService.pageListDto(pageObject));
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(BaseResearchCrfTempletDto baseResearchCrfTempletDto) throws Exception {
        BaseResearch baseResearch = this.baseResearchService
                .selectByPrimaryKey(baseResearchCrfTempletDto.getResearchId());
        if (baseResearch == null) {
            throw new GlobalException(ErrorCode.RESEARCH_NOT_EXIST);
        }

        if (StringUtils.isEmpty(baseResearchCrfTempletDto.getTempletName())) {
            throw new GlobalException(ErrorCode.RESEARCH_TEMPLET_NULL);
        }

        if (StringUtils.isEmpty(baseResearchCrfTempletDto.getBaseResearchCrfSnlJson())) {
            throw new GlobalException(ErrorCode.RESEARCH_TEMPLET_SNL_NULL);
        }

        baseResearchCrfTempletDto.setBaseResearchCrfSnlList(Arrays.asList(new ObjectMapper()
                .readValue(baseResearchCrfTempletDto.getBaseResearchCrfSnlJson(), BaseResearchCrfSnl[].class)));

        String filePath = createAndUploadCrfTempletFile(baseResearchCrfTempletDto);

        baseResearchCrfTempletDto.setTempletId(UUIDUtil.getUUID(32));
        baseResearchCrfTempletDto.setFilePath(filePath);
        baseResearchCrfTempletDto.setCreator(getUser().getUserId());
        baseResearchCrfTempletDto.setAddTime(new Date());
        baseResearchCrfTempletDto.setUpdateUser(getUser().getUserId());
        baseResearchCrfTempletDto.setUpdateTime(new Date());

        int result = baseResearchCrfTempletService.addBaseResearchCrfTempletDto(baseResearchCrfTempletDto);
        if (result < 1) {
            throw new GlobalException(ErrorCode.RESEARCH_TEMPLET_ADD_ERR);
        }
        return getDefinedMap();
    }

    private String createAndUploadCrfTempletFile(BaseResearchCrfTempletDto baseResearchCrfTempletDto) throws Exception {
        List<BaseResearchCrfSnl> crfSnlList = baseResearchCrfTempletDto.getBaseResearchCrfSnlList();

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet(baseResearchCrfTempletDto.getTempletName());
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell;
            for (int j = 0; j < crfSnlList.size(); j++) {
                crfSnlList.get(j).setColSeq(j);
                cell = row.createCell(j);
                cell.setCellValue(crfSnlList.get(j).getSnlName());
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            return FastdfsFileManagerBoot.getInstance().upload(baseResearchCrfTempletDto.getTempletName(),
                    bos.toByteArray(), "xls", null);
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String, Object> delete(String templetId) throws Exception {
        BaseResearchCrfTemplet baseResearchCrfTemplet = baseResearchCrfTempletService.selectByPrimaryKey(templetId);
        if (baseResearchCrfTemplet == null) {
            throw new GlobalException(ErrorCode.RESEARCH_TEMPLET_NOT_EXIST);
        }

        baseResearchCrfTemplet.setIsDel(CommonConstant.Is.YES);
        baseResearchCrfTemplet.setUpdateUser(getUser().getUserId());
        baseResearchCrfTemplet.setUpdateTime(new Date());

        // BaseResearchCrfTempletDto result =
        // baseResearchCrfTempletService.deleteBaseResearchCrfTempletDto(baseResearchCrfTemplet);
        BaseResearchCrfTempletDto result = baseResearchCrfTempletService
                .updateDtoByPrimaryKeySelective(baseResearchCrfTemplet);
        if (result == null) {
            throw new GlobalException(ErrorCode.RESEARCH_TEMPLET_DELETE_ERR);
        }
        return getDefinedMap();
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public Map<String, Object> download(String templetId, HttpServletResponse response) throws Exception {
        BaseResearchCrfTemplet baseResearchCrfTemplet = baseResearchCrfTempletService.selectByPrimaryKey(templetId);
        if (baseResearchCrfTemplet == null) {
            throw new GlobalException(ErrorCode.RESEARCH_TEMPLET_NOT_EXIST);
        }

        // BaseResearchFileLog baseResearchFileLog = new BaseResearchFileLog();
        // baseResearchFileLog.setLogId(UUIDUtil.getUUID(32));
        // baseResearchFileLog.setResearchId(baseResearchCrfTemplet.getResearchId());
        // baseResearchFileLog.setFileId(baseResearchCrfTemplet.getTempletId());
        // baseResearchFileLog.setOperatorId(getUser().getUserId());
        // baseResearchFileLog.setOperateTime(new Date());
        // baseResearchFileLog.setOperatorType(ResearchConstant.FileOperatorType.DOWNLOAD);
        // baseResearchFileLogService.insertSelective(baseResearchFileLog);

        try {
            response.setContentType("application/vnd.ms-excel");
            String suffix = baseResearchCrfTemplet.getFilePath()
                    .substring(baseResearchCrfTemplet.getFilePath().lastIndexOf(".") + 1).toLowerCase();
            response.setHeader("Content-disposition",
                    "attachment;filename="
                            + new String(baseResearchCrfTemplet.getTempletName().getBytes("gb2312"), "ISO8859-1") + "."
                            + suffix);
            response.setCharacterEncoding("UTF-8");
            URL url = new URL(pathPrefix + baseResearchCrfTemplet.getFilePath());
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
        return getDefinedMap();
    }
}