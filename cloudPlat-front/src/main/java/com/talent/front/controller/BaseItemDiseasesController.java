package com.talent.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.talent.front.service.BaseItemDiseasesService;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：疾病数据项表控制类
 */
@Controller
public class BaseItemDiseasesController {
    private static final Logger logger = LoggerFactory.getLogger(BaseItemDiseasesController.class);

    @Autowired
    private BaseItemDiseasesService baseItemDiseasesService;

}