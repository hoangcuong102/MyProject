package jp.co.cyms.base.export;


import java.util.Date;

/**
 *************************************************************************
 * ReportBaseProfile
 *@author tichnv
 *************************************************************************
 */
public class ReportBaseProfile
{
	/**
	 *********************************************************************
	 *	�夲ｾ趣ｾ滂ｽｰ�ИD
	 */
	private String theRepoId = null;
	public void setRepoId(String data) { theRepoId = data; }
	public String getRepoId() { return(theRepoId); }

	/**
	 *********************************************************************
	 *	蟶ｳ逾ｨ��ｽｲ��ｾ�
	 */
	private String theTitle = null;
	public void setTitle(String data) { theTitle = data; }
	public String getTitle() { return(theTitle); }

	/**
	 *********************************************************************
	 *	蟶ｳ逾ｨ�ｻ�鯉ｾ橸ｾ�ｽｲ��ｾ�
	 */
	private String theSubTitle = null;
	public void setSubTitle(String data) { theSubTitle = data; }
	public String getSubTitle() { return(theSubTitle); }

	/**
	 *********************************************************************
	 *	�夲ｾ趣ｾ滂ｽｰ��ｼ夲ｾ�ｽｲ�托ｽｽ��ｾ晢ｾ鯉ｾ�
	 */
	private Date theRepoTStamp = null;
	public void setRepoTStamp(Date data) { theRepoTStamp = data; }
	public Date getRepoTStamp() { return(theRepoTStamp); }

	/**
	 *********************************************************************
	 *	�夲ｾ趣ｾ滂ｽｰ��ｼ夲ｾ�ｾ晢ｾ鯉ｾ滂ｾ夲ｽｰ��錐遘ｰ�ｷ�ｰ
	 */
	private String theRepoTemplkey = null;
	public void setRepoTemplkey(String data) { theRepoTemplkey = data; }
	public String getRepoTemplkey() { return(theRepoTemplkey); }

	/**
	 *********************************************************************
	 *	�夲ｾ趣ｾ滂ｽｰ��ｼ壼�蜉幢ｾ鯉ｽｧ�ｲ�吝錐遘ｰ�ｷ�ｰ
	 */
	private String theRepoOutputKey = null;
	public void setRepoOutputKey(String data) { theRepoOutputKey = data; }
	public String getRepoOutputKey() { return(theRepoOutputKey); }

	/**
	 *********************************************************************
	 *	��ｾ晢ｾ鯉ｾ滂ｾ夲ｽｰ��錐
	 */
	private String theRepoTemplName = null;
	public void setRepoTemplName(String data) { theRepoTemplName = data; }
	public String getRepoTemplName() { return(theRepoTemplName); }

	/**
	 *********************************************************************
	 *	��ｾ晢ｾ鯉ｾ滂ｾ夲ｽｰ��ｽｼ�ｰ��錐
	 */
	private String theRepoTemplSheetName = null;
	public void setRepoTemplSheetName(String data) { theRepoTemplSheetName = data; }
	public String getRepoTemplSheetName() { return(theRepoTemplSheetName); }

	/**
	 *********************************************************************
	 *	蜃ｺ蜉幢ｾ鯉ｽｧ�ｲ�吝錐
	 */
	private String theRepoOutputName = null;
	public void setRepoOutputName(String data) { theRepoOutputName = data; }
	public String getRepoOutputName() { return(theRepoOutputName); }

	/**
	 *********************************************************************
	 *	蜃ｺ蜉幢ｾ鯉ｽｧ�ｲ�吝錐(�鯉ｾ呻ｾ奇ｾ滂ｽｽ)
	 */
	private String theRepoOutputNameFullPath = null;
	public void setRepoOutputNameFullPath(String data) { theRepoOutputNameFullPath = data; }
	public String getRepoOutputNameFullPath() { return(theRepoOutputNameFullPath); }

	/**
	 *********************************************************************
	 *	蜊ｰ蟄暦ｼ夊ｦ∵ｱゆｼ夂､ｾ
	 */
	private String thePrtCorpName = null;
	public void setPrtCorpName(String data) { thePrtCorpName = data; }
	public String getPrtCorpName() { return(thePrtCorpName); }

	/**
	 *********************************************************************
	 *	蜊ｰ蟄暦ｼ夊ｦ∵ｱゆｺ区･ｭ驛ｨ蜷�
	 */
	private String thePrtDivName = null;
	public void setPrtDivName(String data) { thePrtDivName = data; }
	public String getPrtDivName() { return(thePrtDivName); }

	/**
	 *********************************************************************
	 *	蜊ｰ蟄暦ｼ夊ｦ∵ｱるΚ鄂ｲ蜷�
	 */
	private String thePrtDptName = null;
	public void setPrtDptName(String data) { thePrtDptName = data; }
	public String getPrtDptName() { return(thePrtDptName); }

	/**
	 *********************************************************************
	 *	蜊ｰ蟄暦ｼ夊ｦ∵ｱよ球蠖楢��ｺ�ｰ��ｾ�
	 */
	private String thePrtPersonCode = null;
	public void setPrtPersonCode(String data) { thePrtPersonCode = data; }
	public String getPrtPersonCode() { return(thePrtPersonCode); }

	/**
	 *********************************************************************
	 *	蜊ｰ蟄暦ｼ夊ｦ∵ｱよ球蠖楢�蜷�
	 */
	private String thePrtPersonName = null;
	public void setPrtPersonName(String data) { thePrtPersonName = data; }
	public String getPrtPersonName() { return(thePrtPersonName); }

	/**
	 *********************************************************************
	 *	DB謗･邯壽枚蟄怜�
	 */
	private String theDBConnectionString = null;
	public void setDBConnectionString(String data) { theDBConnectionString = data; }
	public String getDBConnectionString() { return(theDBConnectionString); }

	/**
	 *********************************************************************
	 *	DB謗･邯夲ｾ鯉ｾ滂ｾ幢ｾ奇ｾ橸ｽｲ��ｾ�
	 */
	private String theDBProviderName = null;
	public void setDBProviderName(String data) { theDBProviderName = data; }
	public String getDBProviderName() { return(theDBProviderName); }

//	/**
//	 *********************************************************************
//	 *	DB�ｺ�茨ｽｸ�ｼ�ｮ��
//	 */
//	private DbConnection theDbConnection = null;
//	public void setDbConnection(DbConnection data) { theDbConnection = data; }
//	public DbConnection getDbConnection() { return(theDbConnection); }
//
//	/**
//	 *********************************************************************
//	 *	DB��ｾ暦ｾ晢ｽｻ�橸ｽｸ�ｼ�ｮ��
//	 */
//	private DbTransaction theDbTransaction = null;
//	public void setDbTransaction(DbTransaction data) { theDbTransaction = data; }
//	public DbTransaction getDbTransaction() { return(theDbTransaction); }
}
