package jp.co.cyms.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.cyms.apps.faa001.bean.MenuBean;
import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.CategoryBean;
import jp.co.cyms.common.bean.ConfigBean;
import jp.co.cyms.common.bean.CountryBean;
import jp.co.cyms.common.bean.ErrorBean;
import jp.co.cyms.common.bl.CommonBL;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author longnd
 *
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements ServletRequestAware, SessionAware {

	protected Map<String, Object> session;

	protected UserBean userInfo;

	protected List<MenuBean> menuInfo;

	protected String currentFunctionId;

	protected String currentScreenId;

	protected String errCode;

	protected HttpServletResponse response;

	protected DataSourceTransactionManager transactionManager;

	protected HttpServletRequest request;

	protected String contextPath;

	protected String currentScreenName;

	protected ConfigBean configInfo;
	protected List<CountryBean> listCountries;

	protected String updatedUser;
	protected Date updatedDtLocal;
	protected float timeDifference;
	protected String exclusiveFg;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = ServletActionContext.getResponse();
	}

	/**
	 * Struts2のResult：動的遷移
	 */
	public static final String DYNAMIC = "dynamicAction";

	@SuppressWarnings("unchecked")
	public BaseAction() {
		this.userInfo = new UserBean();
		transactionManager = new DataSourceTransactionManager();
		if (transactionManager == null) {
			ApplicationContext ac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
			transactionManager = ac.getBean("transactionManager", DataSourceTransactionManager.class);
		}
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<MenuBean> menuInfo = (List<MenuBean>) request.getSession()
					.getAttribute(Constant.SESSION_KEY_MENU_INFO);
			String currentPath = request.getServletPath();
			if (currentPath != null && !currentPath.contains("/common001") && !currentPath.contains("/redirect")) {
				// Get current screen name
				String functionId = currentPath.split("/")[2];
				String screenName = functionId.replace(".action", "");
				this.currentScreenName = doGetCurrentScreenName(screenName, menuInfo);
				if (session == null) {
					session = ActionContext.getContext().getSession();
				}
				session.put(Constant.SESSION_KEY_CURRENT_SCREEN_NAME, currentScreenName);
			}
		} catch (Exception ex) {

		}

	}
	
	/**
	 * Retrieve the folder where the template is stored
	 * 
	 * @param templateName
	 */
	public String getRealPathInWebInf(String templateName) {
		String templateFile = "";
		try {
			// Retrieve the folder where the template is stored
			ServletContext servletContext = ServletActionContext.getServletContext();
			templateFile = servletContext.getRealPath("/WEB-INF/" + templateName);
		} catch (Exception ex) {
			throw ex;
		}
		return templateFile;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<MenuBean> getMenuInfo() {
		if (session != null) {
			return (List<MenuBean>) session.get(Constant.SESSION_KEY_MENU_INFO);
		}
		return null;
	}

	public UserBean getUserInfo() {
		if (session != null) {
			return (UserBean) session.get(Constant.SESSION_KEY_USER_INFO);
		}
		return null;
	}

	public String getCurrentFunctionId() {
		if (session != null) {
			return (String) session.get(Constant.SESSION_KEY_CURRENT_FUNCTION_ID);
		}
		return null;
	}

	public String getCurrentScreenId() {
		if (session != null) {
			return (String) session.get(Constant.SESSION_KEY_CURRENT_SCREEN_ID);
		}
		return null;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode
	 *            the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return the transactionManager
	 */
	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * @param transactionManager
	 *            the transactionManager to set
	 */
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void addActionError(String messageId) {
		addActionError(messageId, (String[]) null);
	}

	/**
	 * エラーメッセージIDを追加します。
	 *
	 * @param messageId
	 *            メッセージID
	 * @param params
	 *            パラメタ
	 */
	public void addActionError(String messageId, String... params) {
		String message = super.getText(messageId, params);
		super.addActionError(message);
	}

	/**
	 * 呼出元のイベントIDを取得します。
	 *
	 * @return イベントID
	 */
	public String getEventId() {
		return ActionContext.getContext().getName();
	}

	/**
	 * 動的遷移先の情報を設定します。
	 *
	 * @param functionId
	 *            機能ID
	 * @param screenId
	 *            画面ID
	 * @return 結果
	 * @throws IOException 
	 */
	public String setDynamicAction(String functionId, String screenId) throws IOException {
		return setDynamicActionImpl(functionId, screenId);
	}

	/**
	 * 動的遷移情報を設定します。
	 *
	 * @param functionId
	 *            機能ID
	 * @param screenId
	 *            画面ID
	 * @return 結果
	 * @throws IOException 
	 * @throws Exception
	 */
	private String setDynamicActionImpl(String functionId, String screenId) throws IOException {
		this.currentFunctionId = functionId;
		this.currentScreenId = screenId;

		// Get current screen name
		// this.currentScreenName = doGetCurrentScreenName(screenId);

		session.put(Constant.SESSION_KEY_CURRENT_FUNCTION_ID, currentFunctionId);
		session.put(Constant.SESSION_KEY_CURRENT_SCREEN_ID, currentScreenId);
		session.put(Constant.SESSION_KEY_CURRENT_SCREEN_NAME, currentScreenName);
		
		if (session.get(Constant.SESSION_KEY_CONFIG_INFO) == null) {
			List<ErrorBean> listAllMessages;
			List<CategoryBean> listCategory;
			CommonBL logic = new CommonBL();
			configInfo = logic.getConfig();
			listCountries = logic.getCountries();
			listAllMessages = logic.getAllMessages();
			listCategory = logic.getListCategory();
			String timeZone = "";
			for (CountryBean country : listCountries) {
				if (configInfo.getServerLocation().equals(country.getCountryName())) {
					timeZone = country.getTimeDiffrence();
					break;
				}
			}
			String[] timeZoneArray = timeZone.split(":");
			if (Long.parseLong(timeZoneArray[0]) > 0) {
				timeDifference = Float.parseFloat(timeZoneArray[0]) + (Float.parseFloat(timeZoneArray[1]) / 60);
			} else {
				timeDifference = Float.parseFloat(timeZoneArray[0]) - (Float.parseFloat(timeZoneArray[1]) / 60);
			}

			session.put(Constant.SESSION_KEY_CONFIG_INFO, configInfo);
			session.put(Constant.SESSION_KEY_ALL_COUNTRIES, listCountries);
			session.put(Constant.SESSION_KEY_ALL_MESSAGES, listAllMessages);
			session.put(Constant.SESSION_KEY_ALL_CATEGORY, listCategory);
			session.put(Constant.SESSION_KEY_TIME_DIFFERENCE, timeDifference);
		}
		return DYNAMIC;
	}

	public String getContextPath() {
		if (request == null) {
			return null;
		}
		return contextPath = request.getContextPath();
	}

	public String doGetCurrentScreenName(String screenId, List<MenuBean> menuInfo) {
		if (menuInfo != null) {
			for (MenuBean bean : menuInfo) {
				if (screenId.equals(bean.getScreenId())) {
					return bean.getMenuName();
				}
			}
		}
		return "";
	}

	/**
	 * Split timezone and return hour-minute
	 * 
	 * @param timeZone
	 *            Time Zone
	 * @return listTimeZone ArrayList<Integer>
	 */
	public ArrayList<Integer> splitTimeZone(String timeZone) {
		ArrayList<Integer> listTimeZone = new ArrayList<Integer>();
		if (!StringUtil.isEmpty(timeZone)) {
			String[] arrTimeZone = timeZone.split(":");
			if (arrTimeZone != null) {
				listTimeZone.add(Integer.parseInt(arrTimeZone[0]));
				listTimeZone.add(Integer.parseInt(arrTimeZone[1]));
			}
		}
		return listTimeZone;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getCurrentScreenName() {
		if (session != null) {
			return (String) session.get(Constant.SESSION_KEY_CURRENT_SCREEN_NAME);
		}
		return "";
	}

	public void setCurrentScreenName(String currentScreenName) {
		this.currentScreenName = currentScreenName;
	}

	public void setUserInfo(UserBean userInfo) {
		this.userInfo = userInfo;
	}

	public void setMenuInfo(List<MenuBean> menuInfo) {
		this.menuInfo = menuInfo;
	}

	public void setCurrentFunctionId(String currentFunctionId) {
		this.currentFunctionId = currentFunctionId;
	}

	public void setCurrentScreenId(String currentScreenId) {
		this.currentScreenId = currentScreenId;
	}

	public String getUpdatedUser() {
		updatedUser = getUserInfo().getUserId();
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public Date getUpdatedDtLocal() {
		updatedDtLocal = new Date();
		return updatedDtLocal;
	}

	public float getTimeDifference() {
		if (session != null) {
			return (float) session.get(Constant.SESSION_KEY_TIME_DIFFERENCE);
		}
		return 0L;
	}

	public String getExclusiveFg() {
		return exclusiveFg;
	}

	public void setExclusiveFg(String exclusiveFg) {
		this.exclusiveFg = exclusiveFg;
	}

	public ConfigBean getConfigInfo() {
		if (session != null) {
			return (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CountryBean> getListCountries() {
		if (session != null) {
			return (List<CountryBean>) session.get(Constant.SESSION_KEY_ALL_COUNTRIES);
		}
		return null;
	}

}
