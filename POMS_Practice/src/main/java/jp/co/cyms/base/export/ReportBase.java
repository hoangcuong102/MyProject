package jp.co.cyms.base.export;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *************************************************************************
 * �夲ｾ趣ｾ滂ｽｰ���逅�渕蠎輔��ｸ�暦ｽｽ
 *
 * 繝ｻ�夲ｾ趣ｾ滂ｽｰ���逅�〒蜈ｱ騾壽ュ蝣ｱ繝ｻ謖ｯ繧玖�縺�ｒ謇ｱ縺�◆繧√�蝓ｺ蠎包ｽｸ�暦ｽｽ�｡
 *
 * @author tichnv
 *************************************************************************
 */
public abstract class ReportBase {
	/** RootFolderOffset */
	private static String RootFolderOffset = "/../../report";

	/** theDocRoot*/
	private String theDocRoot = null;

	public void setDocRoot(String docRoot) {
		theDocRoot = docRoot;
	}

	public String getDocRoot() {
		return (theDocRoot);
	}

	public void setDocRoot(ServletContext servletContext) {
		theDocRoot = servletContext.getRealPath(RootFolderOffset);
	}

	/**
	 *********************************************************************
	 * theReportProfile
	 */
	private ReportBaseProfile theReportProfile = null;

	public void setReportProfile(ReportBaseProfile reportProfile) {
		theReportProfile = reportProfile;
	}

	public ReportBaseProfile getReportProfile() {
		return (theReportProfile);
	}

	/**
	 *********************************************************************
	 * 繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 */
	private Workbook theWorkbook = null;

	public void setWorkbook(Workbook workbook) {
		theWorkbook = workbook;
	}

	public Workbook getWorkbook() {
		return (theWorkbook);
	}

	/*********************************************************************
	 * DB connection
	 */
	private Connection theDbConnection = null;

	public void setDbConnection(Connection dbConnection) {
		theDbConnection = dbConnection;
	}

	public Connection getDbConnection() {
		return (theDbConnection);
	}

	/**
	 *********************************************************************
	 * 讒狗ｯ牙ｭ�
	 */
	public ReportBase() {
		super();
		theReportProfile = new ReportBaseProfile();
	}

	/**
	 *********************************************************************
	 * 讒狗ｯ牙ｭ撰ｼ茨ｾ鯉ｾ滂ｾ幢ｾ鯉ｽｧ�ｲ�呎欠螳夲ｼ�
	 */
	public ReportBase(ReportBaseProfile reportProfile) {
		super();
		this.setReportProfile(reportProfile);
	}

	/**
	 *********************************************************************
	 * 螳溯｡�
	 */
	public void run() throws Exception {
		Date aRepoTStamp = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		Workbook aWorkbook = null;
		try {
			// /// 蛻晄悄蛹門�逅�/////
			init();

			// /// ��ｾ橸ｾ鯉ｽｫ�呻ｾ�｣懈ｭ｣ /////
			if (theReportProfile.getTitle() == null)
				theReportProfile.setTitle("");
			if (theReportProfile.getSubTitle() == null)
				theReportProfile.setSubTitle("");
			if (theReportProfile.getRepoTStamp() == null)
				theReportProfile.setRepoTStamp(aRepoTStamp);
			if (theReportProfile.getRepoTemplSheetName() == null)
				theReportProfile.setRepoTemplSheetName("Sheet1");
			if (theReportProfile.getRepoTemplkey() == null)
				theReportProfile.setRepoTemplkey("Tmp_");

			String aOutFileNameW = null;
			String aOutFileExt = null;
			String aOutputName = theReportProfile.getRepoOutputName();
			if (aOutputName == null || "".equals(aOutputName)) {
				throw (new Exception("Error! Report file name nothing."));
			}
			int extIndex = aOutputName.lastIndexOf(".");
			if (extIndex < 0) {
				aOutFileNameW = aOutputName;
				aOutFileExt = ".xls";
				aOutputName += aOutFileExt;
			} else {
				aOutFileNameW = aOutputName.substring(0, extIndex);
				aOutFileExt = aOutputName.substring(extIndex);
			}

			String aTemplateName = theReportProfile.getRepoTemplName();
			if (aTemplateName != null && "".equals(aTemplateName)) {
				aTemplateName = null;
			} else {
				extIndex = aTemplateName.lastIndexOf(".");
				if (extIndex < 0)
					aTemplateName += ".xls";
			}

			InputStream inStream = new FileInputStream(theDocRoot + "/"
					+ theReportProfile.getRepoTemplkey() + aTemplateName);
			aWorkbook = WorkbookFactory.create(inStream);
			if (aWorkbook == null) {
				throw (new Exception("Error! Workbook open NG."));
			}
			setWorkbook(aWorkbook);

			draw();

			String aFilePath = null;
			OutputStream outStream = null;
			for (int i = 0; i < 10; i++) {
				try {
					aFilePath = theDocRoot + "/" + aOutFileNameW + "_"
							+ sdf1.format(new Date()) + aOutFileExt;
					outStream = new FileOutputStream(aFilePath);
					aWorkbook.write(outStream);
					theReportProfile.setRepoOutputNameFullPath(aFilePath);
					break;
				} catch (IOException e) {
					// retry
				}
			}
			if (outStream != null) {
				outStream.close();
				outStream = null;
			}

			term();
		} catch (Exception e) {
			throw (e);
		} finally {
			aWorkbook = null;
		}
	}

	/**
	 *********************************************************************
	 * 蛻晄悄蛹門�逅�
	 */
	protected void init() throws Exception {
		System.out.println("---- " + this.getClass().getName() + ".appInit()"
				+ "...");
		appInit();
		InputStream in = new FileInputStream(new File(
				theReportProfile.getRepoOutputName()));
		setWorkbook(WorkbookFactory.create(in));
	}

	/**
	 *********************************************************************
	 * 譛ｬ螳溯｡悟�逅�
	 *
	 * @author tichnv
	 */
	protected void draw() throws Exception {
		System.out.println("---- " + this.getClass().getName() + ".appDraw()"
				+ "...");
		appDraw();
	}

	/**
	 *********************************************************************
	 * 邨ゆｺ��逅�
	 */
	protected void term() throws Exception {
		System.out.println("---- " + this.getClass().getName() + ".appTerm()"
				+ "...");
		appTerm();
	}

	/**
	 *********************************************************************
	 * 螳溯｡�
	 *
	 * @author tichnv
	 * @since 2016/01/19
	 * 
	 */
	public void run2() throws Exception {
		if (theReportProfile.getRepoTemplName() == null) {
			throw (new Exception("Error! Output name nothing."));
		}
		try {
			InputStream in = new FileInputStream(new File(
					theReportProfile.getRepoOutputName()));
			setWorkbook(WorkbookFactory.create(in));
		} catch (Exception e) {
		}
	}

	/**
	 *********************************************************************
	 * 繧｢繝励Μ螳溯｣�ｼ亥�譛溷喧蜃ｦ逅�ｼ�
	 */
	protected abstract void appInit() throws Exception;

	/**
	 *********************************************************************
	 * 繧｢繝励Μ螳溯｣�ｼ域悽螳溯｡悟�逅�ｼ�
	 */
	protected abstract void appDraw() throws Exception;

	/**
	 *********************************************************************
	 * 繧｢繝励Μ螳溯｣�ｼ育ｵゆｺ��逅�ｼ�
	 */
	protected abstract void appTerm() throws Exception;
}

