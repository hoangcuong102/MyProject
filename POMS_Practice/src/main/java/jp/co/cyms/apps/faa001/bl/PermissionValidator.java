package jp.co.cyms.apps.faa001.bl;

import jp.co.cyms.apps.faa001.bean.MenuQueryParam;
import jp.co.cyms.apps.faa001.bean.UserBean;

/**
 * 
 * @author longnd
 *
 */
public class PermissionValidator {
	private UserBean userBean;
	private MenuQueryParam menuParam;

	public PermissionValidator(UserBean userBean, MenuQueryParam rawMenuParam) {
		this.userBean = userBean;
		this.menuParam = rawMenuParam;
	}

	/**
	 * return true if user has permission
	 * @return
	 * @throws Exception
	 */
	public boolean check() throws Exception {
		return new Paa0011BL().hasPermissionScreen(userBean, menuParam);
	}
}
