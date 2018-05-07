package com.talent.front.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.exception.ErrorCode;
import com.talent.base.exception.GlobalException;
import com.talent.base.model.PageObject;
import com.talent.front.service.BaseDataStdService;
import com.talent.front.util.elasticsearch.PhysicalExaminationRecordEs;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：标准数据表控制类
 */
@RestController
@RequestMapping("dataStd")
public class BaseDataStdController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseDataStdController.class);

    @Autowired
    private BaseDataStdService baseDataStdService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping(value = "list")
    public Map<String, Object> list() {
        PageObject pageObject = getPageObject();

        return setDataValue(null);
    }

    @RequestMapping("/listEs")
    public Object singleTitle(String institutionIds, String researchId, String start, String end, String diseasesIds,
            String snlIds, String formula, @PageableDefault Pageable pageable) throws Exception {
        if (StringUtils.isEmpty(institutionIds) || StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
            throw new GlobalException(ErrorCode.QUERY_ERROE);
        }
        Set<String> ins = new HashSet<String>();
        String[] inArray = institutionIds.split(",");
        for (int i = 0; i < inArray.length; i++) {
            ins.add(inArray[i]);
        }
        BoolQueryBuilder bqb = QueryBuilders.boolQuery();
        bqb.must(QueryBuilders.termsQuery("institutionId", ins));
        bqb.must(QueryBuilders.rangeQuery("examDate").from(start).to(end).includeLower(true).includeUpper(true));// 包括上下限
        if (!StringUtils.isEmpty(diseasesIds)) {
            Set<String> dis = new HashSet<String>();
            String[] dis_array = diseasesIds.split(",");
            for (int i = 0; i < dis_array.length; i++) {
                dis.add(dis_array[i]);
            }
            bqb.must(QueryBuilders.termsQuery("multipleDiag", dis));
        }
        if (!StringUtils.isEmpty(researchId)) {
            Set<String> res = new HashSet<String>();
            String[] res_array = researchId.split(",");
            for (int i = 0; i < res_array.length; i++) {
                res.add(res_array[i]);
            }
            bqb.must(QueryBuilders.termsQuery("researchId", res));
        }

        if (!StringUtils.isEmpty(snlIds)) {
            String[] snd_array = snlIds.split(",");
            for (int i = 0; i < snd_array.length; i++) {
                String tmp = snd_array[i];
                if (tmp.indexOf(">=") > -1) {
                    String[] tmps = tmp.split(">=");
                    if (tmps.length > 1)
                        bqb.must(QueryBuilders.rangeQuery(tmps[0].trim()).gte(tmps[1].trim()));
                } else if (tmp.indexOf("<=") > -1) {
                    String[] tmps = tmp.split("<=");
                    if (tmps.length > 1)
                        bqb.must(QueryBuilders.rangeQuery(tmps[0].trim()).lte(tmps[1].trim()));
                } else if (tmp.indexOf(">") > -1) {
                    String[] tmps = tmp.split(">");
                    if (tmps.length > 1)
                        bqb.must(QueryBuilders.rangeQuery(tmps[0].trim()).gt(tmps[1].trim()));
                } else if (tmp.indexOf("<") > -1) {
                    String[] tmps = tmp.split("<");
                    if (tmps.length > 1)
                        bqb.must(QueryBuilders.rangeQuery(tmps[0].trim()).lt(tmps[1].trim()));
                } else if (tmp.indexOf("=") > -1) {
                    String[] tmps = tmp.split("=");
                    if (tmps.length > 1)
                        bqb.must(QueryBuilders.termQuery(tmps[0].trim(), tmps[1].trim()));
                } else if (tmp.indexOf("^") > -1) {
                    String[] tmps = tmp.split("^");
                    if (tmps.length > 1)
                        bqb.must(QueryBuilders.boolQuery()
                                .mustNot(QueryBuilders.termQuery(tmps[0].trim(), tmps[1].trim())));
                } else if (tmp.indexOf("!") > -1) {
                    String[] tmps = tmp.split("!");
                    if (tmps.length > 1)
                        bqb.must(QueryBuilders.boolQuery()
                                .mustNot(QueryBuilders.termQuery(tmps[0].trim(), tmps[1].trim())));
                }
            }
        }

        FieldSortBuilder fsb = SortBuilders.fieldSort("examDate.keyword").order(SortOrder.DESC);

        System.out.println(bqb.toString());

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(bqb).withSort(fsb).withPageable(pageable)
                .build();
        // System.err.println(elasticsearchTemplate.count(searchQuery));
        return setDataValue(elasticsearchTemplate.queryForPage(searchQuery, PhysicalExaminationRecordEs.class));
    }

    @RequestMapping("/searchRaw")
    public Object searchDataRaw() {
        return setDataValue(null);
    }

}