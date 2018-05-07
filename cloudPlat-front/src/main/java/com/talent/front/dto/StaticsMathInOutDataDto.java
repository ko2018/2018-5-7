package com.talent.front.dto;

import java.util.Map;

import com.talent.base.util.JacksonUtil;
import com.talent.front.entity.StaticsMathInOutData;

/**
 * 版权：fwp8@163.com <br/>
 * 作者：fwp8@163.com <br/>
 * 生成日期：2018-03-05 <br/>
 * 描述：数学分析Dto类
 */
public class StaticsMathInOutDataDto extends StaticsMathInOutData {

	public Map<Object, Object> sDataOutMap;
	public Map<Object, Object> sDataInMap;
	public void setDataInMaps() {
		String dataIn = this.getSDataIn();
		if(dataIn != null) {
			sDataInMap = JacksonUtil.readValueByMap(dataIn);
		}
	}
	
	public void setDataOutMaps() {
		String dataOut = this.getSDataOut();
		if(dataOut != null) {
			sDataOutMap = JacksonUtil.readValueByMap(dataOut);
		}
	}
	
	@Override
	public String toString() {
		return "StaticsMathInOutData ["
		 		+ "this.id=" + this.getId() + ", "
		 		+ "this.sType=" + this.getSType() + ", "
		 		+ "this.sTypeFunc=" + this.getSTypeFunc() + ", "
		 		+ "this.sDataIn=" + this.getSDataIn() + ", "
		 		+ "this.sDataOut=" + this.getSDataOut() + ", "
		 		+ "this.sCrowd=" + this.getSCrowd() + ", "
		 		+ "this.exeStatus=" + this.getExeStatus() + ", "
		 		+ "this.deleteStatus=" + this.getDeleteStatus() + ", "
		 		+ "this.creator=" + this.getCreator() + ", "
		 		+ "this.addTime=" + this.getAddTime() + ", "
		 		+ "this.updateTime=" + this.getUpdateTime() + ", "
		+ "]";   
	}

}