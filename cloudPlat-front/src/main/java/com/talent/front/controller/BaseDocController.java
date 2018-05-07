package com.talent.front.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
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
import com.talent.front.dto.BaseDocDto;
import com.talent.front.dto.SysUserDto;
import com.talent.front.entity.BaseDoc;
import com.talent.front.service.BaseDocService;
import com.talent.front.util.elasticsearch.PhysicalExaminationRecordEs;
import com.talent.front.util.security.UserCurrent;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：文档表控制类
 */
@RestController
@RequestMapping("doc")
public class BaseDocController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocController.class);

    @Autowired
    private BaseDocService baseDocService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private SysUserDto getUser() {
        return UserCurrent.getInstance().getSysUserDto();
    }

    @RequestMapping(value = "delete")
    public Map<String, Object> delete(String docId) {
        DeleteQuery dq = new DeleteQuery();
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.matchQuery("docId", docId));
        dq.setQuery(qb);
        elasticsearchTemplate.delete(dq, PhysicalExaminationRecordEs.class);

        baseDocService.deleteBaseDoc(docId);
        return setDataValue(null);
    }

    @RequestMapping(value = "list")
    public Map<String, Object> list() {
        PageObject pageObject = getPageObject();
        PageResult<BaseDocDto> pageResult = baseDocService.pageListDto(pageObject);
        return setDataValue(pageResult);
    }

    @RequestMapping(value = "listJoin")
    public Map<String, Object> listJoin(String qryWord) {
        PageObject pageObject = getPageObject();
        PageResult<BaseDocDto> pageResult = baseDocService.pageListDtoJoin(pageObject);
        return setDataValue(pageResult);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(BaseDoc baseDoc) throws Exception {
        baseDoc.setDocId(UUIDUtil.getUUID(32));
        String docPath = baseDoc.getDocPath();
        if (StringUtils.isEmpty(docPath)) {
            throw new GlobalException(ErrorCode.DOC_FILE_NULL);
        }

        String docType = baseDoc.getDoctypeId();
        String snlVersionId = baseDoc.getInstitutionSnlVersionId();
        // 未标准化体检数据必须选择归一化版本！(年前测试去掉)
        // if (docType.equals(DocTypeEnum.ORIGINAL.getCode()) &&
        // StringUtils.isEmpty(snlVersionId)) {
        // throw new GlobalException(ErrorCode.DOC_PARAM_ERROR);
        // }

        baseDoc.setCreator(getUser().getUserId());

        String institutionSnlVersionId = baseDoc.getInstitutionSnlVersionId();
        if (StringUtils.isEmpty(institutionSnlVersionId)) {
            baseDoc.setInstitutionSnlVersionId(null);
        } else {
            baseDoc.setIsMapping(YesOrNo.Y.name());
        }

        // TODO 是否要验证文件相同
        String docSourceName = baseDoc.getDocSourceName();

        Date date = new Date();
        baseDoc.setAddTime(date);
        baseDoc.setUpdateTime(date);
        baseDocService.insertSelective(baseDoc);
        return setDataValue(null);
    }

}