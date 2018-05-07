package com.talent.front.dto;

import com.talent.front.entity.BaseKeywordUse;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-26 <br/>
 * 描述：关键字应用表Dto类
 */
public class BaseKeywordUseDto extends BaseKeywordUse {

	@Override
	public String toString() {
		return "BaseKeywordUse ["
		 		+ "this.keywordUseId=" + this.getKeywordUseId() + ", "
		 		+ "this.keywordId=" + this.getKeywordId() + ", "
		 		+ "this.matchContent=" + this.getMatchContent() + ", "
		 		+ "this.matchType=" + this.getMatchType() + ", "
		 		+ "this.isDel=" + this.getIsDel() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}