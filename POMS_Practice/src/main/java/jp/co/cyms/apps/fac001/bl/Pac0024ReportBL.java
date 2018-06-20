package jp.co.cyms.apps.fac001.bl;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.stereotype.Service;

import jp.co.cyms.apps.fac001.bean.DownloadDeliveryOrderBean;
import jp.co.cyms.base.export.AbstractExport;
import jp.co.cyms.base.export.ExcelUtility;
import jp.co.cyms.base.export.ExportBL;
import jp.co.cyms.base.export.SysParam;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.FileOfficeConverter;
import jp.co.cyms.common.StringUtil;

/**
 * [SAC0024] Download Delivery Order Entry/Update BL
 * 
 * @author AnhNT2
 * @since 24/01/2018
 */
@Service
public class Pac0024ReportBL extends AbstractExport {
	/** Index of sheet total summary */
	private final static int INDEX_TOTAL_SUMMARY = 0;

	/** Name of sheet first */
	public final static String NAME_SHEET = "Progress Result";
	public final static String NAME_SHEET_TEMP = "Progress Result Temp";
	

	/** Initialize temporary container. */
	private Map<String, Object> tempParams;

	/** Initialize collection contain result. */
	private Map<String, Object> rtnMap = new HashMap<String, Object>();

	CellRangeAddress cellRangeAddressFooter;

	/**
	 * Initialize constructor of class.
	 */
	public Pac0024ReportBL() {
	}

	/**
	 * Put data in the header part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @throws Exception
	 */
	@Override
	protected void fillHeader(Workbook aWORKBOOK, Sheet aSHEET) throws Exception {
		// Fit Sheet to One Page
		aSHEET.setFitToPage(true);
		PrintSetup ps = aSHEET.getPrintSetup();
		ps.setFitWidth((short) 1);
		ps.setFitHeight((short) 0);

		// Set data for header
		DownloadDeliveryOrderBean downloadDeliveryOrderBean = (DownloadDeliveryOrderBean) tempParams
				.get("getDataHeaderForDownloadDelivery");
		int rowsA1 = 7;
		int rowsA2A3 = 9;
		int rowsA4 = 10;
		int rowsB7 = 20;
		int rowsB8 = 21;
		// DO No
		setCellValue(aSHEET, rowsA1, CellReference.convertColStringToIndex("F"), downloadDeliveryOrderBean.getDoNo());
		// CUSTOMER (COMPANY)
		setCellValue(aSHEET, rowsA2A3, CellReference.convertColStringToIndex("B"),
				downloadDeliveryOrderBean.getCompanyName());
		// ATTN:
		setCellValue(aSHEET, rowsA2A3, CellReference.convertColStringToIndex("F"),
				downloadDeliveryOrderBean.getAttnToName());
		// DATE
		setCellValue(aSHEET, rowsA4, CellReference.convertColStringToIndex("F"),
				downloadDeliveryOrderBean.getDeliverDt());

		// Set data for footer
		// Quotation No
		setCellValue(aSHEET, rowsB7, CellReference.convertColStringToIndex("C"),
				"Our Reference : Quotation No. " + downloadDeliveryOrderBean.getQuoteMrc());
		// D/O No.
		String doNo = (String) tempParams.get("doNo");
		setCellValue(aSHEET, rowsB8, CellReference.convertColStringToIndex("C"), "PO No.: " + doNo);
	}

	/**
	 * Put the data in the body part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 */
	@Override
	protected void fillBody(Workbook aWORKBOOK, Sheet aSHEET) throws Exception {
	//aWORKBOOK.setSheetName(INDEX_TOTAL_SUMMARY, NAME_SHEET);
		//String sheetName = aWORKBOOK.getSheetName(0);
		@SuppressWarnings("unchecked")
		DownloadDeliveryOrderBean listDownloadDeliveryOrder = (DownloadDeliveryOrderBean) tempParams
				.get("getDataHeaderForDownloadDelivery");
		if ((listDownloadDeliveryOrder.getCountryCd().equals("SG") || listDownloadDeliveryOrder.getCountryCd().equals("MY"))
				&& aSHEET.getSheetName().equals("DO")) {
			fillDataDeliveryOrderResult(aSHEET, aWORKBOOK);
			//aWORKBOOK.removePrintArea(2);
		}
		if (listDownloadDeliveryOrder.getCountryCd().equals("TH") && aSHEET.getSheetName().equals("DO_1")) {
			fillDataDeliveryOrderResult(aSHEET, aWORKBOOK);	
		}
		if (listDownloadDeliveryOrder.getCountryCd().equals("IN") && aSHEET.getSheetName().equals("DO_2")) {
			fillDataDeliveryOrderResult(aSHEET, aWORKBOOK);
		}
		if (listDownloadDeliveryOrder.getCountryCd().equals("ID") && aSHEET.getSheetName().equals("DO_3")) {
			fillDataDeliveryOrderResult(aSHEET, aWORKBOOK);
		}
		//fillDataDeliveryOrderResult(aSHEET, aWORKBOOK);
		
	}

	/**
	 * Put data in the Footer part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @throws Exception
	 */
	@Override
	protected void fillFooter(Workbook aWORKBOOK, Sheet aSHEET) throws Exception {
	}

	/**
	 * 親クラスの初期化メソッドを展開
	 */
	@Override
	protected void appInit() throws Exception {
		/* Retrieve the folder where the template is stored */
		ExportBL exportBL = new ExportBL();
		SysParam sysParam = exportBL.getSyspara();
		String nameFile = sysParam.getNameFileTempSac0024();

		this.templateFolder = sysParam.getTempDir();
		this.templateName = nameFile + Constant.XLSX_EXTENSION;
		this.templateFile = templateFolder + "/" + templateName;

		/* Delete working files for existing output */
		this.outputFolder = sysParam.getOuputDir();

		// File Name output
		this.outputName = templateName;

		this.outputNewName = templateName;
		super.appInit();
	}

	/**
	 * Fill value to cell
	 * 
	 * @param row
	 *            row index
	 * @param col
	 *            column index
	 * @param value
	 *            value need to fill
	 */
	public void setCellValue(Sheet aSHEET, int row, int col, String value) {
		aSHEET.getRow(row).getCell(col).setCellValue(value);
	}

	/**
	 * Fill number value to cell
	 * 
	 * @param row
	 *            row index
	 * @param col
	 *            column index
	 * @param value
	 *            value need to fill
	 */
	public void setNumberCellValue(Sheet aSHEET, int row, int col, String value) {
		try {
			aSHEET.getRow(row).getCell(col).setCellValue(Integer.parseInt(value));
		} catch (Exception e) {
			aSHEET.getRow(row).getCell(col).setCellValue(value);
		}

	}

	/**
	 * Fill Double value to cell
	 * 
	 * @param row
	 *            row index
	 * @param col
	 *            column index
	 * @param value
	 *            value need to fill
	 */
	public void setDoubleCellValue(Sheet aSHEET, int row, int col, String value) {
		try {
			aSHEET.getRow(row).getCell(col).setCellValue(Double.parseDouble(value));
		} catch (Exception e) {
			aSHEET.getRow(row).getCell(col).setCellValue(value);
		}

	}

	/**
	 * Fill Integer value to cell
	 * 
	 * @param row
	 *            row index
	 * @param col
	 *            column index
	 * @param value
	 *            value need to fill
	 */
	public void setIntegerCellValue(Sheet aSHEET, int row, int col, Integer value) {
		if (value != null) {
			aSHEET.getRow(row).getCell(col).setCellValue(value);
		}
	}

	@Override
	public Map<String, Object> exportExecute(Map<String, Object> reqParams, HttpServletResponse response)
			throws Exception {

		/* Assign Map tempParams = reqParams. */
		tempParams = reqParams;

		/* Set reportID */
		reportID = Constant.REPORT_ID_DATA_DOWNLOAD;

		/* Set user id is userID from reqParams. */
		userID = (String) reqParams.get(Constant.UID);

		// set add sheet                                                                      
		addSheet = false;

		/* プロファイル初期化 */
		init();
		/* ワークブック初期化 */
		draw();

		term();
		// Get status draw export.
		Boolean drawSuccess = (Boolean) exportResult.get(Constant.SUCESS);
		/* Get error code. */
		String errorCode = (String) exportResult.get(Constant.ERROR_CODE);

		// Check status draw export if is false then throw exception.
		if (!drawSuccess) {
			// logger.debug("---- Exception when drawing");
			rtnMap.put(Constant.SUCESS, false);
			rtnMap.put(Constant.ERROR_CODE, errorCode);
			return rtnMap;
		}

		// Initiate variable save status download.
		Boolean downSuccess = false;

		// When not exist error then execution the downloading report.
		rtnMap = super.downloadExcel(outputFile, outputNewName, response, rtnMap);
		// Get status the download.
		downSuccess = (Boolean) rtnMap.get(Constant.SUCESS);

		// Check status the download if false then throw exception.
		if (!downSuccess) {
			// logger.debug("---- Exception when downloading");
			rtnMap.put(Constant.SUCESS, false);
			return rtnMap;
		}
		// Convert excel to pdf
		FileOfficeConverter officeConverter = new FileOfficeConverter((String) tempParams.get("libreOfficeDir"));
		officeConverter.isSuccessCreatePDF(outputFile,
				outputFile.replace(Constant.XLSX_EXTENSION, Constant.PDF_EXTENSION));
		officeConverter.stopOfficeManager();
		// Delete excel file
		if (!StringUtil.isNullOrEmpty(outputFile)) {
			// Delete file when download finish.
			File outFile = new File(outputFile);
			outFile.delete();
		}
		// rename output file
		outputFile = outputFile.replace(Constant.XLSX_EXTENSION, Constant.PDF_EXTENSION);

		// put output file path
		rtnMap.put(Constant.OUTPUT_FILE, outputFile);
		rtnMap.put(Constant.OUTPUT_NAME, outputName);
		return rtnMap;
	}

	/**
	 * Put data in the header part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @throws Exception
	 */
	public void removeSheet(Workbook aWORKBOOK) throws Exception {
		@SuppressWarnings("unchecked")
		DownloadDeliveryOrderBean listDownloadDeliveryOrder = (DownloadDeliveryOrderBean) tempParams
				.get("getDataHeaderForDownloadDelivery");
		String sheetTemp0 = aWORKBOOK.getSheetName(0);
		String sheetTemp1 = aWORKBOOK.getSheetName(1);
		String sheetTemp2 = aWORKBOOK.getSheetName(2);
		String sheetTemp3 = aWORKBOOK.getSheetName(3);
        if ((listDownloadDeliveryOrder.getCountryCd().equals("SG") || listDownloadDeliveryOrder.getCountryCd().equals("MY")) &&sheetTemp0.equals("DO")){
            aWORKBOOK.removeSheetAt(1);
            aWORKBOOK.removeSheetAt(1);
            aWORKBOOK.removeSheetAt(1);
            
        }
        else if (listDownloadDeliveryOrder.getCountryCd().equals("TH")  && sheetTemp1.equals("DO_1")) {
        	aWORKBOOK.removeSheetAt(0);
        	aWORKBOOK.removeSheetAt(1);
        	aWORKBOOK.removeSheetAt(1);
        }
        else if (listDownloadDeliveryOrder.getCountryCd().equals("IN")  && sheetTemp2.equals("DO_2")) {
        	aWORKBOOK.removeSheetAt(0);
        	aWORKBOOK.removeSheetAt(0);
        	aWORKBOOK.removeSheetAt(1);
        }
        else if (listDownloadDeliveryOrder.getCountryCd().equals("ID")  && sheetTemp3.equals("DO_3")) {
        	aWORKBOOK.removeSheetAt(0);
        	aWORKBOOK.removeSheetAt(0);
        	aWORKBOOK.removeSheetAt(0);
        }
	}

	/**
	 * Fill data for sheet Job Detail in Container Drayage Data File
	 */
	@SuppressWarnings("unchecked")
	public void fillDataDeliveryOrderResult(Sheet aSHEET, Workbook aWORKBOOK) {
		// Font normal
		Font font = aWORKBOOK.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setBold(false);
		
		// Font bold
		Font fontBold = aWORKBOOK.createFont();
		fontBold.setFontName("Arial");
		fontBold.setFontHeightInPoints((short) 10);
		fontBold.setBold(true);

		// Row title (line 14 in file template)
		Row rowGetFont = aSHEET.getRow(10);
		// CellStyle center template
		CellStyle cellStyleTemp1 = rowGetFont.getCell(CellReference.convertColStringToIndex("C")).getCellStyle();
		// CellStyle Left template
		CellStyle cellStyleTemp2 = rowGetFont.getCell(CellReference.convertColStringToIndex("B")).getCellStyle();
		// CellStyle Center
		CellStyle cellStyleCenter = aSHEET.getWorkbook().createCellStyle();
		cellStyleCenter.cloneStyleFrom(cellStyleTemp1);
		cellStyleCenter.setFont(font);
		cellStyleCenter.setWrapText(true);
		//cellStyleCenter.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		// CellStyle Left
		CellStyle cellStyleLeft = aSHEET.getWorkbook().createCellStyle();
		cellStyleLeft.cloneStyleFrom(cellStyleTemp2);
		cellStyleLeft.setFont(font);
		cellStyleLeft.setWrapText(true);
		//cellStyleLeft.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		// CellStyle Center font bold
		CellStyle cellStyleCenterBold = aSHEET.getWorkbook().createCellStyle();
		cellStyleCenterBold.cloneStyleFrom(cellStyleTemp1);
		cellStyleCenterBold.setFont(fontBold);
		cellStyleCenterBold.setWrapText(false);
		//cellStyleCenterBold.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		// CellStyle Left font bold
		CellStyle cellStyleLeftBold = aSHEET.getWorkbook().createCellStyle();
		cellStyleLeftBold.cloneStyleFrom(cellStyleTemp2);
		cellStyleLeftBold.setFont(fontBold);
		cellStyleLeftBold.setWrapText(true);
		//cellStyleLeftBold.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		
		// CellStyle Left Top
		CellStyle cellStyleLeftTop = aSHEET.getWorkbook().createCellStyle();
		cellStyleLeftTop.cloneStyleFrom(cellStyleLeft);
		cellStyleLeftTop.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		
		// Maximum number of characters per line in cell at column merge column D and C
		int limitCharacterInWidth = (int) ((aSHEET.getColumnWidth(2) + aSHEET.getColumnWidth(3)) / 256);
		limitCharacterInWidth = Math.round(limitCharacterInWidth * 5f/4f);
		// Get the default height of the row
		float heightDefault = aSHEET.getRow(13).getHeightInPoints();
					
		List<DownloadDeliveryOrderBean> listDownloadDeliveryOrder = (List<DownloadDeliveryOrderBean>) tempParams
				.get("listDownloadDeliveryOrder");
		// If listDownloadDeliveryOrder is not null
		if (listDownloadDeliveryOrder != null){
			// Map DownloadDeliveryOrderBean grouping by sectionName
			Map<String, List<DownloadDeliveryOrderBean>> mapListDownloadDeliveryOrderBean = groupingDeliveryOrderBean(listDownloadDeliveryOrder);
	
			int rowsId = 15;
			// Number of rows to shift
			int numberRowShift = 0;
			int rowIdLastOfItem = 16;
			
			// ItemNo
			int itemNo = 0;
			// Draw data to aSHEET
			for (Map.Entry<String, List<DownloadDeliveryOrderBean>> entry : mapListDownloadDeliveryOrderBean.entrySet()){
				itemNo++;
				String key = entry.getKey();
				List<DownloadDeliveryOrderBean> value = entry.getValue();
				// Number of rows to shift
				numberRowShift = 2 + (value.size() > 3 ? value.size() : 3);
				aSHEET.shiftRows(rowIdLastOfItem, rowIdLastOfItem + 20, numberRowShift);
				rowIdLastOfItem = rowIdLastOfItem + numberRowShift;
				
				// Get row
				Row rows = CellUtil.getRow(rowsId, aSHEET);
				// Set item, value of sectionName
				// Set item
				checkNullValueCells(Integer.toString(itemNo), 1, rows, cellStyleCenterBold);
				// Set sectionName (cell [B-1])
				checkNullValueCells(key, 2, rows, cellStyleLeftBold);
				rowsId++;
				int rowStartFillData = rowsId;
				int numberLineCellRemark = 0;
				for (int i=0; i<value.size(); i++){
					//rowStartFillData = rowsId;
					rows = CellUtil.getRow(rowsId, aSHEET);
					/*// Merge column D and C
					aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,2,3));
					// Set sub item
					checkNullValueCells(Integer.toString(itemNo) + "." + Integer.toString(i+1), 1, rows, cellStyleCenter);
					// Set itemCd (cell [B-2])
					checkNullValueCells(value.get(i).getItemCd(), 2, rows, cellStyleLeft);
					// Set quantity (cell [B-5])
					checkNullValueCells(value.get(i).getItemQty(), 4, rows, cellStyleCenter);
					// Set serialNo (cell [B-6])
					checkNullValueCells(value.get(i).getSerialNo(), 5, rows, cellStyleCenter);
					rowsId++;
					rows = CellUtil.getRow(rowsId, aSHEET);
					// Merge column D and C
					aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,2,3));
					// Set serialNo (cell [B-3])
					checkNullValueCells(value.get(i).getItemName(), 2, rows, cellStyleLeft);
					rowsId++;
					rows = CellUtil.getRow(rowsId, aSHEET);
					// Merge column D and C
					aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,2,3));
					// Set remark (cell [B-4])
					checkNullValueCells(value.get(i).getRemark(), 2, rows, cellStyleLeft);
					rowsId += 2;*/
					if (i == 0) {
						rowStartFillData += 3;
						Row rowDescription = null;
						// Merge column D and C
						aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,2,3));
						// Set sub item
						checkNullValueCells(Integer.toString(itemNo) + "." + Integer.toString(i+1), 1, rows, cellStyleCenter);
						// Set itemCd (cell [B-2])
						checkNullValueCells(value.get(i).getItemCd(), 2, rows, cellStyleLeft);
						
						// Set quantity (cell [B-5])
						checkNullValueCells(value.get(i).getItemQty(), 4, rows, cellStyleCenter);
						rowDescription = CellUtil.getRow(rowsId + 1, aSHEET);
						// Merge column D and C
						aSHEET.addMergedRegion(new CellRangeAddress(rowsId + 1, rowsId + 1, 2, 3));
						// Set serialNo (cell [B-3])
						checkNullValueCells(value.get(i).getItemName(), 2, rowDescription, cellStyleLeft);
						
						// Set value to Remark
						rowDescription = CellUtil.getRow(rowsId + 2, aSHEET);
						// Merge column D and C
						//aSHEET.addMergedRegion(new CellRangeAddress(rowsId + 2, rowsId + 2, 2, 3));
						// Set remark (cell [B-4])
						String remark = value.get(i).getRemark() == null ? "" : value.get(i).getRemark().replaceFirst("\\s+$", "").replaceFirst("^\\s+", "");
						checkNullValueCells(remark, 2, rowDescription, cellStyleLeftTop);
						
						// Resets the row height when the number of characters exceeds the width of the cell "Remark"
						if (remark.length() > limitCharacterInWidth) {
							// The line number of the cell on column merge column D and C
							numberLineCellRemark = ExcelUtility.countLineNumber(remark, limitCharacterInWidth);
							// If the line number > 1
							if (numberLineCellRemark > 1) {
								// Number of rows to shift
								if (value.size() > 3) {
									numberRowShift = numberLineCellRemark - 1 - (value.size() - 3);
								} else {
									numberRowShift = numberLineCellRemark - 1;
								}
								// If numberRowShift > 0 perform  shiftRows
								if (numberRowShift > 0) {
									aSHEET.shiftRows(rowIdLastOfItem, rowIdLastOfItem + 20, numberRowShift);
									rowIdLastOfItem = rowIdLastOfItem + numberRowShift;
								}
								// Merge cell Remark
								aSHEET.addMergedRegion(new CellRangeAddress(rowsId + 2, rowsId + numberLineCellRemark +1, 2, 3));
							}
						} else {
							// Merge cell Remark
							aSHEET.addMergedRegion(new CellRangeAddress(rowsId + 2, rowsId + 2, 2, 3));
						}
						
					}
					// Set serialNo (cell [B-6])
					checkNullValueCells(value.get(i).getSerialNo(), 5, rows, cellStyleCenter);
					rowsId++;
					if (i == value.size() - 1) {
						rowsId = (rowsId <= rowStartFillData) ? rowStartFillData : rowsId;
						//rowsId++;
						// Redefined rowsId
						if (numberLineCellRemark > 1) {
							if ((rowsId - rowStartFillData) < (numberLineCellRemark - 1)) {
								rowsId = rowsId + (numberLineCellRemark - 1) - (rowsId - rowStartFillData);
								// Set the height to the last row of the cell remark
								float height = heightDefault - ((numberLineCellRemark - 2) * 2);
								height = (height > 0) ? height : 0;
								rows = CellUtil.getRow(rowsId - 1, aSHEET);
								rows.setHeightInPoints(height);
							}
						}
					}
				}
				rowsId++;
			}
		}
	}

	/**
	 * 
	 * @param value
	 * @param indexcolumn
	 * @param rows
	 * @param cellStyle
	 * @param alignCRL
	 */
	private void checkNullValueCells(String value, int indexcolumn, Row rows, CellStyle cellStyle) {
		if (value != null && !"".equals(value)) {
			CellUtil.createCell(rows, indexcolumn, value, cellStyle);
		}

	}

	/**
	 * Grouping listDownloadDeliveryOrderBean by sectionName
	 * 
	 * @param listDeliveryOrderBean
	 * @return Map<String, List<DownloadDeliveryOrderBean>>
	 */
	public Map<String, List<DownloadDeliveryOrderBean>> groupingDeliveryOrderBean(
			List<DownloadDeliveryOrderBean> listDeliveryOrderBean) {
		if (listDeliveryOrderBean != null){
			Map<String, List<DownloadDeliveryOrderBean>> temp = null;
			// Grouping List DeliveryOrderBean by CategoryCd
			temp = listDeliveryOrderBean.stream()
					.sorted(Comparator.comparing(DownloadDeliveryOrderBean::getSerialNo, Comparator.nullsLast(Comparator.naturalOrder())))
					.collect(Collectors.groupingBy(DownloadDeliveryOrderBean::getCategoryCd));
			// Sort by CategoryDisplayOrder
			Map<String, List<DownloadDeliveryOrderBean>> result = temp.entrySet().stream()
					.sorted((e1,e2)->e1.getValue().get(0).getCategoryDisplayOrder().compareTo(e2.getValue().get(0).getCategoryDisplayOrder()))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * @return the tempParams
	 */
	public Map<String, Object> getTempParams() {
		return tempParams;
	}

	/**
	 * @param tempParams
	 *            the tempParams to set
	 */
	public void setTempParams(Map<String, Object> tempParams) {
		this.tempParams = tempParams;
	}

}
