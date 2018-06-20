package jp.co.cyms.common.bean;

import java.text.DecimalFormat;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class ExcelBean {
	private int row;
	private int col;
	private String value;
	private String style;
	DecimalFormat formatter = new DecimalFormat("#,###.00");
	protected static Logger LOG = LoggerFactory.getLogger(ExcelBean.class);

	public ExcelBean(int row, int col, Float value, String style) {
		super();
		this.row = row;
		this.col = col;
		if (value != null && value > 0){
			this.value = formatter.format(value);
		} else {
			this.value = "0.00";
		}
		this.style = style;
	}

	public ExcelBean(int row, int col, String value, String style) {
		super();
		this.row = row;
		this.col = col;
		this.value = value;
		this.style = style;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
