package jp.co.cyms.apps.fab001.dao;

import java.util.List;

import jp.co.cyms.apps.fab001.bean.Pab0031Bean;
import jp.co.cyms.base.BaseDao;
import jp.co.cyms.common.bean.CategoryBean;
import jp.co.cyms.common.bean.CountryBean;

/**
 * Active Item Master Pab0031Dao
 * 
 * @author binhvh
 * @since 2018/01/03
 */
public class Pab0031Dao extends BaseDao{
    /**
     *
     */
   public Pab0031Dao() {
       super();
   }
   
   /**
    * get all country form COUNTRY
    * 
    * @return List<CountryBean>
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public List<CountryBean> getListCountry() throws Exception {
	   List<CountryBean> list = null;
	   list = (List<CountryBean>) this.queryList("FAB0031.getListCountry");
	   return list;
   }
   
   /**
    * get all category form CATEGORY
    * 
    * @return
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public List<CategoryBean> getListCategory() throws Exception {
	   List<CategoryBean> list = null;
	   list = (List<CategoryBean>) this.queryList("FAB0031.getListCategory");
	   return list;
   }
   
   /**
    * get all item
    * 
    * @param pab0031Bean
    * @return
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public List<Pab0031Bean> getAllItem(Pab0031Bean pab0031Bean) throws Exception {
	   List<Pab0031Bean> list = null;
	   list = (List<Pab0031Bean>) this.queryList("FAB0031.getAllItem", pab0031Bean);
	   return list;
   }
   
   /**
    * get active item
    * 
    * @param pab0031Bean
    * @return
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public List<Pab0031Bean> getActiveItem(Pab0031Bean pab0031Bean) throws Exception {
	   List<Pab0031Bean> list = null;
	   list = (List<Pab0031Bean>) this.queryList("FAB0031.getActiveItem", pab0031Bean);
	   return list;
   }
   
   /**
    * get inactive item
    * 
    * @param pab0031Bean
    * @return
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public List<Pab0031Bean> getInactiveItem(Pab0031Bean pab0031Bean) throws Exception {
	   List<Pab0031Bean> list = null;
	   list = (List<Pab0031Bean>) this.queryList("FAB0031.getInactiveItem", pab0031Bean);
	   return list;
   }
   
   /**
    * insert item to ACTIVE_MST
    * 
    * @param pab0031Bean
    * @throws Exception
    */
   public void insertItem(Pab0031Bean pab0031Bean) throws Exception {
	   this.delete("FAB0031.insertItem", pab0031Bean);
   }
   
   /**
    * update item in ACTIVE_MST
    * 
    * @param pab0031Bean
    * @throws Exception
    */
   public void updateItem(Pab0031Bean pab0031Bean) throws Exception {
	   this.delete("FAB0031.updateItem", pab0031Bean);
   }
   
   /**
    * get item in ACTIVE_MST
    * 
    * @param pab0031Bean
    * @return
    * @throws Exception
    */
   public Pab0031Bean getItem(Pab0031Bean pab0031Bean) throws Exception {
	   Pab0031Bean item = new Pab0031Bean();
	   item = (Pab0031Bean)this.queryObject("FAB0031.getItem", pab0031Bean);
	   return item;
   }
   
   /**
    * delete item in ACTIVE_MST
    * 
    * @param pab0031Bean
    * @throws Exception
    */
   public void deleteItem(Pab0031Bean pab0031Bean) throws Exception {
	   this.delete("FAB0031.deleteItem", pab0031Bean);
   }
}
