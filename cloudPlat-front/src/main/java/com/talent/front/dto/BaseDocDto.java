package com.talent.front.dto;

import com.talent.front.entity.BaseDoc;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-21 <br/>
 * 描述：文档表Dto类
 */
public class BaseDocDto extends BaseDoc {

    private BaseInstitutionDto baseInstitutionDto;

    private SysUserDto sysUserDto;

    private String institutionName;

    private String institutionCode;

    private String realName;

    // 课题名称
    private String researchName;

    // 版本号
    private Integer versionCode;

    // 版本名称
    private String versionName;

    // 主导机构ID
    private String majorInsId;

    // 主导机构名称
    private String majorInsName;

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public BaseInstitutionDto getBaseInstitutionDto() {
        return baseInstitutionDto;
    }

    public void setBaseInstitutionDto(BaseInstitutionDto baseInstitutionDto) {
        this.baseInstitutionDto = baseInstitutionDto;
    }

    public SysUserDto getSysUserDto() {
        return sysUserDto;
    }

    public void setSysUserDto(SysUserDto sysUserDto) {
        this.sysUserDto = sysUserDto;
    }

    @Override
    public String toString() {
        return "BaseDocDto [baseInstitutionDto=" + baseInstitutionDto + ", sysUserDto=" + sysUserDto
                + ", getBaseInstitutionDto()=" + getBaseInstitutionDto() + ", getSysUserDto()=" + getSysUserDto()
                + ", getDocId()=" + getDocId() + ", getInstitutionId()=" + getInstitutionId() + ", getResearchId()="
                + getResearchId() + ", getDocRows()=" + getDocRows() + ", getDocName()=" + getDocName()
                + ", getDocSourceName()=" + getDocSourceName() + ", getDocPath()=" + getDocPath() + ", getDoctypeId()="
                + getDoctypeId() + ", getDoctypeName()=" + getDoctypeName() + ", getDocStatus()=" + getDocStatus()
                + ", getDocCount()=" + getDocCount() + ", getIsMapping()=" + getIsMapping()
                + ", getInstitutionSnlVersionId()=" + getInstitutionSnlVersionId() + ", getIsLegal()=" + getIsLegal()
                + ", getIsPersistence()=" + getIsPersistence() + ", getIsClean()=" + getIsClean() + ", getIsEs()="
                + getIsEs() + ", getVersionId()=" + getVersionId() + ", getMappingStr()=" + getMappingStr()
                + ", getLegalStr()=" + getLegalStr() + ", getDeleteStatus()=" + getDeleteStatus() + ", getCreator()="
                + getCreator() + ", getAddTime()=" + getAddTime() + ", getUpdateTime()=" + getUpdateTime()
                + ", get_Id()=" + get_Id() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + "]";
    }

    public String getResearchName() {
        return researchName;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getMajorInsId() {
        return majorInsId;
    }

    public void setMajorInsId(String majorInsId) {
        this.majorInsId = majorInsId;
    }

    public String getMajorInsName() {
        return majorInsName;
    }

    public void setMajorInsName(String majorInsName) {
        this.majorInsName = majorInsName;
    }

}