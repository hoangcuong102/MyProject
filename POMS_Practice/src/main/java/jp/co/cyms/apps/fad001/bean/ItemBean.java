package jp.co.cyms.apps.fad001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * Item Class
 *
 * @author anhnt2
 * @since 2018/01/03
 */
public class ItemBean extends BaseDBBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** The category ID */
	private String categoryCd;

	/** The item ID */
	private String itemCd;
	
	/** The The item name */
	private String itemName;

	/**
	 * Get category id
	 * @return categoryCd
	 */
	public String getCategoryCd() {
		return categoryCd;
	}
	
	/**
	 * Set category id
	 */
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}
	
	/**
	 * Get item ID
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}
	
	/**
	 * Set item ID
	 * @param itemCd
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	
	/**
	 * Get item name
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Set item name
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
