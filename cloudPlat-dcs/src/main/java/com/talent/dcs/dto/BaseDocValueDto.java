package com.talent.dcs.dto;

import com.talent.dcs.entity.BaseDocValue;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-01-17 <br/>
 * 描述：文档值显示记录表Dto类
 */
public class BaseDocValueDto extends BaseDocValue {

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

}