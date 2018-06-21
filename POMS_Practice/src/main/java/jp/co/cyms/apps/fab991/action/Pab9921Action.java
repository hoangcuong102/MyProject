package jp.co.cyms.apps.fab991.action;

import java.util.List;

import jp.co.cyms.apps.fab991.bl.Pab9921BL;
import jp.co.cyms.apps.fab991.form.Pab9921Form;

public class Pab9921Action extends Pab9921Form {

	/**
	 * 
	 */
	private List<String> category_cd;
	
	private static final long serialVersionUID = -544124277598148864L;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		LOG.info("*************execute Start SAB9921*************", "");
		Pab9921BL logic =  new Pab9921BL();
		setCategory_cd(logic.getListCategory_CD());
		LOG.info("*************execute End SAB9921*************", "");
		return SUCCESS;
	}

	public List<String> getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(List<String> category_cd) {
		this.category_cd = category_cd;
	}

}
