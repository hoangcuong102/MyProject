package jp.co.cyms.apps.fad001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;
/**
 * PC Stock Management Bean
 * 
 * @author anhnt2
 * @since 2018/01/04
 */
public class Pad0021Bean extends BaseDBBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Category Code */
	protected String categoryCd;

	/** Category Name */
	protected String categoryName;

	/** Item Code*/
	protected String itemCd;

	/** Item Name */
	protected String itemName;

	/** Country Code */
	protected String countryCd;
	
	/** STOCK - Qty */
	protected String qty1;
	
	/** STOCK - Date */
	protected String qtyLoi1;
	
	/** Quantity 1 */
	protected String qty2;
	
	/** L1 - Date */
	protected String qtyLoi2;
	
	/** Quantity 2 */
	protected String qty3;
	
	/** L2 - Date */
	protected String qtyLoi3;
	
	/** Quantity 3 */
	protected String qty4;
	
	/** L3- Date */
	protected String qtyLoi4;
	
	/** Balance */
	protected String balance;
	
	/** Category Code From Db*/
	protected String categoryCdFromDb;
	
	/** Item Code From Db*/
	protected String itemCdFromDb;

	/**
	 * @return the categoryCd
	 */
	public String getCategoryCd() {
		return categoryCd;
	}

	/**
	 * @param categoryCd the categoryCd to set
	 */
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * @param itemCd the itemCd to set
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the countryCd
	 */
	public String getCountryCd() {
		return countryCd;
	}

	/**
	 * @param countryCd the countryCd to set
	 */
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	/**
	 * @return the qty1
	 */
	public String getQty1() {
		return qty1;
	}

	/**
	 * @param qty1 the qty1 to set
	 */
	public void setQty1(String qty1) {
		this.qty1 = qty1;
	}

	/**
	 * @return the qtyLoi1
	 */
	public String getQtyLoi1() {
		return qtyLoi1;
	}

	/**
	 * @param qtyLoi1 the qtyLoi1 to set
	 */
	public void setQtyLoi1(String qtyLoi1) {
		this.qtyLoi1 = qtyLoi1;
	}

	/**
	 * @return the qtyLoi2
	 */
	public String getQtyLoi2() {
		return qtyLoi2;
	}

	/**
	 * @param qtyLoi2 the qtyLoi2 to set
	 */
	public void setQtyLoi2(String qtyLoi2) {
		this.qtyLoi2 = qtyLoi2;
	}

	/**
	 * @return the qty2
	 */
	public String getQty2() {
		return qty2;
	}

	/**
	 * @param qty2 the qty2 to set
	 */
	public void setQty2(String qty2) {
		this.qty2 = qty2;
	}

	/**
	 * @return the qty3
	 */
	public String getQty3() {
		return qty3;
	}

	/**
	 * @param qty3 the qty3 to set
	 */
	public void setQty3(String qty3) {
		this.qty3 = qty3;
	}

	/**
	 * @return the qtyLoi3
	 */
	public String getQtyLoi3() {
		return qtyLoi3;
	}

	/**
	 * @return the qtyLoi4
	 */
	public String getQtyLoi4() {
		return qtyLoi4;
	}

	/**
	 * @param qtyLoi4 the qtyLoi4 to set
	 */
	public void setQtyLoi4(String qtyLoi4) {
		this.qtyLoi4 = qtyLoi4;
	}

	/**
	 * @param qtyLoi3 the qtyLoi3 to set
	 */
	public void setQtyLoi3(String qtyLoi3) {
		this.qtyLoi3 = qtyLoi3;
	}

	/**
	 * @return the qty4
	 */
	public String getQty4() {
		return qty4;
	}

	/**
	 * @param qty4 the qty4 to set
	 */
	public void setQty4(String qty4) {
		this.qty4 = qty4;
	}

	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}

	/**
	 * @return the categoryCdFromDb
	 */
	public String getCategoryCdFromDb() {
		return categoryCdFromDb;
	}

	/**
	 * @param categoryCdFromDb the categoryCdFromDb to set
	 */
	public void setCategoryCdFromDb(String categoryCdFromDb) {
		this.categoryCdFromDb = categoryCdFromDb;
	}

	/**
	 * @return the itemCdFromDb
	 */
	public String getItemCdFromDb() {
		return itemCdFromDb;
	}

	/**
	 * @param itemCdFromDb the itemCdFromDb to set
	 */
	public void setItemCdFromDb(String itemCdFromDb) {
		this.itemCdFromDb = itemCdFromDb;
	}
}
