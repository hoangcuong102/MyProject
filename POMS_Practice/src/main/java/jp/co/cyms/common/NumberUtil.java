/*
 * CLASS   : NumberUtil
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * NumberUtil
 * 
 * @author LSC WANG
 * @since 2014/12/02
 */
public class NumberUtil {

	/**
	 * NumberUtil's constructor
	 */
	public NumberUtil() {
	}

	/**
	 * String to BigDecimal
	 * 
	 * @param strValue String
	 * @return BigDecimal
	 */
	static public BigDecimal strToBigDecimal(String strValue) {
		if (strValue == null || strValue.isEmpty()) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(strValue);
		}
	}

	/**
	 * Compare of BigDecimal The return value<0:strValue1<strValue2 The return
	 * value=0:strValue1=strValue2 The return value>0:strValue1>strValue2
	 * 
	 * @param strValue1 String
	 * @param strValue2 String
	 * @return int
	 */
	static public int numberCompareto(String strValue1, String strValue2) {
		BigDecimal val1;
		BigDecimal val2;

		if (strValue1 == null || strValue1.isEmpty()) {
			val1 = new BigDecimal(0);
		} else {
			val1 = new BigDecimal(strValue1);
		}

		if (strValue2 == null || strValue2.isEmpty()) {
			val2 = new BigDecimal(0);
		} else {
			val2 = new BigDecimal(strValue2);
		}

		return val1.compareTo(val2);
	}

	/**
	 * Compare of BigDecimal <br>
	 * The return value = 0 : strValue1 = strValue2 <br>
	 * The return value > 0 : strValue1 > strValue2 <br>
	 * The return value < 0 : strValue1 < strValue2
	 * 
	 * @param Value1 BigDecimal
	 * @param Value2 BigDecimal
	 * @return int
	 */
	static public int numberCompareto(BigDecimal Value1, BigDecimal Value2) {
		if (Value1 == null) {
			Value1 = new BigDecimal(0);
		}

		if (Value2 == null) {
			Value2 = new BigDecimal(0);
		}

		return Value1.compareTo(Value2);
	}

	/**
	 * null to Zero <br>
	 * 
	 * @param val BigDecimal
	 * @return BigDecimal
	 */
	static public BigDecimal nulltoZero(BigDecimal val) {
		if (val == null) {
			return new BigDecimal(0);
		}

		return val;
	}

	/**
	 * null to Zero <br>
	 * 
	 * @param val Integer
	 * @return Integer
	 */
	static public Integer nulltoZero(Integer val) {
		if (val == null) {
			return new Integer(0);
		}

		return val;
	}

	/**
	 * format number.
	 * 
	 * @param d BigDecimal
	 * @param format String
	 * @return result
	 */
	public static String formatDecimal(BigDecimal d, String format) {
		if (d == null) {
			d = new BigDecimal(0);
		}
		DecimalFormat df = new DecimalFormat(format);

		return df.format(d);
	}
	
	/**
	 * format number.
	 * 
	 * @param timeZone String
	 * @return result int
	 */
	public static int getTimeZone(String timeZone) {
		String[] arrTimeZone = timeZone.split(":");
		if (arrTimeZone != null) {
			return Integer.parseInt(arrTimeZone[0]);
		}else {
			return 0;
		}
	}
	/**
	 * Parse a string to number.
	 * @param numberStr
	 * @return a number or zero if string is empty
	 * @throws NumberFormatException
	 */
	public static int parseIntOrZero(String numberStr) throws NumberFormatException{
		if(StringUtil.isEmpty(numberStr)){
			return 0;
		}
		return Integer.parseInt(numberStr);
	}
	
	/**
	 * check is numberic
	 * 
	 * @param s String
	 * @return result int
	 */
	public static boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}  
}
