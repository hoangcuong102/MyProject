package jp.co.cyms.base.export;

/*
 * COPYRIGHT Mitsui Zosen Systems Research Inc. All right Reserved
 */

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * Excel出力実施クラス。親クラス（ReportForExcel）の各メソッドを継承し、Excelファイルのヘッダー、
 * ボディーとフッターの部分にデータを入れ込む
 * 
 * @author tichnv
 * @since 2016/10/06
 */
@Service
public class ExportBL {

	/**
	 * Initiate constructor of class.
	 */
	public ExportBL() {
	}
	
	/**
	 * Get sysparams elements
	 * 
	 * @return MSyspara contain properties of this object.
	 */
	public SysParam getSyspara() {
		/* Initiate a SysParamBL. */
		SysParamBL sysParamBL = new SysParamBL();
		return sysParamBL.getSysParam();
	}
	
	/**
	 * Set all cells value by iterate result map
	 * 
	 * @param curRow
	 * @param rows
	 * @param map
	 */
	public static void excelRowMapper(Row curRow, Map<String, Object> map) {
		if (map.size() != curRow.getPhysicalNumberOfCells()) {
			return;
		}

		Set<String> keyset = map.keySet();
		int cellnum = 0;
		for (String key : keyset) {
			Object obj = map.get(key);
			Cell cell = curRow.getCell(cellnum++);
			if (obj instanceof Date)
				cell.setCellValue((Date) obj);
			else if (obj instanceof Boolean)
				cell.setCellValue((Boolean) obj);
			else if (obj instanceof Long)
				cell.setCellValue((Long) obj);
			else if (obj instanceof Double)
				cell.setCellValue((Double) obj);
			else
				cell.setCellValue((String) obj);
		}
	}

	/**
	 * 
	 * @param workbook
	 * @param sheet
	 * @param oldCell
	 * @param newCell
	 * @return String
	 */
	public static String getCopyFormula(Workbook workbook, Sheet sheet,
			Cell oldCell, Cell newCell) {
		String oldFormula = oldCell.getCellFormula();
		String newFormula = new String();

		if (oldFormula != null) {
			FormulaParsingWorkbook parsingWorkbook = null;
			FormulaRenderingWorkbook renderingWorkbook = null;

			if (workbook instanceof HSSFWorkbook) {
				parsingWorkbook = HSSFEvaluationWorkbook
						.create((HSSFWorkbook) workbook);
				renderingWorkbook = HSSFEvaluationWorkbook
						.create((HSSFWorkbook) workbook);
			} else if (workbook instanceof XSSFWorkbook) {
				parsingWorkbook = XSSFEvaluationWorkbook
						.create((XSSFWorkbook) workbook);
				renderingWorkbook = XSSFEvaluationWorkbook
						.create((XSSFWorkbook) workbook);
			}

			/*
			 * get PTG's in the formula
			 */Ptg[] ptgs = FormulaParser.parse(oldFormula, parsingWorkbook,
					FormulaType.CELL, workbook.getSheetIndex(sheet));

			/*
			 * iterating through all PTG's
			 */for (Ptg ptg : ptgs) {
				/* for references such as A1, A2, B3 */
				if (ptg instanceof RefPtgBase) {
					RefPtgBase refPtgBase = (RefPtgBase) ptg;

					/* if row is relative */
					if (refPtgBase.isRowRelative()) {
						refPtgBase
								.setRow((short) (newCell.getRowIndex() - (oldCell
										.getRowIndex() - refPtgBase.getRow())));
					}

					/* if col is relative */
					if (refPtgBase.isColRelative()) {
						refPtgBase
								.setColumn((short) (newCell.getColumnIndex() - (oldCell
										.getColumnIndex() - refPtgBase
										.getColumn())));
					}
				}
				/* for area of cells A1:A4 */
				if (ptg instanceof AreaPtgBase) {
					AreaPtgBase areaPtgBase = (AreaPtgBase) ptg;

					/* if first row is relative */
					if (areaPtgBase.isFirstRowRelative()) {
						areaPtgBase
								.setFirstRow((short) (newCell.getRowIndex() - (oldCell
										.getRowIndex() - areaPtgBase
										.getFirstRow())));
					}

					/* if last row is relative */
					if (areaPtgBase.isLastRowRelative()) {
						areaPtgBase
								.setLastRow((short) (newCell.getRowIndex() - (oldCell
										.getRowIndex() - areaPtgBase
										.getLastRow())));
					}

					/* if first column is relative */
					if (areaPtgBase.isFirstColRelative()) {
						areaPtgBase
								.setFirstColumn((short) (newCell
										.getColumnIndex() - (oldCell
										.getColumnIndex() - areaPtgBase
										.getFirstColumn())));
					}

					/* if last column is relative */
					if (areaPtgBase.isLastColRelative()) {
						areaPtgBase
								.setLastColumn((short) (newCell
										.getColumnIndex() - (oldCell
										.getColumnIndex() - areaPtgBase
										.getLastColumn())));
					}
				}
			}

			newFormula = FormulaRenderer.toFormulaString(renderingWorkbook,
					ptgs);
		}

		return newFormula;
	}
}
