package com.talent.front.dto;

import com.talent.front.entity.BaseInstitutionLevel;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：机构级别Dto类
 */
public class BaseInstitutionLevelDto extends BaseInstitutionLevel {
    private BaseInstitutionLevelDto parentBaseInstitutionLevelDto;

    @Override
    public String toString() {
        return "BaseInstitutionLevel [" + "this.institutionlevelId=" + this.getInstitutionlevelId() + ", "
                + "this.institutionlevelName=" + this.getInstitutionlevelName() + ", " + "this.sequence="
                + this.getSequence() + ", " + "this.deleteStatus=" + this.getDeleteStatus() + ", " + "this.creator="
                + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", " + "this.updateTime="
                + this.getUpdateTime() + ", " + "]";
    }

    public BaseInstitutionLevelDto getParentBaseInstitutionLevelDto() {
        return parentBaseInstitutionLevelDto;
    }

    public void setParentBaseInstitutionLevelDto(BaseInstitutionLevelDto parentBaseInstitutionLevelDto) {
        this.parentBaseInstitutionLevelDto = parentBaseInstitutionLevelDto;
    }

}