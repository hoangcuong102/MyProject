package jp.co.cyms.apps.fad001.form;

import jp.co.cyms.base.BaseAction;
/**
 * PC Stock Management Form
 * 
 * @author anhnt2
 * @since 2018/01/04
 */
public class Pad0021Form extends BaseAction {

	private static final long serialVersionUID = 1L;
	protected String countryCd;
	
	protected String previousCountry;
	
	/**
	 * @return the countryCd
	 */
	public String getCountryCd() {
		return countryCd;
	}
	/**
	 * @param countryCd the countryCd to set
	 */
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	/**
	 * @return the previousCountry
	 */
	public String getPreviousCountry() {
		return previousCountry;
	}
	/**
	 * @param previousCountry the previousCountry to set
	 */
	public void setPreviousCountry(String previousCountry) {
		this.previousCountry = previousCountry;
	}
	
}
