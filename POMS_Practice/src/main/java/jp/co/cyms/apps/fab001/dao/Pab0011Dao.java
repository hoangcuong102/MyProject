package jp.co.cyms.apps.fab001.dao;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyms.apps.fab001.bean.Pab0011Bean;
import jp.co.cyms.base.BaseDao;

/**
 * Item Master Pab0011Dao
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0011Dao extends BaseDao {
	public Pab0011Dao() {
		super();
	}

	/**
	 * get All from ITEM_MST
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Pab0011Bean> getListItem() throws Exception {
		List<Pab0011Bean> data = new ArrayList<Pab0011Bean>();
		data = (List<Pab0011Bean>) this.queryList("FAB0011.getAllItem");
		return data;
	}
	
	/**
	 * get item from ITEM_MST
	 * 
	 * @param pab0011Bean
	 * @return
	 * @throws Exception
	 */
	public Pab0011Bean getItem(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Bean bean = null;
		bean = (Pab0011Bean)this.queryObject("FAB0011.getItem", pab0011Bean);
		return bean;
	}
	
	/**
	 * update item in ITEM_MST
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void updateItem(Pab0011Bean pab0011Bean) throws Exception {
		this.delete("FAB0011.updateItem", pab0011Bean);
	}
	
	/**
	 * insert item to ITEM_MST
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void insertItem(Pab0011Bean pab0011Bean) throws Exception {
		this.delete("FAB0011.insertItem", pab0011Bean);
	}
	
	/**
	 * delete item in ITEM_MST
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void deleteItem(Pab0011Bean pab0011Bean) throws Exception {
		this.delete("FAB0011.deleteItem", pab0011Bean);
	}
	
	/**
	 * update record in STOCK
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void updateStock(Pab0011Bean pab0011Bean) throws Exception {
		this.delete("FAB0011.updateStock", pab0011Bean);
	}
	
	/**
	 * insert record in STOCK
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void insertStock(Pab0011Bean pab0011Bean) throws Exception {
		this.delete("FAB0011.insertStock", pab0011Bean);
	}
	
	/**
	 * get record from STOCK
	 * 
	 * @param pab0011Bean
	 * @return
	 * @throws Exception
	 */
	public Pab0011Bean getStock(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Bean bean = null;
		bean = (Pab0011Bean)this.queryObject("FAB0011.getStock", pab0011Bean);
		return bean;
	}
	
	/**
	 * update ACTIVE_FG = '0' when delete item
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void updateActiveMst(Pab0011Bean pab0011Bean) throws Exception {
		this.delete("FAB0011.updateActiveMst", pab0011Bean);
	}
	
	/**
	 * check item exist in active_mst
	 * 
	 * @param pab0011Bean
	 * @return true if item exist
	 * @throws Exception
	 */
	public boolean checkExistItemActiveMst(Pab0011Bean pab0011Bean) throws Exception {
		int count = this.queryCount("FAB0011.checkExistItemActiveMst", pab0011Bean);
		if(count > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * delete item in STOCK
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void deleteStockItem(Pab0011Bean pab0011Bean) throws Exception {
		this.delete("FAB0011.deleteStockItem", pab0011Bean);
	}
}
