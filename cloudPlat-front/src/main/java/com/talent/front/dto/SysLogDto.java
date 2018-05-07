package com.talent.front.dto;

import com.talent.front.entity.SysLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2017-12-05 <br/>
 * 描述：系统日志操作表Dto类
 */
public class SysLogDto extends SysLog {

	@Override
	public String toString() {
		return "SysLog ["
		 		+ "this.syslogId=" + this.getSyslogId() + ", "
		 		+ "this.syslogContent=" + this.getSyslogContent() + ", "
		 		+ "this.syslogTitle=" + this.getSyslogTitle() + ", "
		 		+ "this.syslogIp=" + this.getSyslogIp() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.syslogType=" + this.getSyslogType() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		+ "]";   
	}

}