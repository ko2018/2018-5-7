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
import com.talent.front.dto.BaseDocDefValueDto;
import com.talent.front.service.BaseDocDefValueService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-03 <br/>
 * 描述：文档值通用不合法值记录控制类
 */
@RestController
@RequestMapping("docDefValue")
public class BaseDocDefValueController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseDocDefValueController.class);

    @Autowired
    private BaseDocDefValueService baseDocDefValueService;

    @RequestMapping(value = "list")
    public Map<String, Object> list() {
        PageObject pageObject = getPageObject();
        PageResult<BaseDocDefValueDto> pageResult = baseDocDefValueService.pageListDto(pageObject);
        return setDataValue(pageResult);
    }

    @RequestMapping(value = "listJoin")
    public Map<String, Object> listJoin(String qryWord) {
        PageObject pageObject = getPageObject();
        pageObject.setOrderByClause("docdefvalue_num desc");
        PageResult<BaseDocDefValueDto> pageResult = baseDocDefValueService.pageListDtoJoin(pageObject);

        return setDataValue(pageResult);
    }

}