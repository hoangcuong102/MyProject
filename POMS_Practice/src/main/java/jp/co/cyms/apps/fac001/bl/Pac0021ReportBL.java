package jp.co.cyms.apps.fac001.bl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.stereotype.Service;

import jp.co.cyms.apps.fac001.bean.Pac0021Bean;
import jp.co.cyms.base.export.AbstractExport;
import jp.co.cyms.base.export.ExportBL;
import jp.co.cyms.base.export.SysParam;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.StringUtil;
/**
 * PC Order Progress Update / Inquiry Pac0021ReportBL
 * 
 * @author binhvh
 * @since 2018/01/12
 */
@Service
public class Pac0021ReportBL extends AbstractExport {
	/** Position start copy temp */
	public final static int indexStart = 3;
	
	/** Position end copy temp */
	public final static int indexEnd = 8;
	
	/** Month Cost */
	public final static String montCost = "Month Cost";
	
	/** One Time Cost */
	public final static String oneTimeCost = "One Time Cost";

	/** Index of sheet total summary */
	private final static int INDEX_TOTAL_SUMMARY = 0;
	
	/** Name of sheet first */
	public final static String NAME_SHEET = "Progress Result";
	public final static String NAME_SHEET_TEMP = "Progress Result Temp";
	/** Title message cancel by*/
	public final static String TITLE_MESSAGE_CANCEL = "Cancelled by ";
	/** Initialize temporary container. */
	private Map<String, Object> tempParams;

	/** Initialize collection contain result. */
	private Map<String, Object> rtnMap = new HashMap<String, Object>();
	
	/**
	 * Initialize constructor of class.
	 */
	public Pac0021ReportBL() {
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
		// If sheetName id Progress Result
	    if(StringUtils.equals(aSHEET.getSheetName(), NAME_SHEET)) {
	    	// Get company
	        String companyCd = (String) tempParams.get("companyCd");
	        // Get from
	        String from = (String) tempParams.get("from");
	        // Get to
	        String to = (String) tempParams.get("to");
	        // Get non delivery
	        String nonDelivery = (String) tempParams.get("nonDelivery");
	        // Get expiring Orders
	        String expiringOrders = (String) tempParams.get("expiringOrders");
	        Row rows = aSHEET.getRow(3);
	        // Fill value to sheet
	        rows.getCell(6).setCellValue(companyCd);
	        rows.getCell(12).setCellValue(from);
	        rows.getCell(20).setCellValue(to);
	        rows.getCell(31).setCellValue(nonDelivery);
	        rows.getCell(39).setCellValue(expiringOrders);
	    }
	}
	
	/**
	 * Put the data in the body part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 */
	@Override
	protected void fillBody(Workbook aWORKBOOK, Sheet aSHEET) throws Exception {
		aWORKBOOK.setSheetName(INDEX_TOTAL_SUMMARY, NAME_SHEET);
		fillDataProgressResult(aSHEET, aWORKBOOK);	
	}
	/**
	 * 親クラスの初期化メソッドを展開
	 */
	@Override
	protected void appInit() throws Exception {
		/* Retrieve the folder where the template is stored */
		ExportBL exportBL = new ExportBL();
		SysParam sysParam = exportBL.getSyspara();
		String nameFile = sysParam.getNameFileTempSac0021();

		this.templateFolder = sysParam.getTempDir();
		this.templateName = nameFile + Constant.XLSX_EXTENSION;
		this.templateFile = templateFolder + "/" + templateName;

		/* Delete working files for existing output */
		this.outputFolder = sysParam.getOuputDir();
		
		// File Name output
		// format : Progress_Result_Download_YYMMDDHHMMSSFFF
		String fileNameOutput = createFileNameReport();
		this.outputName = fileNameOutput;

		this.outputNewName = fileNameOutput;
		super.appInit();
	}
	
	/**
	 * create file name
	 * @return
	 */
	public String createFileNameReport(){
		String fileName = "Progress_Result_Download_";
		Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        fileName += format.format(date);
        return fileName;
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
			aSHEET.getRow(row).getCell(col)
					.setCellValue(Integer.parseInt(value));
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
			aSHEET.getRow(row).getCell(col)
					.setCellValue(Double.parseDouble(value));
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
		if (value != null){
			aSHEET.getRow(row).getCell(col).setCellValue(value);
		}
	}

	@Override
	public Map<String, Object> exportExecute(Map<String, Object> reqParams,
			HttpServletResponse response) throws Exception {

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
		rtnMap = super.downloadExcel(outputFile, outputNewName, response,
				rtnMap);
		// Get status the download.
		downSuccess = (Boolean) rtnMap.get(Constant.SUCESS);

		// Check status the download if false then throw exception.
		if (!downSuccess) {
			// logger.debug("---- Exception when downloading");
			rtnMap.put(Constant.SUCESS, false);
			return rtnMap;
		}
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
        String sheetTemp = aWORKBOOK.getSheetName(1);
        if (sheetTemp.equals(NAME_SHEET_TEMP)){
            aWORKBOOK.removeSheetAt(1);
        }
    }
	
	/**
     * Fill data for sheet Job Detail in Container Drayage Data File
     */
    @SuppressWarnings("unchecked")
    public void fillDataProgressResult(Sheet aSHEET, Workbook aWORKBOOK) {
    	// If sheetName id Progress Result
        if(StringUtils.equals(aSHEET.getSheetName(), NAME_SHEET)) {
        	// List orderDtl
            List<List<Pac0021Bean>> listOrderDtl = (List<List<Pac0021Bean>>)tempParams.get("listOrderDtl");
            // CellStyle
            CellStyle cellStyle = null;
            CellStyle cellStyleText = null;
            CellStyle cellStyleNumber = null;
            CellStyle cellStyleQty = null;
            int rowsId = 8;
            
            // Fill data
            for (int i=0; i<listOrderDtl.size(); i++){
            	// DELETE_FG
            	String deleteFg = listOrderDtl.get(i).get(0).getDeleteFg();
            	// Get cellStyle
            	if (deleteFg != null){
            		// If [ORDERS].DELETE_FG = 1
					if (listOrderDtl.get(i).get(0).getDeleteFg().equals("1")) {
						cellStyle = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(12)
								.getCell(CellReference.convertColStringToIndex("A")).getCellStyle();
						cellStyleText = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(12)
								.getCell(CellReference.convertColStringToIndex("X")).getCellStyle();
						cellStyleNumber = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(12)
								.getCell(CellReference.convertColStringToIndex("N")).getCellStyle();
						cellStyleQty = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(12)
								.getCell(CellReference.convertColStringToIndex("U")).getCellStyle();
					} else {
						cellStyle = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(8)
								.getCell(CellReference.convertColStringToIndex("A")).getCellStyle();
						cellStyleText = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(8)
								.getCell(CellReference.convertColStringToIndex("X")).getCellStyle();
						cellStyleNumber = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(8)
								.getCell(CellReference.convertColStringToIndex("N")).getCellStyle();
						cellStyleQty = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(8)
								.getCell(CellReference.convertColStringToIndex("U")).getCellStyle();
					}
				} else {
					cellStyle = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(8)
							.getCell(CellReference.convertColStringToIndex("A")).getCellStyle();
					cellStyleText = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(8)
							.getCell(CellReference.convertColStringToIndex("X")).getCellStyle();
					cellStyleNumber = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(8)
							.getCell(CellReference.convertColStringToIndex("N")).getCellStyle();
					cellStyleQty = getWorkbook().getSheet(NAME_SHEET_TEMP).getRow(8)
							.getCell(CellReference.convertColStringToIndex("U")).getCellStyle();
				}

            	int count = 0;
            	// Fill data orderDtl
                for (Pac0021Bean orderDtl : listOrderDtl.get(i)){
                	count++;
                	// Get row
                    Row rows = CellUtil.getRow(rowsId, aSHEET);
                    // Create cell
                    createCell(aSHEET, rowsId, cellStyle);
                    // Merger cell
                    mergeColumn(aSHEET, rowsId, orderDtl);
                    
                    // Set value to rows
                    // Order ID
                    setCellValue(aSHEET, rowsId, 0, orderDtl.getOrderId());
                    // Dept Code
                    setCellValue(aSHEET, rowsId, 4, orderDtl.getDeptCd());
                    // Atm(M)
                    rows.getCell(9).setCellStyle(cellStyleNumber);
                    setDoubleCellValue(aSHEET, rowsId, 9, orderDtl.getTtlMrc());
                    // Atm(O)
                    rows.getCell(13).setCellStyle(cellStyleNumber);
                    setDoubleCellValue(aSHEET, rowsId, 13, orderDtl.getTtlOtc());
                    // Category
                    setCellValue(aSHEET, rowsId, 17, orderDtl.getCategoryName());
                    // Qty
                    rows.getCell(24).setCellStyle(cellStyleQty);
                    setIntegerCellValue(aSHEET, rowsId, 24, orderDtl.getQuantity());
                    // Last Updated
                    rows.getCell(23).setCellStyle(cellStyleText);
                    setCellValue(aSHEET, rowsId, 27, orderDtl.getLastUpdated());
                    // Updated By
                    setCellValue(aSHEET, rowsId, 32, orderDtl.getUpdatedByUser());
                    // Check deleteFg != "1"
                    if (StringUtil.isEmpty(deleteFg) || !deleteFg.equals("1")){
	                    // Sankyu regist
	                    setCellValue(aSHEET, rowsId, 37, orderDtl.getSankyuRegistDt());
	                    // Sankyu order
	                    setCellValue(aSHEET, rowsId, 41, orderDtl.getSankyuOrderDt());
	                    // Sea request
	                    setCellValue(aSHEET, rowsId, 45, orderDtl.getSeaRequestDt());
	                    // KDDI accept
	                    setCellValue(aSHEET, rowsId, 49, orderDtl.getKddiAcceptDt());
	                    // KDDI order
	                    setCellValue(aSHEET, rowsId, 53, orderDtl.getKddiOrderDt());
	                    // KDDI receive
	                    setCellValue(aSHEET, rowsId, 57, orderDtl.getKddiReceiveDt());
	                    // KDDI Deliver QTY
	                    setIntegerCellValue(aSHEET, rowsId, 61, orderDtl.getKddiDeliverQtyDoNo());
	                    // KDDI Deliver Dt
	                    setCellValue(aSHEET, rowsId, 63, orderDtl.getKddiDeliverDtDoNo());
	                    // KDDI Lease
	                    setCellValue(aSHEET, rowsId, 67, orderDtl.getKddiLeaseDt());
                    }
                    // Merge row if category same
					if (count == listOrderDtl.get(i).size() || !listOrderDtl.get(i).get(count - 1).getCategoryCd()
							.equals(listOrderDtl.get(i).get(count).getCategoryCd())) {
						// If row number have category same > 1
						if (listOrderDtl.get(i).get(count - 1).getCountRecordDeliver() != null
								&& listOrderDtl.get(i).get(count - 1).getCountRecordDeliver() > 1) {
							// Merge row
							mergeRowCategorySame(aSHEET, rowsId, listOrderDtl.get(i).get(count - 1).getCountRecordDeliver() - 1);
						}
					}
	                // Next row
                    rowsId++;
                }
                // Check deleteFg = "1"
                if (!StringUtil.isEmpty(deleteFg) && deleteFg.equals("1")){
                	// Merge row
        	        mergeRowColumnOrderDeleted(aSHEET, rowsId - 1, listOrderDtl.get(i).size() - 1);
        	        // Set value to cell (value = Cancelled by USER
        	        setCellValue(aSHEET, rowsId - listOrderDtl.get(i).size(), 37, TITLE_MESSAGE_CANCEL + listOrderDtl.get(i).get(0).getUpdatedByUser());
                } else {
                	// Merge row
                	mergeRow(aSHEET, rowsId - 1, listOrderDtl.get(i).size() - 1);
                }
            }
        }    
    }
	
    /**
     * Merge row
     *
     * @param aSHEET
     * @param rowsId
     * @param totalRow
     */
    public void mergeRow(Sheet aSHEET, int rowsId, int totalRow){
    	// Column Sankyu Regist
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow,rowsId,37,40));
        // Column Sankyu Order
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow,rowsId,41,44));
        // Column Sea Request
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow,rowsId,45,48));
        // Column KDDI Accept
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow,rowsId,49,52));
        // Column KDDI Order
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow,rowsId,53,56));
        // Column KDDI Receive
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow,rowsId,57,60));
        // Column KDDI Lease
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow,rowsId,67,70));
    }
    
    /**
     * Merge column
     * 
     * @param aSHEET
     * @param rowsId
     * @param cellStyle
     */
    public void mergeColumn(Sheet aSHEET, int rowsId, Pac0021Bean orderDtl){
    	if (orderDtl.getCountRecordDeliver() == null || orderDtl.getCountRecordDeliver() <= 1){
	        // Column Order
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,0,3));
	        // Column Dept Code
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,4,8));
	        // Column Amt(M)
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,9,12));
	        // Column Amt(O)
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,13,16));
	        // Column Category
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,17,23));
	        // Column Qty
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,24,26));
	        // Column Last Updated
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,27,31));
	        // Column Update By
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,32,36));
    	}
        // Check deleteFg != "1"
        if (StringUtil.isEmpty(orderDtl.getDeleteFg()) || !orderDtl.getDeleteFg().equals("1")){
	        // Column KDDI Deliver Qty
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,61,62));
	        // Column KDDI Deliver Dt
	        aSHEET.addMergedRegion(new CellRangeAddress(rowsId,rowsId,63,66));
        }
    }
    
    /**
     * Merge Row Column 
     * If order deleted
     * 
     * @param aSHEET
     * @param rowsId
     * @param totalRow
     */
    public void mergeRowColumnOrderDeleted(Sheet aSHEET, int rowsId, int totalRow){
    	// Column Sankyu Regist - > Kddi Lease
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow,rowsId,37,70));
    }
    
    /**
     * Merge row if category same
     * 
     * @param aSHEET
     * @param rowsId
     * @param totalRow
     */
    public void mergeRowCategorySame(Sheet aSHEET, int rowsId, int totalRow){
    	// Column Order
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow, rowsId, 0, 3));
        // Column Dept Code
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow, rowsId, 4, 8));
        // Column Amt(M)
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow, rowsId, 9, 12));
        // Column Amt(O)
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow, rowsId, 13, 16));
        // Column Category
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow, rowsId, 17, 23));
        // Column Qty
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow, rowsId, 24, 26));
        // Column Last Updated
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow, rowsId, 27, 31));
        // Column Update By
        aSHEET.addMergedRegion(new CellRangeAddress(rowsId-totalRow, rowsId, 32, 36));
    }
    
    /**
     * Set cellStyle for all cell on row
     * @param aSHEET
     * @param rowsId
     * @param cellStyle
     */
    public void setCellStyleRow(Sheet aSHEET, int rowsId, CellStyle cellStyle){
        Row rows =  aSHEET.getRow(rowsId);
        for (int i=0; i<67; i++){
            rows.getCell(i).setCellStyle(cellStyle);
        }
    }
    
    /**
     * Create cell
     * 
     * @param aSHEET
     * @param rowsId
     * @param cellStyle
     */
    public void createCell(Sheet aSHEET, int rowsId, CellStyle cellStyle){
        Row rows =  aSHEET.getRow(rowsId);
        for (int i=0; i<71; i++){
            CellUtil.createCell(rows, i, null,cellStyle);
        }
    }
	
	public void initCellStyle(Workbook aWORKBOOK, Sheet aSHEET, CellStyle cellStyle, CellStyle cellStyleRight, CellStyle cellStyleCenter ) {
		Font font = aWORKBOOK.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		
		cellStyle = aSHEET.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		
		cellStyleRight = aSHEET.getWorkbook().createCellStyle();
		cellStyleRight.setFont(font);
		cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);
		
		cellStyleCenter = aSHEET.getWorkbook().createCellStyle();
		cellStyleCenter.setFont(font);
		cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);
	}
	
	public void removeRow(Sheet sheet, int rowIndex) {
	    int lastRowNum = sheet.getLastRowNum();
	    if (rowIndex >= 0 && rowIndex < lastRowNum) {
	        sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
	    }
	    if (rowIndex == lastRowNum) {
	        Row removingRow = sheet.getRow(rowIndex);
	        if (removingRow != null) {
	            sheet.removeRow(removingRow);
	        }
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
