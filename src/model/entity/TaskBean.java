package model.entity;

import java.time.LocalDate;

/**
 * タスクのBean
 * @author arakawa
 *
 */
public class TaskBean {
	private int taskId;
	private String taskName;
	private int categoryId;
	private LocalDate limitDate;
	private String userId;
	private String statusCode;
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
	 * @return categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId セットする categoryId
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	 * @return statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode セットする statusCode
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
	/* (非 Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((limitDate == null) ? 0 : limitDate.hashCode());
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		result = prime * result + taskId;
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	/* 全部のフィールドを比較して等価判定
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		if (categoryId != other.categoryId)
			return false;
		if (limitDate == null) {
			if (other.limitDate != null)
				return false;
		} else if (!limitDate.equals(other.limitDate))
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		if (taskId != other.taskId)
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}



}
