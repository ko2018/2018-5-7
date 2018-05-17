package com.fuwenpan.tools.codegenere.sercvice;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.fuwenpan.tools.codegenere.dao.MysqlDao;
import com.fuwenpan.tools.codegenere.entity.Column;
import com.fuwenpan.tools.codegenere.entity.Table;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MysqlServiceImpl {
	@Autowired
	private MysqlDao mysqlDao;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Value("${outDir}")
	private String outDir;

	public List<Table> getTables() {
		List<Table> tables = mysqlDao.getTables();
		for (Table table : tables) {
			List<Column> columnList = mysqlDao.getColumns(table.getName());
			table.setColumnList(columnList);
		}
		return tables;
	}

	public List<Column> getColumns(String tableName) {
		return mysqlDao.getColumns(tableName);
	}

	public void generatorCode() throws IOException, TemplateException {
		List<Table> tables = getTables();
		System.out.println("AAAAAAAAAAAAAAAAA:" + tables.size());
		for (Table table : tables) {
			createDomain(table);
			createSqlmap(table);
			createService(table);
			createServiceImpl(table);
			createController(table);

			createDao(table);
			createDto(table);
		}
	}

	private void createDomain(Table table) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("now", new Date());
		model.put("table", table);
		model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("\\java_domain.ftl", "utf-8");
		String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String dir = outDir + "\\src\\" + table.getPackageDomain().replaceAll("\\.", "\\\\") + "\\"
				+ table.getClassDomain() + ".java";
		File file = new File(dir);
		FileUtils.writeStringToFile(file, temp, "utf-8");
	}

	/**
	 * 创建Dao层
	 * 
	 * @param table
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void createDao(Table table) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("now", new Date());
		model.put("table", table);
		model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("\\java_dao.ftl", "utf-8");
		String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String dir = outDir + "\\src\\" + table.getPackageDao().replaceAll("\\.", "\\\\") + "\\"
				+ table.getClassDomain() + "Dao.java";
		File file = new File(dir);
		FileUtils.writeStringToFile(file, temp, "utf-8");
	}

	/**
	 * 创建Dto层
	 * 
	 * @param table
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void createDto(Table table) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("now", new Date());
		model.put("table", table);
		model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("\\java_domain_dto.ftl", "utf-8");
		String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String dir = outDir + "\\src\\" + table.getPackageDto().replaceAll("\\.", "\\\\") + "\\"
				+ table.getClassDomain() + "Dto.java";
		File file = new File(dir);
		FileUtils.writeStringToFile(file, temp, "utf-8");
	}

	private void createSqlmap(Table table) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		boolean hasBlob = false;
		for (Column column : table.getColumnList()) {
			if (column.getIbatisType().equals("BLOB")) {
				hasBlob = true;
				break;
			}
		}
		model.put("now", new Date());
		model.put("hasBlob", hasBlob);
		model.put("table", table);
		model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("\\ibatis_sqlmap.ftl", "utf-8");
		String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String dir = outDir + "/src/" + table.getPackageMapper().replaceAll("\\.", "/") + "/" + table.getClassDomain()
				+ "Mapper.xml";
		File file = new File(dir);
		FileUtils.writeStringToFile(file, temp, "utf-8");
	}

	private void createService(Table table) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("\\java_service_new.ftl", "utf-8");
		model.put("now", new Date());
		model.put("table", table);
		model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String dir = outDir + "/src/" + table.getPackageService().replaceAll("\\.", "/") + "/" + table.getClassService()
				+ ".java";
		File file = new File(dir);
		FileUtils.writeStringToFile(file, temp, "utf-8");
	}

	private void createServiceImpl(Table table) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("\\java_service_impl.ftl", "utf-8");
		model.put("now", new Date());
		model.put("table", table);
		model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String dir = outDir + "/src/" + table.getPackageServiceImpl().replaceAll("\\.", "/") + "/"
				+ table.getClassServiceImpl() + ".java";
		File file = new File(dir);
		FileUtils.writeStringToFile(file, temp, "utf-8");
	}

	private void createController(Table table) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("\\java_controller.ftl", "utf-8");
		model.put("now", new Date());
		model.put("table", table);
		model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		String dir = outDir + "/src/" + table.getPackageController().replaceAll("\\.", "/") + "/"
				+ table.getClassController() + ".java";
		File file = new File(dir);
		FileUtils.writeStringToFile(file, temp, "utf-8");
	}

}
