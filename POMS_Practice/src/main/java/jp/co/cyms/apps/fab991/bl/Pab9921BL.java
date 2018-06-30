package jp.co.cyms.apps.fab991.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;

import jp.co.cyms.apps.fab991.bean.Pab9921Bean;
import jp.co.cyms.apps.fab991.dao.Pab9921Dao;
import jp.co.cyms.base.BaseLogic;

public class Pab9921BL extends BaseLogic {

	/*
	 * Get Category_CD
	 */
	public List<String> getListCategory_CD() throws Exception {
		Pab9921Dao dao = new Pab9921Dao();
		List<String> list = dao.getListCategory_CD();
		return list.size() > 0 ? list : null;
	}

	/*
	 * GET CategoryName
	 */
	public String getCategoryName(Pab9921Bean pab9921Bean) throws Exception {
		Pab9921Dao dao = new Pab9921Dao();
		return dao.getCategoryName(pab9921Bean);
	}

	/*
	 * Get List Item_CD
	 */
	public List<String> getListItem_CD(Pab9921Bean pab9921bean) throws Exception {
		Pab9921Dao dao = new Pab9921Dao();
		List<String> list = dao.getListItem_CD(pab9921bean);
		return list.size() > 0 ? list : null;
	}

	// Get full attribute of Item
	public Pab9921Bean getItemByItemCD_CategoryCD(Pab9921Bean pab9921Bean) throws Exception {
		Pab9921Dao dao = new Pab9921Dao();
		return dao.getItemByItemCD_CategoryCD(pab9921Bean);
	}

	// Get item name
	public String getItemNameByItemCDCateCD(Pab9921Bean pab9921Bean) throws Exception {
		Pab9921Dao dao = new Pab9921Dao();
		return dao.getItemNameByItemCDCateCD(pab9921Bean);
	}

	public int deleteItem_MST(Pab9921Bean pab9921Bean) throws Exception {
		Pab9921Dao dao = new Pab9921Dao();
		return dao.deleteItem_MST(pab9921Bean);
	}
	
//	Update
	public int updateItem_Dtl_Mst(Pab9921Bean pab9921Bean) throws Exception{
		Pab9921Dao dao = new Pab9921Dao();
		return dao.updateItem_Dtl_Mst(pab9921Bean);
	}
//	insert
	public int insertItem_Dtl_Mst(Pab9921Bean pab9921Bean) throws Exception{
		Pab9921Dao dao = new Pab9921Dao();
		return dao.insertItem_Dtl_Mst(pab9921Bean);
	}
	
//	Copy file from local 
	public boolean uploadFile(File file, String fileName, String localPath) throws IOException{
		if(file == null || fileName == null || fileName.trim().length()==0 || localPath == null || localPath.trim().length()==0) {
			return false;
		}
		File f = new File(localPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		File fileLocal = new File(localPath, fileName);
		FileUtils.copyFile(file, fileLocal);
		return true;
	}

	// Conert url image to base 64
	public String encodeImageToBase64(String path, String imageName) throws IOException {
		String encodedFile = null;
		if (path == null || path.length() == 0 || imageName == null || imageName.length() == 0) {
			return encodedFile;
		}
		try {
			File f = new File(path + "\\" + imageName);
			FileInputStream fis = new FileInputStream(f);
			byte[] bytes = new byte[(int) f.length()];
			fis.read(bytes);
			encodedFile = Base64.getEncoder().encodeToString(bytes);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
			return null;
		}
		return encodedFile;

	}

}
