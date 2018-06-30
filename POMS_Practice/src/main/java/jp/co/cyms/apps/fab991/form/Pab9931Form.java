package jp.co.cyms.apps.fab991.form;

import java.util.List;

import jp.co.cyms.base.BaseAction;

public class Pab9931Form extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4946380364532076265L;
	private String countryCD;
	private String categoryCD;
	private List<String> listActiveItemCD;
	private List<String> listInactiveItemCD;
	
	public String getCountryCD() {
		return countryCD;
	}
	public void setCountryCD(String countryCD) {
		this.countryCD = countryCD;
	}
	public String getCategoryCD() {
		return categoryCD;
	}
	public void setCategoryCD(String categoryCD) {
		this.categoryCD = categoryCD;
	}
	public List<String> getListActiveItemCD() {
		return listActiveItemCD;
	}
	public void setListActiveItemCD(List<String> listActiveItemCD) {
		this.listActiveItemCD = listActiveItemCD;
	}
	public List<String> getListInactiveItemCD() {
		return listInactiveItemCD;
	}
	public void setListInactiveItemCD(List<String> listInactiveItemCD) {
		this.listInactiveItemCD = listInactiveItemCD;
	}
	
}
