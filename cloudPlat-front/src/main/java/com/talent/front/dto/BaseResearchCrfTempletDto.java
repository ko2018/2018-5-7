package com.talent.front.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseResearchCrfSnl;
import com.talent.front.entity.BaseResearchCrfTemplet;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-20 <br/>
 * 描述：科研CRF模板表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "isDel", "filePath" })
public class BaseResearchCrfTempletDto extends BaseResearchCrfTemplet {

    private static final long serialVersionUID = -1451108991889785505L;

    private SysUserDto sysUserDto;

    private List<BaseResearchCrfSnl> baseResearchCrfSnlList;

    private String baseResearchCrfSnlJson;

    public String getBaseResearchCrfSnlJson() {
        return baseResearchCrfSnlJson;
    }

    public void setBaseResearchCrfSnlJson(String baseResearchCrfSnlJson) {
        this.baseResearchCrfSnlJson = baseResearchCrfSnlJson;
    }

    public SysUserDto getSysUserDto() {
        return sysUserDto;
    }

    public void setSysUserDto(SysUserDto sysUserDto) {
        this.sysUserDto = sysUserDto;
    }

    public List<BaseResearchCrfSnl> getBaseResearchCrfSnlList() {
        return baseResearchCrfSnlList;
    }

    public void setBaseResearchCrfSnlList(List<BaseResearchCrfSnl> baseResearchCrfSnlList) {
        this.baseResearchCrfSnlList = baseResearchCrfSnlList;
    }

    @Override
    public String toString() {
        return "BaseResearchCrfTemplet [" + "this.templetId=" + this.getTempletId() + ", " + "this.researchId="
                + this.getResearchId() + ", " + "this.templetName=" + this.getTempletName() + ", " + "this.briefInfo="
                + this.getBriefInfo() + ", " + "this.filePath=" + this.getFilePath() + ", " + "this.isDel="
                + this.getIsDel() + ", " + "this.creator=" + this.getCreator() + ", " + "this.addTime="
                + this.getAddTime() + ", " + "this.updateUser=" + this.getUpdateUser() + ", " + "this.updateTime="
                + this.getUpdateTime() + ", " + "]";
    }

}