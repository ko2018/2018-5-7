package com.talent.dcs.dto;

import com.talent.dcs.entity.BaseSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-15 <br/>
 * 描述：标准术语表Dto类
 */
public class BaseSnlDto extends BaseSnl {

	@Override
	public String toString() {
		return "BaseSnl [" + "this.snlId=" + this.getSnlId() + ", " + "this.snlType=" + this.getSnlType() + ", "
				+ "this.snlTypeName=" + this.getSnlTypeName() + ", " + "this.snlCode=" + this.getSnlCode() + ", "
				+ "this.nameCn=" + this.getNameCn() + ", " + "this.fullNameCn=" + this.getFullNameCn() + ", "
				+ "this.shortNameCn=" + this.getShortNameCn() + ", " + "this.nameUs=" + this.getNameUs() + ", "
				+ "this.shortNameUs=" + this.getShortNameUs() + ", " + "this.parentCode=" + this.getParentCode() + ", "
				+ "this.depth=" + this.getDepth() + ", " + "this.isLeaf=" + this.getIsLeaf() + ", " + "this.dictId="
				+ this.getDictId() + ", " + "this.creator=" + this.getCreator() + ", " + "this.addTime="
				+ this.getAddTime() + ", " + "this.updateUser=" + this.getUpdateUser() + ", " + "this.updateTime="
				+ this.getUpdateTime() + ", " + "this.isDel=" + this.getIsDel() + ", " + "]";
	}

}