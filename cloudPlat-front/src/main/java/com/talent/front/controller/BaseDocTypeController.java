package com.talent.front.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent.base.controller.BaseController;
import com.talent.base.model.PageObject;
import com.talent.base.model.PageResult;
import com.talent.front.dto.BaseDocTypeDto;
import com.talent.front.service.BaseDocTypeService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：文档类型控制类
 */
@RestController
@RequestMapping("doctype")
public class BaseDocTypeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocTypeController.class);

    @Autowired
    private BaseDocTypeService baseDocTypeService;

    @RequestMapping(value = "list")
    public Map<String, Object> list() {
        PageObject pageObject = getPageObject();
        pageObject.setPageSize(30);
        pageObject.setOrderByClause("sequence asc");
        PageResult<BaseDocTypeDto> pageResult = baseDocTypeService.pageListDto(pageObject);
        return setDataValue(pageResult);
    }

}