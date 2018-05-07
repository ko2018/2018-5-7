package com.talent.front.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：标准术语表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "isDel" })
public class BaseSnlDto extends BaseSnl {

    private static final long serialVersionUID = 931561938825069042L;

    private BaseSnlRuleValueDto baseSnlRuleValueDto;

    private String baseSnlRuleValueJson;

    // 是否和机构属性有对应关系
    private boolean isMapping = false;

    // 和机构属性有对应关系的IDs
    private String institutionSnlIds;

    // 术语值类型
    private String valuetypeId;

    // 类型名称
    private String valuetypeName;

    // 下限值
    private Double lowerLimit;

    // 上限值
    private Double upperLimit;

    // 关键字
    private String keyWord;

    public String getBaseSnlRuleValueJson() {
        return baseSnlRuleValueJson;
    }

    public void setBaseSnlRuleValueJson(String baseSnlRuleValueJson) {
        this.baseSnlRuleValueJson = baseSnlRuleValueJson;
    }

    private String tmpVal;

    // 适应前端组件添加附属字段 仅供展示
    private String id;

    // 适应前端组件添加附属字段 仅供展示
    private String label;

    private List<BaseSnlDto> children;

    public String getTmpVal() {
        return tmpVal;
    }

    public void setTmpVal(String tmpVal) {
        this.tmpVal = tmpVal;
    }

    public BaseSnlRuleValueDto getBaseSnlRuleValueDto() {
        return baseSnlRuleValueDto;
    }

    public void setBaseSnlRuleValueDto(BaseSnlRuleValueDto baseSnlRuleValueDto) {
        this.baseSnlRuleValueDto = baseSnlRuleValueDto;
    }

    public String getId() {
        return this.getSnlId();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return this.getNameCn();
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<BaseSnlDto> getChildren() {
        return children;
    }

    public void setChildren(List<BaseSnlDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "BaseSnl [" + "this.snlId=" + this.getSnlId() + ", " + "this.snlType=" + this.getSnlType() + ", "
                + "this.snlTypeName=" + this.getSnlTypeName() + ", " + "this.snlCode=" + this.getSnlCode() + ", "
                + "this.nameCn=" + this.getNameCn() + ", " + "this.fullNameCn=" + this.getFullNameCn() + ", "
                + "this.shortNameCn=" + this.getShortNameCn() + ", " + "this.nameUs=" + this.getNameUs() + ", "
                + "this.shortNameUs=" + this.getShortNameUs() + ", " + "this.parentCode=" + this.getParentCode() + ", "
                + "this.depth=" + this.getDepth() + ", " + "this.isLeaf=" + this.getIsLeaf() + ", " + "this.dictId="
                + this.getDictId() + ", " + "this.creator=" + this.getCreator() + ", " + "this.addTime="
                + this.getAddTime() + ", " + "this.updateUser=" + this.getUpdateUser() + ", " + "this.updateTime="
                + this.getUpdateTime() + ", " + "]";
    }

    public boolean isMapping() {
        return isMapping;
    }

    public void setMapping(boolean isMapping) {
        this.isMapping = isMapping;
    }

    public String getValuetypeId() {
        return valuetypeId;
    }

    public void setValuetypeId(String valuetypeId) {
        this.valuetypeId = valuetypeId;
    }

    public String getValuetypeName() {
        return valuetypeName;
    }

    public void setValuetypeName(String valuetypeName) {
        this.valuetypeName = valuetypeName;
    }

    public Double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getInstitutionSnlIds() {
        return institutionSnlIds;
    }

    public void setInstitutionSnlIds(String institutionSnlIds) {
        this.institutionSnlIds = institutionSnlIds;
    }

}