package model.entity;

import java.time.LocalDate;

/**
 * タスクの表示用Bean
 * @author こせき
 *
 */
public class CommentBean {
	private String commentContent;
	private String commentUser;
	private LocalDate commentDate;

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
	public LocalDate getCommentDate() {
		return commentDate;
	}
	/**
	 * @param date セットする commentDate
	 */
	public void setCommentDate(LocalDate date) {
		this.commentDate = date;
	}
}
