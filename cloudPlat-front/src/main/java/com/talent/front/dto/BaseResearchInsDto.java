package com.talent.front.dto;

import com.talent.front.entity.BaseResearchIns;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：课题机构关系表Dto类
 */
public class BaseResearchInsDto extends BaseResearchIns {

    private static final long serialVersionUID = 4191632199873095072L;

    @Override
	public String toString() {
		return "BaseResearchIns ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.researchId=" + this.getResearchId() + ", "
		 		+ "this.insId=" + this.getInsId() + ", "
		 		+ "this.isMajor=" + this.getIsMajor() + ", "
		+ "]";   
	}

}