package model.entity;

import java.util.Date;

/**
 * タスクの表示用Bean
 * @author こせき
 *
 */
public class CommentBean {
	private String commentContent;
	private String commentUser;
	private Date commentDate;

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
	public Date getCommentDate() {
		return commentDate;
	}
	/**
	 * @param commentDate セットする commentDate
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
}
