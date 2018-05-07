/**
 * Project Name:cloudPlat-tools
 * File Name:GenerateRecordEntity.java
 * Package Name:com.talent.tools.snl
 * Date:2018年2月7日下午5:06:58
 * Copyright (c) 2018, curefun.com All Rights Reserved.
 *
*/

package com.talent.tools.snl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * Description: <br/>
 * Date: 2018年2月7日 下午5:06:58 <br/>
 * 
 * @author wangdj
 * @version
 * @see
 */
public class GenerateRecordEntity {
    public static void main(String[] args) throws Exception {

        List<Property> propertyList = new ArrayList();
        int dictIdMax = 5000;
        for (int i = 1; i <= dictIdMax; i++) {
            Property p = new Property();
            p.setJavaType("String");
            p.setPropertyName("_" + i);

            propertyList.add(p);
        }

        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File("src\\main\\java\\com\\talent\\tools\\flt"));
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("physicalExaminationRecord.flt", "utf-8");

        Map<String, Object> entity = new HashMap();
        entity.put("packageDomain", "com.talent.base.entity");
        entity.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        entity.put("annotation", "体检报告");
        entity.put("propertyList", propertyList);

        Writer writer = new OutputStreamWriter(
                new FileOutputStream("src\\main\\java\\com\\talent\\base\\entity\\PhysicalExaminationRecord.java"),
                "UTF-8");
        template.process(entity, writer);
    }
}
