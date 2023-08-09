package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

/**
 * コメント削除メソッドのテストクラス
 * @author 根上
 *
 */
public class CommentDeleteDAOTest {
	@Test
	void deleteComment() {
		CommentDeleteDAO deleteDAO = new CommentDeleteDAO();
		int count = 0;

		try {
//			dbにあるコメントIDを引数に入れる
			count = deleteDAO.deleteComment(13);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1, count);
	}
	@Test
	void deleteCommentFailure() {
		CommentDeleteDAO deleteDAO = new CommentDeleteDAO();
		int count = 0;

		try {
			count = deleteDAO.deleteComment(0);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals(0, count);
	}
}
