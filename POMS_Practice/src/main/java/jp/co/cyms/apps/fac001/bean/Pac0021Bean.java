package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;
import java.util.Date;

import jp.co.cyms.base.BaseDBBean;

/**
 * PC Order Progress Update / Inquiry Pac0021Bean
 * 
 * @author binhvh
 * @since 2018/01/12
 */
public class Pac0021Bean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderId;
	private String deptCd;
	private String ttlMrc;
	private String ttlOtc;
	private String categoryAbbrev;
	private String categoryName;
	private String categoryCd;
	private Integer displayOrder;
	private Integer quantity;
	// private String updatedDtLocal;
	private String lastUpdated;
	// private String updatedUser;
	private String updatedByUser;
	private String sankyuRegistDt;
	private String sankyuOrderDt;
	private String seaRequestDt;
	private String kddiAcceptDt;
	private String kddiOrderDt;
	private String kddiReceiveDt;
	private Integer kddiDeliverQty;
	private String kddiDeliverDt;
	private String kddiLeaseDt;
	private String deleteFg;
	private String orderIdExpiring;

	private String countryCd;
	private String companyCd;
	private String itemCd;
	private String userCreateOrder;
	private Integer kddiDeliverQtyDoNo;
	private Integer countRecordDeliver;
	private String kddiDeliverDtDoNo;
	private Date kddiDeliverDtDoNoTypeDate;
	private Integer seqNo;
	/** Number DO in ORDER_DO table has DO_SIGNBACK_FG = 1 */
	private Integer countDoSignback;

	public Pac0021Bean() {
		super();
	}

	public Pac0021Bean(String orderId, String deptCd, String ttlMrc, String ttlOtc, String categoryAbbrev,
			Integer quantity, String updatedDtLocal, String updatedUser, String sankyuRegistDt, String sankyuOrderDt,
			String seaRequestDt, String kddiAcceptDt, String kddiOrderDt, String kddiReceiveDt, Integer kddiDeliverQty,
			String kddiDeliverDt, String kddiLeaseDt, String deleteFg) {
		super();
		this.orderId = orderId;
		this.deptCd = deptCd;
		this.ttlMrc = ttlMrc;
		this.ttlOtc = ttlOtc;
		this.categoryAbbrev = categoryAbbrev;
		this.quantity = quantity;
		// this.updatedDtLocal = updatedDtLocal;
		this.updatedUser = updatedUser;
		this.sankyuRegistDt = sankyuRegistDt;
		this.sankyuOrderDt = sankyuOrderDt;
		this.seaRequestDt = seaRequestDt;
		this.kddiAcceptDt = kddiAcceptDt;
		this.kddiOrderDt = kddiOrderDt;
		this.kddiReceiveDt = kddiReceiveDt;
		this.kddiDeliverQty = kddiDeliverQty;
		this.kddiDeliverDt = kddiDeliverDt;
		this.kddiLeaseDt = kddiLeaseDt;
		this.deleteFg = deleteFg;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the deptCd
	 */
	public String getDeptCd() {
		return deptCd;
	}

	/**
	 * @param deptCd
	 *            the deptCd to set
	 */
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	/**
	 * @return the ttlMrc
	 */
	public String getTtlMrc() {
		return ttlMrc;
	}

	/**
	 * @param ttlMrc
	 *            the ttlMrc to set
	 */
	public void setTtlMrc(String ttlMrc) {
		this.ttlMrc = ttlMrc;
	}

	/**
	 * @return the ttlOtc
	 */
	public String getTtlOtc() {
		return ttlOtc;
	}

	/**
	 * @param ttlOtc
	 *            the ttlOtc to set
	 */
	public void setTtlOtc(String ttlOtc) {
		this.ttlOtc = ttlOtc;
	}

	/**
	 * @return the categoryAbbrev
	 */
	public String getCategoryAbbrev() {
		return categoryAbbrev;
	}

	/**
	 * @param categoryAbbrev
	 *            the categoryAbbrev to set
	 */
	public void setCategoryAbbrev(String categoryAbbrev) {
		this.categoryAbbrev = categoryAbbrev;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the lastUpdated
	 */
	public String getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated
	 *            the lastUpdated to set
	 */
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the updatedByUser
	 */
	public String getUpdatedByUser() {
		return updatedByUser;
	}

	/**
	 * @param updatedByUser
	 *            the updatedByUser to set
	 */
	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	/**
	 * @return the sankyuRegistDt
	 */
	public String getSankyuRegistDt() {
		return sankyuRegistDt;
	}

	/**
	 * @param sankyuRegistDt
	 *            the sankyuRegistDt to set
	 */
	public void setSankyuRegistDt(String sankyuRegistDt) {
		this.sankyuRegistDt = sankyuRegistDt;
	}

	/**
	 * @return the sankyuOrderDt
	 */
	public String getSankyuOrderDt() {
		return sankyuOrderDt;
	}

	/**
	 * @param sankyuOrderDt
	 *            the sankyuOrderDt to set
	 */
	public void setSankyuOrderDt(String sankyuOrderDt) {
		this.sankyuOrderDt = sankyuOrderDt;
	}

	/**
	 * @return the seaRequestDt
	 */
	public String getSeaRequestDt() {
		return seaRequestDt;
	}

	/**
	 * @param seaRequestDt
	 *            the seaRequestDt to set
	 */
	public void setSeaRequestDt(String seaRequestDt) {
		this.seaRequestDt = seaRequestDt;
	}

	/**
	 * @return the kddiAcceptDt
	 */
	public String getKddiAcceptDt() {
		return kddiAcceptDt;
	}

	/**
	 * @param kddiAcceptDt
	 *            the kddiAcceptDt to set
	 */
	public void setKddiAcceptDt(String kddiAcceptDt) {
		this.kddiAcceptDt = kddiAcceptDt;
	}

	/**
	 * @return the kddiOrderDt
	 */
	public String getKddiOrderDt() {
		return kddiOrderDt;
	}

	/**
	 * @param kddiOrderDt
	 *            the kddiOrderDt to set
	 */
	public void setKddiOrderDt(String kddiOrderDt) {
		this.kddiOrderDt = kddiOrderDt;
	}

	/**
	 * @return the kddiReceiveDt
	 */
	public String getKddiReceiveDt() {
		return kddiReceiveDt;
	}

	/**
	 * @param kddiReceiveDt
	 *            the kddiReceiveDt to set
	 */
	public void setKddiReceiveDt(String kddiReceiveDt) {
		this.kddiReceiveDt = kddiReceiveDt;
	}

	/**
	 * @return the kddiDeliverQty
	 */
	public Integer getKddiDeliverQty() {
		return kddiDeliverQty;
	}

	/**
	 * @param kddiDeliverQty
	 *            the kddiDeliverQty to set
	 */
	public void setKddiDeliverQty(Integer kddiDeliverQty) {
		this.kddiDeliverQty = kddiDeliverQty;
	}

	/**
	 * @return the kddiDeliverDt
	 */
	public String getKddiDeliverDt() {
		return kddiDeliverDt;
	}

	/**
	 * @param kddiDeliverDt
	 *            the kddiDeliverDt to set
	 */
	public void setKddiDeliverDt(String kddiDeliverDt) {
		this.kddiDeliverDt = kddiDeliverDt;
	}

	/**
	 * @return the kddiLeaseDt
	 */
	public String getKddiLeaseDt() {
		return kddiLeaseDt;
	}

	/**
	 * @param kddiLeaseDt
	 *            the kddiLeaseDt to set
	 */
	public void setKddiLeaseDt(String kddiLeaseDt) {
		this.kddiLeaseDt = kddiLeaseDt;
	}

	/**
	 * @return the deleteFg
	 */
	public String getDeleteFg() {
		return deleteFg;
	}

	/**
	 * @param deleteFg
	 *            the deleteFg to set
	 */
	public void setDeleteFg(String deleteFg) {
		this.deleteFg = deleteFg;
	}

	/**
	 * @return the orderIdExpiring
	 */
	public String getOrderIdExpiring() {
		return orderIdExpiring;
	}

	/**
	 * @param orderIdExpiring
	 *            the orderIdExpiring to set
	 */
	public void setOrderIdExpiring(String orderIdExpiring) {
		this.orderIdExpiring = orderIdExpiring;
	}

	/**
	 * @return the countryCd
	 */
	public String getCountryCd() {
		return countryCd;
	}

	/**
	 * @param countryCd
	 *            the countryCd to set
	 */
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	/**
	 * @return the companyCd
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * @param companyCd
	 *            the companyCd to set
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * @return the itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * @param itemCd
	 *            the itemCd to set
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * @return the userCreateOrder
	 */
	public String getUserCreateOrder() {
		return userCreateOrder;
	}

	/**
	 * @param userCreateOrder
	 *            the userCreateOrder to set
	 */
	public void setUserCreateOrder(String userCreateOrder) {
		this.userCreateOrder = userCreateOrder;
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

	/**
	 * @return the kddiDeliverQtyDoNo
	 */
	public Integer getKddiDeliverQtyDoNo() {
		return kddiDeliverQtyDoNo;
	}

	/**
	 * @param kddiDeliverQtyDoNo
	 *            the kddiDeliverQtyDoNo to set
	 */
	public void setKddiDeliverQtyDoNo(Integer kddiDeliverQtyDoNo) {
		this.kddiDeliverQtyDoNo = kddiDeliverQtyDoNo;
	}

	/**
	 * @return the countRecordDeliver
	 */
	public Integer getCountRecordDeliver() {
		return countRecordDeliver;
	}

	/**
	 * @param countRecordDeliver
	 *            the countRecordDeliver to set
	 */
	public void setCountRecordDeliver(Integer countRecordDeliver) {
		this.countRecordDeliver = countRecordDeliver;
	}

	/**
	 * @return the categoryCd
	 */
	public String getCategoryCd() {
		return categoryCd;
	}

	/**
	 * @param categoryCd
	 *            the categoryCd to set
	 */
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	/**
	 * @return the displayOrder
	 */
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder
	 *            the displayOrder to set
	 */
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * @return the kddiDeliverDtDoNo
	 */
	public String getKddiDeliverDtDoNo() {
		return kddiDeliverDtDoNo;
	}

	/**
	 * @param kddiDeliverDtDoNo
	 *            the kddiDeliverDtDoNo to set
	 */
	public void setKddiDeliverDtDoNo(String kddiDeliverDtDoNo) {
		this.kddiDeliverDtDoNo = kddiDeliverDtDoNo;
	}

	/**
	 * @return the kddiDeliverDtDoNoTypeDate
	 */
	public Date getKddiDeliverDtDoNoTypeDate() {
		return kddiDeliverDtDoNoTypeDate;
	}

	/**
	 * @param kddiDeliverDtDoNoTypeDate
	 *            the kddiDeliverDtDoNoTypeDate to set
	 */
	public void setKddiDeliverDtDoNoTypeDate(Date kddiDeliverDtDoNoTypeDate) {
		this.kddiDeliverDtDoNoTypeDate = kddiDeliverDtDoNoTypeDate;
	}

	/**
	 * @return the seqNo
	 */
	public Integer getSeqNo() {
		return seqNo;
	}

	/**
	 * @param seqNo
	 *            the seqNo to set
	 */
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	/**
	 * @return the countDoSignback
	 */
	public Integer getCountDoSignback() {
		return countDoSignback;
	}

	/**
	 * @param countDoSignback
	 *            the countDoSignback to set
	 */
	public void setCountDoSignback(Integer countDoSignback) {
		this.countDoSignback = countDoSignback;
	}

}
