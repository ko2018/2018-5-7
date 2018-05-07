package com.talent.dcs.dto;

import java.util.List;

import com.talent.dcs.entity.BaseDiseasesLogic;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-23 <br/>
 * 描述：疾病诊断逻辑Dto类
 */
public class BaseDiseasesLogicDto extends BaseDiseasesLogic {
    private static final long serialVersionUID = -5183887498265855570L;
    
    private List<BaseItemDto> itemList;
    
    private String itemListJosn;
    
	public String getItemListJosn()
    {
        return itemListJosn;
    }


    public void setItemListJosn(String itemListJosn)
    {
        this.itemListJosn = itemListJosn;
    }


    public List<BaseItemDto> getItemList()
    {
        return itemList;
    }


    public void setItemList(List<BaseItemDto> itemList)
    {
        this.itemList = itemList;
    }

    @Override
	public String toString() {
		return "BaseDiseasesLogic ["
		 		+ "this.logicId=" + this.getLogicId() + ", "
		 		+ "this.userId=" + this.getUserId() + ", "
		 		+ "this.diseasesId=" + this.getDiseasesId() + ", "
		 		+ "this.briefInfo=" + this.getBriefInfo() + ", "
		 		+ "this.isDefault=" + this.getIsDefault() + ", "
		 		+ "this.isDel=" + this.getIsDel() + ", "
		 		+ "this.diagLogic=" + this.getDiagLogic() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateUser=" + this.getUpdateUser() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}