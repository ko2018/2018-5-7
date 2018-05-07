package com.talent.base.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.talent.base.exception.ErrorCode;
import com.talent.base.util.JacksonUtil;

public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5928969340566076291L;

	public static Integer SUCCESS = 1;
	public static Integer FAILURE = 2;
	public static Integer EXCEPTION = 3;
	public static Integer ERROR_SYS = 4;

	public static Map<Integer, String> map = new HashMap<Integer, String>();

	static {
		map.put(SUCCESS, "OK!");
		map.put(FAILURE, "处理失败!");
		map.put(EXCEPTION, "异常!");
		map.put(ERROR_SYS, "系统异常!");
	}

	/**
	 * 状态码
	 */
	private Integer status;// 1 处理成功 2处理失败 3系统异常

	private Long totalCount;

	/**
	 * 返回数据
	 */
	private String dataJson;

	/**
	 * 返回信息
	 */
	private String msg;

	private String jsessionId;

	public String getJsessionId() {
		return jsessionId;
	}

	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the dataJson
	 */
	public String getDataJson() {
		return dataJson;
	}

	/**
	 * @param dataJson
	 *            the dataJson to set
	 */
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @param status
	 * @param dataJson
	 * @param msg
	 */
	public Result(Integer status, String dataJson, String msg) {
		super();
		this.status = status;
		this.dataJson = dataJson;
		this.msg = msg;
	}

	public Result(Integer status, String dataJson, String msg, long totalCount) {
		super();
		this.status = status;
		this.dataJson = dataJson;
		this.msg = msg;
		this.totalCount = totalCount;
	}

	public Result() {
		super();
	}

	public Result(ErrorCode code) {
		super();
		this.status = FAILURE;
		this.msg = code.getMessage();
	}

	public static Result getSuccessPageResult(PageResult<?> pageResult) {
		String dataJson = JacksonUtil.toJSon(pageResult.getQueryResult());
		return new Result(SUCCESS, dataJson, map.get(SUCCESS), pageResult.getTotalCount());
	}

	public static Result getSuccessResult(String dataJson) {
		return new Result(SUCCESS, dataJson, map.get(SUCCESS));
	}

	public static Result getSuccessResult(String dataJson, String msg) {
		return new Result(SUCCESS, dataJson, msg);
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
