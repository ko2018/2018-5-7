package com.talent.base.exception;

/**
 * 2017年11月13日
 * 
 * @author fwp 数据库异常
 */
public class DBException extends BaseException {
	private static final long serialVersionUID = 3787730660315875183L;

	/**
	 * constructor
	 * 
	 * @param code
	 *            code
	 * @param message
	 *            message
	 * @param e
	 *            Exception
	 */
	public DBException(ErrorCode code, Exception e) {
		super(code);
		this.code = code;
		this.exception = e;
	}

	/**
	 * constructor
	 * 
	 * @param code
	 *            code
	 * @param title
	 *            title
	 * @param message
	 *            message
	 * @param e
	 *            Exception
	 */
	public DBException(ErrorCode code, String title, Exception e) {
		super(code);
		this.code = code;
		this.exception = e;
		this.title = title;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.fhpt.core.exception.BaseException#getTitle()
	 */
	public String getTitle() {
		if (null == title || "".equals(title)) {
			return "数据库异常！";
		} else {
			return title;
		}
	}
}
