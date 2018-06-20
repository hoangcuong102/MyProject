package jp.co.cyms.base.export;

import jp.co.cyms.base.BaseLogic;
import jp.co.cyms.common.bean.ConfigBean;
import jp.co.cyms.common.bl.CommonBL;

/**
 * Business Service Interface for entity SysParam.
 * 
 * @author anhnt2
 * @since 2018/01/18
 */
public class SysParamBL extends BaseLogic {
		
	
	/**
	 * 
	 * @return the template dir and out dir
	 */
	public SysParam getSysParam(){
		CommonBL logic = new CommonBL();
		ConfigBean configBean = logic.getConfig();
		SysParam  sysParam = new SysParam();
		if(configBean != null){
			sysParam.setOuputDir(configBean.getExcelOutFiles());
			sysParam.setTempDir(configBean.getExcelTemp());
			sysParam.setNameFile0(configBean.getExcelNamePad00110());
			sysParam.setNameFile1(configBean.getExcelNamePad00111());
			sysParam.setNameFile2(configBean.getExcelNamePad00112());
			sysParam.setNameFile3(configBean.getExcelNamePad00113());
			sysParam.setNameFileTempSac0021(configBean.getExcelNameSac0021());
			sysParam.setNameFileTempSac0024(configBean.getExcelNameSac0024());
			return sysParam;
		}
		return null;
	}
}
