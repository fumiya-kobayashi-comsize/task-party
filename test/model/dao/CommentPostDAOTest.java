package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.CommentBean;

/**
 * コメント投稿メソッドのテストクラス
 * @author 根上
 *
 */
public class CommentPostDAOTest {

	@Test
	void postComment() {
		int count = 0;
		CommentPostDAO postDAO = new CommentPostDAO();
		CommentBean commnetBean = new CommentBean();
		//		dbに存在するタスクIDを入力
		commnetBean.setTaskId(1);
		commnetBean.setCommentUser("admin");
		commnetBean.setCommentContent("test");
		try {
			count = postDAO.postComment(commnetBean);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロックd
			e.printStackTrace();
		}
		assertEquals(1, count);
	}

	@Test
	void postCommentFailure() {
		int count = 0;
		CommentPostDAO postDAO = new CommentPostDAO();
		CommentBean commnetBean = new CommentBean();
		commnetBean.setTaskId(0);
		commnetBean.setCommentUser("");
		commnetBean.setCommentContent("");
		try {
			count = postDAO.postComment(commnetBean);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロックd
			e.printStackTrace();
		}
		assertEquals(0, count);
	}
}
