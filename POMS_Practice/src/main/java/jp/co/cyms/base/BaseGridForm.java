/*
 * CLASS   : BaseGridForm
 * PACKAGE : jp.co.cyms.base
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.base;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.SystemCommon;

/**
 * BaseGridForm
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
@SuppressWarnings("serial")
public abstract class BaseGridForm extends BaseAction {

	/**
	 * gridData
	 */
	protected String gridData = Constant.EMPTY_GRID_DATA;
	/**
	 * totalPager
	 */
	protected int totalPager = 0;
	/**
	 * currPager
	 */
	protected int currPager = 0;
	/**
	 * nextPager
	 */
	protected int nextPager = 0;
	/**
	 * totalRows
	 */
	protected int totalRows = 0;
	/**
	 * rowPerPage
	 */
	protected int rowPerPage = SystemCommon.rowPerPage;

	/**
	 * @return the gridData
	 */
	public String getGridData() {
		return gridData;
	}

	/**
	 * @param gridData the gridData to set
	 */
	public void setGridData(String gridData) {
		this.gridData = gridData;
	}

	/**
	 * @return the totalPager
	 */
	public int getTotalPager() {
		return totalPager;
	}

	/**
	 * @param totalPager the totalPager to set
	 */
	public void setTotalPager(int totalPager) {
		this.totalPager = totalPager;
	}

	/**
	 * @return the currPager
	 */
	public int getCurrPager() {
		return currPager;
	}

	/**
	 * @param currPager the currPager to set
	 */
	public void setCurrPager(int currPager) {
		this.currPager = currPager;
	}

	/**
	 * @return the nextPager
	 */
	public int getNextPager() {
		return nextPager;
	}

	/**
	 * @param nextPager the nextPager to set
	 */
	public void setNextPager(int nextPager) {
		this.nextPager = nextPager;
	}

	/**
	 * @return the rowPerPage
	 */
	public int getRowPerPage() {
		return rowPerPage;
	}

	/**
	 * @param rowPerPage the rowPerPage to set
	 */
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
}
