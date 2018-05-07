package com.talent.dcs.dto;


import com.talent.dcs.entity.BaseItemDiseases;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-09 <br/>
 * 描述：疾病数据项表Dto类
 */
public class BaseItemDiseasesDto extends BaseItemDiseases {

	@Override
	public String toString() {
		return "BaseItemDiseases ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.itemId=" + this.getItemId() + ", "
		 		+ "this.diseaseId=" + this.getDiseaseId() + ", "
		+ "]";   
	}

}