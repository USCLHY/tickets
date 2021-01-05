package com.yc.tickets.dto;

public class JsonObject {
	private int total;
	private Object rows;
	
	@Override
	public String toString() {
		return "JsonObject [total=" + total + ", rows=" + rows + "]";
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the rows
	 */
	public Object getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public JsonObject(int total, Object rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public JsonObject() {
		
	}
	
	
}
