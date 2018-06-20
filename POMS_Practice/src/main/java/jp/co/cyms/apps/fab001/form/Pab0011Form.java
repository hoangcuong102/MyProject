package jp.co.cyms.apps.fab001.form;

import jp.co.cyms.base.BaseAction;

/**
 * Item Master Pab0011Form
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0011Form extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String itemCd;
	protected String itemName;
	protected String categoryCd;
	protected String categoryName;
	protected String itemBrand;
	protected String addMemory;
	protected String noUse;
	protected String unitPrice;

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

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getAddMemory() {
		return addMemory;
	}

	public void setAddMemory(String addMemory) {
		this.addMemory = addMemory;
	}

	public String getNoUse() {
		return noUse;
	}

	public void setNoUse(String noUse) {
		this.noUse = noUse;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

}
