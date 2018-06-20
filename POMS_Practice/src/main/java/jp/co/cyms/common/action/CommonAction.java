package jp.co.cyms.common.action;

import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.base.BaseAction;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.bean.CategoryBean;
import jp.co.cyms.common.bean.ConfigBean;
import jp.co.cyms.common.bean.CountryBean;
import jp.co.cyms.common.bean.ErrorBean;
import jp.co.cyms.common.bl.CommonBL;
import jp.co.cyms.common.properties.ErrorProperties;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author longnd
 *
 */
public class CommonAction extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<ErrorBean> listAllMessages;
	private List<CategoryBean> listCategory;
	private ErrorBean error;

	/**
	 * get All: messages, configuration, countries
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String getAllInfo() throws IOException {
		CommonBL logic = new CommonBL();
		userInfo = super.getUserInfo();
		if (userInfo == null) {
			error = new ErrorBean();
			error.setKey(ErrorProperties.ExceptionKey.EA_0012.toString().replace("_", "-"));
			error.setValue(ErrorProperties.get(ErrorProperties.ExceptionKey.EA_0012));
			return LOGIN;
		}

		if (session.get(Constant.SESSION_KEY_CONFIG_INFO) == null) {
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
		} else {
			configInfo = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
			listCountries = (List<CountryBean>) session.get(Constant.SESSION_KEY_ALL_COUNTRIES);
			listAllMessages = (List<ErrorBean>) session.get(Constant.SESSION_KEY_ALL_MESSAGES);
			listCategory = (List<CategoryBean>) session.get(Constant.SESSION_KEY_ALL_CATEGORY);
		}

		return SUCCESS;
	}

	/**
	 * Redirect to Home Screen
	 * 
	 * @return
	 * @throws Exception
	 */
	public String redirectToHome() throws Exception {
		return SUCCESS;
	}

	public List<ErrorBean> getListAllMessages() {
		return listAllMessages;
	}

	public void setListAllMessages(List<ErrorBean> listAllMessages) {
		this.listAllMessages = listAllMessages;
	}

	public ConfigBean getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(ConfigBean configInfo) {
		this.configInfo = configInfo;
	}

	public List<CountryBean> getListCountries() {
		return listCountries;
	}

	public void setListCountries(List<CountryBean> listCountries) {
		this.listCountries = listCountries;
	}

	public List<CategoryBean> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<CategoryBean> listCategory) {
		this.listCategory = listCategory;
	}

	public ErrorBean getError() {
		return error;
	}

	public void setError(ErrorBean error) {
		this.error = error;
	}

	public UserBean getUserInfo() {
		if (session != null) {
			return (UserBean) session.get(Constant.SESSION_KEY_USER_INFO);
		}
		return null;
	}
}
