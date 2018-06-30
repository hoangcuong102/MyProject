package jp.co.cyms.apps.fab991.dao;

import java.util.List;

import jp.co.cyms.apps.fab991.bean.Pab9921Bean;
import jp.co.cyms.base.BaseDao;

public class Pab9921Dao extends BaseDao {
	
	public Pab9921Dao() {
		// TODO Auto-generated constructor stub
		
	}	
	
//	Get list category_cd
	@SuppressWarnings("unchecked")
	public List<String> getListCategory_CD() throws Exception{
		List<String> list = null;
		list = (List<String>) this.queryList("FAB9921.select01");
		return list;
	}
	
	// Get Category Name
	@SuppressWarnings("unchecked")
	public String getCategoryName(Pab9921Bean pab9921Bean) throws Exception{
		return (String) this.queryObject("FAB9921.select02", pab9921Bean);
	}
	
	// Get list item code
	@SuppressWarnings("unchecked")
	public List<String> getListItem_CD(Pab9921Bean pab9921Bean) throws Exception{
		List<String> list = null;
		list = (List<String>) this.queryList("FAB9921.select03",pab9921Bean);
		return list;
	}
	
	//Get Item following item code and categoryCode
	public Pab9921Bean getItemByItemCD_CategoryCD(Pab9921Bean pab9921Bean) throws Exception{
		return (Pab9921Bean) this.queryObject("FAB9921.select04", pab9921Bean);
	}
	
	//get item name
	public String getItemNameByItemCDCateCD(Pab9921Bean pab9921Bean) throws Exception{
		return (String) this.queryObject("FAB9921.select05", pab9921Bean);
	}
	
	// delete item
	public int deleteItem_MST(Pab9921Bean pab9921Bean) throws Exception{
		return this.delete("FAB9921.deleteItem_mts", pab9921Bean);
	}
	
//	Update
	public int updateItem_Dtl_Mst(Pab9921Bean pab9921Bean) throws Exception{
		return this.update("FAB9921.updateForm", pab9921Bean);
	}
//	iNSERT
	public int insertItem_Dtl_Mst(Pab9921Bean pab9921Bean) throws Exception{
		return this.delete("FAB9921.insert", pab9921Bean);
	}
	
	public static void main(String[] agvs) throws Exception {
		Pab9921Dao dao = new Pab9921Dao();
		System.out.println(dao.getListCategory_CD().size());
	}
}
