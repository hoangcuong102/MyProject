package jp.co.cyms.apps.faa001.action;

import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.apps.faa001.bl.Paa0011BL;
import jp.co.cyms.apps.faa001.form.Paa0011Form;
import jp.co.cyms.common.Constant;

/**
 *
 * @author LongND
 * @since 2017/12/22
 */
@SuppressWarnings("serial")
public class Paa0011Action extends Paa0011Form {

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * Login system
	 *
	 * @return String
	 */
	public String login() {
		try {
			Paa0011BL logic = new Paa0011BL();
			userInfo.setUserId(userId);
			userInfo.setUserPassword(userPassword);
			userInfo = logic.login(userInfo);
			if (userInfo == null) {
				throw new NullPointerException("UserInfo NULL");
			}
			setMenu(logic, userInfo);

			int timeOut = Constant.SESSION_TIME_OUT;
			request.getSession().setMaxInactiveInterval(timeOut * 60);

			session.put(Constant.SESSION_KEY_USER_INFO, userInfo);
		} catch (Exception e) {
			LOG.error("Login progress", e);
			super.addActionError("EA-0011");
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * set menu left bar
	 * 
	 * @param logic
	 * @param userInfo
	 * @throws Exception
	 */
	private void setMenu(Paa0011BL logic, UserBean userInfo) throws Exception {
		menuInfo = logic.getListMenu(userInfo);
		session.put(Constant.SESSION_KEY_MENU_INFO, menuInfo);
	}

	/**
	 * Logout system
	 *
	 * @return String
	 */
	public String logout() {
		session.clear();
		return SUCCESS;
	}

}
