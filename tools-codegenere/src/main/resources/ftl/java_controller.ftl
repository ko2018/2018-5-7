package ${table.packageController};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ${table.packageService}.${table.classDomain}Service;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：${now?string("yyyy-MM-dd")} <br/>
 * 描述：${table.annotation}控制类
 */
@Controller
public class ${table.classDomain}Controller {
	private static final Logger logger = LoggerFactory.getLogger(${table.classDomain}Controller.class);

	@Autowired
	private ${table.classDomain}Service ${table.classDomainVar}Service;

}