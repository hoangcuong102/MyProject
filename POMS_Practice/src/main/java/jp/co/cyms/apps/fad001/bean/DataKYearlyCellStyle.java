package jp.co.cyms.apps.fad001.bean;

import org.apache.poi.ss.usermodel.CellStyle;

import jp.co.cyms.common.Constant;

/**
 * Cell style for ExcelPCLeaseInvoiceDataKYearly
 * 
 * @author binhvh
 * @since 2018/03/22
 */
public class DataKYearlyCellStyle {

	/** Common Cell Style. */
	/** Company Cell Style. */
	private CellStyle companyCellStyle;

	/** Cost Type Cell Style. */
	private CellStyle costTypeCellStyle;

	/** Category Top Cell Style. */
	private CellStyle categoryTopCellStyle;

	/** Category Cell Style. */
	private CellStyle categoryCellStyle;

	/** Item Top Cell Style. */
	private CellStyle itemTopCellStlye;

	/** Item Cell Style. */
	private CellStyle itemCellStyle;

	/** Total Cell Style. */
	private CellStyle totalCellStyle;

	/** Total Qty Cell Style. */
	private CellStyle totalQtyCellStyle;

	/** Total Amount Cell Style. */
	private CellStyle totalAmountCellStyle;

	/** Total First Cell Style. */
	private CellStyle totalFirstCellStyle;

	/** Total Last Cell Style. */
	private CellStyle totalLastCellStyle;

	/** Qty First Cell Style. */
	private CellStyle qtyFirstCellStyle;

	/** Qty Cell Style. */
	private CellStyle qtyCellStyle;

	/** Amount Last Cell Style. */
	private CellStyle amountLastCellStyle;

	/** Amount Cell Style. */
	private CellStyle amountCellStyle;

	/** Qty And Amount. */
	/** Qty Top Cell Style. */
	private CellStyle qtyTopCellStyle;

	/** Qty Mid Cell Style. */
	private CellStyle qtyMidCellStyle;

	/** Qty Top First Cell Style. */
	private CellStyle qtyTopFirstCellStyle;

	/** Qty Mid First Cell Style. */
	private CellStyle qtyMidFirstCellStyle;

	/** Amount Top Cell Style. */
	private CellStyle amountTopCellStyle;

	/** Amount Mid Cell Style. */
	private CellStyle amountMidCellStyle;

	/** Amount Top Last Cell Style. */
	private CellStyle amountTopLastCellStyle;

	/** Amount Mid Last Cell Style. */
	private CellStyle amountMidLastCellStyle;

	/** Monthly Cost(MRC). */
	/** Cost Type MRC Cell Style. */
	private CellStyle costTypeMRCCellStyle;

	/** Category Top MRC Cell Style. */
	private CellStyle categoryTopMRCCellStyle;

	/** Category MRC Cell Style. */
	private CellStyle categoryMRCCellStyle;

	/** Item Top MRC Cell Style. */
	private CellStyle itemTopMRCCellStlye;

	/** Item MRC Cell Style. */
	private CellStyle itemMRCCellStyle;

	/** Total MRC Cell Style. */
	private CellStyle totalMRCCellStyle;

	/** Total Qty MRC Cell Style. */
	private CellStyle totalQtyMRCCellStyle;

	/** Total Amount MRC Cell Style. */
	private CellStyle totalAmountMRCCellStyle;

	/** Total First MRC Cell Style. */
	private CellStyle totalFirstMRCCellStyle;

	/** Total Last MRC Cell Style. */
	private CellStyle totalLastMRCCellStyle;

	/** One Time Cost(OTC). */
	/** Cost Type OTC Cell Style. */
	private CellStyle costTypeOTCCellStyle;

	/** Category Top OTC Cell Style. */
	private CellStyle categoryTopOTCCellStyle;

	/** Category OTC Cell Style. */
	private CellStyle categoryOTCCellStyle;

	/** Item OTC Top Cell Style. */
	private CellStyle itemTopOTCCellStlye;

	/** Item OTC Cell Style. */
	private CellStyle itemOTCCellStyle;

	/** Total OTC Cell Style. */
	private CellStyle totalOTCCellStyle;

	/** Total Qty OTC Cell Style. */
	private CellStyle totalQtyOTCCellStyle;

	/** Total Amount OTC Cell Style. */
	private CellStyle totalAmountOTCCellStyle;

	/** Total First OTC Cell Style. */
	private CellStyle totalFirstOTCCellStyle;

	/** Total Last OTC Cell Style. */
	private CellStyle totalLastOTCCellStyle;

	/** Monthly Cost(MRC). */
	boolean isMonthlyCost = false;

	/** One Time Cost(OTC). */
	boolean isOneTimeCost = false;

	/** Top Row. */
	boolean isTopRow = false;

	/** Mid Row */
	boolean isMidRow = false;

	/**
	 * @return the companyCellStyle
	 */
	public CellStyle getCompanyCellStyle() {
		return companyCellStyle;
	}

	/**
	 * @param companyCellStyle
	 *            the companyCellStyle to set
	 */
	public void setCompanyCellStyle(CellStyle companyCellStyle) {
		this.companyCellStyle = companyCellStyle;
	}

	/**
	 * @return the costTypeCellStyle
	 */
	public CellStyle getCostTypeCellStyle() {
		return costTypeCellStyle;
	}

	/**
	 * @param costTypeCellStyle
	 *            the costTypeCellStyle to set
	 */
	public void setCostTypeCellStyle(CellStyle costTypeCellStyle) {
		this.costTypeCellStyle = costTypeCellStyle;
	}

	/**
	 * @return the categoryTopCellStyle
	 */
	public CellStyle getCategoryTopCellStyle() {
		return categoryTopCellStyle;
	}

	/**
	 * @param categoryTopCellStyle
	 *            the categoryTopCellStyle to set
	 */
	public void setCategoryTopCellStyle(CellStyle categoryTopCellStyle) {
		this.categoryTopCellStyle = categoryTopCellStyle;
	}

	/**
	 * @return the categoryCellStyle
	 */
	public CellStyle getCategoryCellStyle() {
		return categoryCellStyle;
	}

	/**
	 * @param categoryCellStyle
	 *            the categoryCellStyle to set
	 */
	public void setCategoryCellStyle(CellStyle categoryCellStyle) {
		this.categoryCellStyle = categoryCellStyle;
	}

	/**
	 * @return the itemTopCellStlye
	 */
	public CellStyle getItemTopCellStlye() {
		return itemTopCellStlye;
	}

	/**
	 * @param itemTopCellStlye
	 *            the itemTopCellStlye to set
	 */
	public void setItemTopCellStlye(CellStyle itemTopCellStlye) {
		this.itemTopCellStlye = itemTopCellStlye;
	}

	/**
	 * @return the itemCellStyle
	 */
	public CellStyle getItemCellStyle() {
		return itemCellStyle;
	}

	/**
	 * @param itemCellStyle
	 *            the itemCellStyle to set
	 */
	public void setItemCellStyle(CellStyle itemCellStyle) {
		this.itemCellStyle = itemCellStyle;
	}

	/**
	 * @return the totalCellStyle
	 */
	public CellStyle getTotalCellStyle() {
		return totalCellStyle;
	}

	/**
	 * @param totalCellStyle
	 *            the totalCellStyle to set
	 */
	public void setTotalCellStyle(CellStyle totalCellStyle) {
		this.totalCellStyle = totalCellStyle;
	}

	/**
	 * @return the totalQtyCellStyle
	 */
	public CellStyle getTotalQtyCellStyle() {
		return totalQtyCellStyle;
	}

	/**
	 * @param totalQtyCellStyle
	 *            the totalQtyCellStyle to set
	 */
	public void setTotalQtyCellStyle(CellStyle totalQtyCellStyle) {
		this.totalQtyCellStyle = totalQtyCellStyle;
	}

	/**
	 * @return the totalAmountCellStyle
	 */
	public CellStyle getTotalAmountCellStyle() {
		return totalAmountCellStyle;
	}

	/**
	 * @param totalAmountCellStyle
	 *            the totalAmountCellStyle to set
	 */
	public void setTotalAmountCellStyle(CellStyle totalAmountCellStyle) {
		this.totalAmountCellStyle = totalAmountCellStyle;
	}

	/**
	 * @return the totalFirstCellStyle
	 */
	public CellStyle getTotalFirstCellStyle() {
		return totalFirstCellStyle;
	}

	/**
	 * @param totalFirstCellStyle
	 *            the totalFirstCellStyle to set
	 */
	public void setTotalFirstCellStyle(CellStyle totalFirstCellStyle) {
		this.totalFirstCellStyle = totalFirstCellStyle;
	}

	/**
	 * @return the totalLastCellStyle
	 */
	public CellStyle getTotalLastCellStyle() {
		return totalLastCellStyle;
	}

	/**
	 * @param totalLastCellStyle
	 *            the totalLastCellStyle to set
	 */
	public void setTotalLastCellStyle(CellStyle totalLastCellStyle) {
		this.totalLastCellStyle = totalLastCellStyle;
	}

	/**
	 * @return the qtyFirstCellStyle
	 */
	public CellStyle getQtyFirstCellStyle() {
		return qtyFirstCellStyle;
	}

	/**
	 * @param qtyFirstCellStyle
	 *            the qtyFirstCellStyle to set
	 */
	public void setQtyFirstCellStyle(CellStyle qtyFirstCellStyle) {
		this.qtyFirstCellStyle = qtyFirstCellStyle;
	}

	/**
	 * @return the qtyCellStyle
	 */
	public CellStyle getQtyCellStyle() {
		return qtyCellStyle;
	}

	/**
	 * @param qtyCellStyle
	 *            the qtyCellStyle to set
	 */
	public void setQtyCellStyle(CellStyle qtyCellStyle) {
		this.qtyCellStyle = qtyCellStyle;
	}

	/**
	 * @return the amountLastCellStyle
	 */
	public CellStyle getAmountLastCellStyle() {
		return amountLastCellStyle;
	}

	/**
	 * @param amountLastCellStyle
	 *            the amountLastCellStyle to set
	 */
	public void setAmountLastCellStyle(CellStyle amountLastCellStyle) {
		this.amountLastCellStyle = amountLastCellStyle;
	}

	/**
	 * @return the amountCellStyle
	 */
	public CellStyle getAmountCellStyle() {
		return amountCellStyle;
	}

	/**
	 * @param amountCellStyle
	 *            the amountCellStyle to set
	 */
	public void setAmountCellStyle(CellStyle amountCellStyle) {
		this.amountCellStyle = amountCellStyle;
	}

	/**
	 * @return the qtyTopCellStyle
	 */
	public CellStyle getQtyTopCellStyle() {
		return qtyTopCellStyle;
	}

	/**
	 * @param qtyTopCellStyle
	 *            the qtyTopCellStyle to set
	 */
	public void setQtyTopCellStyle(CellStyle qtyTopCellStyle) {
		this.qtyTopCellStyle = qtyTopCellStyle;
	}

	/**
	 * @return the qtyMidCellStyle
	 */
	public CellStyle getQtyMidCellStyle() {
		return qtyMidCellStyle;
	}

	/**
	 * @param qtyMidCellStyle
	 *            the qtyMidCellStyle to set
	 */
	public void setQtyMidCellStyle(CellStyle qtyMidCellStyle) {
		this.qtyMidCellStyle = qtyMidCellStyle;
	}

	/**
	 * @return the qtyTopFirstCellStyle
	 */
	public CellStyle getQtyTopFirstCellStyle() {
		return qtyTopFirstCellStyle;
	}

	/**
	 * @param qtyTopFirstCellStyle
	 *            the qtyTopFirstCellStyle to set
	 */
	public void setQtyTopFirstCellStyle(CellStyle qtyTopFirstCellStyle) {
		this.qtyTopFirstCellStyle = qtyTopFirstCellStyle;
	}

	/**
	 * @return the qtyMidFirstCellStyle
	 */
	public CellStyle getQtyMidFirstCellStyle() {
		return qtyMidFirstCellStyle;
	}

	/**
	 * @param qtyMidFirstCellStyle
	 *            the qtyMidFirstCellStyle to set
	 */
	public void setQtyMidFirstCellStyle(CellStyle qtyMidFirstCellStyle) {
		this.qtyMidFirstCellStyle = qtyMidFirstCellStyle;
	}

	/**
	 * @return the amountTopCellStyle
	 */
	public CellStyle getAmountTopCellStyle() {
		return amountTopCellStyle;
	}

	/**
	 * @param amountTopCellStyle
	 *            the amountTopCellStyle to set
	 */
	public void setAmountTopCellStyle(CellStyle amountTopCellStyle) {
		this.amountTopCellStyle = amountTopCellStyle;
	}

	/**
	 * @return the amountMidCellStyle
	 */
	public CellStyle getAmountMidCellStyle() {
		return amountMidCellStyle;
	}

	/**
	 * @param amountMidCellStyle
	 *            the amountMidCellStyle to set
	 */
	public void setAmountMidCellStyle(CellStyle amountMidCellStyle) {
		this.amountMidCellStyle = amountMidCellStyle;
	}

	/**
	 * @return the amountTopLastCellStyle
	 */
	public CellStyle getAmountTopLastCellStyle() {
		return amountTopLastCellStyle;
	}

	/**
	 * @param amountTopLastCellStyle
	 *            the amountTopLastCellStyle to set
	 */
	public void setAmountTopLastCellStyle(CellStyle amountTopLastCellStyle) {
		this.amountTopLastCellStyle = amountTopLastCellStyle;
	}

	/**
	 * @return the amountMidLastCellStyle
	 */
	public CellStyle getAmountMidLastCellStyle() {
		return amountMidLastCellStyle;
	}

	/**
	 * @param amountMidLastCellStyle
	 *            the amountMidLastCellStyle to set
	 */
	public void setAmountMidLastCellStyle(CellStyle amountMidLastCellStyle) {
		this.amountMidLastCellStyle = amountMidLastCellStyle;
	}

	/**
	 * @return the costTypeMRCCellStyle
	 */
	public CellStyle getCostTypeMRCCellStyle() {
		return costTypeMRCCellStyle;
	}

	/**
	 * @param costTypeMRCCellStyle
	 *            the costTypeMRCCellStyle to set
	 */
	public void setCostTypeMRCCellStyle(CellStyle costTypeMRCCellStyle) {
		this.costTypeMRCCellStyle = costTypeMRCCellStyle;
	}

	/**
	 * @return the categoryTopMRCCellStyle
	 */
	public CellStyle getCategoryTopMRCCellStyle() {
		return categoryTopMRCCellStyle;
	}

	/**
	 * @param categoryTopMRCCellStyle
	 *            the categoryTopMRCCellStyle to set
	 */
	public void setCategoryTopMRCCellStyle(CellStyle categoryTopMRCCellStyle) {
		this.categoryTopMRCCellStyle = categoryTopMRCCellStyle;
	}

	/**
	 * @return the categoryMRCCellStyle
	 */
	public CellStyle getCategoryMRCCellStyle() {
		return categoryMRCCellStyle;
	}

	/**
	 * @param categoryMRCCellStyle
	 *            the categoryMRCCellStyle to set
	 */
	public void setCategoryMRCCellStyle(CellStyle categoryMRCCellStyle) {
		this.categoryMRCCellStyle = categoryMRCCellStyle;
	}

	/**
	 * @return the itemTopMRCCellStlye
	 */
	public CellStyle getItemTopMRCCellStlye() {
		return itemTopMRCCellStlye;
	}

	/**
	 * @param itemTopMRCCellStlye
	 *            the itemTopMRCCellStlye to set
	 */
	public void setItemTopMRCCellStlye(CellStyle itemTopMRCCellStlye) {
		this.itemTopMRCCellStlye = itemTopMRCCellStlye;
	}

	/**
	 * @return the itemMRCCellStyle
	 */
	public CellStyle getItemMRCCellStyle() {
		return itemMRCCellStyle;
	}

	/**
	 * @param itemMRCCellStyle
	 *            the itemMRCCellStyle to set
	 */
	public void setItemMRCCellStyle(CellStyle itemMRCCellStyle) {
		this.itemMRCCellStyle = itemMRCCellStyle;
	}

	/**
	 * @return the totalMRCCellStyle
	 */
	public CellStyle getTotalMRCCellStyle() {
		return totalMRCCellStyle;
	}

	/**
	 * @param totalMRCCellStyle
	 *            the totalMRCCellStyle to set
	 */
	public void setTotalMRCCellStyle(CellStyle totalMRCCellStyle) {
		this.totalMRCCellStyle = totalMRCCellStyle;
	}

	/**
	 * @return the totalQtyMRCCellStyle
	 */
	public CellStyle getTotalQtyMRCCellStyle() {
		return totalQtyMRCCellStyle;
	}

	/**
	 * @param totalQtyMRCCellStyle
	 *            the totalQtyMRCCellStyle to set
	 */
	public void setTotalQtyMRCCellStyle(CellStyle totalQtyMRCCellStyle) {
		this.totalQtyMRCCellStyle = totalQtyMRCCellStyle;
	}

	/**
	 * @return the totalAmountMRCCellStyle
	 */
	public CellStyle getTotalAmountMRCCellStyle() {
		return totalAmountMRCCellStyle;
	}

	/**
	 * @param totalAmountMRCCellStyle
	 *            the totalAmountMRCCellStyle to set
	 */
	public void setTotalAmountMRCCellStyle(CellStyle totalAmountMRCCellStyle) {
		this.totalAmountMRCCellStyle = totalAmountMRCCellStyle;
	}

	/**
	 * @return the totalFirstMRCCellStyle
	 */
	public CellStyle getTotalFirstMRCCellStyle() {
		return totalFirstMRCCellStyle;
	}

	/**
	 * @param totalFirstMRCCellStyle
	 *            the totalFirstMRCCellStyle to set
	 */
	public void setTotalFirstMRCCellStyle(CellStyle totalFirstMRCCellStyle) {
		this.totalFirstMRCCellStyle = totalFirstMRCCellStyle;
	}

	/**
	 * @return the totalLastMRCCellStyle
	 */
	public CellStyle getTotalLastMRCCellStyle() {
		return totalLastMRCCellStyle;
	}

	/**
	 * @param totalLastMRCCellStyle
	 *            the totalLastMRCCellStyle to set
	 */
	public void setTotalLastMRCCellStyle(CellStyle totalLastMRCCellStyle) {
		this.totalLastMRCCellStyle = totalLastMRCCellStyle;
	}

	/**
	 * @return the costTypeOTCCellStyle
	 */
	public CellStyle getCostTypeOTCCellStyle() {
		return costTypeOTCCellStyle;
	}

	/**
	 * @param costTypeOTCCellStyle
	 *            the costTypeOTCCellStyle to set
	 */
	public void setCostTypeOTCCellStyle(CellStyle costTypeOTCCellStyle) {
		this.costTypeOTCCellStyle = costTypeOTCCellStyle;
	}

	/**
	 * @return the categoryTopOTCCellStyle
	 */
	public CellStyle getCategoryTopOTCCellStyle() {
		return categoryTopOTCCellStyle;
	}

	/**
	 * @param categoryTopOTCCellStyle
	 *            the categoryTopOTCCellStyle to set
	 */
	public void setCategoryTopOTCCellStyle(CellStyle categoryTopOTCCellStyle) {
		this.categoryTopOTCCellStyle = categoryTopOTCCellStyle;
	}

	/**
	 * @return the categoryOTCCellStyle
	 */
	public CellStyle getCategoryOTCCellStyle() {
		return categoryOTCCellStyle;
	}

	/**
	 * @param categoryOTCCellStyle
	 *            the categoryOTCCellStyle to set
	 */
	public void setCategoryOTCCellStyle(CellStyle categoryOTCCellStyle) {
		this.categoryOTCCellStyle = categoryOTCCellStyle;
	}

	/**
	 * @return the itemTopOTCCellStlye
	 */
	public CellStyle getItemTopOTCCellStlye() {
		return itemTopOTCCellStlye;
	}

	/**
	 * @param itemTopOTCCellStlye
	 *            the itemTopOTCCellStlye to set
	 */
	public void setItemTopOTCCellStlye(CellStyle itemTopOTCCellStlye) {
		this.itemTopOTCCellStlye = itemTopOTCCellStlye;
	}

	/**
	 * @return the itemOTCCellStyle
	 */
	public CellStyle getItemOTCCellStyle() {
		return itemOTCCellStyle;
	}

	/**
	 * @param itemOTCCellStyle
	 *            the itemOTCCellStyle to set
	 */
	public void setItemOTCCellStyle(CellStyle itemOTCCellStyle) {
		this.itemOTCCellStyle = itemOTCCellStyle;
	}

	/**
	 * @return the totalOTCCellStyle
	 */
	public CellStyle getTotalOTCCellStyle() {
		return totalOTCCellStyle;
	}

	/**
	 * @param totalOTCCellStyle
	 *            the totalOTCCellStyle to set
	 */
	public void setTotalOTCCellStyle(CellStyle totalOTCCellStyle) {
		this.totalOTCCellStyle = totalOTCCellStyle;
	}

	/**
	 * @return the totalQtyOTCCellStyle
	 */
	public CellStyle getTotalQtyOTCCellStyle() {
		return totalQtyOTCCellStyle;
	}

	/**
	 * @param totalQtyOTCCellStyle
	 *            the totalQtyOTCCellStyle to set
	 */
	public void setTotalQtyOTCCellStyle(CellStyle totalQtyOTCCellStyle) {
		this.totalQtyOTCCellStyle = totalQtyOTCCellStyle;
	}

	/**
	 * @return the totalAmountOTCCellStyle
	 */
	public CellStyle getTotalAmountOTCCellStyle() {
		return totalAmountOTCCellStyle;
	}

	/**
	 * @param totalAmountOTCCellStyle
	 *            the totalAmountOTCCellStyle to set
	 */
	public void setTotalAmountOTCCellStyle(CellStyle totalAmountOTCCellStyle) {
		this.totalAmountOTCCellStyle = totalAmountOTCCellStyle;
	}

	/**
	 * @return the totalFirstOTCCellStyle
	 */
	public CellStyle getTotalFirstOTCCellStyle() {
		return totalFirstOTCCellStyle;
	}

	/**
	 * @param totalFirstOTCCellStyle
	 *            the totalFirstOTCCellStyle to set
	 */
	public void setTotalFirstOTCCellStyle(CellStyle totalFirstOTCCellStyle) {
		this.totalFirstOTCCellStyle = totalFirstOTCCellStyle;
	}

	/**
	 * @return the totalLastOTCCellStyle
	 */
	public CellStyle getTotalLastOTCCellStyle() {
		return totalLastOTCCellStyle;
	}

	/**
	 * @param totalLastOTCCellStyle
	 *            the totalLastOTCCellStyle to set
	 */
	public void setTotalLastOTCCellStyle(CellStyle totalLastOTCCellStyle) {
		this.totalLastOTCCellStyle = totalLastOTCCellStyle;
	}

	/**
	 * Set Cell Style To Common Cell Style
	 * 
	 * @param costType
	 *            Cost Type
	 */
	public void setCellStyleByCostType(String costType) {
		// One Time Cost
		if (costType != null && costType.equals(Constant.ONE_TIME_COST)) {
			if (!isOneTimeCost) {
				// Cost Type Cell Style.
				costTypeCellStyle = costTypeOTCCellStyle;

				// Category Top Cell Style.
				categoryTopCellStyle = categoryTopOTCCellStyle;

				// Category Cell Style.
				categoryCellStyle = categoryOTCCellStyle;

				// Item Top Cell Style.
				itemTopCellStlye = itemTopOTCCellStlye;

				// Item Cell Style.
				itemCellStyle = itemOTCCellStyle;

				// Total Cell Style.
				totalCellStyle = totalOTCCellStyle;

				// Total Qty Cell Style.
				totalQtyCellStyle = totalQtyOTCCellStyle;

				// Total Amount Cell Style.
				totalAmountCellStyle = totalAmountOTCCellStyle;

				// Total First Cell Style.
				totalFirstCellStyle = totalFirstOTCCellStyle;

				// Total Last Cell Style.
				totalLastCellStyle = totalLastOTCCellStyle;

				isOneTimeCost = true;
				isMonthlyCost = false;
			}
		} else {
			if (!isMonthlyCost) {
				// Cost Type Cell Style.
				costTypeCellStyle = costTypeMRCCellStyle;

				// Category Top Cell Style.
				categoryTopCellStyle = categoryTopMRCCellStyle;

				// Category Cell Style.
				categoryCellStyle = categoryMRCCellStyle;

				// Item Top Cell Style.
				itemTopCellStlye = itemTopMRCCellStlye;

				// Item Cell Style.
				itemCellStyle = itemMRCCellStyle;

				// Total Cell Style.
				totalCellStyle = totalMRCCellStyle;

				// Total Qty Cell Style.
				totalQtyCellStyle = totalQtyMRCCellStyle;

				// Total Amount Cell Style.
				totalAmountCellStyle = totalAmountMRCCellStyle;

				// Total First Cell Style.
				totalFirstCellStyle = totalFirstMRCCellStyle;

				// Total Last Cell Style.
				totalLastCellStyle = totalLastMRCCellStyle;

				isMonthlyCost = true;
				isOneTimeCost = false;
			}
		}
	}

	/**
	 * Set Cell Style To Common Qty Amount Cell Style
	 * 
	 * @param isTopRow
	 *            Top Row
	 */
	public void setCellStyleQtyAmountByRow(boolean isTopRow) {
		// Top Row
		if (isTopRow) {
			if (!this.isTopRow) {
				// Qty First Cell Style
				qtyFirstCellStyle = qtyTopFirstCellStyle;

				// Qty Cell Style. */
				qtyCellStyle = qtyTopCellStyle;

				// Amount Last Cell Style
				amountLastCellStyle = amountTopLastCellStyle;

				// Amount Cell Style
				amountCellStyle = amountTopCellStyle;

				this.isTopRow = true;
				this.isMidRow = false;
			}
		} else {
			if (!this.isMidRow) {
				// Qty First Cell Style
				qtyFirstCellStyle = qtyMidFirstCellStyle;

				// Qty Cell Style. */
				qtyCellStyle = qtyMidCellStyle;

				// Amount Last Cell Style
				amountLastCellStyle = amountMidLastCellStyle;

				// Amount Cell Style
				amountCellStyle = amountMidCellStyle;

				this.isMidRow = true;
				this.isTopRow = false;
			}
		}
	}
}
