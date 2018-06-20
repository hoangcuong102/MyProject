package jp.co.cyms.apps.faa001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * MenuBean class
 *
 * @author tichnv
 * @since 2016/11/22
 */
public class MenuBean extends BaseDBBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String menuId;

	private String screenId;

	private String menuName;

	private String functionId;

	private String blockDisplay;

	private String orderDisplay;

	/**
	 * menuIdを取得します。
	 * @return menuId
	 */
	public String getMenuId() {
	    return menuId;
	}

	/**
	 * menuIdを設定します。
	 * @param menuId menuId
	 */
	public void setMenuId(String menuId) {
	    this.menuId = menuId;
	}

	/**
	 * screenIdを取得します。
	 * @return screenId
	 */
	public String getScreenId() {
	    return screenId;
	}

	/**
	 * screenIdを設定します。
	 * @param screenId screenId
	 */
	public void setScreenId(String screenId) {
	    this.screenId = screenId;
	}

	/**
	 * menuNameを取得します。
	 * @return menuName
	 */
	public String getMenuName() {
	    return menuName;
	}

	/**
	 * menuNameを設定します。
	 * @param menuName menuName
	 */
	public void setMenuName(String menuName) {
	    this.menuName = menuName;
	}

	/**
	 * functionIdを取得します。
	 * @return functionId
	 */
	public String getFunctionId() {
	    return functionId;
	}

	/**
	 * functionIdを設定します。
	 * @param functionId functionId
	 */
	public void setFunctionId(String functionId) {
	    this.functionId = functionId;
	}


	/**
	 * blockDisplayを取得します。
	 * @return blockDisplay
	 */
	public String getBlockDisplay() {
	    return blockDisplay;
	}

	/**
	 * blockDisplayを設定します。
	 * @param blockDisplay blockDisplay
	 */
	public void setBlockDisplay(String blockDisplay) {
	    this.blockDisplay = blockDisplay;
	}

	/**
	 * orderDisplayを取得します。
	 * @return orderDisplay
	 */
	public String getOrderDisplay() {
	    return orderDisplay;
	}

	/**
	 * orderDisplayを設定します。
	 * @param orderDisplay orderDisplay
	 */
	public void setOrderDisplay(String orderDisplay) {
	    this.orderDisplay = orderDisplay;
	}


}
