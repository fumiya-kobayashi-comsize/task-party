package model.entity;

import java.time.LocalDate;

/**
 * タスク表示用の情報を
 * 格納するBean
 * @author Koseki
 *
 */
public class TaskShowBean {
	private int taskId;
	private String taskName;
	private String categoryName;
	private LocalDate startDate;
	private LocalDate limitDate;
	private String userName;
	private String statusName;
	private String memo;
	/**
	 * @return taskId
	 */
	public int getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId セットする taskId
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName セットする taskName
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName セットする categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate セットする startDate
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return limitDate
	 */
	public LocalDate getLimitDate() {
		return limitDate;
	}
	/**
	 * @param limitDate セットする limitDate
	 */
	public void setLimitDate(LocalDate limitDate) {
		this.limitDate = limitDate;
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
	 * @return statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName セットする statusName
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo セットする memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
