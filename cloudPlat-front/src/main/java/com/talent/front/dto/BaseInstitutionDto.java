package com.talent.front.dto;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseInstitution;
import com.talent.front.util.FastdfsFileManagerBoot;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-16 <br/>
 * 描述：机构表Dto类
 */
@JsonInclude(Include.NON_NULL)
public class BaseInstitutionDto extends BaseInstitution {

    private AreaDto areaDto;

    private BaseInstitutionLevelDto baseInstitutionLevelDto;

    private String institutionLogoView;

    public AreaDto getAreaDto() {
        return areaDto;
    }

    public void setAreaDto(AreaDto areaDto) {
        this.areaDto = areaDto;
    }

    @Override
    public String toString() {
        return "BaseInstitution [" + "this.institutionId=" + this.getInstitutionId() + ", " + "this.institutionCode="
                + this.getInstitutionCode() + ", " + "this.institutionName=" + this.getInstitutionName() + ", "
                + "this.institutionLogo=" + this.getInstitutionLogo() + ", " + "this.creator=" + this.getCreator()
                + ", " + "this.addTime=" + this.getAddTime() + ", " + "this.updateTime=" + this.getUpdateTime() + ", "
                + "this.institutionlevelId=" + this.getInstitutionlevelId() + ", " + "this.institutionlevelName="
                + this.getInstitutionlevelName() + ", " + "this.areaId=" + this.getAreaId() + ", " + "this.areaNameAll="
                + this.getAreaNameAll() + ", " + "this.deleteStatus=" + this.getDeleteStatus() + ", "
                + "this.institutionAddress=" + this.getInstitutionAddress() + ", " + "this.institutionOwner="
                + this.getInstitutionOwner() + ", " + "this.institutionOwnerPhone=" + this.getInstitutionOwnerPhone()
                + ", " + "]";
    }

    public BaseInstitutionLevelDto getBaseInstitutionLevelDto() {
        return baseInstitutionLevelDto;
    }

    public void setBaseInstitutionLevelDto(BaseInstitutionLevelDto baseInstitutionLevelDto) {
        this.baseInstitutionLevelDto = baseInstitutionLevelDto;
    }

    public String getInstitutionLogoView() {
        if (!StringUtils.isEmpty(this.getInstitutionLogo())) {
            if (!this.getInstitutionLogo().startsWith("http")) {
                String pre = FastdfsFileManagerBoot.getUrlPre();
                if (!StringUtils.isEmpty(pre)) {
                    return pre + this.getInstitutionLogo();
                } else {
                    return this.getInstitutionLogo();
                }
            }
        }
        return "";
    }

    public void setInstitutionLogoView(String institutionLogoView) {
        this.institutionLogoView = institutionLogoView;
    }

}