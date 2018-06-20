package jp.co.cyms.apps.fad001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;
/**
 * Data Download Bean
 * 
 * @author anhnt2
 * @since 2018/01/13
 */
public class Pad0011Bean extends BaseDBBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Dept ID */
	protected String deptCd;
	
	/** Dept Name */
	protected String deptName;

	/** Company ID */
	protected String companyCd;

	/** Company Name */
	protected String companyName;

	/** Order No. */
	protected String orderId;
	
	/** Category ID */
	protected String categoryCd;
	
	/** Category Name */
	protected String categoryName;
	
	/** Addon ID */
	protected String addonCd;

	/** Addon Name */
	protected String addonName;

	/** Item ID */
	protected String itemCd;

	/** Item Name */
	protected String itemName;

	/** Billable Quantity */
	protected String billableQuantity;

	/** Billable Amount */
	protected String billableAmount;

	/** Deliver Quantity */
	protected String deliverQuantity;
	
	/** Deliver Amount */
	protected String deliverAmount;
	
	protected String billMthStart;
	
	protected String billMthEnd;

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAddonName() {
		return addonName;
	}

	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBillableQuantity() {
		return billableQuantity;
	}

	public void setBillableQuantity(String billableQuantity) {
		this.billableQuantity = billableQuantity;
	}

	public String getBillableAmount() {
		return billableAmount;
	}

	public void setBillableAmount(String billableAmount) {
		this.billableAmount = billableAmount;
	}

	public String getDeliverQuantity() {
		return deliverQuantity;
	}

	public void setDeliverQuantity(String deliverQuantity) {
		this.deliverQuantity = deliverQuantity;
	}

	public String getDeliverAmount() {
		return deliverAmount;
	}

	public void setDeliverAmount(String deliverAmount) {
		this.deliverAmount = deliverAmount;
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

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getBillMthStart() {
		return billMthStart;
	}

	public void setBillMthStart(String billMthStart) {
		this.billMthStart = billMthStart;
	}

	public String getBillMthEnd() {
		return billMthEnd;
	}

	public void setBillMthEnd(String billMthEnd) {
		this.billMthEnd = billMthEnd;
	}

}
