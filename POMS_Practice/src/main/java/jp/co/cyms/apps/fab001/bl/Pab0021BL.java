package jp.co.cyms.apps.fab001.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;

import jp.co.cyms.apps.fab001.bean.Pab0021Bean;
import jp.co.cyms.apps.fab001.bean.UploadBean;
import jp.co.cyms.apps.fab001.dao.Pab0021Dao;
import jp.co.cyms.base.BaseLogic;

/**
 * Item Master 2 Pab0021BL
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0021BL extends BaseLogic {
    /**
     * Get list category code
     * 
     * @return categoryCdList
     * @throws Exception
     */
    public List<String> getListCategoryCd() throws Exception {
        Pab0021Dao dao = new Pab0021Dao();
        List<String> list = dao.getListCategoryCd();
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * Get list category name
     * 
     * @param pab0021Bean
     * @return categoryName
     * @throws Exception
     */
    public String getCategoryName(Pab0021Bean pab0021Bean) throws Exception {
        Pab0021Dao dao = new Pab0021Dao();
        return dao.getCategoryName(pab0021Bean);
    }

    /**
     * Get list item code
     * 
     * @param pab0021Bean
     * @return itemCdList
     * @throws Exception
     */
	public List<String> getListItemCd(Pab0021Bean pab0021Bean) throws Exception {
        Pab0021Dao dao = new Pab0021Dao();
        List<String> list = dao.getListItemCd(pab0021Bean);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * Get list item name
     * 
     * @param pab0021Bean
     * @return itemName
     * @throws Exception
     */
    public String getItemName(Pab0021Bean pab0021Bean) throws Exception {
        Pab0021Dao dao = new Pab0021Dao();
        return dao.getItemName(pab0021Bean);
    }

    /**
     * Get item_dtl_mst
     * 
     * @param pab0021Bean
     * @return Pab0021Bean
     * @throws Exception
     */
    public Pab0021Bean getItemDtlMst(Pab0021Bean pab0021Bean) throws Exception {
        Pab0021Dao dao = new Pab0021Dao();
        return dao.getItemDtlMst(pab0021Bean);
    }

    /**
     * insert item_dtl_mst
     * 
     * @param pab0021Bean
     * @throws Exception
     */
    public void insertItemDtlMst(Pab0021Bean pab0021Bean) throws Exception {
        Pab0021Dao dao = new Pab0021Dao();
        dao.insertItemDtlMst(pab0021Bean);
    }

    /**
     * update item_dtl_mst
     * 
     * @param pab0021Bean
     * @throws Exception
     */
    public void updateItemDtlMst(Pab0021Bean pab0021Bean) throws Exception {
        Pab0021Dao dao = new Pab0021Dao();
        dao.updateItemDtlMst(pab0021Bean);
    }

    /**
     * delete item__mst
     * 
     * @param pab0021Bean
     * @throws Exception
     */
    public void deleteItemMst(Pab0021Bean pab0021Bean) throws Exception {
        Pab0021Dao dao = new Pab0021Dao();
        dao.deleteItemMst(pab0021Bean);
    }

    /**
     * Upload file to location localPath
     */
    public UploadBean uploadFile(File file, String fileName, String localPath) throws Exception {
        if (file == null || fileName == null || localPath == null) {
            return null;
        }
        UploadBean result = new UploadBean();
        // check localPath exists
        File checkFile = new File(localPath);
        if (!checkFile.exists()) {
            checkFile.mkdir();
        }

        // upload file
        try {
            String fileSize = String.format("%.2f", ((double) file.length()) / 1024 / 1024) + "MB";
            File localFile = new File(localPath, fileName);
            FileUtils.copyFile(file, localFile);
            result.setLinkToFile(localPath + "\\" + fileName);
            result.setFileName(fileName);
            result.setFileSize(fileSize);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * encode image to base64
     * 
     * @param imagePath
     * @return base64
     * @throws java.io.IOException
     */
    public String encoder(String imagePath, String imageName) throws java.io.IOException {
		if (imagePath == null || imagePath.equals("") || imageName == null || imageName.equals("")) {
            return null;
        }
        String base64Image = "";
        File file = new File(imagePath + "\\" + imageName);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
            return null;
        }
        return base64Image;
    }
}
