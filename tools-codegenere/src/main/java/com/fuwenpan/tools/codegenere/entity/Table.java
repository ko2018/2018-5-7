package com.fuwenpan.tools.codegenere.entity;

import java.io.Serializable;
import java.util.List;

import com.fuwenpan.tools.codegenere.util.StringUtil;

public class Table implements Serializable {

	private static final long serialVersionUID = -7703109005640698128L;

	String basePackageRoot = "com.talent.base";
	String packageRoot = "com.talent.front";

	private Integer id; // 表id
	private String name;// 表名称
	private String annotation;// 表注释
	private List<Column> columnList;// 字段集合
	private Boolean buildCURD;// 生成增删改查维护
	private String subjectModuleName; // 子模块名

	public String getBasePackageRoot() {
		return basePackageRoot;
	}

	public String getClassDomain() {
		String className = name;
		if (name.startsWith("t_")) {
			className = name.replaceFirst("t_", "");
		}
		String[] arrStr = className.split("_");
		className = "";
		for (String s : arrStr) {
			className += StringUtil.upperFirstChar(s.toLowerCase());
		}
		return className;
	}

	public String getClassDomainVar() {
		return StringUtil.lowerFirstChar(getClassDomain());
	}

	public String getClassController() {
		return getClassDomain() + "Controller";
	}

	public String getClassService() {
		return getClassDomain() + "Service";
	}

	public String getClassServiceImpl() {
		return getClassDomain() + "ServiceImpl";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	public Boolean getBuildCURD() {
		return buildCURD;
	}

	public void setBuildCURD(Boolean buildCURD) {
		this.buildCURD = buildCURD;
	}

	public String getSubjectModuleName() {
		return subjectModuleName;
	}

	public void setSubjectModuleName(String subjectModuleName) {
		this.subjectModuleName = subjectModuleName;
	}

	public String getModuleName() {
		/*
		 * int index= name.indexOf("_"); if(index!=-1) return
		 * name.substring(0,index).toLowerCase(); else return "";
		 */
		return "";
	}

	public String getPackageDomain() {
		String packageName = packageRoot;
		if (!StringUtil.isEmpty(this.getModuleName()))
			packageName += "." + this.getModuleName();
		if (!StringUtil.isEmpty(this.getSubjectModuleName()))
			packageName += "." + this.getSubjectModuleName();
		packageName += ".entity";
		return packageName;
	}

	public String getPackageDao() {
		String packageName = packageRoot;
		if (!StringUtil.isEmpty(this.getModuleName()))
			packageName += "." + this.getModuleName();
		if (!StringUtil.isEmpty(this.getSubjectModuleName()))
			packageName += "." + this.getSubjectModuleName();
		packageName += ".dao";
		return packageName;
	}

	public String getPackageDto() {
		String packageName = packageRoot;
		if (!StringUtil.isEmpty(this.getModuleName()))
			packageName += "." + this.getModuleName();
		if (!StringUtil.isEmpty(this.getSubjectModuleName()))
			packageName += "." + this.getSubjectModuleName();
		packageName += ".dto";
		return packageName;
	}

	public String getPackageController() {
		String packageName = packageRoot;
		if (!StringUtil.isEmpty(this.getModuleName()))
			packageName += "." + this.getModuleName();
		if (!StringUtil.isEmpty(this.getSubjectModuleName()))
			packageName += "." + this.getSubjectModuleName();
		packageName += ".controller";
		return packageName;
	}

	public String getPackageService() {
		String packageName = packageRoot;
		if (!StringUtil.isEmpty(this.getModuleName()))
			packageName += "." + this.getModuleName();
		if (!StringUtil.isEmpty(this.getSubjectModuleName()))
			packageName += "." + this.getSubjectModuleName();
		packageName += ".service";
		return packageName;
	}

	public String getPackageServiceImpl() {
		String packageName = packageRoot;
		if (!StringUtil.isEmpty(this.getModuleName()))
			packageName += "." + this.getModuleName();
		if (!StringUtil.isEmpty(this.getSubjectModuleName()))
			packageName += "." + this.getSubjectModuleName();
		packageName += ".service.impl";
		return packageName;
	}

	public String getPackageMapper() {
		String packageName = packageRoot;
		if (!StringUtil.isEmpty(this.getModuleName()))
			packageName += "." + this.getModuleName();
		if (!StringUtil.isEmpty(this.getSubjectModuleName()))
			packageName += "." + this.getSubjectModuleName();
		packageName += ".mapper";
		return packageName;
	}

	public String getMappings() {
		String mappings = "";
		if (!StringUtil.isEmpty(this.getModuleName()))
			mappings += "/" + this.getModuleName();
		if (!StringUtil.isEmpty(this.getSubjectModuleName()))
			mappings += "/" + this.getSubjectModuleName();
		mappings += "/" + StringUtil.lowerFirstChar(getClassDomain()) + "_*.do";
		return mappings;
	}

	public String getPk() {
		if (this.columnList != null && this.columnList.size() > 0) {
			for (Column column : columnList) {
				if ("Y".equals(column.getPk()))
					return column.getName();
			}
			return columnList.get(0).getName();
		} else {
			return "";
		}
	}

	public String getPkName() {
		if (this.columnList != null && this.columnList.size() > 0) {
			for (Column column : columnList) {
				if ("Y".equals(column.getPk()))
					return column.getPropertyName();
			}
			return columnList.get(0).getPropertyName();
		} else {
			return "";
		}
	}
}
