package com.talent.front.dto;

import com.talent.front.entity.BaseResRatiotype;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-02-27 <br/>
 * 描述：医疗资源类型Dto类
 */
public class BaseResRatiotypeDto extends BaseResRatiotype {

    private SysUserDto sysUserDto;

    @Override
    public String toString() {
        return "BaseResRatiotype [" + "this.resratioTypeid=" + this.getResratioTypeid() + ", "
                + "this.resratioTypename=" + this.getResratioTypename() + ", " + "this.deleteStatus="
                + this.getDeleteStatus() + ", " + "this.creator=" + this.getCreator() + ", " + "this.addTime="
                + this.getAddTime() + ", " + "this.updateUser=" + this.getUpdateUser() + ", " + "this.updateTime="
                + this.getUpdateTime() + ", " + "]";
    }

    public SysUserDto getSysUserDto() {
        return sysUserDto;
    }

    public void setSysUserDto(SysUserDto sysUserDto) {
        this.sysUserDto = sysUserDto;
    }

}