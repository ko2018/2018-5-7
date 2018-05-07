package com.talent.front.scheduled;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.talent.base.constant.YesOrNo;
import com.talent.base.entity.PhysicalExaminationRecord;
import com.talent.base.model.PageObject;
import com.talent.base.util.JacksonUtil;
import com.talent.base.util.UUIDUtil;
import com.talent.front.constant.CommonConstant;
import com.talent.front.dto.BaseDataRawDto;
import com.talent.front.dto.BaseDocDto;
import com.talent.front.dto.BaseInstitutionSnlDto;
import com.talent.front.dto.BaseInstitutionSnlVersionDto;
import com.talent.front.dto.BaseSnlRuleValueDto;
import com.talent.front.entity.BaseDocLegal;
import com.talent.front.entity.BaseInstitutionSnl;
import com.talent.front.service.BaseDataRawService;
import com.talent.front.service.BaseDataStdService;
import com.talent.front.service.BaseDocLegalService;
import com.talent.front.service.BaseDocService;
import com.talent.front.service.BaseInstitutionSnlService;
import com.talent.front.service.BaseInstitutionSnlVersionService;
import com.talent.front.service.BaseSnlRuleValueService;
import com.talent.front.util.POIUtil;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Value("${default.file.url}")
	String fileUrl;

	@Autowired
	private BaseDocService baseDocService;

	@Autowired
	private BaseInstitutionSnlVersionService baseInstitutionSnlVersionService;

	@Autowired
	private BaseInstitutionSnlService baseInstitutionSnlService;

	@Autowired
	private BaseDataRawService baseDataRawService;

	@Autowired
	private BaseSnlRuleValueService baseSnlRuleValueService;

	@Autowired
	private BaseDataStdService baseDataStdService;

	@Autowired
	private BaseDocLegalService baseDocLegalService;

	// 文档完成标准化映射
	// @Scheduled(fixedDelay = 1000 * 50)
	public void docSnlStatistics() {
		PageObject po = new PageObject();
		po.setPageSize(3000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isMapping", "N");
		po.setQueryCondition(map);
		List<BaseDocDto> baseDocDtos = baseDocService.pageListDto(po).getQueryResult();
		logger.info("需要属性匹配的文档数：" + baseDocDtos.size());
		for (BaseDocDto baseDocDto : baseDocDtos) {
			logger.info("开始进行属性映射：文档名" + baseDocDto.getDocName());
			if (baseDocDto.getDoctypeName().equals("临床数据")) {
				// 文件中列名有可能重复导致匹配出问题，暂时使用上传版本，选择版本号的方法。
				// docStdStatisticsLin(baseDocDto);
			}
		}
	}

	// 文档数据存入原始数据表
	// @Scheduled(fixedDelay = 1000 * 50)
	public void docPersistStatistics() {
		PageObject po = new PageObject();
		po.setPageSize(3000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isMapping", "Y");
		map.put("isPersistence", "N");
		po.setQueryCondition(map);
		List<BaseDocDto> baseDocDtos = baseDocService.pageListDto(po).getQueryResult();
		logger.info("需要持久化的文档数：" + baseDocDtos.size());
		for (BaseDocDto baseDocDto : baseDocDtos) {
			logger.info("开始进行源数据持久化：文档名" + baseDocDto.getDocName());
			if (baseDocDto.getDoctypeName().equals("临床数据")) {
				docStdStatisticsPersist(baseDocDto);
			}
		}
	}

	// 原始数据表清洗保存到标准数据表
	// @Scheduled(fixedDelay = 1000 * 50)
	public void docStdStatistics() {
		PageObject po = new PageObject();
		po.setPageSize(3000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isClean", "N");
		po.setQueryCondition(map);
		List<BaseDataRawDto> baseDataRawDtos = baseDataRawService.pageListDto(po).getQueryResult();
		logger.info("此次处理的原始数据数：" + baseDataRawDtos.size());
		map = new HashMap<String, Object>();
		map.put("isLegitimate", CommonConstant.Is.YES);
		po.setQueryCondition(map);
		List<BaseSnlRuleValueDto> baseSnlRuleValueDtos = baseSnlRuleValueService.pageListDto(po).getQueryResult();
		// dictid对应合法值规则,,, 跑的过程中对应关系被删怎么办。。。
		Map<Integer, BaseSnlRuleValueDto> mapClean = new HashMap<Integer, BaseSnlRuleValueDto>();
		for (BaseSnlRuleValueDto baseSnlRuleValueDto : baseSnlRuleValueDtos) {
			mapClean.put(baseSnlRuleValueDto.getDictId(), baseSnlRuleValueDto);
		}

		for (BaseDataRawDto baseDataRawDto : baseDataRawDtos) {
			logger.info("开始进行原始数据清洗, ID:" + baseDataRawDto.getDatarawId());
			baseDataStdService.cleanData(baseDataRawDto, mapClean);
			logger.info("清洗完成, ID:" + baseDataRawDto.getDatarawId());
		}

	}

	/**
	 * 临床数据
	 * 
	 * @param baseDocDto
	 */
	public void docStdStatisticsLin(BaseDocDto baseDocDto) {
		String uuid = UUIDUtil.getUUID(32);
		List<BaseInstitutionSnl> list = new ArrayList<BaseInstitutionSnl>();
		Workbook wb = null;
		InputStream is = null;
		try {
			String path = fileUrl + baseDocDto.getDocPath();
			// group1/M00/00/00/wKgA51o41TWAU2JcAAElBwkE2Ag36.xlsx
			logger.info("文档地址:" + path);

			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3 * 60 * 1000);
			is = conn.getInputStream();
			wb = WorkbookFactory.create(is);

			// wb = POIUtil.readExcel2Workbook(file.getAbsolutePath());
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			if (rowNum < 2) { // 标题占2行
				logger.info("文件数据错误，行数:" + (rowNum + 1));
				return;
			}
			baseDocDto.setDocCount(rowNum - 1);
			// 获得总列数
			int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();
			Map<String, Integer> dictidAndCode = baseInstitutionSnlVersionService.getDictidAndCode();
			logger.info("版本文件列数:" + coloumNum);
			for (int i = 0; i < coloumNum; i++) {
				BaseInstitutionSnlDto baseInstitutionSnl = new BaseInstitutionSnlDto();
				baseInstitutionSnl.setDocCname(POIUtil.getCellValue(sheet.getRow(1).getCell(i)));
				baseInstitutionSnl.setDocCvalue(i);
				// baseInstitutionSnl.setSnlCode(POIUtil.getCellValue(sheet.getRow(2).getCell(i)));
				// baseInstitutionSnl.setSnlName(POIUtil.getCellValue(sheet.getRow(3).getCell(i)));
				// 错误，，，，要匹配dictid
				Integer dictId = dictidAndCode.get(baseInstitutionSnl.getSnlCode());
				if (dictId != null) {
					baseInstitutionSnl.setDictId(dictId);
				} else {
					logger.info("dictId错误： code:" + baseInstitutionSnl.getSnlCode());
					return;
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

			String institutionSnlVersionIdTmp = baseInstitutionSnlVersionService.versionIsExist(list,
					baseDocDto.getInstitutionId());
			logger.info("已经存在的版本ID:" + institutionSnlVersionIdTmp);
			if (institutionSnlVersionIdTmp != null) {
				baseDocDto.setInstitutionSnlVersionId(institutionSnlVersionIdTmp);
				baseDocDto.setIsMapping(YesOrNo.Y.name());
			} else {
				if (list.size() > 0) {
					baseInstitutionSnlService.batchInsert(list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				wb.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		BaseInstitutionSnlVersionDto baseInstitutionSnlVersionDto = new BaseInstitutionSnlVersionDto();
		baseInstitutionSnlVersionDto.setInstitutionSnlVersionId(uuid);
		Date date = new Date();
		baseInstitutionSnlVersionDto.setAddTime(date);
		baseInstitutionSnlVersionDto.setUpdateTime(date);
		// baseInstitutionSnlVersionDto.setVersionPath(versionPath);
		baseInstitutionSnlVersionDto.setFieldCount(list.size());
		baseInstitutionSnlVersionDto.setInstitutionId(baseDocDto.getInstitutionId());
		baseInstitutionSnlVersionDto.setVersionName(baseDocDto.getDocName());
		baseInstitutionSnlVersionDto.setIsMapping(YesOrNo.Y.name());
		baseInstitutionSnlVersionService.insertSelective(baseInstitutionSnlVersionDto);

		baseDocService.updateDtoByPrimaryKeySelective(baseDocDto);
		logger.info("属性映射完成：文档名" + baseDocDto.getDocName());
	}

	/**
	 * 临床数据持久化
	 * 
	 * @param baseDocDto
	 */
	public void docStdStatisticsPersist(BaseDocDto baseDocDto) {
		Date date = new Date();
		String institutionSnlVersionId = baseDocDto.getInstitutionSnlVersionId();
		if (StringUtils.isEmpty(institutionSnlVersionId)) {
			logger.info("属性映射版本号为空。。。。。。");
			return;
		}
		Map<Integer, BaseInstitutionSnlDto> valueAndInstitutionSnl = baseInstitutionSnlVersionService
				.getValueAndInstitutionSnl(institutionSnlVersionId);

		String uuid = UUIDUtil.getUUID(32);
		List<BaseInstitutionSnl> list = new ArrayList<BaseInstitutionSnl>();
		Workbook wb = null;
		InputStream is = null;
		try {
			String path = fileUrl + baseDocDto.getDocPath();
			// group1/M00/00/00/wKgA51o41TWAU2JcAAElBwkE2Ag36.xlsx
			logger.info("文档地址:" + path);

			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3 * 60 * 1000);
			is = conn.getInputStream();
			wb = WorkbookFactory.create(is);

			// wb = POIUtil.readExcel2Workbook(file.getAbsolutePath());
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			int rowNum_fact = 0;
			logger.info("文件，行数:" + (rowNum + 1));
			if (rowNum < 2) { // 标题占2行
				logger.info("文件数据错误，行数:" + (rowNum + 1));
				return;
			}
			baseDocDto.setDocCount(rowNum - 1);
			// 获得总列数
			int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();
			logger.info("列数:" + coloumNum);

			// 得到身份编号，体检号
			String userCode = "";
			String checkCode = "";
			int userCode_value = -1; // 第几列是身份编码
			int checkCode_value = -1; // 第几列是体检号
			for (int i = 0; i < coloumNum; i++) {
				String valueStr = POIUtil.getCellValue(sheet.getRow(1).getCell(i));
				if (valueStr.equals("体检编号")) {
					checkCode_value = i;
				}
				if (valueStr.equals("姓名")) {
					userCode_value = i;
				}
				if (checkCode_value > -1 && userCode_value > -1)
					break;
			}

			for (int i = 2; i < rowNum + 1; i++) {
				if (StringUtils.isEmpty(POIUtil.getCellValue(sheet.getRow(i).getCell(0)))) { // 用第一列判断行为空？
					rowNum_fact = i - 2;
					break;
				}
				System.out.println(i);
				PhysicalExaminationRecord physicalExaminationRecordDto = new PhysicalExaminationRecord();
				for (int j = 0; j < coloumNum; j++) {
					BaseInstitutionSnlDto baseInstitutionSnlDto = valueAndInstitutionSnl.get(j);
					if (baseInstitutionSnlDto != null) {
						int name = baseInstitutionSnlDto.getDictId();
						String value = POIUtil.getCellValue(sheet.getRow(i).getCell(j));
						if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
							objectToValue("_" + name, value, physicalExaminationRecordDto);
							if (userCode_value == j)
								userCode = value;
							if (checkCode_value == j)
								checkCode = value;
						} else {
							logger.info("有空值       行:" + i + "  列 = " + j);
							logger.info("有空值       name:" + name + "  value = " + value);
						}

						if (i == 2) {// 在第一行中 插入空值记录表
							BaseDocLegal baseDocLegal = new BaseDocLegal();
							baseDocLegal.setDocvalueId(UUIDUtil.getUUID(32));
							baseDocLegal.setDictId(baseInstitutionSnlDto.getDictId());
							baseDocLegal.setDocId(baseDocDto.getDocId());
							baseDocLegal.setCountAll(rowNum - 1);
							baseDocLegal.setAddTime(date);
							baseDocLegal.setUpdateTime(date);
							baseDocLegalService.addBaseDocLegal(baseDocLegal);
						}
					} else {
						logger.info("没有查到列号对应的标准术语对用关系     列号：" + j);
					}
				}
				String json = JacksonUtil.toJSon(physicalExaminationRecordDto);
				logger.info(json);
				PhysicalExaminationRecord tmp = JacksonUtil.readValue(json, PhysicalExaminationRecord.class);
				logger.info("AAAAAAAAAAAAAAAA：" + tmp.toString());

				BaseDataRawDto baseDataRawDto = new BaseDataRawDto();
				baseDataRawDto.setCheckCode(checkCode);
				baseDataRawDto.setUserCode(userCode);
				baseDataRawDto.setInstitutionId(baseDocDto.getInstitutionId());
				baseDataRawDto.setDocId(baseDocDto.getDocId());
				baseDataRawDto.setDataObject(json);
				baseDataRawDto.setAddTime(date);
				baseDataRawDto.setUpdateTime(date);
				baseDataRawDto.setDatarawId(UUIDUtil.getUUID(32));
				baseDataRawService.addBaseDataRawDto(baseDataRawDto);
			}

			if (rowNum_fact > 0 && rowNum_fact != rowNum - 1) { // 最终行数错误,更新空值记录表
				baseDocLegalService.updateByCountAll(rowNum_fact, baseDocDto.getDocId());
			}

			baseDocDto.setIsPersistence(YesOrNo.Y.name());
			baseDocDto.setUpdateTime(date);
			baseDocService.updateDtoByPrimaryKeySelective(baseDocDto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				wb.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		logger.info("原始数据持久化成功：文档名" + baseDocDto.getDocName());
	}

	/**
	 * 根据属性名插入属性值
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @param object
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public void objectToValue(String name, String value, PhysicalExaminationRecord object) throws SecurityException,
			InvocationTargetException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException {
		Class cla = object.getClass();
		// 第一个参数写的是方法名,第二个\第三个\...写的是方法参数列表中参数的类型
		Method method = cla.getMethod("set" + name, String.class);
		method.invoke(object, new Object[] { value });
		// 得到类对象
		// if (object instanceof PhysicalExaminationRecordDto) {
		// object = (PhysicalExaminationRecordDto) object;
		// }

		// Field[] fs = object.getClass().getDeclaredFields();
		// for (int i = 0; i < fs.length; i++) {
		// Field f = fs[i];
		// f.setAccessible(true); // 设置些属性是可以访问的
		// Object val = f.get(object);// 得到此属性的值
		// if (f.getName().equals(name)) {
		// logger.info("name:" + f.getName() + " value = " + val);
		// String type = f.getType().toString();// 得到此属性的类型
		// if (type.endsWith("String")) {
		// logger.info(f.getType() + "\t是String");
		// f.set(object, value); // 给属性设值
		// } else if (type.endsWith("int") || type.endsWith("Integer")) {
		// System.out.println(f.getType() + "\t是int");
		// f.set(object, 12); // 给属性设值
		// } else {
		// System.out.println(f.getType() + "\t");
		// }
		// }
		// }
	}

}
