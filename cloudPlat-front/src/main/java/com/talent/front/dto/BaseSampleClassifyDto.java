package com.talent.front.dto;

import com.talent.front.entity.BaseSampleClassify;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：样本类型表Dto类
 */
public class BaseSampleClassifyDto extends BaseSampleClassify {

	@Override
	public String toString() {
		return "BaseSampleClassify ["
		 		+ "this.classifyCode=" + this.getClassifyCode() + ", "
		 		+ "this.classifyName=" + this.getClassifyName() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}