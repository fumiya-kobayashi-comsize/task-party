package model.entity;

/**
 * user情報を格納するクラス
 * @author Negami
 *
 */
public class UserBean {
	String userId;
	String pass;
	String userName;
	boolean admin;
	int atpt;
	boolean locked;
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
	 * @return admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * @param admin セットする admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	/**
	 * @return atpt
	 */
	public int getAtpt() {
		return atpt;
	}
	/**
	 * @param atpt セットする atpt
	 */
	public void setAtpt(int atpt) {
		this.atpt = atpt;
	}
	/**
	 * @return locked
	 */
	public boolean getLocked() {
		return locked;
	}
	/**
	 * @param locked セットする locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}


}
