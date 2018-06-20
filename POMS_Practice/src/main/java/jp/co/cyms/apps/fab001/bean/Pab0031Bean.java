package jp.co.cyms.apps.fab001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * Active Item Master Pab0031Bean
 * 
 * @author binhvh
 * @since 2018/01/03
 */
public class Pab0031Bean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String countryCd;
	private String categoryCd;
	private String itemCd;
	private String itemName;
	private String activeFg;

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getActiveFg() {
		return activeFg;
	}

	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}
}
