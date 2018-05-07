package com.talent.front.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseHotInfo;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-05 <br/>
 * 描述：热点信息表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "isDel" })
public class BaseHotInfoDto extends BaseHotInfo {

    private List<BaseFileDto> baseFiles;

    public List<BaseFileDto> getBaseFiles() {
        return baseFiles;
    }

    public void setBaseFiles(List<BaseFileDto> baseFiles) {
        this.baseFiles = baseFiles;
    }

    @Override
    public String toString() {
        return "BaseHotInfo [" + "this.hotInfoId=" + this.getHotInfoId() + ", " + "this.infoTitle="
                + this.getInfoTitle() + ", " + "this.infoContent=" + this.getInfoContent() + ", " + "this.infoOrigin="
                + this.getInfoOrigin() + ", " + "this.infoOriginUrl=" + this.getInfoOriginUrl() + ", "
                + "this.infoAttach=" + this.getInfoAttach() + ", " + "this.infoTypeId=" + this.getInfoTypeId() + ", "
                + "this.infoTypeName=" + this.getInfoTypeName() + ", " + "this.deleteStatus=" + this.getDeleteStatus()
                + ", " + "this.creator=" + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", "
                + "this.updateUser=" + this.getUpdateUser() + ", " + "this.updateTime=" + this.getUpdateTime() + ", "
                + "]";
    }

}