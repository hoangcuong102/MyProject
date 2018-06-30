package jp.co.cyms.apps.fab991.bl;

import java.util.List;

import jp.co.cyms.apps.fab991.bean.Pab9931CategoryBean;
import jp.co.cyms.apps.fab991.bean.Pab9931CountryBean;
import jp.co.cyms.apps.fab991.bean.Pab9931ItemBean;
import jp.co.cyms.apps.fab991.dao.Pab9931Dao;
import jp.co.cyms.base.BaseLogic;

public class Pab9931BL extends BaseLogic{

	//get all country
	public List<Pab9931CountryBean> getAllCountry() throws Exception{
		List<Pab9931CountryBean> list = null;
		Pab9931Dao dao = new Pab9931Dao();
		list = dao.getAllCountry();
		return (list.size() > 0) ? list : null;
	}
	
	//get all category
	public List<Pab9931CategoryBean> getAllCategory() throws Exception{
		List<Pab9931CategoryBean> list = null;
		Pab9931Dao dao = new Pab9931Dao();
		list = dao.getAllCategory();
		return (list.size() > 0) ? list : null;
	}
	
	//get all item
	public List<Pab9931ItemBean> getAllItem(Pab9931ItemBean item) throws Exception{
		List<Pab9931ItemBean> list = null;
		Pab9931Dao dao = new Pab9931Dao();
		list = dao.getAllItem(item);
		return (list.size() > 0) ? list : null;
	}
	
	//get item active
	public List<Pab9931ItemBean> getItemActive(Pab9931ItemBean item) throws Exception{
		List<Pab9931ItemBean> list = null;
		Pab9931Dao dao = new Pab9931Dao();
		list = dao.getItemActive(item);
		return (list.size() > 0) ? list : null;
	}
	
	//get item inactive
	public List<Pab9931ItemBean> getItemInactive(Pab9931ItemBean item) throws Exception{
		List<Pab9931ItemBean> list = null;
		Pab9931Dao dao = new Pab9931Dao();
		list = dao.getItemInactive(item);
		return (list.size() > 0) ? list : null;
	}
	
	//check item existed
	public boolean isExist(Pab9931ItemBean item) throws Exception{
		Pab9931Dao dao = new Pab9931Dao();
		Pab9931ItemBean i = dao.getItem(item);
		return (i != null) ? true : false;
	}
	
	//delete item
	public void deleteItem(Pab9931ItemBean item) throws Exception{
		Pab9931Dao dao = new Pab9931Dao();
		dao.deleteItem(item);
	}
	
	//insert item
	public void insertItem(Pab9931ItemBean item) throws Exception{
		Pab9931Dao dao = new Pab9931Dao();
		dao.insertItem(item);
	}
	
	//update item to X
	public void updateItemToX(Pab9931ItemBean item) {
		Pab9931Dao dao = new Pab9931Dao();
		dao.updateItemToX(item);
	}
	
	//update item 
	public void updateItem(Pab9931ItemBean item) {
		Pab9931Dao dao = new Pab9931Dao();
		dao.updateItem(item);
	}
}
