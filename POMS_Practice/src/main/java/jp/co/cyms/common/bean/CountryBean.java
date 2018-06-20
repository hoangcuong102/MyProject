package jp.co.cyms.common.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class CountryBean extends BaseDBBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String countryCd;
	private String countryName;
	private String timeDiffrence;
	private Integer displayOrder;

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

	public String getTimeDiffrence() {
		return timeDiffrence;
	}

	public void setTimeDiffrence(String timeDiffrence) {
		this.timeDiffrence = timeDiffrence;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
}
