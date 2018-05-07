package com.talent.front.dto;

import java.util.List;

import org.springframework.util.StringUtils;

import com.talent.front.constant.ValueTypeEnum;
import com.talent.front.entity.BaseDocValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-25 <br/>
 * 描述：文档值显示记录表Dto类
 */
public class BaseDocValueDto extends BaseDocValue {
    private BaseSnlDto baseSnlDto;

    private String snlCode;

    private String nameCn;

    // 术语值类型 数值-1 文本-2
    private String valuetypeId;

    // 类型名称
    private String valuetypeName;

    // 下限值
    private Double lowerLimit;

    // 上限值
    private Double upperLimit;

    // 关键字
    private String keyWord;

    // 合法值范围
    private String legalValue;

    private List<BaseDocValueDto> list;

    public String getSnlCode() {
        return snlCode;
    }

    public void setSnlCode(String snlCode) {
        this.snlCode = snlCode;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getValuetypeName() {
        return valuetypeName;
    }

    public void setValuetypeName(String valuetypeName) {
        this.valuetypeName = valuetypeName;
    }

    public BaseSnlDto getBaseSnlDto() {
        return baseSnlDto;
    }

    public void setBaseSnlDto(BaseSnlDto baseSnlDto) {
        this.baseSnlDto = baseSnlDto;
    }

    @Override
    public String toString() {
        return "BaseDocValue [" + "this.docvalueId=" + this.getDocvalueId() + ", " + "this.dictId=" + this.getDictId()
                + ", " + "this.docId=" + this.getDocId() + ", " + "this.docvalueValue=" + this.getDocvalueValue() + ", "
                + "this.docvalueValuenew=" + this.getDocvalueValuenew() + ", " + "this.docvalueNum="
                + this.getDocvalueNum() + ", " + "this.isLegal=" + this.getIsLegal() + ", " + "this.isReplace="
                + this.getIsReplace() + ", " + "this.deleteStatus=" + this.getDeleteStatus() + ", " + "this.creator="
                + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", " + "this.updateTime="
                + this.getUpdateTime() + ", " + "]";
    }

    public List<BaseDocValueDto> getList() {
        return list;
    }

    public void setList(List<BaseDocValueDto> list) {
        this.list = list;
    }

    public String getValuetypeId() {
        return valuetypeId;
    }

    public void setValuetypeId(String valuetypeId) {
        this.valuetypeId = valuetypeId;
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

    public String getLegalValue() {
        legalValue = "";
        String type = getValuetypeId();
        if (!StringUtils.isEmpty(type)) {
            if (type.equals(ValueTypeEnum.NUM.getCode()) && !StringUtils.isEmpty(getLowerLimit())
                    && !StringUtils.isEmpty(getUpperLimit())) {
                legalValue = getLowerLimit() + "--" + getUpperLimit();
            } else if (!StringUtils.isEmpty(getKeyWord())) {
                legalValue = getKeyWord();
            }
        }
        return legalValue;
    }

    public void setLegalValue(String legalValue) {
        this.legalValue = legalValue;
    }

}