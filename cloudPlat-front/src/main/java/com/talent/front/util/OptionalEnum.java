package com.talent.front.util;

public enum  OptionalEnum {
	//集中趋势
	MEAN("1", "平均值"),MIDDLE("2", "中间值"),MODE("3", "众数"), TOTAL("4", "合计"),
	PERCENT_Q("5", "四分位数(Q)"),
	PERCENT_U("6", "分割点(Q)"),
	PERCENT_P("7", "百分位数(P)"),
	//离散
	STAD("11", "标准偏差"),MIN("12", "最小值"), VAR("13", "方差"), MAX("14", "最大值"),
	FIELD("15", "范围"),MEAN_STD_ERROR("16", "平均值标准误差"),
	//分布
	PARTIAL_W("21", "偏值"), PARTIAL_K("22", "峰值"),
	
	//显示顺序
	SORT_DICT("01", "变量列表"),SORT_LETTER("02", "变量列表"),SORT_DESC("04", "变量列表"),
	SORT_ASC("03", "变量列表");
	private String name;   
	private String index;  
	    // 构造方法  
	private OptionalEnum(String index, String name) {  
	        this.name = name;  
	        this.index = index;  
	 }  
}
