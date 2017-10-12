package com.noisyle.demo.itextpdf;

import java.util.List;
import java.util.Map;

public class TableData {

	private int total;
	private List<Map<String, Object>> rows;
	private List<Map<String, Object>> footer;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public List<Map<String, Object>> getFooter() {
		return footer;
	}

	public void setFooter(List<Map<String, Object>> footer) {
		this.footer = footer;
	}

	@Override
	public String toString() {
		return String.format("total:%d rows:%s", total, rows.size());
	}
}
