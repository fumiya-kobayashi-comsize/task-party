package model.entity;

import java.util.Date;

/**
 * ステータスのBean
 * @author arakawa
 *
 */
public class StatusBean {
	private String status_code;
	private String status_name;
	private Date update_datetime;
	/**
	 * @return status_code
	 */
	public String getStatus_code() {
		return status_code;
	}
	/**
	 * @param status_code セットする status_code
	 */
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	/**
	 * @return status_name
	 */
	public String getStatus_name() {
		return status_name;
	}
	/**
	 * @param status_name セットする status_name
	 */
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
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
