package com.talent.dcs.dto;

import com.talent.dcs.entity.BaseResearch;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-05 <br/>
 * 描述：科研管理表（课题表）Dto类
 */
public class BaseResearchDto extends BaseResearch {

    @Override
    public String toString() {
        return "BaseResearch [" + "this.researchId=" + this.getResearchId() + ", " + "this.researchName="
                + this.getResearchName() + ", " + "this.status=" + this.getStatus() + ", " + "this.briefInfo="
                + this.getBriefInfo() + ", " + "this.isDel=" + this.getIsDel() + ", " + "this.majorUserId="
                + this.getMajorUserId() + ", " + "this.majorUserName=" + this.getMajorUserName() + ", "
                + "this.majorInsId=" + this.getMajorInsId() + ", " + "this.majorInsName=" + this.getMajorInsName()
                + ", " + "this.creator=" + this.getCreator() + ", " + "this.beginTime=" + this.getBeginTime() + ", "
                + "this.endTime=" + this.getEndTime() + ", " + "this.addTime=" + this.getAddTime() + ", "
                + "this.updateUser=" + this.getUpdateUser() + ", " + "this.updateTime=" + this.getUpdateTime() + ", "
                + "]";
    }

}