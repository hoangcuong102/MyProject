package jp.co.cyms.common.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class CategoryBean extends BaseDBBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoryCd;
	private String categoryName;
	private String categoryType;
	private String categoryAbbrev;
	private Integer displayOrder;
	private String sectionName;
	private String addonCd;

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryAbbrev() {
		return categoryAbbrev;
	}

	public void setCategoryAbbrev(String categoryAbbrev) {
		this.categoryAbbrev = categoryAbbrev;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getAddonCd() {
		return addonCd;
	}

	public void setAddonCd(String addonCd) {
		this.addonCd = addonCd;
	}

}
