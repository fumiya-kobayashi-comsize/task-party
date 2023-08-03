package model.entity;

import java.util.Date;

/**
 * カテゴリーのBean
 * @author arakawa
 *
 */
public class CategoryBean {
	private int category_id;
	private String category_name;
	private Date update_datetime;
	/**
	 * @return category_id
	 */
	public int getCategory_id() {
		return category_id;
	}
	/**
	 * @param category_id セットする category_id
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	/**
	 * @return category_name
	 */
	public String getCategory_name() {
		return category_name;
	}
	/**
	 * @param category_name セットする category_name
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	/**
	 * @return update_datetime
	 */
	public Date getUpdate_datetime() {
		return update_datetime;
	}
	/**
	 * @param update_datetime セットする update_datetime
	 */
	public void setUpdate_datetime(Date update_datetime) {
		this.update_datetime = update_datetime;
	}



}
