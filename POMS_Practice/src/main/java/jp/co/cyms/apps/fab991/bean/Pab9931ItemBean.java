package jp.co.cyms.apps.fab991.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class Pab9931ItemBean extends BaseDBBean implements Serializable {

	/**
	 * created by DuyNK
	 * 21/06/2018 
	 */
	private static final long serialVersionUID = -3260264645614679138L;
	
	private String categoryCD;
	private String countryCD;
	private String itemCD;
	private String itemName;
	private String activeFG;
	
	public Pab9931ItemBean() {
		// TODO Auto-generated constructor stub
	}

	public Pab9931ItemBean(String categoryCD, String countryCD, String itemCD, String itemName, String activeFG) {
		super();
		this.categoryCD = categoryCD;
		this.countryCD = countryCD;
		this.itemCD = itemCD;
		this.itemName = itemName;
		this.activeFG = activeFG;
	}

	public String getCategoryCD() {
		return categoryCD;
	}

	public void setCategoryCD(String categoryCD) {
		this.categoryCD = categoryCD;
	}

	public String getCountryCD() {
		return countryCD;
	}

	public void setCountryCD(String countryCD) {
		this.countryCD = countryCD;
	}

	public String getItemCD() {
		return itemCD;
	}

	public void setItemCD(String itemCD) {
		this.itemCD = itemCD;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getActiveFG() {
		return activeFG;
	}

	public void setActiveFG(String activeFG) {
		this.activeFG = activeFG;
	}

}
