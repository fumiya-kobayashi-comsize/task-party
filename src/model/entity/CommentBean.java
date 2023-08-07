package model.entity;

/**
 * タスクの表示用Bean
 * @author こせき
 *
 */
public class CommentBean {
	private int taskId;
	private String commentContent;
	private String commentUser;
	private String commentDate;

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
	 * @return commentContent
	 */
	public String getCommentContent() {
		return commentContent;
	}
	/**
	 * @param commentContent セットする commentContent
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	/**
	 * @return commentUser
	 */
	public String getCommentUser() {
		return commentUser;
	}
	/**
	 * @param commentUser セットする commentUser
	 */
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}
	/**
	 * @return commentDate
	 */
	public String getCommentDate() {
		return commentDate;
	}
	/**
	 * @param string セットする commentDate
	 */
	public void setCommentDate(String string) {
		this.commentDate = string;
	}
}
