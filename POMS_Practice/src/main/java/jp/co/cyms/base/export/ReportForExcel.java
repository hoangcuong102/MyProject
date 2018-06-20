package jp.co.cyms.base.export;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *  Excel �夲ｾ趣ｾ滂ｽｰ���逅�渕蠎輔��ｸ�暦ｽｽ
 */
public abstract class ReportForExcel extends ReportBase {
	/** ExcelUtility (static Method). */
	protected ReportBaseProfile xPROFILE = null;

	/** ExcelUtility (static Method). */
	protected Workbook xWORKBOOK = null;

	/** ExcelUtility (static Method). */
	protected Connection xDBCONN = null;

	/** ExcelUtility (static Method). */
	protected static ExcelUtility UTIL;

	/**
	 *********************************************************************
	 * 蛻晄悄蛹門�逅�
	 */
	protected void init() throws Exception {
		System.out.println("---- " + this.getClass().getName() + ".init()"
				+ "...");

		xPROFILE = this.getReportProfile();
		xDBCONN = this.getDbConnection();

		super.init();
	}

	/**
	 *********************************************************************
	 * 譛ｬ螳溯｡悟�逅�
	 */
	protected void draw() throws Exception {
		System.out.println("---- " + this.getClass().getName() + ".draw()"
				+ "...");
		xWORKBOOK = this.getWorkbook();
		if (xWORKBOOK == null) {
			// UPDATE : 繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ繧貞�譛溷喧縺吶ｋ
			InputStream in = new FileInputStream(new File(
					xPROFILE.getRepoOutputName()));
			
			xWORKBOOK = WorkbookFactory.create(in);
			if (xWORKBOOK == null) {
				throw (new Exception("Error! Workbook open NG."));
			}
		}
		// 繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ縺ｮ閾ｪ蜍募�險育ｮ励ｒ螳滓命
		xWORKBOOK.setForceFormulaRecalculation(true);
		super.draw();
	}
	
	

	/**
	 *********************************************************************
	 * 邨ゆｺ��逅�
	 */
	protected void term() throws Exception {
		System.out.println("---- " + this.getClass().getName() + ".term()"
				+ "...");
		super.term();
	}

	/**
	 *********************************************************************
	 * 蠑上�險ｭ螳夲ｼ夊ｶｳ縺礼ｮ暦ｼ域ｱ守畑��謖�ｮ壻ｽ咲ｽｮ�郁｡悟��会ｼ代→菴咲ｽｮ�郁｡悟��会ｼ偵ｒ蜉�ｮ励☆繧句ｼ上ｒ險ｭ螳�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param colNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param row1
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col1
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ1
	 * @param row2
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col2
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ2
	 * @author create:TichNV2013/12/12
	 */
	protected void setFormula_Addition(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int rowNum, // 險ｭ螳夲ｼ夊ｨｭ螳夊｡御ｽ咲ｽｮ
			int colNum, // 險ｭ螳夲ｼ夊ｨｭ螳壼�菴咲ｽｮ
			int row1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�陦御ｽ咲ｽｮ1
			int col1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ1
			int row2, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�陦御ｽ咲ｽｮ2
			int col2 // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ2
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			// 蠑上�險ｭ螳�
			Row aROW = aSHEET.getRow(rowNum);
			String aCel1 = ExcelUtility.cellA1(col1, row1); // 險育ｮ怜�菴咲ｽｮ1
			String aCel2 = ExcelUtility.cellA1(col2, row2); // 險育ｮ怜�菴咲ｽｮ2
			aROW.getCell(colNum).setCellFormula(aCel1 + "+" + aCel2);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * 蠑上�險ｭ螳夲ｼ壼ｼ輔″邂暦ｼ域ｱ守畑��謖�ｮ壻ｽ咲ｽｮ�郁｡悟��会ｼ代°繧我ｽ咲ｽｮ�郁｡悟��会ｼ偵ｒ貂帷ｮ励☆繧句ｼ上ｒ險ｭ螳�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param colNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param row1
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col1
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ1
	 * @param row2
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col2
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ2
	 * @author create:TichNV2013/12/12
	 */
	protected void setFormula_Subtraction(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int rowNum, // 險ｭ螳夲ｼ夊ｨｭ螳夊｡御ｽ咲ｽｮ
			int colNum, // 險ｭ螳夲ｼ夊ｨｭ螳壼�菴咲ｽｮ
			int row1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�陦御ｽ咲ｽｮ1
			int col1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ1
			int row2, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�陦御ｽ咲ｽｮ2
			int col2 // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ2
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			// 蠑上�險ｭ螳�
			Row aROW = aSHEET.getRow(rowNum);
			String aCel1 = ExcelUtility.cellA1(col1, row1); // 險育ｮ怜�菴咲ｽｮ1
			String aCel2 = ExcelUtility.cellA1(col2, row2); // 險育ｮ怜�菴咲ｽｮ2
			aROW.getCell(colNum).setCellFormula(aCel1 + "-" + aCel2);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * 蠑上�險ｭ螳夲ｼ售ubTotal蠑上�險ｭ螳�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            陦御ｽ咲ｽｮ
	 * @param colNum
	 *            蛻嶺ｽ咲ｽｮ
	 * @param rowCondition
	 *            譚｡莉ｶ陦御ｽ咲ｽｮ
	 * @param colTotalSt
	 *            髮�ｨ磯幕蟋玖｡御ｽ咲ｽｮ
	 * @param colTotalEn
	 *            髮�ｨ育ｵゆｺ�｡御ｽ咲ｽｮ
	 * @author create:TichNV2013/11/30
	 */
	protected void setFormula_SumIf(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int rowNum, // 險ｭ螳夲ｼ夊｡御ｽ咲ｽｮ
			int colNum, // 險ｭ螳夲ｼ壼�菴咲ｽｮ
			int rowCondition, // 險ｭ螳夲ｼ壽擅莉ｶ陦御ｽ咲ｽｮ
			int colTotalSt, // 險ｭ螳夲ｼ夐寔險磯幕蟋玖｡御ｽ咲ｽｮ
			int colTotalEn // 險ｭ螳夲ｼ夐寔險育ｵゆｺ�｡御ｽ咲ｽｮ
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			// SUMIF蠑上�險ｭ螳�
			Row aROW = aSHEET.getRow(rowNum);
			String aIfSt = ExcelUtility.cellA1(colTotalSt, rowCondition); // 譚｡莉ｶ遽�峇髢句ｧ倶ｽ咲ｽｮ
			String aIfEn = ExcelUtility.cellA1(colTotalEn, rowCondition); // 譚｡莉ｶ遽�峇邨ゆｺ�ｽ咲ｽｮ
			String aWhile = ExcelUtility.cellA1(colNum, rowCondition); // 譚｡莉ｶ菴咲ｽｮ
			String aSumSt = ExcelUtility.cellA1(colTotalSt, rowNum); // 髮�ｨ育ｯ�峇髢句ｧ倶ｽ咲ｽｮ
			String aSumEn = ExcelUtility.cellA1(colTotalEn, rowNum); // 髮�ｨ育ｯ�峇邨ゆｺ�ｽ咲ｽｮ
			aROW.getCell(colNum).setCellFormula(
					"SUMIF(" + aIfSt + ":" + aIfEn + "," + aWhile + ","
							+ aSumSt + ":" + aSumEn + ")");
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * 蠑上�險ｭ螳夲ｼ壼牡繧顔ｮ暦ｼ域ｱ守畑��謖�ｮ壻ｽ咲ｽｮ�郁｡悟��会ｼ代ｒ菴咲ｽｮ�郁｡悟��会ｼ偵〒蜑ｲ繧顔ｮ励☆繧句ｼ上ｒ險ｭ螳�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param colNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param row1
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col1
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ1
	 * @param row2
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col2
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ2
	 * @author create:TichNV2013/12/11
	 */
	protected void setFormula_Division(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int rowNum, // 險ｭ螳夲ｼ夊ｨｭ螳夊｡御ｽ咲ｽｮ
			int colNum, // 險ｭ螳夲ｼ夊ｨｭ螳壼�菴咲ｽｮ
			int row1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�陦御ｽ咲ｽｮ1
			int col1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ1
			int row2, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�陦御ｽ咲ｽｮ2
			int col2 // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ2
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			// 髯､邂怜ｼ上�險ｭ螳�
			Row aROW = aSHEET.getRow(rowNum);
			String aCel1 = ExcelUtility.cellA1(col1, row1); // 險育ｮ怜�菴咲ｽｮ1
			String aCel2 = ExcelUtility.cellA1(col2, row2); // 險育ｮ怜�菴咲ｽｮ2
			aROW.getCell(colNum).setCellFormula(
					"IF(" + aCel2 + "=0,," + aCel1 + "/" + aCel2 + ")");
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * 蠑上�險ｭ螳夲ｼ壼牡繧顔ｮ�蜷御ｸ�｡後�謖�ｮ壼�菴咲ｽｮ�代ｒ蛻嶺ｽ咲ｽｮ�偵〒蜑ｲ繧顔ｮ励☆繧句ｼ上ｒ險ｭ螳�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param colNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param col1
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ1
	 * @param col2
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ2
	 * @author create:TichNV2013/11/30
	 */
	protected void setFormula_Division(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int rowNum, // 險ｭ螳夲ｼ夊ｨｭ螳夊｡御ｽ咲ｽｮ
			int colNum, // 險ｭ螳夲ｼ夊ｨｭ螳壼�菴咲ｽｮ
			int col1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ1
			int col2 // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ2
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			// 髯､邂怜ｼ上�險ｭ螳夲ｼ域ｱ守畑�峨ｒ螳溯｡�
			this.setFormula_Division(aWORKBOOK, aSHEET, rowNum, colNum, rowNum,
					col1, rowNum, col2);

		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * 蠑上�險ｭ螳夲ｼ壽寺縺醍ｮ暦ｼ域ｱ守畑��謖�ｮ壻ｽ咲ｽｮ�郁｡悟��会ｼ代→菴咲ｽｮ�郁｡悟��会ｼ偵ｒ謗帙￠邂励☆繧句ｼ上ｒ險ｭ螳�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param colNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param row1
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col1
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ1
	 * @param row2
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col2
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ2
	 * @param kyenFlg
	 *            1000蜀�腰菴崎ｨ育ｮ励ヵ繝ｩ繧ｰ 1:蜊��蜊倅ｽ�
	 * @author create:TichNV2013/12/11
	 */
	protected void setFormula_Multiplication(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int rowNum, // 險ｭ螳夲ｼ夊ｨｭ螳夊｡御ｽ咲ｽｮ
			int colNum, // 險ｭ螳夲ｼ夊ｨｭ螳壼�菴咲ｽｮ
			int row1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�陦御ｽ咲ｽｮ1
			int col1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ1
			int row2, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�陦御ｽ咲ｽｮ2
			int col2, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ2
			boolean kyenFlg // 險ｭ螳夲ｼ�000蜀�腰菴崎ｨ育ｮ励ヵ繝ｩ繧ｰ true:蜊��蜊倅ｽ�
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			// 謗帙￠邂怜ｼ上�險ｭ螳�
			Row aROW = aSHEET.getRow(rowNum);
			String aCel1 = ExcelUtility.cellA1(col1, row1); // 險育ｮ怜�菴咲ｽｮ1
			String aCel2 = ExcelUtility.cellA1(col2, row2); // 險育ｮ怜�菴咲ｽｮ2
			if (kyenFlg) {
				aROW.getCell(colNum).setCellFormula(
						aCel1 + "*" + aCel2 + "/1000");
			} else {
				aROW.getCell(colNum).setCellFormula(aCel1 + "*" + aCel2);
			}
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * 蠑上�險ｭ螳夲ｼ壽寺縺醍ｮ�蜷御ｸ�｡後�謖�ｮ壼�菴咲ｽｮ�代→蛻嶺ｽ咲ｽｮ�偵ｒ謗帙￠邂励☆繧句ｼ上ｒ險ｭ螳�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param colNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param row1
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col1
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ1
	 * @param row2
	 *            險育ｮ怜�陦御ｽ咲ｽｮ1
	 * @param col2
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ2
	 * @param kyenFlg
	 *            1000蜀�腰菴崎ｨ育ｮ励ヵ繝ｩ繧ｰ 1:蜊��蜊倅ｽ�
	 * @author create:TichNV2013/12/11
	 */
	protected void setFormula_Multiplication(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int rowNum, // 險ｭ螳夲ｼ夊ｨｭ螳夊｡御ｽ咲ｽｮ
			int colNum, // 險ｭ螳夲ｼ夊ｨｭ螳壼�菴咲ｽｮ
			int col1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ1
			int col2, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ2
			boolean kyenFlg // 險ｭ螳夲ｼ�000蜀�腰菴崎ｨ育ｮ励ヵ繝ｩ繧ｰ true:蜊��蜊倅ｽ�
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			// 謗帙￠邂怜ｼ上�險ｭ螳�
			this.setFormula_Multiplication(aWORKBOOK, aSHEET, rowNum, colNum,
					rowNum, col1, rowNum, col2, kyenFlg);

		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * SubTotal蠑上�險ｭ螳夲ｼ亥�譁ｹ蜷代↓險ｭ螳夲ｼ�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param colNumSt
	 *            險ｭ螳夐幕蟋句�菴咲ｽｮ
	 * @param colNumEn
	 *            險ｭ螳夂ｵゆｺ��菴咲ｽｮ
	 * @param rowTotalSt
	 *            髮�ｨ磯幕蟋玖｡御ｽ咲ｽｮ
	 * @param rowTotalEn
	 *            髮�ｨ育ｵゆｺ�｡御ｽ咲ｽｮ
	 * @author create:TichNV2013/11/29
	 */
	protected void setFormula_SubTotal(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int rowNum, // 險ｭ螳夲ｼ夊ｨｭ螳夊｡御ｽ咲ｽｮ
			int colNumSt, // 險ｭ螳夲ｼ夊ｨｭ螳夐幕蟋句�菴咲ｽｮ
			int colNumEn, // 險ｭ螳夲ｼ夊ｨｭ螳夂ｵゆｺ��菴咲ｽｮ
			int rowTotalSt, // 險ｭ螳夲ｼ夐寔險磯幕蟋玖｡御ｽ咲ｽｮ
			int rowTotalEn // 險ｭ螳夲ｼ夐寔險育ｵゆｺ�｡御ｽ咲ｽｮ
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			// SubTotal蠑上�險ｭ螳�
			Row aROW = aSHEET.getRow(rowNum);
			for (int i = 0; i <= (colNumEn - colNumSt); i++) {
				int aColNum = colNumSt + i;
				String aSumSt = ExcelUtility.cellA1(aColNum, rowTotalSt);
				String aSumEn = ExcelUtility.cellA1(aColNum, rowTotalEn);
				String aFormula = aROW.getCell(aColNum).getStringCellValue();
				if (aFormula.toUpperCase().matches(".*#SUBTOTAL#.*")) {
					aROW.getCell(aColNum).setCellFormula(
							"SUBTOTAL(9," + aSumSt + ":" + aSumEn + ")");
				}
			}
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * 繝代�繧ｻ繝ｳ繝亥ｼ上�險ｭ螳夲ｼ郁｡梧婿蜷代↓險ｭ螳夲ｼ�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param colNum
	 *            險ｭ螳壼�菴咲ｽｮ
	 * @param colCalc
	 *            險育ｮ怜�菴咲ｽｮ
	 * @param rowCalcSt
	 *            險育ｮ鈴幕蟋玖｡御ｽ咲ｽｮ
	 * @param rowCalcEn
	 *            險育ｮ礼ｵゆｺ�｡御ｽ咲ｽｮ
	 * @param rowTotal
	 *            蜷郁ｨ亥�陦御ｽ咲ｽｮ�亥�豈搾ｼ�
	 * @author create:TichNV2013/12/3
	 */
	protected void setFormula_Percentage(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int colNum, // 險ｭ螳夲ｼ夊ｨｭ螳壼�菴咲ｽｮ
			int colCalc, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�菴咲ｽｮ
			int rowCalcSt, // 險ｭ螳夲ｼ夊ｨ育ｮ鈴幕蟋玖｡御ｽ咲ｽｮ
			int rowCalcEn, // 險ｭ螳夲ｼ夊ｨ育ｮ礼ｵゆｺ�｡御ｽ咲ｽｮ
			int rowTotal // 險ｭ螳夲ｼ壼粋險亥�陦御ｽ咲ｽｮ�亥�豈搾ｼ�
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			for (int i = 0; i <= rowCalcEn - rowCalcSt; i++) {
				int aRowNum = rowCalcSt + i;
				Row aROW = aSHEET.getRow(aRowNum);
				String aValue = ExcelUtility.cellA1(colCalc, aRowNum);
				String aTotal = ExcelUtility.cellA1(colCalc, rowTotal);
				String aFormula = aROW.getCell(colNum).getStringCellValue();
				if (aFormula.toUpperCase().matches(".*#PERCENT#.*")) {
					aROW.getCell(colNum).setCellFormula(
							"IF(" + aTotal + "=0,," + aValue + "/" + aTotal
									+ ")");
				}
			}
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 *********************************************************************
	 * 隕狗ｩ埼｡阪→莠育ｮ励�蟾ｮ�育紫�臥ｮ怜�蠑上�險ｭ螳�蜑ｲ繧顔ｮ�謖�ｮ壼�菴咲ｽｮ�代ｒ蛻嶺ｽ咲ｽｮ�偵〒蜑ｲ繧�
	 *
	 * @param aWORKBOOK
	 *            繝ｯ繝ｼ繧ｯ繝悶ャ繧ｯ
	 * @param aSHEET
	 *            繧ｷ繝ｼ繝�
	 * @param rowNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param colNum
	 *            險ｭ螳夊｡御ｽ咲ｽｮ
	 * @param col1
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ1
	 * @param col2
	 *            險育ｮ怜�蛻嶺ｽ咲ｽｮ2
	 * @author create:TichNV2013/12/3
	 */
	protected void setFormula_Diff(Workbook aWORKBOOK, // 險ｭ螳夲ｼ夲ｾ懶ｽｰ�ｸ�鯉ｾ橸ｽｯ�ｸ
			Sheet aSHEET, // 險ｭ螳夲ｼ夲ｽｼ�ｰ��
			int colNum, // 險ｭ螳夲ｼ夊ｨｭ螳壼�菴咲ｽｮ
			int rowCalcSt, // 險ｭ螳夲ｼ夊ｨ育ｮ鈴幕蟋玖｡御ｽ咲ｽｮ
			int rowCalcEn, // 險ｭ螳夲ｼ夊ｨ育ｮ礼ｵゆｺ�｡御ｽ咲ｽｮ
			int col1, // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ1
			int col2 // 險ｭ螳夲ｼ夊ｨ育ｮ怜�蛻嶺ｽ咲ｽｮ2
	) throws Exception // 萓句､厄ｼ壽ｱ守畑萓句､�
	{
		try {

			for (int i = 0; i <= rowCalcEn - rowCalcSt; i++) {
				int aRowNum = rowCalcSt + i;
				Row aROW = aSHEET.getRow(aRowNum);
				String aCel1 = ExcelUtility.cellA1(col1, aRowNum); // 險育ｮ怜�菴咲ｽｮ1
				String aCel2 = ExcelUtility.cellA1(col2, aRowNum); // 險育ｮ怜�菴咲ｽｮ2
				String aFormula = aROW.getCell(colNum).getStringCellValue();
				if (aFormula.toUpperCase().matches(".*#DIFF#.*")) {
					aROW.getCell(colNum).setCellFormula(
							"IF(" + aCel2 + "=0,,(" + aCel2 + "-" + aCel1
									+ ")/" + aCel2 + ")");
				}
			}
		} catch (Exception e) {
			throw (e);
		}
	}
}
