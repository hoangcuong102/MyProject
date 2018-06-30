package jp.co.cyms.apps.faa001.bl;

import jp.co.cyms.apps.faa001.bean.*;
import jp.co.cyms.apps.faa001.dao.Paa001Dao;
import jp.co.cyms.base.BaseLogic;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.properties.MenuProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * @author longnd
 *
 */
@Service
public class Paa0011BL extends BaseLogic {
	/**
	 * Login system
	 *
	 * @param bean
	 * @return UserBean
	 * @throws Exception
	 */
	public UserBean login(UserBean bean) throws Exception {
		UserBean userInfo = new Paa001Dao().authorityUser(bean);
		if (userInfo != null) {
			switch (Integer.parseInt(userInfo.getUserAuthorityCd())) {
			case Constant.GENERAL_USER_CD:
				userInfo.setUserAuthorityName(Constant.GENERAL_USER);
			case Constant.KDDI_CD:
				userInfo.setUserAuthorityName(Constant.KDDI);
			case Constant.SANKYU_ADMIN_CD:
				userInfo.setUserAuthorityName(Constant.SANKYU_ADMIN);
			}
		}
		return userInfo;

	}

	/**
	 * 
	 * @param userInfo
	 * @param menuParam
	 * @return true if user has permission
	 * @throws Exception
	 */
	public boolean hasPermissionScreen(UserBean userInfo, MenuQueryParam menuParam) throws Exception {
		boolean check = true;
		if (Integer.toString(Constant.GENERAL_USER_CD).equals(userInfo.getUserAuthorityCd())
				&& !Constant.FUNCTION_DISPLAY_ONLY_GENERAL_USER.equals(menuParam.getFunctionId())
				&& !Constant.FUNCTION_DISPLAY_ONLY_GENERAL_USER2.equals(menuParam.getFunctionId())) {
			check = false;
		}
		return check;
	}

	/**
	 * 
	 * @param userInfo
	 * @return list menu for side bar with permission of user
	 * @throws IOException
	 */
	public List<MenuBean> getListMenu(UserBean userInfo) throws IOException {
		List<MenuBean> listMenu = new ArrayList<MenuBean>();
		List<MenuProperties.MenuKey> menuEnums = Arrays.asList(MenuProperties.MenuKey.values());
		for (MenuProperties.MenuKey data : menuEnums) {
			MenuBean menuBean = MenuProperties.get(data);
			if (!Integer.toString(Constant.GENERAL_USER_CD).equals(userInfo.getUserAuthorityCd())
					|| Constant.FUNCTION_DISPLAY_ONLY_GENERAL_USER.equals(menuBean.getFunctionId())
					||  Constant.FUNCTION_DISPLAY_ONLY_GENERAL_USER2.equals(menuBean.getFunctionId())) {
				listMenu.add(menuBean);
			}
		}
		return listMenu;
	}
}
