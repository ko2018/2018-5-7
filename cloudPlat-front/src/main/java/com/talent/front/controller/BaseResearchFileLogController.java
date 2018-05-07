package com.talent.front.controller;

import java.util.Map;

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
import com.talent.front.constant.CommonConstant;
import com.talent.front.entity.BaseResearch;
import com.talent.front.service.BaseResearchFileLogService;
import com.talent.front.service.BaseResearchService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：科研文档操作日志表控制类
 */
@RestController
@RequestMapping("researchFileLog")
public class BaseResearchFileLogController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseResearchFileLogController.class);

    @Autowired
    private BaseResearchFileLogService baseResearchFileLogService;

    @Autowired
    private BaseResearchService baseResearchService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list() throws Exception {
        PageObject pageObject = getPageObject();

        // 默认update_time排序,数据库base_research_file_log无此字段调整为operate_time
        if ("update_time".equals(pageObject.getSortBy())) {
            pageObject.setSortBy("operate_time");
            pageObject.setOrderByClause("operate_time desc");
        }
        pageObject.getQueryCondition().put("isDel", CommonConstant.Is.NO);
        BaseResearch baseResearch = this.baseResearchService
                .selectByPrimaryKey((String) pageObject.getQueryCondition().get("researchId"));
        if (baseResearch == null) {
            throw new GlobalException(ErrorCode.RESEARCH_NOT_EXIST);
        }
        return setDataValue(baseResearchFileLogService.pageListDto(pageObject));
    }
}