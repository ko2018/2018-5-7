package com.talent.front.controller;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.constant.YesOrNo;
import com.talent.base.controller.BaseController;
import com.talent.base.controller.RMConstants;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.util.FileUtil;
import com.talent.base.util.UUIDUtil;
import com.talent.front.dto.BaseDocValueDto;
import com.talent.front.dto.BaseInstitutionSnlDto;
import com.talent.front.dto.BaseInstitutionSnlVersionDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseInstitutionSnl;
import com.talent.front.service.BaseInstitutionSnlService;
import com.talent.front.service.BaseInstitutionSnlVersionService;
import com.talent.front.service.BaseSnlService;
import com.talent.front.util.POIUtil;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-18 <br/>
 * 描述：机构标准术语版本控制类
 */
@RestController
@RequestMapping("institutionSnlVersion")
public class BaseInstitutionSnlVersionController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseInstitutionSnlVersionController.class);

	@Value("${default.file.url}")
	String fileUrl;

	@Autowired
	private BaseInstitutionSnlVersionService baseInstitutionSnlVersionService;

	@Autowired
	private BaseSnlService baseSnlService;

	@Autowired
	private BaseInstitutionSnlService baseInstitutionSnlService;

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
		PageResult<BaseInstitutionSnlVersionDto> pageResult = baseInstitutionSnlVersionService.pageListDto(pageObject);
		return setDataValue(pageResult);
	}

	@RequestMapping(value = "listJoin")
	public Map<String, Object> listJoin(String qryWord) {
		PageObject pageObject = getPageObject();
		PageResult<BaseInstitutionSnlVersionDto> pageResult = baseInstitutionSnlVersionService
				.pageListDtoJoin(pageObject);
		return setDataValue(pageResult);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Map<String, Object> add(BaseInstitutionSnlVersionDto baseInstitutionSnlVersionDto) throws Exception {
		String uuid = UUIDUtil.getUUID(32);
		baseInstitutionSnlVersionDto.setInstitutionSnlVersionId(uuid);
		String versionPath = baseInstitutionSnlVersionDto.getVersionPath();
		String institutionId = baseInstitutionSnlVersionDto.getInstitutionId();
		// versionPath = "group1/M00/00/00/wKgA51o42aKAP3iIAAC5Q0DnhJE11.xlsx";
		// versionPath = "group1/M00/00/00/wKgA51o6RqWALv1dAAAnmf0ou7014.xlsx";
		// institutionId = "14250e6b72e14233b7bbbad8870329c6";
		// versionPath = "group1/M00/00/00/wKgA51o41TWAU2JcAAElBwkE2Ag36.xlsx";
		if (StringUtils.isEmpty(versionPath)) {
			throw new GlobalException(ErrorCode.INSTITUTIONSNLVERSION_FILE_NULL);
		}
		if (StringUtils.isEmpty(institutionId)) {
			throw new GlobalException(ErrorCode.INSTITUTIONSNLVERSION_INSTITUTIONID_NULL);
		}
		List<BaseInstitutionSnl> list = new ArrayList<BaseInstitutionSnl>();
		Workbook wb = null;
		InputStream is = null;
		try {
			// group1/M00/00/00/wKgA51o41TWAU2JcAAElBwkE2Ag36.xlsx
			logger.info(fileUrl + versionPath);
			// wb = POIUtil.readExcel2WorkbookByUrl(fileUrl + versionPath);

			URL url = new URL(fileUrl + versionPath);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3 * 60 * 1000);
			is = conn.getInputStream();
			wb = WorkbookFactory.create(is);

			// wb = POIUtil.readExcel2Workbook(file.getAbsolutePath());
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			logger.info("版本文件行数:" + rowNum + 1);
			if (rowNum != 3) { // 标准行数4行
				throw new GlobalException(ErrorCode.INSTITUTIONSNLVERSION_FILE_ERROR);
			}
			// 获得总列数
			int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();
			Map<String, Integer> dictidAndCode = baseInstitutionSnlVersionService.getDictidAndCode();
			logger.info("版本文件列数:" + coloumNum);
			for (int i = 0; i < coloumNum; i++) {
				BaseInstitutionSnlDto baseInstitutionSnl = new BaseInstitutionSnlDto();
				baseInstitutionSnl.setDocCname(POIUtil.getCellValue(sheet.getRow(1).getCell(i)));
				baseInstitutionSnl.setDocCvalue(i);
				baseInstitutionSnl.setSnlCode(POIUtil.getCellValue(sheet.getRow(2).getCell(i)));
				baseInstitutionSnl.setSnlName(POIUtil.getCellValue(sheet.getRow(3).getCell(i)));
				Integer dictId = dictidAndCode.get(baseInstitutionSnl.getSnlCode());
				if (dictId != null) {
					baseInstitutionSnl.setDictId(dictId);
				} else {
					logger.info("dictId错误： code:" + baseInstitutionSnl.getSnlCode());
					throw new GlobalException(ErrorCode.INSTITUTIONSNLVERSION_FILE_ERROR);
				}
				baseInstitutionSnl.setDictId(dictidAndCode.get(baseInstitutionSnl.getSnlCode()));
				baseInstitutionSnl.setInstitutionSnlVersionId(uuid);
				Date date = new Date();
				baseInstitutionSnl.setAddTime(date);
				baseInstitutionSnl.setUpdateTime(date);
				baseInstitutionSnl.setInstitutionSnlId(UUIDUtil.getUUID(32));

				// baseSnlService.
				list.add(baseInstitutionSnl);
			}

			String institutionSnlVersionIdTmp = baseInstitutionSnlVersionService.versionIsExist(list, institutionId);
			logger.info("已经存在的版本ID:" + institutionSnlVersionIdTmp);
			BaseInstitutionSnlVersionDto result = baseInstitutionSnlVersionService
					.selectDtoByPrimaryKey(institutionSnlVersionIdTmp);
			if (institutionSnlVersionIdTmp != null) {
				Map<String, Object> map = setDataValue(result);
				map.put(RMConstants.RESPONSE_STATUS, RMConstants.RESPONSE_STATUS_SUCCESS);
				map.put(RMConstants.RESPONSE_CODE, ErrorCode.INSTITUTIONSNLVERSION_FILE_EXIST.getCode());
				map.put(RMConstants.RESPONSE_MESSAGE, ErrorCode.INSTITUTIONSNLVERSION_FILE_EXIST.getMessage());
				return map;
			}

			if (list.size() > 0) {
				// TODO 要求先删除
				baseInstitutionSnlService.batchInsert(list);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			is.close();
			wb.close();
		}

		Date date = new Date();
		baseInstitutionSnlVersionDto.setAddTime(date);
		baseInstitutionSnlVersionDto.setUpdateTime(date);
		baseInstitutionSnlVersionDto.setVersionPath(versionPath);
		baseInstitutionSnlVersionDto.setFieldCount(list.size());
		baseInstitutionSnlVersionDto.setInstitutionId(institutionId);
		baseInstitutionSnlVersionDto.setIsMapping(YesOrNo.Y.name());
		baseInstitutionSnlVersionDto.setCreator(getUser().getUserId());
		baseInstitutionSnlVersionService.insertSelective(baseInstitutionSnlVersionDto);

		return setDataValue(baseInstitutionSnlVersionDto);
	}

}