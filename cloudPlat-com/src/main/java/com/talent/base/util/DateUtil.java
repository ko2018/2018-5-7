package com.talent.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.base.exception.BusinessException;
import com.talent.base.exception.ErrorCode;

/**
 * 日期处理工具类
 * 
 * @author jary0524
 * @date 2015年6月4日 下午1:59:46
 */
public class DateUtil {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 日期类型 年
	 */
	public static final String YEAR = "year";
	/**
	 * 日期类型 天
	 */
	public static final String DAY = "day";
	/**
	 * 日期类型 月
	 */
	public static final String MONTH = "month";
	/**
	 * 日期类型 时
	 */
	public static final String HOUR = "hour";
	/**
	 * 日期类型 分
	 */
	public static final String MINUTE = "minute";
	/**
	 * 日期类型 秒
	 */
	public static final String SECOND = "second";
	/**
	 * 升序
	 */
	public static final String ASC = "asc";
	/**
	 * 降序
	 */
	public static final String DESC = "desc";

	public static final String TIMEOUT = "timeout";

	public static final String DATE_YYYYMM = "yyyyMM";
	public static final String DATE_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	public final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	public final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

	public final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	public final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 *             luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			logger.info("日期格式不对！");
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	 * 
	 * @Methodname: transDateToString
	 * @Discription: 将日期转换为字符串
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式，参考系统常量
	 * @Return: String
	 * @Throws
	 * 
	 */
	// TODO public static String dateToString(Date date, String format) {
	// SimpleDateFormat sdf = new SimpleDateFormat(format);
	// String dateString = null;
	// try {
	// dateString = sdf.format(date);
	// return dateString;
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new BaseException();
	// }
	// }

	/**
	 * 
	 * @Methodname: transStringToDate
	 * @Discription: 将字符串转换成日期
	 * @param dateString
	 *            日期字符串
	 * @param format
	 *            日期格式，参考系统常量
	 * @Return: Date
	 * @Throws
	 * 
	 */
	// TODO public static Date stringToDate(String dateString, String format) {
	// SimpleDateFormat sdf = new SimpleDateFormat(format);
	// Date date = null;
	// try {
	// date = sdf.parse(dateString);
	// return date;
	// } catch (ParseException e) {
	// e.printStackTrace();
	// throw new BaseException(e.getMessage());
	// }
	// }

	/**
	 * 
	 * @Methodname: dateFormat
	 * @Discription: 格式化日期
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式，参考系统常量
	 * @Return: Date
	 * @Throws
	 * 
	 */
	// TODO public static Date dateFormat(Date date, String format) {
	// SimpleDateFormat sdf = new SimpleDateFormat(format);
	// try {
	// String dateString = sdf.format(date);
	// date = sdf.parse(dateString);
	// return date;
	// } catch (ParseException e) {
	// e.printStackTrace();
	// throw new BaseException(e.getMessage());
	// }
	// }

	/**
	 * 转换日期为指定格式的字符串
	 * 
	 * @param date0
	 *            待转换的日期
	 * @param style
	 *            日期格式,参见isDateStyle方法中的日期格式说明
	 * @return java.lang.String 如果date0为空,返回空字符串
	 */
	public static String formatDate(java.util.Date date0, String style) {
		if (date0 == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return sdf.format(date0);
	}

	/**
	 * 转换日期为指定格式的字符串
	 * 
	 * @param date0
	 *            待转换的日期
	 * @param style
	 *            日期格式,参见isDateStyle方法中的日期格式说明
	 * @return java.lang.String 如果date0为空,返回空字符串
	 */
	public static String formatDate(java.sql.Date date0, String style) {
		if (date0 == null) {
			return "";
		}
		return formatDate(new java.util.Date(date0.getTime()), style);
	}

	/**
	 * 转换日期为指定格式的字符串
	 * 
	 * @param date0
	 *            待转换的日期
	 * @param style
	 *            日期格式,参见isDateStyle方法中的日期格式说明
	 * @return java.lang.String 如果date0为空,返回空字符串
	 */
	public static String formatDate(java.sql.Timestamp date0, String style) {
		if (date0 == null) {
			return "";
		}
		return formatDate(new java.util.Date(date0.getTime()), style);
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param source
	 *            待转换的日期字符串,不能为空
	 * @param style
	 *            日期格式,参见isDateStyle方法中的日期格式说明
	 * @return java.util.Date 转换后的日期
	 */
	public static java.util.Date parseDate(String source, String style) {
		if (source == null || source.trim().equals(""))
			return null;
		source = source.trim();
		style = style.trim();
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return sdf.parse(source, new ParsePosition(0));
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param source
	 *            待转换的日期字符串,不能为空
	 * @param style
	 *            日期格式,参见isDateStyle方法中的日期格式说明
	 * @return java.util.Date 转换后的日期
	 */
	public static java.sql.Date strToSQLDate(String source, String style) {
		if (source == null || source.trim().equals(""))
			return null;
		source = source.trim();
		style = style.trim();
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return new java.sql.Date(sdf.parse(source, new ParsePosition(0)).getTime());
	}

	/**
	 * util.Date转换成sql.Date日期
	 * 
	 * @param source
	 *            待转换的util.Date日期,如果为空，取当前日期
	 * @return java.sql.Date 转换后的日期
	 */
	public static java.sql.Date utilToSQLDate(java.util.Date utilDate) {
		if (utilDate == null)
			utilDate = new java.util.Date();
		return new java.sql.Date(utilDate.getTime());
	}

	/**
	 * util.Date转换成sql.Date日期
	 * 
	 * @param source
	 *            待转换的util.Date日期,如果为空，取当前日期
	 * @return java.sql.Date 转换后的日期
	 */
	public static java.util.Date sqlToUtilDate(java.sql.Date sqlDate) {
		if (sqlDate == null)
			return new java.util.Date();
		String strDate = formatDate(sqlDate, "yyyyMMdd");
		return parseDate(strDate, "yyyyMMdd");
	}

	/**
	 * 
	 * @Methodname: changeDate
	 * @Discription: 改变日期
	 * @param date
	 *            日期
	 * @param type
	 *            改变日期的类型，参考系统常量
	 * @param number
	 *            改变日期的数目
	 * @throws BusinessException
	 * @Return: Date
	 * @Throws
	 * 
	 */
	public static Date changeDate(Date date, String type, int number) throws BusinessException {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (DateUtil.YEAR.equals(type))
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + number);
		else if (DateUtil.MONTH.equals(type))
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + number);
		else if (DateUtil.DAY.equals(type))
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + number);
		else if (DateUtil.HOUR.equals(type))
			calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + number);
		else if (DateUtil.MINUTE.equals(type))
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + number);
		else if (DateUtil.SECOND.equals(type))
			calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + number);
		else
			throw new BusinessException(ErrorCode.DateUtil_ERROE);

		return calendar.getTime();
	}

	/**
	 * 
	 * @Methodname: getCurrentDate
	 * @Discription: 获取当前服务时间,分布式时需要改此方法
	 * @return
	 * @Return: java.util.Date
	 * @Throws
	 * 
	 */
	public static java.util.Date getCurrentDate() {
		return new java.util.Date();

	}

	/**
	 * 
	 * @Methodname: getCurrentTimeMillis
	 * @return
	 * @Return: long
	 * @Throws
	 * 
	 */
	public final static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * @Methodname: getWeekDayString
	 * @Discription: 获取某天是星期几
	 * @return 星期几
	 * @Return: String
	 * @Throws
	 */
	public static String getWeekDayString(Date today) {
		String weekString = "";
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		weekString = dayNames[dayOfWeek - 1];
		return weekString;
	}

	/**
	 * 获取某日期的星期几
	 */
	public static int getWeekDay(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 
	 * @Methodname: getYMDByDate
	 * @Discription: 得到日期的年月日(包括时分秒)
	 * @param date
	 * @Return: Map<String,String>，第一个参数是DateUtil.YEAR（MONTH,DAY），第二个参数就是值
	 * @Throws
	 *
	 */
	public static Map<String, String> getYMDByDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		Map<String, String> ymd = new HashMap<String, String>();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		ymd.put(DateUtil.YEAR, String.valueOf(year));
		ymd.put(DateUtil.MONTH, String.valueOf(month));
		ymd.put(DateUtil.DAY, String.valueOf(day));
		ymd.put(DateUtil.HOUR, String.valueOf(hour));
		ymd.put(DateUtil.MINUTE, String.valueOf(minute));
		ymd.put(DateUtil.SECOND, String.valueOf(second));
		return ymd;
	}

	/**
	 * 计算两个日期之间相差的天数 ,注意，时间大的在前面，时间小的在后面
	 * 
	 * @param date1
	 * @param date2
	 */
	public static int diffDate(Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		return diffDate(calendar, calendar2);
	}

	/**
	 * 注意，时间大的在前面，时间小的在后面
	 */
	public static int diffDate(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 
	 * @Methodname: isMorning
	 * @Discription: 判断时间是否为上午
	 * @param date
	 *            时间
	 * @Return: boolean
	 * @Throws
	 * 
	 */
	public static boolean isMorning(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.HOUR_OF_DAY) >= 12) {
			return false;
		}
		return true;
	}

	/**
	 * 获取与某月间隔n个月的日期表示
	 * 
	 * @param currentMonth
	 *            带4位年份的月
	 * @param n
	 *            若n大于0,表示currentMonth之后(将来的)的月份,若n小于0,表示currentMonth之前(过去的)的月份
	 * @return 返回带4位年份的下一个月字符串表示,4位年份+2位月份(不足两位前面补0)
	 */
	public static String addMonth(String currentMonth, int n) {
		int year = Integer.parseInt(currentMonth.substring(0, 4));
		int month = Integer.parseInt(currentMonth.substring(4));
		month = year * 12 + month + n;
		year = month / 12;
		month = month - year * 12;
		if (month == 0) {
			year = year - 1;
			month = 12;
		}
		return new Integer(year * 100 + month).toString();
	}

	/**
	 * 获取与某月间隔n天的日期字符串表示,格式为yyyyMMdd
	 * 
	 * @param currentDate
	 *            格式位yyyyMMdd的日期表示
	 * @param n
	 *            若n大于0,表示currentDate之后(将来的)的日期,若n小于0,表示currentDate之前(过去的)的日期
	 * @return 返回带4位年份的下一个月字符串表示,4位年份+2位月份(不足两位前面补0)
	 */
	public static String addDateString(String currentDate, int n) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(parseDate(currentDate, "yyyyMMdd"));
		cl.add(Calendar.DATE, n);
		return formatDate(cl.getTime(), "yyyyMMdd");
	}

	/**
	 * 获取与某月间隔n天的日期字符串表示,格式为style
	 * 
	 * @param currentDate
	 *            格式位yyyyMMdd的日期表示
	 * @param n
	 *            若n大于0,表示currentDate之后(将来的)的日期,若n小于0,表示currentDate之前(过去的)的日期
	 * @return 返回带4位年份的下一个月字符串表示,4位年份+2位月份(不足两位前面补0)
	 */
	public static String addDateString(String currentDate, int n, String style) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(parseDate(currentDate, "yyyyMMdd"));
		cl.add(Calendar.DATE, n);
		return formatDate(cl.getTime(), style);
	}

	/**
	 * 获取与某月间隔n天的日期表示
	 * 
	 * @param currentDate
	 *            日期
	 * @param n
	 *            若n大于0,表示currentDate之后(将来的)的日期,若n小于0,表示currentDate之前(过去的)的日期
	 * @return java.util.Date 返回日期
	 */
	public static java.util.Date addDate(java.util.Date currentDate, int n) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(currentDate);
		cl.add(Calendar.DATE, n);
		return cl.getTime();
	}

	/**
	 * 
	 * @param currentDate
	 * @param code
	 * @param n
	 * @return
	 */
	public static Date addTime(Date currentDate, int filed, int n) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(currentDate);
		cl.add(filed, n);
		return cl.getTime();
	}

	/**
	 * 比较两个时间是否是同一天
	 * 
	 * @param remindDate
	 * @param date
	 * @return
	 */
	public static boolean isOneDay(Date srcDate, Date destDate) {
		if (srcDate == null || destDate == null) {
			return false;
		}
		Calendar src = Calendar.getInstance();
		src.setTime(srcDate);
		Calendar dest = Calendar.getInstance();
		dest.setTime(destDate);
		boolean isOneDay = false;
		if (src.get(Calendar.YEAR) == dest.get(Calendar.YEAR) && src.get(Calendar.MONTH) == dest.get(Calendar.MONTH)
				&& src.get(Calendar.DAY_OF_MONTH) == dest.get(Calendar.DAY_OF_MONTH)) {
			isOneDay = true;
		}
		return isOneDay;
	}

	/**
	 * 时间是否是工作日
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWorkDay(Date date) {
		if (date == null) {
			throw new BusinessException(ErrorCode.DateUtil_ERROE);
		}
		Calendar src = Calendar.getInstance();
		src.setTime(date);
		int dayOfWork = src.get(Calendar.DAY_OF_WEEK);
		return dayOfWork != Calendar.SATURDAY && dayOfWork != Calendar.SUNDAY;
	}

	/**
	 * 日期是否是同一个月
	 * 
	 * @param srcDate
	 * @param destDate
	 * @return
	 */
	public static boolean isOneMonth(Date srcDate, Date destDate) {
		if (srcDate == null || destDate == null) {
			throw new BusinessException(ErrorCode.DateUtil_ERROE);
		}
		Calendar src = Calendar.getInstance();
		src.setTime(srcDate);
		Calendar dest = Calendar.getInstance();
		dest.setTime(destDate);
		boolean isOneMonth = false;
		if (src.get(Calendar.YEAR) == dest.get(Calendar.YEAR) && src.get(Calendar.MONTH) == dest.get(Calendar.MONTH)) {
			isOneMonth = true;
		}
		return isOneMonth;
	}

	/**
	 * 日期是否是同一年
	 * 
	 * @param srcDate
	 * @param destDate
	 * @return
	 */
	public static boolean isOneYear(Date srcDate, Date destDate) {
		if (srcDate == null || destDate == null) {
			throw new BusinessException(ErrorCode.DateUtil_ERROE);
		}
		Calendar src = Calendar.getInstance();
		src.setTime(srcDate);
		Calendar dest = Calendar.getInstance();
		dest.setTime(destDate);
		boolean isOneYear = false;
		if (src.get(Calendar.YEAR) == dest.get(Calendar.YEAR)) {
			isOneYear = true;
		}
		return isOneYear;
	}

}
