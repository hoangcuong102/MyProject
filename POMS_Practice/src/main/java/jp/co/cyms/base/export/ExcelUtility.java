package jp.co.cyms.base.export;


import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import jp.co.cyms.common.StringUtil;

/**
 *************************************************************************
 *	ExcelUtility
 * @author tichnv
 *************************************************************************
 */
public class ExcelUtility
{
	/** 讓呎ｺ�({@value}) */
	public static final int ALIGN_GENERAL = CellStyle.ALIGN_GENERAL;
	/** 蟾ｦ隧ｰ繧�({@value}) */
	public static final int ALIGN_LEFT = CellStyle.ALIGN_LEFT;
	/** 荳ｭ螟ｮ謠�∴ ({@value}) */
	public static final int ALIGN_CENTER = CellStyle.ALIGN_CENTER;
	/** 蜿ｳ隧ｰ繧�({@value}) */
	public static final int ALIGN_RIGHT = CellStyle.ALIGN_RIGHT;
	/** 郢ｰ繧願ｿ斐＠ ({@value}) */
	public static final int ALIGN_FILL = CellStyle.ALIGN_FILL;
	/** 荳｡遶ｯ謠�∴ ({@value}) */
	public static final int ALIGN_JUSTIFY = CellStyle.ALIGN_JUSTIFY;
	/** 驕ｸ謚樒ｯ�峇蜀�〒荳ｭ螟ｮ ({@value}) */
	public static final int ALIGN_CENTER_SELECTION = CellStyle.ALIGN_CENTER_SELECTION;
	/** 荳願ｩｰ繧�({@value}) */
	public static final int VERTICAL_TOP = CellStyle.VERTICAL_TOP;
	/** 荳ｭ螟ｮ謠�∴ ({@value}) */
	public static final int VERTICAL_CENTER = CellStyle.VERTICAL_CENTER;
	/** 荳玖ｩｰ繧�({@value}) */
	public static final int VERTICAL_BOTTOM = CellStyle.VERTICAL_BOTTOM;
	/** 荳｡遶ｯ謠�∴ ({@value}) */
	public static final int VERTICAL_JUSTIFY = CellStyle.VERTICAL_JUSTIFY;
	/** 鄂ｫ邱壹↑縺�({@value}) */
	public static final int BORDER_NONE = CellStyle.BORDER_NONE;
	/** 邏ｰ邱�({@value}) */
	public static final int BORDER_THIN = CellStyle.BORDER_THIN;
	/** 譎ｮ騾夂ｷ�({@value}) */
	public static final int BORDER_MEDIUM = CellStyle.BORDER_MEDIUM;
	/** 轤ｹ邱夲ｼ磯聞繧�ｼ�({@value}) */
	public static final int BORDER_DASHED = CellStyle.BORDER_DASHED;
	/** 轤ｹ邱夲ｼ育洒繧�ｼ�({@value}) */
	public static final int BORDER_DOTTED = CellStyle.BORDER_DOTTED;
	/** 螟ｪ邱�({@value}) */
	public static final int BORDER_THICK = CellStyle.BORDER_THICK;
	/** 莠碁㍾邱�({@value}) */
	public static final int BORDER_DOUBLE = CellStyle.BORDER_DOUBLE;
	/** 繝倥い繝ｩ繧､繝ｳ ({@value}) */
	public static final int BORDER_HAIR = CellStyle.BORDER_HAIR;
	/** 譎ｮ騾壹�轤ｹ邱�({@value}) */
	public static final int BORDER_MEDIUM_DASHED = CellStyle.BORDER_MEDIUM_DASHED;
	/** 荳�せ骼也ｷ�({@value}) */
	public static final int BORDER_DASH_DOT = CellStyle.BORDER_DASH_DOT;
	/** 譎ｮ騾夂ｷ壹�荳�せ骼也ｷ�({@value}) */
	public static final int BORDER_MEDIUM_DASH_DOT = CellStyle.BORDER_MEDIUM_DASH_DOT;
	/** 莠檎せ骼也ｷ�({@value}) */
	public static final int BORDER_DASH_DOT_DOT = CellStyle.BORDER_DASH_DOT_DOT;
	/** 譎ｮ騾夂ｷ壹�莠檎せ骼也ｷ�({@value}) */
	public static final int BORDER_MEDIUM_DASH_DOT_DOT = CellStyle.BORDER_MEDIUM_DASH_DOT_DOT;
	/** 譁懊ａ縺ｫ繧ｫ繝�ヨ縺輔ｌ縺滉ｸ�せ骼也ｷ�({@value}) */
	public static final int BORDER_SLANTED_DASH_DOT = CellStyle.BORDER_SLANTED_DASH_DOT;
	/** 讓呎ｺ�({@value}) */
	public static final int BOLDWEIGHT_NORMAL = Font.BOLDWEIGHT_NORMAL;
	/** 螟ｪ蟄�({@value}) */
	public static final int BOLDWEIGHT_BOLD = Font.BOLDWEIGHT_BOLD;


	/**
	 *********************************************************************
	 * 繧ｷ繝ｼ繝医さ繝斐���
	 *
	 *
	 * @param wb			Workbook
	 * @param src_name		Source name 
	 * @param dst_name		Destination name
	 * @param dst_posi		Destination postion
	 * @author tichnv
	 */
	public static void cloneSheet(Workbook wb, String src_name, String dst_name, int dst_posi) {

		// Set src_name
		wb.cloneSheet(wb.getSheetIndex(src_name));

		// Set dst_name
		wb.setSheetName(wb.getNumberOfSheets()-1, dst_name);

		// Set sheetOrder
		wb.setSheetOrder(dst_name, dst_posi);

	}

	/**
	 *********************************************************************
	 * Set name of sheet
	 *
	 *
	 * @param wb			Workbook
	 * @param src_index		Source index
	 * @param new_name		New name
	 * @author tichnv
	 */
	public static void setSheetName(Workbook wb, int src_index, String new_name) {

		// Set Sheet name
		wb.setSheetName(src_index, new_name);

	}

	/**
	 *********************************************************************
	 *Sequence setting
	 *
	 *
	 * @param wb			Workbook
	 * @param old_name		Old name
	 * @param new_name		New Name
	 * @author tichnv
	 */
	public static void setSheetName(Workbook wb, String old_name, String new_name) {

		// Set Sheet name
		setSheetName(wb, wb.getSheetIndex(old_name), new_name);

	}


	/**
	 *********************************************************************
	 * 繧ｷ繝ｼ繝亥炎髯､��
	 *
	 *
	 * @param wb			Workbook
	 * @param del_index		Postion delete
	 * @author tichnv
	 */
	public static void sheetRemove(Workbook wb, int del_index) {

		// Set remove index
		wb.removeSheetAt(del_index);
	}

	/**
	 *********************************************************************
	 * 繧ｷ繝ｼ繝亥炎髯､��
	 *
	 *
	 * @param wb			Workbook
	 * @param sheet_posi	
	 * @author tichnv
	 */
	public static void sheetRemove(Workbook wb, String del_name) {

		// 繧ｷ繝ｼ繝亥炎髯､
		wb.removeSheetAt(wb.getSheetIndex(del_name));
	}

	/**
	 *********************************************************************
	 * 陦後さ繝斐�
	 *
	 * @param src			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param dst			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param st			繧ｳ繝斐�蜈�幕蟋玖｡檎分蜿ｷ
	 * @param en			繧ｳ繝斐�蜈�ｵゆｺ�｡檎分蜿ｷ
	 * @param dst_row		繧ｳ繝斐�蜈郁｡檎分蜿ｷ
	 */
	public static void rowCopy(Sheet src, Sheet dst, int st, int en, int dst_row) {
		int r_cnt = 0;
		for (int i = st; i <= en; i++) {
			Row r = src.getRow(i);
			if (r != null) {
				short h = r.getHeight();
				Row dst_r = dst.createRow(dst_row + r_cnt);
				dst_r.setHeight(h);
				for (int j = 0; j < r.getLastCellNum(); j++) {
					Cell cell = r.getCell(j);
					if (cell != null) {
						cellCopy(src, i, j, dst, dst_row + r_cnt, j);
					}
				}
			}
			r_cnt++;
		}

		int max = 0;
		int cellnum = 0;
		for (int k = 0; k <= (en - st); k++) {
			cellnum = (int) (dst.getRow(dst_row + k).getLastCellNum() - 1);
			if (cellnum > max)
				max = cellnum;
		}
		margedRegionCopy(
				src,
				new CellRangeAddress(st, en, 0, max),
				dst,
				dst_row, 0);

	}
	/**
	 *********************************************************************
	 * 陦後さ繝斐�
	 *
	 * @param src			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param dst			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param st			繧ｳ繝斐�蜈�幕蟋玖｡檎分蜿ｷ
	 * @param en			繧ｳ繝斐�蜈�ｵゆｺ�｡檎分蜿ｷ
	 * @param dst_row		繧ｳ繝斐�蜈郁｡檎分蜿ｷ
	 */
	public static void rowCopy2(Sheet src, Sheet dst, int st, int en, int dst_row) {
		int r_cnt = 0;
		for (int i = st; i <= en; i++) {
			Row r = src.getRow(i);
			if (r != null) {
				short h = r.getHeight();
				Row dst_r = dst.createRow(dst_row + r_cnt);
				dst_r.setHeight(h);
				for (int j = 0; j < r.getLastCellNum(); j++) {
					Cell cell = r.getCell(j);
					if (cell != null) {
						cellCopy2(src, i, j, dst, dst_row + r_cnt, j);
					}
				}
			}
			r_cnt++;
		}

		int max = 0;
		int cellnum = 0;
		for (int k = 0; k <= (en - st); k++) {
			cellnum = (int) (dst.getRow(dst_row + k).getLastCellNum() - 1);
			if (cellnum > max)
				max = cellnum;
		}
		margedRegionCopy(
				src,
				new CellRangeAddress(st, en, 0, max),
				dst,
				dst_row, 0);

	}
	/**
	 *********************************************************************
	 * 陦梧諺蜈･��
	 *   謖�ｮ壻ｽ咲ｽｮ莉･荳九�陦後ｒ�題｡御ｸ九↓繧ｷ繝輔ヨ縺励�謖�ｮ壻ｽ咲ｽｮ縺ｮ�題｡御ｸ九ｒ謖�ｮ壻ｽ咲ｽｮ縺ｫ繧ｳ繝斐�縺吶ｋ
	 *
	 * @param src			繧ｷ繝ｼ繝�
	 * @param src_row		陦梧諺蜈･菴咲ｽｮ
	 * @author tichnv
	 */
	public static void rowInsert(Sheet src, int src_row) {

		// 謖�ｮ夊｡後°繧画怙邨り｡後�遽�峇繧抵ｼ題｡御ｸ九↓繧ｷ繝輔ヨ
		src.shiftRows(src_row, src.getLastRowNum(), 1);

		// 謖�ｮ夊｡後��題｡悟燕繧呈欠螳壻ｽ咲ｽｮ縺ｫ繧ｳ繝斐�
		rowCopy(src, src, src_row + 1, src_row + 1, src_row);
	}

	/**
	 *********************************************************************
	 * 陦梧諺蜈･��
	 *   謖�ｮ壻ｽ咲ｽｮ莉･荳九�陦後ｒ�題｡御ｸ九↓繧ｷ繝輔ヨ縺励�謖�ｮ夊｡後ｒ繧ｳ繝斐�縺吶ｋ
	 *
	 * @param src			繧ｷ繝ｼ繝�
	 * @param src_row		陦梧諺蜈･菴咲ｽｮ
	 * @param copy_row		繧ｳ繝斐�蜈�｡檎分蜿ｷ
	 * @author tichnv
	 */
	public static void rowInsert(Sheet src, int src_row, int copy_row) {

		// 謖�ｮ夊｡後°繧画怙邨り｡後�遽�峇繧抵ｼ題｡御ｸ九↓繧ｷ繝輔ヨ
		src.shiftRows(src_row, src.getLastRowNum(), 1);

		// 謖�ｮ夊｡後��題｡悟燕繧呈欠螳壻ｽ咲ｽｮ縺ｫ繧ｳ繝斐�
		rowCopy(src, src, copy_row, copy_row, src_row);

	}

	/**
	 *********************************************************************
	 * 陦梧諺蜈･��
	 *   謖�ｮ壻ｽ咲ｽｮ莉･荳九�陦後ｒ�題｡御ｸ九↓繧ｷ繝輔ヨ縺励�謖�ｮ夊｡後ｒ繧ｳ繝斐�縺吶ｋ
	 *
	 * @param src			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param dst			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param st			繧ｳ繝斐�蜈�幕蟋玖｡檎分蜿ｷ
	 * @param en			繧ｳ繝斐�蜈�ｵゆｺ�｡檎分蜿ｷ
	 * @param dst_row		繧ｳ繝斐�蜈郁｡檎分蜿ｷ
	 * @author tichnv
	 */
	public static void rowCopyInsert(Sheet src, Sheet dst, int st, int en, int dst_row) {

		///// 繧ｳ繝斐�縺吶ｋ陦梧焚繧貞叙蠕�
		int copyRowCount = en - st + 1;

		///// 繧ｳ繝斐�遽�峇縺ｮ險ｭ螳�
		int cp_st = st;						//謖ｿ蜈･蠕後さ繝斐�蜈�幕蟋倶ｽ咲ｽｮ
		int cp_en = en;						//謖ｿ蜈･蠕後さ繝斐�蜈�ｵゆｺ�ｽ咲ｽｮ
		if (src.equals(dst) && dst_row <= st ){
			cp_st = cp_st + copyRowCount;
			cp_en = cp_en + copyRowCount;
		}else if (src.equals(dst) && dst_row > st && dst_row <= en){
			System.out.println("error");
		}

		// 謖ｿ蜈･陦御ｽ咲ｽｮ莉･荳九ｒ繧ｳ繝斐�縺吶ｋ陦悟�縺�￠繧ｷ繝輔ヨ縺吶ｋ縲�
		dst.shiftRows(dst_row, src.getLastRowNum(), copyRowCount);

		// 謖�ｮ夊｡後ｒ繧ｳ繝斐�
		rowCopy(src, dst, cp_st, cp_en, dst_row);

	}
	
	/**
	 * 
	 * @param src
	 * @param dst
	 * @param st
	 * @param en
	 * @param dst_row
	 */
	public static void rowCopyInsert2(Sheet src, Sheet dst, int st, int en, int dst_row) {

		///// 繧ｳ繝斐�縺吶ｋ陦梧焚繧貞叙蠕�
		int copyRowCount = en - st + 1;

		///// 繧ｳ繝斐�遽�峇縺ｮ險ｭ螳�
		int cp_st = st;						//謖ｿ蜈･蠕後さ繝斐�蜈�幕蟋倶ｽ咲ｽｮ
		int cp_en = en;						//謖ｿ蜈･蠕後さ繝斐�蜈�ｵゆｺ�ｽ咲ｽｮ
		if (src.equals(dst) && dst_row <= st ){
			cp_st = cp_st + copyRowCount;
			cp_en = cp_en + copyRowCount;
		}else if (src.equals(dst) && dst_row > st && dst_row <= en){
			System.out.println("error");
		}

		// 謖ｿ蜈･陦御ｽ咲ｽｮ莉･荳九ｒ繧ｳ繝斐�縺吶ｋ陦悟�縺�￠繧ｷ繝輔ヨ縺吶ｋ縲�
		dst.shiftRows(dst_row, dst_row+src.getLastRowNum(), copyRowCount);

		// 謖�ｮ夊｡後ｒ繧ｳ繝斐�
		rowCopy(src, dst, cp_st, cp_en, dst_row);
	}
	/**
	 *********************************************************************
	 * 陦梧諺蜈･��
	 *   謖�ｮ壻ｽ咲ｽｮ莉･荳九�陦後ｒ�題｡御ｸ九↓繧ｷ繝輔ヨ縺励�謖�ｮ夊｡後ｒ繧ｳ繝斐�縺吶ｋ
	 *
	 * @param src			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param dst			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param st			繧ｳ繝斐�蜈�幕蟋玖｡檎分蜿ｷ
	 * @param en			繧ｳ繝斐�蜈�ｵゆｺ�｡檎分蜿ｷ
	 * @param dst_row		繧ｳ繝斐�蜈郁｡檎分蜿ｷ
	 * @author tichnv
	 */
	public static void rowCopyInsert3(Sheet src, Sheet dst, int st, int en, int dst_row) {

		///// 繧ｳ繝斐�縺吶ｋ陦梧焚繧貞叙蠕�
		int copyRowCount = en - st + 1;

		///// 繧ｳ繝斐�遽�峇縺ｮ險ｭ螳�
		int cp_st = st;						//謖ｿ蜈･蠕後さ繝斐�蜈�幕蟋倶ｽ咲ｽｮ
		int cp_en = en;						//謖ｿ蜈･蠕後さ繝斐�蜈�ｵゆｺ�ｽ咲ｽｮ
		if (src.equals(dst) && dst_row <= st ){
			cp_st = cp_st + copyRowCount;
			cp_en = cp_en + copyRowCount;
		}else if (src.equals(dst) && dst_row > st && dst_row <= en){
			System.out.println("error");
		}

		// 謖ｿ蜈･陦御ｽ咲ｽｮ莉･荳九ｒ繧ｳ繝斐�縺吶ｋ陦悟�縺�￠繧ｷ繝輔ヨ縺吶ｋ縲�
		dst.shiftRows(dst_row, src.getLastRowNum(), copyRowCount);

		// 謖�ｮ夊｡後ｒ繧ｳ繝斐�
		rowCopy2(src, dst, cp_st, cp_en, dst_row);

	}
	/**
	 *********************************************************************
	 * 陦悟炎髯､��
	 *   謖�ｮ壻ｽ咲ｽｮ縺ｮ陦後ｒ蜑企勁縺励�莉･荳九�陦後ｒ�題｡御ｸ翫↓繧ｷ繝輔ヨ縺吶ｋ
	 *
	 * @param src			繧ｷ繝ｼ繝�
	 * @param src_row		蜑企勁陦御ｽ咲ｽｮ
	 * @author create:T.Dan 2013/11/20
	 */
	public static void rowRemove(Sheet src, int src_row) {
		src.removeRow(src.getRow(src_row));

		if (src_row < src.getLastRowNum()){
			src.shiftRows(src_row+1, src.getLastRowNum(), -1);
		}
	}

	/**
	 *********************************************************************
	 * 陦悟炎髯､��
	 *   謖�ｮ壻ｽ咲ｽｮ縺ｮ陦後ｒ蜑企勁縺励�莉･荳九�陦後ｒ�題｡御ｸ翫↓繧ｷ繝輔ヨ縺吶ｋ
	 *
	 * @param src			繧ｷ繝ｼ繝�
	 * @param src_row		蜑企勁陦御ｽ咲ｽｮ
	 * @author tichnv
	 */
	public static void rowRemove(Sheet src, int st, int en) {

		// 謖�ｮ壼炎髯､邨ゆｺ�｡後ｈ繧頑怙邨り｡後′蜑阪�蝣ｴ蜷�
		if(en > src.getLastRowNum()) en = src.getLastRowNum();

		// 蜑企勁陦梧焚
		int removeCount = (en - st) + 1;
		for (int i = 0; i < removeCount; i++){
			src.removeRow(src.getRow(st + i));
		}

		// 蜑企勁陦悟�繧ｷ繝輔ヨ
		if(st < src.getLastRowNum()){
			src.shiftRows(st + removeCount, src.getLastRowNum(), -removeCount);
		}
	}

	/**
	 *********************************************************************
	 * 蛻励さ繝斐�
	 *
	 * @param src			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param dst			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param src_col		繧ｳ繝斐�蜈��逡ｪ蜿ｷ
	 * @param dst_col		繧ｳ繝斐�蜈亥�逡ｪ蜿ｷ
	 * @author tichnv
	 */
	public static void colCopy(Sheet src, Sheet dst, int src_col, int dst_col) {


		//蛻励�繧ｳ繝斐�
		for (int i = 0; i <= src.getLastRowNum(); i++) {
			cellCopy(src, i, src_col, dst, i, dst_col);
		}

		//繧ｻ繝ｫ縺ｮ邨仙粋迥ｶ諷九さ繝斐�
        // 蠢�ｦ√°�滂ｼ�
	}

	/**
	 * 遽�峇繧ｳ繝斐�
	 *
	 * @param src 			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param src_region 	繧ｳ繝斐�蜈�ｯ�峇
	 * @param dst			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param dst_row		繧ｳ繝斐�蜈磯幕蟋玖｡檎分蜿ｷ
	 * @param dst_col 		繧ｳ繝斐�蜈磯幕蟋句�逡ｪ蜿ｷ
	 */
	public static void regionCopy(Sheet src, CellRangeAddress src_region, Sheet dst, int dst_row, int dst_col) {
		for (int i = src_region.getFirstRow(); i <= src_region.getLastRow(); i++) {
			Row src_row = src.getRow(i);
			if (src_row != null) {
				for (int j = src_region.getFirstColumn(); j <= src_region.getLastColumn(); j++) {
					Cell src_cell = src_row.getCell(j);
					if (src_cell != null) {
						cellCopy(src, i, j, dst, dst_row + i - src_region.getFirstRow(), dst_col + j - src_region.getFirstColumn());
					}
				}
			}
		}

		//繧ｻ繝ｫ縺ｮ邨仙粋迥ｶ諷九さ繝斐�
		margedRegionCopy(
				src, src_region,
				dst, dst_row, dst_col);
	}

	/**
	 * 遽�峇繧ｳ繝斐�
	 *
	 * @param src 			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param src_region 	繧ｳ繝斐�蜈�ｯ�峇
	 * @param dst			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param dst_row		繧ｳ繝斐�蜈磯幕蟋玖｡檎分蜿ｷ
	 * @param dst_col 		繧ｳ繝斐�蜈磯幕蟋句�逡ｪ蜿ｷ
	 */
	public static void regionCopy2(Sheet src, CellRangeAddress src_region, Sheet dst, int dst_row, int dst_col) {
		for (int i = src_region.getFirstRow(); i <= src_region.getLastRow(); i++) {
			Row src_row = src.getRow(i);
			if (src_row != null) {
				for (int j = src_region.getFirstColumn(); j <= src_region.getLastColumn(); j++) {
					Cell src_cell = src_row.getCell(j);
					if (src_cell != null) {
						cellCopy2(src, i, j, dst, dst_row + i - src_region.getFirstRow(), dst_col + j - src_region.getFirstColumn());
					}
				}
			}
		}

		//繧ｻ繝ｫ縺ｮ邨仙粋迥ｶ諷九さ繝斐�
		margedRegionCopy(
				src, src_region,
				dst, dst_row, dst_col);
	}
	/**
	 * 繧ｻ繝ｫ繧ｳ繝斐�
	 *
	 * @param src 			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param src_row 		繧ｳ繝斐�蜈�｡�
	 * @param src_col 		繧ｳ繝斐�蜈��逡ｪ蜿ｷ
	 * @param dst 			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param dst_row 		繧ｳ繝斐�蜈郁｡檎分蜿ｷ
	 * @param dst_col 		繧ｳ繝斐�蜈亥�逡ｪ蜿ｷ
	 * @return				Cell
	 */
	public static Cell cellCopy(Sheet src, int src_row, int src_col, Sheet dst, int dst_row, int dst_col) {
		Cell dst_cell = null;
		Cell cell = src.getRow(src_row).getCell(src_col);
		int w = src.getColumnWidth(src_col);
		String value = "";
		if (cell != null) {
			dst.setColumnWidth(dst_col, w);
			if (dst.getRow(dst_row) == null) {
				dst.createRow(dst_row);
			}
			dst_cell = dst.getRow(dst_row).createCell(dst_col);
			CellStyle cellstyle = cell.getCellStyle();
			dst_cell.setCellStyle(cellstyle);
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				dst_cell.setCellValue(cell.getStringCellValue().toString());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				//謨ｰ蛟､縺ｮ蝣ｴ蜷�
				if (DateUtil.isCellDateFormatted(cell)) {
					dst_cell.setCellValue(cell.getDateCellValue());
				} else {
					dst_cell.setCellValue(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_FORMULA:
				//險育ｮ怜ｼ上�蝣ｴ蜷�
				value = String.valueOf(cell.getCellFormula());
				dst_cell.setCellFormula(value);
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				dst_cell.setCellValue(cell.getBooleanCellValue());
				break;
			}
		}
		return dst_cell;
	}
	/**
	 * 繧ｻ繝ｫ繧ｳ繝斐�
	 *
	 * @param src 			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param src_row 		繧ｳ繝斐�蜈�｡�
	 * @param src_col 		繧ｳ繝斐�蜈��逡ｪ蜿ｷ
	 * @param dst 			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param dst_row 		繧ｳ繝斐�蜈郁｡檎分蜿ｷ
	 * @param dst_col 		繧ｳ繝斐�蜈亥�逡ｪ蜿ｷ
	 * @return				Cell
	 */
	public static Cell cellCopy2(Sheet src, int src_row, int src_col, Sheet dst, int dst_row, int dst_col) {
		Cell dst_cell = null;
		Cell cell = src.getRow(src_row).getCell(src_col);
		String value = "";
		if (cell != null) {
			if (dst.getRow(dst_row) == null) {
				dst.createRow(dst_row);
			}
			dst_cell = dst.getRow(dst_row).createCell(dst_col);
			CellStyle cellstyle = cell.getCellStyle();
			dst_cell.setCellStyle(cellstyle);
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				dst_cell.setCellValue(cell.getStringCellValue().toString());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				//謨ｰ蛟､縺ｮ蝣ｴ蜷�
				if (DateUtil.isCellDateFormatted(cell)) {
					dst_cell.setCellValue(cell.getDateCellValue());
				} else {
					dst_cell.setCellValue(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_FORMULA:
				//險育ｮ怜ｼ上�蝣ｴ蜷�
				value = String.valueOf(cell.getCellFormula());
				dst_cell.setCellFormula(value);
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				dst_cell.setCellValue(cell.getBooleanCellValue());
				break;
			}
		}
		return dst_cell;
	}
	/**
	 * 繧ｻ繝ｫ縺ｮ邨仙粋迥ｶ諷九さ繝斐�
	 * 繧ｳ繝斐�蜈�欠螳夂ｯ�峇蜀��繧ｻ繝ｫ邨仙粋繧偵さ繝斐�蜈域欠螳壼ｺｧ讓吶∈蜿肴丐縺吶ｋ縲らｵ仙粋迥ｶ諷九�縺ｿ縺ｮ繧ｳ繝斐�縺ｧ繧ｻ繝ｫ蛟､縺ｪ縺ｩ縺ｯ繧ｳ繝斐�縺励↑縺��
	 *
	 * @param src			繧ｳ繝斐�蜈�す繝ｼ繝�
	 * @param src_region	繧ｳ繝斐�蜈�ｯ�峇
	 * @param dst			繧ｳ繝斐�蜈医す繝ｼ繝�
	 * @param dst_row		繧ｳ繝斐�蜈磯幕蟋玖｡檎分蜿ｷ
	 * @param dst_col		繧ｳ繝斐�蜈磯幕蟋句�逡ｪ蜿ｷ
	 */
	public static void margedRegionCopy(Sheet src, CellRangeAddress src_region, Sheet dst, int dst_row, int dst_col) {
		for (int i = 0; i < src.getNumMergedRegions(); i++) {
			//邨仙粋諠�� (Region)繧貞叙蠕励☆繧�
			CellRangeAddress region = src.getMergedRegion(i);
			//邨仙粋髢句ｧ句�繧貞叙蠕励☆繧�
			int column_from = region.getFirstColumn();
			if (!(src_region.getFirstColumn() <= column_from && column_from <= src_region.getLastColumn()))
				continue;
			//邨仙粋邨ゆｺ��繧貞叙蠕励☆繧�
			int column_to = region.getLastColumn();
			if (!(src_region.getFirstColumn() <= column_to && column_to <= src_region.getLastColumn()))
				continue;
			//邨仙粋髢句ｧ玖｡後ｒ蜿門ｾ励☆繧�
			int row_from = region.getFirstRow();
			if (!(src_region.getFirstRow() <= row_from && row_from <= src_region.getLastRow()))
				continue;
			//邨仙粋邨ゆｺ�｡後ｒ蜿門ｾ励☆繧�
			int row_to = region.getLastRow();
			if (!(src_region.getFirstRow() <= row_to && row_to <= src_region.getLastRow()))
				continue;

			CellRangeAddress addregion = new CellRangeAddress(
					dst_row + (row_from - src_region.getFirstRow()),
					dst_row + (row_to - src_region.getFirstRow()),
					dst_col + (column_from - src_region.getFirstColumn()),
					dst_col + (column_to - src_region.getFirstColumn()));

			dst.addMergedRegion(addregion);
		}
	}

	/**
	 *********************************************************************
	 * 繧ｻ繝ｫ邨仙粋
	 *   謖�ｮ夂ｯ�峇縺ｮ繧ｻ繝ｫ繧堤ｵ仙粋縺吶ｋ
	 *
	 * @param src			蟇ｾ雎｡繧ｷ繝ｼ繝�
	 * @param st_row		邨仙粋髢句ｧ玖｡檎分蜿ｷ
	 * @param en_row		邨仙粋邨ゆｺ�｡檎分蜿ｷ
	 * @param st_col		邨仙粋髢句ｧ句�逡ｪ蜿ｷ
	 * @param en_col		邨仙粋邨ゆｺ��逡ｪ蜿ｷ
	 */
	public static void addMergedRegion(Sheet src, int st_row, int en_row, int st_col, int en_col){
		// 繧ｻ繝ｫ邨仙粋
		src.addMergedRegion(new CellRangeAddress(st_row, en_row, st_col, en_col));
	}

	/**
	 *********************************************************************
	 * 繧ｻ繝ｫ邨仙粋
	 *   蛻励＃縺ｨ�郁､�焚�峨↓謖�ｮ夂ｯ�峇陦後ｒ繧ｻ繝ｫ邨仙粋縺吶ｋ
	 *
	 * @param src			蟇ｾ雎｡繧ｷ繝ｼ繝�
	 * @param cols[]		邨仙粋菴咲ｽｮ蛻礼ｾ､
	 * @param st_row		邨仙粋髢句ｧ玖｡檎分蜿ｷ
	 * @param en_row		邨仙粋邨ゆｺ�｡檎分蜿ｷ
	 */
	public static void addMergedRegion(Sheet src, int cols[], int st_row, int en_row){
		// 繧ｻ繝ｫ邨仙粋

		for (int i=0; i < cols.length; i++){
			///// 繧ｻ繝ｫ邨仙粋(FWBS Code)
			addMergedRegion(src, st_row, en_row, cols[i], cols[i]);
		}
	}

	/**
	 * 繧ｻ繝ｫ蛻礼分蜿ｷ遞ｮ蛻･螟画峩(1 -> A, 2 -> B, ...)
	 *
	 * @param no 			繧ｻ繝ｫ蛻礼分蜿ｷ(1, 2, ...)
	 * @return				繧ｻ繝ｫ蛻苓ｨ伜捷(A, B, ... , Y, Z, AA, AB, ...)
	 */
	public static String cellNo(int no) {
		int div = no / 26;
		int mod = no % 26;
		String atos1 = "";
		String atos2 = "";
		Integer ivalue = 0;
		byte[] bvalue = new byte[1];

		try {
			if (div > 0) {
				ivalue = div + 0x40;
				bvalue[0] = ivalue.byteValue();
				atos1 = new String(bvalue, "US-ASCII");
			}
			ivalue = mod + 0x41;
			bvalue[0] = ivalue.byteValue();
			atos2 = new String(bvalue, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return atos1 + atos2;
	}

	/**
	 * 繧ｻ繝ｫ逡ｪ蝨ｰ縺九ｉA1蠖｢蠑上∈縺ｮ螟画峩
	 *
	 * @param col 			蛻礼分蜿ｷ
	 * @param row 			陦檎分蜿ｷ
	 * @return				A1蠖｢蠑乗枚蟄怜�
	 */
	public static String cellA1(int col, int row) {
		return cellA1(col, row, false, false);
	}

	/**
	 * 繧ｻ繝ｫ逡ｪ蝨ｰ縺九ｉA1蠖｢蠑上∈縺ｮ螟画峩
	 *
	 * @param col 			蛻礼分蜿ｷ
	 * @param row 			陦檎分蜿ｷ
	 * @param col_fixed 	蛻礼分蜿ｷ邨ｶ蟇ｾ謖�ｮ�
	 * @param row_fixed 	陦檎分蜿ｷ邨ｶ蟇ｾ謖�ｮ�
	 * @return				A1蠖｢蠑乗枚蟄怜�
	 */
	public static String cellA1(int col, int row, boolean col_fixed, boolean row_fixed) {
		return (col_fixed ? "$" : "") + cellNo(col) + (row_fixed ? "$" : "") + String.valueOf(row + 1);
	}

	/**
	 * 繧ｻ繝ｫ蛟､ 蜿門ｾ�
	 *
	 * @param row 			Row
	 * @param column_no		蛻礼分蜿ｷ
	 * @return				繧ｻ繝ｫ蛟､
	 */
	public static String getItem(Row row, int column_no) {

		String data = "";
		Cell cell = row.getCell(column_no);
		int type = cell.getCellType();

		switch (type) {
		case Cell.CELL_TYPE_NUMERIC:
			data = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			data = cell.getStringCellValue().toString().trim();
			break;
		case Cell.CELL_TYPE_FORMULA:
			data = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			data = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			data = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			break;
		}

		return data;
	}

	/**
	 * 繧ｻ繝ｫ蛟､繧呈枚蟄怜�縺ｸ螟画鋤
	 *
	 * @param data 		繧ｻ繝ｫ蛟､
	 * @return			螟画鋤譁�ｭ怜�
	 */
	public static String itemToS(double data) {
		String item = "";
		item = String.valueOf(data);
		return item;
	}

	/**
	 * 繧ｻ繝ｫ蛟､繧呈枚蟄怜�縺ｸ螟画鋤
	 *
	 * @param data 		繧ｻ繝ｫ蛟､
	 * @return			螟画鋤譁�ｭ怜�
	 */
	public static String itemToS(int data) {
		String item = "";
		item = String.valueOf(data);
		return item;
	}

	/**
	 * 繧ｻ繝ｫ蛟､繧呈枚蟄怜�縺ｸ螟画鋤
	 *
	 * @param data 		繧ｻ繝ｫ蛟､
	 * @return			螟画鋤譁�ｭ怜�
	 */
	public static String itemToS(String data) {
		String item = "";
		if (data != null)
			item = data;
		return item;
	}

	/**
	 * 陦梧ｯ碑ｼ�
	 *
	 * @param src 		豈碑ｼ��繧ｷ繝ｼ繝�
	 * @param dst 		豈碑ｼ��繧ｷ繝ｼ繝�
	 * @param st 		豈碑ｼ��髢句ｧ玖｡�
	 * @param en 		豈碑ｼ��邨ゆｺ�｡�
	 * @param dst_row	豈碑ｼ��陦�
	 * @return			true:荳��,false:荳堺ｸ��
	 */
	public static boolean rowCheck(Sheet src, Sheet dst, int st, int en, int dst_row) {
		boolean ret = true;
		int r_cnt = 0;
		for (int i = st; i <= en; i++) {
			Row r = src.getRow(i);
			if (r != null) {
				Row dst_r = dst.getRow(dst_row + r_cnt);
				if (dst_r == null) {
					ret = false;
				} else {
					for (int j = 0; j < r.getLastCellNum(); j++) {
						Cell cell = r.getCell(j);
						Cell dst_cell = dst_r.getCell(j);
						if (cell == null) {
							if (dst_cell != null) {
								ret = false;
							}
						} else {
							if (dst_cell == null) {
								ret = false;
							} else {
							if (!getItem(r, j).equals(getItem(dst_r, j))) {
									ret = false;
								}
							}
						}
					}
				}
			}
			r_cnt++;
		}
		return ret;
	}

	/**
	 * 譌･莉倥ョ繝ｼ繧ｿ繧定ｨｭ螳壹☆繧�
	 *
	 * @param cell 		Cell
	 * @param value 	Data蝙区律莉�
	 */
	public static void setDateCellValue(Cell cell, Date value) {

		DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		String s = fmt.format(value);
		Date set_value = null;
		try {
			set_value = fmt.parse(s);
		} catch (ParseException e) {
		}
		cell.setCellValue(set_value);
	}

	/**
	 * 譌･譎ゅョ繝ｼ繧ｿ繧定ｨｭ螳壹☆繧�
	 *
	 * @param cell 		Cell
	 * @param value 	Data蝙区律譎�
	 */
	public static void setDatetimeCellValue(Cell cell, Date value) {
		cell.setCellValue(value);
	}

	/**
	 * 譌･莉倥ョ繝ｼ繧ｿ繧貞叙蠕励☆繧�
	 *
	 * @param cell		Cell
	 * @return			譌･莉�
	 */
	public static String getDateCellValue(Cell cell) {
		String value = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell))
			{
				//譌･莉伜梛縺ｮ蝣ｴ蜷�
				Date date = cell.getDateCellValue();
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				value = df.format(date);
			}
			break;
		}
		return value;
	}

	/**
	 *  譌･譎ゅョ繝ｼ繧ｿ繧貞叙蠕励☆繧�
	 *
	 * @param cell 		Cell
	 * @return			譌･譎�
	 */
	public static String getDatetimeCellValue(Cell cell) {
		String value = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell))
			{
				//譌･莉伜梛縺ｮ蝣ｴ蜷�
				Date date = cell.getDateCellValue();
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				value = df.format(date);
			}
			break;
		}
		return value;
	}
	
	/**
	 * Counts the number of lines in the cell corresponding to the content on
	 * that cell
	 * 
	 * @param content
	 * @param limitCharacterInWidth
	 * @return line number
	 */
	public static int countLineNumber(String content, int limitCharacterInWidth) {
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
