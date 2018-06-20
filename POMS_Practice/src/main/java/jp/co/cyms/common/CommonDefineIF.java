package jp.co.cyms.common;

/*
 * COPYRIGHT Mitsui Zosen Systems Research Inc. All right Reserved
 */

/**
 * The Interface CommonDefineIF.
 */
@SuppressWarnings("javadoc")
public interface CommonDefineIF {
	public final static String			SYSTEM_CODE						= "pms_secure";
	public final static String			LOGIN_PATH						= "/secure/login/";
	public final static String			HEADERKEY_DOWNLOAD_FILE			= "Content-Disposition";
	public final static String			ENCODE_UTF8						= "UTF-8";
	public final static String			HEADER_VALUE_DOWNLOAD_FILE		= "attachment; filename=\"%s\";";
	public final static int				BUFFER_STREAM_FILE				= 1024 * 1024;
	public final static int				BYTE_END_FILE					= -1;
	public final static String			MAP_KEY_ERROR					= "error";
	public final static String			MAP_KEY_SUCCESS					= "success";
	public final static String			MAP_KEY_SEQNO					= "seqno";
	public final static String			MAP_KEY_DATA					= "data";
	public final static String			MAP_KEY_DATA_ERROR				= "data_error";
	public final static String			MAP_KEY_ERROR_FIELD				= "field";
	public final static String			MAP_KEY_ROW_ERROR				= "row";
	public final static String			MAP_KEY_FILE_ERROR				= "file";
	public final static String			FUNCTION_ID_SYS					= "SYS";
	public final static String			PARAMETER_TEMPLATE_FOLDER		= "TEMPLATE_FOLDER";
	public final static String			PARAMETER_LIBREOFFFICE_PATH		= "LIBREOFFFICE_PATH";
	public final static String			PARAMETER_TEMP_FOLDER			= "TEMP_FOLDER";
	public final static String			REPORT_PDF						= "1";
	public final static String			REPORT_EXTENSION_XLSX			= ".xlsx";
	public final static String			REPORT_EXTENSION_PDF			= ".pdf";
	public final static String			TYPE_STRING						= "STRING";
	public final static String			TYPE_CURRENCY					= "CURRENCY";
	public final static String			TYPE_DATE						= "DATE";
	public final static String			USER_U							= "U";
	public final static String			USER_B							= "B";
	public final static String			USER_G							= "G";
	public final static String			USER_A							= "A";
	public final static String			USER_S							= "S";
	public final static String			KOJI_JCFLAG_7					= "7";
	public final static String			KOJI_JCFLAG_9					= "9";
	public final static String			KOJI_JCFLAG_0					= "0";
	public final static String			KOJI_FACA6_0					= "0";
	public final static long			FILE_MB							= 1024 * 1024;
	public final static long			FILE_KB							= 1024;
	public final static String			FILE_SEPERATOR_CSV				= ",";
	public final static String			STRING_EMPTY					= "";
	public final static String			USER_KENGEN_A					= "A";
	public final static String			USER_KENGEN_S					= "S";
	public final static String			KEITAI_U						= "U";
	public final static String			KEITAI_H						= "H";
	public final static String			DATA_EXPORT						= "data_export";
	public final static String			DATA_TYPE						= "data_type";
	public static final String			DATA_EXPORT_TITLE				= "title";
	public static final String			DATA_EXPORT_CONDITION			= "condition";
	public static final String			DATA_EXPORT_START_ROW			= "start_row";
	public static final String			DATA_EXPORT_START_COL			= "start_colunm";
	public static final String			DATA_EXPORT_HAS_USERNAME		= "has_username";
	public final static String			START_YEAR						= "1998";
	public static final String			SHIFT_JIS						= "Shift_JIS";
	public static final String			EUC_JP							= "EUC-JP";
	public static final String			ALL_PATH_LINK_REQPO				= "/reqpo/*";
	public static final String			ALL_PATH_LINK_TOUSHO			= "/tousho/*";
	public static final String			ALL_PATH_LINK_KENSHU			= "/kenshu/*";
	public static final String			ALL_PATH_LINK_PROC				= "/proc/*";
	public static final String			ALL_PATH_LINK_SEARCH			= "/search/*";
	public static final String			ALL_PATH_LINK_ADMIN				= "/admin/*";
	public static final String			ALL_PATH_LINK_REPORT			= "/report/*";
	public static final String			ALL_PATH_LINK_END				= "*";
	public static final String			STRING_NULL						= "NULL";
	public static final CharSequence	STRING_NOT						= "NOT";
	public static final String			DATA_KOJI						= "DATA_KOJI";
	public static final Object			ACCEPT_JCFLAG_0					= "1";
	public static final String			MAP_KEY_ERROR_CLASS				= "error_class";
	public static final String			CLASS_TABLE_KOJINO				= "TABLE_KOJINO";
	public static final String			CLASS_TABLE_KOJIKBN				= "TABLE_KOJIKBN";
	public static final String			CLASS_TABLE_HIMOKUCD			= "TABLE_HIMOKUCD";
	public static final String			CLASS_TABLE_AMOUNT				= "TABLE_AMOUNT";
	public static final String			CLASS_TABLE_ZEI					= "TABLE_ZEI";
	public static final String			CLASS_TABLE_SAGYOME				= "TABLE_SAGYOME";
	public static final String			MAP_KEY_LISTKOJI_CHECKED		= "checked";
	public static final String			MAP_KEY_LISTKOJI_GYOBAN			= "gyoban";
	public static final String			MAP_KEY_LISTKOJI_KOJINO			= "kojino";
	public static final String			MAP_KEY_LISTKOJI_KOJIKBN		= "kojikbn";
	public static final String			MAP_KEY_LISTKOJI_HIMOKU			= "himoku";
	public static final String			MAP_KEY_LISTKOJI_AMOUNT			= "amount";
	public static final String			MAP_KEY_LISTKOJI_ZEI			= "zei";
	public static final String			MAP_KEY_LISTKOJI_ZEIGAKU		= "zeigaku";
	public static final String			MAP_KEY_LISTKOJI_ALL_AMOUNT		= "all_amount";
	public static final String			MAP_KEY_LISTKOJI_SAGYOME		= "sagyome";
	public static final String			MAP_KEY_LISTKOJI_INSERTED		= "inserted";
	public static final String			MAP_KEY_LISTKOJI_SEQNO			= "seqno";
	public static final String			DATA_KOJI_INIT					= "DATA_KOJI_INIT";
	public static final String			MPE								= "MPE";
	public static final String			GYOBAN_R						= "R";
	public static final String			GYOBAN							= "gyoban";
	public static final String			GYOBAN_OTHER					= "OTHER";
	public static final String			GYOBAN_INIT						= "-";
	public static final String			YEAR							= "YEAR";
	public static final String			MONTH							= "MONTH";
	public static final String			FIRST_MONTH						= "-01";
	public static final String			STRING_DOT						= ".";
	public static final String			STRING_MONEY_FORMATTER			= "###,###,###,###,###,###,###,###";
	public static final String			CONTENT_TYPE					= "application/vnd.ms-excel";
	public static final String			TYPE_NUMBER						= "NUMBER";
	public static final String			MPB_USER_INFO					= "MPB_USER_INFO";
	public static final String			ERROR_CODE						= "errorCode";
	public static final Object			ERROR							= "ERROR";
	public static final String			INFOMATION						= "infomation";
	public static final String			SESSION_STATUS_CODE				= "SESSION_STATUS_CODE";
	public static final String			CURRENCY_JPY					= "JPY";
	public static final String			JOBNO							= "JOBNO";
	public static final String			JOBNAME							= "JOBNAME";
	public static final String			TERM							= "TERM";
	public static final String			TYPE2							= "TYPE2";
	public static final String			MODE							= "MODE";
	public final static String			MAP_KEY_MSG						= "msg";

	public final static int				TIME_IN_DAY						= 24 * 60 * 60 * 1000;
	public static final String			SYSTEM_NEWS_MAIN				= "2";
	public static final String			INSIDE_TYPE_NAIBU				= "1";
	public static final String			TYPE_CONFIRM_CHANGE				= "change";
	public static final String			TYPE_CONFIRM_DELETE				= "delete";
	public static final String			TYPE_CONFIRM_COMMIT				= "commit";
	public static final String			TYPE_CONFIRM_OTHER				= "other";
	public static final String			LIST_MAX						= "LIST_MAX";
	public static final String			SYS								= "SYS";
	public static final String			SYS_ID_IVI						= "IVI";
	public static final String			SYS_ID_VND						= "VND";
	public static final String			COM_KIND_EVALUATION				= "1";
	public static final String			COM_KIND_COMMENT				= "2";
	public static final String			FLAG_ON							= "ON";
	public static final String			CONTENT_LENGTH					= "Content-Length";
	// コード定義マスタ（M_CODEMST）の区分：QC_AUTH
	public static final String			KBN_QC_AUTH						= "QC_AUTH";
	// コード定義マスタ（M_CODEMST）の区分：RE_EVAL
	public static final String			KBN_RE_EVAL						= "RE_EVAL";
	// コード定義マスタ（M_CODEMST）の区分：RE_EVAL
	public static final String			KBN_QC_INSP						= "QC_INSP";
	public final static String			MAP_KEY_PRIVILEGE_FLG			= "privilegeFlg";
	public static final String			VEND_CDSUB_DELETE				= "01";
	public static final String			mnuIVI_VendorInformation		= "mnuIVI_VendorInformation";
	public static final String			mnuIVI_VendorInformation_New	= "mnuIVI_VendorInformation_New";
	public static final String			mnuIVI_VendorInformation_Edit	= "mnuIVI_VendorInformation_Edit";
	public static final String			SYS_ID_REF						= "REF";
	public static final String			mnu_REF_Vendor					= "mnu_REF_Vendor";
	public static final String			mnu_REF_VendorInfomation_PUM	= "mnu_REF_VendorInfomation(PUM)";
	public static final String			FLAG_OFF						= "FLAG_OFF";
	public static final String			SELECTED_LIST					= "SELECTED_LIST";
	public static final String			MODE_MULT						= "MULT";


}

