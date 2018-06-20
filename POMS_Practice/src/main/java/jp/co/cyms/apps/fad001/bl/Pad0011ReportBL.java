package jp.co.cyms.apps.fad001.bl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.stereotype.Service;

import jp.co.cyms.apps.fad001.bean.DataKYearlyCellStyle;
import jp.co.cyms.apps.fad001.bean.ExcelPCLeaseInvoiceDataKYearlyBean;
import jp.co.cyms.apps.fad001.bean.ExcelPCLeaseInvoiceDataSBean;
import jp.co.cyms.apps.fad001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fad001.bean.Pad0011Bean;
import jp.co.cyms.base.export.AbstractExport;
import jp.co.cyms.base.export.ExcelUtility;
import jp.co.cyms.base.export.ExportBL;
import jp.co.cyms.base.export.SysParam;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.DateFormat;
import jp.co.cyms.common.DateUtil;
import jp.co.cyms.common.StringUtil;
/**
 * Data Download (Export Excel) BL
 * 
 * @author anhnt2
 * @since 2018/01/11
 */
@Service
public class Pad0011ReportBL extends AbstractExport {
	/** Position start copy temp */
	public final static int indexStart = 3;
	
	/** Position start copy temp for PC Lease Data */
	public final static int indexStartPCLeaseData = 4;
	
	/** Position end copy temp */
	public final static int indexEnd = 8;
	
	/** Month Cost */
	public final static String montCost = "Monthly Cost";
	
	/** One Time Cost */
	public final static String oneTimeCost = "One Time Cost";

	/** Index of sheet total summary */
	private final static int INDEX_TOTAL_SUMMARY = 0;
	
	/** Name of sheet first */
	public final static String NAME_SHEET_0 = "Total Summary";
	public final static String NAME_SHEET_1 = "PC Lease Data";

	public final static String DATAK_YEARLY_SHEET_NAME_TEMPLATE= "DataK_Yearly_Template";
	/** Cell style for month year selected */
	CellStyle selectMonthYearStyleType;
	
	/**  Cell style for Month Cost (K) */
	CellStyle blueCellStyleType;
	
	CellStyle orderCellStyle;
	
	CellStyle categoryAddonCellStyle;
	
	CellStyle orangeCellStyle;
	
	CellStyle totalBlueCellStyleType;
	
	CellStyle numberBlueCellStyleType;
	
	CellStyle numberDeliverAmountBottomCellStyle;
	
	CellStyle numberDecimal2CellStyleType;
	
	/**  Cell style for Month Cost (S) */
	// Monthly Cost Style
	CellStyle monthlyCostCellStyle;
	// Item style
	CellStyle itemNoColorCellStyle;
	// total style left
	CellStyle totalCCellStyle;
	// total style right
	CellStyle totalDCellStyle;
	// month left style
	CellStyle monthLeftCellStyle;
	// month right style
	CellStyle monthRightCellStyle;
	// month last style
	CellStyle monthLastCellStyle;
	// total left style
	CellStyle totalLeftCellStyle;
	// total right style
	CellStyle totalRightCellStyle;
	// total last style
	CellStyle totalLastCellStyle;
	// One time cost style
	CellStyle oneTimeCostLeftCellStyle;
	// One time cost style
	CellStyle oneTimeCostRightCellStyle;
	// One time cost month left style
	CellStyle oneTimeCostMonthLeftCellStyle;
	// One time cost month right style
	CellStyle oneTimeCostMonthRightCellStyle;
	// One time cost month last style
	CellStyle oneTimeCostMonthLastCellStyle;
	
	/**  Cell style for month cost */
	CellStyle greenCellStyleType;
	
	CellStyle orderGreenCellStyleType;
	
	CellStyle pinkCellStyleType;
	
	CellStyle purpleCellStyleType;
	
	CellStyle totalgreencellStyleType;

	/** Initialize temporary container. */
	private Map<String, Object> tempParams;

	/** Initialize collection contain result. */
	private Map<String, Object> rtnMap = new HashMap<String, Object>();

	/**  PC Lease Invoice Data Download (K) */
	private boolean outputLeaseInvoiceDataK = false;
	
	/**  PC Lease Invoice Data Download (K) Yearly */
	private boolean outputLeaseInvoiceDataKYearly = false;
	
	/**  PC Lease Invoice Data Download (S) */
	private boolean outputLeaseInvoiceDataS = false;
	
	/**  PC Lease Data Download */
	private boolean outputLeaseData = false;
	
	/**  Selected Month And Year */
	private String monthYearSelected;
	
	/**  Selected Year */
	private String yearSelected;
	
	public static final Logger LOG = Logger.getLogger(Pad0011ReportBL.class);
	
	/**
	 * Initialize constructor of class.
	 */
	public Pad0011ReportBL() {
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
	}
	
	/**
	 * Put the data in the body part
	 *
	 * @param aWORKBOOK
	 * @param aSHEET
	 */
	@Override
	protected void fillBody(Workbook aWORKBOOK, Sheet aSHEET) throws Exception {
		// PC Lease Monthly Summary
		if (outputLeaseInvoiceDataK) {
			aWORKBOOK.setSheetName(INDEX_TOTAL_SUMMARY, NAME_SHEET_0);
			fillDataLeaseInvoiceDataK(aSHEET, aWORKBOOK);
		}
		
		// PC Lease Invoice Data K Download (Yearly)
		if (outputLeaseInvoiceDataKYearly) {
			if (!aSHEET.getSheetName().equals(DATAK_YEARLY_SHEET_NAME_TEMPLATE)) {
				aWORKBOOK.setSheetName(INDEX_TOTAL_SUMMARY, NAME_SHEET_0);
				fillDataLeaseInvoiceDataKYearly(aSHEET, aWORKBOOK);
			}
		}
		
		// PC Lease Invoice Data Download (S)
		if (outputLeaseInvoiceDataS) {
			aWORKBOOK.setSheetName(INDEX_TOTAL_SUMMARY, NAME_SHEET_0);
			fillDataLeaseInvoiceDataS(aSHEET, aWORKBOOK);	
		}
		
		// PC Lease Data Download
		if (outputLeaseData) {
			aWORKBOOK.setSheetName(INDEX_TOTAL_SUMMARY, NAME_SHEET_1);
			fillDataLeaseData(aSHEET, aWORKBOOK);	
		}
	}
	/**
	 * è¦ªã‚¯ãƒ©ã‚¹ã�®åˆ�æœŸåŒ–ãƒ¡ã‚½ãƒƒãƒ‰ã‚’å±•é–‹
	 */
	@Override
	protected void appInit() throws Exception {
		/* Retrieve the folder where the template is stored */
		ExportBL exportBL = new ExportBL();
		SysParam sysParam = exportBL.getSyspara();
		String nameFile = sysParam.getNameFile0();
		if (outputLeaseData) {
			nameFile = sysParam.getNameFile1();
		}
		if (outputLeaseInvoiceDataS) {
			nameFile = sysParam.getNameFile2();
		}
		if (outputLeaseInvoiceDataKYearly) {
			nameFile = sysParam.getNameFile3();
		}
		this.templateFolder = sysParam.getTempDir();
		this.templateName = nameFile + Constant.XLSX_EXTENSION;
		this.templateFile = templateFolder + "/" + templateName;

		/* Delete working files for existing output */
		this.outputFolder = sysParam.getOuputDir();
		this.outputName = createFileNameReport();

		this.outputNewName = createFileNameReport();
		super.appInit();
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

	@Override
	public Map<String, Object> exportExecute(Map<String, Object> reqParams,
			HttpServletResponse response) throws Exception {

		monthYearSelected = (String) reqParams.get("monthYearSelected");
		
		yearSelected = (String) reqParams.get("yearSelected");
		/* Assign Map tempParams = reqParams. */
		tempParams = reqParams;

		/* Set reportID */
		reportID = Constant.REPORT_ID_DATA_DOWNLOAD;
		
		/* Set user id is userID from reqParams. */
		userID = (String) reqParams.get(Constant.UID);
		
		if (reqParams.get("outputLeaseInvoiceDataK") != null) {
			outputLeaseInvoiceDataK = (boolean) reqParams.get("outputLeaseInvoiceDataK");
		}
		
		if (reqParams.get("outputLeaseInvoiceDataKYearly") != null) {
			outputLeaseInvoiceDataKYearly = (boolean) reqParams.get("outputLeaseInvoiceDataKYearly");
		}
		
		if (reqParams.get("outputLeaseInvoiceDataS") != null) {
			outputLeaseInvoiceDataS = (boolean) reqParams.get("outputLeaseInvoiceDataS");
		}
		
		if (reqParams.get("outputLeaseData") != null) {
			outputLeaseData = (boolean) reqParams.get("outputLeaseData");
		}
		
		// set add sheet
		addSheet = false;

		/* ãƒ—ãƒ­ãƒ•ã‚¡ã‚¤ãƒ«åˆ�æœŸåŒ– */
		init();
		/* ãƒ¯ãƒ¼ã‚¯ãƒ–ãƒƒã‚¯åˆ�æœŸåŒ– */
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

	public void removeSheet(Workbook aWORKBOOK) throws Exception {
		if (outputLeaseInvoiceDataKYearly){
			Sheet sheet = aWORKBOOK.getSheet("DataK_Yearly_Template");
			if (sheet != null && aWORKBOOK.getSheetIndex(sheet) > -1){
				aWORKBOOK.removeSheetAt(aWORKBOOK.getSheetIndex(sheet));
			}
		}
    }
	
	/**
	 * Fill data K for sheet total summary
	 */
	@SuppressWarnings("unchecked")
	public void fillDataLeaseInvoiceDataK(Sheet aSHEET, Workbook aWORKBOOK) {
		// Get style of cell month and year
		setSelectMonthYearStyleType(getWorkbook().getSheet("Total Summary").getRow(1).getCell(CellReference.convertColStringToIndex("F")).getCellStyle());
		// Get style of cell for month cost
		setBlueCellStyleType(getWorkbook().getSheet("Total Summary").getRow(3).getCell(CellReference.convertColStringToIndex("A")).getCellStyle());
		setOrderCellStyle(getWorkbook().getSheet("Total Summary").getRow(3).getCell(CellReference.convertColStringToIndex("B")).getCellStyle());
		setCategoryAddonCellStyle(getWorkbook().getSheet("Total Summary").getRow(3).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		setOrangeCellStyle(getWorkbook().getSheet("Total Summary").getRow(4).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		setTotalBlueCellStyleType(getWorkbook().getSheet("Total Summary").getRow(5).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		setNumberBlueCellStyleType(getWorkbook().getSheet("Total Summary").getRow(5).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		setNumberDeliverAmountBottomCellStyle(getWorkbook().getSheet("Total Summary").getRow(5).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		setNumberDecimal2CellStyleType(getWorkbook().getSheet("Total Summary").getRow(4).getCell(CellReference.convertColStringToIndex("G")).getCellStyle());
		// Get style of cell for one time cost
		setGreenCellStyleType(getWorkbook().getSheet("Total Summary").getRow(6).getCell(CellReference.convertColStringToIndex("A")).getCellStyle());
		setOrderGreenCellStyleType(getWorkbook().getSheet("Total Summary").getRow(6).getCell(CellReference.convertColStringToIndex("B")).getCellStyle());
		setPinkCellStyleType(getWorkbook().getSheet("Total Summary").getRow(6).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		setPurpleCellStyleType(getWorkbook().getSheet("Total Summary").getRow(6).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		setTotalgreencellStyleType(getWorkbook().getSheet("Total Summary").getRow(8).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		List<Pad0011Bean> getOrdersByMonthlyCost = (List<Pad0011Bean>)tempParams.get("getOrdersByMonthlyCost");
		if (getOrdersByMonthlyCost == null || getOrdersByMonthlyCost.size() == 0) {
			ExcelUtility.rowRemove(aSHEET, indexStart, indexEnd);
			return;
		}
		List<Pad0011Bean> getOrdersByOneTimeCost = (List<Pad0011Bean>)tempParams.get("getOrdersByOneTimeCost");
		if (getOrdersByOneTimeCost == null ||getOrdersByOneTimeCost.size() == 0) {
			ExcelUtility.rowRemove(aSHEET, indexStart, indexEnd);
			getOrdersByOneTimeCost = new ArrayList<Pad0011Bean>();
			//return;
		}
		
		/* [START] Process for sheet "Total Summary" */
		
		// Group by company (Month cost)
		Map<String, List<Pad0011Bean>> getOrdersByMonthlyCostGroupByCompany =
				getOrdersByMonthlyCost.stream().sorted(Comparator.comparing(Pad0011Bean::getOrderId, Comparator.nullsLast(Comparator.naturalOrder())))
				.collect(Collectors.groupingBy(Pad0011Bean::getCompanyCd));
		// Sort by key
		getOrdersByMonthlyCostGroupByCompany = new TreeMap<String, List<Pad0011Bean>>(getOrdersByMonthlyCostGroupByCompany);
		
		// Group by company (One time cost)
		Map<String, List<Pad0011Bean>> getOrdersByOneTimeCostGroupByCompany =
				getOrdersByOneTimeCost.stream().sorted(Comparator.comparing(Pad0011Bean::getOrderId, Comparator.nullsLast(Comparator.naturalOrder())))
				.collect(Collectors.groupingBy(Pad0011Bean::getCompanyCd));
		// Sort by key
		getOrdersByOneTimeCostGroupByCompany = new TreeMap<String, List<Pad0011Bean>>(getOrdersByOneTimeCostGroupByCompany);
		
		// Position start fill data

		int rowIndexStartParmater = indexStart;
		int rowIndexStartResult = 0;
		boolean typeCost = true;
		Font font = aWORKBOOK.createFont();
		font.setFontName("Tahoma");
		font.setBold(true);
		font.setFontHeightInPoints((short) 10);
		CellStyle companyCellStyle = aSHEET.getWorkbook().createCellStyle();
		companyCellStyle.setFont(font);
		
		// Fill data for Compnay Name
		// No â€œCOMPANY NAMEâ€� for â€œTotal Summaryâ€œ sheet *Updated 5/Feb -> set value null for compnay name
		Row rows = CellUtil.getRow(0, aSHEET);
		CellUtil.createCell(rows, 0, "", companyCellStyle);
		// Fill data for date time selected
		rows = CellUtil.getRow(1, aSHEET);
		CellUtil.createCell(rows, 4, monthYearSelected , null);
		
		for(Entry<String, List<Pad0011Bean>> enMonthCost : getOrdersByMonthlyCostGroupByCompany.entrySet()) {
			typeCost = true;
			// First time don't copy template header
			if (rowIndexStartParmater != indexStart) {
				rowIndexStartResult = loadListDataToExcelSumTotal(aSHEET, aWORKBOOK, enMonthCost.getValue(), rowIndexStartParmater, typeCost);
				rowIndexStartParmater = rowIndexStartResult + 1;
			}
			else {
				rowIndexStartResult = loadListDataToExcelSumTotal(aSHEET, aWORKBOOK, enMonthCost.getValue(), rowIndexStartParmater, typeCost);
				rowIndexStartParmater = rowIndexStartResult + 1;
			}
		}
		// Megre column type of Monthly Cost
		loadMegreType(aWORKBOOK, aSHEET, indexStart, rowIndexStartResult, montCost, blueCellStyleType);
		// Get last row of Monthy Cost
		int rowIndexStartForOneTimeCost = rowIndexStartResult + 1;
		for(Entry<String, List<Pad0011Bean>> enTimeOneCost : getOrdersByOneTimeCostGroupByCompany.entrySet()) {
			typeCost = false;
			rowIndexStartResult = loadListDataToExcelSumTotal(aSHEET, aWORKBOOK, enTimeOneCost.getValue(), rowIndexStartResult + 1, typeCost);
		}
		// Megre column type of One Time Cost
		// Style for type cell One Time Cost
		CellStyle cellStyleOneTimeCost = checkTypeCost(typeCost, getBlueCellStyleType(), getGreenCellStyleType());
		cellStyleOneTimeCost.setBorderTop(CellStyle.BORDER_MEDIUM);
		cellStyleOneTimeCost.setBorderRight(CellStyle.BORDER_MEDIUM);
		cellStyleOneTimeCost.setBorderLeft(CellStyle.BORDER_MEDIUM);
		cellStyleOneTimeCost.setBorderBottom(CellStyle.BORDER_MEDIUM);
		if (rowIndexStartForOneTimeCost < rowIndexStartResult) {
			loadMegreType(aWORKBOOK, aSHEET, rowIndexStartForOneTimeCost, rowIndexStartResult, oneTimeCost, cellStyleOneTimeCost);
		}
		
		/* [END] Process for sheet "Total Summary" */
		
		/* [START] Process for sheet 2->n */
		
		typeCost = true;

		// Group by company, dept (Month cost)
		Map<String, Map<String, List<Pad0011Bean>>> getOrdersByMonthlyCostGroupByCompanyDept =
				getOrdersByMonthlyCost.stream().sorted(Comparator.comparing(Pad0011Bean::getDeptCd, Comparator.nullsLast(Comparator.naturalOrder())))
				.collect(Collectors.groupingBy(Pad0011Bean::getCompanyCd, Collectors.groupingBy(Pad0011Bean::getDeptCd)));
		// Sort by key
		getOrdersByMonthlyCostGroupByCompanyDept = new TreeMap<String, Map<String, List<Pad0011Bean>>>(getOrdersByMonthlyCostGroupByCompanyDept);
		
		Map<String, Map<String, List<Pad0011Bean>>> getOrdersByOneTimeCostGroupByCompanyDept =
				getOrdersByOneTimeCost.stream().sorted(Comparator.comparing(Pad0011Bean::getDeptCd, Comparator.nullsLast(Comparator.naturalOrder())))
				.collect(Collectors.groupingBy(Pad0011Bean::getCompanyCd, Collectors.groupingBy(Pad0011Bean::getDeptCd)));
		
		for(Entry<String, Map<String, List<Pad0011Bean>>> enMonthCost : getOrdersByMonthlyCostGroupByCompanyDept.entrySet()){
			rowIndexStartResult = 1;
			rowIndexStartParmater = indexStart + 1;
			// Create sheet with name of companyCd
			Sheet companySheet = xWORKBOOK.createSheet(enMonthCost.getKey());
			for(Entry<String, List<Pad0011Bean>> enSubMonthCost : enMonthCost.getValue().entrySet()) {
				companyCellStyle = companySheet.getWorkbook().createCellStyle();
				companyCellStyle.setFont(font);
				ExcelUtility.rowCopy(getWorkbook().getSheet("Total Summary"), companySheet, 0, 2, rowIndexStartResult);
				Row rowsCompany = CellUtil.getRow(rowIndexStartResult -1, companySheet);
				Row rowsDept = CellUtil.getRow(rowIndexStartResult, companySheet);
				CellUtil.createCell(rowsCompany, 0, "Company Name: " + enSubMonthCost.getValue().get(0).getCompanyName(), companyCellStyle);
				CellUtil.createCell(rowsDept, 0, "Department Name: " + enSubMonthCost.getValue().get(0).getDeptName(), companyCellStyle);
				typeCost = true;
				rowIndexStartResult = loadListDataToExcel(companySheet, aWORKBOOK, enSubMonthCost.getValue(), rowIndexStartParmater, typeCost);
				for(Entry<String, Map<String, List<Pad0011Bean>>> enTimeOneCost : getOrdersByOneTimeCostGroupByCompanyDept.entrySet()) {
					if (enMonthCost.getKey().equals(enTimeOneCost.getKey())) {
						for(Entry<String, List<Pad0011Bean>> enSubTimeOneCost : enTimeOneCost.getValue().entrySet()) {
							if (enSubMonthCost.getKey().equals(enSubTimeOneCost.getKey())) {
								typeCost = false;
								rowIndexStartResult = loadListDataToExcel(companySheet, aWORKBOOK, enSubTimeOneCost.getValue(), rowIndexStartResult + 1, typeCost);
								//rowIndexStartResult = rowIndexStartResult + 3;
								//rowIndexStartParmater = rowIndexStartResult + 3;
							}
						}
					}
				}
				rowIndexStartResult = rowIndexStartResult + 3;
				rowIndexStartParmater = rowIndexStartResult + 3;
			}
		}
		/* [END] Process for sheet 2->n */
		
//		int rowIndexStartResult = loadListDataToExcel(aSHEET, aWORKBOOK, getOrdersByMonthlyCost, rowIndexStartParmater);
//		loadListDataToExcel(aSHEET, aWORKBOOK, getOrdersByOneTimeCost, rowIndexStartResult + 1);
	}
	
	/**
	 * Fill data S for sheet total summary
	 */
	@SuppressWarnings("unchecked")
	public void fillDataLeaseInvoiceDataS(Sheet aSHEET, Workbook aWORKBOOK) {
		CellStyle companyCellStyle = aSHEET.getRow(2).getCell(CellReference.convertColStringToIndex("C")).getCellStyle();
		
		// Style vertical bold blue
		setMonthlyCostCellStyle(aSHEET.getRow(4).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		// Style blue bold blue
		setItemNoColorCellStyle(aSHEET.getRow(4).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		// Style no color border left
		setMonthLeftCellStyle(aSHEET.getRow(5).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		// Style no color border right
		setMonthRightCellStyle(aSHEET.getRow(5).getCell(CellReference.convertColStringToIndex("F")).getCellStyle());
		// Style no color border right last
		setMonthLastCellStyle(aSHEET.getRow(5).getCell(CellReference.convertColStringToIndex("AB")).getCellStyle());
		// Style total blue left
		setTotalCCellStyle(aSHEET.getRow(8).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		// Style total blue right
		setTotalDCellStyle(aSHEET.getRow(8).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		// Style blue border left
		setTotalLeftCellStyle(aSHEET.getRow(8).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		// Style blue border right
		setTotalRightCellStyle(aSHEET.getRow(8).getCell(CellReference.convertColStringToIndex("F")).getCellStyle());
		// Style blue border right last
		setTotalLastCellStyle(aSHEET.getRow(8).getCell(CellReference.convertColStringToIndex("AB")).getCellStyle());
		// Style green border left
		setOneTimeCostLeftCellStyle(aSHEET.getRow(9).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		// Style green border right
		setOneTimeCostRightCellStyle(aSHEET.getRow(9).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		// Style month green border left
		setOneTimeCostMonthLeftCellStyle(aSHEET.getRow(9).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		// Style month green border right
		setOneTimeCostMonthRightCellStyle(aSHEET.getRow(9).getCell(CellReference.convertColStringToIndex("F")).getCellStyle());
		// Style month green border right last
		setOneTimeCostMonthLastCellStyle(aSHEET.getRow(9).getCell(CellReference.convertColStringToIndex("AB")).getCellStyle());
		// Row start template
		int rowIndexStartParmater = 4;
		// Row end template
		int rowIndexEndParmater = 9;
		
		/* [START] Process for sheet 1 */
		// Get list data for sheet total sum

		// Longnd 19_may
		List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummaryMRC = (List<ExcelPCLeaseInvoiceDataSBean>)tempParams.get("getLeaseInvoiceDataSTotalSummaryMRC");
		ExcelUtility.rowRemove(aSHEET, rowIndexStartParmater, rowIndexEndParmater);
		Map<String, List<ExcelPCLeaseInvoiceDataSBean>> mapDataMRC = new HashMap<String, List<ExcelPCLeaseInvoiceDataSBean>>();
		if (getLeaseInvoiceDataSTotalSummaryMRC != null) {
			mapDataMRC = getLeaseInvoiceDataSTotalSummaryMRC.stream().collect(Collectors.groupingBy(ExcelPCLeaseInvoiceDataSBean::getCompanyCd));
		}
		
		List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummaryOTC = (List<ExcelPCLeaseInvoiceDataSBean>)tempParams.get("getLeaseInvoiceDataSTotalSummaryOTC");
		Map<String, List<ExcelPCLeaseInvoiceDataSBean>> mapDataOTC = new HashMap<String, List<ExcelPCLeaseInvoiceDataSBean>>();
		if (getLeaseInvoiceDataSTotalSummaryMRC != null) {
			mapDataOTC = getLeaseInvoiceDataSTotalSummaryOTC.stream().collect(Collectors.groupingBy(ExcelPCLeaseInvoiceDataSBean::getCompanyCd));
		}
		rowIndexStartParmater = 4;
				
		mapDataMRC = mapDataMRC.entrySet().stream().sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		for (Map.Entry<String, List<ExcelPCLeaseInvoiceDataSBean>> r: mapDataMRC.entrySet()) {
			LOG.info(r.getKey() + "---sort");

			for (Map.Entry<String, List<ExcelPCLeaseInvoiceDataSBean>> r2: mapDataOTC.entrySet()) {	
				if (r.getKey().equals(r2.getKey())) {
					r.getValue().addAll(r2.getValue());
					break;
				}
			}
			if (rowIndexStartParmater != 4) {
				ExcelUtility.rowCopy(aSHEET, aSHEET, 3, 3, rowIndexStartParmater - 1);
			}
			Row rowsCompany = CellUtil.getRow(rowIndexStartParmater -2, aSHEET);
			CellUtil.createCell(rowsCompany, CellReference.convertColStringToIndex("C"), "Company Name: " + r.getValue().get(0).getCompanyName(), companyCellStyle);
			
			rowIndexStartParmater =  loadListDataKToExcelSumTotal(aSHEET, aWORKBOOK, r.getValue(), rowIndexStartParmater);
			rowIndexStartParmater += 4;			
		}
		
		/* [END] Process for sheet 1 */

		/* [START] Process for sheet 2->n */
		// Group by company, dept (Month cost)
		List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSCompanyCode = (List<ExcelPCLeaseInvoiceDataSBean>)tempParams.get("getLeaseInvoiceDataSCompanyCode");
		if (getLeaseInvoiceDataSCompanyCode != null) {
			Map<String, Map<String, List<ExcelPCLeaseInvoiceDataSBean>>> getLeaseInvoiceDataSCompanyCodeGroupByCompanyDept =
					getLeaseInvoiceDataSCompanyCode.stream().sorted(Comparator.comparing(ExcelPCLeaseInvoiceDataSBean::getDeptCd, Comparator.nullsLast(Comparator.naturalOrder())))
					.collect(Collectors.groupingBy(ExcelPCLeaseInvoiceDataSBean::getCompanyCd, Collectors.groupingBy(ExcelPCLeaseInvoiceDataSBean::getDeptCd)));
			// Sort by key
			getLeaseInvoiceDataSCompanyCodeGroupByCompanyDept = new TreeMap<String, Map<String, List<ExcelPCLeaseInvoiceDataSBean>>>(getLeaseInvoiceDataSCompanyCodeGroupByCompanyDept);
			getLeaseInvoiceDataSCompanyCodeGroupByCompanyDept = getLeaseInvoiceDataSCompanyCodeGroupByCompanyDept.entrySet().stream().sorted(Map.Entry.comparingByKey())
			        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
			                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			
			for(Entry<String, Map<String, List<ExcelPCLeaseInvoiceDataSBean>>> enMonthCost : getLeaseInvoiceDataSCompanyCodeGroupByCompanyDept.entrySet()){
				LOG.info(enMonthCost.getKey() + " --- sort 2-n companyCd");
				// Reset Row start template
				rowIndexStartParmater = 4;
				int rowIndexStartResult = 4;
				int rowIndexStartDept = 0;
				// Create sheet with name of companyCd
				Sheet companySheet = xWORKBOOK.createSheet(enMonthCost.getKey());
				companySheet.setDisplayGridlines(false);
				Map<String, List<ExcelPCLeaseInvoiceDataSBean>> dataSorted = enMonthCost.getValue().entrySet().stream().sorted(Map.Entry.comparingByKey())
				        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
				for(Entry<String, List<ExcelPCLeaseInvoiceDataSBean>> enSubMonthCost : dataSorted.entrySet()) {
					LOG.info(enSubMonthCost.getKey() + " --- sort Dept");
					ExcelUtility.rowCopy(getWorkbook().getSheet("Total Summary"), companySheet, 0, 3, rowIndexStartDept);
					Row rowsCompany = CellUtil.getRow(rowIndexStartResult -3, companySheet);
					Row rowsDept = CellUtil.getRow(rowIndexStartResult -2, companySheet);
					CellUtil.createCell(rowsCompany, CellReference.convertColStringToIndex("C"), "Company Name: " + enSubMonthCost.getValue().get(0).getCompanyName(), companyCellStyle);
					CellUtil.createCell(rowsDept, CellReference.convertColStringToIndex("C"), "Department Name: " + enSubMonthCost.getValue().get(0).getDeptName(), companyCellStyle);
					loadListDataKToExcelSumTotal(companySheet, aWORKBOOK, enSubMonthCost.getValue(), rowIndexStartParmater);
					// Check last element have CategoryName != One Time Cost
					if  (!enSubMonthCost.getValue().get(enSubMonthCost.getValue().size() - 1).getCategoryName().equals("One Time Cost")) {
						rowIndexStartDept += 6 + enSubMonthCost.getValue().size();
					} else {
						rowIndexStartDept += 5 + enSubMonthCost.getValue().size();
					}
					rowIndexStartParmater = 4 + rowIndexStartDept;
					rowIndexStartResult = rowIndexStartParmater;
				}companySheet.setColumnWidth(0, companySheet.getColumnWidth(1));
			}
		}
		/* [END] Process for sheet 2->n */
	}
	
	/**
	 * Fill data K yearly for sheet
	 * 
	 * @param aSHEET
	 * @param aWORKBOOK
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void fillDataLeaseInvoiceDataKYearly(Sheet aSHEET, Workbook aWORKBOOK) throws Exception {
		// Cell Style for aWORKBOOK
		DataKYearlyCellStyle cellStyle = new DataKYearlyCellStyle();
		// Get Cell Style
		getDataKYearlyCellStyle(aWORKBOOK, cellStyle);
		
		// Row start template
		int rowIndexStartParmater = 4;
		
		// create 12 month by year for sheet (Jan - Dec)
		loadMonthYearDataK(aSHEET);
		
		// Get Column Width
		// Column A
		int widthColA = aSHEET.getColumnWidth(0);
		// Column B
		int widthColB = aSHEET.getColumnWidth(1);
		
		/* [START] Process for sheet 1 (Total Summary)*/
		// Get list data for sheet total summary
		List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummary = (List<ExcelPCLeaseInvoiceDataKYearlyBean>) tempParams
				.get("getLeaseInvoiceDataKYearlyTotalSummary");
		if (getLeaseInvoiceDataKYearlyTotalSummary != null) {
			// Fill to excel template
			loadListDataKYearlyToExcel(aSHEET, aWORKBOOK, getLeaseInvoiceDataKYearlyTotalSummary, rowIndexStartParmater, cellStyle);
		}
		// Set Column Width Fit Content
		// Column Category
		aSHEET.autoSizeColumn(CellReference.convertColStringToIndex("D"), true);
		// Column Item
		aSHEET.autoSizeColumn(CellReference.convertColStringToIndex("E"), true);
		// Set Zoom 90% For Sheet
		aSHEET.setZoom(9, 10);
		/* [END] Process for sheet 1 */
		
		/* [START] Process for sheet 2->n */
		// Get list data for sheet 2->n
		List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyCompanyCode = (List<ExcelPCLeaseInvoiceDataKYearlyBean>) tempParams
				.get("getLeaseInvoiceDataKYearlyCompanyCode");
		if (getLeaseInvoiceDataKYearlyCompanyCode != null) {
			// Group by company, dept
			Map<String, Map<String, List<ExcelPCLeaseInvoiceDataKYearlyBean>>> dataKYearlyCompanyCodeGroupByCompanyDept =
					getLeaseInvoiceDataKYearlyCompanyCode.stream().sorted(Comparator.comparing(ExcelPCLeaseInvoiceDataKYearlyBean::getDeptCd, Comparator.nullsLast(Comparator.naturalOrder())))
					.collect(Collectors.groupingBy(ExcelPCLeaseInvoiceDataKYearlyBean::getCompanyCd, Collectors.groupingBy(ExcelPCLeaseInvoiceDataKYearlyBean::getDeptCd)));
			
			// Sort by key
			dataKYearlyCompanyCodeGroupByCompanyDept = new TreeMap<String, Map<String, List<ExcelPCLeaseInvoiceDataKYearlyBean>>>(dataKYearlyCompanyCodeGroupByCompanyDept);
			
			// Fill Data To Excel
			for(Entry<String, Map<String, List<ExcelPCLeaseInvoiceDataKYearlyBean>>> dataKYearlyByCompanyCode : dataKYearlyCompanyCodeGroupByCompanyDept.entrySet()){
				// Reset Row start template
				rowIndexStartParmater = 4;
				int rowIndexStartResult = 4;
				int rowIndexStartDept = 0;
				// Create sheet with name of companyCd
				Sheet companySheet = xWORKBOOK.createSheet(dataKYearlyByCompanyCode.getKey());
				
				// Set Column Width
				// Column A
				companySheet.setColumnWidth(0, widthColA);
				// Column B
				companySheet.setColumnWidth(1, widthColB);
				
				// Fill data to companySheet
				for(Entry<String, List<ExcelPCLeaseInvoiceDataKYearlyBean>> dataKYearlyByDept : dataKYearlyByCompanyCode.getValue().entrySet()) {
					// If Data of company is not null or empty
					if (dataKYearlyByDept.getValue() != null && dataKYearlyByDept.getValue().size() > 0) {
						// Copy 4 first rows from Total summary sheet to company sheet
						ExcelUtility.rowCopy(getWorkbook().getSheet(NAME_SHEET_0), companySheet, 0, 3, rowIndexStartDept);
						
						// Fill info Company and Department 
						Row rowsCompany = CellUtil.getRow(rowIndexStartResult -4, companySheet);
						Row rowsDept = CellUtil.getRow(rowIndexStartResult -3, companySheet);
						CellUtil.createCell(rowsCompany, CellReference.convertColStringToIndex("C"), "Company Name: " + dataKYearlyByDept.getValue().get(0).getCompanyName(), cellStyle.getCompanyCellStyle());
						CellUtil.createCell(rowsDept, CellReference.convertColStringToIndex("C"), "Department Name: " + dataKYearlyByDept.getValue().get(0).getDeptName(), cellStyle.getCompanyCellStyle());
						
						// Fill to excel template
						loadListDataKYearlyToExcel(companySheet, aWORKBOOK, dataKYearlyByDept.getValue(), rowIndexStartParmater, cellStyle);
						
						// Check last element have CategoryName != One Time Cost
						// Reset Row Index  when Fill Department Data
						if  (!dataKYearlyByDept.getValue().get(dataKYearlyByDept.getValue().size() - 1).getCostType().equals("One Time Cost")) {
							rowIndexStartDept += 7 + dataKYearlyByDept.getValue().size();
						} else {
							rowIndexStartDept += 8 + dataKYearlyByDept.getValue().size();
						}
						
						// Reset Row Index fill data 
						rowIndexStartParmater = 4 + rowIndexStartDept;
						rowIndexStartResult = rowIndexStartParmater;
					}
				}
				// Set Zoom 90% For Company Sheet
				companySheet.setZoom(9, 10);
			}
		}
		/* [END] Process for sheet 2->n */
		
		// Set Visible Total Summary
		xWORKBOOK.setFirstVisibleTab(0);
	}
	
	/**
	 * Fill data for sheet PC lease data (PC Lease Data Download)
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public void fillDataLeaseData(Sheet aSHEET, Workbook aWORKBOOK) throws ParseException {
		List<OrderLeaseBean> getPCLeaseData = (List<OrderLeaseBean>)tempParams.get("getPCLeaseData");
		if (getPCLeaseData == null || getPCLeaseData.size() == 0) {
			ExcelUtility.rowRemove(aSHEET, indexStartPCLeaseData, indexEnd);
			return;
		}
		Font font = aWORKBOOK.createFont();
		font.setFontName("Tahoma");
		font.setFontHeightInPoints((short) 10);
		CellStyle itemCellStyle = aSHEET.getWorkbook().createCellStyle();
		itemCellStyle.setFont(font);

		CellStyle itemNumberCellStyle = aSHEET.getWorkbook().createCellStyle();
		itemNumberCellStyle.setFont(font);
		itemNumberCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		itemNumberCellStyle.setDataFormat((short)4);
		
		CellStyle itemRightCellStyle = aSHEET.getWorkbook().createCellStyle();
		itemRightCellStyle.setFont(font);
		itemRightCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		CellStyle itemDateCellStyle = aSHEET.getWorkbook().createCellStyle();
		itemDateCellStyle.setFont(font);
		itemDateCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		CreationHelper creationHelper = aSHEET.getWorkbook().getCreationHelper();
		itemDateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("MMM dd yyyy"));
		
		// Position start fill data
		int rowIndexStart = indexStartPCLeaseData;
		
		for (int i = 0; i < getPCLeaseData.size(); i++) {
			OrderLeaseBean orderLeaseBean = getPCLeaseData.get(i);
			Row rows = CellUtil.getRow(rowIndexStart, aSHEET);
			
			createCell1(rows, 2,  orderLeaseBean.getSeq(), itemCellStyle );
			checkNullValueCells(orderLeaseBean.getCompanyCd(), 3, rows, itemCellStyle);
			checkNullValueCells(orderLeaseBean.getDeptCd(), 4, rows, itemCellStyle);
			checkNullValueCells(orderLeaseBean.getQuotationNo(), 5, rows, itemCellStyle);
			checkNullValueCells(orderLeaseBean.getLeaseNo(), 6, rows, itemCellStyle);
			createCell(rows, 7, orderLeaseBean.getLeaseFee(), itemNumberCellStyle);
			createDateCell(rows,8, orderLeaseBean.getDeliverDt(), itemDateCellStyle);
			createDateCell(rows, 9, orderLeaseBean.getEndDt(),  itemDateCellStyle);
			createDateCell(rows, 10, orderLeaseBean.getWarrantyDt(), itemDateCellStyle);
			checkNullValueCells(orderLeaseBean.getCategoryCd(), 11, rows, itemCellStyle);
			checkNullValueCells(orderLeaseBean.getItemCd(), 12, rows, itemCellStyle);
			checkNullValueCells(orderLeaseBean.getSerialNo(), 13, rows, itemCellStyle);
			checkNullValueCells(orderLeaseBean.getActiveStatus(), 14, rows, itemCellStyle);
			rowIndexStart++;
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
	private void checkNullValueCells(String value, int indexcolumn,
			Row rows,CellStyle cellStyle) {
		if (value !=  null && !"".equals(value)) {
			CellUtil.createCell(rows, indexcolumn, value,cellStyle);
		}
	}
	
	
	public static Cell createDateCell(Row row, int column, String value, CellStyle style) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Cell cell = CellUtil.getCell(row, column);
		if (value != null && value.matches(".*-.*-.*")) {
			Date updateDate = sdf.parse(value);
			cell.setCellValue(updateDate);
		}
		
		if (style != null) {
			cell.setCellStyle(style);
		}
		return cell;
	}
	
	
	
	/**
	 * 
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @param List<Pad0011Bean>
	 * @param List<Pad0011Bean>
	 */
	private int loadListDataToExcel(Sheet aSHEET, Workbook aWORKBOOK, List<Pad0011Bean> listData, int rowIndexStartParmater, boolean typeCost) {

		Font font = aWORKBOOK.createFont();
		font.setFontName("Tahoma");
		font.setFontHeightInPoints((short) 10);
		
		CellStyle itemCellStyle = aSHEET.getWorkbook().createCellStyle();
		itemCellStyle.setFont(font);
		itemCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		itemCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		itemCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		itemCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		itemCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		CellStyle numberCellStyle = aSHEET.getWorkbook().createCellStyle();
		numberCellStyle.setFont(font);
		numberCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		numberCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		numberCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

		CellStyle numberDeliverAmountCellStyle = aSHEET.getWorkbook().createCellStyle();
		numberDeliverAmountCellStyle.setFont(font);
		numberDeliverAmountCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		numberDeliverAmountCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberDeliverAmountCellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		numberDeliverAmountCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberDeliverAmountCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		CellStyle numberBottomCellStyle = aSHEET.getWorkbook().createCellStyle();
		numberBottomCellStyle.setFont(font);
		numberBottomCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		numberBottomCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberBottomCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		numberBottomCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberBottomCellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
		
		// Style for type cell
		CellStyle blueCellStyleType = checkTypeCost(typeCost, getBlueCellStyleType(), getGreenCellStyleType());
		blueCellStyleType.setBorderTop(CellStyle.BORDER_MEDIUM);
		blueCellStyleType.setBorderRight(CellStyle.BORDER_MEDIUM);
		blueCellStyleType.setBorderLeft(CellStyle.BORDER_MEDIUM);
		blueCellStyleType.setBorderBottom(CellStyle.BORDER_MEDIUM);
		
		// Style for order no.  cell
		CellStyle orderCellStyle = checkTypeCost(typeCost, getOrderCellStyle(), getOrderGreenCellStyleType());
		
		// Style for category and addon  cell
		CellStyle categoryAddonCellStyleMain = checkTypeCost(typeCost, getCategoryAddonCellStyle(), getPinkCellStyleType());
		CellStyle categoryAddonCellStyleCloneMain = aSHEET.getWorkbook().createCellStyle();
		categoryAddonCellStyleCloneMain.cloneStyleFrom(categoryAddonCellStyleMain);
		categoryAddonCellStyleCloneMain.setBorderRight(CellStyle.BORDER_THIN);
		categoryAddonCellStyleCloneMain.setBorderLeft(CellStyle.BORDER_THIN);
		categoryAddonCellStyleCloneMain.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Style for item cell
		CellStyle orangeCellStyle = checkTypeCost(typeCost, getOrangeCellStyle(), getPurpleCellStyleType());
		orangeCellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		orangeCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		orangeCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Style for total cell
		CellStyle totalBlueCellStyleType = checkTypeCost(typeCost, getTotalBlueCellStyleType(), getTotalgreencellStyleType());
		
		CellStyle totalBlueCellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		totalBlueCellStyleTypeClone.cloneStyleFrom(totalBlueCellStyleType);
		// totalBlueCellStyleTypeClone
		totalBlueCellStyleTypeClone.setBorderTop(CellStyle.BORDER_MEDIUM);
		totalBlueCellStyleTypeClone.setBorderRight(CellStyle.BORDER_NONE);
		totalBlueCellStyleTypeClone.setBorderLeft(CellStyle.BORDER_THIN);
		totalBlueCellStyleTypeClone.setBorderBottom(CellStyle.BORDER_MEDIUM);
		
		// numberBlueCellStyleType
		CellStyle numberBlueCellStyleType = checkTypeCost(typeCost, getNumberBlueCellStyleType(), getTotalgreencellStyleType());

		CellStyle numberBlueCellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		numberBlueCellStyleTypeClone.cloneStyleFrom(numberBlueCellStyleType);
		numberBlueCellStyleTypeClone.setBorderBottom(CellStyle.BORDER_MEDIUM);
		numberBlueCellStyleTypeClone.setBorderTop(CellStyle.BORDER_MEDIUM);
		numberBlueCellStyleTypeClone.setBorderRight(CellStyle.BORDER_THIN);
		numberBlueCellStyleTypeClone.setBorderLeft(CellStyle.BORDER_THIN);
		numberBlueCellStyleTypeClone.setDataFormat(aWORKBOOK.createDataFormat().getFormat("#,##0.00"));

		CellStyle numberDecimal2CellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		numberDecimal2CellStyleTypeClone.cloneStyleFrom(getNumberDecimal2CellStyleType());
		numberDecimal2CellStyleTypeClone.setFont(font);
		numberDecimal2CellStyleTypeClone.setBorderBottom(CellStyle.BORDER_THIN);
		numberDecimal2CellStyleTypeClone.setBorderRight(CellStyle.BORDER_THIN);
		
		CellStyle numberDecimal2LastCellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		numberDecimal2LastCellStyleTypeClone.cloneStyleFrom(getNumberDecimal2CellStyleType());
		numberDecimal2LastCellStyleTypeClone.setFont(font);
		numberDecimal2LastCellStyleTypeClone.setBorderRight(CellStyle.BORDER_MEDIUM);
		numberDecimal2LastCellStyleTypeClone.setBorderBottom(CellStyle.BORDER_THIN);
		
		CellStyle blueCellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		blueCellStyleTypeClone.cloneStyleFrom(numberBlueCellStyleType);
		blueCellStyleTypeClone.setBorderBottom(CellStyle.BORDER_MEDIUM);
		blueCellStyleTypeClone.setBorderTop(CellStyle.BORDER_MEDIUM);
		blueCellStyleTypeClone.setBorderRight(CellStyle.BORDER_THIN);
		blueCellStyleTypeClone.setBorderLeft(CellStyle.BORDER_THIN);
		blueCellStyleTypeClone.setDataFormat(aWORKBOOK.createDataFormat().getFormat("#,##0"));
		
		// numberDeliverAmountBottomCellStyle
		CellStyle numberDeliverAmountBottomCellStyle = checkTypeCost(typeCost, getNumberDeliverAmountBottomCellStyle(), getTotalgreencellStyleType());
		
		CellStyle numberBottomLastCellStyle = aSHEET.getWorkbook().createCellStyle();
		numberBottomLastCellStyle.cloneStyleFrom(numberDeliverAmountBottomCellStyle);
		numberBottomLastCellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
		numberBottomLastCellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		numberBottomLastCellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		numberBottomLastCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberBottomLastCellStyle.setDataFormat(aWORKBOOK.createDataFormat().getFormat("#,##0.00"));
		
		String orderId = "";
		String categoryCd = "";
		String type = montCost;
		if (!typeCost) {
			type = oneTimeCost;
		}

		// Position merge for "type" column
		int rowIndexStartConst = rowIndexStartParmater;
		
		// Position start fill data
		int rowIndexStart = rowIndexStartParmater;
		
		// Column Type
		int columnIndex = 0;
		// Position merge for order no. column
		int rowIndexOrderMerge = rowIndexStart;
		// Position start merge for category column
		int rowIndexCategoryStartMerge = rowIndexStart;
		// Position end merge for category column
		int rowIndexCategoryEndMerge = rowIndexStart;
		for (int i = 0; i < listData.size(); i++) {
			Pad0011Bean pad0011Bean = listData.get(i);
			Row rows = CellUtil.getRow(rowIndexStart, aSHEET);
			if (StringUtil.isNullOrEmpty(orderId)) {
				orderId = pad0011Bean.getOrderId();
				categoryCd = pad0011Bean.getCategoryCd();
				if (rows != null) {
					// Column Order No.
					checkNullValueCells(pad0011Bean.getOrderId(), 1, rows, orderCellStyle);
					
					// Column Category.
					checkNullValueCells(pad0011Bean.getCategoryName(), 2, rows, categoryAddonCellStyleCloneMain);
				}
			}
			if (rows != null) {
				if (!orderId.equals(pad0011Bean.getOrderId())) {
					// **Start row for total**

					// Column Category.
					checkNullValueCells("Total", 2, rows, totalBlueCellStyleTypeClone);
					// Column Addon.
					checkNullValueCells("Total", 3, rows, totalBlueCellStyleTypeClone);
					
					// Column item.
					CellRangeAddress mergedCellItemName = new CellRangeAddress(rowIndexStart, rowIndexStart, 4, 5);
					aSHEET.addMergedRegion(mergedCellItemName);
					CellStyle itemTotalCellStyle = aSHEET.getWorkbook().createCellStyle();
					itemTotalCellStyle.cloneStyleFrom(totalBlueCellStyleTypeClone);
					itemTotalCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					checkNullValueCells("-", 4, rows, itemTotalCellStyle);
					RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellItemName, aSHEET, aWORKBOOK);
					RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
					RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
					
					sumTotalQty(rows, pad0011Bean, numberBlueCellStyleTypeClone, blueCellStyleTypeClone, numberBottomLastCellStyle, rowIndexOrderMerge + 1, rowIndexStart, true, numberDecimal2CellStyleTypeClone, numberDecimal2LastCellStyleTypeClone);
					
					// **End row for total**
					
					rowIndexStart++;
					rowIndexCategoryEndMerge++;
					rows = CellUtil.getRow(rowIndexStart, aSHEET);
					
					orderId = pad0011Bean.getOrderId();
					categoryCd = pad0011Bean.getCategoryCd();
					// Column Order No.
					checkNullValueCells(pad0011Bean.getOrderId(), 1, rows, orderCellStyle);

					// Column Category.
					checkNullValueCells(pad0011Bean.getCategoryName(), 2, rows, categoryAddonCellStyleCloneMain);
					// Merge for order no. column
					CellRangeAddress mergedCellOrder = new CellRangeAddress(rowIndexOrderMerge, rowIndexStart -1, 1, 1);
					aSHEET.addMergedRegion(mergedCellOrder);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellOrder, aSHEET, aWORKBOOK);
					RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellOrder, aSHEET, aWORKBOOK);
					

					// Merge for category column ("rowIndexCategoryEndMerge -2" reason: (1): length start 0. (2): index add of Total)
					CellRangeAddress mergedCellCategoryAddon = new CellRangeAddress(rowIndexCategoryStartMerge, rowIndexCategoryEndMerge -2, CellReference.convertColStringToIndex("C"), CellReference.convertColStringToIndex("C"));
					aSHEET.addMergedRegion(mergedCellCategoryAddon);
					
					rowIndexOrderMerge = rowIndexStart;
					rowIndexCategoryStartMerge = rowIndexCategoryEndMerge;
				}
				
				// Column category.
				if (!categoryCd.equals(pad0011Bean.getCategoryCd())) {
					categoryCd = pad0011Bean.getCategoryCd();
					checkNullValueCells(pad0011Bean.getCategoryName(), 2, rows, categoryAddonCellStyleCloneMain);
					// Merge for category column (if row is merge to equals form then don't merge)
					if (rowIndexCategoryStartMerge != rowIndexCategoryEndMerge -1) {
						CellRangeAddress mergedCellCategory = new CellRangeAddress(rowIndexCategoryStartMerge, rowIndexCategoryEndMerge -1, CellReference.convertColStringToIndex("C"), CellReference.convertColStringToIndex("C"));
						aSHEET.addMergedRegion(mergedCellCategory);
						RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellCategory, aSHEET, aWORKBOOK);
					}

					
					rowIndexCategoryStartMerge = rowIndexCategoryEndMerge;
				}
				
				// Column Addon.
				if (!StringUtil.isNullOrEmpty(pad0011Bean.getAddonCd())) {
					checkNullValueCells(pad0011Bean.getAddonName(), 3, rows, orangeCellStyle);
				}
				else {

					CellStyle categoryAddonCellStyleClone = aSHEET.getWorkbook().createCellStyle();
					categoryAddonCellStyleClone.cloneStyleFrom(categoryAddonCellStyleCloneMain);
					categoryAddonCellStyleClone.setBorderRight(CellStyle.BORDER_MEDIUM);
					checkNullValueCells(pad0011Bean.getAddonName(), 3, rows, categoryAddonCellStyleClone);
				}
				
				// Column item.
				CellRangeAddress mergedCellItemName = new CellRangeAddress(rowIndexStart, rowIndexStart, CellReference.convertColStringToIndex("E"), CellReference.convertColStringToIndex("F"));
				aSHEET.addMergedRegion(mergedCellItemName);
				checkNullValueCells(pad0011Bean.getItemName(), 4, rows, itemCellStyle);
				CellUtil.createCell(rows, 5, "", itemCellStyle);
				
				sumTotalQty(rows, pad0011Bean, numberCellStyle, blueCellStyleTypeClone, numberDeliverAmountCellStyle, rowIndexOrderMerge + 1, rowIndexStart, false, numberDecimal2CellStyleTypeClone, numberDecimal2LastCellStyleTypeClone);
			}
			rowIndexStart++;
			rowIndexCategoryEndMerge++;

			if  (i == listData.size() -1) {
				rows = CellUtil.getRow(rowIndexStart, aSHEET);
				// Set value for total
				checkNullValueCells("Total", 2, rows, totalBlueCellStyleTypeClone);
				checkNullValueCells("Total", 3, rows, totalBlueCellStyleTypeClone);
				
				// Column item.
				CellRangeAddress mergedCellItemName = new CellRangeAddress(rowIndexStart, rowIndexStart,  CellReference.convertColStringToIndex("E"), CellReference.convertColStringToIndex("F"));
				aSHEET.addMergedRegion(mergedCellItemName);
				CellStyle itemTotalCellStyle = aSHEET.getWorkbook().createCellStyle();
				itemTotalCellStyle.cloneStyleFrom(totalBlueCellStyleTypeClone);
				itemTotalCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				checkNullValueCells("-", 4, rows, itemTotalCellStyle);
				RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
				RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellItemName, aSHEET, aWORKBOOK);
				RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
				RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);

				sumTotalQty(rows, null, numberBlueCellStyleTypeClone, blueCellStyleTypeClone, numberBottomLastCellStyle, rowIndexOrderMerge + 1, rowIndexStart, true, numberDecimal2CellStyleTypeClone, numberDecimal2LastCellStyleTypeClone);
				// Merge for order no. column
				CellRangeAddress mergedCellOrder = new CellRangeAddress(rowIndexOrderMerge, rowIndexStart, 1, 1);
				aSHEET.addMergedRegion(mergedCellOrder);
				RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellOrder, aSHEET, aWORKBOOK);
				RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellOrder, aSHEET, aWORKBOOK);

				// Merge for category column
				aSHEET.addMergedRegion(new CellRangeAddress(rowIndexCategoryStartMerge, rowIndexCategoryEndMerge -1, 2, 2));
			}
		}

//		CellUtil.getRow(indexStart, aSHEET);
		// Merge for "type" column
		CellRangeAddress mergedCellType = new CellRangeAddress(rowIndexStartConst, rowIndexStart, columnIndex, columnIndex);
		aSHEET.addMergedRegion(mergedCellType);
		RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		checkNullValueCells(type, columnIndex, aSHEET.getRow(rowIndexStartConst), blueCellStyleType);
		return rowIndexStart;
	}
	
	
	/**
	 * 
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @param List<Pad0011Bean>
	 * @param List<Pad0011Bean>
	 */
	private int loadListDataToExcelSumTotal(Sheet aSHEET, Workbook aWORKBOOK, List<Pad0011Bean> listData, int rowIndexStartParmater, boolean typeCost) {

		Font font = aWORKBOOK.createFont();
		font.setFontName("Tahoma");
		font.setFontHeightInPoints((short) 10);
		
		CellStyle itemCellStyle = aSHEET.getWorkbook().createCellStyle();
		itemCellStyle.setFont(font);
		itemCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		itemCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		itemCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		itemCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		itemCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		CellStyle numberCellStyle = aSHEET.getWorkbook().createCellStyle();
		numberCellStyle.setFont(font);
		numberCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		numberCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		numberCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

		CellStyle numberDeliverAmountCellStyle = aSHEET.getWorkbook().createCellStyle();
		numberDeliverAmountCellStyle.setFont(font);
		numberDeliverAmountCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		numberDeliverAmountCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberDeliverAmountCellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		numberDeliverAmountCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberDeliverAmountCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		CellStyle numberBottomCellStyle = aSHEET.getWorkbook().createCellStyle();
		numberBottomCellStyle.setFont(font);
		numberBottomCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		numberBottomCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberBottomCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		numberBottomCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberBottomCellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
		
		// Style for type cell
		CellStyle blueCellStyleType = checkTypeCost(typeCost, getBlueCellStyleType(), getGreenCellStyleType());
		blueCellStyleType.setBorderTop(CellStyle.BORDER_MEDIUM);
		blueCellStyleType.setBorderRight(CellStyle.BORDER_MEDIUM);
		blueCellStyleType.setBorderLeft(CellStyle.BORDER_MEDIUM);
		blueCellStyleType.setBorderBottom(CellStyle.BORDER_MEDIUM);
		
		// Style for order no.  cell
		CellStyle orderCellStyle = checkTypeCost(typeCost, getOrderCellStyle(), getOrderGreenCellStyleType());
		
		// Style for category and addon  cell
		CellStyle categoryAddonCellStyleMain = checkTypeCost(typeCost, getCategoryAddonCellStyle(), getPinkCellStyleType());
		CellStyle categoryAddonCellStyleCloneMain = aSHEET.getWorkbook().createCellStyle();
		categoryAddonCellStyleCloneMain.cloneStyleFrom(categoryAddonCellStyleMain);
		categoryAddonCellStyleCloneMain.setBorderRight(CellStyle.BORDER_THIN);
		categoryAddonCellStyleCloneMain.setBorderLeft(CellStyle.BORDER_THIN);
		categoryAddonCellStyleCloneMain.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Style for item cell
		CellStyle orangeCellStyle = checkTypeCost(typeCost, getOrangeCellStyle(), getPurpleCellStyleType());
		orangeCellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		orangeCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		orangeCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Style for total cell
		CellStyle totalBlueCellStyleType = checkTypeCost(typeCost, getTotalBlueCellStyleType(), getTotalgreencellStyleType());
		
		CellStyle totalBlueCellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		totalBlueCellStyleTypeClone.cloneStyleFrom(totalBlueCellStyleType);
		// totalBlueCellStyleTypeClone
		totalBlueCellStyleTypeClone.setBorderTop(CellStyle.BORDER_MEDIUM);
		totalBlueCellStyleTypeClone.setBorderRight(CellStyle.BORDER_NONE);
		totalBlueCellStyleTypeClone.setBorderLeft(CellStyle.BORDER_THIN);
		totalBlueCellStyleTypeClone.setBorderBottom(CellStyle.BORDER_MEDIUM);
		
		// numberBlueCellStyleType
		CellStyle numberBlueCellStyleType = checkTypeCost(typeCost, getNumberBlueCellStyleType(), getTotalgreencellStyleType());
		
		CellStyle numberBlueCellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		numberBlueCellStyleTypeClone.cloneStyleFrom(numberBlueCellStyleType);
		numberBlueCellStyleTypeClone.setBorderBottom(CellStyle.BORDER_MEDIUM);
		numberBlueCellStyleTypeClone.setBorderTop(CellStyle.BORDER_MEDIUM);
		numberBlueCellStyleTypeClone.setBorderRight(CellStyle.BORDER_THIN);
		numberBlueCellStyleTypeClone.setBorderLeft(CellStyle.BORDER_THIN);
		numberBlueCellStyleTypeClone.setDataFormat(aWORKBOOK.createDataFormat().getFormat("#,##0.00"));
		
		CellStyle numberDecimal2CellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		numberDecimal2CellStyleTypeClone.cloneStyleFrom(getNumberDecimal2CellStyleType());
		numberDecimal2CellStyleTypeClone.setFont(font);
		numberDecimal2CellStyleTypeClone.setBorderBottom(CellStyle.BORDER_THIN);
		numberDecimal2CellStyleTypeClone.setBorderRight(CellStyle.BORDER_THIN);
		
		CellStyle numberDecimal2LastCellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		numberDecimal2LastCellStyleTypeClone.cloneStyleFrom(getNumberDecimal2CellStyleType());
		numberDecimal2LastCellStyleTypeClone.setFont(font);
		numberDecimal2LastCellStyleTypeClone.setBorderRight(CellStyle.BORDER_MEDIUM);
		numberDecimal2LastCellStyleTypeClone.setBorderBottom(CellStyle.BORDER_THIN);
		
		CellStyle blueCellStyleTypeClone = aSHEET.getWorkbook().createCellStyle();
		blueCellStyleTypeClone.cloneStyleFrom(numberBlueCellStyleType);
		blueCellStyleTypeClone.setBorderBottom(CellStyle.BORDER_MEDIUM);
		blueCellStyleTypeClone.setBorderTop(CellStyle.BORDER_MEDIUM);
		blueCellStyleTypeClone.setBorderRight(CellStyle.BORDER_THIN);
		blueCellStyleTypeClone.setBorderLeft(CellStyle.BORDER_THIN);
		blueCellStyleTypeClone.setDataFormat(aWORKBOOK.createDataFormat().getFormat("#,##0"));
		
		// numberDeliverAmountBottomCellStyle
		CellStyle numberDeliverAmountBottomCellStyle = checkTypeCost(typeCost, getNumberDeliverAmountBottomCellStyle(), getTotalgreencellStyleType());
		
		CellStyle numberBottomLastCellStyle = aSHEET.getWorkbook().createCellStyle();
		numberBottomLastCellStyle.cloneStyleFrom(numberDeliverAmountBottomCellStyle);
		numberBottomLastCellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
		numberBottomLastCellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		numberBottomLastCellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		numberBottomLastCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberBottomLastCellStyle.setDataFormat(aWORKBOOK.createDataFormat().getFormat("#,##0.00"));
		
		String orderId = "";
		String categoryCd = "";

		// Position start fill data
		int rowIndexStart = rowIndexStartParmater;
		
		// Position merge for order no. column
		int rowIndexOrderMerge = rowIndexStart;
		// Position start merge for category column
		int rowIndexCategoryStartMerge = rowIndexStart;
		// Position end merge for category column
		int rowIndexCategoryEndMerge = rowIndexStart;
		for (int i = 0; i < listData.size(); i++) {
			Pad0011Bean pad0011Bean = listData.get(i);
			Row rows = CellUtil.getRow(rowIndexStart, aSHEET);
			if (StringUtil.isNullOrEmpty(orderId)) {
				orderId = pad0011Bean.getOrderId();
				categoryCd = pad0011Bean.getCategoryCd();
				if (rows != null) {
					// Column Order No.
					checkNullValueCells(pad0011Bean.getOrderId(), 1, rows, orderCellStyle);
					
					// Column Category.
					checkNullValueCells(pad0011Bean.getCategoryName(), 2, rows, categoryAddonCellStyleCloneMain);
				}
			}
			if (rows != null) {
				if (!orderId.equals(pad0011Bean.getOrderId())) {
					// **Start row for total**

					// Column Category.
					checkNullValueCells("Total", 2, rows, totalBlueCellStyleTypeClone);
					// Column Addon.
					checkNullValueCells("Total", 3, rows, totalBlueCellStyleTypeClone);
					
					// Column item.
					CellRangeAddress mergedCellItemName = new CellRangeAddress(rowIndexStart, rowIndexStart, 4, 5);
					aSHEET.addMergedRegion(mergedCellItemName);
					CellStyle itemTotalCellStyle = aSHEET.getWorkbook().createCellStyle();
					itemTotalCellStyle.cloneStyleFrom(totalBlueCellStyleTypeClone);
					itemTotalCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					checkNullValueCells("-", 4, rows, itemTotalCellStyle);
					RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellItemName, aSHEET, aWORKBOOK);
					RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
					RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
					
					sumTotalQty(rows, pad0011Bean, numberBlueCellStyleTypeClone, blueCellStyleTypeClone, numberBottomLastCellStyle, rowIndexOrderMerge + 1, rowIndexStart, true, numberDecimal2CellStyleTypeClone, numberDecimal2LastCellStyleTypeClone);
					
					// **End row for total**
					
					rowIndexStart++;
					rowIndexCategoryEndMerge++;
					rows = CellUtil.getRow(rowIndexStart, aSHEET);
					
					orderId = pad0011Bean.getOrderId();
					categoryCd = pad0011Bean.getCategoryCd();
					// Column Order No.
					checkNullValueCells(pad0011Bean.getOrderId(), 1, rows, orderCellStyle);

					// Column Category.
					checkNullValueCells(pad0011Bean.getCategoryName(), 2, rows, categoryAddonCellStyleCloneMain);
					// Merge for order no. column
					CellRangeAddress mergedCellOrder = new CellRangeAddress(rowIndexOrderMerge, rowIndexStart -1, 1, 1);
					aSHEET.addMergedRegion(mergedCellOrder);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellOrder, aSHEET, aWORKBOOK);
					RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellOrder, aSHEET, aWORKBOOK);
					

					// Merge for category column ("rowIndexCategoryEndMerge -2" reason: (1): length start 0. (2): index add of Total)
					CellRangeAddress mergedCellCategoryAddon = new CellRangeAddress(rowIndexCategoryStartMerge, rowIndexCategoryEndMerge -2, CellReference.convertColStringToIndex("C"), CellReference.convertColStringToIndex("C"));
					aSHEET.addMergedRegion(mergedCellCategoryAddon);
					
					rowIndexOrderMerge = rowIndexStart;
					rowIndexCategoryStartMerge = rowIndexCategoryEndMerge;
				}
				
				// Column category.
				if (!categoryCd.equals(pad0011Bean.getCategoryCd())) {
					categoryCd = pad0011Bean.getCategoryCd();
					checkNullValueCells(pad0011Bean.getCategoryName(), 2, rows, categoryAddonCellStyleCloneMain);
					// Merge for category column ( if row is merge to equals form then don't merge)
					if (rowIndexCategoryStartMerge != rowIndexCategoryEndMerge -1) {
						CellRangeAddress mergedCellCategory = new CellRangeAddress(rowIndexCategoryStartMerge, rowIndexCategoryEndMerge -1, CellReference.convertColStringToIndex("C"), CellReference.convertColStringToIndex("C"));
						aSHEET.addMergedRegion(mergedCellCategory);
						RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellCategory, aSHEET, aWORKBOOK);
					}

					
					rowIndexCategoryStartMerge = rowIndexCategoryEndMerge;
				}
				
				// Column Addon.
				if (!StringUtil.isNullOrEmpty(pad0011Bean.getAddonCd())) {
					checkNullValueCells(pad0011Bean.getAddonName(), 3, rows, orangeCellStyle);
				}
				else {

					CellStyle categoryAddonCellStyleClone = aSHEET.getWorkbook().createCellStyle();
					categoryAddonCellStyleClone.cloneStyleFrom(categoryAddonCellStyleCloneMain);
					categoryAddonCellStyleClone.setBorderRight(CellStyle.BORDER_MEDIUM);
					checkNullValueCells(pad0011Bean.getAddonName(), 3, rows, categoryAddonCellStyleClone);
				}
				
				// Column item.
				CellRangeAddress mergedCellItemName = new CellRangeAddress(rowIndexStart, rowIndexStart, CellReference.convertColStringToIndex("E"), CellReference.convertColStringToIndex("F"));
				aSHEET.addMergedRegion(mergedCellItemName);
				checkNullValueCells(pad0011Bean.getItemName(), 4, rows, itemCellStyle);
				CellUtil.createCell(rows, 5, "", itemCellStyle);
				
				sumTotalQty(rows, pad0011Bean, numberCellStyle, blueCellStyleTypeClone, numberDeliverAmountCellStyle, rowIndexOrderMerge + 1, rowIndexStart, false, numberDecimal2CellStyleTypeClone, numberDecimal2LastCellStyleTypeClone);
			}
			rowIndexStart++;
			rowIndexCategoryEndMerge++;

			if  (i == listData.size() -1) {
				rows = CellUtil.getRow(rowIndexStart, aSHEET);
				// Set value for total
				checkNullValueCells("Total", 2, rows, totalBlueCellStyleTypeClone);
				checkNullValueCells("Total", 3, rows, totalBlueCellStyleTypeClone);
				
				// Column item.
				CellRangeAddress mergedCellItemName = new CellRangeAddress(rowIndexStart, rowIndexStart,  CellReference.convertColStringToIndex("E"), CellReference.convertColStringToIndex("F"));
				aSHEET.addMergedRegion(mergedCellItemName);
				CellStyle itemTotalCellStyle = aSHEET.getWorkbook().createCellStyle();
				itemTotalCellStyle.cloneStyleFrom(totalBlueCellStyleTypeClone);
				itemTotalCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				checkNullValueCells("-", 4, rows, itemTotalCellStyle);
				RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
				RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellItemName, aSHEET, aWORKBOOK);
				RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);
				RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellItemName, aSHEET, aWORKBOOK);

				sumTotalQty(rows, null, numberBlueCellStyleTypeClone, blueCellStyleTypeClone, numberBottomLastCellStyle, rowIndexOrderMerge + 1, rowIndexStart, true, numberDecimal2CellStyleTypeClone, numberDecimal2LastCellStyleTypeClone);
				// Merge for order no. column
				CellRangeAddress mergedCellOrder = new CellRangeAddress(rowIndexOrderMerge, rowIndexStart, 1, 1);
				aSHEET.addMergedRegion(mergedCellOrder);
				RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellOrder, aSHEET, aWORKBOOK);
				RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellOrder, aSHEET, aWORKBOOK);

				// Merge for category column
				aSHEET.addMergedRegion(new CellRangeAddress(rowIndexCategoryStartMerge, rowIndexCategoryEndMerge -1, 2, 2));
			}
		}

//		CellUtil.getRow(indexStart, aSHEET);
		return rowIndexStart;
	}
	
	/**
	 * Create 12 month in sheet
	 * @param aSHEET
	 */
	private void loadMonthYearDataK(Sheet aSHEET) {
		String[] coloumIndexDataK = {"AA","Y","W","U","S","Q","O","M","K","I","G","E"};
		String[] coloumIndexDataKYearly = {"AB","Z","X","V","T","R","P","N","L","J","H","F"};
		String[] coloumIndex;
		int rowIndex = 3;
		if (outputLeaseInvoiceDataKYearly){
			coloumIndex = coloumIndexDataKYearly;
		} else {
			coloumIndex = coloumIndexDataK;
		}
		String maxDate = "Dec-" + yearSelected;
		SimpleDateFormat monthDate = new SimpleDateFormat("MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(monthDate.parse(maxDate));
		} catch (ParseException e) {
		}

		if (outputLeaseInvoiceDataKYearly){
			monthDate = new SimpleDateFormat("MMM/yy");
			String date = monthDate.format(cal.getTime());
			Row rowMonth = CellUtil.getRow(rowIndex, aSHEET);
		    Cell cell = CellUtil.getCell(rowMonth, CellReference.convertColStringToIndex(coloumIndex[0]));
			cell.setCellValue(date);
			for (int i = 1; i < 12; i++) {
			    cal.add(Calendar.MONTH, -1);
			    cell = CellUtil.getCell(rowMonth, CellReference.convertColStringToIndex(coloumIndex[i]));
			    date = monthDate.format(cal.getTime());
				cell.setCellValue(date);
			}
			return;
		}
		
		Row rowMonth = CellUtil.getRow(rowIndex, aSHEET);
	    Cell cell = CellUtil.getCell(rowMonth, CellReference.convertColStringToIndex(coloumIndex[0]));
		cell.setCellValue(cal);
		for (int i = 1; i < 12; i++) {
		    cal.add(Calendar.MONTH, -1);
		    cell = CellUtil.getCell(rowMonth, CellReference.convertColStringToIndex(coloumIndex[i]));
			cell.setCellValue(cal);
		}
	}
	
	private void creatCellFormula(Row rows, String cell, int rowStartSumTotal,  int rowIndexStart, CellStyle cellStyle) {
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		CellUtil.createCell(rows, CellReference.convertColStringToIndex(cell), "", cellStyle);
		int indexCell = CellReference.convertColStringToIndex(cell);
		if (indexCell < (month*2) + 4) {
			rows.getCell(indexCell).setCellFormula("SUM("+cell + rowStartSumTotal+":" +cell+rowIndexStart+")");
		}
//		else if (!"".equals(rows.getCell(indexCell).getStringCellValue())) {
//			rows.getCell(indexCell).setCellFormula("SUM("+cell + rowStartSumTotal+":" +cell+rowIndexStart+")");
//		} 
		else {
			CellUtil.createCell(rows, indexCell, "", cellStyle);
		}
	}
	
	/**
	 * Sum qty and sum price
	 * @param aSHEET
	 * @param rowIndexStartPlus
	 * @param rowStartSumTotal
	 * @param rowIndexStart
	 */
//	private void sumTotalMonthlyCost(Sheet aSHEET, int rowIndexStartPlus, int rowStartSumTotal, int rowIndexStart) {
//		Row rows = CellUtil.getRow(rowIndexStartPlus, aSHEET);
//		
//		checkNullValueCells(" ", CellReference.convertColStringToIndex("C"), rows, getTotalCCellStyle());
//		// Column item
//		checkNullValueCells("Total", CellReference.convertColStringToIndex("D"), rows, getTotalDCellStyle());
//		// Column Jan
////		CellUtil.createCell(rows, CellReference.convertColStringToIndex("E"), "", getTotalLeftCellStyle());
//		creatCellFormula(rows, "E", rowStartSumTotal, rowIndexStart);
////		if (!"".equals(rows.getCell(CellReference.convertColStringToIndex("E")).getStringCellValue())) {
////			rows.getCell(CellReference.convertColStringToIndex("E")).setCellFormula("SUM(E"+rowStartSumTotal+":E"+rowIndexStart+")");
////		} else {
////			CellUtil.createCell(rows, CellReference.convertColStringToIndex("E"), "", getTotalLeftCellStyle());
////		}
//		
//		// Column JanSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("F"), "", getTotalRightCellStyle()).setCellFormula("SUM(F"+rowStartSumTotal+":F"+rowIndexStart+")");
//		// Column Feb
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("G"), "", getTotalLeftCellStyle()).setCellFormula("SUM(G"+rowStartSumTotal+":G"+rowIndexStart+")");
//		// Column FebSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("H"), "", getTotalRightCellStyle()).setCellFormula("SUM(H"+rowStartSumTotal+":H"+rowIndexStart+")");
//		// Column Mar
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("I"), "", getTotalLeftCellStyle()).setCellFormula("SUM(I"+rowStartSumTotal+":I"+rowIndexStart+")");
//		// Column MarSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("J"), "", getTotalRightCellStyle()).setCellFormula("SUM(J"+rowStartSumTotal+":J"+rowIndexStart+")");
//		// Column Apr
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("K"), "", getTotalLeftCellStyle()).setCellFormula("SUM(K"+rowStartSumTotal+":K"+rowIndexStart+")");
//		// Column AprSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("L"), "", getTotalRightCellStyle()).setCellFormula("SUM(L"+rowStartSumTotal+":L"+rowIndexStart+")");
//		// Column May
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("M"), "", getTotalLeftCellStyle()).setCellFormula("SUM(M"+rowStartSumTotal+":M"+rowIndexStart+")");
//		// Column MaySum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("N"), "", getTotalRightCellStyle()).setCellFormula("SUM(N"+rowStartSumTotal+":N"+rowIndexStart+")");
//		// Column Jun
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("O"), "", getTotalLeftCellStyle()).setCellFormula("SUM(O"+rowStartSumTotal+":O"+rowIndexStart+")");
//		// Column JunSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("P"), "", getTotalRightCellStyle()).setCellFormula("SUM(P"+rowStartSumTotal+":P"+rowIndexStart+")");
//		// Column Jul
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("Q"), "", getTotalLeftCellStyle()).setCellFormula("SUM(Q"+rowStartSumTotal+":Q"+rowIndexStart+")");
//		// Column JulSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("R"), "", getTotalRightCellStyle()).setCellFormula("SUM(R"+rowStartSumTotal+":R"+rowIndexStart+")");
//		// Column Aug
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("S"), "", getTotalLeftCellStyle()).setCellFormula("SUM(S"+rowStartSumTotal+":S"+rowIndexStart+")");
//		// Column AugSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("T"), "", getTotalRightCellStyle()).setCellFormula("SUM(T"+rowStartSumTotal+":T"+rowIndexStart+")");
//		// Column Sep
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("U"), "", getTotalLeftCellStyle()).setCellFormula("SUM(U"+rowStartSumTotal+":U"+rowIndexStart+")");
//		// Column SepSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("V"), "", getTotalRightCellStyle()).setCellFormula("SUM(V"+rowStartSumTotal+":V"+rowIndexStart+")");
//		// Column Oct
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("W"), "", getTotalLeftCellStyle()).setCellFormula("SUM(W"+rowStartSumTotal+":W"+rowIndexStart+")");
//		// Column OctSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("X"), "", getTotalRightCellStyle()).setCellFormula("SUM(X"+rowStartSumTotal+":X"+rowIndexStart+")");
//		// Column Nov
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("Y"), "", getTotalLeftCellStyle()).setCellFormula("SUM(Y"+rowStartSumTotal+":Y"+rowIndexStart+")");
//		// Column NovSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("Z"), "", getTotalRightCellStyle()).setCellFormula("SUM(Z"+rowStartSumTotal+":Z"+rowIndexStart+")");
//		// Column Dec
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("AA"), "", getTotalLeftCellStyle()).setCellFormula("SUM(AA"+rowStartSumTotal+":AA"+rowIndexStart+")");
//		// Column DecSum
//		CellUtil.createCell(rows, CellReference.convertColStringToIndex("AB"), "", getTotalLastCellStyle()).setCellFormula("SUM(AB"+rowStartSumTotal+":AB"+rowIndexStart+")");
//	}
	
	private void sumTotalMonthlyCost(Sheet aSHEET, int rowIndexStartPlus, int rowStartSumTotal, int rowIndexStart) {
		Row rows = CellUtil.getRow(rowIndexStartPlus, aSHEET);
		
		checkNullValueCells(" ", CellReference.convertColStringToIndex("C"), rows, getTotalCCellStyle());
		// Column item
		checkNullValueCells("Total", CellReference.convertColStringToIndex("D"), rows, getTotalDCellStyle());
		// Column Jan
		creatCellFormula(rows, "E", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		
		// Column JanSum
		creatCellFormula(rows, "F", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		// Column Feb
		creatCellFormula(rows, "G", rowStartSumTotal, rowIndexStart , getTotalLeftCellStyle());
		// Column FebSum
		creatCellFormula(rows, "H", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		// Column Mar
		creatCellFormula(rows, "I", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		// Column MarSum
		creatCellFormula(rows, "J", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		// Column Apr
		creatCellFormula(rows, "K", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		// Column AprSum
		creatCellFormula(rows, "L", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		// Column May
		creatCellFormula(rows, "M", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		// Column MaySum
		creatCellFormula(rows, "N", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		// Column Jun
		creatCellFormula(rows, "O", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		// Column JunSum
		creatCellFormula(rows, "P", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		// Column Jul
		creatCellFormula(rows, "Q", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		// Column JulSum
		// Column Aug
		creatCellFormula(rows, "R", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		creatCellFormula(rows, "S", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		creatCellFormula(rows, "T", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		creatCellFormula(rows, "U", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		creatCellFormula(rows, "V", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		creatCellFormula(rows, "W", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		creatCellFormula(rows, "X", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		creatCellFormula(rows, "Y", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		creatCellFormula(rows, "Z", rowStartSumTotal, rowIndexStart, getTotalRightCellStyle());
		creatCellFormula(rows, "AA", rowStartSumTotal, rowIndexStart, getTotalLeftCellStyle());
		creatCellFormula(rows, "AB", rowStartSumTotal, rowIndexStart, getTotalLastCellStyle());
		// Column AugSum
		// Column Sep
//		// Column SepSum
//		// Column Oct
//		// Column OctSum
//		// Column Nov
//		// Column NovSum
//		// Column Dec
//		// Column DecSum
	}
	/**
	 * 
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @param List<Pad0011Bean>
	 * @param List<Pad0011Bean>
	 */
	private int loadListDataKToExcelSumTotal(Sheet aSHEET, Workbook aWORKBOOK, List<ExcelPCLeaseInvoiceDataSBean> listData, int rowIndexStartParmater) {
		// create 12 month by year for sheet (Jan - Dec)
		loadMonthYearDataK(aSHEET);
		
		Font font = aWORKBOOK.createFont();
		font.setFontName("Tahoma");
		font.setFontHeightInPoints((short) 10);
		
		// Position start fill data
		int rowIndexStart = rowIndexStartParmater;
		int rowStartSumTotal = rowIndexStartParmater + 1;
		
		for (int i = 0; i < listData.size(); i++) {
			ExcelPCLeaseInvoiceDataSBean excelPCLeaseInvoiceDataSBean = listData.get(i);
			/*if (i == 0 && !excelPCLeaseInvoiceDataSBean.getCategoryName().equals("OTC")) {
				Row rowsIndex = CellUtil.getRow(rowIndexStart-1, aSHEET);
				checkNullValueCells(excelPCLeaseInvoiceDataSBean.getCompanyCd(), CellReference.convertColStringToIndex("C"), rowsIndex, getItemNoColorCellStyle());
			}*/
			
			Row rows = CellUtil.getRow(rowIndexStart, aSHEET);
			// Last element in list ( For One Time Cost Style)
			if (i == listData.size() - 1 && excelPCLeaseInvoiceDataSBean.getCategoryName().equals("One Time Cost")) {
				// For total Monthly Cost
				if (rowIndexStart > rowStartSumTotal) {
					sumTotalMonthlyCost(aSHEET, rowIndexStart, rowStartSumTotal, rowIndexStart);
					rows = CellUtil.getRow(rowIndexStart +1, aSHEET);
					rowIndexStart += 1;
				} else {
					rows = CellUtil.getRow(rowIndexStart, aSHEET);
				}
				checkNullValueCells(" ", CellReference.convertColStringToIndex("C"), rows, getOneTimeCostLeftCellStyle());
				// Column item
				checkNullValueCells(excelPCLeaseInvoiceDataSBean.getCategoryName(), CellReference.convertColStringToIndex("D"), rows, getOneTimeCostRightCellStyle());
				// Column Jan
				createCellForMonth(rows, CellReference.convertColStringToIndex("E"), excelPCLeaseInvoiceDataSBean.getJan(), getOneTimeCostMonthLeftCellStyle());
				// Column JanSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("F"), excelPCLeaseInvoiceDataSBean.getJanSum(), getOneTimeCostMonthRightCellStyle());
				// Column Feb
				createCellForMonth(rows, CellReference.convertColStringToIndex("G"), excelPCLeaseInvoiceDataSBean.getFeb(), getOneTimeCostMonthLeftCellStyle());
				// Column FebSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("H"), excelPCLeaseInvoiceDataSBean.getFebSum(), getOneTimeCostMonthRightCellStyle());
				// Column Mar
				createCellForMonth(rows, CellReference.convertColStringToIndex("I"), excelPCLeaseInvoiceDataSBean.getMar(), getOneTimeCostMonthLeftCellStyle());
				// Column MarSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("J"), excelPCLeaseInvoiceDataSBean.getMarSum(), getOneTimeCostMonthRightCellStyle());
				// Column Apr
				createCellForMonth(rows, CellReference.convertColStringToIndex("K"), excelPCLeaseInvoiceDataSBean.getApr(), getOneTimeCostMonthLeftCellStyle());
				// Column AprSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("L"), excelPCLeaseInvoiceDataSBean.getAprSum(), getOneTimeCostMonthRightCellStyle());
				// Column May
				createCellForMonth(rows, CellReference.convertColStringToIndex("M"), excelPCLeaseInvoiceDataSBean.getMay(), getOneTimeCostMonthLeftCellStyle());
				// Column MaySum
				createCellForMonth(rows, CellReference.convertColStringToIndex("N"), excelPCLeaseInvoiceDataSBean.getMaySum(), getOneTimeCostMonthRightCellStyle());
				// Column Jun
				createCellForMonth(rows, CellReference.convertColStringToIndex("O"), excelPCLeaseInvoiceDataSBean.getJun(), getOneTimeCostMonthLeftCellStyle());
				// Column JunSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("P"), excelPCLeaseInvoiceDataSBean.getJunSum(), getOneTimeCostMonthRightCellStyle());
				// Column Jul
				createCellForMonth(rows, CellReference.convertColStringToIndex("Q"), excelPCLeaseInvoiceDataSBean.getJul(), getOneTimeCostMonthLeftCellStyle());
				// Column JulSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("R"), excelPCLeaseInvoiceDataSBean.getJulSum(), getOneTimeCostMonthRightCellStyle());
				// Column Aug
				createCellForMonth(rows, CellReference.convertColStringToIndex("S"), excelPCLeaseInvoiceDataSBean.getAug(), getOneTimeCostMonthLeftCellStyle());
				// Column AugSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("T"), excelPCLeaseInvoiceDataSBean.getAugSum(), getOneTimeCostMonthRightCellStyle());
				// Column Sep
				createCellForMonth(rows, CellReference.convertColStringToIndex("U"), excelPCLeaseInvoiceDataSBean.getSep(), getOneTimeCostMonthLeftCellStyle());
				// Column SepSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("V"), excelPCLeaseInvoiceDataSBean.getSepSum(), getOneTimeCostMonthRightCellStyle());
				// Column Oct
				createCellForMonth(rows, CellReference.convertColStringToIndex("W"), excelPCLeaseInvoiceDataSBean.getOct(), getOneTimeCostMonthLeftCellStyle());
				// Column OctSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("X"), excelPCLeaseInvoiceDataSBean.getOctSum(), getOneTimeCostMonthRightCellStyle());
				// Column Nov
				createCellForMonth(rows, CellReference.convertColStringToIndex("Y"), excelPCLeaseInvoiceDataSBean.getNov(), getOneTimeCostMonthLeftCellStyle());
				// Column NovSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("Z"), excelPCLeaseInvoiceDataSBean.getNovSum(), getOneTimeCostMonthRightCellStyle());
				// Column Dec
				createCellForMonth(rows, CellReference.convertColStringToIndex("AA"), excelPCLeaseInvoiceDataSBean.getDec(), getOneTimeCostMonthLeftCellStyle());
				// Column DecSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("AB"), excelPCLeaseInvoiceDataSBean.getDecSum(), getOneTimeCostMonthLastCellStyle());
				// Merge for "type" column
				CellRangeAddress mergedCellType = new CellRangeAddress(rowIndexStartParmater, rowIndexStart - 1, CellReference.convertColStringToIndex("C"), CellReference.convertColStringToIndex("C"));
				aSHEET.addMergedRegion(mergedCellType);
				aSHEET.getRow(rowIndexStartParmater).setHeight((short)-1);
				break;
			}
			// Monthly Cost Style
			if (rows != null) {
				// Column Monthly Cost
				checkNullValueCells("Monthly Cost", CellReference.convertColStringToIndex("C"), rows, getMonthlyCostCellStyle());
				// Column item
				checkNullValueCells(excelPCLeaseInvoiceDataSBean.getCategoryName(), CellReference.convertColStringToIndex("D"), rows, getItemNoColorCellStyle());
				// Column Jan
				createCellForMonth(rows, CellReference.convertColStringToIndex("E"), excelPCLeaseInvoiceDataSBean.getJan(), getMonthLeftCellStyle());
				// Column JanSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("F"), excelPCLeaseInvoiceDataSBean.getJanSum(), getMonthRightCellStyle());
				// Column Feb
				createCellForMonth(rows, CellReference.convertColStringToIndex("G"), excelPCLeaseInvoiceDataSBean.getFeb(), getMonthLeftCellStyle());
				// Column FebSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("H"), excelPCLeaseInvoiceDataSBean.getFebSum(), getMonthRightCellStyle());
				// Column Mar
				createCellForMonth(rows, CellReference.convertColStringToIndex("I"), excelPCLeaseInvoiceDataSBean.getMar(), getMonthLeftCellStyle());
				// Column MarSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("J"), excelPCLeaseInvoiceDataSBean.getMarSum(), getMonthRightCellStyle());
				// Column Apr
				createCellForMonth(rows, CellReference.convertColStringToIndex("K"), excelPCLeaseInvoiceDataSBean.getApr(), getMonthLeftCellStyle());
				// Column AprSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("L"), excelPCLeaseInvoiceDataSBean.getAprSum(), getMonthRightCellStyle());
				// Column May
				createCellForMonth(rows, CellReference.convertColStringToIndex("M"), excelPCLeaseInvoiceDataSBean.getMay(), getMonthLeftCellStyle());
				// Column MaySum
				createCellForMonth(rows, CellReference.convertColStringToIndex("N"), excelPCLeaseInvoiceDataSBean.getMaySum(), getMonthRightCellStyle());
				// Column Jun
				createCellForMonth(rows, CellReference.convertColStringToIndex("O"), excelPCLeaseInvoiceDataSBean.getJun(), getMonthLeftCellStyle());
				// Column JunSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("P"), excelPCLeaseInvoiceDataSBean.getJunSum(), getMonthRightCellStyle());
				// Column Jul
				createCellForMonth(rows, CellReference.convertColStringToIndex("Q"), excelPCLeaseInvoiceDataSBean.getJul(), getMonthLeftCellStyle());
				// Column JulSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("R"), excelPCLeaseInvoiceDataSBean.getJulSum(), getMonthRightCellStyle());
				// Column Aug
				createCellForMonth(rows, CellReference.convertColStringToIndex("S"), excelPCLeaseInvoiceDataSBean.getAug(), getMonthLeftCellStyle());
				// Column AugSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("T"), excelPCLeaseInvoiceDataSBean.getAugSum(), getMonthRightCellStyle());
				// Column Sep
				createCellForMonth(rows, CellReference.convertColStringToIndex("U"), excelPCLeaseInvoiceDataSBean.getSep(), getMonthLeftCellStyle());
				// Column SepSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("V"), excelPCLeaseInvoiceDataSBean.getSepSum(), getMonthRightCellStyle());
				// Column Oct
				createCellForMonth(rows, CellReference.convertColStringToIndex("W"), excelPCLeaseInvoiceDataSBean.getOct(), getMonthLeftCellStyle());
				// Column OctSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("X"), excelPCLeaseInvoiceDataSBean.getOctSum(), getMonthRightCellStyle());
				// Column Nov
				createCellForMonth(rows, CellReference.convertColStringToIndex("Y"), excelPCLeaseInvoiceDataSBean.getNov(), getMonthLeftCellStyle());
				// Column NovSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("Z"), excelPCLeaseInvoiceDataSBean.getNovSum(), getMonthRightCellStyle());
				// Column Dec
				createCellForMonth(rows, CellReference.convertColStringToIndex("AA"), excelPCLeaseInvoiceDataSBean.getDec(), getMonthLeftCellStyle());
				// Column DecSum
				createCellForMonth(rows, CellReference.convertColStringToIndex("AB"), excelPCLeaseInvoiceDataSBean.getDecSum(), getMonthLastCellStyle());

				// For total Monthly Cost
				if  (i == listData.size() - 1 && !excelPCLeaseInvoiceDataSBean.getCategoryName().equals("One Time Cost")) {
					sumTotalMonthlyCost(aSHEET, rowIndexStart +1, rowStartSumTotal, rowIndexStart + 1);
					rows = CellUtil.getRow(rowIndexStart +2, aSHEET);
					rowIndexStart += 2;
					// Fill data null row One Time Cost
					checkNullValueCells(" ", CellReference.convertColStringToIndex("C"), rows, getOneTimeCostLeftCellStyle());
					// Column item
					checkNullValueCells("One Time Cost", CellReference.convertColStringToIndex("D"), rows, getOneTimeCostRightCellStyle());
					// Column Jan
					createCellForMonth(rows, CellReference.convertColStringToIndex("E"), null, getOneTimeCostMonthLeftCellStyle());
					// Column JanSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("F"), null, getOneTimeCostMonthRightCellStyle());
					// Column Feb
					createCellForMonth(rows, CellReference.convertColStringToIndex("G"), null, getOneTimeCostMonthLeftCellStyle());
					// Column FebSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("H"), null, getOneTimeCostMonthRightCellStyle());
					// Column Mar
					createCellForMonth(rows, CellReference.convertColStringToIndex("I"), null, getOneTimeCostMonthLeftCellStyle());
					// Column MarSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("J"), null, getOneTimeCostMonthRightCellStyle());
					// Column Apr
					createCellForMonth(rows, CellReference.convertColStringToIndex("K"), null, getOneTimeCostMonthLeftCellStyle());
					// Column AprSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("L"), null, getOneTimeCostMonthRightCellStyle());
					// Column May
					createCellForMonth(rows, CellReference.convertColStringToIndex("M"), null, getOneTimeCostMonthLeftCellStyle());
					// Column MaySum
					createCellForMonth(rows, CellReference.convertColStringToIndex("N"), null, getOneTimeCostMonthRightCellStyle());
					// Column Jun
					createCellForMonth(rows, CellReference.convertColStringToIndex("O"), null, getOneTimeCostMonthLeftCellStyle());
					// Column JunSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("P"), null, getOneTimeCostMonthRightCellStyle());
					// Column Jul
					createCellForMonth(rows, CellReference.convertColStringToIndex("Q"), null, getOneTimeCostMonthLeftCellStyle());
					// Column JulSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("R"), null, getOneTimeCostMonthRightCellStyle());
					// Column Aug
					createCellForMonth(rows, CellReference.convertColStringToIndex("S"), null, getOneTimeCostMonthLeftCellStyle());
					// Column AugSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("T"), null, getOneTimeCostMonthRightCellStyle());
					// Column Sep
					createCellForMonth(rows, CellReference.convertColStringToIndex("U"), null, getOneTimeCostMonthLeftCellStyle());
					// Column SepSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("V"), null, getOneTimeCostMonthRightCellStyle());
					// Column Oct
					createCellForMonth(rows, CellReference.convertColStringToIndex("W"), null, getOneTimeCostMonthLeftCellStyle());
					// Column OctSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("X"), null, getOneTimeCostMonthRightCellStyle());
					// Column Nov
					createCellForMonth(rows, CellReference.convertColStringToIndex("Y"), null, getOneTimeCostMonthLeftCellStyle());
					// Column NovSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("Z"), null, getOneTimeCostMonthRightCellStyle());
					// Column Dec
					createCellForMonth(rows, CellReference.convertColStringToIndex("AA"), null, getOneTimeCostMonthLeftCellStyle());
					// Column DecSum
					createCellForMonth(rows, CellReference.convertColStringToIndex("AB"), null, getOneTimeCostMonthLastCellStyle());
				}
			}
			if  (i == listData.size() - 1 ) {
				//int rowIndexStartConst = 4;
				// Merge for "type" column
				CellRangeAddress mergedCellType = new CellRangeAddress(rowIndexStartParmater, rowIndexStart - 1, CellReference.convertColStringToIndex("C"), CellReference.convertColStringToIndex("C"));
				aSHEET.addMergedRegion(mergedCellType);
				aSHEET.getRow(rowIndexStartParmater).setHeight((short)-1);
			}
			rowIndexStart++;
		}
		return rowIndexStart;
	}
	
	/**
	 * Sum qty and sum amount on DataK Yearly
	 * 
	 * @param aSHEET
	 * @param aWORKBOOK
	 * @param cellStyle
	 * @param rowIndexStart
	 * @param rowStartSumTotal
	 * @param rowEndSumTotal
	 * @param isMonthlyCost
	 */
	private void sumTotalDataKYearly(Sheet aSHEET, Workbook aWORKBOOK, DataKYearlyCellStyle cellStyle, int rowIndexStart,
			int rowStartSumTotal, int rowEndSumTotal, String costType) {
		// Get row
		Row rows = CellUtil.getRow(rowIndexStart, aSHEET);
		
		// Column Cost Type
		checkNullValueCells(costType, CellReference.convertColStringToIndex("C"), rows, cellStyle.getCostTypeCellStyle());
		// Column Total
		checkNullValueCells(Constant.TOTAL, CellReference.convertColStringToIndex("D"), rows, cellStyle.getTotalCellStyle());
		checkNullValueCells(Constant.TOTAL, CellReference.convertColStringToIndex("E"), rows, cellStyle.getTotalCellStyle());
		// Merge Column Total
		aSHEET.addMergedRegion(new CellRangeAddress(rowIndexStart, rowIndexStart, CellReference.convertColStringToIndex("D"), CellReference.convertColStringToIndex("E")));
		
		// Column Jan Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("F"), "", cellStyle.getTotalFirstCellStyle()).setCellFormula("SUM(F"+rowStartSumTotal+":F"+rowEndSumTotal+")");
		// Column Jan Amount
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("G"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(G"+rowStartSumTotal+":G"+rowEndSumTotal+")");
		
		// Column Feb Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("H"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(H"+rowStartSumTotal+":H"+rowEndSumTotal+")");
		// Column Feb Amount
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("I"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(I"+rowStartSumTotal+":I"+rowEndSumTotal+")");
		
		// Column Mar Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("J"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(J"+rowStartSumTotal+":J"+rowEndSumTotal+")");
		// Column MarSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("K"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(K"+rowStartSumTotal+":K"+rowEndSumTotal+")");
		
		// Column Apr Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("L"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(L"+rowStartSumTotal+":L"+rowEndSumTotal+")");
		// Column AprSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("M"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(M"+rowStartSumTotal+":M"+rowEndSumTotal+")");
		
		// Column May Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("N"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(N"+rowStartSumTotal+":N"+rowEndSumTotal+")");
		// Column MaySum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("O"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(O"+rowStartSumTotal+":O"+rowEndSumTotal+")");
		
		// Column Jun Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("P"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(P"+rowStartSumTotal+":P"+rowEndSumTotal+")");
		// Column JunSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("Q"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(Q"+rowStartSumTotal+":Q"+rowEndSumTotal+")");
		
		// Column Jul Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("R"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(R"+rowStartSumTotal+":R"+rowEndSumTotal+")");
		// Column JulSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("S"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(S"+rowStartSumTotal+":S"+rowEndSumTotal+")");
		
		// Column Aug Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("T"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(T"+rowStartSumTotal+":T"+rowEndSumTotal+")");
		// Column AugSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("U"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(U"+rowStartSumTotal+":U"+rowEndSumTotal+")");
		
		// Column Sep Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("V"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(V"+rowStartSumTotal+":V"+rowEndSumTotal+")");
		// Column SepSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("W"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(W"+rowStartSumTotal+":W"+rowEndSumTotal+")");
		
		// Column Oct Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("X"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(X"+rowStartSumTotal+":X"+rowEndSumTotal+")");
		// Column OctSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("Y"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(Y"+rowStartSumTotal+":Y"+rowEndSumTotal+")");
		
		// Column Nov Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("Z"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(Z"+rowStartSumTotal+":Z"+rowEndSumTotal+")");
		// Column NovSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("AA"), "", cellStyle.getTotalAmountCellStyle()).setCellFormula("SUM(AA"+rowStartSumTotal+":AA"+rowEndSumTotal+")");
		
		// Column Dec Qty
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("AB"), "", cellStyle.getTotalQtyCellStyle()).setCellFormula("SUM(AB"+rowStartSumTotal+":AB"+rowEndSumTotal+")");
		// Column DecSum
		CellUtil.createCell(rows, CellReference.convertColStringToIndex("AC"), "", cellStyle.getTotalLastCellStyle()).setCellFormula("SUM(AC"+rowStartSumTotal+":AC"+rowEndSumTotal+")");
	}
	
	/**
	 * Fill data to Excel PC Lease Invoice Data (Y)
	 * 
	 * @param aWORKBOOK
	 * @param aSHEET
	 * @param List<Pad0011Bean>
	 * @param List<Pad0011Bean>
	 */
	private int loadListDataKYearlyToExcel(Sheet aSHEET, Workbook aWORKBOOK,
			List<ExcelPCLeaseInvoiceDataKYearlyBean> listData, int rowIndexStartParmater,
			DataKYearlyCellStyle cellStyle) throws Exception {
		
		// Position start fill data
		int rowIndexStart = rowIndexStartParmater;
		int rowStartSumTotal = rowIndexStartParmater + 1;
		int rowStartMergeCostType = rowIndexStart;
		int rowStartMergeCategory = rowIndexStart;
		// Row Height Default
		short rowHeightDefault = CellUtil.getRow(3, aSHEET).getHeight();
		// Top Row
		boolean isTopRow = false;
		
		
		// If listData is not null or empty
		if (listData != null && listData.size() > 0) {
			for (int i = 0; i < listData.size(); i++) {
				
				// Get data 
				ExcelPCLeaseInvoiceDataKYearlyBean dataKYearlyBean = listData.get(i);
				
				// Check Top Row
				if (i == 0) {
					isTopRow = true;
				}
				
				if (dataKYearlyBean != null) {
					// Current row
					Row rows = CellUtil.getRow(rowIndexStart, aSHEET);
					rows.setHeight(rowHeightDefault);
					// Set cell style
					cellStyle.setCellStyleByCostType(dataKYearlyBean.getCostType());
					cellStyle.setCellStyleQtyAmountByRow(isTopRow);
					isTopRow = false;
					
					// Fill data to cell
					// Column Cost Type
					checkNullValueCells(dataKYearlyBean.getCostType(), CellReference.convertColStringToIndex("C"), rows, cellStyle.getCostTypeCellStyle());
					// Column category
					checkNullValueCells(dataKYearlyBean.getCategoryName(), CellReference.convertColStringToIndex("D"), rows, cellStyle.getCategoryCellStyle());
					// Column item
					checkNullValueCells(dataKYearlyBean.getItemName(), CellReference.convertColStringToIndex("E"), rows, cellStyle.getItemCellStyle());
					
					// Column Jan Qty
					createCell(rows, CellReference.convertColStringToIndex("F"), dataKYearlyBean.getJanQty(), cellStyle.getQtyFirstCellStyle());
					// Column Jan Amount
					createCell(rows, CellReference.convertColStringToIndex("G"), dataKYearlyBean.getJanAmount(), cellStyle.getAmountCellStyle());
					
					// Column Feb Qty
					createCell(rows, CellReference.convertColStringToIndex("H"), dataKYearlyBean.getFebQty(), cellStyle.getQtyCellStyle());
					// Column Feb Amount
					createCell(rows, CellReference.convertColStringToIndex("I"), dataKYearlyBean.getFebAmount(), cellStyle.getAmountCellStyle());
					
					// Column Mar Qty
					createCell(rows, CellReference.convertColStringToIndex("J"), dataKYearlyBean.getMarQty(), cellStyle.getQtyCellStyle());
					// Column Mar Amount
					createCell(rows, CellReference.convertColStringToIndex("K"), dataKYearlyBean.getMarAmount(), cellStyle.getAmountCellStyle());
					
					// Column Apr Qty
					createCell(rows, CellReference.convertColStringToIndex("L"), dataKYearlyBean.getAprQty(), cellStyle.getQtyCellStyle());
					// Column Apr Amount
					createCell(rows, CellReference.convertColStringToIndex("M"), dataKYearlyBean.getAprAmount(), cellStyle.getAmountCellStyle());
					
					// Column May Qty
					createCell(rows, CellReference.convertColStringToIndex("N"), dataKYearlyBean.getMayQty(), cellStyle.getQtyCellStyle());
					// Column May Amount
					createCell(rows, CellReference.convertColStringToIndex("O"), dataKYearlyBean.getMayAmount(), cellStyle.getAmountCellStyle());
					
					// Column Jun Qty
					createCell(rows, CellReference.convertColStringToIndex("P"), dataKYearlyBean.getJunQty(), cellStyle.getQtyCellStyle());
					// Column Jun Amount
					createCell(rows, CellReference.convertColStringToIndex("Q"), dataKYearlyBean.getJunAmount(), cellStyle.getAmountCellStyle());
					
					// Column Jul Qty
					createCell(rows, CellReference.convertColStringToIndex("R"), dataKYearlyBean.getJulQty(), cellStyle.getQtyCellStyle());
					// Column Jul Amount
					createCell(rows, CellReference.convertColStringToIndex("S"), dataKYearlyBean.getJulAmount(), cellStyle.getAmountCellStyle());
					
					// Column Aug Qty
					createCell(rows, CellReference.convertColStringToIndex("T"), dataKYearlyBean.getAugQty(), cellStyle.getQtyCellStyle());
					// Column Aug Amount
					createCell(rows, CellReference.convertColStringToIndex("U"), dataKYearlyBean.getAugAmount(), cellStyle.getAmountCellStyle());
					
					// Column Sep Qty
					createCell(rows, CellReference.convertColStringToIndex("V"), dataKYearlyBean.getSepQty(), cellStyle.getQtyCellStyle());
					// Column Sep Amount
					createCell(rows, CellReference.convertColStringToIndex("W"), dataKYearlyBean.getSepAmount(), cellStyle.getAmountCellStyle());
					
					// Column Oct Qty
					createCell(rows, CellReference.convertColStringToIndex("X"), dataKYearlyBean.getOctQty(), cellStyle.getQtyCellStyle());
					// Column Oct Amount
					createCell(rows, CellReference.convertColStringToIndex("Y"), dataKYearlyBean.getOctAmount(), cellStyle.getAmountCellStyle());
					
					// Column Nov Qty
					createCell(rows, CellReference.convertColStringToIndex("Z"), dataKYearlyBean.getNovQty(), cellStyle.getQtyCellStyle());
					// Column Nov Amount
					createCell(rows, CellReference.convertColStringToIndex("AA"), dataKYearlyBean.getNovAmount(), cellStyle.getAmountCellStyle());
					
					// Column Dec Qty
					createCell(rows, CellReference.convertColStringToIndex("AB"), dataKYearlyBean.getDecQty(), cellStyle.getQtyCellStyle());
					// Column Dec Amount
					createCell(rows, CellReference.convertColStringToIndex("AC"), dataKYearlyBean.getDecAmount(), cellStyle.getAmountLastCellStyle());
					
					// Last element in listData
					if (i == listData.size() - 1){
						// Get total row
						rows = CellUtil.getRow(rowIndexStart + 1, aSHEET);
						rows.setHeight(rowHeightDefault);
						
						// Fill Total Row
						sumTotalDataKYearly(aSHEET, aWORKBOOK, cellStyle, rowIndexStart + 1, rowStartSumTotal, rowIndexStart + 1, dataKYearlyBean.getCostType());
						
						// Merge column cost type
						CellRangeAddress mergedCellCostType = new CellRangeAddress(rowStartMergeCostType, rowIndexStart+1, CellReference.convertColStringToIndex("C"), CellReference.convertColStringToIndex("C"));
						aSHEET.addMergedRegion(mergedCellCostType);
						
						// Merge Column Category	
						if ((rowIndexStart - rowStartMergeCategory) > 0) {
							// Merge column Category
							CellRangeAddress mergedCellCategory = new CellRangeAddress(rowStartMergeCategory, rowIndexStart, CellReference.convertColStringToIndex("D"), CellReference.convertColStringToIndex("D"));
							aSHEET.addMergedRegion(mergedCellCategory);
						}
						
					} else {
						// Merge Column Category
						if (!dataKYearlyBean.getCategoryCd().equals(listData.get(i + 1).getCategoryCd())) {
							if ((rowIndexStart - rowStartMergeCategory) > 0) {
								// Merge column Category
								CellRangeAddress mergedCellCategory = new CellRangeAddress(rowStartMergeCategory, rowIndexStart, CellReference.convertColStringToIndex("D"), CellReference.convertColStringToIndex("D"));
								aSHEET.addMergedRegion(mergedCellCategory);
							}
							rowStartMergeCategory = rowIndexStart + 1;
						}
						
						// Last element Monthly Cost in list
						if (dataKYearlyBean.getCostType().equals(Constant.MONTHLY_COST) && listData.get(i + 1).getCostType().equals(Constant.ONE_TIME_COST)) {
							rows = CellUtil.getRow(rowIndexStart + 1, aSHEET);
							rows.setHeight(rowHeightDefault);
							
							// Fill data to total (One time Cost) row
							sumTotalDataKYearly(aSHEET, aWORKBOOK, cellStyle, rowIndexStart + 1, rowStartSumTotal, rowIndexStart + 1, dataKYearlyBean.getCostType());
							
							// Merge column cost type
							CellRangeAddress mergedCellCostType = new CellRangeAddress(rowStartMergeCostType, rowIndexStart+1, CellReference.convertColStringToIndex("C"), CellReference.convertColStringToIndex("C"));
							aSHEET.addMergedRegion(mergedCellCostType);
							
							// Set Row Index Start OTC
							rowStartSumTotal = rowIndexStart + 3;
							rowStartMergeCostType = rowStartSumTotal - 1;
							rowIndexStart++;
							rowStartMergeCategory = rowIndexStart + 1;
							// Set Top Row
							isTopRow = true;
						}
					}
					// Next row
					rowIndexStart++;
				}
			}
		}
		// Return Row Index Current
		return rowIndexStart;
	}
	
	private void getTotal(Row rows, int startIndexOrderId, int endIndexOrderId, CellStyle totalStyleLeft, CellStyle itemNameCellStyle, CellStyle totalQtyStyle, CellStyle totalAmountStyle) {
		totalStyleLeft.setBorderRight(CellStyle.BORDER_THIN);
		CellUtil.createCell(rows, 1, "", totalQtyStyle).setCellValue("");
		CellUtil.createCell(rows, 2, "", totalQtyStyle).setCellValue("");
		CellUtil.createCell(rows, 3, "", totalStyleLeft).setCellValue("Total");
		CellUtil.createCell(rows, 4, "", totalStyleLeft).setCellValue("Total");
		startIndexOrderId++;
		// Jan
		
		//itemNameCellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
		CellUtil.createCell(rows, 5, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 6, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 7, "", totalQtyStyle).setCellFormula("SUM(H"+startIndexOrderId+":H"+endIndexOrderId+")");
		CellUtil.createCell(rows, 8, "", totalQtyStyle).setCellFormula("SUM(I"+startIndexOrderId+":I"+endIndexOrderId+")");
		CellUtil.createCell(rows, 9, "", totalAmountStyle).setCellFormula("SUM(J"+startIndexOrderId+":J"+endIndexOrderId+")");
		CellUtil.createCell(rows, 10, "", totalAmountStyle).setCellFormula("SUM(K"+startIndexOrderId+":K"+endIndexOrderId+")");
		
		// Feb
		CellUtil.createCell(rows, 11, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 12, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 13, "", totalQtyStyle).setCellFormula("SUM(N"+startIndexOrderId+":N"+endIndexOrderId+")");
		CellUtil.createCell(rows, 14, "", totalQtyStyle).setCellFormula("SUM(O"+startIndexOrderId+":O"+endIndexOrderId+")");
		CellUtil.createCell(rows, 15, "", totalAmountStyle).setCellFormula("SUM(P"+startIndexOrderId+":P"+endIndexOrderId+")");
		CellUtil.createCell(rows, 16, "", totalAmountStyle).setCellFormula("SUM(Q"+startIndexOrderId+":Q"+endIndexOrderId+")");
		
		// Mar
		CellUtil.createCell(rows, 17, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 18, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 19, "", totalQtyStyle).setCellFormula("SUM(T"+startIndexOrderId+":T"+endIndexOrderId+")");
		CellUtil.createCell(rows, 20, "", totalQtyStyle).setCellFormula("SUM(U"+startIndexOrderId+":U"+endIndexOrderId+")");
		CellUtil.createCell(rows, 21, "", totalAmountStyle).setCellFormula("SUM(V"+startIndexOrderId+":V"+endIndexOrderId+")");
		CellUtil.createCell(rows, 22, "", totalAmountStyle).setCellFormula("SUM(W"+startIndexOrderId+":W"+endIndexOrderId+")");
		// Apr
		CellUtil.createCell(rows, 23, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 24, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 25, "", totalQtyStyle).setCellFormula("SUM(Z"+startIndexOrderId+":Z"+endIndexOrderId+")");
		CellUtil.createCell(rows, 26, "", totalQtyStyle).setCellFormula("SUM(AA"+startIndexOrderId+":AA"+endIndexOrderId+")");
		CellUtil.createCell(rows, 27, "", totalAmountStyle).setCellFormula("SUM(AB"+startIndexOrderId+":AB"+endIndexOrderId+")");
		CellUtil.createCell(rows, 28, "", totalAmountStyle).setCellFormula("SUM(AC"+startIndexOrderId+":AC"+endIndexOrderId+")");
		// May
		CellUtil.createCell(rows, 29, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 30, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 31, "", totalQtyStyle).setCellFormula("SUM(AF"+startIndexOrderId+":AF"+endIndexOrderId+")");
		CellUtil.createCell(rows, 32, "", totalQtyStyle).setCellFormula("SUM(AG"+startIndexOrderId+":AG"+endIndexOrderId+")");
		CellUtil.createCell(rows, 33, "", totalAmountStyle).setCellFormula("SUM(AH"+startIndexOrderId+":AH"+endIndexOrderId+")");
		CellUtil.createCell(rows, 34, "", totalAmountStyle).setCellFormula("SUM(AI"+startIndexOrderId+":AI"+endIndexOrderId+")");
		// Jun
		CellUtil.createCell(rows, 35, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 36, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 37, "", totalQtyStyle).setCellFormula("SUM(AL"+startIndexOrderId+":AL"+endIndexOrderId+")");
		CellUtil.createCell(rows, 38, "", totalQtyStyle).setCellFormula("SUM(AM"+startIndexOrderId+":AM"+endIndexOrderId+")");
		CellUtil.createCell(rows, 39, "", totalAmountStyle).setCellFormula("SUM(AN"+startIndexOrderId+":AN"+endIndexOrderId+")");
		CellUtil.createCell(rows, 40, "", totalAmountStyle).setCellFormula("SUM(AO"+startIndexOrderId+":AO"+endIndexOrderId+")");
		// Jul
		CellUtil.createCell(rows, 41, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 42, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 43, "", totalQtyStyle).setCellFormula("SUM(AR"+startIndexOrderId+":AR"+endIndexOrderId+")");
		CellUtil.createCell(rows, 44, "", totalQtyStyle).setCellFormula("SUM(AS"+startIndexOrderId+":AS"+endIndexOrderId+")");
		CellUtil.createCell(rows, 45, "", totalAmountStyle).setCellFormula("SUM(AT"+startIndexOrderId+":AT"+endIndexOrderId+")");
		CellUtil.createCell(rows, 46, "", totalAmountStyle).setCellFormula("SUM(AU"+startIndexOrderId+":AU"+endIndexOrderId+")");
		// Aug
		CellUtil.createCell(rows, 47, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 48, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 49, "", totalQtyStyle).setCellFormula("SUM(AX"+startIndexOrderId+":AX"+endIndexOrderId+")");
		CellUtil.createCell(rows, 50, "", totalQtyStyle).setCellFormula("SUM(AY"+startIndexOrderId+":AY"+endIndexOrderId+")");
		CellUtil.createCell(rows, 51, "", totalAmountStyle).setCellFormula("SUM(AZ"+startIndexOrderId+":AZ"+endIndexOrderId+")");
		CellUtil.createCell(rows, 52, "", totalAmountStyle).setCellFormula("SUM(BA"+startIndexOrderId+":BA"+endIndexOrderId+")");
		// Sep
		CellUtil.createCell(rows, 53, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 54, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 55, "", totalQtyStyle).setCellFormula("SUM(BD"+startIndexOrderId+":BD"+endIndexOrderId+")");
		CellUtil.createCell(rows, 56, "", totalQtyStyle).setCellFormula("SUM(BE"+startIndexOrderId+":BE"+endIndexOrderId+")");
		CellUtil.createCell(rows, 57, "", totalAmountStyle).setCellFormula("SUM(BF"+startIndexOrderId+":BF"+endIndexOrderId+")");
		CellUtil.createCell(rows, 58, "", totalAmountStyle).setCellFormula("SUM(BG"+startIndexOrderId+":BG"+endIndexOrderId+")");
		
		// Oct
		CellUtil.createCell(rows, 59, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 60, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 61, "", totalQtyStyle).setCellFormula("SUM(BJ"+startIndexOrderId+":BJ"+endIndexOrderId+")");
		CellUtil.createCell(rows, 62, "", totalQtyStyle).setCellFormula("SUM(BK"+startIndexOrderId+":BK"+endIndexOrderId+")");
		CellUtil.createCell(rows, 63, "", totalAmountStyle).setCellFormula("SUM(BL"+startIndexOrderId+":BL"+endIndexOrderId+")");
		CellUtil.createCell(rows, 64, "", totalAmountStyle).setCellFormula("SUM(BM"+startIndexOrderId+":BM"+endIndexOrderId+")");
		
		// Nov
		CellUtil.createCell(rows, 65, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 66, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 67, "", totalQtyStyle).setCellFormula("SUM(BP"+startIndexOrderId+":BP"+endIndexOrderId+")");
		CellUtil.createCell(rows, 68, "", totalQtyStyle).setCellFormula("SUM(BQ"+startIndexOrderId+":BQ"+endIndexOrderId+")");
		CellUtil.createCell(rows, 69, "", totalAmountStyle).setCellFormula("SUM(BR"+startIndexOrderId+":BR"+endIndexOrderId+")");
		CellUtil.createCell(rows, 70, "", totalAmountStyle).setCellFormula("SUM(BS"+startIndexOrderId+":BS"+endIndexOrderId+")");
		
		// Dec
		CellUtil.createCell(rows, 71, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 72, "", itemNameCellStyle).setCellValue("-");
		CellUtil.createCell(rows, 73, "", totalQtyStyle).setCellFormula("SUM(BV"+startIndexOrderId+":BV"+endIndexOrderId+")");
		CellUtil.createCell(rows, 74, "", totalQtyStyle).setCellFormula("SUM(BW"+startIndexOrderId+":BW"+endIndexOrderId+")");
		CellUtil.createCell(rows, 75, "", totalAmountStyle).setCellFormula("SUM(BX"+startIndexOrderId+":BX"+endIndexOrderId+")");
		CellUtil.createCell(rows, 76, "", totalAmountStyle).setCellFormula("SUM(BY"+startIndexOrderId+":BY"+endIndexOrderId+")");
		
		
	}
	
	/**
	 * Merge column Name
	 * 
	 * @param rowIndex
	 */
	public void mergeColumnItemName(Sheet aSHEET, int rowIndex){		
		// Column Jan
		CellRangeAddress mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("F"), CellReference.convertColStringToIndex("G"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Feb
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("L"), CellReference.convertColStringToIndex("M"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Mar
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("R"), CellReference.convertColStringToIndex("S"));
		aSHEET.addMergedRegion(mergedCell);
		// Apr
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("X"), CellReference.convertColStringToIndex("Y"));
		aSHEET.addMergedRegion(mergedCell);
		// May
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("AD"), CellReference.convertColStringToIndex("AE"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Jun
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("AJ"), CellReference.convertColStringToIndex("AK"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Jul
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("AP"), CellReference.convertColStringToIndex("AQ"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Aug
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("AV"), CellReference.convertColStringToIndex("AW"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Sep
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("BB"), CellReference.convertColStringToIndex("BC"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Oct
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("BH"), CellReference.convertColStringToIndex("BI"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Nov
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("BN"), CellReference.convertColStringToIndex("BO"));
		aSHEET.addMergedRegion(mergedCell);
		// Column Dec
		mergedCell = new CellRangeAddress(rowIndex, rowIndex, CellReference.convertColStringToIndex("BT"), CellReference.convertColStringToIndex("BU"));
		aSHEET.addMergedRegion(mergedCell);
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
	 * @param start index of row
	 * @param month cost
	 * @param one time cost
	 * 
	 * @return type style (Month cost or one time cost)
	 */
	private CellStyle checkTypeCost(boolean typeCost, CellStyle monthCost, CellStyle oneTimeCost) {
		if (!typeCost) {
			return oneTimeCost;
		}
		return monthCost;
	}
	
	/**
	 * 
	 * Set cell formula
	 * 
	 * @param rows
	 * @param pad0011Bean
	 * @param cellStyle
	 * @param cellStyleLast
	 * @param startIndex
	 * @param endIndex
	 * 
	 */
	private void sumTotalQty(Row rows, Pad0011Bean pad0011Bean, CellStyle cellStyleNumber, CellStyle cellStyle, CellStyle cellStyleLast, int startIndex, int endIndex, boolean isSum, CellStyle numberDecimal2CellStyleTypeClone, CellStyle numberDecimal2LastCellStyleTypeClone) {
		String billableQuantity = "";
		String deliverQuantity = "";
		String billableAmount = "";
		String deliverAmount = "";
		if (pad0011Bean != null) {
			billableQuantity = pad0011Bean.getBillableQuantity();
			deliverQuantity = pad0011Bean.getDeliverQuantity();
			billableAmount = pad0011Bean.getBillableAmount();
			deliverAmount = pad0011Bean.getDeliverAmount();
		}
		if (isSum) {
			// Column Billable Qty.
			CellUtil.createCell(rows, 6, billableQuantity, cellStyle).setCellFormula("SUM(G"+startIndex+":G"+endIndex+")");
			
			// Column Deliver Qty.
			CellUtil.createCell(rows, 7, deliverQuantity, cellStyle).setCellFormula("SUM(H"+startIndex+":H"+endIndex+")");
			
			// Column Billable Amount.
			CellUtil.createCell(rows, 8, billableAmount, cellStyleNumber).setCellFormula("SUM(I"+startIndex+":I"+endIndex+")");
			
			// Column Deliver Amount.
			CellUtil.createCell(rows, 9, deliverAmount, cellStyleLast).setCellFormula("SUM(J"+startIndex+":J"+endIndex+")");
		} else {
			// Column Billable Qty.
			createCell(rows, 6, billableQuantity, cellStyleNumber);
			
			// Column Deliver Qty.
			createCell(rows, 7, deliverQuantity, cellStyleNumber);
			
			// Column Billable Amount.
			createCell(rows, 8, billableAmount, numberDecimal2CellStyleTypeClone);
			
			// Column Deliver Amount.
			createCell(rows, 9, deliverAmount, numberDecimal2LastCellStyleTypeClone);
			
		}
	}
	
	/**
	 * Creates a cell, gives it a value, and applies a style if provided
	 *
	 * @param  row     the row to create the cell in
	 * @param  column  the column index to create the cell in
	 * @param  value   The value of the cell
	 * @param  style   If the style is not null, then set
	 * @return         A new Cell
	 */
	public static Cell createCell(Row row, int column, String value, CellStyle style) {
		Cell cell = CellUtil.getCell(row, column);
		//LOG.info(value + "---" + Float.parseFloat(value));
		if (!StringUtil.isNullOrEmpty(value)) {
			cell.setCellValue(new BigDecimal(value).doubleValue());
			
		} else {
			cell.setCellValue("");
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		return cell;
	}
	
	public static Cell createCellForMonth(Row row, int column, String value, CellStyle style) {
		Cell cell = CellUtil.getCell(row, column);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		//LOG.info(value + "---" + Float.parseFloat(value));
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (!StringUtil.isNullOrEmpty(value)) {
			if (column < (month*2 + 4)) {
				cell.setCellValue(new BigDecimal(value).doubleValue());
			} else {
				cell.setCellValue("");
			}
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		return cell;
	}
	
	public static Cell createCell1(Row row, int column, String value, CellStyle style) {
		Cell cell = CellUtil.getCell(row, column);
		//LOG.info(value + "---" + Float.parseFloat(value));
		if (!StringUtil.isNullOrEmpty(value)) {
			cell.setCellValue(Integer.parseInt(value));
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		return cell;
	}
	
	/**
	 * create file name
	 * @return
	 */
	public String createFileNameReport() {
		String fileName = "";
		// Excel PC Lease Invoice Data (K)
		if (outputLeaseInvoiceDataK) {
			fileName = "PC_Lease_Invoice_DataK_";
		}
		
		// PC Lease Invoice Data K Download (Yearly)
		if (outputLeaseInvoiceDataKYearly) {
			fileName = "PC_Lease_Invoice_DataK_Yearly_";
		}
		
		// Excel PC Lease Invoice Data (S)
		if (outputLeaseInvoiceDataS) {
			fileName = "PC_Lease_Invoice_DataS_";
		}
		
		// PC Lease Data Download
		if (outputLeaseData) {
			fileName = "PC_Lease_Data_";	
		}
		Date date = new Date();
        fileName += DateUtil.formatDate(date, DateFormat.yyMMdd);
        return fileName;
	}
	
	private void loadMegreType(Workbook aWORKBOOK, Sheet aSHEET, int rowIndexStartConst, int rowIndexStart, String type, CellStyle cellStyle) {
		// Column Type
		int columnIndex = 0;
		
		// Merge for "type" column
		CellRangeAddress mergedCellType = new CellRangeAddress(rowIndexStartConst, rowIndexStart, columnIndex, columnIndex);
		aSHEET.addMergedRegion(mergedCellType);
		RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		checkNullValueCells(type, columnIndex, aSHEET.getRow(rowIndexStartConst), cellStyle);
	}
	
	/**
	 * Get all CellStyle from DataK_Yearly_Template to DataKYearlyCellStyle
	 * 
	 * @param aWORKBOOK
	 * @param cellStyle
	 */
	private void getDataKYearlyCellStyle(Workbook aWORKBOOK, DataKYearlyCellStyle cellStyle) throws Exception {
		
		// Get Sheet DATAK_YEARLY_SHEET_NAME_TEMPLATE
		Sheet sheet = getWorkbook().getSheet(DATAK_YEARLY_SHEET_NAME_TEMPLATE);
		
		// Get Cell Style Month Columns
		// Set Company Cell Style
		cellStyle.setCompanyCellStyle(sheet.getRow(0).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		// Set Qty Top Cell Style
		cellStyle.setQtyTopCellStyle(sheet.getRow(4).getCell(CellReference.convertColStringToIndex("H")).getCellStyle());
		// Set Qty Mid Cell Style
		cellStyle.setQtyMidCellStyle(sheet.getRow(5).getCell(CellReference.convertColStringToIndex("H")).getCellStyle());
		// Set Qty Top First Cell Style
		cellStyle.setQtyTopFirstCellStyle(sheet.getRow(4).getCell(CellReference.convertColStringToIndex("F")).getCellStyle());
		// Set Qty Mid First Cell Style
		cellStyle.setQtyMidFirstCellStyle(sheet.getRow(5).getCell(CellReference.convertColStringToIndex("F")).getCellStyle());
		// Set Amount Top Cell Style
		cellStyle.setAmountTopCellStyle(sheet.getRow(4).getCell(CellReference.convertColStringToIndex("G")).getCellStyle());
		// Set Amount Mid Cell Style
		cellStyle.setAmountMidCellStyle(sheet.getRow(5).getCell(CellReference.convertColStringToIndex("G")).getCellStyle());
		// Set Amount Top Last Cell Style
		cellStyle.setAmountTopLastCellStyle(sheet.getRow(4).getCell(CellReference.convertColStringToIndex("AC")).getCellStyle());
		// Set Amount Mid Last Cell Style
		cellStyle.setAmountMidLastCellStyle(sheet.getRow(5).getCell(CellReference.convertColStringToIndex("AC")).getCellStyle());
		
		// Get Cell Style Monthly Cost(MRC)
		// Set Cost Type MRC Cell Style
		cellStyle.setCostTypeMRCCellStyle(sheet.getRow(4).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		// Set Category Top MRC Cell Style
		cellStyle.setCategoryTopMRCCellStyle(sheet.getRow(4).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		// Set Category MRC Cell Style
		cellStyle.setCategoryMRCCellStyle(sheet.getRow(6).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		// Set Item Top MRC Cell Style
		cellStyle.setItemTopMRCCellStlye(sheet.getRow(4).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		// Set Item MRC Cell Style
		cellStyle.setItemMRCCellStyle(sheet.getRow(5).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		//Set Total MRC Cell Style
		cellStyle.setTotalMRCCellStyle(sheet.getRow(10).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		//Set Total Qty MRC Cell Style
		cellStyle.setTotalQtyMRCCellStyle(sheet.getRow(10).getCell(CellReference.convertColStringToIndex("H")).getCellStyle());
		// Set Total Amount MRC Cell Style
		cellStyle.setTotalAmountMRCCellStyle(sheet.getRow(10).getCell(CellReference.convertColStringToIndex("G")).getCellStyle());
		// Set Total First MRC Cell Style
		cellStyle.setTotalFirstMRCCellStyle(sheet.getRow(10).getCell(CellReference.convertColStringToIndex("F")).getCellStyle());
		// Set Total Last MRC Cell Style
		cellStyle.setTotalLastMRCCellStyle(sheet.getRow(10).getCell(CellReference.convertColStringToIndex("AC")).getCellStyle());
		
		// Get Cell Style One Time Cost(OTC)
		// Set Cost Type OTC Cell Style
		cellStyle.setCostTypeOTCCellStyle(sheet.getRow(11).getCell(CellReference.convertColStringToIndex("C")).getCellStyle());
		// Set Category Top OTC Cell Style
		cellStyle.setCategoryTopOTCCellStyle(sheet.getRow(11).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		// Set Category OTC Cell Style
		cellStyle.setCategoryOTCCellStyle(sheet.getRow(12).getCell(CellReference.convertColStringToIndex("D")).getCellStyle());
		// Set Item Top OTC Cell Style
		cellStyle.setItemTopOTCCellStlye(sheet.getRow(11).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		// Set Item OTC Cell Style
		cellStyle.setItemOTCCellStyle(sheet.getRow(12).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		//Set Total OTC Cell Style
		cellStyle.setTotalOTCCellStyle(sheet.getRow(15).getCell(CellReference.convertColStringToIndex("E")).getCellStyle());
		//Set Total Qty OTC Cell Style
		cellStyle.setTotalQtyOTCCellStyle(sheet.getRow(15).getCell(CellReference.convertColStringToIndex("H")).getCellStyle());
		// Set Total Amount OTC Cell Style
		cellStyle.setTotalAmountOTCCellStyle(sheet.getRow(15).getCell(CellReference.convertColStringToIndex("G")).getCellStyle());
		// Set Total First OTC Cell Style
		cellStyle.setTotalFirstOTCCellStyle(sheet.getRow(15).getCell(CellReference.convertColStringToIndex("F")).getCellStyle());
		// Set Total Last OTC Cell Style
		cellStyle.setTotalLastOTCCellStyle(sheet.getRow(15).getCell(CellReference.convertColStringToIndex("AC")).getCellStyle());
		
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

	public CellStyle getBlueCellStyleType() {
		return blueCellStyleType;
	}

	public void setBlueCellStyleType(CellStyle blueCellStyleType) {
		this.blueCellStyleType = blueCellStyleType;
	}

	public CellStyle getOrderCellStyle() {
		return orderCellStyle;
	}

	public void setOrderCellStyle(CellStyle orderCellStyle) {
		this.orderCellStyle = orderCellStyle;
	}

	public CellStyle getCategoryAddonCellStyle() {
		return categoryAddonCellStyle;
	}

	public void setCategoryAddonCellStyle(CellStyle categoryAddonCellStyle) {
		this.categoryAddonCellStyle = categoryAddonCellStyle;
	}

	public CellStyle getOrangeCellStyle() {
		return orangeCellStyle;
	}

	public void setOrangeCellStyle(CellStyle orangeCellStyle) {
		this.orangeCellStyle = orangeCellStyle;
	}

	public CellStyle getTotalBlueCellStyleType() {
		return totalBlueCellStyleType;
	}

	public void setTotalBlueCellStyleType(CellStyle totalBlueCellStyleType) {
		this.totalBlueCellStyleType = totalBlueCellStyleType;
	}

	public CellStyle getNumberBlueCellStyleType() {
		return numberBlueCellStyleType;
	}

	public void setNumberBlueCellStyleType(CellStyle numberBlueCellStyleType) {
		this.numberBlueCellStyleType = numberBlueCellStyleType;
	}

	public CellStyle getNumberDeliverAmountBottomCellStyle() {
		return numberDeliverAmountBottomCellStyle;
	}

	public void setNumberDeliverAmountBottomCellStyle(CellStyle numberDeliverAmountBottomCellStyle) {
		this.numberDeliverAmountBottomCellStyle = numberDeliverAmountBottomCellStyle;
	}

	public CellStyle getGreenCellStyleType() {
		return greenCellStyleType;
	}

	public void setGreenCellStyleType(CellStyle greenCellStyleType) {
		this.greenCellStyleType = greenCellStyleType;
	}

	public CellStyle getPinkCellStyleType() {
		return pinkCellStyleType;
	}

	public void setPinkCellStyleType(CellStyle pinkCellStyleType) {
		this.pinkCellStyleType = pinkCellStyleType;
	}

	public CellStyle getPurpleCellStyleType() {
		return purpleCellStyleType;
	}

	public void setPurpleCellStyleType(CellStyle purpleCellStyleType) {
		this.purpleCellStyleType = purpleCellStyleType;
	}

	public CellStyle getTotalgreencellStyleType() {
		return totalgreencellStyleType;
	}

	public void setTotalgreencellStyleType(CellStyle totalgreencellStyleType) {
		this.totalgreencellStyleType = totalgreencellStyleType;
	}

	public CellStyle getOrderGreenCellStyleType() {
		return orderGreenCellStyleType;
	}

	public void setOrderGreenCellStyleType(CellStyle orderGreenCellStyleType) {
		this.orderGreenCellStyleType = orderGreenCellStyleType;
	}

	/**
	 * @return the selectMonthYearStyleType
	 */
	public CellStyle getSelectMonthYearStyleType() {
		return selectMonthYearStyleType;
	}

	/**
	 * @param selectMonthYearStyleType the selectMonthYearStyleType to set
	 */
	public void setSelectMonthYearStyleType(CellStyle selectMonthYearStyleType) {
		this.selectMonthYearStyleType = selectMonthYearStyleType;
	}

	/**
	 * @return the numberDecimal2CellStyleType
	 */
	public CellStyle getNumberDecimal2CellStyleType() {
		return numberDecimal2CellStyleType;
	}

	/**
	 * @param numberDecimal2CellStyleType the numberDecimal2CellStyleType to set
	 */
	public void setNumberDecimal2CellStyleType(CellStyle numberDecimal2CellStyleType) {
		this.numberDecimal2CellStyleType = numberDecimal2CellStyleType;
	}

	/**
	 * @return the monthlyCostCellStyle
	 */
	public CellStyle getMonthlyCostCellStyle() {
		return monthlyCostCellStyle;
	}

	/**
	 * @param monthlyCostCellStyle the monthlyCostCellStyle to set
	 */
	public void setMonthlyCostCellStyle(CellStyle monthlyCostCellStyle) {
		this.monthlyCostCellStyle = monthlyCostCellStyle;
	}

	/**
	 * @return the itemCellStyle
	 */
	public CellStyle getItemNoColorCellStyle() {
		return itemNoColorCellStyle;
	}

	/**
	 * @param itemCellStyle the itemCellStyle to set
	 */
	public void setItemNoColorCellStyle(CellStyle itemNoColorCellStyle) {
		this.itemNoColorCellStyle = itemNoColorCellStyle;
	}

	/**
	 * @return the monthLeftCellStyle
	 */
	public CellStyle getMonthLeftCellStyle() {
		return monthLeftCellStyle;
	}

	/**
	 * @param monthLeftCellStyle the monthLeftCellStyle to set
	 */
	public void setMonthLeftCellStyle(CellStyle monthLeftCellStyle) {
		this.monthLeftCellStyle = monthLeftCellStyle;
	}

	/**
	 * @return the monthRightCellStyle
	 */
	public CellStyle getMonthRightCellStyle() {
		return monthRightCellStyle;
	}

	/**
	 * @param monthRightCellStyle the monthRightCellStyle to set
	 */
	public void setMonthRightCellStyle(CellStyle monthRightCellStyle) {
		this.monthRightCellStyle = monthRightCellStyle;
	}

	/**
	 * @return the monthLastCellStyle
	 */
	public CellStyle getMonthLastCellStyle() {
		return monthLastCellStyle;
	}

	/**
	 * @param monthLastCellStyle the monthLastCellStyle to set
	 */
	public void setMonthLastCellStyle(CellStyle monthLastCellStyle) {
		this.monthLastCellStyle = monthLastCellStyle;
	}

	/**
	 * @return the totalLeftCellStyle
	 */
	public CellStyle getTotalLeftCellStyle() {
		return totalLeftCellStyle;
	}

	/**
	 * @param totalLeftCellStyle the totalLeftCellStyle to set
	 */
	public void setTotalLeftCellStyle(CellStyle totalLeftCellStyle) {
		this.totalLeftCellStyle = totalLeftCellStyle;
	}

	/**
	 * @return the totalRightCellStyle
	 */
	public CellStyle getTotalRightCellStyle() {
		return totalRightCellStyle;
	}

	/**
	 * @param totalRightCellStyle the totalRightCellStyle to set
	 */
	public void setTotalRightCellStyle(CellStyle totalRightCellStyle) {
		this.totalRightCellStyle = totalRightCellStyle;
	}

	/**
	 * @return the totalLastCellStyle
	 */
	public CellStyle getTotalLastCellStyle() {
		return totalLastCellStyle;
	}

	/**
	 * @param totalLastCellStyle the totalLastCellStyle to set
	 */
	public void setTotalLastCellStyle(CellStyle totalLastCellStyle) {
		this.totalLastCellStyle = totalLastCellStyle;
	}

	/**
	 * @return the oneTimeCostLeftCellStyle
	 */
	public CellStyle getOneTimeCostLeftCellStyle() {
		return oneTimeCostLeftCellStyle;
	}

	/**
	 * @param oneTimeCostLeftCellStyle the oneTimeCostLeftCellStyle to set
	 */
	public void setOneTimeCostLeftCellStyle(CellStyle oneTimeCostLeftCellStyle) {
		this.oneTimeCostLeftCellStyle = oneTimeCostLeftCellStyle;
	}

	/**
	 * @return the oneTimeCostRightCellStyle
	 */
	public CellStyle getOneTimeCostRightCellStyle() {
		return oneTimeCostRightCellStyle;
	}

	/**
	 * @param oneTimeCostRightCellStyle the oneTimeCostRightCellStyle to set
	 */
	public void setOneTimeCostRightCellStyle(CellStyle oneTimeCostRightCellStyle) {
		this.oneTimeCostRightCellStyle = oneTimeCostRightCellStyle;
	}

	/**
	 * @return the oneTimeCostMonthLeftCellStyle
	 */
	public CellStyle getOneTimeCostMonthLeftCellStyle() {
		return oneTimeCostMonthLeftCellStyle;
	}

	/**
	 * @param oneTimeCostMonthLeftCellStyle the oneTimeCostMonthLeftCellStyle to set
	 */
	public void setOneTimeCostMonthLeftCellStyle(CellStyle oneTimeCostMonthLeftCellStyle) {
		this.oneTimeCostMonthLeftCellStyle = oneTimeCostMonthLeftCellStyle;
	}

	/**
	 * @return the oneTimeCostMonthRightCellStyle
	 */
	public CellStyle getOneTimeCostMonthRightCellStyle() {
		return oneTimeCostMonthRightCellStyle;
	}

	/**
	 * @param oneTimeCostMonthRightCellStyle the oneTimeCostMonthRightCellStyle to set
	 */
	public void setOneTimeCostMonthRightCellStyle(CellStyle oneTimeCostMonthRightCellStyle) {
		this.oneTimeCostMonthRightCellStyle = oneTimeCostMonthRightCellStyle;
	}

	/**
	 * @return the oneTimeCostMonthLastCellStyle
	 */
	public CellStyle getOneTimeCostMonthLastCellStyle() {
		return oneTimeCostMonthLastCellStyle;
	}

	/**
	 * @param oneTimeCostMonthLastCellStyle the oneTimeCostMonthLastCellStyle to set
	 */
	public void setOneTimeCostMonthLastCellStyle(CellStyle oneTimeCostMonthLastCellStyle) {
		this.oneTimeCostMonthLastCellStyle = oneTimeCostMonthLastCellStyle;
	}

	/**
	 * @return the totalCellStyle
	 */
	public CellStyle getTotalDCellStyle() {
		return totalDCellStyle;
	}

	/**
	 * @param totalCellStyle the totalCellStyle to set
	 */
	public void setTotalDCellStyle(CellStyle totalDCellStyle) {
		this.totalDCellStyle = totalDCellStyle;
	}

	/**
	 * @return the totalCCellStyle
	 */
	public CellStyle getTotalCCellStyle() {
		return totalCCellStyle;
	}

	/**
	 * @param totalCCellStyle the totalCCellStyle to set
	 */
	public void setTotalCCellStyle(CellStyle totalCCellStyle) {
		this.totalCCellStyle = totalCCellStyle;
	}
	
	// Longnd merge excel
	private void mergeColumnExcel(Workbook aWORKBOOK, Sheet aSHEET, int rowIndexStart, int rowIndexEnd, int columnIndex, String type, CellStyle cellStyle) {
		CellRangeAddress mergedCellType = new CellRangeAddress(rowIndexStart, rowIndexEnd, columnIndex, columnIndex);
		aSHEET.addMergedRegion(mergedCellType);
		if (2 == columnIndex) {
			RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		} else if (1 == columnIndex) {
			RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
			RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
			RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
			RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, mergedCellType, aSHEET, aWORKBOOK);
		} else {
			RegionUtil.setBorderTop(CellStyle.BORDER_THIN, mergedCellType, aSHEET, aWORKBOOK);
			RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, mergedCellType, aSHEET, aWORKBOOK);
			RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, mergedCellType, aSHEET, aWORKBOOK);
			RegionUtil.setBorderRight(CellStyle.BORDER_THIN, mergedCellType, aSHEET, aWORKBOOK);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		}
		
		checkNullValueCells(type, columnIndex, aSHEET.getRow(rowIndexStart), cellStyle);
	}
	
}
