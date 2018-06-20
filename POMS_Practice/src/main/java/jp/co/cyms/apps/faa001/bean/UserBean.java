package jp.co.cyms.apps.faa001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * 
 * @author longnd
 *
 */
public class UserBean extends BaseDBBean implements Serializable {

	private static final long serialVersionUID = -33333331L;

	private String userId;

	private String userPassword;

	private String userAuthorityCd;
	
	private String userAuthorityName;

	private String userName;

	private String userCompanyCd;
	
	private String companyName;

	private String userEmail;
	
	private String countryCd;
	
	private String countryName;

	/**
	 * userIdを取得します。
	 * 
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * userIdを設定します。
	 * 
	 * @param userId
	 *            userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userPasswordを取得します。
	 * 
	 * @return userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * userPasswordを設定します。
	 * 
	 * @param userPassword
	 *            userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * userAuthorityCdを取得します。
	 * 
	 * @return userAuthorityCd
	 */
	public String getUserAuthorityCd() {
		return userAuthorityCd;
	}

	/**
	 * userAuthorityCdを設定します。
	 * 
	 * @param userAuthorityCd
	 *            userAuthorityCd
	 */
	public void setUserAuthorityCd(String userAuthorityCd) {
		this.userAuthorityCd = userAuthorityCd;
	}

	public String getUserAuthorityName() {
		return userAuthorityName;
	}

	public void setUserAuthorityName(String userAuthorityName) {
		this.userAuthorityName = userAuthorityName;
	}

	/**
	 * userNameを取得します。
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * userNameを設定します。
	 * 
	 * @param userName
	 *            userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * userCompanyCdを取得します。
	 * 
	 * @return userCompanyCd
	 */
	public String getUserCompanyCd() {
		return userCompanyCd;
	}

	/**
	 * userCompanyCdを設定します。
	 * 
	 * @param userCompanyCd
	 *            userCompanyCd
	 */
	public void setUserCompanyCd(String userCompanyCd) {
		this.userCompanyCd = userCompanyCd;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
