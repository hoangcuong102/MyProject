/*
 * CLASS   : PageCheck
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.common;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * <p>
 * [クラス] PageCheck ページチェック
 * </p>
 * <p>
 * [概要] 画面項目のチェック用方法です。
 * </p>
 * <p>
 * [詳細] 画面項目のチェック用方法です。
 * </p>
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
public class PageCheck {

	/**
	 * PageCheckのコンストラクタ
	 */
	public PageCheck() {
	}

	/**
	 * 必須チェック
	 * 
	 * @param token Object チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorEmpty(Object token) {
		return StringUtil.isTrimEmpty(token);
	}

	/**
	 * select必須チェック
	 * 
	 * @param token Object チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorSelectEmpty(Object token) {
		return "".equals(token);
	}

	/**
	 * 文字列桁数チェック
	 * 
	 * @param token String チェック対象
	 * @param length int 桁数
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringLength(String token, int length) {
		if (StringUtil.isEmpty(token)) {
			return length != 0;
		} else {
			return length != token.length();
		}
	}

	/**
	 * 文字列最大桁数チェック
	 * 
	 * @param token String チェック対象
	 * @param length int 最大桁数
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringMaxLength(String token, int length) {
		if (StringUtil.isEmpty(token)) {
			return length < 0;
		} else {
			return length < token.length();
		}
	}

	/**
	 * 文字列整型チェック
	 * 
	 * <p>
	 * 文字列に、整型をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatInteger(String token) {
		return StringUtil.isEmpty(token) ? false : !Pattern.compile("^(([-]{0,1}[1-9][0-9]*)|0)$").matcher(token)
		    .find();
	}

	/**
	 * 文字列正整型チェック
	 * 
	 * <p>
	 * 文字列に、正整型をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatPosInteger(String token) {
		return StringUtil.isEmpty(token) ? false : !Pattern.compile("^(([1-9][0-9]*)|0)$").matcher(token).find();
	}

	/**
	 * 文字列浮点型チェック
	 * 
	 * <p>
	 * 文字列に、浮点型をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatDouble(String token) {
		return StringUtil.isEmpty(token) ? false : !Pattern.compile("^([1-9][0-9]*|0)[.][0-9][0-9]*$").matcher(token)
		    .find();
	}

	/**
	 * 文字列浮点型チェック
	 * 
	 * <p>
	 * 文字列に、浮点型をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @param iLength int
	 * @param fLength int
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatDouble(String token, int iLength, int fLength) {
		iLength = iLength - 1;
		fLength = fLength - 1;
		return StringUtil.isEmpty(token) ? false : !Pattern
		    .compile(
		        "^([-]{0,1}[1-9][0-9]{0," + Integer.toString(iLength) + "}|0)$|^[-]{0,1}(([1-9][0-9]{0,"
		            + Integer.toString(iLength) + "}|0)[.][0-9][0-9]{0," + Integer.toString(fLength) + "})$")
		    .matcher(token).find();
	}

	/**
	 * 文字列英数字チェック
	 * 
	 * <p>
	 * 文字列に、数字0-9、英字a-zA-Zをチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatLetterNumber(String token) {
		return StringUtil.isEmpty(token) ? false : !Pattern.compile("^[0-9a-zA-Z]*$").matcher(token).find();
	}

	/**
	 * 文字列数字チェック
	 * 
	 * <p>
	 * 文字列に、数字0-9をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatNumber(String token) {
		return StringUtil.isEmpty(token) ? false : !Pattern.compile("^[0-9]*$").matcher(token).find();
	}

	/**
	 * 文字列英字チェック
	 * 
	 * <p>
	 * 文字列に、英字a-zA-Zをチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatLetter(String token) {
		return StringUtil.isEmpty(token) ? false : !Pattern.compile("^[a-zA-Z]*$").matcher(token).find();
	}

	/**
	 * 文字列メールチェック
	 * 
	 * <p>
	 * 文字列のメールフォーマットをチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatEmail(String token) {
		return StringUtil.isEmpty(token) ? false : !Pattern
		    .compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*$").matcher(token).find();
	}

	/**
	 * 数値の範囲チェック
	 * 
	 * <p>
	 * 数値の範囲をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @param min int 最小値
	 * @param max int 最大値
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorIntegerRange(String token, int min, int max) {

		return StringUtil.isEmpty(token) ? false : !(min < Integer.valueOf(token).intValue() && Integer.valueOf(token)
		    .intValue() < max);
	}

	/**
	 * 浮点値の範囲チェック
	 * 
	 * <p>
	 * 浮点値の範囲をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @param min double 最小値
	 * @param max double 最大値
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorDoubleRange(String token, double min, double max) {

		return StringUtil.isEmpty(token) ? false : !(min < Double.valueOf(token).doubleValue() && Double
		    .valueOf(token).doubleValue() < max);
	}

	/**
	 * 文字列日期チェック
	 * 
	 * @param token String チェック対象
	 * @param format String 日期格式
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringFormatDate(String token, String format) {
		boolean isNotDate = false;
		if (!StringUtil.isEmpty(token)) {
			try {
				new DateUtil(token, format);
			} catch (ParseException e) {
				isNotDate = true;
			}
		}
		return isNotDate;
	}

	/**
	 * 文字列日期在指定日期之前チェック
	 * 
	 * @param token String チェック対象
	 * @param format String 日期格式
	 * @param date Date 日期
	 * @return boolean 結果
	 * @throws ParseException ParseException
	 */
	static public boolean errorStringDateBefore(String token, String format, Date date) throws ParseException {
		return StringUtil.isEmpty(token) ? false : new DateUtil(token, format).before(date);
	}

	/**
	 * compare
	 * 
	 * @param dest Compare dest
	 * @param src Compare src
	 * @return result boolean
	 */
	public static boolean isEqual(Object dest, Object src) {
		if (dest == null && src == null) {
			return true;
		}
		if (dest == null || src == null) {
			return false;
		}
		return dest.equals(src);
	}

	/**
	 * 文字列半角チェック
	 * 
	 * <p>
	 * 文字列に、半角をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringHalfChar(String token) {
		if (StringUtil.isEmpty(token)) {
			return false;
		} else {
			if (token.length() != token.getBytes().length) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 英数字チェック
	 * 
	 * <p>
	 * 文字列に、英数字をチェックする。
	 * </p>
	 * 
	 * @param token String チェック対象
	 * @return boolean 結果
	 * @throws なし
	 */
	static public boolean errorStringEnOrNumber(String token) {
		if (StringUtil.isEmpty(token)) {
			return false;
		} else {
			if (token.length() != token.getBytes().length) {
				return true;
			}
		}
		return !Pattern.compile("^[\\x20-\\x7e]*$").matcher(token).find();
	}
}
