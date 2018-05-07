package com.talent.front.dto;

import com.talent.base.util.CommonUtil;
import com.talent.front.entity.BaseDocLegal;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-22 <br/>
 * 描述：文档空值合法值记录表Dto类
 */
public class BaseDocLegalDto extends BaseDocLegal {

    // 标准术语
    private BaseSnlDto baseSnlDto;

    private String countNullOdds;

    private String countLegalOdds;

    private String countNolegalOdds;

    // 修改后不合法率
    private String countChangedOdds;

    private String snlCode;

    private String nameCn;

    private String valuetypeId;

    // 类型名称
    private String valuetypeName;

    // 下限值
    private Double lowerLimit;

    // 上限值
    private Double upperLimit;

    // 关键字
    private String keyWord;

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

    public String getCountNullOdds() {
        countNullOdds = CommonUtil.percentPrefix(this.getCountAll() - this.getCountLegal() - this.getCountNolegal(),
                this.getCountAll()) + "%";
        return countNullOdds;
    }

    public void setCountNullOdds(String countNullOdds) {
        this.countNullOdds = countNullOdds;
    }

    public String getCountLegalOdds() {
        countLegalOdds = CommonUtil.percentPrefix(this.getCountLegal(), this.getCountAll()) + "%";
        return countLegalOdds;
    }

    public void setCountLegalOdds(String countLegalOdds) {
        this.countLegalOdds = countLegalOdds;
    }

    public String getCountNolegalOdds() {
        countNolegalOdds = CommonUtil.percentPrefix(this.getCountNolegal(), this.getCountAll()) + "%";
        return countNolegalOdds;
    }

    public void setCountNolegalOdds(String countNolegalOdds) {
        this.countNolegalOdds = countNolegalOdds;
    }

    public BaseSnlDto getBaseSnlDto() {
        return baseSnlDto;
    }

    public void setBaseSnlDto(BaseSnlDto baseSnlDto) {
        this.baseSnlDto = baseSnlDto;
    }

    @Override
    public String toString() {
        return "BaseDocLegal [" + "this.docvalueId=" + this.getDocvalueId() + ", " + "this.dictId=" + this.getDictId()
                + ", " + "this.docId=" + this.getDocId() + ", " + "this.countNull=" + this.getCountNull() + ", "
                + "this.countLegal=" + this.getCountLegal() + ", " + "this.countNolegal=" + this.getCountNolegal()
                + ", " + "this.countAll=" + this.getCountAll() + ", " + "this.deleteStatus=" + this.getDeleteStatus()
                + ", " + "this.creator=" + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", "
                + "this.updateTime=" + this.getUpdateTime() + ", " + "]";
    }

    public String getCountChangedOdds() {
        int noLegalChange = this.getCountNolegal() - this.getCountChanged();
        if (noLegalChange >= 0) {
            countChangedOdds = CommonUtil.percentPrefix(noLegalChange, this.getCountAll()) + "%";
        } else {
            countChangedOdds = "0%";
        }
        return countChangedOdds;
    }

    public void setCountChangedOdds(String countChangedOdds) {
        this.countChangedOdds = countChangedOdds;
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

}