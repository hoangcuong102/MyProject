package jp.co.cyms.apps.fab991.dao;

import java.util.List;

import jp.co.cyms.apps.fab991.bean.Pab9931CategoryBean;
import jp.co.cyms.apps.fab991.bean.Pab9931CountryBean;
import jp.co.cyms.apps.fab991.bean.Pab9931ItemBean;
import jp.co.cyms.base.BaseDao;

public class Pab9931Dao extends BaseDao {

	/**
	 * created by DuyNK
	 * 21/06/2018 
	 */
	public Pab9931Dao() {
		super();
	}
	
	/*get All Country*/
    @SuppressWarnings("unchecked")
	public List<Pab9931CountryBean> getAllCountry() throws Exception {
	    List<Pab9931CountryBean> list = null;
	    list = (List<Pab9931CountryBean>) this.queryList("FAB9931.getAllCountry");
	    return list;
    }

    /*get All Category*/
	@SuppressWarnings("unchecked")
	public List<Pab9931CategoryBean> getAllCategory() throws Exception {
	    List<Pab9931CategoryBean> list = null;
	    list = (List<Pab9931CategoryBean>) this.queryList("FAB9931.getAllCategory");
	    return list;
    }
	
	/*get All Item*/
	@SuppressWarnings("unchecked")
	public List<Pab9931ItemBean> getAllItem(Pab9931ItemBean item) throws Exception {
	    List<Pab9931ItemBean> list = null;
	    list = (List<Pab9931ItemBean>) this.queryList("FAB9931.getItemAll",item);
	    return list;
    }
	
	/*get Item Active*/
	@SuppressWarnings("unchecked")
	public List<Pab9931ItemBean> getItemActive(Pab9931ItemBean item) throws Exception {
	    List<Pab9931ItemBean> list = null;
	    list = (List<Pab9931ItemBean>) this.queryList("FAB9931.getItemActive",item);
	    return list;
    }
	
	/*get Item Inactive*/
	@SuppressWarnings("unchecked")
	public List<Pab9931ItemBean> getItemInactive(Pab9931ItemBean item) throws Exception {
	    List<Pab9931ItemBean> list = null;
	    list = (List<Pab9931ItemBean>) this.queryList("FAB9931.getItemInactive",item);
	    return list;
    }
	

	/*get a item*/
	public Pab9931ItemBean getItem(Pab9931ItemBean item) throws Exception {
	    Pab9931ItemBean i = null;
	    i = (Pab9931ItemBean) this.queryObject("FAB9931.getItem",item);
	    return i;
    }
	
	/*delete a item from active_mst*/
	public void deleteItem(Pab9931ItemBean item) throws Exception {	
		this.delete("FAB9931.deleteItem", item);
	}
	
	/*insert a item from active_mst*/
	public void insertItem(Pab9931ItemBean item) {	
		this.delete("FAB9931.insertItem", item);
	}
	
	/*update item (activeFG= 0 or 1 to 'X')*/
	public void updateItemToX(Pab9931ItemBean item) {
		this.update("FAB9931.updateItemToX", item);
	}
	
	/*update item (activeFG='X' to 0 or 1)*/
	public void updateItem(Pab9931ItemBean item) {
		this.update("FAB9931.updateItem", item);
	}
	
}
