package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseDocLegal;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-26 <br/>
 * 描述：文档空值合法值记录表Dto类
 */
public class BaseDocLegalDto extends BaseDocLegal {

	@Override
	public String toString() {
		return "BaseDocLegal ["
		 		+ "this.docvalueId=" + this.getDocvalueId() + ", "
		 		+ "this.dictId=" + this.getDictId() + ", "
		 		+ "this.docId=" + this.getDocId() + ", "
		 		+ "this.countNull=" + this.getCountNull() + ", "
		 		+ "this.countLegal=" + this.getCountLegal() + ", "
		 		+ "this.countNolegal=" + this.getCountNolegal() + ", "
		 		+ "this.countAll=" + this.getCountAll() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}