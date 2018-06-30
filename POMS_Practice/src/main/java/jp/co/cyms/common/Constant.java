/*
 * CLASS   : Constant
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01縲�SC縲�U CREATE
 */
package jp.co.cyms.common;

/**
 * Constant
 *
 * @author LSC LU
 * @since 2014/08/01
 */
public class Constant {

	/**
	 * Constant's constructor
	 */
	public Constant() {
	}

	/** UID */
	public static final String UID = "userID";

	/** REPORT_ID DATA DOWNLOAD */
	public static final String REPORT_ID_DATA_DOWNLOAD = "sad0011";

	/** SUCESS */
	public static final String SUCESS = "success";

	/** ERROR_CODE */
	public static final String ERROR_CODE = "errorCode";

	/** SYSTEM_ID_PARAM */
	public static final String SYSTEM_ID_PARAM = "systemId";

	/** TEMPFOLDER */
	public static final String TEMP_TYPE = "TEMPFOLDER";

	/** TEMPLATE_FOLDER */
	public static final String TEMPLATE_FOLDER = "TEMP-0002";

	/** TEMPFOLDER */
	public static final String FILE_TYPE = "FILE";

	/** OUTPUT_FOLDER */
	public static final String OUTPUT_FOLDER = "TEMP-0001";

	/**
	 * ROWS_PER_PAGE
	 */
	public static final int ROWS_PER_PAGE = 100;

	/** INTERVAL_LOADING_BAY */
	public static final int INTERVAL_LOADING_BAY = 5;

	/** INTERVAL_LOADING_BAY_VIEW */
	public static final int INTERVAL_LOADING_BAY_VIEW = 5;

	/** INTERVAL_LOADING_BAY_MOVICE */
	public static final int INTERVAL_LOADING_BAY_MOVICE = 10;

	/** INTERVAL_PICKING_INSTRUCTION */
	public static final int INTERVAL_PICKING_INSTRUCTION = 30;

	/**
	 * GRID逕ｨ遨ｺJSON謨ｰ謐ｮ
	 */
	public static final String EMPTY_GRID_DATA = "[]";

	/**
	 * SESSION_KEY_USER_INFO
	 */
	public static final String SESSION_KEY_USER_INFO = "userInfo";

	/**
	 * SESSION_KEY_MENU_INFO
	 */
	public static final String SESSION_KEY_MENU_INFO = "menuInfo";

	/**
	 * SESSION_KEY_MENU_SELECTED
	 */
	public static final String SESSION_KEY_MENU_SELECTED = "menuSelected";

	/**
	 * SESSION_KEY_CURRENT_FUNCTION_ID
	 */
	public static final String SESSION_KEY_CURRENT_FUNCTION_ID = "currentFunctionId";

	/**
	 * SESSION_KEY_CURRENT_SCREEN_ID
	 */
	public static final String SESSION_KEY_CURRENT_SCREEN_ID = "currentScreenId";
	public static final String SESSION_KEY_CURRENT_SCREEN_NAME = "currentScreenName";

	/** XLSX_EXTENSION */

	public static final String XLSX_EXTENSION = ".xlsx";
	/** PDF_EXTENSION */

	public static final String PDF_EXTENSION = ".pdf";

	public static final String ZIP_EXTENSION = ".zip";

	/** PDF_CONTENT_TYPE */
	public static final String PDF_TYPE = "application/pdf";

	/** EXCEL_CONTENT_TYPE */
	public static final String EXCEL_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/** SHEET_TEMPLATE */
	public static final String SHEET_TEMPLATE = "template";

	/** SHEET_DATA */
	public static final String SHEET_DATA = "data";

	/** CHARACTER_ENDCODE_UTF8 */
	public static final String CHARACTER_ENDCODE_UTF8 = "UTF-8";

	/** OUTPUT_FILE */
	public static final String OUTPUT_FILE = "outputFile";

	/** OUTPUT_PATH */
	public static final String OUTPUT_PATH = "outputFolder";

	/** FORMAT dd-M-yyyy HH:mm:ss */
	public static final String FORMAT_DATE_FROM_CLIENT = "dd-M-yyyy HH:mm:ss";

	public static final String TRUE = "true";

	public static final String FALSE = "false";

	public static final int GENERAL_USER_CD = 1;
	public static final String GENERAL_USER = "General User";
	public static final int KDDI_CD = 2;
	public static final String KDDI = "KDDI Admin";
	public static final int SANKYU_ADMIN_CD = 3;
	public static final String SANKYU_ADMIN = "SANKYU Admin";
	public static final String FUNCTION_DISPLAY_ONLY_GENERAL_USER = "fac001";
	public static final String FUNCTION_DISPLAY_ONLY_GENERAL_USER2 = "fac099";

	public static final String SESSION_KEY_CONFIG_INFO = "configInfo";
	public static final String SESSION_KEY_ALL_COUNTRIES = "allCountries";
	public static final String SESSION_KEY_ALL_MESSAGES = "allMessages";
	public static final String SESSION_KEY_ALL_CATEGORY = "allCategory";
	public static final String SESSION_KEY_TIME_DIFFERENCE = "timeDifference";
	public static final int SESSION_TIME_OUT = 120;

	public static final String EXCLUSIVE_FG = "";
	public static final long RUNNING_N0_PLUS = 1;

	// PC Order Entry
	public static final String SAVE_FG = "1";
	public static final String SANKYU_REGIST = "SANKYU_REGIST";
	public static final String SANKYU_ORDER = "SANKYU_ORDER";
	public static final int DECIMAL_PLACES_DIGIST = 2;
	public static final String LEASE_TYPE_MRC = "MRC";
	public static final String LEASE_TYPE_OTC = "OTC";
	public static final String ADDON_ANTIVIRUS = "ANTIVIRUS";
	public static final String ADDON_MONITORING = "MONITORING";
	public static final int DEFAULT_RUNNING_NO = 0;
	public static final String QUOTE_MRC_XLSX = "QUOTE_MRC.xlsx";
	public static final String QUOTE_OTC_XLSX = "QUOTE_OTC.xlsx";
	public static final String XLSX = ".xlsx";
	public static final String PDF = ".pdf";
	public static final String DD_M_YY = "dd-m-yy";
	public static final String NUBMER_DAYS_FROM_DATE = "30 Days from date of Invoice";
	public static final String PRINT_ORDER_FG = "1";
	public static final String SEND_ORDER_FG = "1";
	public static final String DELETE_ORDER_FG = "1";
	public static final String ALIGN_LEFT = "left";
	public static final String ALIGN_CENTER = "center";
	public static final String EXCEPTION_AJAX = "exceptionAjax";
	public static final String SAVE_ORDER_TITLE = "POMS Order Entry Notification";
	public static final String SEND_ORDER_TITLE = "POMS Send Order Notification";
	public static final String COMPANY_NAME_SANKYU = "SANKYU SOUTHEAST ASIA HOLDINGS PTE. LTD.";

	/** PC Order Progress */
	/** REJECT */
	public static final String REJECT = "REJECT";
	/** APPROVE */
	public static final String APPROVE = "APPROVE";
	/** SEA_REQUEST */
	public static final String SEA_REQUEST = "SEA_REQUEST";
	/** KDDI_ACCEPT */
	public static final String KDDI_ACCEPT = "KDDI_ACCEPT";

	/** KDDI_ORDER */
	public static final String KDDI_ORDER = "KDDI_ORDER";
	/** KDDI_RECEIVE */
	public static final String KDDI_RECEIVE = "KDDI_RECEIVE";
	/** KDDI_LEASE */
	public static final String KDDI_LEASE = "KDDI_LEASE";
	/** KDDI_DELIVER */
	public static final String KDDI_DELIVER = "KDDI_DELIVER";
	/** UPDATE */
	public static final String UPDATE = "UPDATE";
	/** DELETE */
	public static final String DELETE = "DELETE";
	/** DELIVER */
	public static final String DELIVER = "DELIVER";
	/** OUTPUT NAME */
	public static final String OUTPUT_NAME = "outputName";
	/** REDIRECT_1 */
	public static final String REDIRECT_1 = "1";
	/** SANKYU ADMIN AUTHORITY */
	public static final String SANKYU_ADMIN_AUTHORITY = "3";
	/** KDDI ADMIN AUTHORITY */
	public static final String KDDI_ADMIN_AUTHORITY = "2";
	/** GENERAL USER AUTHORITY */
	public static final String GENERAL_USER_AUTHORITY = "1";
	
	/** GENERATED */
	public static final String GENERATED = "generated";
	/** SIGNED */
	public static final String SIGNED = "signed";
	/** DELIVER_FG */
	public static final String DELIVER_FG = "1";
	
	public static final String MONTHLY_COST = "Monthly Cost";
	public static final String ONE_TIME_COST = "One Time Cost";
	
	/** Total */
	public static final String TOTAL = "Total";
}
