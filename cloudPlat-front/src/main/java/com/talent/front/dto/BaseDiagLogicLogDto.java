package com.talent.front.dto;

import com.talent.front.entity.BaseDiagLogicLog;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-02 <br/>
 * 描述：诊断逻辑日志Dto类
 */
public class BaseDiagLogicLogDto extends BaseDiagLogicLog {

	@Override
	public String toString() {
		return "BaseDiagLogicLog ["
		 		+ "this.logId=" + this.getLogId() + ", "
		 		+ "this.logicId=" + this.getLogicId() + ", "
		 		+ "this.operateType=" + this.getOperateType() + ", "
		 		+ "this.processStatus=" + this.getProcessStatus() + ", "
		 		+ "this.logTime=" + this.getLogTime() + ", "
		 		+ "this.operator=" + this.getOperator() + ", "
		+ "]";   
	}

}