package jp.co.cyms.apps.fac001.bean;

public class AddonBean {

	private String addonCd;
	private String addonName;
	private String addonType;
	private Integer displayOrder;
	private Integer config;
	private String sectionName;

	public String getAddonCd() {
		return addonCd;
	}

	public void setAddonCd(String addonCd) {
		this.addonCd = addonCd;
	}

	public String getAddonName() {
		return addonName;
	}

	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}

	public String getAddonType() {
		return addonType;
	}

	public void setAddonType(String addonType) {
		this.addonType = addonType;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getConfig() {
		return config;
	}

	public void setConfig(Integer config) {
		this.config = config;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}
