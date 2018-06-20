/*
 * CLASS   : DateUtil
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * DateUtil
 *
 * @author LSC LU
 * @since 2014/08/01
 */
public class DateUtil extends Date {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2688014145762391076L;

	private static TimeZone SINGAPORE_TIMEZONE = TimeZone.getTimeZone("Asia/Singapore");

	/**
	 * DateUtil's constructor
	 */
	public DateUtil() {
		super();
	}

	/**
	 * DateUtil's constructor
	 *
	 * @param d
	 *            Date
	 */
	public DateUtil(Date d) {
		this.setTime(d.getTime());
	}

	/**
	 * DateUtil's constructor
	 *
	 * @param token
	 *            String
	 * @param format
	 *            String
	 * @throws ParseException
	 *             ParseException
	 */
	public DateUtil(String token, String format) throws ParseException {
		java.text.SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		this.setTime(dateFormat.parse(token).getTime());
	}

	/**
	 * toString
	 *
	 * @param format
	 *            String
	 * @return formatted date
	 */
	public String toString(String format) {
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(format);
		return dateFormat.format(this);
	}

	/**
	 * addDay
	 *
	 * @param number
	 *            int
	 * @return DateUtil
	 */
	public DateUtil addDay(int number) {
		Calendar c = Calendar.getInstance();
		c.setTime(this);
		c.add(Calendar.DATE, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * addMonth
	 *
	 * @param number
	 *            int
	 * @return DateUtil
	 */
	public DateUtil addMonth(int number) {
		Calendar c = Calendar.getInstance();
		c.setTime(this);
		c.add(Calendar.MONTH, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * addMonth
	 *
	 * @param number
	 *            int
	 * @return DateUtil
	 */
	public static DateUtil addMonth(Date date, int number) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * addYear
	 *
	 * @param number
	 *            int
	 * @return DateUtil
	 */
	public DateUtil addYear(int number) {
		Calendar c = Calendar.getInstance();
		c.setTime(this);
		c.add(Calendar.YEAR, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * addHour
	 *
	 * @param number
	 *            int
	 * @return DateUtil
	 */
	public DateUtil addHour(int number) {
		Calendar c = Calendar.getInstance();
		c.setTime(this);
		c.add(Calendar.HOUR, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * addMinute
	 *
	 * @param number
	 *            int
	 * @return DateUtil
	 */
	public DateUtil addMinute(int number) {
		Calendar c = Calendar.getInstance();
		c.setTime(this);
		c.add(Calendar.MINUTE, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * addSecond
	 *
	 * @param number
	 *            int
	 * @return DateUtil
	 */
	public DateUtil addSecond(int number) {
		Calendar c = Calendar.getInstance();
		c.setTime(this);
		c.add(Calendar.SECOND, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * format date by the specified format in English local.
	 *
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return formatted date string
	 */
	public static String formatDate(Date date, String format) {

		return formatDate(date, format, Locale.ENGLISH);
	}

	/**
	 * format date by the specified format in English local.
	 *
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return formatted date string
	 */
	public static String formatDateForReport(Date date, String format) {

		return formatDateForReport(date, format, Locale.ENGLISH);
	}

	/**
	 * format date by the specified format in English local.
	 *
	 * @param date
	 *            String
	 * @param formatBefore
	 *            String
	 * @param formatEnd
	 *            String
	 * @return String
	 * @throws ParseException
	 */
	public static String formatDate(String date, String formatBefore, String formatEnd) throws ParseException {

		Date dateBefore = DateUtil.parseStrToDate(date, formatBefore);
		return DateUtil.formatDate(dateBefore, formatEnd);
	}

	/**
	 * format date by the specified format in specified local.
	 *
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @param locale
	 *            Locale
	 * @return formatted date string
	 */
	public static String formatDate(Date date, String format, Locale locale) {

		if (date == null) {
			return "";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
		dateFormat.setTimeZone(SINGAPORE_TIMEZONE);
		return dateFormat.format(date);
	}

	/**
	 * format date by the specified format in specified local.
	 *
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @param locale
	 *            Locale
	 * @return formatted date string
	 */
	public static String formatDateForReport(Date date, String format, Locale locale) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * parse a date string to Date type by the specified format in English local.
	 *
	 * @param dateStr
	 *            String
	 * @param format
	 *            String
	 * @return Date
	 * @throws ParseException
	 *             ParseException
	 */
	public static Date parseStrToDate(String dateStr, String format) throws ParseException {

		return parseStrToDate(dateStr, format, Locale.ENGLISH);
	}

	/**
	 * parse a date string to Date type by the specified format in specified local.
	 *
	 * @param dateStr
	 *            String
	 * @param format
	 *            String
	 * @param locale
	 *            Locale
	 * @return Date
	 * @throws ParseException
	 *             ParseException
	 */
	public static Date parseStrToDate(String dateStr, String format, Locale locale) throws ParseException {

		if (!isDate(dateStr, format, locale)) {
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);

		return dateFormat.parse(dateStr);
	}

	/**
	 * getBussinessDate
	 *
	 * @return Date
	 */
	public static Date doGetBussinessDate() {

		Date resultDate = new Date();
		Calendar resultDay = Calendar.getInstance();
		resultDay.setTime(resultDate);
		resultDay.set(Calendar.HOUR_OF_DAY, 0);
		resultDay.set(Calendar.MINUTE, 0);
		resultDay.set(Calendar.SECOND, 0);
		resultDay.set(Calendar.MILLISECOND, 0);

		// today
		Date now = new Date();
		Calendar nowDay = Calendar.getInstance();
		nowDay.setTime(now);

		// bussiness day start : today 08:00
		Calendar bDayStart = Calendar.getInstance();
		bDayStart.setTime(now);
		bDayStart.set(Calendar.HOUR_OF_DAY, 8);
		bDayStart.set(Calendar.MINUTE, 0);
		bDayStart.set(Calendar.SECOND, 0);
		bDayStart.set(Calendar.MILLISECOND, 0);

		if (nowDay.compareTo(bDayStart) < 0) {
			resultDay.add(Calendar.DAY_OF_MONTH, -1);
		}

		return resultDay.getTime();
	}

	/**
	 * getBussinessDate
	 *
	 * @param dt
	 *            Date
	 * @return Date
	 */
	public static Date doGetBussinessDate(Date dt) {

		Calendar resultDay = Calendar.getInstance();
		resultDay.setTime(dt);
		resultDay.set(Calendar.HOUR_OF_DAY, 0);
		resultDay.set(Calendar.MINUTE, 0);
		resultDay.set(Calendar.SECOND, 0);
		resultDay.set(Calendar.MILLISECOND, 0);

		Calendar nowDay = Calendar.getInstance();
		nowDay.setTime(dt);

		// bussiness day start : today 08:00
		Calendar bDayStart = Calendar.getInstance();
		bDayStart.setTime(dt);
		bDayStart.set(Calendar.HOUR_OF_DAY, 8);
		bDayStart.set(Calendar.MINUTE, 0);
		bDayStart.set(Calendar.SECOND, 0);
		bDayStart.set(Calendar.MILLISECOND, 0);

		if (nowDay.compareTo(bDayStart) < 0) {
			resultDay.add(Calendar.DAY_OF_MONTH, -1);
		}

		return resultDay.getTime();
	}

	/**
	 * check if the input string is a valid date by the default format(dd.MMM.yyyy).
	 *
	 * @param dateStr
	 *            dateStr
	 * @return result
	 */
	public static boolean isDate(String dateStr) {

		return isDate(dateStr, DateFormat.DDMMMYYYY, Locale.ENGLISH);
	}

	/**
	 * check if the input string is a valid date by the specified format in English
	 * locale.
	 *
	 * @param dateStr
	 *            dateStr
	 * @param dateFormat
	 *            dateFormat
	 * @return result
	 */
	public static boolean isDate(String dateStr, String dateFormat) {

		return isDate(dateStr, dateFormat, Locale.ENGLISH);
	}

	/**
	 * check if the input string is a valid date by the specified format in the
	 * specified locale.
	 *
	 * @param dateStr
	 *            dateStr
	 * @param dateFormat
	 *            dateFormat
	 * @param locale
	 *            Locale
	 * @return result
	 */
	public static boolean isDate(String dateStr, String dateFormat, Locale locale) {

		if (StringUtil.isEmpty(dateStr)) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);
		sdf.setLenient(false);

		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	/**
	 * add days to a specified date
	 *
	 * @param date
	 *            Date
	 * @param days
	 *            Integer
	 * @return result
	 */
	public static Date addDay(Date date, Integer days) {
		if (date == null) {
			return null;
		}
		if (days == null) {
			return date;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);

		return c.getTime();
	}

	/**
	 * addHour
	 *
	 * @param number
	 *            int
	 * @return Date
	 */
	public static Date addHour(Date date, int number) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * addMinute
	 *
	 * @param number
	 *            int
	 * @return Date
	 */
	public static Date addMinute(Date date, int number) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, number);

		return new DateUtil(c.getTime());
	}

	/**
	 * change timezone To Minute
	 *
	 * @param timezone
	 *            String
	 * @return int
	 */
	public static int timezoneToMinute(String timezone) {
		Integer totalMinute = 0;
		String[] timezoneArray = timezone.split(":");
		Integer hour = Integer.valueOf(timezoneArray[0]);
		Integer minute = Integer.valueOf(timezoneArray[1]);
		if (hour >= 0) {
			totalMinute = hour * 60 + minute;
		} else {
			totalMinute = hour * 60 - minute;
		}
		return totalMinute;
	}

	/**
	 * Get business date
	 *
	 * @param serverTimezone
	 *            Server Time Zone
	 * @param clientTimezone
	 *            Client Time Zone
	 * @param startTime
	 *            Start Time
	 * @return business date
	 */
	public static String doGetBusinessDate(String serverTimezone, String clientTimezone, String startTime) {
		Calendar calendar = Calendar.getInstance(SINGAPORE_TIMEZONE);
		if (serverTimezone == null || clientTimezone == null || startTime == null) {
			return formatDate(calendar.getTime(), "dd/MMM/yyyy");
		}
		calendar.add(Calendar.HOUR_OF_DAY, -Integer.valueOf(serverTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, -Integer.valueOf(serverTimezone.split(":")[1]));
		calendar.add(Calendar.HOUR_OF_DAY, Integer.valueOf(clientTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, Integer.valueOf(clientTimezone.split(":")[1]));
		if (calendar.get(Calendar.HOUR_OF_DAY) < Integer.valueOf(startTime.split(":")[0])) {
			calendar.add(Calendar.DATE, -1);
		}
		if (calendar.get(Calendar.HOUR_OF_DAY) == Integer.valueOf(startTime.split(":")[0])) {
			if (calendar.get(Calendar.MINUTE) < Integer.valueOf(startTime.split(":")[1])) {
				calendar.add(Calendar.DATE, -1);
			}
		}
		return formatDate(calendar.getTime(), "dd/MMM/yyyy");
	}

	/**
	 * Get business date with hour
	 *
	 * @param serverTimezone
	 *            Server Time Zone
	 * @param clientTimezone
	 *            Client Time Zone
	 * @param startTime
	 *            Start Time
	 * @return business date
	 */
	public static String doGetBusinessDateWithHour(String serverTimezone, String clientTimezone, String startTime) {
		Calendar calendar = Calendar.getInstance(SINGAPORE_TIMEZONE);
		if (serverTimezone == null || clientTimezone == null || startTime == null) {
			return formatDate(calendar.getTime(), "dd/MMM/yyyy kk:mm");
		}
		calendar.add(Calendar.HOUR_OF_DAY, -Integer.valueOf(serverTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, -Integer.valueOf(serverTimezone.split(":")[1]));
		calendar.add(Calendar.HOUR_OF_DAY, Integer.valueOf(clientTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, Integer.valueOf(clientTimezone.split(":")[1]));
		if (calendar.get(Calendar.HOUR_OF_DAY) < Integer.valueOf(startTime.split(":")[0])) {
			calendar.add(Calendar.DATE, -1);
		}
		if (calendar.get(Calendar.HOUR_OF_DAY) == Integer.valueOf(startTime.split(":")[0])) {
			if (calendar.get(Calendar.MINUTE) < Integer.valueOf(startTime.split(":")[1])) {
				calendar.add(Calendar.DATE, -1);
			}
		}
		return formatDate(calendar.getTime(), "dd/MMM/yyyy kk:mm");
	}

	/**
	 * Get business date by input format
	 *
	 * @param serverTimezone
	 *            Server Time Zone
	 * @param clientTimezone
	 *            Client Time Zone
	 * @param startTime
	 *            Start Time
	 * @return business date
	 */
	public static String doGetBusinessDate(String serverTimezone, String clientTimezone, String startTime,
			String format) {
		Calendar calendar = Calendar.getInstance(SINGAPORE_TIMEZONE);
		if (serverTimezone == null || clientTimezone == null || startTime == null) {
			return formatDate(calendar.getTime(), format);
		}
		calendar.add(Calendar.HOUR_OF_DAY, -Integer.valueOf(serverTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, -Integer.valueOf(serverTimezone.split(":")[1]));
		calendar.add(Calendar.HOUR_OF_DAY, Integer.valueOf(clientTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, Integer.valueOf(clientTimezone.split(":")[1]));
		if (calendar.get(Calendar.HOUR_OF_DAY) < Integer.valueOf(startTime.split(":")[0])) {
			calendar.add(Calendar.DATE, -1);
		}
		if (calendar.get(Calendar.HOUR_OF_DAY) == Integer.valueOf(startTime.split(":")[0])) {
			if (calendar.get(Calendar.MINUTE) < Integer.valueOf(startTime.split(":")[1])) {
				calendar.add(Calendar.DATE, -1);
			}
		}
		return formatDate(calendar.getTime(), format);
	}

	/**
	 * Get date for report
	 *
	 * @param serverTimezone
	 *            Server Time Zone
	 * @param clientTimezone
	 *            Client Time Zone
	 * @return business date
	 */
	public static String doGetDateForReport(String serverTimezone, String clientTimezone, String format) {
		Calendar calendar = Calendar.getInstance();
		if (serverTimezone == null || clientTimezone == null) {
			return formatDate(calendar.getTime(), format);
		}
		calendar.add(Calendar.HOUR_OF_DAY, -Integer.valueOf(serverTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, -Integer.valueOf(serverTimezone.split(":")[1]));
		calendar.add(Calendar.HOUR_OF_DAY, Integer.valueOf(clientTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, Integer.valueOf(clientTimezone.split(":")[1]));

		return formatDate(calendar.getTime(), format);
	}

	/**
	 * Get date for report
	 *
	 * @param serverTimezone
	 *            Server Time Zone
	 * @param clientTimezone
	 *            Client Time Zone
	 * @return business date
	 * @throws ParseException
	 */
	public static String doGetDateForReport2(String clientTimezone, String format, String clientTime,
			String timeZoneClient) throws ParseException {
		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATE_FROM_CLIENT);
		if (!StringUtil.isNullOrEmpty(clientTime)) {
			Date date = sdf.parse(clientTime);
			calendar.setTime(date);
		}

		if (timeZoneClient != null && !"".equals(timeZoneClient)) {
			calendar.add(Calendar.HOUR_OF_DAY, -Integer.valueOf(timeZoneClient));
		}

		if (clientTimezone == null) {
			return formatDateForReport(calendar.getTime(), format);
		}
		calendar.add(Calendar.HOUR_OF_DAY, Integer.valueOf(clientTimezone.split(":")[0]));
		calendar.add(Calendar.MINUTE, Integer.valueOf(clientTimezone.split(":")[1]));

		return formatDateForReport(calendar.getTime(), format);
	}

	/**
	 *
	 * @param String format
	 * @since 2015/24/07
	 * @return String
	 */
	public static String doGetStringCurrentDateWithFormat(String format) {
		SimpleDateFormat dt = new SimpleDateFormat(format);
		Date currentDate = new Date();
		String strReturn = dt.format(currentDate);
		return strReturn;
	}
}
