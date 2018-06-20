package jp.co.cyms.apps.fac001.bean;

public class ItemOrder {

	private String orderId;
	private String categoryLarge;
	private String itemCd;
	private String itemName;
	private String categoryCd;
	private Integer qty;
	private String addonCd;
	private Integer addonConfig;
	private Float unitPrice;
	private String leaseType;
	private String warningFg;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCategoryLarge() {
		return categoryLarge;
	}

	public void setCategoryLarge(String categoryLarge) {
		this.categoryLarge = categoryLarge;
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

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
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

	public String getWarningFg() {
		return warningFg;
	}

	public void setWarningFg(String warningFg) {
		this.warningFg = warningFg;
	}

}
