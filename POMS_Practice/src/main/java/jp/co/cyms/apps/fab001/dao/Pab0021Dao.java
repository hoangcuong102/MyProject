package jp.co.cyms.apps.fab001.dao;

import java.util.List;

import jp.co.cyms.apps.fab001.bean.Pab0021Bean;
import jp.co.cyms.base.BaseDao;
/**
 * Item Master 2 Pab0021Dao
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0021Dao extends BaseDao{
    /**
     *
     */
   public Pab0021Dao() {
      // super();
   }
   
   /**
    * Get list category code
    * 
    * @return categoryCDList
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public List<String> getListCategoryCd() throws Exception {
       List<String> list = null;
       list = (List<String>) this.queryList("FAB0021.select01");
       return list;
   }
   /**
    * Get list category name
    * 
    * @param pab0021Bean
    * @return categoryName
    * @throws Exception
    */
   public String getCategoryName(Pab0021Bean pab0021Bean) throws Exception {
       String categoryName = null;
       categoryName = (String) this.queryObject("FAB0021.select03", pab0021Bean);
       return categoryName;
   }
   
   /**
    * Get list item code
    * 
    * @param pab0021Bean
    * @return itemCdList
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public List<String> getListItemCd(Pab0021Bean pab0021Bean) throws Exception {
       List<String> list = null;
       list = (List<String>) this.queryList("FAB0021.select02", pab0021Bean);
       return list;
   }
   
   /**
    * Get list item name
    * 
    * @param pab0021Bean
    * @return itemName
    * @throws Exception
    */
   public String getItemName(Pab0021Bean pab0021Bean) throws Exception {
       String itemName = null;
       itemName = (String) this.queryObject("FAB0021.select04", pab0021Bean);
       return itemName;
   }
   
   /**
    * Get item_dtl_mst
    * 
    * @param pab0021Bean
    * @return pab0021Bean
    * @throws Exception
    */
   public Pab0021Bean getItemDtlMst(Pab0021Bean pab0021Bean) throws Exception {
       Pab0021Bean item = null;
       item = (Pab0021Bean) this.queryObject("FAB0021.select05", pab0021Bean);
       return item;
   }
   
   /**
    * insert item_dtl_mst
    * 
    * @param pab0021Bean
    * @throws Exception
    */
   public void insertItemDtlMst(Pab0021Bean pab0021Bean) throws Exception {
       this.delete("FAB0021.insertItemDtl", pab0021Bean);
   }
   
   /**
    * update item_dtl_mst
    * 
    * @param pab0021Bean
    * @throws Exception
    */
   public void updateItemDtlMst(Pab0021Bean pab0021Bean) throws Exception {
       this.delete("FAB0021.updateItemDtl", pab0021Bean);
   }

   /**
    * delete item__mst
    * 
    * @param pab0021Bean
    * @throws Exception
    */
   public void deleteItemMst(Pab0021Bean pab0021Bean) throws Exception {
       this.delete("FAB0021.deleteItemMst", pab0021Bean);
   }
}
