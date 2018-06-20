/*
 * CLASS   : InitSystem
 * PACKAGE : jp.co.cyms.system
 * VERSION : 1.0
 * HISTORY : 2014-08-01�ｽ?SC�ｽ?U CREATE
 */
package jp.co.cyms.system;

import java.util.Properties;

import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.SystemCommon;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.ibatis.SqlMapClientTemplate;



/**
 * InitSystem
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
public class InitSystem implements InitializingBean {

    /**
     * SqlMapClientTemplate
     */
    private SqlMapClientTemplate sqlMapClientTemplate;
    @Override
    public void afterPropertiesSet() throws Exception {

        SystemCommon system = new SystemCommon();
        Properties sysProperties = system.getProperties("/properties/system.properties");

        system.loadLanguage(sysProperties);

        String rowsPerPage = sysProperties.getProperty("grid.rowsperpage");
        if (!StringUtil.isEmpty(rowsPerPage)) {
            SystemCommon.rowPerPage = Integer.parseInt(rowsPerPage);
        }

        String intervalLoadingBay = sysProperties.getProperty("intervalLoadingBay");
        if (!StringUtil.isEmpty(intervalLoadingBay)) {
            SystemCommon.intervalLoadingBay = Integer.parseInt(intervalLoadingBay);
        }
        
        String intervalLoadingBayView = sysProperties.getProperty("intervalLoadingBayView");
        if (!StringUtil.isEmpty(intervalLoadingBayView)) {
            SystemCommon.intervalLoadingBayView = Integer.parseInt(intervalLoadingBayView);
        }
        
        String intervalLoadingBayMovice = sysProperties.getProperty("intervalLoadingBayMovice");
        if (!StringUtil.isEmpty(intervalLoadingBayMovice)) {
            SystemCommon.intervalLoadingBayMovice = Integer.parseInt(intervalLoadingBayMovice);
        }

        String uploadFilePath = sysProperties.getProperty("uploadFilePath");
        if (!StringUtil.isEmpty(uploadFilePath)) {
            SystemCommon.uploadFilePath = uploadFilePath;
        }
        
        String wlpttoutprogressline = sysProperties.getProperty("wlpttoutprogressline");
        if (!StringUtil.isEmpty(wlpttoutprogressline)) {
            SystemCommon.wlpttoutprogressline = wlpttoutprogressline;
        }
        
        String intervalPickingInstruction = sysProperties.getProperty("intervalPickingInstruction");
        if (!StringUtil.isEmpty(intervalPickingInstruction)) {
            SystemCommon.intervalPickingInstruction = Integer.parseInt(intervalPickingInstruction);
        }
    }

  

    /**
	 * @return the sqlMapClientTemplate
	 */
	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}



	/**
	 * @param sqlMapClientTemplate the sqlMapClientTemplate to set
	 */
	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	

	/**
     * InitSystem
     * 
     */
    public InitSystem() {
        super();
    }

}
