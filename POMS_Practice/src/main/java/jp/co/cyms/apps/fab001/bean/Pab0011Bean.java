package jp.co.cyms.apps.fab001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * Item Master Pab0011Bean
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0011Bean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String categoryCd;
	private String categoryName;
	private String itemCd;
	private String itemName;
	private String itemBrand;
	private String countryCd;
	private Double unitPrice;
	private String warningFg;
	private String noUseFg;
	private String deleteFg;

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

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getWarningFg() {
		return warningFg;
	}

	public void setWarningFg(String warningFg) {
		this.warningFg = warningFg;
	}

	public String getNoUseFg() {
		return noUseFg;
	}

	public void setNoUseFg(String noUseFg) {
		this.noUseFg = noUseFg;
	}

	public String getDeleteFg() {
		return deleteFg;
	}

	public void setDeleteFg(String deleteFg) {
		this.deleteFg = deleteFg;
	}
}
