/*
 * CLASS   : SystemCommon
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01邵ｲ�ｽSC邵ｲ�ｽU CREATE
 */
package jp.co.cyms.common;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * SystemCommon
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
public class SystemCommon {

	/**
	 * SystemCommon's constructor
	 */
	public SystemCommon() {
	}

	/**
	 * ROWS_PER_PAGE
	 */
	static public int rowPerPage = Constant.ROWS_PER_PAGE;

	/**
	 * INTERVAL_LOADING_BAY
	 */
	static public Integer intervalLoadingBay = Constant.INTERVAL_LOADING_BAY;

	/**
	 * INTERVAL_LOADING_BAY
	 */
	static public Integer intervalLoadingBayView = Constant.INTERVAL_LOADING_BAY_VIEW;

	/**
	 * INTERVAL_LOADING_BAY
	 */
	static public Integer intervalLoadingBayMovice = Constant.INTERVAL_LOADING_BAY_MOVICE;

	/**
	 * UPLOAD_FILE_PATH
	 */
	static public String uploadFilePath;

	/**
	 * WLP_TT_OUT_PROGRESS_LINE
	 */
	static public String wlpttoutprogressline;

	/**
	 * INTERVAL_PICKING_INSTRUCTION
	 */
	static public Integer intervalPickingInstruction = Constant.INTERVAL_PICKING_INSTRUCTION;

	/**
	 * languageDefault
	 */
	static private String languageDefault;

	/**
	 * languageMap
	 */
	static private Map<String, Object> languageMap;

	/**
	 * getProperties
	 * 
	 * @param location String
	 * @return Properties
	 * @throws IOException IOException
	 */
	public Properties getProperties(String location) throws IOException {

		InputStream in = getClass().getResourceAsStream(location);
		Properties prop = new Properties();
		prop.load(in);

		return prop;
	}

	/**
	 * loadLanguage
	 * 
	 * @param sysProperties Properties
	 * @throws IOException IOException
	 */
	public void loadLanguage(Properties sysProperties) throws IOException {
		SystemCommon system = new SystemCommon();
		languageMap = new HashMap<String, Object>();

		String[] languageList = sysProperties.getProperty("language").split(",");
		for (String language : languageList) {
			Properties messageProp = system.getProperties("/properties/language/message-" + language + ".properties");
			Properties labelProp = system.getProperties("/properties/language/label-" + language + ".properties");
			Map<String, Object> subMap = new HashMap<String, Object>();
			subMap.put("m", messageProp);
			subMap.put("l", labelProp);

			languageMap.put(language, subMap);
		}

		languageDefault = sysProperties.getProperty("language.default");
	}

	/**
	 * languageCode
	 * 
	 * @return language
	 */
	static public String languageCode() {
		String language = languageDefault;
		return language;
	}

	/**
	 * message
	 * 
	 * @param msgId String
	 * @param params String[]
	 * @return message
	 * @throws ClassCastException ClassCastException
	 * @throws NullPointerException NullPointerException
	 */
	@SuppressWarnings("unchecked")
	static public String message(String msgId, String[] params) throws ClassCastException, NullPointerException {
		Properties messageProp = (Properties) ((HashMap<String, Object>) languageMap.get(languageCode())).get("m");
		String message = messageProp.getProperty(msgId);
		for (int i = 0; i < params.length; i++) {
			message = message.replaceAll("[{]" + Integer.toString(i) + "[}]", params[i]);
		}
		return message;
	}

	/**
	 * label
	 * 
	 * @param labelId String
	 * @param params String[]
	 * @return label
	 * @throws ClassCastException ClassCastException
	 * @throws NullPointerException NullPointerException
	 */
	@SuppressWarnings("unchecked")
	static public String label(String labelId, String[] params) throws ClassCastException, NullPointerException {
		Properties messageProp = (Properties) ((HashMap<String, Object>) languageMap.get(languageCode())).get("l");
		String label = messageProp.getProperty(labelId);
		for (int i = 0; i < params.length; i++) {
			label = label.replaceAll("[{]" + Integer.toString(i) + "[}]", params[i]);
		}
		return label;
	}

	/**
	 * labels
	 * 
	 * @return Properties
	 * @throws ClassCastException ClassCastException
	 * @throws NullPointerException NullPointerException
	 */
	@SuppressWarnings("unchecked")
	static public Properties labels() throws ClassCastException, NullPointerException {
		Properties messageProp = (Properties) ((HashMap<String, Object>) languageMap.get(languageCode())).get("l");
		return messageProp;
	}

	/**
	 * addSession
	 * 
	 * @param key String
	 * @param value Object
	 */
	static public void addSession(String key, Object value) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(key, value);
	}

	/**
	 * getInputStream
	 * 
	 * @param resource String
	 * @return InputStream
	 */
	static public InputStream getInputStream(String resource) {
		return ServletActionContext.getServletContext().getResourceAsStream(resource);
	}

	/**
	 * getOutputStream
	 * 
	 * @return ServletOutputStream
	 * @throws IOException IOException
	 */
	static public ServletOutputStream getOutputStream() throws IOException {
		return ServletActionContext.getResponse().getOutputStream();
	}

	/**
	 * getContextPath
	 * 
	 * @return contextPath
	 */
	static public String getContextPath() {
		return ServletActionContext.getServletContext().getContextPath();
	}

	/**
	 * getRealPath
	 * 
	 * @return realPath
	 */
	static public String getRealPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}

	/**
	 * getInputStream
	 * 
	 * @return ServletInputStream
	 * @throws IOException IOException
	 */
	static public ServletInputStream getInputStream() throws IOException {
		return ServletActionContext.getRequest().getInputStream();
	}

	/**
	 * getParameter
	 * 
	 * @param param String
	 * @return result
	 */
	static public String getParameter(String param) {
		return ServletActionContext.getRequest().getParameter(param);
	}

	/**
	 * removeSession
	 * 
	 * @param key String
	 */
	static public void removeSession(String key) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(key);
	}

	/**
	 * getSession
	 * 
	 * @param key String
	 * @return result
	 */
	static public Object getSession(String key) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		return session.get(key);
	}

	/**
	 * addCookie
	 * 
	 * @param key String
	 * @param value String
	 */
	static public void addCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(31536000);
		cookie.setPath("/");
		ServletActionContext.getResponse().addCookie(cookie);
	}

	/**
	 * getCookie
	 * 
	 * @param key String
	 * @return cookie
	 */
	static public String getCookie(String key) {
		if (ServletActionContext.getRequest() != null) {
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * addHeader
	 * 
	 * @param name String
	 * @param value String
	 */
	static public void addHeader(String name, String value) {
		ServletActionContext.getResponse().addHeader(name, value);
	}

	/**
	 * setContentType
	 * 
	 * @param type String
	 */
	static public void setContentType(String type) {
		ServletActionContext.getResponse().setContentType(type);
	}

	/**
	 * setContentLength
	 * 
	 * @param len int
	 */
	static public void setContentLength(int len) {
		ServletActionContext.getResponse().setContentLength(len);
	}
	
}
