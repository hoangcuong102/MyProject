package jp.co.cyms.apps.faa001.form;

import jp.co.cyms.base.BaseAction;
import jp.co.cyms.common.StringUtil;

public class Paa0011Form extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * Event ID:Login
	 */
	protected static final String EVENT_ID_LOGIN = "saa0011_01";
	protected String userId;
	protected String userPassword;

	public void validate() {
		if (EVENT_ID_LOGIN.equals(super.getEventId())) {
			if (StringUtil.isEmpty(userId)) {
				super.addActionError("EA-0009");
			}
			if (StringUtil.isEmpty(userPassword)) {
				super.addActionError("EA-0010");
			}
		}
	}

	/**
	 * userId
	 * 
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * userId
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userPassword
	 * 
	 * @return userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * userPassword
	 * 
	 * @param userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
