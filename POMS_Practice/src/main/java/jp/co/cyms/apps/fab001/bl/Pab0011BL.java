package jp.co.cyms.apps.fab001.bl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jp.co.cyms.apps.fab001.bean.Pab0011Bean;
import jp.co.cyms.apps.fab001.dao.Pab0011Dao;

/**
 * Item Master Pab0011BL
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0011BL {

	/**
	 * get all list item
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Pab0011Bean> getListItem() throws Exception {
		return new Pab0011Dao().getListItem();
	}

	/**
	 * get item from ITEM_MST
	 * 
	 * @param pab0011Bean
	 * @return
	 * @throws Exception
	 */
	public Pab0011Bean getItem(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		return dao.getItem(pab0011Bean);
	}
	
	/**
	 * update item in ITEM_MST
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void updateItem(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		dao.updateItem(pab0011Bean);
	}

	/**
	 * insert item to ITEM_MST
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void insertItem(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		dao.insertItem(pab0011Bean);
	}
	
	/**
	 * delete item in ITEM_MST
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void deleteItem(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		dao.deleteItem(pab0011Bean);
	}
	
	/**
	 * get record from STOCK
	 * 
	 * @param pab0011Bean
	 * @return
	 * @throws Exception
	 */
	public Pab0011Bean getStock(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		return dao.getStock(pab0011Bean);
	}
	
	/**
	 * update record in STOCK
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void updateStock(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		dao.updateStock(pab0011Bean);
	}
	
	/**
	 * insert record in STOCK
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void insertStock(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		dao.insertStock(pab0011Bean);
	}
	
	/**
	 * get country from unitPrice(type json)
	 * 
	 * @param unitPrice
	 * @return
	 */
	public List<String> getCountry(String unitPrice) throws Exception {
		List<String> result = new ArrayList<String>();
		JSONObject jsonObject = new JSONObject(unitPrice);
		JSONArray jsonArray = jsonObject.getJSONArray("country");
		for (int i = 0; i < jsonArray.length(); i++) {
			result.add(jsonArray.getString(i));
		}
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	/**
	 * get price from unitPrice(type json)
	 * 
	 * @param unitPrice
	 * @return
	 */
	public List<Double> getPrice(String unitPrice) throws Exception {
		List<Double> result = new ArrayList<Double>();
		JSONObject jsonObject = new JSONObject(unitPrice);
		JSONArray jsonArray = jsonObject.getJSONArray("price");
		for (int i = 0; i < jsonArray.length(); i++) {
			result.add(jsonArray.getDouble(i));
		}
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * check item exist 
	 * 
	 * @param pab0011Bean
	 * @return true if exist
	 * @throws Exception
	 */
	public boolean checkItemExist(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Bean item = getItem(pab0011Bean);
		return item == null ? false : true;
	}
	
	/**
	 * check stock exist 
	 * @param pab0011Bean
	 * @return true if exist
	 * @throws Exception
	 */
	public boolean checkStockExist(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Bean item = getStock(pab0011Bean);
		return item == null ? false : true;
	}
	
	/**
	 * update ACTIVE_FG = '0' when delete item
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void updateActiveMst(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		dao.updateActiveMst(pab0011Bean);
	}
	
	/**
	 * check item exist in active_mst
	 * 
	 * @param pab0011Bean
	 * @return true if item exist
	 * @throws Exception
	 */
	public boolean checkExistItemActiveMst(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		return dao.checkExistItemActiveMst(pab0011Bean);
	}
	
	/**
	 * delete item in STOCK
	 * 
	 * @param pab0011Bean
	 * @throws Exception
	 */
	public void deleteStockItem(Pab0011Bean pab0011Bean) throws Exception {
		Pab0011Dao dao = new Pab0011Dao();
		dao.deleteStockItem(pab0011Bean);
	}
}
