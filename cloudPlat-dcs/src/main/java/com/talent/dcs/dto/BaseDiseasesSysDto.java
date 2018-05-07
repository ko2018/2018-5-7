package com.talent.dcs.dto;

import com.talent.dcs.entity.BaseDiseasesSys;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：知识库表(疾病表)Dto类
 */
public class BaseDiseasesSysDto extends BaseDiseasesSys {

	@Override
	public String toString() {
		return "BaseDiseasesSys ["
		 		+ "this.diseasesId=" + this.getDiseasesId() + ", "
		 		+ "this.diseasesCode=" + this.getDiseasesCode() + ", "
		 		+ "this.nameCn=" + this.getNameCn() + ", "
		 		+ "this.fullNameCn=" + this.getFullNameCn() + ", "
		 		+ "this.shortNameCn=" + this.getShortNameCn() + ", "
		 		+ "this.nameUs=" + this.getNameUs() + ", "
		 		+ "this.shortNameUs=" + this.getShortNameUs() + ", "
		 		+ "this.parentId=" + this.getParentId() + ", "
		 		+ "this.briefInfo=" + this.getBriefInfo() + ", "
		 		+ "this.isLeaf=" + this.getIsLeaf() + ", "
		 		+ "this.isDel=" + this.getIsDel() + ", "
		 		+ "this.depth=" + this.getDepth() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}