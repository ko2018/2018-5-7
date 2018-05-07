package com.talent.front.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talent.front.entity.BaseKeyword;
import com.talent.front.entity.BaseKeywordUse;
import com.talent.front.entity.BaseSnl;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-26 <br/>
 * 描述：关键字表Dto类
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({ "isDel" })
public class BaseKeywordDto extends BaseKeyword {

    private static final long serialVersionUID = -5818760705683184781L;

    private List<BaseKeywordUse> baseKeywordUse = new ArrayList();

    private BaseSnl baseSnl;

    private String insName;

    private String insCode;

    public String getInsCode() {
        return insCode;
    }

    public void setInsCode(String insCode) {
        this.insCode = insCode;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public BaseSnl getBaseSnl() {
        return baseSnl;
    }

    public void setBaseSnl(BaseSnl baseSnl) {
        this.baseSnl = baseSnl;
    }

    public List<BaseKeywordUse> getBaseKeywordUse() {
        return baseKeywordUse;
    }

    public void setBaseKeywordUse(List<BaseKeywordUse> baseKeywordUse) {
        this.baseKeywordUse = baseKeywordUse;
    }

    @Override
    public String toString() {
        return "BaseKeyword [" + "this.keywordId=" + this.getKeywordId() + ", " + "this.insId=" + this.getInsId() + ", "
                + "this.snlId=" + this.getSnlId() + ", " + "this.dictId=" + this.getDictId() + ", "
                + "this.keywordCode=" + this.getKeywordCode() + ", " + "this.keywordContent=" + this.getKeywordContent()
                + ", " + "this.keywordDesc=" + this.getKeywordDesc() + ", " + "this.isDel=" + this.getIsDel() + ", "
                + "this.creator=" + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", "
                + "this.updateUser=" + this.getUpdateUser() + ", " + "this.updateTime=" + this.getUpdateTime() + ", "
                + "]";
    }

}