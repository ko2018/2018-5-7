package com.talent.dcs.dto;

import com.talent.dcs.entity.BaseDocDefValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-04 <br/>
 * 描述：文档值通用不合法值记录Dto类
 */
public class BaseDocDefValueDto extends BaseDocDefValue {

    @Override
    public String toString() {
        return "BaseDocDefValue [" + "this.docdefvalueId=" + this.getDocdefvalueId() + ", " + "this.dictId="
                + this.getDictId() + ", " + "this.docId=" + this.getDocId() + ", " + "this.docdefvalueValue="
                + this.getDocdefvalueValue() + ", " + "this.docdefvalueValuenew=" + this.getDocdefvalueValuenew() + ", "
                + "this.docdefvalueNum=" + this.getDocdefvalueNum() + ", " + "this.isLegal=" + this.getIsLegal() + ", "
                + "this.isReplace=" + this.getIsReplace() + ", " + "this.deleteStatus=" + this.getDeleteStatus() + ", "
                + "this.creator=" + this.getCreator() + ", " + "this.addTime=" + this.getAddTime() + ", "
                + "this.updateTime=" + this.getUpdateTime() + ", " + "]";
    }

}