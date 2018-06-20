/**
 *
 */
package jp.co.cyms.base.export;

/**
 * @author hungtd
 *
 */
public class CertificateParamBL {

	/**
	 * Get list union 4 master
	 *
	 * @param parameterId
	 * @param cd2pK
	 *
	 * @return the list union 4 master
	 */
	public CertificateParam doGetCertificate(String cd1pK, String cd2Pk, String systemId) {
		/** Initial Pam0041Dao */
//		Pam0041Dao pam0041Dao = new Pam0041Dao();
//
//		CertificateParam certParam = new CertificateParam();
//		Pam0041Bean param = new Pam0041Bean();
//		param.setCategory(Constant.CATEGORY_CNF);
//		param.setSystemId(systemId);
//		param.setCd1Pk(cd1pK);
//		param.setCd2Pk(cd2Pk);
//		param.setClassUnion4(Constant.CLASS_98);
//		Pam0041Bean bean = pam0041Dao.getUnion4(param);
//		if (bean != null) {
//			certParam.setTitle(bean.getName());
//			certParam.setParameterName(bean.getColumn1());
//			certParam.setPath(bean.getColumn2());
//			certParam.setFileName(bean.getColumn3());
//			certParam.setPassword(bean.getColumn4());
//			return certParam;
//		}
		return null;
	}
}
