package jp.co.cyms.apps.fab991.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class Pab9931CountryBean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8639744132874090470L;

	private String countryCD;
	
	public Pab9931CountryBean() {
		// TODO Auto-generated constructor stub
	}

	public Pab9931CountryBean(String countryCD) {
		super();
		this.countryCD = countryCD;
	}

	public String getCountryCD() {
		return countryCD;
	}

	public void setCountryCD(String countryCD) {
		this.countryCD = countryCD;
	}
}
