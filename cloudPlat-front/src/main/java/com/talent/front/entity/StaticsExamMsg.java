package com.talent.front.entity;

import java.io.Serializable;
/**
 * @author zhangqian
 * @time 2018年1月5日 上午11:02:02
 * @version 1.0v
 */
public class StaticsExamMsg implements Serializable {

	private static final long serialVersionUID = 1L;
	private String examID;
	private String examTime;
	public String getExamID() {
		return examID;
	}
	public void setExamID(String examID) {
		this.examID = examID;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
		
		
}
