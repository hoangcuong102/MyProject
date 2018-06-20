package jp.co.cyms.apps.fab001.bl;

import java.util.List;

import jp.co.cyms.apps.fab001.bean.Pab0031Bean;
import jp.co.cyms.apps.fab001.dao.Pab0031Dao;
import jp.co.cyms.base.BaseLogic;
import jp.co.cyms.common.bean.CategoryBean;
import jp.co.cyms.common.bean.CountryBean;

/**
 * Active Item Master Pab0031BL
 * 
 * @author binhvh
 * @since 2018/01/03
 */
public class Pab0031BL extends BaseLogic {

	/**
	 * get all country
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CountryBean> getListCountry() throws Exception {
		Pab0031Dao dao = new Pab0031Dao();
		List<CountryBean> list = null;
		list = dao.getListCountry();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * get all category
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> getListCategory() throws Exception {
		Pab0031Dao dao = new Pab0031Dao();
		List<CategoryBean> list = null;
		list = dao.getListCategory();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * get all item
	 * 
	 * @param pab0031Bean
	 * @return
	 * @throws Exception
	 */
	public List<Pab0031Bean> getAllItem(Pab0031Bean pab0031Bean) throws Exception {
		Pab0031Dao dao = new Pab0031Dao();
		List<Pab0031Bean> list = null;
		list = dao.getAllItem(pab0031Bean);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * get active item from ACTIVE_MST
	 * 
	 * @param pab0031Bean
	 * @return
	 * @throws Exception
	 */
	public List<Pab0031Bean> getActiveItem(Pab0031Bean pab0031Bean) throws Exception {
		Pab0031Dao dao = new Pab0031Dao();
		List<Pab0031Bean> list = null;
		list = dao.getActiveItem(pab0031Bean);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * get active item from ACTIVE_MST
	 * 
	 * @param pab0031Bean
	 * @return
	 * @throws Exception
	 */
	public List<Pab0031Bean> getInactiveItem(Pab0031Bean pab0031Bean) throws Exception{
		Pab0031Dao dao = new Pab0031Dao();
		List<Pab0031Bean> list = null;
		list = dao.getInactiveItem(pab0031Bean);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * insert item to ACTIVE_MST
	 * 
	 * @param pab0031Bean
	 * @throws Exception
	 */
	public void insertItem(Pab0031Bean pab0031Bean) throws Exception {
		Pab0031Dao dao = new Pab0031Dao();
		dao.insertItem(pab0031Bean);
	}

	/**
	 * update item in ACTIVE_MST
	 * 
	 * @param pab0031Bean
	 * @throws Exception
	 */
	public void updateItem(Pab0031Bean pab0031Bean) throws Exception {
		Pab0031Dao dao = new Pab0031Dao();
		dao.updateItem(pab0031Bean);
	}

	/**
	 * get item in ACTIVE_MST
	 * 
	 * @param pab0031Bean
	 * @return
	 * @throws Exception
	 */
	public Pab0031Bean getItem(Pab0031Bean pab0031Bean) throws Exception {
		Pab0031Dao dao = new Pab0031Dao();
		return dao.getItem(pab0031Bean);
	}
	
	/**
	    * delete item in ACTIVE_MST
	    * 
	    * @param pab0031Bean
	    * @throws Exception
	    */
	   public void deleteItem(Pab0031Bean pab0031Bean) throws Exception {
		   Pab0031Dao dao = new Pab0031Dao();
		   dao.deleteItem(pab0031Bean);
	   }
	
	/**
	 * check item exist in ACTIVE_MST
	 * @param pab0031Bean
	 * @return true if exist
	 * @throws Exception
	 */
	public boolean checkItemExist(Pab0031Bean pab0031Bean) throws Exception {
		Pab0031Bean item = getItem(pab0031Bean);
		return item == null ? false : true;
	}

}
