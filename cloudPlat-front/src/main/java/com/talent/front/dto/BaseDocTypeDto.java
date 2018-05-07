package com.talent.front.dto;

import com.talent.front.entity.BaseDocType;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：文档类型Dto类
 */
public class BaseDocTypeDto extends BaseDocType {

	@Override
	public String toString() {
		return "BaseDocType ["
		 		+ "this.doctypeId=" + this.getDoctypeId() + ", "
		 		+ "this.doctypeName=" + this.getDoctypeName() + ", "
		 		+ "this.sequence=" + this.getSequence() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}