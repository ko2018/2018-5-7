package com.talent.front.dto;

import com.talent.front.entity.StaticsHasDiseaseYear;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：统计原始数据每年患病情况Dto类
 */

public class StaticsHasDiseaseYearDto extends StaticsHasDiseaseYear {

	@Override
	public String toString() {
		return "StaticsHasDiseaseYear ["
		 		+ "this.staticsSid=" + this.getStaticsSid() + ", "
		 		+ "this.staticsYear=" + this.getStaticsYear() + ", "
		 		+ "this.staticsRSum=" + this.getStaticsRSum() + ", "
		 		+ "this.staticsDSum=" + this.getStaticsDSum() + ", "
		 		+ "this.staticsPSum=" + this.getStaticsPSum() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}