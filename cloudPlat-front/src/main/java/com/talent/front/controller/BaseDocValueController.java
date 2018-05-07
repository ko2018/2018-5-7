package com.talent.front.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.BaseDocValueDto;
import com.talent.front.dto.BaseDocValueFileDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseSnlRuleValue;
import com.talent.front.service.BaseDocValueFileService;
import com.talent.front.service.BaseDocValueService;
import com.talent.front.util.POIUtil;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：文档值显示记录表控制类
 */
@RestController
@RequestMapping("docValue")
public class BaseDocValueController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocValueController.class);

    @Autowired
    private BaseDocValueService baseDocValueService;

    @Autowired
    private BaseDocValueFileService baseDocValueFileService;

    private SysUserDto getUser() {
        SysUserDto sud = new SysUserDto();
        sud.setUserId("user_1");
        sud.setInstitutionId("14250e6b72e14233b7bbbad8870329c6");
        sud.setUserName("admin");
        return new SysUserDto();
    }

    @RequestMapping(value = "list")
    public Map<String, Object> list() {
        PageObject pageObject = getPageObject();
        PageResult<BaseDocValueDto> pageResult = baseDocValueService.pageListDto(pageObject);
        return setDataValue(pageResult);
    }

    @RequestMapping(value = "listGroup")
    public Map<String, Object> listGroup(String docId, String dictId, String qryWord) {
        PageObject pageObject = getPageObject();
        pageObject.getQueryCondition().put("docId", docId);
        pageObject.getQueryCondition().put("dictId", dictId);
        PageResult<BaseDocValueDto> pageResult = baseDocValueService.pageListDtoJoinGroupBy(pageObject);

        List<BaseDocValueDto> list = pageResult.getQueryResult();
        for (BaseDocValueDto baseDocValueDto : list) {
            PageObject po = new PageObject();
            po.getQueryCondition().put("dictId", baseDocValueDto.getDictId());
            po.getQueryCondition().put("docId", baseDocValueDto.getDocId());
            po.setPageSize(10);
            po.setOrderByClause("docvalue_num desc");
            baseDocValueDto.setList(baseDocValueService.pageListDtoJoin(po).getQueryResult());
        }

        return setDataValue(pageResult);
    }

    @RequestMapping(value = "listJoin")
    public Map<String, Object> listJoin(String qryWord) {
        PageObject pageObject = getPageObject();
        pageObject.setOrderByClause("docvalue_num desc");
        PageResult<BaseDocValueDto> pageResult = baseDocValueService.pageListDtoJoin(pageObject);

        return setDataValue(pageResult);
    }

    @RequestMapping(value = "listSpe")
    public Map<String, Object> listSpe(String docvalueValues, String docId, int dictId) throws Exception {
        if (StringUtils.isEmpty(docvalueValues) || StringUtils.isEmpty(docId)) {
            throw new GlobalException(ErrorCode.QUERY_ERROE);
        }
        List<String> inParams = Arrays.asList(docvalueValues.split(","));
        return setDataValue(baseDocValueService.pageListDtoJoinSpe(inParams, docId, dictId));
    }

    @RequestMapping(value = "change")
    public Map<String, Object> change(BaseDocValueDto baseDocValueDto) {
        String docvalueId = baseDocValueDto.getDocvalueId();
        String docvalueValue = baseDocValueDto.getDocvalueValue();
        int dictId = baseDocValueDto.getDictId();
        String docvalueValuenew = baseDocValueDto.getDocvalueValuenew();
        String docId = baseDocValueDto.getDocId();

        baseDocValueService.change(docvalueId, docId, dictId, docvalueValue, docvalueValuenew);
        return setDataValue(null);
    }

    @RequestMapping(value = "load")
    public void load(BaseDocValueDto baseDocValueDto, HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("合法值修改");
        sheet.setDefaultColumnWidth(20);
        // sheet.setDefaultRowHeightInPoints(20);

        String[] excelHead = { "唯一码(不能修改)", "文档标识(不能修改)", "属性标识(不能修改)", "术语编码(不能修改)", "属性名称(不能修改)", "合法值范围(不能修改)",
                "非合法值形态(不能修改)", "频次(不能修改)", "需要修改成的合法值" };
        POIUtil.writeExcelTitle(wb, excelHead);

        PageObject pageObject = new PageObject();
        pageObject.setPageSize(Integer.MAX_VALUE);
        pageObject.getQueryCondition().put("dictId", baseDocValueDto.getDictId());
        pageObject.getQueryCondition().put("docId", baseDocValueDto.getDocId());
        pageObject.setOrderByClause("docvalue_num desc");

        int i = 1;
        List<BaseDocValueDto> list = baseDocValueService.pageListDtoJoin(pageObject).getQueryResult();
        for (BaseDocValueDto tmp : list) {
            HSSFRow row = sheet.createRow(i);

            row.createCell((short) 0).setCellValue(tmp.getDocvalueId());
            row.createCell((short) 1).setCellValue(tmp.getDocId());
            row.createCell((short) 2).setCellValue(tmp.getDictId());
            row.createCell((short) 3).setCellValue(tmp.getSnlCode());
            row.createCell((short) 4).setCellValue(tmp.getNameCn());
            row.createCell((short) 5).setCellValue(tmp.getLegalValue());
            row.createCell((short) 6).setCellValue(tmp.getDocvalueValue());
            row.createCell((short) 7).setCellValue(tmp.getDocvalueNum());
            row.createCell((short) 8).setCellValue("");

            i++;
        }
        OutputStream ouputStream = null;
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String("合法值修改".getBytes("gb2312"), "ISO8859-1") + ".xls");
            response.setCharacterEncoding("UTF-8");

            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ouputStream.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "upload")
    public Map<String, Object> upload(BaseDocValueFileDto baseDocValueFileDto) {
        Date date = new Date();
        UserCurrent.getInstance().setUserId(baseDocValueFileDto);
        baseDocValueFileDto.setAddTime(date);
        baseDocValueFileDto.setUpdateTime(date);
        baseDocValueFileDto.setValuefileId(UUIDUtil.getUUID(32));
        baseDocValueFileService.insertSelective(baseDocValueFileDto);
        return setDataValue(null);
    }

    @RequestMapping(value = "graph")
    public Map<String, Object> graph(String docId, BaseSnlRuleValue baseSnlRuleValue) {
        int floor = 5;
        int dictId = baseSnlRuleValue.getDictId();
        Double lowerLimit = baseSnlRuleValue.getLowerLimit();
        Double upperLimit = baseSnlRuleValue.getUpperLimit();
        if (lowerLimit != null && upperLimit != null) {
            double all = upperLimit - lowerLimit;
            if (all > 0) {
                floor = (int) all / 40;
            }
        }
        return setDataValue(baseDocValueService.graph(floor, docId, dictId));
    }

}