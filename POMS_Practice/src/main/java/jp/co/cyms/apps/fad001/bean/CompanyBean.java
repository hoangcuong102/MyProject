package jp.co.cyms.apps.fad001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * Company Class
 *
 * @author anhnt2
 * @since 2018/01/11
 */
public class CompanyBean extends BaseDBBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** The company ID */
	private String companyCd;
	
	/** The company name */
	private String companyName;

	/**
	 * Get company id
	 * @return companyCd
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	
	/**
	 * Set company id
	 */
	public void setCompnayCd(String companyCd) {
		this.companyCd = companyCd;
	}
	
	/**
	 * Get company name
	 * @return companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * Set company name
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
