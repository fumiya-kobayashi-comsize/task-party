package model.entity;

import java.util.Date;

/**
 * user情報を格納するクラス
 * @author 根上
 *
 */
public class UserBean {
	String userId;
	String pass;
	String userName;
	Date updateTime;
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass セットする pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime セットする updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



}
