package com.fuwenpan.tools.codegenere.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fuwenpan.tools.codegenere.entity.Column;
import com.fuwenpan.tools.codegenere.entity.Table;

public interface MysqlDao {
	public List<Table> getTables();

	public List<Column> getColumns(@Param("table_name") String tableName);
}
