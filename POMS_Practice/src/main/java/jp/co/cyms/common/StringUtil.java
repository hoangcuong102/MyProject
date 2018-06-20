/*
 * CLASS   : StringUtil
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01縲�SC縲�U CREATE
 */
package jp.co.cyms.common;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * [繧ｯ繝ｩ繧ｹ] StringUtil 譁�ｭ怜�繝ｦ繝ｼ繝�ぅ繝ｪ繝�ぅ
 * </p>
 * <p>
 * [讎りｦ‐ 譁�ｭ怜�縺ｮ謫堺ｽ懊ｒ陬懷�縺吶ｋ縲�
 * </p>
 * <p>
 * [隧ｳ邏ｰ] 譁�ｭ怜�縺ｮ謫堺ｽ懊ｒ陬懷�縺吶ｋ縲�
 * </p>
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
public class StringUtil {

	/**
	 * Language縺ｮ繧ｳ繝ｳ繧ｹ繝医Λ繧ｯ繧ｿ
	 */
	public StringUtil() {
	}

	/**
	 * ASCII陦ｨ荳ｭ蜿ｯ隗∝ｭ礼ｬｦ莉�蠑�ｧ具ｼ悟￥遘ｻ菴榊�荳ｺ33(Decimal)
	 */
	static final char DBC_CHAR_START = 33; // 蜊願ｧ�
	/**
	 * ASCII陦ｨ荳ｭ蜿ｯ隗∝ｭ礼ｬｦ蛻ｰ~扈捺據�悟￥遘ｻ菴榊�荳ｺ126(Decimal)
	 */
	static final char DBC_CHAR_END = 126; // 蜊願ｧ蓄
	/**
	 * 蜈ｨ隗貞ｯｹ蠎比ｺ拶SCII陦ｨ逧�庄隗∝ｭ礼ｬｦ莉趣ｼ∝ｼ�ｧ具ｼ悟￥遘ｻ蛟ｼ荳ｺ65281
	 */
	static final char SBC_CHAR_START = 65281; // 蜈ｨ隗抵ｼ�
	/**
	 * 蜈ｨ隗貞ｯｹ蠎比ｺ拶SCII陦ｨ逧�庄隗∝ｭ礼ｬｦ蛻ｰ�樒ｻ捺據�悟￥遘ｻ蛟ｼ荳ｺ65374
	 */
	static final char SBC_CHAR_END = 65374; // 蜈ｨ隗抵ｽ�
	/**
	 * ASCII陦ｨ荳ｭ髯､遨ｺ譬ｼ螟也噪蜿ｯ隗∝ｭ礼ｬｦ荳主ｯｹ蠎皮噪蜈ｨ隗貞ｭ礼ｬｦ逧�嶌蟇ｹ蛛冗ｧｻ
	 */
	static final int CONVERT_STEP = 65248; // 蜈ｨ隗貞濠隗定ｽｬ謐｢髣ｴ髫�
	/**
	 * 蜈ｨ隗堤ｩｺ譬ｼ逧���悟ｮ�ｲ｡譛蛾�莉惹ｸ拶SCII逧�嶌蟇ｹ蛛冗ｧｻ�悟ｿ�｡ｻ蜊慕峡螟�炊
	 */
	static final char SBC_SPACE = 12288; // 蜈ｨ隗堤ｩｺ譬ｼ 12288
	/**
	 * 蜊願ｧ堤ｩｺ譬ｼ逧���悟惠ASCII荳ｭ荳ｺ32(Decimal)
	 */
	static final char DBC_SPACE = ' '; // 蜊願ｧ堤ｩｺ譬ｼ

	/**
	 * 遨ｺ謌墨ull逧�愛譁ｭ
	 * 
	 * @param token Object 繝√ぉ繝�け蟇ｾ雎｡
	 * @return boolean 邨先棡
	 * @throws 縺ｪ縺�
	 */
	static public boolean isEmpty(Object token) {
		if (token == null || token.toString().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 遨ｺ謌墨ull逧�愛譁ｭ(trim)
	 * 
	 * @param token Object 繝√ぉ繝�け蟇ｾ雎｡
	 * @return boolean 邨先棡
	 * @throws 縺ｪ縺�
	 */
	static public boolean isTrimEmpty(Object token) {
		if (token == null || token.toString().trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * null霓ｬ髱柤ull�碁ｻ倩ｮ､霓ｬ謌千ｩｺ
	 * 
	 * @param token1 String 蟄礼ｬｦ荳ｲ
	 * @param token2 String 髱柤ull蟄礼ｬｦ荳ｲ
	 * @return String 邨先棡
	 * @throws 縺ｪ縺�
	 */
	static public String nvlString(String token1, String token2) {

		if (StringUtil.isEmpty(token2)) {
			token2 = "";
		}
		return token1 == null ? token2 : token1;
	}

	/**
	 * 蟄礼ｬｦ荳ｲ霓ｬ蟶�ｰ泌梛
	 * 
	 * @param key Object 繝√ぉ繝�け蟇ｾ雎｡
	 * @return boolean 邨先棡
	 * @throws 縺ｪ縺�
	 */
	static public boolean stringToBoolean(Object key) {
		if (key == null || !"1".equals(key)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 蟶�ｰ泌梛霓ｬ蟄礼ｬｦ荳ｲ
	 * 
	 * @param key Object 繝√ぉ繝�け蟇ｾ雎｡
	 * @return String 邨先棡
	 * @throws 縺ｪ縺�
	 */
	static public String booleanToString(Object key) {
		if (key == null || !(Boolean) key) {
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * 譌･譛溯ｽｬ蟄礼ｬｦ荳ｲ
	 * 
	 * @param key Date 譌･譛�
	 * @param format String 蟄礼ｬｦ荳ｲ
	 * @return String 邨先棡
	 * @throws 縺ｪ縺�
	 */
	static public String dateToString(Date key, String format) {

		String dateStr = null;

		if (key != null) {
			dateStr = new DateUtil(key).toString(format);
		}
		return dateStr;
	}

	/**
	 * 蟄礼ｬｦ荳ｲ霓ｬ譌･譛�
	 * 
	 * @param key String 蟄礼ｬｦ荳ｲ
	 * @param format String 蟄礼ｬｦ荳ｲ
	 * @return Date 邨先棡
	 * @throws ParseException ParseException
	 */
	static public Date stringToDate(String key, String format) throws ParseException {
		Date date = null;

		if (!isEmpty(key)) {
			date = new DateUtil(key, format);
		}
		return date;
	}

	/**
	 * 譌･譛溷ｭ礼ｬｦ荳ｲ譬ｼ蠑剰ｽｬ謐｢
	 * 
	 * @param key String 蟄礼ｬｦ荳ｲ
	 * @param formatFrom String 蟄礼ｬｦ荳ｲ
	 * @param formatTo String 蟄礼ｬｦ荳ｲ
	 * @return String 邨先棡
	 * @throws ParseException ParseException
	 */
	static public String stringDateFormat(String key, String formatFrom, String formatTo) throws ParseException {
		return dateToString(stringToDate(key, formatFrom), formatTo);
	}

	/**
	 * 蟄礼ｬｦ荳ｲ蝙区律譛溷刈螟ｩ謨ｰ
	 * 
	 * @param key String 蟄礼ｬｦ荳ｲ
	 * @param format String 蟄礼ｬｦ荳ｲ
	 * @param number int 謨ｴ蝙�
	 * @return String 邨先棡
	 * @throws ParseException ParseException
	 */
	static public String dateStrAddDay(String key, String format, int number) throws ParseException {
		String dateStr = null;

		if (key != null) {
			dateStr = new DateUtil(key, format).addDay(number).toString(format);
		}
		return dateStr;
	}

	/**
	 * 蟄礼ｬｦ荳ｲ蜿ｳ陦･蜈ｨ, 鮟倩ｮ､陦･蜊願ｧ堤ｩｺ譬ｼ縲�
	 * 
	 * @param sSrc String 蟄礼ｬｦ荳ｲ
	 * @param nLen int 謨ｴ蝙�
	 * @param sPad char 蟄礼ｬｦ蝙�
	 * @return String 邨先棡
	 * @throws 縺ｪ縺�
	 */
	static public String rPadding(String sSrc, int nLen, char sPad) {

		String result = nvlString(sSrc, "");

		if (result.length() >= nLen) {
			return result;
		}

		if (isEmpty(sPad)) {
			sPad = ' ';
		}

		int i = result.length();

		for (; i < nLen; i++) {
			result = result + sPad;
		}

		return result;
	}

	/**
	 * 蟄礼ｬｦ荳ｲ蟾ｦ陦･蜈ｨ, 鮟倩ｮ､陦･蜊願ｧ堤ｩｺ譬ｼ縲�
	 * 
	 * @param sSrc String 蟄礼ｬｦ荳ｲ
	 * @param nLen int 謨ｴ蝙�
	 * @param sPad char 蟄礼ｬｦ蝙�
	 * @return String 邨先棡
	 * @throws 縺ｪ縺�
	 */
	static public String lPadding(String sSrc, int nLen, char sPad) {

		String result = nvlString(sSrc, "");

		if (result.length() >= nLen) {
			return result;
		}

		if (isEmpty(sPad)) {
			sPad = ' ';
		}

		int i = result.length();

		for (; i < nLen; i++) {
			result = sPad + result;
		}

		return result;
	}

	/**
	 * getUUID
	 * 
	 * @param token boolean
	 * @return UUID
	 * @throws 縺ｪ縺�
	 */
	static public String getUUID(boolean token) {

		// 闔ｷ蠕嶺ｸ�ｸｪUUID
		UUID uuid = UUID.randomUUID();
		String result = uuid.toString();

		if (!token) {

			// 蜴ｻ謗俄�-窶晉ｬｦ蜿ｷ
			result =
			    result.substring(0, 8) + result.substring(9, 13) + result.substring(14, 18) + result.substring(19, 23)
			        + result.substring(24);
		}
		return result;
	}

	/**
	 * substr
	 * 
	 * @param start int
	 * @param length int
	 * @param token String
	 * @return result
	 * @throws 縺ｪ縺�
	 */
	static public String substr(int start, int length, String token) {

		// 闔ｷ蠕嶺ｸ�ｸｪUUIDtoken
		if (!isEmpty(token)) {
			if (token.length() < length && -1 < start && start < token.length()) {
				token = token.substring(start);
			} else if (-1 < start && start < token.length()) {
				token = token.substring(start, length + start);
			}
		} else {
			token = "";
		}
		return token;
	}

	/**
	 * getJanCode
	 * 
	 * @param code String
	 * @return result
	 * @throws 縺ｪ縺�
	 */
	static public String getJanCode(String code) {
		if (null == code) {
			return "";
		}
		char[] codes = code.toCharArray();
		int sum = 0;
		for (int i = 0; i < codes.length; i++) {
			int num = codes[i] - '0';
			if (i % 2 == 0) {
				sum += num;
			} else {
				sum += num * 3;
			}
		}
		int x = sum % 10;
		return code + (x == 0 ? 0 : 10 - x);
	}

	/**
	 * indexOf
	 * 
	 * @param inFind String
	 * @param find String
	 * @return result
	 * @throws 縺ｪ縺�
	 */
	static public int indexOf(String inFind, String find) {

		return StringUtil.isEmpty(inFind) ? -1 : inFind.indexOf(find);
	}

	/**
	 * 譁�ｭ怜�縺ｮ繧｢繝ｫ繝輔ぃ繝吶ャ繝医�謨ｰ蛟､繧貞濠隗呈枚蟄励↓螟画鋤縺吶ｋ縲�
	 * 
	 * @param str String
	 * @return result
	 */
	static public String fullToHalfChange(String str) {
		char[] cc = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : cc) {

			// 螯よ棡菴堺ｺ主�隗抵ｼ∝芦蜈ｨ隗抵ｽ槫玄髣ｴ蜀�
			if (c >= SBC_CHAR_START && c <= SBC_CHAR_END) {
				sb.append((char) (c - CONVERT_STEP));

				// 螯よ棡譏ｯ蜈ｨ隗堤ｩｺ譬ｼ
			} else if (c == SBC_SPACE) {
				sb.append(DBC_SPACE);

				// 荳榊､�炊蜈ｨ隗堤ｩｺ譬ｼ�悟�隗抵ｼ∝芦蜈ｨ隗抵ｽ槫玄髣ｴ螟也噪蟄礼ｬｦ
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 譁�ｭ怜�縺ｮ繝医Μ繝��遨ｺ縺系ull縺ｫ霆｢謠帙�
	 * 
	 * @param str String
	 * @return result
	 */
	static public String trimStr(String str) {

		if (str == null) {
			return null;
		}

		String result = str.trim();
		if ("".equals(result)) {
			return null;
		}
		return result;
	}
	
	/**
	 * Concat Multi String
	 * 
	 * @param symbol String
	 * @param str String ...
	 * @return String
	 */
	static public String concatStrWithSymbol(String symbol, String ... str ) {
		if (str == null) {
			return "";
		}
		StringBuilder returnStr = new StringBuilder();
		for (int i = 0; i < str.length - 1; i++) {
			returnStr.append(str[i]).append(symbol);
			
		}
		returnStr.append(str[str.length - 1]);
		return returnStr.toString();
	}
	

	/**
	 * Check value is null or empty ?
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String value) {
		if (value == null || "".equals(value)) {
			return true;
		}
		return false;
	}

}
