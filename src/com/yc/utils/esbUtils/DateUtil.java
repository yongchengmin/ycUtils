package com.yc.utils.esbUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}
	
	
	public static long timeDiff(Date date1, Date date2) {
		  return Math.abs((date2.getTime() - date1.getTime()));// 寰楀埌涓よ�鐨勬绉掓暟
	}
	
	/**
	 * 系统时间增加秒
	 * @param amount:增加的秒数
	 * @return
	 */
	public static Long addSecond(int amount){
		long time = System.currentTimeMillis();
		Date d = new Date(time);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.SECOND, amount);
		return c.getTimeInMillis();
	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 鎸囧畾涓�釜鏃ユ湡
		Date date = dateFormat.parse("2013-6-1 23:59:59");
		Date date2 = dateFormat.parse("2013-6-2 23:59:59");
		//System.out.println(isSameDate(date, date2));
		System.out.println(timeDiff(date2, date));
	}

}
