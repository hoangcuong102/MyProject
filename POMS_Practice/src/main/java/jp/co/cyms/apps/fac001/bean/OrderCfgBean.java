package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class OrderCfgBean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderId;
	private String categoryCd;
	private String addonCd;
	private Integer addonConfig;
	private String itemCd;
	private Integer itemQty;
	private Float unitPrice;
	private Float amount;
	private String leaseType;
	private String warningFg;
	private String itemName;
	private String itemBrand;
	private String itemRemark;
	private String sectionName;
	private Integer addonDisplayOrder;
	private Integer categoryDisplayOrder;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getAddonCd() {
		return addonCd;
	}

	public void setAddonCd(String addonCd) {
		this.addonCd = addonCd;
	}

	public Integer getAddonConfig() {
		return addonConfig;
	}

	public void setAddonConfig(Integer addonConfig) {
		this.addonConfig = addonConfig;
	}

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	public String getWarningFg() {
		return warningFg;
	}

	public void setWarningFg(String warningFg) {
		this.warningFg = warningFg;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	/**
	 * @return the addonDisplayOrder
	 */
	public Integer getAddonDisplayOrder() {
		return addonDisplayOrder;
	}

	/**
	 * @param addonDisplayOrder
	 *            the addonDisplayOrder to set
	 */
	public void setAddonDisplayOrder(Integer addonDisplayOrder) {
		this.addonDisplayOrder = addonDisplayOrder;
	}

	/**
	 * @return the categoryDisplayOrder
	 */
	public Integer getCategoryDisplayOrder() {
		return categoryDisplayOrder;
	}

	/**
	 * @param categoryDisplayOrder
	 *            the categoryDisplayOrder to set
	 */
	public void setCategoryDisplayOrder(Integer categoryDisplayOrder) {
		this.categoryDisplayOrder = categoryDisplayOrder;
	}

}
