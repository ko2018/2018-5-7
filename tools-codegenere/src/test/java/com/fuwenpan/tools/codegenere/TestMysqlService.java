package com.fuwenpan.tools.codegenere;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fuwenpan.tools.codegenere.entity.Column;
import com.fuwenpan.tools.codegenere.entity.Table;
import com.fuwenpan.tools.codegenere.sercvice.MysqlServiceImpl;

import freemarker.template.TemplateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestMysqlService {
	@Autowired
	private MysqlServiceImpl mysqlServiceImpl;

	@Test
	public void testTables() {
		List<Table> tables = mysqlServiceImpl.getTables();
		for (Table table : tables) {
			System.out.println(table.getName());
		}
	}

	public void testColumns(String tableName) {
		List<Table> tables = mysqlServiceImpl.getTables();
		for (Table table : tables) {
			List<Column> columns = mysqlServiceImpl.getColumns(table.getName());
			table.setColumnList(columns);
		}
	}

	@Test
	public void testGen() throws IOException, TemplateException {
		mysqlServiceImpl.generatorCode();
	}
}
