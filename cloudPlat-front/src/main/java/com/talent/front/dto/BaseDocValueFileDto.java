package com.talent.front.dto;

import com.talent.front.entity.BaseDocValueFile;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-20 <br/>
 * 描述：不合法修改文件表Dto类
 */
public class BaseDocValueFileDto extends BaseDocValueFile {

	@Override
	public String toString() {
		return "BaseDocValueFile ["
		 		+ "this.valuefileId=" + this.getValuefileId() + ", "
		 		+ "this.docId=" + this.getDocId() + ", "
		 		+ "this.dictId=" + this.getDictId() + ", "
		 		+ "this.valuefilePath=" + this.getValuefilePath() + ", "
		 		+ "this.isChange=" + this.getIsChange() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}