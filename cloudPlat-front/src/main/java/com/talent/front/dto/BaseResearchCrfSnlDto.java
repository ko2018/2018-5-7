package com.talent.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseResearchCrfSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板属性表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "colSeq" })
public class BaseResearchCrfSnlDto extends BaseResearchCrfSnl {

    private static final long serialVersionUID = 5196016128969738310L;

    @Override
	public String toString() {
		return "BaseResearchCrfSnl ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.templetId=" + this.getTempletId() + ", "
		 		+ "this.snlId=" + this.getSnlId() + ", "
		 		+ "this.colSeq=" + this.getColSeq() + ", "
		+ "]";   
	}

}