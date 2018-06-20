package jp.co.cyms.base.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import jp.co.cyms.common.Constant;
import jp.co.cyms.common.DownloadUtil;
import jp.co.cyms.common.FileUtil;

/**
 * AbstractExportは＊＊＊＊＊を行うクラスです。
 *
 * @author dent
 * @since 2016/10/06
 */

public abstract class AbstractExport extends ReportForExcel {
	protected String userID;
	protected String reportID;
	protected String templateFolder;
	protected String outputFolder;
	protected String outputFile;
	protected String outputName;
	protected String outputNewName;
	protected String templateName;
	protected String templateFile;
	protected String systemId;
	protected String category;
	protected boolean addSheet;
	protected String sheetTemplateName;
	protected List<String> listSheetTemp;
	protected String multiFile;
	
	
	protected MapSqlParameterSource sqlMapParams;
	protected Map<String, Object> exportResult = new HashMap<String, Object>();

	/**
	 * 親クラスの初期化メソッドを展開
	 */
	@Override
	protected void appInit() throws Exception {
		exportResult.put("success", true);
		exportResult.put("errorCode", "");
		try {
			if(multiFile != null && multiFile != ""){
				this.outputFile = this.outputFolder + "/" + this.outputName + this.multiFile + Constant.XLSX_EXTENSION;
			}else{
				this.outputFile = this.outputFolder + "/" + this.outputName + Constant.XLSX_EXTENSION;
			}
			
			/* Copy template file */
			FileUtil.copyFile(templateFile, outputFile);

			/* Delete working files for existing output */

			if (xPROFILE == null) {
				xPROFILE = this.getReportProfile();
			}
			xPROFILE.setRepoId(reportID);
			/* Name of template file*/
			xPROFILE.setRepoTemplName(templateFile);
			/*Name of output file */
			xPROFILE.setRepoOutputName(outputFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			exportResult.put("success", false);
		}
	}

	@Override
	protected void appTerm() throws Exception {
	}

	/**
	 * 出力の本処理実施
	 */
	@Override
	protected void appDraw() throws Exception {
		Sheet aSHEET = null;
		xWORKBOOK = this.getWorkbook();
		System.out.println("path " + templateFile);
		FileOutputStream out = null;
		try {
			if (xWORKBOOK == null) {
				System.out.println("xworkbook was be null and create new Workbook");
				File fileToExport = new File(templateFile);
				InputStream inputStream = new FileInputStream(fileToExport);
				xWORKBOOK = WorkbookFactory.create(inputStream);
			}

			// add sheet
			addSheet(xWORKBOOK, aSHEET);

			if (!addSheet) {
				// create list sheet name
				List<String> lstSheetName = new LinkedList<>();
				// put name to list
				for (int i = 0; i < xWORKBOOK.getNumberOfSheets(); i++) {
					lstSheetName.add(xWORKBOOK.getSheetAt(i).getSheetName());
				}
				/* Copy of sheet */
				for (String sheetName : lstSheetName) {
					ExcelUtility.cloneSheet(xWORKBOOK, sheetName, sheetName + Constant.SHEET_TEMPLATE,
							xWORKBOOK.getSheetIndex(sheetName) + 1);
					aSHEET = xWORKBOOK.getSheetAt(xWORKBOOK.getSheetIndex(sheetName + Constant.SHEET_TEMPLATE));
					aSHEET.getPrintSetup().setLandscape(xWORKBOOK.getSheet(sheetName).getPrintSetup().getLandscape());
					aSHEET.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
					/* Delete Sheet */
					ExcelUtility.sheetRemove(xWORKBOOK, sheetName);
					ExcelUtility.setSheetName(xWORKBOOK, sheetName + Constant.SHEET_TEMPLATE, sheetName);

					/* Data output */
					/* ヘッダーにデータを記入する */
					fillHeader(xWORKBOOK, aSHEET);
					/* Fill in the data on the body */
					fillBody(xWORKBOOK, aSHEET);

					fillFooter(xWORKBOOK, aSHEET);
					customSheet(aSHEET);
					aSHEET.setForceFormulaRecalculation(true);
				}
				removeSheet(xWORKBOOK);
			} else {
				for(int i=0;i<xWORKBOOK.getNumberOfSheets();i++){
					aSHEET = xWORKBOOK.getSheetAt(i);
					/* Data output */
					if(sheetTemplateName != null && aSHEET.getSheetName().equals(sheetTemplateName)){
						continue;
					}
					/* ヘッダーにデータを記入する */
					fillHeader(xWORKBOOK, aSHEET);
					/* Fill in the data on the body */
					fillBody(xWORKBOOK, aSHEET);

					fillFooter(xWORKBOOK, aSHEET);
					
					customSheet(aSHEET);
				}
				if(sheetTemplateName != null) {
					ExcelUtility.sheetRemove(xWORKBOOK, sheetTemplateName);
				}
			}
			//Remove template
			if(listSheetTemp != null && listSheetTemp.size() > 0) {
				for(String temp : listSheetTemp){
					ExcelUtility.sheetRemove(xWORKBOOK, temp);
				}
			}
			
			/* Write to file */
			File file = new File(this.outputFile);

			if (file.exists()) {
				file.delete();
			}
			file = new File(this.outputFile);
			/* Check for exist file */
			if (!file.exists()) {
				file.createNewFile();
			}

			out = new FileOutputStream(file);
			xWORKBOOK.write(out);
		} catch (Exception ex) {
			ex.printStackTrace();
			exportResult.put("success", false);
		} finally {
			try {
				out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	protected void customSheet(Sheet aSheet) {
		
	}
	/**
	 * Put data in the header part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @throws Exception
	 */
	protected void addSheet(Workbook aWORKBOOK, Sheet aSHEET) throws Exception {

	}
	/**
	 * Put data in the header part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @throws Exception
	 */
	protected void removeSheet(Workbook aWORKBOOK) throws Exception {

	}
	/**
	 * Put data in the header part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @throws Exception
	 */
	protected abstract void fillHeader(Workbook aWORKBOOK, Sheet aSHEET) throws Exception;

	/**
	 * Put the data in the body part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 */
	protected abstract void fillBody(Workbook aWORKBOOK, Sheet aSHEET) throws Exception;

	/**
	 * Put data in the Footer part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @throws Exception
	 */
	protected abstract void fillFooter(Workbook aWORKBOOK, Sheet aSHEET) throws Exception;

	/**
	 * Output processing
	 *
	 * @param reqReportID
	 * @return 真偽（ エラーが発生しない場合、trueを戻す。）
	 * @throws Exception
	 */
	protected abstract Map<String, Object> exportExecute(Map<String, Object> reqParams, HttpServletResponse response)
			throws Exception;

	/**
	 * Download report excel
	 *
	 * @param outputFile
	 * @param fileName
	 * @param response
	 * @return true/false
	 */
	protected Map<String, Object> downloadExcel(String outputFile, String fileName, HttpServletResponse response) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		try {
			if (outputFile != null) {
				rtnMap = DownloadUtil.responseFile(outputFile, fileName, response);
			}
			return rtnMap;
		} catch (Exception e) {
			e.printStackTrace();
			rtnMap.put("success", false);
			return rtnMap;
		}
	}

	/**
	 * Download report excel
	 *
	 * @param outputFile
	 * @param fileName
	 * @param response
	 * @return true/false
	 */
	protected Map<String, Object> downloadExcel(String outputFile, String fileName, HttpServletResponse response,
			Map<String, Object> rtnMap) {
		try {
			if (outputFile != null) {
				rtnMap = DownloadUtil.responseFile(outputFile, fileName, response, rtnMap);
			}
			return rtnMap;
		} catch (Exception e) {
			e.printStackTrace();
			rtnMap.put("success", false);
			return rtnMap;
		}
	}
	
}

