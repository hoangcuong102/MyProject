package jp.co.cyms.apps.fab991.action;

import java.util.List;

import jp.co.cyms.apps.fab991.bean.Pab9921Bean;
import jp.co.cyms.apps.fab991.bl.Pab9921BL;
import jp.co.cyms.apps.fab991.form.Pab9921Form;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.bean.ConfigBean;

public class Pab9921Action extends Pab9921Form {

	/**
	 * 
	 */
	private List<String> listCategory_cd;
	private List<String> listItemCd;
	private Pab9921Bean pab9921Bean;
	private String imageBase64;
	
	private static final long serialVersionUID = -544124277598148864L;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		LOG.info("*************execute Start SAB9921*************", "");
		Pab9921BL logic =  new Pab9921BL();
		setListCategory_cd(logic.getListCategory_CD());
		LOG.info("*************execute End SAB9921*************", "");
		return SUCCESS;
	}
	
	/*
	 * Get item_cd allowing category_cd*/
	public String doEvent01() throws Exception{
		LOG.info("*********** execute Start doEvent01 *****************");
		pab9921Bean = new Pab9921Bean();
		Pab9921BL logic =  new Pab9921BL();
		
		pab9921Bean.setCategory_cd(category_cd);
		pab9921Bean.setCategory_name(logic.getCategoryName(pab9921Bean));
		listItemCd = logic.getListItem_CD(pab9921Bean);
		LOG.info("*********** execute End doEvent01 *****************");
		return SUCCESS ;
	}
	
	/*
	 * Get ItemName allowing itemCode
	 * */
	public String doEvent02() throws Exception{
		LOG.info("*********** execute Start doEvent02 *****************");
		Pab9921Bean temp = new Pab9921Bean();
		Pab9921BL logic =  new Pab9921BL();
		ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		temp.setItem_cd(item_cd);
		temp.setCategory_cd(category_cd);
		//pab9921Bean.setItem_name(logic.getItemNameByItemCode(pab9921Bean));
		pab9921Bean = logic.getItemByItemCD_CategoryCD(temp);
		if(pab9921Bean == null) {
			pab9921Bean = new Pab9921Bean();
			
		}
		pab9921Bean.setItem_name(logic.getItemNameByItemCDCateCD(temp));	
		imageBase64 = logic.encodeImageToBase64(config.getImageDir(), pab9921Bean.getImage_name());
			
		
		LOG.info("*********** execute End doEvent02 *****************");
		return SUCCESS;
	}

	public Pab9921Bean getPab9921Bean() {
		return pab9921Bean;
	}

	public void setPab9921Bean(Pab9921Bean pab9921Bean) {
		this.pab9921Bean = pab9921Bean;
	}

	public List<String> getListCategory_cd() {
		return listCategory_cd;
	}

	public void setListCategory_cd(List<String> listCategory_cd) {
		this.listCategory_cd = listCategory_cd;
	}

	public List<String> getListItemCd() {
		return listItemCd;
	}

	public void setListItemCd(List<String> listItemCd) {
		this.listItemCd = listItemCd;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

}
