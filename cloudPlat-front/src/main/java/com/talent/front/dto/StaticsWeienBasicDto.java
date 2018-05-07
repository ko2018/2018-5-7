package com.talent.front.dto;

import com.talent.front.entity.StaticsWeienBasic;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-12 <br/>
 * 描述：Dto类
 */
public class StaticsWeienBasicDto extends StaticsWeienBasic {

	@Override
	public String toString() {
		return "StaticsWeienBasic ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.personId=" + this.getPersonId() + ", "
		 		+ "this.date=" + this.getDate() + ", "
		 		+ "this.examSex=" + this.getExamSex() + ", "
		 		+ "this.examAgeGroup=" + this.getExamAgeGroup() + ", "
		 		+ "this.disResult=" + this.getDisResult() + ", "
		 		+ "this.groupPro=" + this.getGroupPro() + ", "
		 		+ "this.knowlegeId=" + this.getKnowlegeId() + ", "
		+ "]";   
	}

}