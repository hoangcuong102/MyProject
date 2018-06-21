package jp.co.cyms.common.properties;

import java.io.IOException;
import java.util.Properties;

import com.opensymphony.xwork2.util.logging.LoggerFactory;

import jp.co.cyms.apps.faa001.bean.MenuBean;
import jp.co.cyms.common.SystemCommon;

public class MenuProperties {
	protected static com.opensymphony.xwork2.util.logging.Logger LOG = LoggerFactory.getLogger(ErrorProperties.class);

	public static enum MenuKey {

		MENU_10,
		MENU_11,
		MENU_12,
		MENU_13,
		MENU_20,
		MENU_21,
		MENU_22,
		MENU_23,
		MENU_30,
		MENU_31,
		MENU_32,
		MENU_33,
		MENU_34,
		;

		public static MenuKey get(String key) {
			MenuKey ret = null;
			try {
				ret = valueOf(key);
			} catch (IllegalArgumentException e) {
				LOG.error("Error key:", e);
			}
			return ret;
		}
	}

	public static MenuBean get(MenuKey key) throws IOException {
		SystemCommon system = new SystemCommon();
		Properties messageProp = system.getProperties("/properties/menu.properties");
		MenuBean menuBean = new MenuBean();
		menuBean.setMenuId(messageProp.getProperty(key.toString()+"_ID"));
		menuBean.setScreenId(messageProp.getProperty(key.toString()+"_SCREEN_ID"));
		menuBean.setMenuName(messageProp.getProperty(key.toString()+"_NAME"));
		menuBean.setFunctionId(messageProp.getProperty(key.toString()+"_FUNCTION_ID"));
		menuBean.setBlockDisplay(messageProp.getProperty(key.toString()+"_GROUP_ID"));
		menuBean.setOrderDisplay(messageProp.getProperty(key.toString()+"_ORDER_ID"));
		return menuBean;

	}
}
