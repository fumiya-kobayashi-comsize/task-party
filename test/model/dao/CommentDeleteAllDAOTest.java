package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

/**
 * タスクに紐づいているコメントを全て削除するメソッドのテストクラス
 * @author 根上
 *
 */
public class CommentDeleteAllDAOTest {
	@Test
	void deleteAllComment() {
		CommentDeleteAllDAO deleteAllDAO = new CommentDeleteAllDAO();
		int count = 0;

		try {
//			comment tableにあるtaskIdを引数に入れる
			count = deleteAllDAO.deleteAllComment(2);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals(7, count);
	}
	@Test
	void deleteAllCommentFailure() {
		CommentDeleteAllDAO deleteAllDAO = new CommentDeleteAllDAO();
		int count = 0;

		try {
			count = deleteAllDAO.deleteAllComment(0);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals(0, count);
	}
}
