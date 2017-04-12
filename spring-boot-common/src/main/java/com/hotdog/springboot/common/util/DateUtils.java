package com.hotdog.springboot.common.util;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间转换工具类型 项目名称：basestation-common 类名称：DateUtils 类描述： 创建人：郭辰 创建时间：2016-4-24
 * 上午6:47:26 修改人：hisign 修改时间：2016-4-24 上午6:47:26 修改备注：
 * 
 * @version 3.1
 */
public class DateUtils {

	/**
	 * 获取当前系统时间长整型函数 方法功能说明： 创建：2016-8-3 下午4:06:52 by
	 * guochen @参数： @参数： @return @throws
	 */
	public static Long getSystemDateTimeForLong() {
		try {
			Date date = Calendar.getInstance().getTime();
			return date.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 长整型时间转DATE根据时间类型 方法功能说明： 创建：2016-8-3 下午4:14:28 by
	 * guochen @参数： @参数： @return @throws
	 */
	public static Date longToDate(long currentTime, String formatType) throws ParseException {
		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
		String sDateTime = getStrByDate(dateOld, formatType); // 把date类型的时间转换为string
		Date date = getDateByStr(sDateTime, formatType); // 把String类型转换为Date类型
		return date;
	}

	/**
	 * @throws ParseException date时间转Long根据时间格式 方法功能说明： 创建：2016-8-3 下午4:18:24 by
	 * guochen @参数： 时间 @参数： 时间格式 @return @throws
	 */
	public static Long DateToLong(Date date, String formatType) throws ParseException {
		String dateStr = getStrByDate(date, formatType); // 把date类型的时间转换为string
		Date newDate = getDateByStr(dateStr, formatType); // 把String类型转换为Date类型
		return newDate.getTime();
	}

	/**
	 * 时间格式字符串转Long 方法功能说明： 创建：2016-8-3 下午4:21:50 by guochen @参数：
	 * 时间格式字符串 @参数：时间格式 @return @throws
	 */
	public static Long StrToLong(String dateStr, String formatType) throws ParseException {
		Date newDate = getDateByStr(dateStr, formatType); // 把String类型转换为Date类型
		return newDate.getTime();
	}

	/**
	 * 时间格式字符串转Long 方法功能说明： 创建：2016-8-3 下午4:21:50 by guochen @参数：
	 * 时间格式字符串 @参数：时间格式 @return @throws
	 */
	public static Long StrToLong(String dateStr) throws ParseException {
		Date newDate = getDateByStr(dateStr, "yyyy-MM-dd"); // 把String类型转换为Date类型
		return newDate.getTime();
	}

	/**
	 * 长整型时间，转成指定格式字符串 方法功能说明： 创建：2016-8-3 下午4:23:31 by guochen @参数： 长整型值 @参数：
	 * 时间格式 @return @throws
	 */
	public static String LongToStr(long currentTime, String formatType) throws ParseException {

		return getStrByDate(new Date(currentTime), formatType);
	}

	/**
	 * 获取当前系统时间戳Timestamp 方法功能说明： 创建：2016-4-24 上午6:48:03 by
	 * guochen @参数： @参数： @return @throws
	 */
	public static Timestamp getSystemDateTimeStamp() {

		Date date = Calendar.getInstance().getTime();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);
		try {
			return string2Time(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp） 方法功能说明： 创建：2016-4-24
	 * 上午6:48:36 by guochen @参数： @参数： @return @throws
	 */
	public static Timestamp string2Time(String dateString) throws ParseException {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tsStr = dateString;
		try {
			ts = Timestamp.valueOf(tsStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 设置格式获取系统时间 方法功能说明： 创建：2016-4-24 下午7:16:16 by
	 * guochen @参数： @参数： @return @throws
	 */
	public static String getSystemDateStr(String gs) {
		Date date = Calendar.getInstance().getTime();
		DateFormat sdf = new SimpleDateFormat(gs);
		String dateString = sdf.format(date);
		try {
			return dateString;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串转date 方法功能说明： 创建：2016-4-24 上午6:57:40 by guochen @参数： datestr时间字符串 @参数：
	 * gs 时间格式 @return @throws
	 */
	public static Date getDateByStr(String datestr, String gs) throws ParseException {
		DateFormat sdf = new SimpleDateFormat(gs);
		Date dateVale = sdf.parse(datestr);
		try {
			return dateVale;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * DATE格式转换成String 方法功能说明: 创建人：zhangyongjie 创建时间：2016-5-18 下午3:03:00 返回值
	 * String
	 */
	public static String getStrByDate(Date date, String gs) throws ParseException {
		DateFormat sdf = new SimpleDateFormat(gs);
		String dateString = sdf.format(date);
		try {
			return dateString;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 日期月份加一个月
	 *
	 * @param datetime
	 *            日期(2014-11)
	 * @return 2014-10
	 */
	public static String dateFormat(String datetime, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, i);
		date = cl.getTime();
		return sdf.format(date);
	}

	/***
	 * 日期减一天、加一天
	 *
	 * @param option
	 *            传入类型 pro：日期减一天，next：日期加一天
	 * @param _date
	 *            2014-11-24
	 * @return 减一天：2014-11-23或(加一天：2014-11-25)
	 */
	public static String checkOption(String option, String _date, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cl = Calendar.getInstance();
		Date date = null;

		try {
			date = (Date) sdf.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cl.setTime(date);
		if ("pre".equals(option)) {
			// 时间减i天
			cl.add(Calendar.DAY_OF_MONTH, i);

		} else if ("next".equals(option)) {
			// 时间加i天
			cl.add(Calendar.DAY_OF_YEAR, i);
		} else {
			// do nothing
		}
		date = cl.getTime();
		return sdf.format(date);
	}

	/**
	 * 字符串类型的日期格式转换 customStringDateFormat("04/01/2014","mm/dd/yyyy",
	 * "yyyy-mm-dd") return 2014-04-01
	 *
	 * @param source
	 *            待转换字符串
	 * @param srcFormat
	 *            待转换字符串格式
	 * @param destFormat
	 *            输出字符串格式
	 * @return
	 */
	public static String customStringDateFormat(String source, String srcFormat, String destFormat) {
		String retString = "";
		if (StringUtils.isNotEmpty(source)) {
			SimpleDateFormat sdfSrc = new SimpleDateFormat(srcFormat);
			try {
				Date srcDate = sdfSrc.parse(source);
				if (srcDate != null) {
					SimpleDateFormat sdfDest = new SimpleDateFormat(destFormat);
					retString = sdfDest.format(srcDate);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retString;

	}

	/**
	 * 精确计算相隔天数的方法 方法功能说明： 创建：2016-4-24 上午6:49:27 by
	 * guochen @参数： @参数： @return @throws
	 */
	public static int getDaysBetween(Calendar d1, Calendar d2) {
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
	 * 字符串日期转换成中文格式日期
	 * 
	 * @param date
	 *            字符串日期 yyyy-MM-dd
	 * @return yyyy年M月d日
	 * @throws Exception
	 */
	public static String dateToCnDate(String date) throws Exception {
		String result = "";
		String[] cnDate = new String[] { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

		String ten = "十";
		String[] dateStr = date.split("-");
		for (int i = 0; i < dateStr.length; i++) {
			for (int j = 0; j < dateStr[i].length(); j++) {
				String charStr = dateStr[i];
				String str = String.valueOf(charStr.charAt(j));
				if (charStr.length() == 2) {
					if (charStr.equals("10")) {
						result += ten;
						break;
					} else {
						if (j == 0) {
							if (charStr.charAt(j) == '1')
								result += ten;
							else if (charStr.charAt(j) == '0')
								result += "";
							else
								result += cnDate[Integer.parseInt(str)] + ten;
						}
						if (j == 1) {
							if (charStr.charAt(j) == '0')
								result += "";
							else
								result += cnDate[Integer.parseInt(str)];
						}
					}
				} else {
					result += cnDate[Integer.parseInt(str)];
				}
			}
			if (i == 0) {
				result += "年";
				continue;
			}
			if (i == 1) {
				result += "月";
				continue;
			}
			if (i == 2) {
				result += "日";
				continue;
			}
		}
		return result;
	}
	
	
	/**
	 * 通过时间获取毫秒值
	 * @param millis
	 * @return
	 */
	public static String getTimeStrByMills(long millis,String format){
		SimpleDateFormat simpleDateFormatAll = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		Date date = c.getTime();
		String timeStr = simpleDateFormatAll.format(date);
		return timeStr;
	}
	
	   /**
     * 通过时间获取毫秒值
     * @param millis
     * @return
     */
    public static String getTimeStrByMills(long millis){
        SimpleDateFormat simpleDateFormatAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        Date date = c.getTime();
        String timeStr = simpleDateFormatAll.format(date);
        return timeStr;
    }

}
