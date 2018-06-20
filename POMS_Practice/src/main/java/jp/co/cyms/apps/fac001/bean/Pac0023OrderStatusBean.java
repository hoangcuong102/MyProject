package jp.co.cyms.apps.fac001.bean;

public class Pac0023OrderStatusBean {

	private String timestamp;
	private String updatedUser;
	private String phase;
	private String action;
	private String deliverCategory;
	private Integer deliveryQty;
	private Integer itemQty;
	private String categoryName;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDeliverCategory() {
		return deliverCategory;
	}

	public void setDeliverCategory(String deliverCategory) {
		this.deliverCategory = deliverCategory;
	}

	public Integer getDeliveryQty() {
		return deliveryQty;
	}

	public void setDeliveryQty(Integer deliveryQty) {
		this.deliveryQty = deliveryQty;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
