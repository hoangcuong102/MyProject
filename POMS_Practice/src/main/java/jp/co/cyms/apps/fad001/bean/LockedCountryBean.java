package jp.co.cyms.apps.fad001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class LockedCountryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String countryCd;
	private String lockedUserName;

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getLockedUserName() {
		return lockedUserName;
	}

	public void setLockedUserName(String lockedUserName) {
		this.lockedUserName = lockedUserName;
	}
	
}
