package com.talent.dcs.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.base.constant.YesOrNo;
import com.talent.base.database.BaseDao;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.base.service.BaseServiceImpl;
import com.talent.base.util.CommonUtil;
import com.talent.base.util.JacksonUtil;
import com.talent.base.util.UUIDUtil;
import com.talent.dcs.constant.ValueTypeEnum;
import com.talent.dcs.dao.BaseDataCleanDao;
import com.talent.dcs.dao.BaseDataRawDao;
import com.talent.dcs.dao.BaseDataStdDao;
import com.talent.dcs.dao.BaseDocDefValueDao;
import com.talent.dcs.dao.BaseDocLegalDao;
import com.talent.dcs.dao.BaseDocValueDao;
import com.talent.dcs.dto.BaseDataCleanDto;
import com.talent.dcs.dto.BaseDataRawDto;
import com.talent.dcs.dto.BaseDataStdDto;
import com.talent.dcs.dto.BaseDocDefValueDto;
import com.talent.dcs.dto.BaseDocLegalDto;
import com.talent.dcs.dto.BaseDocValueDto;
import com.talent.dcs.dto.BaseSnlRuleValueDto;
import com.talent.dcs.entity.BaseDataClean;
import com.talent.dcs.entity.BaseDataStd;
import com.talent.dcs.entity.BaseDocLegal;
import com.talent.dcs.service.BaseDataStdService;
import com.talent.dcs.service.BaseDataUpdateService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：标准数据表服务实现类
 */
@Service
public class BaseDataStdServiceImpl extends BaseServiceImpl<BaseDataStd> implements BaseDataStdService {
    private static final Logger logger = LoggerFactory.getLogger(BaseDataStdServiceImpl.class);

    @Value("${default.static.dictid.examdate}")
    private int examdate;

    @Value("${default.static.dictid.birthdate}")
    private int birthdate;

    @Autowired
    private BaseDataStdDao baseDataStdDao;

    @Autowired
    private BaseDataCleanDao baseDataCleanDao;

    @Autowired
    private BaseDocValueDao baseDocValueDao;

    @Autowired
    private BaseDocDefValueDao baseDocDefValueDao;

    @Autowired
    private BaseDataRawDao baseDataRawDao;

    @Autowired
    private BaseDocLegalDao baseDocLegalDao;

    @Autowired
    private BaseDataUpdateService baseDataUpdateService;

    @Override
    public BaseDao<BaseDataStd> getBaseDao() {
        return this.baseDataStdDao;
    }

    @Override
    public BaseDataStdDto selectDtoByPrimaryKey(String datastdId) {
        return this.baseDataStdDao.selectDtoByPrimaryKey(datastdId);
    }

    @Override
    public BaseDataStdDto updateDtoByPrimaryKeySelective(BaseDataStd baseDataStd) {
        this.baseDataStdDao.updateByPrimaryKeySelective(baseDataStd);
        return this.baseDataStdDao.selectDtoByPrimaryKey(baseDataStd.getDatastdId());
    }

    @Override
    public PageResult<BaseDataStdDto> pageListDto(PageObject pageObject) {
        long totalCount = this.baseDataStdDao.pageCount(pageObject);
        PageResult<BaseDataStdDto> pageResult = new PageResult<BaseDataStdDto>();
        pageResult.setTotalCount(totalCount);
        pageResult.setCurPage(pageObject.getCurPage());
        if (totalCount > 0) {
            List<BaseDataStdDto> baseDataStdDtoList = ((BaseDataStdServiceImpl) AopContext.currentProxy())
                    .pageListCache(pageObject);
            pageResult.setQueryResult(baseDataStdDtoList);
        }
        return pageResult;
    }

    @Override
    // @CacheSpeList(value = "pageList", key =
    // "#pageObject.sortBy+'_'+#pageObject.sortDir")
    public List<BaseDataStdDto> pageListCache(PageObject pageObject) {
        return this.baseDataStdDao.pageListDto(pageObject);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void cleanData(BaseDataRawDto baseDataRawDto, Map<Integer, BaseSnlRuleValueDto> mapClean,
            Map<Integer, BaseSnlRuleValueDto> mapCleanDef, String docId) {
        Map<String, String> all_data = new HashMap<String, String>(); // 所有值
        // Map<String, String> legal_data = new HashMap<String, String>(); //
        // 合法值
        Map<String, String> legal_data_no = new HashMap<String, String>(); // 不合法值

        Date data = new Date();
        String date_Object = baseDataRawDto.getDataObject();
        Map mapTypes = JacksonUtil.readValue(date_Object, Map.class);
        StringBuffer sb = new StringBuffer("");
        all_data.putAll(mapTypes);

        // 过滤已经清洗过的数据
        BaseDataStdDto old = baseDataStdDao.getDtoByAllKey(baseDataRawDto.getUserCode(),
                baseDataRawDto.getInstitutionId(), baseDataRawDto.getCheckCode());
        if (old != null) {
            String old_data = old.getDataObject();
            if (!StringUtils.isEmpty(old_data)) {
                Map old_map = JacksonUtil.readValue(old_data, Map.class);
                all_data.putAll(old_map);
                if (old_map.size() > 0) {
                    Iterator<String> iter = mapTypes.keySet().iterator();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        if (old_map.containsKey(key)) {
                            iter.remove();
                        }
                    }
                }

            }
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy MM dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        for (Object obj : mapTypes.keySet()) {
            String value = String.valueOf(mapTypes.get(obj));
            logger.info("obj为：" + obj + "值为：" + value);
            int key = Integer.parseInt(obj.toString().replace("_", "").trim());

            boolean isDef = false; // 是否是通用不合法值
            // 得到判断通用不合法词(通用不合法值不入库)
            if (CommonUtil.replaceBlank(value).equals("")) {
                isDef = true;
                value = " ";
            } else {
                if (mapCleanDef.size() > 0) {
                    BaseSnlRuleValueDto bsrvd = mapCleanDef.get(key);
                    if (bsrvd != null) {
                        String keyWord = bsrvd.getKeyWord();
                        if (!StringUtils.isEmpty(keyWord)) {
                            // [已检, 未检, 未查, 不查, 拒查, 拒检, 结果自带, *报告]
                            String[] values = keyWord.split("\\|\\|");
                            String value_tmp = value.replace("#", "");
                            for (int i = 0; i < values.length; i++) {
                                if (values[i].startsWith("*")) {
                                    if (value_tmp.endsWith(values[i].replace("*", ""))) {
                                        isDef = true;
                                        value = value_tmp;
                                        break;
                                    }
                                } else if (values[i].endsWith("*")) {
                                    if (value_tmp.startsWith(values[i].replace("*", ""))) {
                                        isDef = true;
                                        value = value_tmp;
                                        break;
                                    }
                                } else {
                                    String value_tmp_tmp = value_tmp.replace(values[i], "");
                                    if (StringUtils.isEmpty(value_tmp_tmp)) {
                                        isDef = true;
                                        value = values[i];
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (isDef) { // 如果是通用不合法值
                // TODO 是否需要记录不合法值率
                BaseDocLegal baseDocLegal = new BaseDocLegal();
                baseDocLegal.setDictId(key);
                baseDocLegal.setDocId(docId);
                BaseDocLegalDto baseDocLegalDto = baseDocLegalDao.getDtoByDictIdAndDocId(key, docId);
                if (baseDocLegalDto != null) { // 空值统计（不合法值加1）
                    baseDocLegal.setDocvalueId(baseDocLegalDto.getDocvalueId());
                    baseDocLegal.setCountNolegal(baseDocLegalDto.getCountNolegal() + 1);
                    baseDocLegal.setUpdateTime(data);
                    baseDocLegalDao.updateByPrimaryKeySelective(baseDocLegal);
                } else {
                    logger.info("空值统计 错误源数据里没有统计合法值的记录：dictId:" + key + "docId：" + docId);
                }

                BaseDocDefValueDto baseDocDefValueDto = baseDocDefValueDao.getDtoByDictIdAndDocId(key, docId, value);
                if (baseDocDefValueDto != null) {
                    baseDocDefValueDto.setDocdefvalueNum(baseDocDefValueDto.getDocdefvalueNum() + 1);
                    baseDocDefValueDto.setUpdateTime(data);
                    baseDocDefValueDao.updateByPrimaryKeySelective(baseDocDefValueDto);
                } else {
                    baseDocDefValueDto = new BaseDocDefValueDto();
                    baseDocDefValueDto.setDocdefvalueId(UUIDUtil.getUUID(32));
                    baseDocDefValueDto.setDictId(key);
                    baseDocDefValueDto.setDocId(docId);
                    baseDocDefValueDto.setDocdefvalueValue(value);
                    baseDocDefValueDto.setDocdefvalueNum(1);
                    baseDocDefValueDto.setUpdateTime(data);
                    baseDocDefValueDto.setAddTime(data);
                    baseDocDefValueDao.insertSelective(baseDocDefValueDto);
                }
                continue;
            }

            boolean legal = false; // 是否合法值
            boolean valueNull = false; // 是否是空值

            if (!StringUtils.isEmpty(value)) {
                BaseSnlRuleValueDto baseSnlRuleValueDto = mapClean.get(key);
                if (baseSnlRuleValueDto != null && !StringUtils.isEmpty(baseSnlRuleValueDto.getValuetypeId())
                        && !baseSnlRuleValueDto.getValuetypeId().equals(ValueTypeEnum.UNDO.getCode())) {
                    if (baseSnlRuleValueDto.getValuetypeId().equals(ValueTypeEnum.NUM.getCode())) { // 数值型
                        if (CommonUtil.isNumeric(value)) {
                            double v = Double.parseDouble(value);
                            Double lower = baseSnlRuleValueDto.getLowerLimit();
                            Double upper = baseSnlRuleValueDto.getUpperLimit();
                            if (lower != null && upper != null) {
                                if (v >= lower && v <= upper) {
                                    legal = true;
                                }
                            } else {// TODO 上下线值异常，表为合法值
                                legal = true;
                            }
                        }
                    } else if (baseSnlRuleValueDto.getValuetypeId().equals(ValueTypeEnum.ENUM.getCode())) {
                        String reg = baseSnlRuleValueDto.getKeyWord();
                        if (!StringUtils.isEmpty(reg)) {
                            String[] regs = reg.split(",");
                            for (int i = 0; i < regs.length; i++) {
                                if (value.equals(regs[i])) {
                                    legal = true;
                                    break;
                                }
                            }
                        } else {
                            legal = true;
                            logger.info("枚举值异常 为空：dictId:" + key + "docId：" + docId);
                        }
                    } else if (baseSnlRuleValueDto.getValuetypeId().equals(ValueTypeEnum.TEXT.getCode())) {
                        legal = true; // TODO文本类型，默认合法
                    }
                } else if (key == examdate || key == birthdate) { // 对基础数据时间格式的数据做格式化处理
                    try {
                        if (value.indexOf("-") > -1) {
                            value = sdf1.format(sdf3.parse(value));
                        } else if (value.indexOf(" ") > -1) {
                            value = sdf1.format(sdf2.parse(value));
                        } else {
                            value = sdf1.format(sdf1.parse(value));
                        }
                        legal = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else { // TODO 是其他类型默认合法
                    legal = true;
                }
            } else {
                valueNull = true;
            }

            if (!valueNull) { // TODO 是否考虑空值,如果考虑，空值合法性怎么定义
                if (legal) {
                    BaseDocLegal baseDocLegal = new BaseDocLegal();
                    baseDocLegal.setDictId(key);
                    baseDocLegal.setDocId(docId);
                    BaseDocLegalDto baseDocLegalDto = baseDocLegalDao.getDtoByDictIdAndDocId(key, docId);
                    if (baseDocLegalDto != null) { // 空值统计（合法值加1）
                        baseDocLegal.setDocvalueId(baseDocLegalDto.getDocvalueId());
                        baseDocLegal.setCountLegal(baseDocLegalDto.getCountLegal() + 1);
                        baseDocLegal.setUpdateTime(data);
                        baseDocLegalDao.updateByPrimaryKeySelective(baseDocLegal);
                    } else {
                        logger.info("空值统计 错误源数据里没有统计合法值的记录：dictId:" + key + "docId：" + docId);
                    }
                } else {// 不合法值
                    BaseDocLegal baseDocLegal = new BaseDocLegal();
                    baseDocLegal.setDictId(key);
                    baseDocLegal.setDocId(docId);
                    BaseDocLegalDto baseDocLegalDto = baseDocLegalDao.getDtoByDictIdAndDocId(key, docId);
                    if (baseDocLegalDto != null) { // 空值统计（不合法值加1）
                        baseDocLegal.setDocvalueId(baseDocLegalDto.getDocvalueId());
                        baseDocLegal.setCountNolegal(baseDocLegalDto.getCountNolegal() + 1);
                        baseDocLegal.setUpdateTime(data);
                        baseDocLegalDao.updateByPrimaryKeySelective(baseDocLegal);
                    } else {
                        logger.info("空值统计 错误源数据里没有统计合法值的记录：dictId:" + key + "docId：" + docId);
                    }

                    legal_data_no.put("_" + key, value);

                    if (sb.toString().equals("")) {
                        sb.append("_" + key);
                    } else {
                        sb.append(",").append("_" + key);
                    }

                }

                // 不合法值入库 统计(合法值也要记录)
                BaseDocValueDto baseDocValueDto = baseDocValueDao.getDtoByDictIdAndDocId(key, docId, value);
                if (baseDocValueDto != null) {
                    baseDocValueDto.setDocvalueNum(baseDocValueDto.getDocvalueNum() + 1);
                    baseDocValueDto.setUpdateTime(data);
                    baseDocValueDao.updateByPrimaryKeySelective(baseDocValueDto);
                } else {
                    baseDocValueDto = new BaseDocValueDto();
                    baseDocValueDto.setDocvalueId(UUIDUtil.getUUID(32));
                    baseDocValueDto.setDictId(key);
                    baseDocValueDto.setDocId(docId);
                    baseDocValueDto.setDocvalueValue(value);
                    baseDocValueDto.setDocvalueNum(1);
                    baseDocValueDto.setUpdateTime(data);
                    baseDocValueDto.setAddTime(data);
                    if (legal) {
                        baseDocValueDto.setIsLegal(YesOrNo.Y.name());
                    }
                    baseDocValueDao.insertSelective(baseDocValueDto);
                }
            }

        }
        if (sb.toString().equals("")) { // 值全部合法
            if (old == null) {
                BaseDataStdDto baseDataStdDto = new BaseDataStdDto();
                baseDataStdDto.setUserCode(baseDataRawDto.getUserCode());
                baseDataStdDto.setInstitutionId(baseDataRawDto.getInstitutionId());
                baseDataStdDto.setCheckCode(baseDataRawDto.getCheckCode());
                baseDataStdDto.setDocId(docId);
                baseDataStdDto.setDataObject(baseDataRawDto.getDataObject());
                baseDataStdDto.setCreator(baseDataRawDto.getCreator());
                baseDataStdDto.setAddTime(data);
                baseDataStdDto.setUpdateTime(data);
                baseDataStdDto.setDatastdId(UUIDUtil.getUUID(32));
                baseDataStdDao.insertSelective(baseDataStdDto);
                baseDataUpdateService.addBaseDataUpdate(baseDataStdDto, 0);
            } else {
                old.setDataObject(JacksonUtil.toJSon(all_data));
                old.setUpdateTime(data);
                baseDataStdDao.updateByPrimaryKeySelective(old);
                baseDataUpdateService.addBaseDataUpdate(old, 0);
            }

            // 标准数据进入更新表

        } else {
            // 在所有值中过滤掉不合法值就是所有合法值
            if (all_data.size() > 0) {
                Iterator<String> iter = all_data.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    if (legal_data_no.containsKey(key)) {
                        iter.remove();
                    }
                }
            }

            // 保存合法的值
            if (old == null) {
                BaseDataStdDto baseDataStdDto = new BaseDataStdDto();
                baseDataStdDto.setUserCode(baseDataRawDto.getUserCode());
                baseDataStdDto.setInstitutionId(baseDataRawDto.getInstitutionId());
                baseDataStdDto.setCheckCode(baseDataRawDto.getCheckCode());
                baseDataStdDto.setDocId(baseDataRawDto.getDocId());
                baseDataStdDto.setDataObject(JacksonUtil.toJSon(all_data));
                baseDataStdDto.setCreator(baseDataRawDto.getCreator());
                baseDataStdDto.setAddTime(data);
                baseDataStdDto.setUpdateTime(data);
                baseDataStdDto.setDatastdId(UUIDUtil.getUUID(32));
                baseDataStdDao.insertSelective(baseDataStdDto);
                baseDataUpdateService.addBaseDataUpdate(baseDataStdDto, 0);
            } else {
                old.setDataObject(JacksonUtil.toJSon(all_data));
                old.setUpdateTime(data);
                baseDataStdDao.updateByPrimaryKeySelective(old);
                baseDataUpdateService.addBaseDataUpdate(old, 0);
            }

            BaseDataCleanDto baseDataCleanDto = baseDataCleanDao.getDtoByAllKey(baseDataRawDto.getUserCode(),
                    baseDataRawDto.getInstitutionId(), baseDataRawDto.getCheckCode(), null);
            if (baseDataCleanDto == null) {
                baseDataCleanDto = new BaseDataCleanDto();
                baseDataCleanDto.setUserCode(baseDataRawDto.getUserCode());
                baseDataCleanDto.setInstitutionId(baseDataRawDto.getInstitutionId());
                baseDataCleanDto.setCheckCode(baseDataRawDto.getCheckCode());
                baseDataCleanDto.setDocId(docId);
                baseDataCleanDto.setDataObject(JacksonUtil.toJSon(legal_data_no));
                baseDataCleanDto.setCreator(baseDataRawDto.getCreator());
                baseDataCleanDto.setErrorFlag(sb.toString());
                baseDataCleanDto.setCreator(baseDataRawDto.getCreator());
                baseDataCleanDto.setAddTime(data);
                baseDataCleanDto.setUpdateTime(data);
                baseDataCleanDto.setDatacleanId(UUIDUtil.getUUID(32));
                baseDataCleanDao.insertSelective(baseDataCleanDto);
            } else {
                String date_clean_old = baseDataCleanDto.getDataObject();
                Map map_clean_old = JacksonUtil.readValue(date_clean_old, Map.class);
                if (map_clean_old.size() > 0) {
                    legal_data_no.putAll(map_clean_old);
                    String errorStr = baseDataCleanDto.getErrorFlag();
                    if (!StringUtils.isEmpty(errorStr)) {
                        if (sb.toString().equals("")) {
                            sb.append(errorStr);
                        } else {
                            sb.append(",").append(errorStr);
                        }
                    }
                }

                // 判断docId是否已经存在表中(baseDataClean表中的docId字段存入多个docId)
                boolean isExist = false;
                String docId_tmp = baseDataCleanDto.getDocId();
                if (!StringUtils.isEmpty(docId_tmp)) {
                    String[] docIds = docId_tmp.split(",");
                    for (int i = 0; i < docIds.length; i++) {
                        if (docIds[i].equals(docId)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        baseDataCleanDto.setDocId(docId_tmp + "," + docId);
                    }
                } else {
                    baseDataCleanDto.setDocId(docId);
                }
                baseDataCleanDto.setDataObject(JacksonUtil.toJSon(legal_data_no));
                baseDataCleanDto.setUpdateTime(data);
                baseDataCleanDao.updateByPrimaryKeySelective(baseDataCleanDto);
            }
        }

        // 更新原数据状态
        baseDataRawDto.setIsClean(YesOrNo.Y.name());
        baseDataRawDto.setUpdateTime(data);
        baseDataRawDao.updateByPrimaryKeySelective(baseDataRawDto);

    }

    @Override
    public BaseDataStdDto getDtoByAllKey(String userCode, String institutionId, String checkCode) {
        return this.baseDataStdDao.getDtoByAllKey(userCode, institutionId, checkCode);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void addBaseDataStdDto(BaseDataClean baseDataClean) {
        Date data = new Date();
        BaseDataStdDto baseDataStdDto = baseDataStdDao.getDtoByAllKey(baseDataClean.getUserCode(),
                baseDataClean.getInstitutionId(), baseDataClean.getCheckCode());
        if (baseDataStdDto == null) {
            baseDataStdDto = new BaseDataStdDto();
            baseDataStdDto.setUserCode(baseDataClean.getUserCode());
            baseDataStdDto.setInstitutionId(baseDataClean.getInstitutionId());
            baseDataStdDto.setCheckCode(baseDataClean.getCheckCode());
            baseDataStdDto.setDocId(baseDataClean.getDocId());
            baseDataStdDto.setDataObject(baseDataClean.getDataObject());
            baseDataStdDto.setCreator(baseDataClean.getCreator());
            baseDataStdDto.setAddTime(data);
            baseDataStdDto.setUpdateTime(data);
            baseDataStdDto.setDatastdId(UUIDUtil.getUUID(32));
            baseDataStdDao.insertSelective(baseDataStdDto);
        }
        baseDataClean.setDeleteStatus(YesOrNo.Y.name());
        baseDataClean.setUpdateTime(data);
        baseDataCleanDao.updateByPrimaryKeySelective(baseDataClean);
    }

}