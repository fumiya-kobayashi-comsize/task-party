package model.entity;

/**
 * タスクの表示用Bean
 * @author こせき
 *
 */
public class CommentBean {
	private int taskId;
	private int commentId;
	private String commentUserName;
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
	 * @return commentId
	 */
	public int getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId セットする commentId
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	/**
	 * @return commentUserName
	 */
	public String getCommentUserName() {
		return commentUserName;
	}
	/**
	 * @param commentUserName セットする commentUserName
	 */
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
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
	 * @param commentDate セットする commentDate
	 */
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}


}
