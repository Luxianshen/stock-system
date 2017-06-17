package com.zjht.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 */
public class DateUtils {

	private static DateFormat daydf = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat daydf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 将字符串格式化为日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatyyyyMMdd(String date) {
		try {
			return daydf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将时间格式化为日期
	 * 
	 */
	public static Date formatyyyyMMddHH(Date date) {
		try {
			return daydf1.parse(daydf1.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间格式化为字符串
	 */
	public static String formatDateToStr(Date date){
		return daydf.format(date);
	}

	/**
	 * 计算时间的前一天
	 */
	public static Date getBeforeDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day-1);
		return calendar.getTime();
	}

	/**
	 * 计算时间的后一天
	 */
	public static Date getAfterDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day+1);
        return calendar.getTime();
	}


}
