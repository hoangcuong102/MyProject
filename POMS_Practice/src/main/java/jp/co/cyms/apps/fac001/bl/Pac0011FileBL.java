package jp.co.cyms.apps.fac001.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import jp.co.cyms.apps.fac001.bean.DownloadDeliveryOrderBean;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderCfgBean;
import jp.co.cyms.apps.fac001.bean.OrderDtlBean;
import jp.co.cyms.apps.fac001.dao.Pac0011Dao;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.DateUtil;
import jp.co.cyms.common.FileOfficeConverter;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.ConfigBean;
import jp.co.cyms.common.bean.ExcelBean;

public class Pac0011FileBL {
	protected static Logger LOG = LoggerFactory.getLogger(Pac0011FileBL.class);
	static final String styleLeftBold = "styleLeftBold";

	static final String styleLeftNormal = "styleLeftNormal";

	static final String styleCenterBold = "styleCenterBold";

	static final String styleCenterNormal = "styleCenterNormal";
	
	static final String styleRightBold = "styleRightBold";

	static final String styleRightNormal = "styleRightNormal";
	
	static final String styleLeftNormalNoWrapText = "styleLeftNormalNoWrapText";
	
	static final String styleLeftNormalBoldNoWrapText = "styleLeftNormalBoldNoWrapText";
	
	static final String styleTotal = "styleTotal";
	
	static final String styleBorderBottom = "styleBorderBottom";

	public String getFileName(String nameFile, Map<String, Object> session, boolean isTempFile) {
		ConfigBean configBean = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		File file;
		if (isTempFile) {
			file = new File(configBean.getExcelTemp() + "\\" + nameFile);
		} else {
			file = new File(configBean.getQuoteDir() + "\\" + nameFile);
		}

		return file.toString();
	}

	/**
	 * @param orderId
	 * @param leaseType
	 * @return
	 * @throws InvalidFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<ExcelBean> getDataToExport(String orderId, String leaseType)
			throws InvalidFormatException, FileNotFoundException, IOException {
		List<ExcelBean> listData = new ArrayList<ExcelBean>();
		Pac0011Dao dao = new Pac0011Dao();
		OrderBean order = dao.getOrder(orderId);
		String quoteNo = "";

		if (Constant.LEASE_TYPE_MRC.equals(leaseType)) {
			quoteNo = order.getQuoteMrc();
		} else if (Constant.LEASE_TYPE_OTC.equals(leaseType)) {
			quoteNo = order.getQuoteOtc();
		}

		listData.add(new ExcelBean(5, 1, Constant.COMPANY_NAME_SANKYU, styleLeftNormal));
		listData.add(new ExcelBean(6, 1, order.getAttnToName(), styleLeftNormal));
		listData.add(new ExcelBean(7, 1, order.getCcName(), styleLeftNormal));
		listData.add(new ExcelBean(5, 4, quoteNo, styleLeftNormal));
		listData.add(new ExcelBean(6, 4, DateUtil.formatDate(new Date(), jp.co.cyms.common.DateFormat.ddMMMyy),
				styleLeftNormal));
		listData.add(new ExcelBean(7, 4, Constant.NUBMER_DAYS_FROM_DATE, styleLeftNormal));
		listData.add(new ExcelBean(8, 4, order.getDeptName(), styleLeftNormal));

		OrderCfgBean orderCfgBean = new OrderCfgBean();
		orderCfgBean.setOrderId(orderId);
		orderCfgBean.setLeaseType(leaseType);
		List<OrderCfgBean> listOrderCfg = dao.getListOrderCfg(orderCfgBean);
		// Sort listOrderCfg by CategoryCd and AddonDisplayOrder
		listOrderCfg.sort(Comparator.comparing(OrderCfgBean::getCategoryDisplayOrder, Comparator.nullsLast(Comparator.naturalOrder()))
				.thenComparing(Comparator.comparing(OrderCfgBean::getCategoryCd, Comparator.nullsLast(Comparator.naturalOrder())))
				.thenComparing(Comparator.comparing(OrderCfgBean::getAddonDisplayOrder, Comparator.nullsLast(Comparator.naturalOrder())))
				.thenComparing(Comparator.comparing(OrderCfgBean::getItemCd, Comparator.nullsLast(Comparator.naturalOrder())).reversed()));
		listOrderCfg.stream().collect(Collectors.groupingBy(OrderCfgBean::getItemCd));
		//int lastRow = 0;
		if (Constant.LEASE_TYPE_MRC.equals(leaseType)) {
			OrderDtlBean orderDtlBean = new OrderDtlBean();
			orderDtlBean.setOrderId(orderId);
			orderDtlBean.setLeaseType(leaseType);
			List<OrderDtlBean> listOrderDtl = dao.getListOrderDtl(orderDtlBean);
			String quoteTitle = "";
			int count = 0;
			int countCfg = 0;

			for (OrderDtlBean dtl : listOrderDtl) {
				
				int countCheck = 0;
				listData.add(
						new ExcelBean(15 + count * 4 + countCfg * 3, 0, String.valueOf((count + 1)), styleCenterBold));
				listData.add(new ExcelBean(15 + count * 4 + countCfg * 3, 1, dtl.getSectionName(), styleLeftBold));
				listData.add(new ExcelBean(16 + count * 4 + countCfg * 3, 0, count + 1 + "." + (countCheck + 1),
						styleCenterNormal));
				listData.add(new ExcelBean(16 + count * 4 + countCfg * 3, 1, dtl.getItemCd(), styleLeftNormal));
				listData.add(new ExcelBean(16 + count * 4 + countCfg * 3, 2, dtl.getItemName(), styleCenterNormal));
				listData.add(new ExcelBean(16 + count * 4 + countCfg * 3, 3, String.valueOf(dtl.getItemQty()),
						styleCenterNormal));
				listData.add(new ExcelBean(16 + count * 4 + countCfg * 3, 4, dtl.getUnitPrice(), styleRightNormal));
				listData.add(new ExcelBean(16 + count * 4 + countCfg * 3, 5, dtl.getAmount(), styleRightNormal));
				listData.add(new ExcelBean(17 + count * 4 + countCfg * 3, 2, dtl.getItemRemark(), styleCenterNormal));
				quoteTitle += ((dtl.getItemBrand() == null) ? "" :  dtl.getItemBrand()) + " x " + dtl.getItemQty() + ", ";

				countCheck++;
				for (OrderCfgBean cfg : listOrderCfg) {

					if (cfg.getCategoryCd().equals(dtl.getCategoryCd())) {
						int next = count * 4 + countCfg * 3;
						//listData.add(new ExcelBean(19 + next, 1, cfg.getSectionName(), styleLeftNormal));
						listData.add(new ExcelBean(19 + next, 0, count + 1 + "." + (countCheck + 1), styleCenterNormal));
						listData.add(new ExcelBean(19 + next, 1, cfg.getItemCd(), styleLeftNormal));
						listData.add(new ExcelBean(19 + next, 2, cfg.getItemName(), styleCenterNormal));
						listData.add(new ExcelBean(19 + next, 3, String.valueOf(cfg.getItemQty()), styleCenterNormal));
						listData.add(new ExcelBean(19 + next, 4, cfg.getUnitPrice(), styleRightNormal));
						listData.add(new ExcelBean(19 + next, 5, cfg.getAmount(), styleRightNormal));
						listData.add(new ExcelBean(20 + next, 2, cfg.getItemRemark(), styleCenterNormal));
						countCfg++;
						countCheck++;
					}

				}
				count++;

			}
			count--;
			//quoteTitle += " for SANKYU " + order.getCountryCd();
			//listData.add(new ExcelBean(8, 1, quoteTitle, styleLeftBold));

			countCfg--;
			listData.add(new ExcelBean(21 + count * 4 + countCfg * 3, 4, "TOTAL(" + leaseType + ")", styleTotal));
			listData.add(new ExcelBean(21 + count * 4 + countCfg * 3, 5, order.getTtlMrc(), styleTotal));
			//lastRow = 23 + count * 4 + countCfg * 3;

		} else if (Constant.LEASE_TYPE_OTC.equals(leaseType)) {
			int rowId = 15;
			int numberSectionName = 0;
			int numberItemCd = 0;
			String quoteTitle = "";
			String sectionNameOld = "";
			String itemCdOld = "";
			String itemNameOld = "";
			for (int i=0; i<listOrderCfg.size(); i++) {
				// OrderCfgBean
				OrderCfgBean cfg = listOrderCfg.get(i);
				if (i == 0 || !sectionNameOld.equals(cfg.getSectionName())) {
					numberSectionName++;
					listData.add(new ExcelBean(rowId, 0, Integer.toString(numberSectionName), styleCenterBold));
					listData.add(new ExcelBean(rowId, 1, cfg.getSectionName(), styleLeftBold));
					sectionNameOld = cfg.getSectionName();
					rowId++;
				}
				
				listData.add(new ExcelBean(rowId, 0, "", styleCenterNormal));
				if(i== 0 || !itemCdOld.equals(cfg.getItemCd()) || !itemNameOld.equals(cfg.getItemName()) ) {
					listData.add(new ExcelBean(rowId, 1, cfg.getItemCd(), styleLeftNormal));
					itemCdOld = cfg.getItemCd();
					listData.add(new ExcelBean(rowId, 2, cfg.getItemName(), styleCenterNormal));
					itemNameOld = cfg.getItemName();
					//rowId++;
				}
				//listData.add(new ExcelBean(rowId, 1, cfg.getItemCd(), styleLeftNormal));
				//listData.add(new ExcelBean(rowId, 2, cfg.getItemName(), styleCenterNormal));
				
				listData.add(new ExcelBean(rowId, 3, String.valueOf(cfg.getItemQty()), styleCenterNormal));
				listData.add(new ExcelBean(rowId, 4, cfg.getUnitPrice(), styleRightNormal));
				listData.add(new ExcelBean(rowId, 5, cfg.getItemQty()*cfg.getUnitPrice(), styleRightNormal));
				listData.add(new ExcelBean(rowId + 1, 1, cfg.getItemRemark(), styleLeftNormal));
				
				//quoteTitle += ((cfg.getItemBrand() == null) ? "" :  cfg.getItemBrand()) + " x " + cfg.getItemQty() + ", ";
				rowId += 2;

			}
			//quoteTitle += " for SANKYU " + order.getCountryCd();
			//listData.add(new ExcelBean(8, 1, quoteTitle, styleLeftBold));
			listData.add(new ExcelBean(rowId, 4, "TOTAL(" + leaseType + ")", styleTotal));
			listData.add(new ExcelBean(rowId, 5, order.getTtlOtc(), styleTotal));
		}
		
		return listData;
	}

	public String createPdfFile( String inputFile, String outputFile, List<ExcelBean> listData,
			Map<String, Object> session, String quoteNo, String leaseType) throws Exception {
		try {
			FileInputStream inputStream = new FileInputStream(new File(inputFile));
			Workbook workbook = WorkbookFactory.create(inputStream);

			Sheet sheet = workbook.getSheetAt(0);
			
			// Fit Sheet to One Page
			sheet.setFitToPage(true);
			PrintSetup ps = sheet.getPrintSetup();
			ps.setFitWidth((short) 1);
			ps.setFitHeight((short) 0);

			Font fontNormal = workbook.createFont();
			fontNormal.setFontHeightInPoints((short) 11);
			fontNormal.setFontName("Times New Roman");
			fontNormal.setItalic(false);
			fontNormal.setBold(false);

			Font fontBold = workbook.createFont();
			fontBold.setFontHeightInPoints((short) 11);
			fontBold.setFontName("Times New Roman");
			fontBold.setItalic(false);
			fontBold.setBold(true);

			CellStyle cellStyleAlignLeft = sheet.getRow(13).getCell(1).getCellStyle();
			CellStyle cellStyleAlignCenter = sheet.getRow(13).getCell(2).getCellStyle();
			CellStyle cellStyleAlignRight = sheet.getRow(13).getCell(3).getCellStyle();
			
			CellStyle cellStyleLeftBold = sheet.getWorkbook().createCellStyle();
			cellStyleLeftBold.cloneStyleFrom(cellStyleAlignLeft);
			//cellStyleLeftBold.setAlignment(CellStyle.ALIGN_LEFT);
			cellStyleLeftBold.setFont(fontBold);

			CellStyle cellStyleLeftNormal = sheet.getWorkbook().createCellStyle();
			cellStyleLeftNormal.cloneStyleFrom(cellStyleAlignLeft);
			//cellStyleLeftNormal.setAlignment(CellStyle.ALIGN_LEFT);
			cellStyleLeftNormal.setFont(fontNormal);

			CellStyle cellStyleCenterBold = sheet.getWorkbook().createCellStyle();
			cellStyleCenterBold.cloneStyleFrom(cellStyleAlignCenter);
			//cellStyleCenterBold.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyleCenterBold.setFont(fontBold);

			CellStyle cellStyleCenterNormal = sheet.getWorkbook().createCellStyle();
			cellStyleCenterNormal.cloneStyleFrom(cellStyleAlignCenter);
			//cellStyleCenterNormal.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyleCenterNormal.setFont(fontNormal);
			
			CellStyle cellStyleRightBold = sheet.getWorkbook().createCellStyle();
			cellStyleRightBold.cloneStyleFrom(cellStyleAlignRight);
			//cellStyleCenterBold.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyleRightBold.setFont(fontBold);

			CellStyle cellStyleRightNormal = sheet.getWorkbook().createCellStyle();
			cellStyleRightNormal.cloneStyleFrom(cellStyleAlignRight);
			//cellStyleCenterNormal.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyleRightNormal.setFont(fontNormal);
			
			CellStyle cellStyleLeftNormalNoWrapText = sheet.getWorkbook().createCellStyle();
			cellStyleLeftNormalNoWrapText.cloneStyleFrom(cellStyleLeftNormal);
			cellStyleLeftNormalNoWrapText.setWrapText(false);
			cellStyleLeftNormalNoWrapText.setFont(fontNormal);
			
			CellStyle cellStyleLeftNormalBoldNoWrapText = sheet.getWorkbook().createCellStyle();
			cellStyleLeftNormalBoldNoWrapText.cloneStyleFrom(cellStyleLeftBold);
			cellStyleLeftNormalBoldNoWrapText.setWrapText(false);
			cellStyleLeftNormalBoldNoWrapText.setFont(fontBold);
			
			CellStyle cellStyleTotal = sheet.getWorkbook().createCellStyle();
			cellStyleTotal.cloneStyleFrom(cellStyleAlignRight);
			cellStyleTotal.setBorderTop(CellStyle.BORDER_THIN);
			cellStyleTotal.setBorderBottom(CellStyle.BORDER_MEDIUM);
			cellStyleTotal.setFont(fontBold);

			CellStyle cellStyleBorderBottom = sheet.getWorkbook().createCellStyle();
			cellStyleBorderBottom.setBorderBottom(CellStyle.BORDER_MEDIUM);

			//CellStyle cellStyleCenterNormal
			CellStyle cellStyleCenterNormalTop = sheet.getWorkbook().createCellStyle();
			cellStyleCenterNormalTop.cloneStyleFrom(cellStyleCenterNormal);
			cellStyleCenterNormalTop.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			
			// Set value quoteNo to cell [A-5]
			if (Constant.LEASE_TYPE_MRC.equals(leaseType)) {
				Row rowQuoteNo = sheet.getRow(38);
				if (rowQuoteNo == null) {
					rowQuoteNo = sheet.createRow(38);
				}
				Cell cellQuoteNo = rowQuoteNo.getCell(4);
				if (cellQuoteNo == null) {
					cellQuoteNo = rowQuoteNo.createCell(4);
				}
				cellQuoteNo.setCellValue(quoteNo == null ? "" : quoteNo);
			} else if (Constant.LEASE_TYPE_OTC.equals(leaseType)) {
				Row rowQuoteNo = sheet.getRow(34);
				if (rowQuoteNo == null) {
					rowQuoteNo = sheet.createRow(34);
				}
				Cell cellQuoteNo = rowQuoteNo.getCell(4);
				if (cellQuoteNo == null) {
					cellQuoteNo = rowQuoteNo.createCell(4);
				}
				cellQuoteNo.setCellValue(quoteNo == null ? "" : quoteNo);
			}
			/*Row rowQuoteNo = sheet.getRow(38);
			if (rowQuoteNo == null) {
				rowQuoteNo = sheet.createRow(38);
			}
			Cell cellQuoteNo = rowQuoteNo.getCell(4);
			if (cellQuoteNo == null) {
				cellQuoteNo = rowQuoteNo.createCell(4);
			}
			cellQuoteNo.setCellValue(quoteNo == null ? "" : quoteNo);*/
			// Shift rows
			sheet.shiftRows(16, 45, listData.get(listData.size() - 1).getRow() - 14);
			
			// Maximum number of characters per line in cell at column C
			int limitCharacterInWidth = (int) (sheet.getColumnWidth(2) / 256);
			limitCharacterInWidth = Math.round(limitCharacterInWidth * 5f/4f);
			// Get the default height of the row
			float heightDefault = sheet.getRow(5).getHeightInPoints();
			
			for (ExcelBean data : listData) {
				if (data != null) {
					Row row = sheet.getRow(data.getRow());
					if (row == null) {
						row = sheet.createRow(data.getRow());
					}
					Cell cell = row.getCell(data.getCol());
					if (cell == null) {
						cell = row.createCell(data.getCol());
						if (styleLeftBold.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleLeftBold);
						} else if (styleLeftNormal.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleLeftNormal);
						} else if (styleCenterBold.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleCenterBold);
						} else if (styleCenterNormal.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleCenterNormal);
						} else if (styleRightNormal.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleRightNormal);
						} else if (styleRightBold.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleRightBold);
						}  else if (styleLeftNormalNoWrapText.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleLeftNormalNoWrapText);
						} else if (styleLeftNormalBoldNoWrapText.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleLeftNormalBoldNoWrapText);
						} else if (styleTotal.equals(data.getStyle())) {
							cell.setCellStyle(cellStyleTotal);
						} else if (styleBorderBottom.equals(data.getStyle())) {row.setHeightInPoints(2);
							cell.setCellStyle(cellStyleBorderBottom);
						}
					}

					cell.setCellValue(data.getValue() == null ? "" : data.getValue().replaceFirst("\\s+$", "").replaceFirst("^\\s+", ""));
					
					// If column C is (columnId = 2)
					// Resets the row height when the number of characters exceeds the width of the cell
					if (data.getCol() == 2 && data.getValue() != null && data.getValue().length() > limitCharacterInWidth) {
						// The line number of the cell on column C
						int numberLine = countLineNumber(data.getValue().replaceFirst("\\s+$", "").replaceFirst("^\\s+", ""), limitCharacterInWidth);
						// If the line number > 1
						if (numberLine > 1) {
							// Set height for row
							row.setHeightInPoints(heightDefault + ((fontNormal.getFontHeightInPoints() + 1) * (numberLine - 1)) + 2);
							cell.setCellStyle(cellStyleCenterNormalTop);
						}
					}
				}

			}
			// Sheet Terms & Conditions of Sales
			Sheet sheetTerms = workbook.getSheetAt(1);
			
			// Fit Sheet to One Page
			sheetTerms.setFitToPage(true);
			PrintSetup psTerms = sheetTerms.getPrintSetup();
			psTerms.setFitWidth((short) 1);
			psTerms.setFitHeight((short) 1);

			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
			ex.printStackTrace();
		}
		return outputFile;
	}

	/**
	 * get term and Condition
	 */

	public List<ExcelBean> getTermAndConditions() {
		return null;

	}

	/**
	 * 
	 * @param outputFile
	 * @param session
	 * @throws IOException
	 */
	public void converPdf(List<String> listOutputFile, ConfigBean configBean) throws IOException {

		FileOfficeConverter officeConverter = new FileOfficeConverter(configBean.getLibreOffice());
		for (String outputFile : listOutputFile) {
			officeConverter.isSuccessCreatePDF(outputFile,
					outputFile.replace(Constant.XLSX_EXTENSION, Constant.PDF_EXTENSION));
		}
		officeConverter.stopOfficeManager();

	}
	
	/**
	 * Counts the number of lines in the cell corresponding to the content on
	 * that cell
	 * 
	 * @param content
	 * @param limitCharacterInWidth
	 * @return line number
	 */
	public int countLineNumber(String content, int limitCharacterInWidth) {
		int lineNumber = 0;
		if (!StringUtil.isNullOrEmpty(content)) {
			String[] chars = content.split("");
			int counter = 0;
			for (int i = 0; i < chars.length; i++) {
				counter++;
				if (counter == limitCharacterInWidth) {
					lineNumber++;
					counter = 0;
				} else if ("\n".equals(chars[i])) {
					lineNumber++;
					counter = 0;
				}
			}
			if (counter > 0) {
				lineNumber++;
			}
		}
		return lineNumber;
	}
}
